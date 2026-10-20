package com.adotopet.controller;

import com.adotopet.dtos.request.AnimalRequest;
import com.adotopet.dtos.response.AnimalResponse;
import com.adotopet.service.AnimalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/registrar")
    public AnimalResponse registrarAnimal(AnimalRequest animalRequest) {
        return animalService.registrarAnimal(animalRequest);
    }
}
