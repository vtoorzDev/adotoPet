package com.adotopet.service;

import com.adotopet.dtos.request.AnimalPatchRequest;
import com.adotopet.dtos.request.AnimalRequest;
import com.adotopet.dtos.response.AnimalResponse;
import com.adotopet.entities.AnimalEntity;
import com.adotopet.enums.StatusAnimalEnum;
import com.adotopet.exceptions.AnimalException;
import com.adotopet.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    public List<AnimalResponse> listarTodosAnimais() {
        List<AnimalEntity> animalEntities = animalRepository.findAll();

        if (animalEntities.isEmpty()) {
            throw new AnimalException("Não tem nenhum animal cadastrado no sistema");
        }

        return animalEntities.stream().map(this::transformResponse).toList();
        }

    public List<AnimalResponse> buscarAnimal(String nome) {
        List<AnimalEntity> animalEcontrado = animalRepository.findByNome(nome);

        if (animalEcontrado.isEmpty()) {
            throw new AnimalException("Nenhum animal encontrado com esse nome");
        }

        return animalEcontrado.stream().map(this::transformResponse).toList();
    }


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

        AnimalEntity animalAtualizado = animalRepository.save(animalEncontrado);

        return transformResponse(animalAtualizado);
    }

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

        AnimalEntity animalAtualizado = animalRepository.save(animalEncontrado);

        return transformResponse(animalAtualizado);
    }

    public void deletarAnimal(UUID id) {
        AnimalEntity animalEncontrado = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalException("Animal não encontrado"));

        animalRepository.delete(animalEncontrado);
    }

}
