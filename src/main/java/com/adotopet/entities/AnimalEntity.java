
package com.adotopet.entities;

import com.adotopet.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "animal")
@AllArgsConstructor
@NoArgsConstructor
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer idade;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "especie_animal", nullable = false)
    private EspecieEnum especieEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "porte_animal", nullable = false)
    private PorteEnum porteEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo_animal", nullable = false)
    private SexoEnum sexoEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_animal", nullable = false)
    private StatusAnimalEnum statusAnimalEnum;
}
