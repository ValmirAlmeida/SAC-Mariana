package br.uefs.ecomp.sacMariana.util;

/**
 * Classe que implementa uma lista encadeada. Permite que operações de inserção, remoção, obtenção, ordenação e
 * acompanhamento do estado da lista (obter o tamanho e verificar se está vazia).
 * 
 * @author Valmir Vinicius
 */
public class ListaEncadeada implements ILista {
	
	/** Referência para o primeiro nó da lista. */
	private No primeiro;
	
	/** Tamanho da lista encadeada. */
	private int tamanhoLista;
	
	/* Na medida do possível, as especificações em JAVADOC aqui contidas buscam focar mais no modo de implementação utilizando, visando 
	 * fornecer maiores detalhes para quem acessar a documentação do sistema e evitar repetições do que foi antes explicitado no JAVADOC
	 * da interface implementada por essa classe.
	 */
	
	/**
	 * Verifica se a lista encadeada está vazia, retornando <code>true</code> apenas se não existir referência para o primeiro nó da lista.
	 * 
	 * @return <code>true</code>, se o primeiro nó da lista for uma referência <code>null</code>; <code>false</code>, caso contrário
	 */
	@Override
	public boolean estaVazia() {
		return (this.primeiro == null); //verifica se o primeiro nó da lista possui uma referência null e retorna o resultado booleano da comparação
	}


	/**
	 * Retorna a quantidade de nós da lista encadeada.
	 * 
	 * @return número de nós da lista encadeada
	 */
	@Override
	public int obterTamanho() {
		return this.tamanhoLista; //retorna o atributo correspondente ao tamanho da lista
	}

	/**
	 * Cria um novo nó com o objeto recebido e o define como primeiro da lista.
	 * 
	 * @param obj referência para o objeto que se deseja alocar no primeiro nó da lista
	 */
	@Override
	public void inserirInicio(Object obj) {
		No novoNo = null; //referência para o novo nó da lista
		
		if(this.estaVazia() == true){ //verifica se a lista está vazia
			novoNo = new No(obj, null); //se a lista estiver vazia o novo nó é criado tendo uma referência null para próximo nó
		}else{
			novoNo = new No(obj, this.primeiro); //caso já existam nós na lista o novo nó é criado tendo a referência para o primeiro nó atual como próximo
		}
		
		this.primeiro = novoNo; //faz com que o atributo que especifica o primeiro nó da lista referencie o novo nó
		
		this.tamanhoLista++; //incrementa um no tamanho da lista
	}


	/**
	 * Cria um novo nó com o objeto recebido e o define como último da lista encadeada
	 * 
	 * @param obj referência para o objeto que se deseja alocar no último nó da lista
	 */
	@Override
	public void inserirFinal(Object obj) {
		No novoNo = null; //conterá a referência para o novo nó que será criado
		No auxiliarNo = null; //nó auxiliar para percorrer a lista
		
		novoNo = new No(obj, null); //instancia um novo nó com o objeto recebido e definindo o próximo como null
			
		if(this.estaVazia() == true){ //verifica se a lista está vazia
			this.primeiro = novoNo; //caso a lista esteja vazia o novo nó criado é definido como o primeiro
		}else{
			auxiliarNo = this.primeiro; //atribui a referência do primeiro nó à variável que será utilizada no processo de percorrer a lista
		
			/* No laço de repetição abaixo a lista é percorrida enquanto o último nó dela não é encontrado, de forma a obter
			 * a referência para este último nó e utilizá-la no processso de inserir no final. */
			while(auxiliarNo.getProximo() != null){ //verifica se o nó atual contém uma referência não null para próximo nó, isto é, se o nó atual não é o último da lista
				auxiliarNo = auxiliarNo.getProximo(); //faz com que o nó auxiliar referencie para o próximo nó
			}
				
			auxiliarNo.setProximo(novoNo); //faz com que a referência de próximo do nó atual seja o novo nó
		}
			
		this.tamanhoLista++; //incrementa um no tamanho da lista
	}


	/**
	 * Remove o primeiro nó da lista encadeada, se a lista não estiver vazia, retornando a referência para o objeto contido 
	 * no nó removido.
	 * @return referência para o objeto contido no primeiro nó da lista; <code>null</code>, caso a lista esteja vazia
	 */
	@Override
	public Object removerInicio() {
		Object objRemovido = null; //auxiliar que conterá a referência para o objeto contido no nó removido
		
		if(this.estaVazia() == true){ //verifica se a lista está vazia
			return null; //caso a lista esteja vazia retorna uma referência null
		}
		
		objRemovido = this.primeiro.getObj(); //obtém a referência do objeto contido no primeiro nó da lista
		
		this.primeiro = this.primeiro.getProximo(); //faz o primeiro nó apontar para o segundo nó da lista, de forma que a referência deste primeiro nó é perdida
		
		this.tamanhoLista--; //diminui em um o tamanho da lista
		
		return objRemovido; //retorna o objeto contido no nó removido
	}


