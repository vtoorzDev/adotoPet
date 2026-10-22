package com.adotopet.repository;

import com.adotopet.entities.AdotanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdotanteRepository extends JpaRepository<AdotanteEntity, UUID> {
    boolean existsByCpf(String cpf);
    List<AdotanteEntity> findByNome(String nome);
    Optional<AdotanteEntity> findByCpf(String cpf);
}
