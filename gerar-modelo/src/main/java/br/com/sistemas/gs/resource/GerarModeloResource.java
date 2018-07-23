/**
 * 
 */
package br.com.sistemas.gs.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemas.gs.commons.service.ServiceResponse;
import br.com.sistemas.gs.data.domain.Modelo;
import br.com.sistemas.gs.service.GerarModeloService;
import br.com.sistemas.gs.service.exception.GerarModeloServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Guilherme
 *
 */
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class GerarModeloResource {

    @Autowired
    private GerarModeloService gerarModeloService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<Boolean>> criar(@Valid @RequestBody Modelo gerarModelo,
	    @RequestHeader final HttpHeaders headers) throws GerarModeloServiceException {

	log.debug("Início do criar Modelo", gerarModelo, headers);

	final ServiceResponse<Boolean> serviceResponse = this.gerarModeloService.criar(gerarModelo);

	log.debug("Sucesso na criação do modelo.");

	return new ResponseEntity<>(serviceResponse, HttpHeaders.EMPTY, HttpStatus.CREATED);
    }
}
