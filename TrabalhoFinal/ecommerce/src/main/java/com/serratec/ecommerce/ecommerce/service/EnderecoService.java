package com.serratec.ecommerce.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.serratec.ecommerce.ecommerce.dto.ConsultaCepDTO;
import com.serratec.ecommerce.ecommerce.model.Endereco;
import com.serratec.ecommerce.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

  @Autowired
  EnderecoRepository enderecoRepository;

  public List<Endereco> getAllAlunos() {
    return enderecoRepository.findAll();
  }

  public Endereco saveEndereco(Endereco editora) {
    return enderecoRepository.save(editora);
  }

  public Endereco saveEditoraFromApi(String cep) {
    ConsultaCepDTO enderecoDTO = consultaCepApiExterna(cep);

    Endereco endereco = new Endereco();
    endereco.setCidade(enderecoDTO.getLocalidade());
    endereco.setBairro(enderecoDTO.getBairro());
    endereco.setComplemento(enderecoDTO.getComplemento());
    endereco.setRua(enderecoDTO.getLogradouro());
    endereco.setUf(enderecoDTO.getUf());
    if (enderecoDTO.getNumero() != null) {
      endereco.setNumero(enderecoDTO.getNumero());
    } else {
      endereco.setNumero("");
    }

    return enderecoRepository.save(endereco);
  }

  public ConsultaCepDTO consultaCepApiExterna(String cep) {
    RestTemplate restTemplate = new RestTemplate();
    String uri = "https://viacep.com.br/ws/{cep}/json/";

    Map<String, String> params = new HashMap<String, String>();
    params.put("cep", cep);

    ConsultaCepDTO consultaCepDTO = restTemplate.getForObject(uri, ConsultaCepDTO.class, params);

    return consultaCepDTO;
  }
}