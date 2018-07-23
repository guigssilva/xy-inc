/**
 * 
 */
package br.com.sistemas.gs.data.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "Modelo")
public class Modelo extends AbstractId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 942985371967027768L;

    @NotBlank(message = "{modelo.nomeModelo.obrigatorio}")
    @Indexed(unique = true)
    private String nomeModelo;

    @NotEmpty(message = "{modelo.atributos.obrigatorio}")
    @Valid
    private List<Atributos> atributos;
}
