/**
 * 
 */
package br.com.sistemas.gs.resource.exception.handler;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sistemas.gs.commons.enums.Mensagem;
import br.com.sistemas.gs.commons.service.ServiceResponse;
import br.com.sistemas.gs.service.exception.GenericException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Guilherme
 *
 */
@Slf4j
@ControllerAdvice
public class GerarModeloGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DELIMITADOR = "||";

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	log.error(ex.getMessage(), ex, headers);

	return super.handleHttpMessageNotReadable(ex, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
	log.error(ex.getMessage(), ex);

	log.debug("Fim da execução do handler 'handleExceptionInternal'");

	return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * Método implementado para validação de constraints.
     * 
     * @param ex
     *            ConstraintViolationException - Exceção capturada.
     * 
     * @param request
     *            WebRequest - Request enviada.
     * 
     * @return ResponseEntity
     * 
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

	final ServiceResponse<Void> serviceResponse = new ServiceResponse<>();

	for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
	    serviceResponse.addMessage(constraint.getMessage());
	}

	return super.handleExceptionInternal(ex, serviceResponse, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    }

    @Bean(name = "validationMessages")
    private Properties getValidationMessages() {

	Properties properties = null;

	try {
	    properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/ValidationMessages.properties"));
	}
	catch (IOException e) {
	    log.debug("O arquivo de propriedade {} não pode ser lido!", "ValidationMessages.properties");
	}

	return properties;
    }

    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

	log.error(ex.getMessage(), ex);

	Mensagem mensagem = null;
	String mensagemCodigo = null;
	final ServiceResponse<Void> serviceResponse = new ServiceResponse<>();

	// Se nenhum tratamento for feito irá definir este cabeçalho HTTP.
	HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	// Se for uma 'GenericException' significa que é uma 'Exception' personalizada
	// da aplicação.
	if (ex instanceof GenericException) {
	    if (ex.getMessage().contains(DELIMITADOR)) {
		mensagemCodigo = ex.getMessage();

		serviceResponse.addMessage(mensagemCodigo);
	    }
	    else {
		mensagem = ((GenericException) ex).getMensagem();
	    }
	}
	else {
	    mensagem = Mensagem.INTERNO;
	}

	if (Objects.nonNull(mensagem)) {
	    serviceResponse.addMessage(mensagem);

	    // Define a um status HTTP com base na mensagem.
	    if (Mensagem.SEM_CONTEUDO_COM_FILTRO.getCodigo().equals(mensagem.getCodigo())) {

		httpStatus = HttpStatus.OK;
	    }
	}
	else if (StringUtils.isNotBlank(mensagemCodigo)) {
	    httpStatus = HttpStatus.BAD_REQUEST;
	}

	log.debug("Fim da execução do handler 'handleAllException'");

	return super.handleExceptionInternal(ex, serviceResponse, HttpHeaders.EMPTY, httpStatus, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {

	log.error("Houve erro(s) na validação de argumento(s). O(s) erro(s) retornado(s): {}", ex.getMessage(),
		headers);

	final ServiceResponse<Void> serviceResponse = new ServiceResponse<>();

	for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	    serviceResponse.addMessage(error.getDefaultMessage());
	}

	for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	    serviceResponse.addMessage(error.getDefaultMessage());
	}

	log.debug("Fim da execução do handler 'handleMethodArgumentNotValid'");

	return super.handleExceptionInternal(ex, serviceResponse, headers, status, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
	    WebRequest request) {

	log.error("Houve erro(s) de bind dos argumento(s). O(s) erro(s) retornado(s): {}", ex.getMessage(), headers);

	final ServiceResponse<Void> serviceResponse = new ServiceResponse<>();

	for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	    if (error.getDefaultMessage().contains(DELIMITADOR)) {
		serviceResponse.addMessage(error.getDefaultMessage());
	    }
	    else {
		serviceResponse.addMessage(error.getCode(), error.getDefaultMessage());
	    }
	}

	for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	    serviceResponse.addMessage(error.getObjectName(), error.getDefaultMessage());
	}

	log.debug("Fim da execução do handler 'handleBindException'");

	return super.handleExceptionInternal(ex, serviceResponse, headers, status, request);
    }
}