	/**
	 * Remove o último nó da lista encadeada, se a lista não estiver vazia, retornando a referência para o objeto contido no 
	 * nó removido.
	 * @return referência para o objeto contido no último nó da lista; <code>null</code>, caso a lista esteja vazia
	 */
	@Override
	public Object removerFinal() {
		No atual = null; //referência para um nó atual afim de auxiliar no percorrimento da lista
		No anterior = null; //referência para um nó anterior para auxiliar no percorrimento da lista
		
		atual = this.primeiro; //obtém a referência do primeiro nó
		
		if(this.estaVazia() == true){ //verifica se a lista está vazia
			return null; //caso a lista esteja vazia retorna uma referência null
		}
		
		/* No laço de repetição abaixo a lista é percorrida enquanto o último nó dela não é encontrado, de forma a obter
		 * a referência para este último nó e utilizá-la no processso de remover no final da lista. */
		while(atual.getProximo() != null){ //verifica se o nó atual contém uma referência não null para próximo nó, isto é, se o nó atual não é o último da lista
			anterior = atual; //obtém a referência do nó anterior ao atual
			atual = atual.getProximo(); //obtém a referência para o nó que sucede o atual na lista
		}	
		
		anterior.setProximo(null); //configura como null a referência de próximo do nó anterior ao último
		
		this.tamanhoLista--; //diminui em um o tamanho da lista
		
		return atual.getObj(); //retorna o objeto contido no nó removido da lista
	}


	/**
	 * Remove um nó qualquer da lista por meio do index especificado, retornando a referência do objeto contido no 
	 * nó removido.
	 * 
	 * @param index do nó que será removido
	 * @return referência do objeto contido no nó removido
	 */
	@Override
	public Object remover(int index) {
		int contador = 0; //contador para auxiliar na busca do objeto cujo index foi fornecido
		No atual = null; //referência para um nó atual afim de auxiliar no percorrimento da lista
		No anterior = null; //referência para um nó anterior para auxiliar no percorrimento da lista
		
		atual = this.primeiro; //obtém a referência do primeiro nó da lista
		
		if(this.estaVazia() == true || index > this.obterTamanho()-1 || index < 0){ //verifica se a lista está vazia e se o index fornecido é válido (não é menor que 0 ou maior do que esperado)
			return null; //retorna null caso o index seja inválido ou se a lista estiver vazia
		}else if(index == 0){ //verifica se o index fornecido é 0, isto é, se o nó que será removido é o primeiro
			return this.removerInicio(); //chama o método de remover no início, retornando o objeto contido no nó removido
		}else if(index == this.obterTamanho()-1){ //verifica se o index fornecido corresponde ao último index válido da lista, isto é, se o nó que será removido é o último da lista
			return removerFinal(); //chama o método de remover do final, retornando o objeto contido no nó removido
		}
		
		/* No laço de repetição abaixo a lista é percorrida enquanto o valor do contador não se iguala ao index, isto é, 
		 * enquanto não foi encontrado o nó correspondente ao index fornecido. */
		while(index != contador){ //verifica se o valor do index é diferente do contador
			anterior = atual; //guarda a referência para o nó anterior
			atual = atual.getProximo(); //obtém a referência do próximo nó
			contador++; //incrementa o contador com um
		}
		
		anterior.setProximo(atual.getProximo()); //configura o atributo proximo do nó anterior ao removido com a referência do nó seguinte ao removido
		
		this.tamanhoLista--; //diminui em um o tamanho da lista
		
		return atual.getObj(); //retorna a referência para o objeto contido no nó removido
	}

	/** Recupera a referência para o objeto contido no nó cujo index foi especificado, se a lsta não estiver vazia e o valor de index informado for válido.
	 * 
	 *  @param index posição do nó que contém o objeto desejado
	 * 	@return referência para objeto contido no nó cujo index foi especificado, se o index for válido; <code>null</code>, caso contrário.
	 */
	@Override
	public Object recuperar(int index) {
		int contador = 0; //contador para auxiliar na busca do objeto cujo index foi fornecido
		No auxiliarNo = null; //nó auxiliar 
		
		if(this.estaVazia() == true || index > this.obterTamanho()-1 || index < 0){ //verifica se a lista está vazia e se o index fornecido é válido (não é menor que 0 ou maior do que esperado)
			return null; //retorna null caso o index seja inválido ou se a lista estiver vazia
		}
		
		auxiliarNo = this.primeiro; //obtém o primeiro nó da lista

		/* No laço de repetição abaixo a lista é percorrida enquanto o valor do contador não se iguala ao index, isto é, 
		 * enquanto não foi encontrado o nó correspondente ao index fornecido. */
		while(index != contador){ //verifica se o valor do index é diferente do contador
			auxiliarNo = auxiliarNo.getProximo(); //obtém o próximo nó
			contador++; //incrementa um no contador
		}
		
		return auxiliarNo.getObj(); //retorna o objeto contido no nó encontrado
	}
	
