package br.com.residencia.biblioteca.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoEmprestimoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.dto.ResumoEmprestimoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.AlunoRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class AlunoService {
	public static Scanner input = new Scanner(System.in);
	
	@Autowired
	AlunoRepository alunoRepository; 
	
	@Autowired 
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	EmprestimoService emprestimoService;
	
	@Autowired
	private ModelMapper modelMapper;

//	Get
	public List<Aluno> getAllAlunos() {
		return alunoRepository.findAll();	
	}
	
	public List<AlunoDTO> getAllAlunosDTO() {
		List<Aluno> aluno = getAllAlunos();
		List<AlunoDTO> alunoDTO = new ArrayList<AlunoDTO>();
		aluno.forEach(aln -> {
			alunoDTO.add(converteEntitytoDTO(aln));
		});
		return alunoDTO;
	}
	
	public Aluno getAlunoById(Integer id) {
		return alunoRepository.findById(id).orElse(null);
	}
	
	public AlunoDTO getAlunoDtoById(Integer id) {
		Aluno aluno = alunoRepository.findById(id).orElse(null);
		if(aluno != null) {
			return converteEntitytoDTO(aluno);
		}
		return null;
	}
	
	public List<AlunoDTO> getAllEmprestimosAlunosDTO() {
		List<Aluno> aluno = getAllAlunos();
		List<AlunoDTO> listaAlunosDTO = new ArrayList<AlunoDTO>();
		
		aluno.forEach(aln -> {
			AlunoDTO alunoDTO = converteEntitytoDTO(aln);
			List<Emprestimo> listaEmprestimos = new ArrayList<>();
			List<EmprestimoDTO> listaEmprestimosDTO = new ArrayList<>();
			listaEmprestimos = emprestimoRepository.findByAluno(aln);
			
			listaEmprestimos.forEach(emp -> {
				listaEmprestimosDTO.add(emprestimoService.converteEntitytoDTO(emp));
			});
			
			alunoDTO.setEmprestimos(listaEmprestimosDTO);
			listaAlunosDTO.add(alunoDTO);
		});
		return listaAlunosDTO;
	}
	
	public List<AlunoEmprestimoDTO> filterAlunoEmprestimo() {
		List<AlunoDTO> alunos = getAllEmprestimosAlunosDTO();
		List<AlunoEmprestimoDTO> alunosEmprestimosDTO = new ArrayList<>();
		for(AlunoDTO aln: alunos) {
			AlunoEmprestimoDTO alunoTemp = new AlunoEmprestimoDTO();
			alunoTemp.setNumeroMatriculaAluno(aln.getNumeroMatriculaAluno());
			alunoTemp.setNome(aln.getNome());
			alunoTemp.setCpf(aln.getCpf());
			if(aln.getEmprestimos().size() > 0) {
				List<ResumoEmprestimoDTO> listaResumo = new ArrayList<>();
				for(int i = 0; i < aln.getEmprestimos().size(); i++) {
					ResumoEmprestimoDTO resumo = new ResumoEmprestimoDTO();
					try {
						resumo.setCodigoEmprestimo(aln.getEmprestimos().get(i).getCodigoEmprestimo());
						resumo.setDataEmprestimo(aln.getEmprestimos().get(i).getDataEmprestimo());
						resumo.setDataEntrega(aln.getEmprestimos().get(i).getDataEntrega());
						listaResumo.add(resumo);
					} catch (Exception e){
						e.printStackTrace();
						continue;
					}
				}
				alunoTemp.setResumoEmprestimoDTO(listaResumo);
			}
			alunosEmprestimosDTO.add(alunoTemp);
		}
		return alunosEmprestimosDTO;
	}

//	Save
	public Aluno saveAluno(Aluno aluno) {
		aluno = formatToUpper(aluno);
		return alunoRepository.save(aluno);
	}
	
	public List<Aluno> saveAllAluno(List<Aluno> aluno) {
		aluno.forEach(aln -> {
			formatToUpper(aln);
		});
		return alunoRepository.saveAll(aluno);
	}
	
	public AlunoDTO saveAlunoDTO(AlunoDTO alunoDTO) {
		alunoDTO = formatToUpperDTO(alunoDTO);
		Aluno aluno = toEntity(alunoDTO);
		Aluno novoAluno = alunoRepository.save(aluno);
		
		AlunoDTO alunoAtualizadoDTO = converteEntitytoDTO(novoAluno);
		return alunoAtualizadoDTO;
	}
	
	public List<AlunoDTO> saveAllAlunosDTO(List<AlunoDTO> alunoDTO) {
		alunoDTO.forEach(aln -> {
			formatToUpperDTO(aln);
			alunoRepository.save(toEntity(aln));
		});
		return alunoDTO;
	}

//	Update
	public Aluno updateAluno(Aluno aluno, Integer id) {
		aluno = formatToUpper(aluno);
		Aluno alunoExistenteNoBanco = getAlunoById(id);

		alunoExistenteNoBanco.setBairro(aluno.getBairro());
		alunoExistenteNoBanco.setCidade(aluno.getCidade());
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento());
		alunoExistenteNoBanco.setCpf(aluno.getCpf());
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		
		return alunoRepository.save(alunoExistenteNoBanco);
	}
	
	public AlunoDTO updateAlunoDTO(AlunoDTO alunoDTO, Integer id) {
		alunoDTO = formatToUpperDTO(alunoDTO);
		Aluno alunoExistenteNoBanco = getAlunoById(id);
		AlunoDTO alunoAtualizadoDTO = new AlunoDTO();
		if(alunoExistenteNoBanco != null) {
			alunoExistenteNoBanco = toEntity(alunoDTO);
			Aluno alunoAtualizado = alunoRepository.save(alunoExistenteNoBanco);
			alunoAtualizadoDTO = converteEntitytoDTO(alunoAtualizado);
		}
		return alunoAtualizadoDTO;
	}

