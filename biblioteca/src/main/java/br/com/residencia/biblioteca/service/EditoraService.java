package br.com.residencia.biblioteca.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.residencia.biblioteca.dto.ConsultaCnpjDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.FreeImageHostDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.EditoraRepository;
import br.com.residencia.biblioteca.repository.LivroRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Service
public class EditoraService {
	@Autowired
	EditoraRepository editoraRepository;

	@Autowired
	LivroRepository livroRepository;

	@Autowired
	LivroService livroService;
	
	@Value("${freeimage.host.url}")
	private String freeImageHostUrl;
	
	@Value("${freeimage.host.key}")
    private String freeImageHostKey;
	
	public List<Editora> getAllEditoras(){
		return editoraRepository.findAll();
	}
	
	public List<EditoraDTO> getAllEditorasDTO(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		//1. Percorrer a lista de entidades Editora (chamada listaEditora)
		//2. Na lista de entidade, pegar cada entidade existente nela
		for(Editora editora: listaEditora) {
			//3. Transformar cada entidade existente na lista em um DTO
			EditoraDTO editoraDTO = toDTO(editora);

			//OBS: para converter a entidade no DTO, usar o metodo toDTO
			//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
			listaEditoraDTO.add(editoraDTO);
		}
		
		//5. Retornar/devolver a lista de DTO preenchida
		return listaEditoraDTO; 
	}
	
	public Editora getEditoraById(Integer id) {
		return editoraRepository.findById(id).orElse(null);
	}
	
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	public Editora saveEditoraFromApi(String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO = consultaCnpjApiExterna(cnpj);
		
		Editora editora = new Editora();
		editora.setNome(consultaCnpjDTO.getNome());
		
		return editoraRepository.save(editora);
	}

	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		Editora editora = toEntidade(editoraDTO);
		Editora novaEditora = editoraRepository.save(editora);
		
		EditoraDTO editoraAtualizadaDTO = toDTO(novaEditora);
		return editoraAtualizadaDTO;
	}
