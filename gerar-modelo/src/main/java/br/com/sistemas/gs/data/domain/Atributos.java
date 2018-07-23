/**
 * 
 */
package br.com.sistemas.gs.data.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Guilherme
 *
 */
@NoArgsConstructor(force = true)
@EqualsAndHashCode
@ToString
@Data
public class Atributos implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7888779499058403366L;

    @NotBlank(message = "{atributos.nome.obrigatorio}")
    private String nome;

    @NotBlank(message = "{atributos.tipo.obrigatorio}")
    private String tipo;
}
