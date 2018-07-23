/**
 * 
 */
package br.com.sistemas.gs.data.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * @author Guilherme
 *
 */
@Data
public class AbstractId {

    @Id
    private String id;
}