/*
	public EditoraDTO saveEditoraDTOOtimizado(EditoraDTO editoraDTO) {
		Editora novaEditora = editoraRepository.save(toEntidade(editoraDTO));
		return toDTO(novaEditora);
	}

	public EditoraDTO saveEditoraDTOOtimizadoTwo(EditoraDTO editoraDTO) {
		return toDTO(editoraRepository.save(toEntidade(editoraDTO)));
	}
*/	
	
	public EditoraDTO updateEditoraDTO(EditoraDTO editoraDTO, Integer id) {
		Editora editoraExistenteNoBanco = getEditoraById(id);
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		
		if(editoraExistenteNoBanco != null) {
			editoraDTO.setCodigoEditora(editoraExistenteNoBanco.getCodigoEditora());
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			editoraAtualizadaDTO = toDTO(editoraAtualizada);
			
		}
		return editoraAtualizadaDTO;
	}
	
	private Editora toEntidade (EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		
		editora.setCodigoEditora(editoraDTO.getCodigoEditora());
		editora.setNome(editoraDTO.getNome());
		return editora;
	}
	
	private EditoraDTO toDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		
		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());
		
		return editoraDTO;
	}
	
	public Editora updateEditora(Editora editora, Integer id) {
		//Editora editoraExistenteNoBanco = editoraRepository.findById(id).get();
		
		Editora editoraExistenteNoBanco = getEditoraById(id);

		editoraExistenteNoBanco.setNome(editora.getNome());
		
		return editoraRepository.save(editoraExistenteNoBanco);
		
		//return editoraRepository.save(editora);
	}
	
	
	public ConsultaCnpjDTO consultaCnpjApiExterna(String cnpj) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);
		
		ConsultaCnpjDTO consultaCnpjDTO = restTemplate.getForObject(uri, ConsultaCnpjDTO.class , params);
		
		return consultaCnpjDTO;
	}
	
	

	public Editora deleteEditora(Integer id) {
		editoraRepository.deleteById(id);
		return getEditoraById(id);
	}
	
	public List<EditoraDTO> getAllEditorasLivrosDTO(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		
		for(Editora editora: listaEditora) {
			EditoraDTO editoraDTO = toDTO(editora);
			List<Livro> listaLivros = new ArrayList<>();
			List<LivroDTO> listaLivrosDTO = new ArrayList<>();
			
			
			listaLivros = livroRepository.findByEditora(editora);
			for(Livro livro : listaLivros) {
				LivroDTO livroDTO = livroService.toDTO(livro);
				listaLivrosDTO.add(livroDTO);
			}
			editoraDTO.setListaLivrosDTO(listaLivrosDTO);

			listaEditoraDTO.add(editoraDTO);
		}
		
		return listaEditoraDTO; 
	}
	
	public ResponseEntity<String> saveEditoraComFotoNew(String editora,
			MultipartFile file) throws IOException {
        
		RestTemplate restTemplate = new RestTemplate();
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // This nested HttpEntiy is important to create the correct
        // Content-Disposition entry with metadata "name" and "filename"
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("source")
                .filename(file.getOriginalFilename())
                .build();
        
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("source", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);
        
        
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(
            		serverUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        
        return response;
    }
	
	public ResponseEntity<String> saveEditoraComFoto(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) throws IOException {
		/*
		HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    
		DefaultHttpClient httpClient
	      = (DefaultHttpClient) requestFactory.getHttpClient();
	    
		TrustStrategy acceptingTrustStrategy = (cert, authType) -> true
				SSLSocketFactory sf = new SSLSocketFactory(
	      acceptingTrustStrategy, ALLOW_ALL_HOSTNAME_VERIFIER);
	    httpClient.getConnectionManager().getSchemeRegistry()
	      .register(new Scheme("https", 8443, sf));
	    
	    String urlOverHttps
	      = "https://localhost:8443/httpclient-simple/api/bars/1";
	    ResponseEntity<String> response = new RestTemplate(requestFactory).
	      exchange(urlOverHttps, HttpMethod.GET, null, String.class);
	    assertThat(response.getStatusCode().value(), equalTo(200));	    
		*/
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		LinkedMultiValueMap<String, String> pdfHeaderMap = new LinkedMultiValueMap<>();
		pdfHeaderMap.add("Content-disposition", "form-data; name=source; filename=" + file.getOriginalFilename());
		pdfHeaderMap.add("Content-type", "image/jpeg");
		HttpEntity<byte[]> doc = new HttpEntity<byte[]>(file.getBytes(), pdfHeaderMap);
		
		LinkedMultiValueMap<String, Object> multipartReqMap = new LinkedMultiValueMap<>();
		multipartReqMap.add("source", doc);
		
		HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(multipartReqMap, headers);
		ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.POST, reqEntity, String.class);		
		
		return response;		
	}
	
	public ResponseEntity<String> saveEditoraComFotoOld2(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) throws IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

	    LinkedMultiValueMap<String, String> pdfHeaderMap = new LinkedMultiValueMap<>();
	    pdfHeaderMap.add("Content-disposition", "form-data; name=source; filename=" + file.getOriginalFilename());
	    pdfHeaderMap.add("Content-type", "image/jpeg");
	    HttpEntity<byte[]> doc = new HttpEntity<byte[]>(file.getBytes(), pdfHeaderMap);

	    LinkedMultiValueMap<String, Object> multipartReqMap = new LinkedMultiValueMap<>();
	    multipartReqMap.add("source", doc);

	    HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(multipartReqMap, headers);
	    ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.POST, reqEntity, String.class);		
		
		return response;		
	}
	
	public ResponseEntity<String> saveEditoraComFotoOld(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) {
		
		Editora editoraFromJson = convertEditoraFromStringJson(editora);
		//Editora novaEditora= editoraRepository.save(editoraFromJson);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		try {
			body.add("source", new ByteArrayResource(file.getBytes()));
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao obter os dados da imagem. - " + e);
		}
		
		RestTemplate restTemplate = new RestTemplate();
		String serverUrl = freeImageHostUrl + freeImageHostKey;
		
		HttpEntity<MultiValueMap<String, Object>> 
			requestEntity = new HttpEntity<>(body, headers);
/*
		ResponseEntity<FreeImageHostDTO> response = restTemplate
		  .postForEntity(serverUrl, requestEntity, FreeImageHostDTO.class);
*/		
		ResponseEntity<String> response = restTemplate
				  .postForEntity(serverUrl, requestEntity, String.class);

		return response;		
	}
	
	private Editora convertEditoraFromStringJson(String editoraJson) {
		Editora editora = new Editora();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			editora = objectMapper.readValue(editoraJson, Editora.class);
		} catch (IOException err) {
			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância da entidade Editora", err.toString());
		}
		
		return editora;
	}
}