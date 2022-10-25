package br.com.residencia.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.residencia.biblioteca.dto.ConsultaCnpjDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.imgbb.ImgBBDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.service.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController {
	@Autowired
	EditoraService editoraService;
	
	@GetMapping
	public ResponseEntity<List<Editora>> getAllEditoras(){
		return new ResponseEntity<>(editoraService.getAllEditoras(),
				HttpStatus.OK);
	}

	@GetMapping("/dto")
	public ResponseEntity<List<EditoraDTO>> getAllEditorasDTO(){
		return new ResponseEntity<>(editoraService.getAllEditorasDTO(),
				HttpStatus.OK);
	}

	@GetMapping("/editora-livros")
	public ResponseEntity<List<EditoraDTO>> getAllEditorasLivrosDTO(){
		return new ResponseEntity<>(editoraService.getAllEditorasLivrosDTO(),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Editora> getEditoraById(@PathVariable Integer id) {
		Editora editora = editoraService.getEditoraById(id);
		if(null != editora)
			return new ResponseEntity<>(editora,
					HttpStatus.OK);
		else
			return new ResponseEntity<>(editora,
					HttpStatus.NOT_FOUND);
	}

	@GetMapping("/consulta-cnpj/{cnpj}")
	public ResponseEntity<ConsultaCnpjDTO> consultaCnpjApiExterna(@PathVariable String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO = editoraService.consultaCnpjApiExterna(cnpj);
		if(null != consultaCnpjDTO)
			return new ResponseEntity<>(consultaCnpjDTO,
					HttpStatus.OK);
		else
			return new ResponseEntity<>(consultaCnpjDTO,
					HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Editora> saveEditora(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.saveEditora(editora),
				HttpStatus.CREATED);
	}
	
	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<Editora> saveEditoraFromApi(@PathVariable String cnpj) {
		return new ResponseEntity<>(editoraService.saveEditoraFromApi(cnpj),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/cnpj/post")
	public ResponseEntity<Editora> saveEditoraFromApiPost(@RequestBody String cnpj) {
		return new ResponseEntity<>(editoraService.saveEditoraFromApi(cnpj),
				HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	public ResponseEntity<EditoraDTO> saveEditoraDTO(@RequestBody EditoraDTO editoraDTO) {
		return new ResponseEntity<>(editoraService.saveEditoraDTO(editoraDTO),
				HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/cadastro-editora-com-foto",
			     consumes = { MediaType.APPLICATION_JSON_VALUE, 
			    		      MediaType.MULTIPART_FORM_DATA_VALUE}
	)
	public ResponseEntity<EditoraDTO> saveEditoraFoto(
			@RequestPart("editora") String editoraTxt,
			@RequestPart("filename") MultipartFile file
	) throws IOException{
		EditoraDTO editoraDTO = editoraService.saveEditoraFoto(editoraTxt, file);
		if(editoraDTO == null)
			return new ResponseEntity<>(editoraDTO, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(editoraDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Editora> updateEditora(@RequestBody Editora editora, 
			@PathVariable Integer id){
		return new ResponseEntity<>(editoraService.updateEditora(editora, id),
				HttpStatus.OK);
	}

	@PutMapping("/dto/{id}")
	public ResponseEntity<EditoraDTO> updateEditoraDTO(@RequestBody EditoraDTO editoraDTO, 
			@PathVariable Integer id){
		return new ResponseEntity<>(editoraService.updateEditoraDTO(editoraDTO, id),
				HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Editora> deleteEditora(@PathVariable Integer id) {
		Editora editora = editoraService.getEditoraById(id);
		if(null == editora)
			return new ResponseEntity<>(editora,
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(editoraService.deleteEditora(id),
					HttpStatus.OK);
	}
	
	@PostMapping(value = "/editora-com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
			 MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<EditoraDTO> saveEditoraComFoto(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) throws IOException {
		
		EditoraDTO editoraDTO = editoraService.saveFotoImgBB(editora, file);
		
		if (null == editoraDTO)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(editoraDTO, HttpStatus.CREATED);
	}

}
