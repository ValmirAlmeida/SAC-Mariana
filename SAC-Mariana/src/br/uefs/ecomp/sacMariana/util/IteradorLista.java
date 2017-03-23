package br.uefs.ecomp.sacMariana.util;

/**
 * Classe que implementa a interface Iterador especificamente para uma lista encadeada.
 * @author Valmir Vinicius
 */
public class IteradorLista implements Iterador{
	
	/** N� atual. */
	private No atual;
	
	/**
	 * Constr�i um novo iterador para uma lista encadeada a partir do seu primeiro n�.
	 *
	 * @param primeiro primeiro n� da lista encadeada
	 */
	public IteradorLista(No primeiro){
		this.atual = primeiro; //inicializa a refer�ncia de atual com a refer�ncia para o primeiro n� da lista
	}

	
	/**
	 * Verifica se existem pr�ximos n�s na lista encadeada, retornando <code>true</code> apenas se isso for verdade.
	 * 
	 * @return <code>true</code>, se existem mais elementos na lista encadeada; <code>false</code>, caso contr�rio
	 */
	@Override
	public boolean temProximo() {
		return !(this.atual == null); //verifica se o n� atual possui refer�ncia nula e retorna a nega��o da condi��o
	}


	/**
	 * Recupera o objeto contido no n� atual e muda a refer�ncia desse n� para o pr�ximo da lista.
	 * 
	 * @return refer�ncia para o objeto contido no n� atual
	 */
	@Override
	public Object obterProximo() {
		Object auxiliarObjeto = null; //auxiliar para guardar a refer�ncia do objeto contido no n� atual
		
		auxiliarObjeto = this.atual.getObj(); //obt�m o objeto contido no n� atual
		this.atual = this.atual.getProximo(); //muda a refer�ncia do atributo atual para o pr�ximo n� da lista
		
		return auxiliarObjeto; //retorna a refer�ncia do objeto obtido
	}
}
