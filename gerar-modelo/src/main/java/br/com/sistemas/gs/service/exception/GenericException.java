package br.com.sistemas.gs.service.exception;

import br.com.sistemas.gs.commons.enums.Mensagem;
import lombok.Getter;

/**
 * Exception genérica do sistema.
 * 
 * @author Verity
 *
 */
public class GenericException extends Exception {

    private static final long serialVersionUID = 779823760601827117L;

    @Getter
    private final Mensagem mensagem;

    /**
     * Constrói uma nova exceção com a mensagem detalhada.
     * 
     * @param message
     *            String - Mensagem detalhada da exceção que deseja retornar.
     */
    public GenericException(final String message) {
	super(message);
	// Como não é passado como parâmetro é apenas inicializado.
	this.mensagem = Mensagem.INTERNO;
    }

    /**
     * Constrói uma nova exceção com um objeto Mensagem.
     * 
     * @param mensagem
     *            Mensagem - Objeto a ser utilizado, conténdo a mensagem detalhada
     *            de retorno.
     * 
     * @see Mensagem
     */
    public GenericException(final Mensagem mensagem) {
	super(mensagem.getDescricao());
	this.mensagem = mensagem;
    }

    /**
     * Constrói uma nova exceção com a mensagem detalhada de retorno e a causa da
     * exceção capturada.
     * 
     * @param message
     *            String - Mensagem detalhada da exceção que deseja retornar.
     * 
     * @param cause
     *            Throwable - Causa da exceção capturada.
     */
    public GenericException(final String message, final Throwable cause) {
	super(message, cause);
	// Como não é passado como parâmetro é apenas inicializado.
	this.mensagem = Mensagem.INTERNO;
    }

    /**
     * Constrói uma nova exceção com um objeto Mensagem e a causa da exceção
     * capturada.
     * 
     * @param mensagem
     *            Mensagem - Objeto a ser utilizado, conténdo a mensagem detalhada
     *            de retorno.
     * 
     * @param cause
     *            Throwable - Causa da exceção capturada.
     * 
     * @see Mensagem
     */
    public GenericException(final Mensagem mensagem, final Throwable cause) {
	super(mensagem.getDescricao(), cause);
	this.mensagem = mensagem;
    }
}
