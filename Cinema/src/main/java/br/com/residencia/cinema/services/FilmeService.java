package br.com.residencia.cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.cinema.entity.Filme;
import br.com.residencia.cinema.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	FilmeRepository filmeRepository;

	public List<Filme> getAllFilmes() {
		return filmeRepository.findAll();
	}

	public Filme getFilmeById(Integer Id) {
		return filmeRepository.findById(Id).orElse(null);
	}

	public Filme saveFilme(Filme filme) {
		return filmeRepository.save(filme);
	}

	public Filme updateFilme(Filme filme, Integer id) {
		Filme filmeExist = getFilmeById(id);

		filmeExist.setIdFilme(filme.getIdFilme());
		filmeExist.setNomeBr(filme.getNomeBr());
		filmeExist.setNomeEn(filme.getNomeEn());
		filmeExist.setSipnose(filme.getSipnose());
		filmeExist.setAnoLancamento(filme.getAnoLancamento());
		filmeExist.setDiretor(filme.getDiretor());
		filmeExist.setGenero(filme.getGenero());

		return filmeRepository.save(filmeExist);
	}

	public Filme deleteFilme(Integer id) {
		filmeRepository.deleteById(id);
		return getFilmeById(id);
	}
}
