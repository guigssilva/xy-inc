/**
 * 
 */
package br.com.sistemas.gs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemas.gs.commons.enums.Mensagem;
import br.com.sistemas.gs.commons.service.ServiceResponse;
import br.com.sistemas.gs.data.domain.Modelo;
import br.com.sistemas.gs.data.repository.GerarModeloRepository;
import br.com.sistemas.gs.service.exception.GerarModeloServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Guilherme
 *
 */
@Slf4j
@Service
public class GerarModeloServiceImpl implements GerarModeloService {

    @Autowired
    private GerarModeloRepository gerarModeloRepository;

    @Override
    public ServiceResponse<Boolean> criar(Modelo gerarModelo) throws GerarModeloServiceException {
	log.debug("Iniciando da criação do modelo.");

	this.gerarModeloRepository.findByNomeModelo(gerarModelo.getNomeModelo()).ifPresent(o -> {
	    new GerarModeloServiceException(Mensagem.EXISTENTE);
	});

	final ServiceResponse<Boolean> serviceResponse = new ServiceResponse<>();
	serviceResponse.setResult(Boolean.FALSE);

	this.gerarModeloRepository.save(gerarModelo);

	serviceResponse.setResult(Boolean.TRUE);
	serviceResponse.addMessage(Mensagem.SUCESSO);

	return serviceResponse;
    }

}
