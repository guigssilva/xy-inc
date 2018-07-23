/*
* Copyright(c) by VR Benefícios
*
* All rights reserved.
*
* This software is confidential and proprietary information of
* VR Benefícios ("Confidential Information").
* You shall not disclose such Confidential Information and shall
* use it only in accordance with the terms of the license agreement
* you entered with VR Benefícios.
*/

package br.com.sistemas.gs;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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

    @Bean
    RestTemplate restTemplate() {
	return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

}
