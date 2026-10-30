package com.adotopet.repository;

import com.adotopet.entities.AdotanteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdotanteRepository extends JpaRepository<AdotanteEntity, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<AdotanteEntity> findByCpf(String cpf);
    Page<AdotanteEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Optional<AdotanteEntity> findByEmail(String email);
}