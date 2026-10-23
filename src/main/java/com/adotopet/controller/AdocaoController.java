package com.adotopet.controller;

import com.adotopet.dtos.request.AdocaoRequest;
import com.adotopet.dtos.response.AdocaoResponse;
import com.adotopet.service.AdocaoService;
import jakarta.validation.Valid;
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
    public List<AdocaoResponse> listarTodaaAdocao() {
        return adocaoService.listarTodasAdocao();
    }

    @PutMapping("/atualizar/{id}")
    public AdocaoResponse atualizarAdocao(@PathVariable UUID adocaoId, UUID adotanteId, UUID animalId){
        return adocaoService.atualizarAdocao(adocaoId, adotanteId, animalId);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarAdocao(@PathVariable UUID adocaoId) {
        adocaoService.deletarAdocao(adocaoId);
    }

    @PatchMapping("/finalizar/{id}/")
    public AdocaoResponse finalizarAdocao(@PathVariable UUID adocaoId){
        return adocaoService.finalizarAdocao(adocaoId);
    }
}
