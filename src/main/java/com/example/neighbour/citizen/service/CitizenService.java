package com.example.neighbour.citizen.service;

import com.example.neighbour.citizen.dto.CitizenCreationRequestDto;
import com.example.neighbour.citizen.dto.CitizenDto;
import com.example.neighbour.citizen.dto.CitizenUpdateRequestDto;
import com.example.neighbour.citizen.model.Citizen;
import com.example.neighbour.citizen.repository.CitizenRepository;
import com.example.neighbour.flat.dto.FlatDto;
import com.example.neighbour.flat.service.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final FlatService flatService;

    @Transactional
    public CitizenDto create(CitizenCreationRequestDto citizen) {
        if (citizenRepository.existsByName(citizen.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Citizen model = citizenRepository.save(citizen.toModel());
        return new CitizenDto(model);
    }

    public Optional<CitizenDto> getById(UUID uid) {
        return citizenRepository.findById(uid).map(CitizenDto::new);
    }

    public Page<CitizenDto> getAll(Pageable pageable) {
        return citizenRepository.findAll(pageable).map(CitizenDto::new);
    }

    @Transactional
    public Optional<CitizenDto> update(CitizenUpdateRequestDto citizen) {
        Optional<Citizen> entity = citizenRepository.findById(citizen.getId()).map(
                it -> {
                    Citizen model = citizen.toModel();

                    Optional.ofNullable(model.getName()).ifPresent(it::setName);

                    return citizenRepository.save(it);
                }
        );

        return entity.map(CitizenDto::new);
    }

    public void delete(UUID uid) {
        citizenRepository.deleteById(uid);
    }

    @Transactional
    public CitizenDto attachFlat(UUID id, UUID flatId) {
        Citizen citizen = citizenRepository.getReferenceById(id);
        Optional<FlatDto> flat = flatService.getById(flatId);

        if (flat.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        citizen.setFlat(flat.get().toModel());

        return new CitizenDto(citizenRepository.saveAndFlush(citizen));
    }

    @Transactional
    public CitizenDto detachFlat(UUID id) {
        Citizen citizen = citizenRepository.getReferenceById(id);

        if (citizen.getFlat() == null) {
            return new CitizenDto(citizen);
        }

        citizen.setFlat(null);

        return new CitizenDto(citizenRepository.saveAndFlush(citizen));
    }
}
