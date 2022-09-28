package com.example.neighbour.flat.service;

import com.example.neighbour.flat.model.Flat;
import com.example.neighbour.flat.repository.FlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlatService {

    private final FlatRepository flatRepository;

    public Flat create(Flat flat){
        if (flatRepository.existsById(flat.getUid())) {
            //todo except
        }
        return flatRepository.save(flat);
    }

    public Optional<Flat> getById(UUID uid) {
        return flatRepository.findById(uid);
    }

    public Page<Flat> getAll(Pageable pageable) {
        return flatRepository.findAll(pageable);
    }

    public Optional<Flat> update(Flat flat) {
        return flatRepository.findById(flat.getUid())
                .map(it -> {
                    Optional.ofNullable(flat.getNumber()).ifPresent(it::setNumber);
                    Optional.ofNullable(flat.getCitizens()).ifPresent(it::setCitizens);
                    return it;
                }).map(flatRepository::save);
    }

    public void delete(Flat flat) {
        flatRepository.delete(flat);
    }
}
