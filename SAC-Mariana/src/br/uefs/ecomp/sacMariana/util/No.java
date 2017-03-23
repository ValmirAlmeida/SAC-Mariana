package br.uefs.ecomp.sacMariana.util;

/**
 * Classe para moldar os nós da lista encadeada.
 */
public class No {

	/** Referência para objeto genérico contido no nó. */
	private Object obj;
	
	/** Referência para o próximo nó na lista. */
	private No proximo;
	
	/**
	 * Constrói um novo nó e inicializa seus atributos com os dados especificados.
	 *
	 * @param obj referência para o objeto que estará no nó
	 * @param proximo referência para o próximo nó na lista
	 */
	public No(Object obj, No proximo){
		/* Inicializa os atributos do nó com as informações recebidas. */
		this.obj = obj;
		this.proximo = proximo;
	}

	/**
	 * Método para obtenção da referência para o objeto contido no nó.
	 *
	 * @return referência para o objeto contido no nó
	 */
	public Object getObj(){
		return this.obj; //retorna a referência do atributo "objGenerico"
	}
	
	/**
	 * Método para obtenção da referência para o próximo nó da lista.
	 *
	 * @return referência para o próximo nó da lista
	 */
	public No getProximo(){
		return this.proximo; //retorna a referência do atributo "proximo"
	}
	
	/**
	 * Configura o objeto que estará contido no nó.
	 *
	 * @param obj referência para o objeto que estará contido no nó
	 */
	public void setObj(Object obj){
		this.obj = obj; //atribui a referência recebida por parâmetro ao atributo "objeto"
	}
	
	/**
	 * Configura o próximo nó da lista.
	 *
	 * @param proximo referência para o próximo nó da lista
	 */
	public void setProximo(No proximo){
		this.proximo = proximo; //atribui a referência recebida por parâmetro ao atributo "proximo"
	}
}
