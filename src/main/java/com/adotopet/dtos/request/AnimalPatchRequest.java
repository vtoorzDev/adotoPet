
package com.adotopet.dtos.request;

import com.adotopet.enums.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalPatchRequest {

    private String nome;

    private String descricao;

    private Integer idade;

    private EspecieEnum especieEnum;

    private PorteEnum porteEnum;

    private SexoEnum sexoEnum;

    private StatusAnimalEnum statusEnum;
}
