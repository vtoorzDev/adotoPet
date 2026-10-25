package com.adotopet.repository;

import com.adotopet.entities.AdocaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.UUID;

@Repository
public interface AdocaoRepository extends JpaRepository<AdocaoEntity, UUID> {
    boolean existsById(UUID id);
}
