package com.adotopet.dtos.response;

import com.adotopet.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AnimalResponse {
    private UUID id;

    private String nome;
    private String descricao;
    private Integer idade;
    private LocalDate dataRegistro = LocalDate.now();

    private EspecieEnum especieEnum;
    private PorteEnum porteEnum;
    private SexoEnum sexoEnum;
    private StatusAnimalEnum statusAnimalEnum;
}
