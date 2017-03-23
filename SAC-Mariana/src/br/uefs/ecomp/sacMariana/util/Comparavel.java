package br.uefs.ecomp.sacMariana.util;

/**
 * Essa interface permite uma comparação específica entre dois objetos de uma classe que a implementa.
 * 
 * @author Valmir Vinicius 
 */
public interface Comparavel {
	
	/**
	 * Compara o objeto atual com um objeto especificado, de acordo com padrão definido de comparação. 
	 *
	 * @param obj referência para o objeto que será comparado ao objeto atual
	 * @return 1, caso o objeto passado seja maior que o atual, no contexto da implementação; -1, se for menor; 0 se forem iguais.
	 */
	public int comparacao(Object obj);
	
}
