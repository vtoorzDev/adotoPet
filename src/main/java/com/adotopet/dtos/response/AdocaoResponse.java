package com.adotopet.dtos.response;

import com.adotopet.enums.StatusAdocaoEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AdocaoResponse {
    private UUID id;
    private UUID adotanteId;
    private UUID animalId;
    private StatusAdocaoEnum statusAdocaoEnum;
}
