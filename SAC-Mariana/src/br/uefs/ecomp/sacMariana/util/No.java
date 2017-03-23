package br.uefs.ecomp.sacMariana.util;

/**
 * Classe para moldar os n�s da lista encadeada.
 */
public class No {

	/** Refer�ncia para objeto gen�rico contido no n�. */
	private Object obj;
	
	/** Refer�ncia para o pr�ximo n� na lista. */
	private No proximo;
	
	/**
	 * Constr�i um novo n� e inicializa seus atributos com os dados especificados.
	 *
	 * @param obj refer�ncia para o objeto que estar� no n�
	 * @param proximo refer�ncia para o pr�ximo n� na lista
	 */
	public No(Object obj, No proximo){
		/* Inicializa os atributos do n� com as informa��es recebidas. */
		this.obj = obj;
		this.proximo = proximo;
	}

	/**
	 * M�todo para obten��o da refer�ncia para o objeto contido no n�.
	 *
	 * @return refer�ncia para o objeto contido no n�
	 */
	public Object getObj(){
		return this.obj; //retorna a refer�ncia do atributo "objGenerico"
	}
	
	/**
	 * M�todo para obten��o da refer�ncia para o pr�ximo n� da lista.
	 *
	 * @return refer�ncia para o pr�ximo n� da lista
	 */
	public No getProximo(){
		return this.proximo; //retorna a refer�ncia do atributo "proximo"
	}
	
	/**
	 * Configura o objeto que estar� contido no n�.
	 *
	 * @param obj refer�ncia para o objeto que estar� contido no n�
	 */
	public void setObj(Object obj){
		this.obj = obj; //atribui a refer�ncia recebida por par�metro ao atributo "objeto"
	}
	
	/**
	 * Configura o pr�ximo n� da lista.
	 *
	 * @param proximo refer�ncia para o pr�ximo n� da lista
	 */
	public void setProximo(No proximo){
		this.proximo = proximo; //atribui a refer�ncia recebida por par�metro ao atributo "proximo"
	}
}
