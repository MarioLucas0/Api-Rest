package br.com.residencia.blibioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.blibioteca.entity.Alunos;
import br.com.residencia.blibioteca.repository.AlunosRepository;
import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class AlunoService {

  @Autowired
  AlunosRepository alunosRepository;

  public List<Alunos> getAllAlunos() {
    return alunosRepository.findAll();
  }

  public Alunos getAlunoById(Integer matriculaAluno) {
    return alunosRepository.findById(matriculaAluno).orElse(null);
  }

  public Alunos saveAluno(Alunos aluno) {
    return alunosRepository.save(aluno);
  }

  public Alunos updateAluno(Alunos aluno, Integer id) {
    // Alunos alunoExistenteNoBanco = alunosRepository.findById(id).get();
    Alunos alunoExistenteNoBanco = getAlunoById(id);

    return alunosRepository.save(alunoExistenteNoBanco);
  }

  public Alunos deleteAlunoBool(Integer id) {

    alunosRepository.deleteById(id);
    return getAlunoById(id);

  }
}