	/**
	 * Recupera a referência para um nó da lista tendo como parâmetro de busca o objeto contido nesse nó, se o objeto
	 * fornecido for válido.
	 *
	 * @param obj objeto contido no nó que se deseja obter a referência
	 * @return referência para um nó, se o objeto fornecido for válido; <code>null</code>, caso contrário.
	 */
	/* A criação do método abaixo foi fundamental para o processo de trocar objetos entre dois nós, um dos métodos 
	 * necessários na ordenação implementada. */
	private No recuperarNo(Object obj){
		No noAuxiliar = null; //nó para auxiliar no processo de busca
		
		if(obj == null || this.estaVazia() == true){ //verifica se a referência de objeto informada é null e se a lista está vazia
			return null; //retorna referência null caso alguma das condições seja satisfeita
		}

		noAuxiliar = this.primeiro; //obtém a referência para o primeiro nó da lista
		
		
		/* No laço de repetição abaixo é feita uma busca na lista pelo nó que contém um objeto com a mesma referência do objeto
		 * recebido na função. Se, nesse processo, o fim da lista for atingido e ainda assim o nó desejado não tenha sido encontrado
		 * é retornada uma referência null.
		 */
		while(obj.equals(noAuxiliar.getObj()) == false){ //enquanto não for encontrado um objeto na lista com a mesma referência do objeto informado o laço de repetição é executado
			noAuxiliar = noAuxiliar.getProximo(); //obtém a referência para o próximo nó
			
			if(noAuxiliar == null){ //verifica se a referência do nó é null, isto é, se foi atingido o fim da lista
				return null; //retorna referência null caso a condição seja verdadeira
			}
		}
		
		
		return noAuxiliar; //retorna a referência para o nó desejado
	}
	
	/**
	 * Permuta os objetos especificados entre os seus respectivos nós na lista encadeada, retornando <code>true</code>, apenas se
	 * o processo for realizado com sucesso.
	 *
	 * @param obj referência para um dos objetos que será permutado
	 * @param obj2 referência para um dos objetos que será permutado
	 * @return <code>true</code>, apenas se a lista não estiver vazia e se as referências de objetos informadas forem válidas; <code>false</code>, caso contrário
	 */
	/* A construção do método abaixo foi fundamental para o processo de ordenação */
	private boolean permutaObjetosEntreNos(Object obj, Object obj2){
		No no1 = null, no2 = null; //referência para os nós
		Object objAuxiliar = null; //referência auxiliar de objeto para auxiliar no processo de permutação
		
		if(this.estaVazia() == true){ //verifica se a lista está vazia
			return false; //retorna false indicando que não foi possível realizar o processo
		}
		
		no1 = this.recuperarNo(obj); //recupera a referência do nó que contém o primeiro objeto
		no2 = this.recuperarNo(obj2); //recupera a referência do nó que contém o segundo objeto
		
		if(no1 == null || no2 == null){ //verifica se algum dos nós obtidos possui referência null, isto é, se foi informado um objeto inválido
			return false; //retorna false indicando que não foi possível realizar o processo
		}
		
		/* Nas linhas abaixo os objetos são permutados nos nós obtidos anteriormente */
		objAuxiliar = no1.getObj(); //guarda a referência para o objeto contido no primeiro nó
		no1.setObj(no2.getObj()); //o atributo objeto do primeiro nó é configurado para referenciar o objeto contido no segundo nó
		no2.setObj(objAuxiliar); //o atributo objeto do segundo nó é configurado para referenciar o objeto que anteriormente estava no primeiro nó
		
		return true; //retorna true indicando que o processo ocorreu com sucesso
			
	}
	
