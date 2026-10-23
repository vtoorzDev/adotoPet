package com.adotopet.dtos.request;



import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AdocaoRequest {
    @NotNull
    private UUID adotanteId;

    @NotNull
    private UUID animalId;


}
