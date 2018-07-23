package br.com.sistemas.gs;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.sistemas.gs.data.repository.GerarModeloRepository;
import br.com.sistemas.gs.service.GerarModeloService;
import br.com.sistemas.gs.service.ModeloService;

@ComponentScan(basePackages = { "br.com.sistemas.gs.resource", "br.com.sistemas.gs.service" })
@Configuration
public class GerarModeloTestConfig {

    @Bean
    GerarModeloRepository gerarModeloRepository() {
	return Mockito.mock(GerarModeloRepository.class);
    }

    @Bean
    MongoTemplate mongoTemplate() {
	return Mockito.mock(MongoTemplate.class);
    }

    @Bean
    ModeloService modeloService() {
	return Mockito.mock(ModeloService.class);
    }

    @Bean
    GerarModeloService gerarModeloService() {
	return Mockito.mock(GerarModeloService.class);
    }

}
