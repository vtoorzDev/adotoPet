package com.adotopet.service;

import com.adotopet.dtos.request.AdocaoRequest;
import com.adotopet.dtos.response.AdocaoResponse;
import com.adotopet.entities.AdocaoEntity;
import com.adotopet.entities.AdotanteEntity;
import com.adotopet.entities.AnimalEntity;
import com.adotopet.enums.StatusAdocaoEnum;
import com.adotopet.exceptions.AdocaoException;
import com.adotopet.exceptions.AdotanteException;
import com.adotopet.exceptions.AnimalException;
import com.adotopet.repository.AdocaoRepository;
import com.adotopet.repository.AdotanteRepository;
import com.adotopet.repository.AnimalRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdocaoService {
    private final AdocaoRepository adocaoRepository;
    private final AdotanteRepository adotanteRepository;
    private final AnimalRepository animalRepository;

    public AdocaoService(AdocaoRepository adocaoRepository, AdotanteRepository adotanteRepository, AnimalRepository animalRepository) {
        this.adocaoRepository = adocaoRepository;
        this.adotanteRepository = adotanteRepository;
        this.animalRepository = animalRepository;
    }

    private AdocaoResponse transformResponse(AdocaoEntity adocaoEntity) {
        AdocaoResponse adocaoResponse = new AdocaoResponse();

        adocaoResponse.setId(adocaoEntity.getId());
        adocaoResponse.setAdotanteId(adocaoEntity.getAdotante().getId());
        adocaoResponse.setAnimalId(adocaoEntity.getAnimal().getId());
        adocaoResponse.setStatusAdocaoEnum(adocaoEntity.getStatusAdocaoEnum());

        return adocaoResponse;
    }

    public AdocaoResponse registrarAdocao(UUID adotanteId, UUID animalId) {
        AdotanteEntity adotanteEcontrado = adotanteRepository.findById(adotanteId)
                .orElseThrow(() -> new AdotanteException("Adotante não registrado"));

        AnimalEntity animalEncontrado = animalRepository.findById(animalId)
                .orElseThrow(() -> new AnimalException("Animal não registrado"));

        AdocaoEntity adocao = new AdocaoEntity();

        adocao.setAdotante(adotanteEcontrado);
        adocao.setAnimal(animalEncontrado);
        adocao.setStatusAdocaoEnum(StatusAdocaoEnum.PENDENTE);

        AdocaoEntity adocaoRegistrada = adocaoRepository.save(adocao);

        return transformResponse(adocaoRegistrada);

    }

    public List<AdocaoResponse> listarTodasAdocao() {
        List<AdocaoEntity> listaAdocao = adocaoRepository.findAll();

        if (listaAdocao.isEmpty()) {
            throw new AdocaoException("Nenhuma adoção registrada");
        }

        return listaAdocao.stream().map(this::transformResponse).toList();
    }

    public AdocaoResponse atualizarAdocao( UUID adocaoId, UUID adotanteId, UUID animalId) {
        AdocaoEntity adocaoEncontrada = adocaoRepository.findById(adocaoId)
                .orElseThrow(() -> new AdocaoException("Adoção não encontrada"));

        AdotanteEntity adotanteEncontrado = adotanteRepository.findById(adotanteId)
                .orElseThrow(() -> new AdotanteException("Adotante não encontrado"));

        AnimalEntity animalEncontrado = animalRepository.findById(animalId)
                .orElseThrow(() -> new AnimalException("Animal não encontrado"));

        adocaoEncontrada.setAdotante(adotanteEncontrado);
        adocaoEncontrada.setAnimal(animalEncontrado);

        AdocaoEntity adocaoAtualizada = adocaoRepository.save(adocaoEncontrada);

        return transformResponse(adocaoAtualizada);

    }

    public void deletarAdocao(UUID adocaoId) {
        AdocaoEntity adocaoEncontrada = adocaoRepository.findById(adocaoId)
                .orElseThrow(() -> new AdocaoException("Adoção não encontrada"));

        adocaoRepository.delete(adocaoEncontrada);
    }

}
