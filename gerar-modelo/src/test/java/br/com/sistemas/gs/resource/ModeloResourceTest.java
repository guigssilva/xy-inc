/**
 * 
 */
package br.com.sistemas.gs.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sistemas.gs.GerarModeloTestConfig;
import br.com.sistemas.gs.data.repository.GerarModeloRepository;

/**
 * @author Guilherme
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GerarModeloTestConfig.class)
@WebMvcTest(ModeloResource.class)
public class ModeloResourceTest {

    @MockBean
    private MongoTemplate mongoTemplate;

    @MockBean
    private GerarModeloRepository gerarModeloRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void alterar_sucesso() {

    }
}
