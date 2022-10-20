package br.com.residencia.biblioteca.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	DecimalFormat df = new DecimalFormat("#.##");
	
	@Autowired
	EmprestimoRepository emprestimoRepository; 
	
	@Autowired
	private ModelMapper modelMapper;

//	Get
	public List<Emprestimo> getAllEmprestimos() {
		return emprestimoRepository.findAll();	
	}
	
	public List<EmprestimoDTO> getAllEmprestimosDTO() {
		List<Emprestimo> emprestimo = getAllEmprestimos();
		List<EmprestimoDTO> emprestimoDTO = new ArrayList<EmprestimoDTO>();
		emprestimo.forEach(emp -> {
			emprestimoDTO.add(converteEntitytoDTO(emp));
		});
		return emprestimoDTO;
	}
	
	public Emprestimo getEmprestimoById(Integer id) {
		return emprestimoRepository.findById(id).orElse(null);
	}
	
	public EmprestimoDTO getEmprestimoDtoById(Integer id) {
		Emprestimo emprestimo = emprestimoRepository.findById(id).orElse(null);
		if(emprestimo != null) {
			return converteEntitytoDTO(emprestimo);
		}
		return null;
	}

//	Save
	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	public List<Emprestimo> saveAllEmprestimos(List<Emprestimo> emprestimo) {
		return emprestimoRepository.saveAll(emprestimo);
	}
	
	public EmprestimoDTO saveEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = toEntity(emprestimoDTO);
		Emprestimo novoEmprestimo = emprestimoRepository.save(emprestimo);
		
		EmprestimoDTO emprestimoAtualizadoDTO = converteEntitytoDTO(novoEmprestimo);
		return emprestimoAtualizadoDTO;
	}
	
	public List<EmprestimoDTO> saveAllEmprestimosDTO(List<EmprestimoDTO> emprestimoDTO) {
		emprestimoDTO.forEach(emp -> {
			emprestimoRepository.save(toEntity(emp));
		});	
		return emprestimoDTO;
	}

//	Update
	public Emprestimo updateEmprestimo(Emprestimo emprestimo, Integer id) {
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		
		emprestimoExistenteNoBanco.setAluno(emprestimo.getAluno());
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoExistenteNoBanco.setLivro(emprestimo.getLivro());
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
		
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
	}
	
	public EmprestimoDTO updateEmprestimoDTO(EmprestimoDTO emprestimoDTO, Integer id) {
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		EmprestimoDTO emprestimoAtualizadoDTO = new EmprestimoDTO();
		if(emprestimoExistenteNoBanco != null) {
			emprestimoExistenteNoBanco = toEntity(emprestimoDTO);
			Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimoExistenteNoBanco);
			emprestimoAtualizadoDTO = converteEntitytoDTO(emprestimoAtualizado);
		}
		return emprestimoAtualizadoDTO;
	}

//	Delete
	public Emprestimo deleteEmprestimo(Integer id) {
		emprestimoRepository.deleteById(id);
		return getEmprestimoById(id);
	}

	public EmprestimoDTO deleteEmprestimoDTO(Integer id) {
		emprestimoRepository.deleteById(id);
		return getEmprestimoDtoById(id);
	}
	
//	Convert
	public EmprestimoDTO converteEntitytoDTO(Emprestimo emprestimo) {
		EmprestimoDTO editoraDTO = new EmprestimoDTO();
		editoraDTO = (modelMapper.map(emprestimo, EmprestimoDTO.class));
		return editoraDTO;	
	}
	
	public Emprestimo toEntity(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = new Emprestimo();	
		emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
		emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
		emprestimo.setValorEmprestimo(emprestimoDTO.getValorEmprestimo());
	
		return emprestimo;	
	}
}
