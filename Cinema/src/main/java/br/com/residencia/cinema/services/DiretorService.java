package br.com.residencia.cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.cinema.entity.Diretor;
import br.com.residencia.cinema.repository.DiretorRepository;

@Service
public class DiretorService {

	@Autowired
	DiretorRepository diretorRepository;

	public List<Diretor> getAllDiretores() {
		return diretorRepository.findAll();
	}

	public Diretor getDiretorById(Integer id) {
		return diretorRepository.findById(id).orElse(null);

	}

	public Diretor saveDiretor(Diretor diretor) {
		return diretorRepository.save(diretor);
	}

	public Diretor updateDiretor(Diretor diretor, Integer id) {
		Diretor diretorExist = diretorRepository.getReferenceById(id);

		// diretorExist.setIdDiretor(diretor.getIdDiretor());
		diretorExist.setNomeDiretor(diretor.getNomeDiretor());
		return diretorRepository.save(diretorExist);

	}

	public Diretor deleteDiretor(Integer id) {
		diretorRepository.deleteById(id);
		return getDiretorById(id);
	}
}
