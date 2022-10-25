package com.serratec.ecommerce.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serratec.ecommerce.ecommerce.dto.ProdutoDTO;
import com.serratec.ecommerce.ecommerce.model.Categoria;
import com.serratec.ecommerce.ecommerce.model.Produto;
import com.serratec.ecommerce.ecommerce.repository.CategoriaRepository;
import com.serratec.ecommerce.ecommerce.repository.ProdutoRepository;
import com.serratec.ecommerce.ecommerce.service.exceptions.DatabaseExcption;
import com.serratec.ecommerce.ecommerce.service.exceptions.ResourceNotFoundException;
import com.serratec.ecommerce.ecommerce.service.img.ImgBBDTO;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Value("${imgbb.host.url}")
	private String imgBBHostUrl;

	@Value("${imgbb.host.key}")
	private String imgBBHostKey;

	public ProdutoDTO findById(Long id) {

		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		ProdutoDTO dto = new ProdutoDTO(produto);
		return dto;

	}

	public List<ProdutoDTO> listar() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> produtoDTO = new ArrayList<>();

		for (Produto produto : produtos) {
			produtoDTO.add(new ProdutoDTO(produto));
		}

		return produtoDTO;
	}

	/*
	 * public List<Produto> getAllProdutos() {
	 * return produtoRepository.findAll();
	 * }
	 */
	public ProdutoDTO insert(String produtoTxt,
			MultipartFile file) throws IOException {

		RestTemplate restTemplate = new RestTemplate();
		String serverUrl = imgBBHostUrl + imgBBHostKey;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();

		ContentDisposition contentDisposition = ContentDisposition
				.builder("form-data")
				.name("image")
				.filename(file.getOriginalFilename())
				.build();

		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

		HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("image", fileEntity);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<ImgBBDTO> response = null;
		ImgBBDTO imgDTO = new ImgBBDTO();
		Produto novoProduto = new Produto();
		// ProdutoDTO productDTO = new ProdutoDTO();
		try {
			response = restTemplate.exchange(
					serverUrl,
					HttpMethod.POST,
					requestEntity,
					ImgBBDTO.class);

			imgDTO = response.getBody();
			System.out.println("ImgBBDTO: " + imgDTO.getData().toString());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
		Produto produtoFromJson = convertProdutoFromStringJson(produtoTxt);
		produtoFromJson.setImagemFileName(imgDTO.getData().getImage().getFilename());
		produtoFromJson.setImagemNome(imgDTO.getData().getTitle());
		produtoFromJson.setImagemUrl(imgDTO.getData().getUrl());

		if (produtoFromJson != null) {
			novoProduto = produtoRepository.save(produtoFromJson);
		}

		return paraDTO(novoProduto);

	}

	private Produto convertProdutoFromStringJson(String produtoJson) {
		Produto produto = new Produto();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			produto = objectMapper.readValue(produtoJson, Produto.class);
		} catch (IOException err) {
			System.out.printf(err.toString());
		}

		return produto;
	}

	public ProdutoDTO paraDTO(Produto entity) {

		entity.setNome(entity.getNome());
		entity.setDataCadastro(entity.getDataCadastro());

		entity.setQtdEstoque(entity.getQtdEstoque());
		entity.setValorUnitario(entity.getValorUnitario());

		entity.setCategoria(entity.getCategoria());
		entity.setImagemUrl(entity.getImagemUrl());
		entity.setDescricao(entity.getDescricao());

		Categoria categoria = categoriaRepository.getReferenceById(entity.getCategoria().getId());

		entity.setCategoria(categoria);

		entity = produtoRepository.save(entity);

		return new ProdutoDTO(entity);
	}

	/*
	 * @Transactional
	 * public ProdutoDTO insert(ProdutoDTO productDto) {
	 * 
	 * Produto entity = new Produto();
	 * 
	 * Categoria categoria =
	 * categoriaRepository.getReferenceById(productDto.getCategoria().getId());
	 * 
	 * entity.setCategoria(categoria);
	 * 
	 * copyDtoToEntity(productDto, entity);
	 * entity = produtoRepository.save(entity);
	 * return new ProdutoDTO(entity);
	 * }
	 */
	public ProdutoDTO update(ProdutoDTO productDto, Long id) {

		try {
			Produto entity = produtoRepository.getReferenceById(id);
			copyDtoToEntity(productDto, entity);
			entity = produtoRepository.save(entity);
			return new ProdutoDTO(entity);

		} catch (

		EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso nao encontrado");

		}

	}

	// @Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long id) {

		try {
			produtoRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Recurso nao encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseExcption("Falha de integridade Referencial");
		}

	}

	private void copyDtoToEntity(ProdutoDTO productDto, Produto entity) {

		entity.setNome(productDto.getNome());
		entity.setDataCadastro(productDto.getDataCadastro());
		entity.setQtdEstoque(productDto.getQtdEstoque());
		entity.setValorUnitario(productDto.getValorUnitario());
		entity.setCategoria(entity.getCategoria());
		entity.setImagemUrl(entity.getImagemUrl());
		entity.setDescricao(entity.getDescricao());
	}

}