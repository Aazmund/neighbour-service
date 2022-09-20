package com.example.neighbour.service;

import com.example.neighbour.model.Citizen;
import com.example.neighbour.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;

    public Citizen create(Citizen citizen) {
        if (citizenRepository.existsById(citizen.getUid())) {
            //todo create exceptions
        }
        return citizenRepository.save(citizen);
    }

    public Optional<Citizen> getById(UUID uid) {
        return citizenRepository.findById(uid);
    }

    public Page<Citizen> getAll(Pageable pageable) {
        return citizenRepository.findAll(pageable);
    }

    public Optional<Citizen> update(Citizen citizen) {
        return citizenRepository.findById(citizen.getUid())
                .map(it -> {
                    Optional.ofNullable(citizen.getName()).ifPresent(it::setName);
                    Optional.ofNullable(citizen.getFlat()).ifPresent(it::setFlat);
                    return it;
                }).map(citizenRepository::save);
    }

    public void delete(Citizen citizen) {
        citizenRepository.delete(citizen);
    }
}
