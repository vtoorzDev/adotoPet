package com.adotopet.dtos.request;

import com.adotopet.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalRequest {

    @NotBlank
    @Size(min = 3, max = 255)
    private String nome;

    @NotBlank
    @Size(min = 10, max = 300)
    private String descricao;

    @NotNull
    @Positive
    private Integer idade;

    @NotNull
    private EspecieEnum especieEnum;

    @NotNull
    private PorteEnum porteEnum;

    @NotNull
    private SexoEnum sexoEnum;

    @NotNull
    private StatusAnimalEnum statusEnum;
}
