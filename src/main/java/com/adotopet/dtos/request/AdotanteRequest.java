package com.adotopet.dtos.request;

import com.adotopet.enums.AdotanteEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdotanteRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    private AdotanteEnum adotanteEnum;
}
