package com.example.neighbour.citizen.service;

import com.example.neighbour.citizen.dto.CitizenCreationRequestDto;
import com.example.neighbour.citizen.dto.CitizenDto;
import com.example.neighbour.citizen.dto.CitizenUpdateRequestDto;
import com.example.neighbour.citizen.model.Citizen;
import com.example.neighbour.citizen.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;

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
}
