package com.adotopet.entities;

import com.adotopet.enums.StatusAdocaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "adocao")
@AllArgsConstructor
@NoArgsConstructor
public class AdocaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "adotante_id", nullable = false)
    private AdotanteEntity adotante;

    @OneToOne
    @JoinColumn(name = "animal_id", nullable = false, unique = true)
    private AnimalEntity animal;

    private LocalDate dataAdocao = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status_adocao", nullable = false)
    private StatusAdocaoEnum statusAdocaoEnum;

}