/**
 * 
 */
package br.com.sistemas.gs.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.sistemas.gs.service.GerarModeloService;

/**
 * @author Guilherme
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(GerarModeloResource.class)
public class GerarModeloResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerarModeloService gerarModeloService;

    @Test
    public void criar_sucesso() {

    }
}
