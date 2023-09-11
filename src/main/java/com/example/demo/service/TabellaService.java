package com.example.demo.service;

import com.example.demo.entity.TabellaEntity;
import com.example.demo.repository.TabellaJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class TabellaService
{
    private final TabellaJpaRepository repository;

    public Optional<TabellaEntity> findById(UUID id) {
        return repository.findById(id);
    }

    public List<TabellaEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TabellaEntity save(TabellaEntity entity) {
        return repository.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TabellaEntity saveRequiresNew(TabellaEntity entity) {
        return repository.save(entity);
    }
}
