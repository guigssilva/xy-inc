/**
 * 
 */
package br.com.sistemas.gs.commons.enums;

import lombok.Getter;

/**
 * @author Guilherme
 *
 */
public enum Mensagem {

    /**
     * Mensagem utilizada quando comando realizado com sucesso para alteração,
     * inativação, ativação e consulta.
     */
    SUCESSO("0", "Operação realizada com sucesso."),

    CRIADO("0", "As informações foram criados com sucesso."),

    EXISTENTE("-1", "As informações do modelo informado já está cadastrado."),

    TIPO_DIFERENTE("-2", "O modelo ${0} possui o atributo ${1}, no qual o tipo dele tem que ser ${2}."),

    ALTERADO("0", "As informações foram alteradas com sucesso."),

    /**
     * Mensagem utilizada quando o comando é executado com sucesso mas não há
     * registros a serem retornados.
     */
    SEM_CONTEUDO_COM_FILTRO("3", "Não existem resultados a serem exibidos para a pesquisa informada."),

    /**
     * Mensagem utilizada quando o comando é executado com sucesso mas não há
     * registros a serem retornados.
     */
    SEM_CONTEUDO_FILTRO("3", "Não existem resultados a serem exibidos."),

    MODELO_NAO_ENCONTRADO("-9", "O Modelo não foi encontrado."),

    COLECAO_NAO_ENCONTRADO("-9", "A Coleção desejada não foi encontrado."),

    /**
     * Mensagem utilizada quando ocorre erro interno do sistema.
     */
    INTERNO("-99", "Erro Interno."),

    /**
     * Mensagem utilizada quando não é encontrada nenhuma outra mensagem cadastrada.
     */
    UNKNOWN("9999", "Mensagem desconhecida ou não informada ou não de negócio.");

    @Getter
    private final String codigo;

    @Getter
    private String descricao;

    /**
     * Constrói o objeto Mensagem com um código e uma descrição.
     * 
     * @param codigo
     *            String - Código da mensagem.
     * 
     * @param descricao
     *            String - Descrição da mensagem.
     */
    Mensagem(final String codigo, final String descricao) {
	this.codigo = codigo;
	this.descricao = descricao;
    }

    /**
     * Metodo responsavel por receber a mensagem e trocar por valores passados.
     * 
     * @param params
     *            - parametros para realizar troca da referência por valor
     */
    public String show(String... params) {
	Integer i = 0;
	String retorno = this.descricao;

	for (String param : params) {
	    retorno = retorno.replace("${" + i + "}", param);
	    i += 1;
	}

	return retorno;
    }
}
