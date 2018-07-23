/**
 * 
 */
package br.com.sistemas.gs.commons.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sistemas.gs.commons.enums.Mensagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Guilherme
 *
 */
@EqualsAndHashCode
@ToString
public class ServiceResponse<T extends Object> {

	private static final String MESSAGE_DELIMITER = "||";

	@Getter
	@Setter
	private T result;

	@JsonProperty("messages")
	private List<Message> messages = new ArrayList<>();

	@Data
	@AllArgsConstructor
	private class Message {
		private String code;
		private String description;
	}

	@JsonIgnore
	public List<Message> getAll() {
		return this.messages;
	}

	@JsonIgnore
	public Boolean isEmpty() {
		return this.messages.isEmpty();
	}

	/**
	 * Método que retorna todos os códigos.
	 * 
	 * @return
	 */
	@JsonIgnore
	public List<String> getAllCodes() {

		final List<String> codeMessages = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(this.messages)) {
			codeMessages.addAll(this.messages.stream().map(Message::getCode).collect(Collectors.toList()));
		}

		return codeMessages;
	}

	/**
	 * Adiciona uma mensagem através de objeto Mensagem.
	 * 
	 * @param mensagem
	 *            Mensagem - Objeto conténdo o código e a descrição de retorno.
	 * 
	 * @see Mensagem
	 */
	public void addMessage(final Mensagem mensagem) {
		this.addMessage(mensagem.getCodigo(), mensagem.getDescricao());
	}

	/**
	 * Adiciona uma mensagem através de mensagem detalhada.
	 * 
	 * @param message
	 *            String - Mensagem detalhada de retorno do serviço, contendo o
	 *            código e a descrição, separados por um delimitador.
	 */
	public void addMessage(final String message) {

		final String[] toSplit = StringUtils.split(message, MESSAGE_DELIMITER);

		if (!StringUtils.isEmpty(toSplit)) {
			this.addMessage(toSplit[0], toSplit[1]);
		} else {
			// Se o separador não foi encontrado então não é possível encontrar o código.
			this.addMessage(null, message);
		}
	}

	/**
	 * Adiciona uma mensagem através de mensagem detalhada.
	 * 
	 * @param code
	 *            String - Código de retorno do serviço.
	 * 
	 * @param description
	 *            String - Descrição de retorno do serviço.
	 */
	public void addMessage(final String code, final String description) {
		this.messages.add(new Message(code, description));
	}
}
