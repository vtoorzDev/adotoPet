package com.adotopet.service;

import com.adotopet.dtos.request.AnimalRequest;
import com.adotopet.dtos.response.AnimalResponse;
import com.adotopet.entities.AnimalEntity;
import com.adotopet.enums.StatusAnimalEnum;
import com.adotopet.exceptions.AnimalException;
import com.adotopet.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    private AnimalResponse transformResponse(AnimalEntity animalEntity) {
        AnimalResponse animalResponse = new AnimalResponse();

        animalResponse.setId(animalEntity.getId());
        animalResponse.setNome(animalEntity.getNome());
        animalResponse.setDescricao(animalEntity.getDescricao());
        animalResponse.setIdade(animalEntity.getIdade());
        animalResponse.setStatusAnimalEnum(animalEntity.getStatusAnimalEnum());
        animalResponse.setDataRegistro(animalEntity.getDataRegistro());
        animalResponse.setEspecieEnum(animalEntity.getEspecieEnum());
        animalResponse.setPorteEnum(animalEntity.getPorteEnum());

        return animalResponse;
    }

    public AnimalResponse registrarAnimal(AnimalRequest animalRequest) {
        boolean animalEncontrado = animalRepository.findByNome(animalRequest.getNome());

        if (animalEncontrado) {
            throw new AnimalException("Animal já registrado no sistema");

        }
        AnimalEntity animalRegistrado = new AnimalEntity();

        animalRegistrado.setNome(animalRequest.getNome());
        animalRegistrado.setDescricao(animalRequest.getDescricao());
        animalRegistrado.setIdade(animalRequest.getIdade());
        animalRegistrado.setStatusAnimalEnum(StatusAnimalEnum.DISPONIVEL);
        animalRegistrado.setDataRegistro(animalRegistrado.getDataRegistro());
        animalRegistrado.setEspecieEnum(animalRequest.getEspecieEnum());
        animalRegistrado.setPorteEnum(animalRequest.getPorteEnum());

        AnimalEntity animalSalvo = animalRepository.save(animalRegistrado);

        return transformResponse(animalSalvo);
    }
}
