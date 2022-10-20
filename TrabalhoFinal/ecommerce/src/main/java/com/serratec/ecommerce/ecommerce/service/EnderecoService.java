package com.serratec.ecommerce.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.serratec.ecommerce.ecommerce.dto.ConsultaCepDTO;
import com.serratec.ecommerce.ecommerce.dto.EnderecoDTO2;
import com.serratec.ecommerce.ecommerce.model.Endereco;
import com.serratec.ecommerce.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

  @Autowired
  EnderecoRepository enderecoRepository;

  public List<Endereco> getAllAlunos() {
    return enderecoRepository.findAll();
  }

  public Endereco saveEndereco(Endereco endereco) {
    return enderecoRepository.save(endereco);
  }

  public Endereco saveEditoraFromApi(String cep, EnderecoDTO2 end) {
    ConsultaCepDTO enderecoDTO = consultaCepApiExterna(cep);

    Endereco endereco = new Endereco();

    endereco.setCidade(enderecoDTO.getLocalidade());
    endereco.setBairro(enderecoDTO.getBairro());
    endereco.setComplemento(end.getComplemento());
    endereco.setRua(enderecoDTO.getLogradouro());
    endereco.setUf(enderecoDTO.getUf());
    endereco.setNumero(end.getNumero());

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