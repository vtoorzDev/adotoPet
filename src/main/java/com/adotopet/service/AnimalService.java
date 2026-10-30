package com.adotopet.service;

import com.adotopet.dtos.request.AnimalPatchRequest;
import com.adotopet.dtos.request.AnimalRequest;
import com.adotopet.dtos.response.AnimalResponse;
import com.adotopet.entities.AnimalEntity;
import com.adotopet.enums.StatusAnimalEnum;
import com.adotopet.exceptions.AnimalException;
import com.adotopet.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

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

    @Transactional
    public AnimalResponse registrarAnimal(AnimalRequest animalRequest) {
        boolean animalEncontrado = animalRepository.existsByNome(animalRequest.getNome());

        if (animalEncontrado) {
            throw new AnimalException("Animal já registrado no sistema");

        }
        AnimalEntity animalRegistrado = new AnimalEntity();

        animalRegistrado.setNome(animalRequest.getNome());
        animalRegistrado.setDescricao(animalRequest.getDescricao());
        animalRegistrado.setIdade(animalRequest.getIdade());
        animalRegistrado.setSexoEnum(animalRequest.getSexoEnum());
        animalRegistrado.setStatusAnimalEnum(StatusAnimalEnum.DISPONIVEL);
        animalRegistrado.setDataRegistro(LocalDate.now());
        animalRegistrado.setEspecieEnum(animalRequest.getEspecieEnum());
        animalRegistrado.setPorteEnum(animalRequest.getPorteEnum());

        AnimalEntity animalSalvo = animalRepository.save(animalRegistrado);

        return transformResponse(animalSalvo);
    }

    public Page<AnimalResponse> listarTodosAnimais(Pageable pageable) {
        Page<AnimalEntity> animalEntities = animalRepository.findAll(pageable);

        if (animalEntities.isEmpty()) {
            throw new AnimalException("Não tem nenhum animal cadastrado no sistema");
        }

        return animalEntities.map(this::transformResponse);
        }

    public Page<AnimalResponse> buscarAnimal(String nome, Pageable pageable) {
        Page<AnimalEntity> animalEcontrado = animalRepository.findByNome(nome, pageable);

        if (animalEcontrado.isEmpty()) {
            throw new AnimalException("Nenhum animal encontrado com esse nome");
        }

        return animalEcontrado.map(this::transformResponse);
    }


    @Transactional
    public AnimalResponse atualizarAnimalParcial(AnimalPatchRequest animalPatchRequest, UUID id) {
        AnimalEntity animalEncontrado = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalException("Animal não encontrado no sistema"));

        if (animalPatchRequest.getNome() != null) {
            animalEncontrado.setNome(animalPatchRequest.getNome());
        }

        if (animalPatchRequest.getDescricao() != null) {
            animalEncontrado.setDescricao(animalPatchRequest.getDescricao());
        }

        if (animalPatchRequest.getIdade() != null) {
            animalEncontrado.setIdade(animalPatchRequest.getIdade());
        }

        if (animalPatchRequest.getSexoEnum() != null) {
            animalEncontrado.setSexoEnum(animalPatchRequest.getSexoEnum());
        }

        if (animalPatchRequest.getStatusEnum() != null) {
            animalEncontrado.setStatusAnimalEnum(animalPatchRequest.getStatusEnum());
        }

        if (animalPatchRequest.getEspecieEnum() != null) {
            animalEncontrado.setEspecieEnum(animalPatchRequest.getEspecieEnum());
        }

        if (animalPatchRequest.getPorteEnum() != null) {
            animalEncontrado.setPorteEnum(animalPatchRequest.getPorteEnum());
        }

        return transformResponse(animalEncontrado);
    }

    @Transactional
    public AnimalResponse atualizarAnimalCompleto(AnimalRequest animalRequest, UUID id) {
        AnimalEntity animalEncontrado = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalException("Animal não encontrado no sistema"));

        animalEncontrado.setNome(animalRequest.getNome());
        animalEncontrado.setDescricao(animalRequest.getDescricao());
        animalEncontrado.setIdade(animalRequest.getIdade());
        animalEncontrado.setSexoEnum(animalRequest.getSexoEnum());
        animalEncontrado.setStatusAnimalEnum(animalRequest.getStatusEnum());
        animalEncontrado.setEspecieEnum(animalRequest.getEspecieEnum());
        animalEncontrado.setPorteEnum(animalRequest.getPorteEnum());

        return transformResponse(animalEncontrado);
    }

    @Transactional
    public void deletarAnimal(UUID id) {
        AnimalEntity animalEncontrado = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalException("Animal não encontrado"));

        animalRepository.delete(animalEncontrado);
    }

}
