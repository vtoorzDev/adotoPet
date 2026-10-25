package com.adotopet.controller;

import com.adotopet.dtos.request.AdotanteParcialRequest;
import com.adotopet.dtos.request.AdotanteRequest;
import com.adotopet.dtos.response.AdotanteResponse;
import com.adotopet.service.AdotanteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/adotante")
public class AdotanteController {
    private final AdotanteService adotanteService;

    public AdotanteController(AdotanteService adotanteService) {
        this.adotanteService = adotanteService;
    }

    @PostMapping("/registrar")
    public AdotanteResponse registrar(@Valid @RequestBody AdotanteRequest adotanteRequest) {
        return adotanteService.registrarAdotante(adotanteRequest);
    }

    @GetMapping("/listar-todos")
    public Page<AdotanteResponse> listarTodos(@PageableDefault(size = 10)Pageable pageable) {
        return adotanteService.listarTodos(pageable);
    }

    @GetMapping("listar-nome/{nome}")
    public Page<AdotanteResponse> listarNome(@PathVariable String nome,  @PageableDefault(size = 10) Pageable pageable) {
        return adotanteService.listarNome(nome, pageable);
    }

    @GetMapping("listar-cpf/{cpf}")
    public AdotanteResponse listarCpf(@PathVariable String cpf) {
        return adotanteService.listarCpf(cpf);
    }

    @PutMapping("/atualizar/{cpf}")
    public AdotanteResponse atualizarCompleto(@RequestBody @Valid AdotanteRequest adotanteRequest, @PathVariable String cpf) {
        return adotanteService.atualizarAdotante(adotanteRequest, cpf);
    }

    @PatchMapping("/atualizar/{cpf}")
    public AdotanteResponse atualizarParcial(@RequestBody @Valid AdotanteParcialRequest adotanteParcialRequest, @PathVariable String cpf) {
        return adotanteService.atualizarParcial(adotanteParcialRequest, cpf);
    }

    @DeleteMapping("/deletar/{cpf}")
    public void deletarAdotante(@PathVariable String cpf) {
        adotanteService.deletarAdotante(cpf);
    }
}
