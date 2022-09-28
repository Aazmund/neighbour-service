package com.example.neighbour.street.service;

import com.example.neighbour.street.dto.StreetCreationRequestDto;
import com.example.neighbour.street.dto.StreetDto;
import com.example.neighbour.street.dto.StreetUpdateRequestDto;
import com.example.neighbour.street.model.Street;
import com.example.neighbour.street.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreetService {

    private final StreetRepository streetRepository;

    public StreetDto create(StreetCreationRequestDto street) {
        if (streetRepository.existsByName(street.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Street model = streetRepository.save(street.toModel());
        return new StreetDto(model);
    }

    public Optional<StreetDto> getById(UUID uid) {
        return streetRepository.findById(uid).map(StreetDto::new);
    }

    public Page<StreetDto> getAll(Pageable pageable) {
        return streetRepository.findAll(pageable).map(StreetDto::new);
    }

    public Optional<StreetDto> update(StreetUpdateRequestDto street) {
        Optional<Street> entity = streetRepository.findById(street.getUid()).map(
                it -> {
                    Street model = street.toModel();
                    Optional.ofNullable(model.getName()).ifPresent(it::setName);
                    return streetRepository.save(it);
                }
        );
        return entity.map(StreetDto::new);
    }

    public void delete(UUID uid) {
        streetRepository.deleteById(uid);
    }
}
