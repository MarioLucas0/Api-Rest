package br.com.residencia.blibioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.blibioteca.entity.Aluno;
import br.com.residencia.blibioteca.repository.AlunosRepository;

@Service
public class AlunoService {

  @Autowired
  AlunosRepository alunosRepository;

  public List<Aluno> getAllAlunos() {
    return alunosRepository.findAll();
  }

  public Aluno getAlunoById(Integer matriculaAluno) {
    return alunosRepository.findById(matriculaAluno).orElse(null);
  }

  public Aluno saveAluno(Aluno aluno) {
    return alunosRepository.save(aluno);
  }

  public Aluno updateAluno(Aluno aluno, Integer id) {
    // Alunos alunoExistenteNoBanco = alunosRepository.findById(id).get();
    Aluno alunoExistenteNoBanco = getAlunoById(id);

    return alunosRepository.save(alunoExistenteNoBanco);
  }

  public Aluno deleteAlunoBool(Integer id) {

    alunosRepository.deleteById(id);
    return getAlunoById(id);

  }
}