package com.blibiotecaex.blibioteca.controller.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerExceptionHandler {

  /*
   * @ExceptionHandler(ResourceNotFoundException.class)
   * public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException
   * e, HttpServletRequest request) {
   * HttpStatus status = HttpStatus.NOT_FOUND;
   * CustomError err = new CustomError(Instant.now(), status.value(),
   * e.getMessage(), request.getRequestURI());
   * return ResponseEntity.status(status).body(err);
   * }
   * 
   * @ExceptionHandler(DatabaseException.class)
   * public ResponseEntity<CustomError> database(DatabaseException e,
   * HttpServletRequest request) {
   * HttpStatus status = HttpStatus.BAD_REQUEST;
   * CustomError err = new CustomError(Instant.now(), status.value(),
   * e.getMessage(), request.getRequestURI());
   * return ResponseEntity.status(status).body(err);
   * }
   * 
   * @ExceptionHandler(MethodArgumentNotValidException.class)
   * public ResponseEntity<ValidationError>
   * MethodArgumentNotValidException(MethodArgumentNotValidException e,
   * HttpServletRequest request) {
   * HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
   * ValidationError err = new ValidationError(Instant.now(), status.value(),
   * "Dados invalidos",
   * request.getRequestURI());
   * for (FieldError f : e.getBindingResult().getFieldErrors()) {
   * err.addError(f.getField(), f.getDefaultMessage());
   * }
   * return ResponseEntity.status(status).body(err);
   * }
   */
}