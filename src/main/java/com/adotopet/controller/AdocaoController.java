package com.adotopet.controller;

import com.adotopet.dtos.request.AdocaoRequest;
import com.adotopet.dtos.response.AdocaoResponse;
import com.adotopet.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/adocao")
public class AdocaoController {
    private final AdocaoService adocaoService;

    public AdocaoController(AdocaoService adocaoService){
        this.adocaoService = adocaoService;
    }

    @PostMapping("/registrar")
    public AdocaoResponse registrarAdocao(@Valid @RequestBody AdocaoRequest adocaoRequest) {
        return adocaoService.registrarAdocao(adocaoRequest.getAdotanteId(), adocaoRequest.getAnimalId());
    }

    @GetMapping("/listar-todos")
    public Page<AdocaoResponse> listarTodos(@PageableDefault(size = 10)Pageable pageable) {
        return adocaoService.listarTodasAdocao(pageable);
    }

    @PutMapping("/atualizar/{adocaoId}")
    public AdocaoResponse atualizarAdocao(@PathVariable UUID adocaoId, @RequestBody @Valid AdocaoRequest adocaoRequest) {
        return adocaoService.atualizarAdocao(adocaoId, adocaoRequest.getAdotanteId(), adocaoRequest.getAnimalId());
    }

    @DeleteMapping("/deletar/{adocaoId}")
    public void deletarAdocao(@PathVariable UUID adocaoId) {
        adocaoService.deletarAdocao(adocaoId);
    }

    @PatchMapping("/finalizar/{adocaoId}")
    public AdocaoResponse finalizarAdocao(@PathVariable UUID adocaoId) {
        return adocaoService.finalizarAdocao(adocaoId);
    }

    @PatchMapping("/cancelar/{adocaoId}")
    public AdocaoResponse cancelarAdocao(@PathVariable UUID adocaoId) {
        return adocaoService.cancelarAdocao(adocaoId);
    }
}
