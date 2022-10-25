/*
 * package com.serratec.ecommerce.ecommerce.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.PathVariable;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.RequestBody;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RestController;
 * 
 * import com.serratec.ecommerce.ecommerce.dto.ConsultaCepDTO;
 * import com.serratec.ecommerce.ecommerce.dto.EnderecoInserirDTO;
 * import com.serratec.ecommerce.ecommerce.model.Endereco;
 * import com.serratec.ecommerce.ecommerce.service.EnderecoService;
 * 
 * @RestController
 * 
 * @RequestMapping("/endereco")
 * public class EnderecoController {
 * 
 * @Autowired
 * EnderecoService enderecoService;
 * 
 * @GetMapping("/consulta-cep/{cep}")
 * public ResponseEntity<ConsultaCepDTO> consultaCnpjApiExterna(@PathVariable
 * String cep) {
 * ConsultaCepDTO enderecoDTO = enderecoService.consultaCepApiExterna(cep);
 * if (null != enderecoDTO)
 * return new ResponseEntity<>(enderecoDTO,
 * HttpStatus.OK);
 * else
 * return new ResponseEntity<>(enderecoDTO,
 * HttpStatus.NOT_FOUND);
 * }
 * 
 * @PostMapping("/cep/post/{cep}")
 * public ResponseEntity<Endereco> saveEditoraFromApiPost(@PathVariable String
 * cep,
 * 
 * @RequestBody EnderecoInserirDTO endereco2) {
 * return new ResponseEntity<>(enderecoService.saveEnderecoFromApi(cep,
 * endereco2),
 * HttpStatus.CREATED);
 * }
 * 
 * }
 */