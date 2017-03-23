package br.uefs.ecomp.sacMariana.util;

/**
 * Interface que permite percorrer e acessar elementos de diversas estruturas de dados, como listas encadeadas, pilhas e filas.
 * 
 */
public interface Iterador {
	
	/**
	 * Verifica se existe um próximo elemento na estrutura de dados acessada, retornando <code>true</code> apenas se não foi 
	 * atingido o final dessa estrutura.
	 *
	 * @return <code>true</code>, caso exista mais elementos na estrutura; <code>false</code>, caso contrário.
	 */
	public boolean temProximo();

	/**
	 * Retorna a referência para o elemento atual da estrutura e avança uma posição do cursor.
	 *
	 * @return referência para o elemento atual
	 */
	public Object obterProximo();

}
