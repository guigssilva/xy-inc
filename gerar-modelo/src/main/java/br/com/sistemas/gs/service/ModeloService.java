/**
 * 
 */
package br.com.sistemas.gs.service;

import java.util.List;

import org.bson.Document;

import br.com.sistemas.gs.commons.service.ServiceResponse;
import br.com.sistemas.gs.service.exception.GerarModeloServiceException;

/**
 * @author Guilherme
 *
 */
public interface ModeloService {

    ServiceResponse<List<Document>> listar(String modelo) throws GerarModeloServiceException;

    ServiceResponse<Document> consultar(String modelo, String id) throws GerarModeloServiceException;

    ServiceResponse<Document> criar(String modelo, Document documento) throws GerarModeloServiceException;

    ServiceResponse<Boolean> alterar(String modelo, String id, Document documento) throws GerarModeloServiceException;

    ServiceResponse<Boolean> excluir(String modelo, String id) throws GerarModeloServiceException;
}
