/**
 * 
 */
package br.com.sistemas.gs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import br.com.sistemas.gs.commons.enums.Mensagem;
import br.com.sistemas.gs.commons.service.ServiceResponse;
import br.com.sistemas.gs.data.domain.Atributos;
import br.com.sistemas.gs.data.repository.GerarModeloRepository;
import br.com.sistemas.gs.service.exception.GerarModeloServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Guilherme
 *
 */
@Slf4j
@Service
public class ModeloServiceImpl implements ModeloService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GerarModeloRepository gerarModeloRepository;

    @Override
    public ServiceResponse<List<Document>> listar(String modelo) throws GerarModeloServiceException {

	log.debug("Inicia o listar todos a partir do modelo: {} ", modelo);
	final ServiceResponse<List<Document>> retorno = new ServiceResponse<>();

	final List<Document> documentos = new ArrayList<>();
	this.retornaColecaoByModelo(modelo).find().forEach((Block<Document>) p -> documentos.add(p));

	if (CollectionUtils.isEmpty(documentos)) {
	    throw new GerarModeloServiceException(Mensagem.SEM_CONTEUDO_FILTRO);
	}

	retorno.addMessage(Mensagem.SUCESSO);
	retorno.setResult(documentos);

	log.debug("Sucesso ao listar todos a partir do modelo: {} ", modelo);

	return retorno;
    }

    @Override
    public ServiceResponse<Document> consultar(String modelo, String id) throws GerarModeloServiceException {

	log.debug("Inicia o consultar todos a partir do modelo: {} ", modelo);
	final ServiceResponse<Document> retorno = new ServiceResponse<>();
	retorno.setResult(Optional
		.ofNullable(this.retornaColecaoByModelo(modelo).find(Filters.eq("_id", new ObjectId(id))).first())
		.orElseThrow(() -> new GerarModeloServiceException(Mensagem.SEM_CONTEUDO_COM_FILTRO)));
	retorno.addMessage(Mensagem.SUCESSO);

	log.debug("Sucesso ao consultar a partir do id a partir do modelo: {} ", modelo);

	return retorno;
    }

    @Override
    public ServiceResponse<Document> criar(String modelo, Document documento) throws GerarModeloServiceException {
	log.debug("Inicia o criar o documento do modelo: {} ", modelo);
	final ServiceResponse<Document> retorno = new ServiceResponse<>();

	validaAtributosByModelo(modelo, documento, retorno);

	if (CollectionUtils.isEmpty(retorno.getAll())) {

	    this.retornaColecaoByModelo(modelo).insertOne(documento);

	    retorno.setResult(documento);
	    retorno.addMessage(Mensagem.CRIADO);
	}

	log.debug("Sucesso ao criar o documento do modelo: {} ", modelo);

	return retorno;
    }

    @Override
    public ServiceResponse<Boolean> alterar(String modelo, String id, Document documento)
	    throws GerarModeloServiceException {
	log.debug("Inicia o alterar o documento do modelo: {} ", modelo);
	final ServiceResponse<Boolean> retorno = new ServiceResponse<>();

	validaAtributosByModelo(modelo, documento, retorno);

	if (CollectionUtils.isEmpty(retorno.getAll())) {
	    retorno.setResult(this.retornaColecaoByModelo(modelo)
		    .replaceOne(Filters.eq("_id", new ObjectId(id)), documento).isModifiedCountAvailable());
	    retorno.addMessage(Mensagem.ALTERADO);

	    log.debug("Sucesso ao alterar o documento do modelo: {} ", modelo);
	}

	return retorno;
    }

    @Override
    public ServiceResponse<Boolean> excluir(String modelo, String id) throws GerarModeloServiceException {
	log.debug("Inicia o excluir o documento do modelo: {} ", modelo);
	final ServiceResponse<Boolean> retorno = new ServiceResponse<>();

	retorno.setResult(
		this.retornaColecaoByModelo(modelo).deleteOne(Filters.eq("_id", new ObjectId(id))).wasAcknowledged());

	log.debug("Sucesso ao excluir o documento do modelo: {} ", modelo);
	return retorno;
    }

    private MongoCollection<Document> retornaColecaoByModelo(String modelo) throws GerarModeloServiceException {

	final MongoCollection<Document> retorno = Optional.ofNullable(this.mongoTemplate.getCollection(modelo))
		.orElseThrow(() -> new GerarModeloServiceException(Mensagem.COLECAO_NAO_ENCONTRADO));

	return retorno;
    }

    private void validaAtributosByModelo(String nomeModelo, Document documento, ServiceResponse serviceResponse)
	    throws GerarModeloServiceException {
	final List<Atributos> atributos = this.gerarModeloRepository.findByNomeModelo(nomeModelo)
		.orElseThrow(() -> new GerarModeloServiceException(Mensagem.MODELO_NAO_ENCONTRADO)).getAtributos();

	List<Atributos> atributosDiferentes = atributos.stream()
		.filter(a -> documento.containsKey(a.getNome())
			&& !a.getTipo().equals(documento.get(a.getNome()).getClass().getSimpleName()))
		.collect(Collectors.toList());

	atributosDiferentes.forEach(ad -> serviceResponse.addMessage(Mensagem.TIPO_DIFERENTE.getCodigo(),
		Mensagem.TIPO_DIFERENTE.show(nomeModelo, ad.getNome(), ad.getTipo())));
    }
}
