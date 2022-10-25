package com.serratec.ecommerce.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.ecommerce.ecommerce.config.MailConfig;
import com.serratec.ecommerce.ecommerce.dto.ClienteDTO;
import com.serratec.ecommerce.ecommerce.dto.ClienteInserirDTO;
import com.serratec.ecommerce.ecommerce.dto.ClienteListDTO;
import com.serratec.ecommerce.ecommerce.dto.EnderecoDTO;
import com.serratec.ecommerce.ecommerce.dto.EnderecoInserirDTO;
import com.serratec.ecommerce.ecommerce.model.Cliente;
import com.serratec.ecommerce.ecommerce.model.Endereco;
import com.serratec.ecommerce.ecommerce.repository.ClienteRepository;
import com.serratec.ecommerce.ecommerce.service.exceptions.CpfException;
import com.serratec.ecommerce.ecommerce.service.exceptions.EmailException;
import com.serratec.ecommerce.ecommerce.service.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private EnderecoService enderecoService;

  @Autowired
  private MailConfig mailConfig;

  public List<ClienteListDTO> listar() {
    List<Cliente> clientes = clienteRepository.findAll();
    List<ClienteListDTO> clientesDTO = new ArrayList<>();

    for (Cliente cliente : clientes) {
      clientesDTO.add(new ClienteListDTO(cliente));
    }

    return clientesDTO;
  }

  public ClienteListDTO buscar(Long id) {
    Cliente clientes = clienteRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente nao econtrado"));

    return new ClienteListDTO(clientes);
  }

  public ClienteDTO inserir(ClienteInserirDTO c) {

    if (clienteRepository.findByEmail(c.getEmail()) != null) {
      throw new EmailException("Email já existe na base");
    }

    if (clienteRepository.findByCpf(c.getCpf()) != null) {
      throw new CpfException("CPF já cadastrado");
    }

    EnderecoInserirDTO endereco = c.getEndereco();
    Endereco enderecoViaCep = enderecoService.salvar(endereco.getCep(), endereco.getComplemento(),
        endereco.getNumero());

    Cliente cliente = new Cliente();
    cliente.setNomeCompleto(c.getNomeCompleto());
    cliente.setEmail(c.getEmail());
    cliente.setCpf(c.getCpf());
    cliente.setDataNascimento(c.getDataNascimento());
    cliente.setEndereco(enderecoViaCep);
    cliente = clienteRepository.save(cliente);

    return new ClienteDTO(cliente);
  }

  public ClienteDTO atualizar(Long id, ClienteInserirDTO clienteInserirDTO) {

    clienteInserirDTO.setId(id);

    // Buscando o cliente que vai ser atualizado
    ClienteListDTO clientL = buscar(id);
    // Buscando o endereço que vai ser atualizado do cliente
    EnderecoDTO enderecoBuscarId = clientL.getEndereco();

    // verifica no banco de pode ser alterado o email
    if (clienteRepository.findByEmail(clienteInserirDTO.getEmail()) != null) {
      if (!clienteRepository.findByEmail(clienteInserirDTO.getEmail()).getEmail().equals(clientL.getEmail())) {
        throw new EmailException("Email já existe na base");
      }
    }

    // não permite alteração do cpf do cliente
    if (clienteRepository.findByCpf(clienteInserirDTO.getCpf()) != null) {
      if (!clienteRepository.findByCpf(clienteInserirDTO.getCpf()).getCpf().equals(clientL.getCpf())) {
        throw new CpfException("CPF não pode ser alterado");
      }
    }
    if (clienteRepository.findByCpf(clienteInserirDTO.getCpf()) == null) {
      throw new CpfException("CPF não pode ser alterado");
    }

    // Atualizando o endereço do cliente passando o id do endereço que foi achando
    // acima e o dados novos
    EnderecoInserirDTO endereco = clienteInserirDTO.getEndereco();
    Endereco enderecoViaCep = enderecoService.atualizar(endereco.getCep(), endereco.getComplemento(),
        endereco.getNumero(), enderecoBuscarId.getId());

    // Cadastrando no banco de dados
    Cliente novoCliente = new Cliente();
    novoCliente.setId(clienteInserirDTO.getId());
    novoCliente.setEmail(clienteInserirDTO.getEmail());

    novoCliente.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
    novoCliente.setCpf(clienteInserirDTO.getCpf());

    novoCliente.setDataNascimento(clienteInserirDTO.getDataNascimento());
    novoCliente.setEndereco(enderecoViaCep);

    novoCliente = clienteRepository.save(novoCliente);

    // Enviando Email para notificar a mudança no cadastro
    mailConfig.sendEmail(clienteInserirDTO.getEmail(), "Atualização de cadastro de Usuário",
        novoCliente.toString());

    return new ClienteDTO(novoCliente);
  }

  public Boolean delete(Long id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);

    if (cliente.isPresent()) {
      clienteRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
