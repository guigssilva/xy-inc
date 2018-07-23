/**
 * 
 */
package br.com.sistemas.gs.service;

<<<<<<< HEAD
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@WebMvcTest(GerarModeloService.class)
public class GerarModeloServiceTest {

    @MockBean
    private GerarModeloRepository gerarModeloRepository;

    @Test
    public void criar_sucesso() {

    }
=======
/**
 * @author Guilherme
 *
 */
public class GerarModeloServiceTest {

>>>>>>> branch 'master' of https://github.com/guigssilva/xy-inc.git
}
