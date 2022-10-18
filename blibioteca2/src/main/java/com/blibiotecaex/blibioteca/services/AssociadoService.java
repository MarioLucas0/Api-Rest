package com.blibiotecaex.blibioteca.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibiotecaex.blibioteca.dto.AssociadoDTO;
import com.blibiotecaex.blibioteca.entities.Associado;
import com.blibiotecaex.blibioteca.repository.AssociadoRepository;

@Service
public class AssociadoService {

  @Autowired
  AssociadoRepository associadoRepository;

  @Transactional
  public List<Associado> getAllAssociados() {
    return associadoRepository.findAll();

  }

  @Transactional
  public AssociadoDTO insert(AssociadoDTO associadoDto) {

    Associado entity = new Associado();

    entity.setId(associadoDto.getId());
    entity.setNome(associadoDto.getNome());
    entity.setBairro(associadoDto.getBairro());
    entity.setCidade(associadoDto.getCidade());
    entity.setComplemento(associadoDto.getComplemento());
    entity.setCpf(associadoDto.getCpf());
    entity.setEstado(associadoDto.getEstado());
    entity.setLogradouro(associadoDto.getLogradouro());
    entity.setNumero(associadoDto.getNumero());
    entity.setTelefone(associadoDto.getTelefone());

    entity = associadoRepository.save(entity);

    return new AssociadoDTO(entity);
  }

}