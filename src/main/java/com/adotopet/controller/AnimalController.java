package com.adotopet.controller;

import com.adotopet.dtos.request.AnimalPatchRequest;
import com.adotopet.dtos.request.AnimalRequest;
import com.adotopet.dtos.response.AnimalResponse;
import com.adotopet.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/registrar")
    public AnimalResponse registrarAnimal(@RequestBody @Valid AnimalRequest animalRequest) {
        return animalService.registrarAnimal(animalRequest);
    }

    @GetMapping("/listar-todos")
    public List<AnimalResponse> listarTodosAnimais() {
        return animalService.listarTodosAnimais();
    }

    @GetMapping("/listar-animais")
    public List<AnimalResponse> listarAnimaisNome(@RequestParam String nome) {
        return animalService.buscarAnimal(nome);
    }

    @PatchMapping("/atualizar-animal/{id}")
    public AnimalResponse atualizarAnimalParcial(@RequestBody @Valid AnimalPatchRequest animalPatchRequest, @PathVariable UUID id) {
        return animalService.atualizarAnimalParcial(animalPatchRequest, id);
    }

    @PutMapping("/atualizar-animal/{id}")
    public AnimalResponse atualizarAnimalCompleto(@RequestBody @Valid AnimalRequest animalRequest, @PathVariable UUID id) {
        return animalService.atualizarAnimalCompleto(animalRequest, id);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarAnimal(@PathVariable UUID id) {
         animalService.deletarAnimal(id);
    }
}
