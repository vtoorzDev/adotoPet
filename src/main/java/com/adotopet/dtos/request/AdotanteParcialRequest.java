
package com.adotopet.dtos.request;

import com.adotopet.enums.AdotanteEnum;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdotanteParcialRequest {

    @Size(min = 3, max = 100)
    private String nome;

    @Size(min = 11, max = 11)
    private String cpf;

    private LocalDate dataNascimento;

    private AdotanteEnum adotanteEnum;
}