//	Delete
	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}
	
//	public Aluno deleteAllAlunos(Integer range) {
//		for(int i = 1; i < range; i++) {
//			if(getAlunoById(i) != null) {
//				try {
//					System.out.println(i);
//					alunoRepository.deleteById(i);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else {
//				continue;
//			}
//		}
//		return getAlunoById(range);
//	}
	
	public AlunoDTO deleteAlunoDTO(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoDtoById(id);
	}
		
//	public Boolean deleteAlunoBool(Integer id) {
//		alunoRepository.deleteById(id);
//		if(getAlunoById(id) != null) {
//			return false;
//		} else {
//			return true;
//		}
//	}
	
//	Convert
	private AlunoDTO converteEntitytoDTO(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
		alunoDTO = (modelMapper.map(aluno, AlunoDTO.class));
		return alunoDTO;	
	}
	
	private Aluno toEntity(AlunoDTO alunoDTO) {
		Aluno aluno= new Aluno();
		aluno.setNome(alunoDTO.getNome());
		aluno.setBairro(alunoDTO.getBairro());
		aluno.setCidade(alunoDTO.getCidade());
		aluno.setComplemento(alunoDTO.getComplemento());
		aluno.setCpf(alunoDTO.getCpf());
		aluno.setDataNascimento(alunoDTO.getDataNascimento());
		aluno.setLogradouro(alunoDTO.getLogradouro());
		aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());
		
		return aluno;	
	}

//	Format
	private Aluno formatToUpper(Aluno aluno) {
		aluno.setNome(aluno.getNome().toUpperCase());
		aluno.setBairro(aluno.getBairro().toUpperCase());
		aluno.setCidade(aluno.getCidade().toUpperCase());
		aluno.setComplemento(aluno.getComplemento().toUpperCase());
		aluno.setLogradouro(aluno.getLogradouro().toUpperCase());
		return aluno;
	}
	
	private AlunoDTO formatToUpperDTO(AlunoDTO alunoDTO) {
		alunoDTO.setNome(alunoDTO.getNome().toUpperCase());
		alunoDTO.setBairro(alunoDTO.getBairro().toUpperCase());
		alunoDTO.setCidade(alunoDTO.getCidade().toUpperCase());
		alunoDTO.setComplemento(alunoDTO.getComplemento().toUpperCase());
		alunoDTO.setLogradouro(alunoDTO.getLogradouro().toUpperCase());
		return alunoDTO;
	}
		
}
