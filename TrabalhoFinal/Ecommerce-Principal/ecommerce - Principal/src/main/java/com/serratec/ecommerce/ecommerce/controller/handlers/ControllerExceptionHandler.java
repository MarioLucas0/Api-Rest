package com.serratec.ecommerce.ecommerce.controller.handlers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.serratec.ecommerce.ecommerce.dto.CustomError;
import com.serratec.ecommerce.ecommerce.service.exceptions.CpfException;
import com.serratec.ecommerce.ecommerce.service.exceptions.DatabaseExcption;
import com.serratec.ecommerce.ecommerce.service.exceptions.EmailException;
import com.serratec.ecommerce.ecommerce.service.exceptions.ErroResposta;
import com.serratec.ecommerce.ecommerce.service.exceptions.FindIdException;
import com.serratec.ecommerce.ecommerce.service.exceptions.QuantidadeException;
import com.serratec.ecommerce.ecommerce.service.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseExcption.class)
  public ResponseEntity<CustomError> database(DatabaseExcption e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  /*
   * @ExceptionHandler(MethodArgumentNotValidException.class)
   * public ResponseEntity<ValidationError>
   * MethodArgumentNotValidException(MethodArgumentNotValidException e,
   * HttpServletRequest request) {
   * HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
   * ValidationError err = new ValidationError(Instant.now(), status.value(),
   * "Dados invalidos",
   * request.getRequestURI());
   * 
   * for (FieldError f : e.getBindingResult().getFieldErrors()) {
   * err.addError(f.getField(), f.getDefaultMessage());
   * }
   * return ResponseEntity.status(status).body(err);
   * }
   */
  @ExceptionHandler(QuantidadeException.class)
  public ResponseEntity<Object> QuantidadeException(QuantidadeException ex) {
    return ResponseEntity.unprocessableEntity().body("Verifique a quantidade informada");
  }

  @ExceptionHandler(EmailException.class)
  public ResponseEntity<Object> handleEmailException(EmailException ex) {
    EmailException emailException = new EmailException(ex.getMessage());
    return ResponseEntity.unprocessableEntity().body(emailException);
  }

  @ExceptionHandler(CpfException.class)
  public ResponseEntity<Object> handleCpfException(CpfException ex) {
    CpfException cpfException = new CpfException(ex.getMessage());
    return ResponseEntity.unprocessableEntity().body(cpfException);
  }

  @ExceptionHandler(FindIdException.class)
  public ResponseEntity<Object> FindIdException(FindIdException ex) {
    FindIdException findIdException = new FindIdException(ex.getMessage());
    return ResponseEntity.unprocessableEntity().body(findIdException);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
    return ResponseEntity.unprocessableEntity().body("Cep Inválido !");
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
    return ResponseEntity.unprocessableEntity().body("Aquivo muito poderoso, máximo suportado 1 MegaByte !");
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> DataIntegrityViolationException(DataIntegrityViolationException exc) {
    return ResponseEntity.unprocessableEntity()
        .body("Impossível realizar essa opção, violação de chave estrangeira ou violação no banco de dados !");
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Object> NoSuchElementException(NoSuchElementException exc) {
    return ResponseEntity.unprocessableEntity().body("Id não encontrado ou null");
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> erros = new ArrayList<>();
    for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
      erros.add(erro.getField() + ":" + erro.getDefaultMessage());
    }

    ErroResposta erroResposta = new ErroResposta(status.value(),
        "Existem campos inválidos. Confira o preenchimento", LocalDateTime.now(), erros);

    return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ErroResposta erroResposta = new ErroResposta(status.value(),
        "Existem campos inválidos. Confira o preenchimento", LocalDateTime.now(), null);

    return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
  }

}