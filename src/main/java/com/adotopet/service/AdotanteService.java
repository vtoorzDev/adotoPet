package com.adotopet.service;

import com.adotopet.dtos.request.AdotanteParcialRequest;
import com.adotopet.dtos.request.AdotanteRequest;
import com.adotopet.dtos.response.AdotanteResponse;
import com.adotopet.entities.AdotanteEntity;
import com.adotopet.enums.AdotanteEnum;
import com.adotopet.exceptions.AdotanteException;
import com.adotopet.exceptions.AnimalException;
import com.adotopet.repository.AdotanteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdotanteService {
    private final AdotanteRepository adotanteRepository;

    public AdotanteService(AdotanteRepository adotanteRepository) {
        this.adotanteRepository = adotanteRepository;
    }

    private AdotanteResponse transformResponse(AdotanteEntity adotanteEntity) {
        AdotanteResponse adotanteResponse = new AdotanteResponse();

        adotanteResponse.setId(adotanteEntity.getId());
        adotanteResponse.setNome(adotanteEntity.getNome());
        adotanteResponse.setCpf(adotanteEntity.getCpf());
        adotanteResponse.setDataNascimento(adotanteEntity.getDataNascimento());
        adotanteResponse.setAdotanteEnum(adotanteEntity.getAdotanteEnum());

        return adotanteResponse;
    }

    public AdotanteResponse registrarAdotante (AdotanteRequest adotanteRequest) {
        boolean adotanteEncontrado = adotanteRepository.existsByCpf(adotanteRequest.getCpf());

        if (adotanteEncontrado) {
            throw new AnimalException("Adotante já cadastrado no sistema");
        }

        AdotanteEntity adotanteRegistrado = new AdotanteEntity();

        adotanteRegistrado.setNome(adotanteRequest.getNome());
        adotanteRegistrado.setCpf(adotanteRequest.getCpf());
        adotanteRegistrado.setDataNascimento(adotanteRequest.getDataNascimento());
        adotanteRegistrado.setAdotanteEnum(AdotanteEnum.Ativo);

        AdotanteEntity adotante = adotanteRepository.save(adotanteRegistrado);

        return transformResponse(adotante);
    }

    public Page<AdotanteResponse> listarTodos(Pageable pageable) {
        Page<AdotanteEntity> adotanteEntity= adotanteRepository.findAll(pageable);

        if (adotanteEntity.isEmpty()) {
            throw new AdotanteException("Nenhum adotante cadastrado no sistema");
        }

        return adotanteEntity.map(this::transformResponse);
    }

    public List<AdotanteResponse> listarNome(String nome) {
        List<AdotanteEntity> adotanteEntity = adotanteRepository.findByNome(nome);

        if (adotanteEntity.isEmpty()) {
            throw new AdotanteException("Nenhum adotante com esse nome encontrado no sistema");
        }

        return adotanteEntity.stream().map(this::transformResponse).toList();
    }

    public AdotanteResponse listarCpf(String cpf) {
       AdotanteEntity adotanteEntity = adotanteRepository.findByCpf(cpf)
               .orElseThrow(() -> new AdotanteException("Nenhum adotante encontrado com esse cpf"));

       return transformResponse(adotanteEntity);
        }

    public AdotanteResponse atualizarAdotante(AdotanteRequest adotanteRequest, String cpf) {
        AdotanteEntity adotanteEntity = adotanteRepository.findByCpf(cpf)
                .orElseThrow(() -> new AdotanteException("Nenhum adotante Encontrado com esse cpf"));

        if (adotanteEntity.getAdotanteEnum().equals(AdotanteEnum.Inativo)) {
            throw new AdotanteException("Não foi possivel atualizar, o usuário está inativo");
        }

        adotanteEntity.setNome(adotanteRequest.getNome());
        adotanteEntity.setCpf(adotanteRequest.getCpf());
        adotanteEntity.setDataNascimento(adotanteRequest.getDataNascimento());

        AdotanteEntity adotanteAtualizado = adotanteRepository.save(adotanteEntity);

        return transformResponse(adotanteAtualizado);
    }

    public AdotanteResponse atualizarParcial(AdotanteParcialRequest adotanteParcialRequest, String cpf) {
        AdotanteEntity adotanteEntity = adotanteRepository.findByCpf(cpf)
                .orElseThrow(() -> new AdotanteException("Nenhum adotante encontrado com esse cpf"));

        if (adotanteEntity.getAdotanteEnum().equals(AdotanteEnum.Inativo)) {
            throw new AdotanteException("Não foi possivel atualizar, o usuário está inativo");
        }

        if (adotanteParcialRequest.getNome() != null) {
            adotanteEntity.setNome(adotanteParcialRequest.getNome());
        }

        if (adotanteParcialRequest.getCpf() != null) {
            adotanteEntity.setCpf(adotanteParcialRequest.getCpf());
        }

        if (adotanteParcialRequest.getDataNascimento() != null) {
            adotanteEntity.setDataNascimento(adotanteParcialRequest.getDataNascimento());
        }

        AdotanteEntity adotanteAtualizado = adotanteRepository.save(adotanteEntity);

        return transformResponse(adotanteAtualizado);
    }

    public void deletarAdotante(String cpf) {
        AdotanteEntity adotanteEntity = adotanteRepository.findByCpf(cpf)
                .orElseThrow(() -> new AdotanteException("Nenhum adotante encontrado com esse cpf"));

        adotanteRepository.delete(adotanteEntity);
    }
}
