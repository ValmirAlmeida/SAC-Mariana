package br.uefs.ecomp.sacMariana.util;

/**
 * Interface que permite percorrer e acessar elementos de diversas estruturas de dados, como listas encadeadas, pilhas e filas.
 * 
 */
public interface Iterador {
	
	/**
	 * Verifica se existe um pr�ximo elemento na estrutura de dados acessada, retornando <code>true</code> apenas se n�o foi 
	 * atingido o final dessa estrutura.
	 *
	 * @return <code>true</code>, caso exista mais elementos na estrutura; <code>false</code>, caso contr�rio.
	 */
	public boolean temProximo();

	/**
	 * Retorna a refer�ncia para o elemento atual da estrutura e avan�a uma posi��o do cursor.
	 *
	 * @return refer�ncia para o elemento atual
	 */
	public Object obterProximo();

}
