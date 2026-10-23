package com.adotopet.repository;

import com.adotopet.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, UUID> {
    boolean existsByNome(String nome);
    boolean existsById(UUID id);
    List<AnimalEntity> findByNome(String nome);
}
