/**
 * 
 */
package br.com.sistemas.gs.service;

import br.com.sistemas.gs.commons.service.ServiceResponse;
import br.com.sistemas.gs.data.domain.Modelo;
import br.com.sistemas.gs.service.exception.GerarModeloServiceException;

/**
 * @author Guilherme
 *
 */
public interface GerarModeloService {

    ServiceResponse<Boolean> criar(Modelo gerarModelo) throws GerarModeloServiceException;
}
