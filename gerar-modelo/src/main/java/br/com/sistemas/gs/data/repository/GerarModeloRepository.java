/**
 * 
 */
package br.com.sistemas.gs.data.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemas.gs.data.domain.Modelo;

/**
 * @author Guilherme
 *
 */
@Repository
public interface GerarModeloRepository extends MongoRepository<Modelo, Long> {

    Optional<Modelo> findByNomeModelo(String nomeModelo);
}
