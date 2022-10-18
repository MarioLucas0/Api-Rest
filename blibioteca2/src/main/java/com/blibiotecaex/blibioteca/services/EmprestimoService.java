package com.blibiotecaex.blibioteca.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibiotecaex.blibioteca.dto.EmprestimoDTO;
import com.blibiotecaex.blibioteca.entities.Associado;
import com.blibiotecaex.blibioteca.entities.Emprestimo;
import com.blibiotecaex.blibioteca.repository.AssociadoRepository;
import com.blibiotecaex.blibioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

  @Autowired
  EmprestimoRepository emprestimoRepository;
  @Autowired
  AssociadoRepository associadoRepository;

  @Transactional
  public List<Emprestimo> getAllEmprestimos() {
    return emprestimoRepository.findAll();

  }

  @Transactional
  public EmprestimoDTO insert(EmprestimoDTO emprestimoDto) {

    Emprestimo entity = new Emprestimo();

    entity.setDataemprestimo(emprestimoDto.getDataemprestimo());

    Associado associado = associadoRepository.getReferenceById(emprestimoDto.getAssociadoId());
    // Associado associado = new Associado();
    // entity.setAssociado(associado);

    entity.setAssociado(associado);
    entity = emprestimoRepository.save(entity);

    return new EmprestimoDTO(entity);
  }

  public Object findAll() {
    return null;
  }
}