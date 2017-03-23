package br.uefs.ecomp.sacMariana.util;

/**
 * Essa interface permite uma compara��o espec�fica entre dois objetos de uma classe que a implementa.
 * 
 * @author Valmir Vinicius 
 */
public interface Comparavel {
	
	/**
	 * Compara o objeto atual com um objeto especificado, de acordo com padr�o definido de compara��o. 
	 *
	 * @param obj refer�ncia para o objeto que ser� comparado ao objeto atual
	 * @return 1, caso o objeto passado seja maior que o atual, no contexto da implementa��o; -1, se for menor; 0 se forem iguais.
	 */
	public int comparacao(Object obj);
	
}
