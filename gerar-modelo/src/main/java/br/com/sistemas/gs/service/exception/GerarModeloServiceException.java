/**
 * 
 */
package br.com.sistemas.gs.service.exception;

import br.com.sistemas.gs.commons.enums.Mensagem;

/**
 * @author Guilherme
 *
 */
public class GerarModeloServiceException extends GenericException {

	/**
	 * Constrói uma nova exceção com a mensagem detalhada.
	 * 
	 * @param message
	 *            String - Mensagem detalhada da exceção que deseja retornar.
	 */
	public GerarModeloServiceException(final String message) {
		super(message);
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
	public GerarModeloServiceException(final Mensagem mensagem) {
		super(mensagem);
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
	public GerarModeloServiceException(final String message, final Throwable cause) {
		super(message, cause);
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
	public GerarModeloServiceException(final Mensagem mensagem, final Throwable cause) {
		super(mensagem, cause);
	}
}
