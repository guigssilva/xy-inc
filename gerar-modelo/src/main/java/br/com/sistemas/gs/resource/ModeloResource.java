/**
 * 
 */
package br.com.sistemas.gs.resource;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemas.gs.commons.service.ServiceResponse;
import br.com.sistemas.gs.service.ModeloService;
import br.com.sistemas.gs.service.exception.GerarModeloServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Guilherme
 *
 */
@RestController
@Slf4j
@RequestMapping(value = "/")
public class ModeloResource {

    @Autowired
    private ModeloService modeloService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{modelo}")
    public ResponseEntity<ServiceResponse<Document>> criar(@RequestBody Document documento,
	    @RequestHeader final HttpHeaders headers, @PathVariable(name = "modelo", required = true) String modelo)
	    throws GerarModeloServiceException {

	log.debug("Início do criar Modelo", modelo, headers);

	final ServiceResponse<Document> serviceResponse = this.modeloService.criar(modelo, documento);

	log.debug("Sucesso na criação do modelo.");

	return new ResponseEntity<>(serviceResponse, HttpHeaders.EMPTY, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{modelo}")
    public ResponseEntity<ServiceResponse<List<Document>>> listar(
	    @PathVariable(name = "modelo", required = true) String modelo, @RequestHeader final HttpHeaders headers)
	    throws GerarModeloServiceException {

	log.debug("Início do listar Modelo", modelo, headers);

	final ServiceResponse<List<Document>> serviceResponse = this.modeloService.listar(modelo);

	log.debug("Sucesso na listar do modelo.");

	return new ResponseEntity<>(serviceResponse, HttpHeaders.EMPTY, HttpStatus.OK);
    }

    @GetMapping(path = "/{modelo}/{id}")
    public ResponseEntity<ServiceResponse<Document>> consultar(
	    @PathVariable(name = "modelo", required = true) String modelo,
	    @PathVariable(name = "id", required = true) String id, @RequestHeader final HttpHeaders headers)
	    throws GerarModeloServiceException {

	log.debug("Início do consultar Modelo", modelo, headers);

	final ServiceResponse<Document> serviceResponse = this.modeloService.consultar(modelo, id);

	log.debug("Sucesso na consultar do modelo.");

	return new ResponseEntity<>(serviceResponse, HttpHeaders.EMPTY, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{modelo}/{id}")
    public ResponseEntity<ServiceResponse<Boolean>> atualizar(@RequestBody Document documento,
	    @RequestHeader final HttpHeaders headers, @PathVariable(name = "modelo", required = true) String modelo,
	    @PathVariable(name = "id", required = true) String id) throws GerarModeloServiceException {

	log.debug("Início do atualizar Modelo", modelo, headers);

	final ServiceResponse<Boolean> serviceResponse = this.modeloService.alterar(modelo, id, documento);

	log.debug("Sucesso na atualizacao do modelo.");

	return new ResponseEntity<>(serviceResponse, HttpHeaders.EMPTY, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{modelo}/{id}")
    public ResponseEntity<ServiceResponse<Boolean>> deletar(
	    @PathVariable(name = "modelo", required = true) String modelo,
	    @PathVariable(name = "id", required = true) String id, @RequestHeader final HttpHeaders headers)
	    throws GerarModeloServiceException {

	log.debug("Início do deletar Modelo", modelo, headers);

	final ServiceResponse<Boolean> serviceResponse = this.modeloService.excluir(modelo, id);

	log.debug("Sucesso na deletacao do modelo.");

	return new ResponseEntity<>(serviceResponse, HttpHeaders.EMPTY, HttpStatus.OK);
    }
}