	/**
	 * Ordena a lista encadeada apenas se ela for composta de objetos que implementem a interface Comparavel.
	 * 
	 * @see br.uefs.ecomp.sacMariana.util.Comparavel
	 * @return <code>true</code>, se a ordenação ocorrer corretamente; <code>false</code>, caso contrário.
	 */
	public boolean ordenar(){
		Comparavel objComparavel1 = null, objComparavel2 = null; //referências do tipo Comparavel para auxiliar na ordenação
		No noAuxiliar1 = null, noAuxiliar2 = null; //referências auxiliares para nós da lista encadeada
		int contador=0; //armazenará a quantidade de vezes que um nó já foi selecionado na ordenação
		
		if(this.estaVazia() == true){ //verifica se a lista está vazia
			return false; //retorna false indicando que não foi possível concluir a ordenação
		}
			
		if(this.primeiro.getObj() instanceof Comparavel == false){ //verifica se o primeiro objeto (e consequentemente toda a lista) não implementa a interface Comparavel
			return false; //retorna false indicando que não foi possível concluir a ordenação
		}
		
		noAuxiliar1 = this.primeiro; //obtém a referência para o primeiro nó da lista
		
		/* Nas linhas de código a seguir é realizado o processo de ordenação. No método de ordenação implementado, cada nó da lista (exceto o último) é
		 * selecionado e o seu objeto é comparado com todos os outros que estão em nós à sua direita na lista, de forma que, se 
		 * o objeto que está sendo comparado ao objeto selecionado for "maior" que ele, os objetos são permutados entre seus nós. Os conceitos 
		 * para especificar se um objeto é "maior" que outro foram previamente definidos durante a implementação da interface Comparavel nesses objetos.
		 * 
		 */
		
		while(noAuxiliar1 != null){ //verifica se a referência do nó selecionado é não nula
			objComparavel1 = (Comparavel) noAuxiliar1.getObj(); //obtém a referência para o objeto contido no nó selecionado na iteração
			noAuxiliar2 = noAuxiliar1.getProximo(); //obtém a referência para o nó que sucede o atualmente selecionado
			contador++; //incrementa em um o contador
			
			while(noAuxiliar2 != null){ //verifica se o nó que será comparado possui uma referência nula, isto é, verifica se o fim da lista foi atingido
				objComparavel2 = (Comparavel) noAuxiliar2.getObj(); //obtém a referência para o objeto contido no nó que será comparado nessa iteração
							
				if(objComparavel2.comparacao(objComparavel1) == 1){ //compara os objetos
					this.permutaObjetosEntreNos(objComparavel1, objComparavel2); //se a comparação for válida para os critérios da ordenação os objetos são permutados de nós
				}
				
				objComparavel1 = (Comparavel) this.recuperar(contador-1); //obtém novamente a referência para o objeto contido no nó selecionado na iteração, já que se tiver ocorrido uma troca a referência não estará mais no nó selecionado
				noAuxiliar2 = noAuxiliar2.getProximo(); //obtém o próximo nó que será comparado
			}
			
			noAuxiliar1 = noAuxiliar1.getProximo(); //seleciona o próximo nó para ser comparado aos demais da lista
		}
		
		return true; //retorna true indicando que o processo ocorreu com sucesso
	}
	
	/**
	 * Retorna o index na lista encadeada de um dado objeto ou -1 caso não tenha sido possível encontrar o objeto.
	 * @param obj referência para objeto que deseja-se obter index
	 * @return index do objeto na lista encadeada; -1 se não existir o objeto especificado.
	 */
	public int obterIndex(Object obj){
		int index = 0; //index que será retornado
		No noAtual = null; //referência para o nó atual enquanto percorre a lista
		boolean encontrou = false; //indica se já foi encontrado o objeto procurado
		
		if(this.estaVazia() == true || obj == null){ //verifica se a lista está vazia
			return -1; //retorna -1 caso a condição seja satisfeita
		}
		
		
		noAtual = this.primeiro; //o nó atual recebe a referência para o primeiro nó da lista
		
		/* Nesse laço de repetição é feita uma busca em cada nó da lista encadeada pelo objeto especificado. Enquanto ele não é encontrado o valor
		 * do index é acrescido de 1.
		 */
		while(noAtual != null && encontrou == false){ //verifica se o fim da lista foi antigido e se o objeto buscado não foi encontrado antes de continuar a busc
			if(noAtual.getObj().equals(obj) == false){ //verifica se o objeto contido no nó atual possui a mesma referência que o objeto recebido por parâmetro
				index++; //incrementa um no index
			} else{
				encontrou = true; //modifica o valor booleano da variável indicando que o objeto foi encontrado
			}
			
			noAtual = noAtual.getProximo(); //faz o nó atual da iteração receber a referência do próximo nó
		}
		
		if(encontrou == true){ //verifica se o objeto buscado foi encontrado
			return index; //index do objeto buscado
		} else{
			return -1; //retorna -1 caso não tenha sido possível encontrar o objeto
		}
	}
	

	/**
	 * Retorna um iterador para percorrer a lista encadeada em sequência.
	 * 
	 * @return iterador da lista encadeada
	 */
	@Override
	public Iterador iterador() {
		return (new IteradorLista(this.primeiro)); //instancia um novo iterador passando a referência para o primeiro nó da lista e o retorna
	}

}
