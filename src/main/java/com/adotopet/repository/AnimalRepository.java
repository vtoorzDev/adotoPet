package com.adotopet.repository;

import com.adotopet.entities.AnimalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, UUID> {
    boolean existsByNome(String nome);
    Page<AnimalEntity> findByNome(String nome, Pageable pageable);
}
