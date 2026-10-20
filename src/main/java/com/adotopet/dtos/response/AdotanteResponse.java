package com.adotopet.dtos.response;

import com.adotopet.enums.AdotanteEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AdotanteResponse {
    private UUID id;
    private String name;
    private String cpf;
    private LocalDate dataNascimento;
    private AdotanteEnum adotanteEnum;
}
