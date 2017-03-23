package br.uefs.ecomp.sacMariana.util;

/**
 * Classe que implementa uma lista encadeada. Permite que opera��es de inser��o, remo��o, obten��o, ordena��o e
 * acompanhamento do estado da lista (obter o tamanho e verificar se est� vazia).
 * 
 * @author Valmir Vinicius
 */
public class ListaEncadeada implements ILista {
	
	/** Refer�ncia para o primeiro n� da lista. */
	private No primeiro;
	
	/** Tamanho da lista encadeada. */
	private int tamanhoLista;
	
	/* Na medida do poss�vel, as especifica��es em JAVADOC aqui contidas buscam focar mais no modo de implementa��o utilizando, visando 
	 * fornecer maiores detalhes para quem acessar a documenta��o do sistema e evitar repeti��es do que foi antes explicitado no JAVADOC
	 * da interface implementada por essa classe.
	 */
	
	/**
	 * Verifica se a lista encadeada est� vazia, retornando <code>true</code> apenas se n�o existir refer�ncia para o primeiro n� da lista.
	 * 
	 * @return <code>true</code>, se o primeiro n� da lista for uma refer�ncia <code>null</code>; <code>false</code>, caso contr�rio
	 */
	@Override
	public boolean estaVazia() {
		return (this.primeiro == null); //verifica se o primeiro n� da lista possui uma refer�ncia null e retorna o resultado booleano da compara��o
	}


	/**
	 * Retorna a quantidade de n�s da lista encadeada.
	 * 
	 * @return n�mero de n�s da lista encadeada
	 */
	@Override
	public int obterTamanho() {
		return this.tamanhoLista; //retorna o atributo correspondente ao tamanho da lista
	}

	/**
	 * Cria um novo n� com o objeto recebido e o define como primeiro da lista.
	 * 
	 * @param obj refer�ncia para o objeto que se deseja alocar no primeiro n� da lista
	 */
	@Override
	public void inserirInicio(Object obj) {
		No novoNo = null; //refer�ncia para o novo n� da lista
		
		if(this.estaVazia() == true){ //verifica se a lista est� vazia
			novoNo = new No(obj, null); //se a lista estiver vazia o novo n� � criado tendo uma refer�ncia null para pr�ximo n�
		}else{
			novoNo = new No(obj, this.primeiro); //caso j� existam n�s na lista o novo n� � criado tendo a refer�ncia para o primeiro n� atual como pr�ximo
		}
		
		this.primeiro = novoNo; //faz com que o atributo que especifica o primeiro n� da lista referencie o novo n�
		
		this.tamanhoLista++; //incrementa um no tamanho da lista
	}


	/**
	 * Cria um novo n� com o objeto recebido e o define como �ltimo da lista encadeada
	 * 
	 * @param obj refer�ncia para o objeto que se deseja alocar no �ltimo n� da lista
	 */
	@Override
	public void inserirFinal(Object obj) {
		No novoNo = null; //conter� a refer�ncia para o novo n� que ser� criado
		No auxiliarNo = null; //n� auxiliar para percorrer a lista
		
		novoNo = new No(obj, null); //instancia um novo n� com o objeto recebido e definindo o pr�ximo como null
			
		if(this.estaVazia() == true){ //verifica se a lista est� vazia
			this.primeiro = novoNo; //caso a lista esteja vazia o novo n� criado � definido como o primeiro
		}else{
			auxiliarNo = this.primeiro; //atribui a refer�ncia do primeiro n� � vari�vel que ser� utilizada no processo de percorrer a lista
		
			/* No la�o de repeti��o abaixo a lista � percorrida enquanto o �ltimo n� dela n�o � encontrado, de forma a obter
			 * a refer�ncia para este �ltimo n� e utiliz�-la no processso de inserir no final. */
			while(auxiliarNo.getProximo() != null){ //verifica se o n� atual cont�m uma refer�ncia n�o null para pr�ximo n�, isto �, se o n� atual n�o � o �ltimo da lista
				auxiliarNo = auxiliarNo.getProximo(); //faz com que o n� auxiliar referencie para o pr�ximo n�
			}
				
			auxiliarNo.setProximo(novoNo); //faz com que a refer�ncia de pr�ximo do n� atual seja o novo n�
		}
			
		this.tamanhoLista++; //incrementa um no tamanho da lista
	}


	/**
	 * Remove o primeiro n� da lista encadeada, se a lista n�o estiver vazia, retornando a refer�ncia para o objeto contido 
	 * no n� removido.
	 * @return refer�ncia para o objeto contido no primeiro n� da lista; <code>null</code>, caso a lista esteja vazia
	 */
	@Override
	public Object removerInicio() {
		Object objRemovido = null; //auxiliar que conter� a refer�ncia para o objeto contido no n� removido
		
		if(this.estaVazia() == true){ //verifica se a lista est� vazia
			return null; //caso a lista esteja vazia retorna uma refer�ncia null
		}
		
		objRemovido = this.primeiro.getObj(); //obt�m a refer�ncia do objeto contido no primeiro n� da lista
		
		this.primeiro = this.primeiro.getProximo(); //faz o primeiro n� apontar para o segundo n� da lista, de forma que a refer�ncia deste primeiro n� � perdida
		
		this.tamanhoLista--; //diminui em um o tamanho da lista
		
		return objRemovido; //retorna o objeto contido no n� removido
	}


	/**
	 * Remove o �ltimo n� da lista encadeada, se a lista n�o estiver vazia, retornando a refer�ncia para o objeto contido no 
	 * n� removido.
	 * @return refer�ncia para o objeto contido no �ltimo n� da lista; <code>null</code>, caso a lista esteja vazia
	 */
	@Override
	public Object removerFinal() {
		No atual = null; //refer�ncia para um n� atual afim de auxiliar no percorrimento da lista
		No anterior = null; //refer�ncia para um n� anterior para auxiliar no percorrimento da lista
		
		atual = this.primeiro; //obt�m a refer�ncia do primeiro n�
		
		if(this.estaVazia() == true){ //verifica se a lista est� vazia
			return null; //caso a lista esteja vazia retorna uma refer�ncia null
		}
		
		/* No la�o de repeti��o abaixo a lista � percorrida enquanto o �ltimo n� dela n�o � encontrado, de forma a obter
		 * a refer�ncia para este �ltimo n� e utiliz�-la no processso de remover no final da lista. */
		while(atual.getProximo() != null){ //verifica se o n� atual cont�m uma refer�ncia n�o null para pr�ximo n�, isto �, se o n� atual n�o � o �ltimo da lista
			anterior = atual; //obt�m a refer�ncia do n� anterior ao atual
			atual = atual.getProximo(); //obt�m a refer�ncia para o n� que sucede o atual na lista
		}	
		
		anterior.setProximo(null); //configura como null a refer�ncia de pr�ximo do n� anterior ao �ltimo
		
		this.tamanhoLista--; //diminui em um o tamanho da lista
		
		return atual.getObj(); //retorna o objeto contido no n� removido da lista
	}


	/**
	 * Remove um n� qualquer da lista por meio do index especificado, retornando a refer�ncia do objeto contido no 
	 * n� removido.
	 * 
	 * @param index do n� que ser� removido
	 * @return refer�ncia do objeto contido no n� removido
	 */
	@Override
	public Object remover(int index) {
		int contador = 0; //contador para auxiliar na busca do objeto cujo index foi fornecido
		No atual = null; //refer�ncia para um n� atual afim de auxiliar no percorrimento da lista
		No anterior = null; //refer�ncia para um n� anterior para auxiliar no percorrimento da lista
		
		atual = this.primeiro; //obt�m a refer�ncia do primeiro n� da lista
		
		if(this.estaVazia() == true || index > this.obterTamanho()-1 || index < 0){ //verifica se a lista est� vazia e se o index fornecido � v�lido (n�o � menor que 0 ou maior do que esperado)
			return null; //retorna null caso o index seja inv�lido ou se a lista estiver vazia
		}else if(index == 0){ //verifica se o index fornecido � 0, isto �, se o n� que ser� removido � o primeiro
			return this.removerInicio(); //chama o m�todo de remover no in�cio, retornando o objeto contido no n� removido
		}else if(index == this.obterTamanho()-1){ //verifica se o index fornecido corresponde ao �ltimo index v�lido da lista, isto �, se o n� que ser� removido � o �ltimo da lista
			return removerFinal(); //chama o m�todo de remover do final, retornando o objeto contido no n� removido
		}
		
		/* No la�o de repeti��o abaixo a lista � percorrida enquanto o valor do contador n�o se iguala ao index, isto �, 
		 * enquanto n�o foi encontrado o n� correspondente ao index fornecido. */
		while(index != contador){ //verifica se o valor do index � diferente do contador
			anterior = atual; //guarda a refer�ncia para o n� anterior
			atual = atual.getProximo(); //obt�m a refer�ncia do pr�ximo n�
			contador++; //incrementa o contador com um
		}
		
		anterior.setProximo(atual.getProximo()); //configura o atributo proximo do n� anterior ao removido com a refer�ncia do n� seguinte ao removido
		
		this.tamanhoLista--; //diminui em um o tamanho da lista
		
		return atual.getObj(); //retorna a refer�ncia para o objeto contido no n� removido
	}

	/** Recupera a refer�ncia para o objeto contido no n� cujo index foi especificado, se a lsta n�o estiver vazia e o valor de index informado for v�lido.
	 * 
	 *  @param index posi��o do n� que cont�m o objeto desejado
	 * 	@return refer�ncia para objeto contido no n� cujo index foi especificado, se o index for v�lido; <code>null</code>, caso contr�rio.
	 */
	@Override
	public Object recuperar(int index) {
		int contador = 0; //contador para auxiliar na busca do objeto cujo index foi fornecido
		No auxiliarNo = null; //n� auxiliar 
		
		if(this.estaVazia() == true || index > this.obterTamanho()-1 || index < 0){ //verifica se a lista est� vazia e se o index fornecido � v�lido (n�o � menor que 0 ou maior do que esperado)
			return null; //retorna null caso o index seja inv�lido ou se a lista estiver vazia
		}
		
		auxiliarNo = this.primeiro; //obt�m o primeiro n� da lista

		/* No la�o de repeti��o abaixo a lista � percorrida enquanto o valor do contador n�o se iguala ao index, isto �, 
		 * enquanto n�o foi encontrado o n� correspondente ao index fornecido. */
		while(index != contador){ //verifica se o valor do index � diferente do contador
			auxiliarNo = auxiliarNo.getProximo(); //obt�m o pr�ximo n�
			contador++; //incrementa um no contador
		}
		
		return auxiliarNo.getObj(); //retorna o objeto contido no n� encontrado
	}
	
	/**
	 * Recupera a refer�ncia para um n� da lista tendo como par�metro de busca o objeto contido nesse n�, se o objeto
	 * fornecido for v�lido.
	 *
	 * @param obj objeto contido no n� que se deseja obter a refer�ncia
	 * @return refer�ncia para um n�, se o objeto fornecido for v�lido; <code>null</code>, caso contr�rio.
	 */
	/* A cria��o do m�todo abaixo foi fundamental para o processo de trocar objetos entre dois n�s, um dos m�todos 
	 * necess�rios na ordena��o implementada. */
	private No recuperarNo(Object obj){
		No noAuxiliar = null; //n� para auxiliar no processo de busca
		
		if(obj == null || this.estaVazia() == true){ //verifica se a refer�ncia de objeto informada � null e se a lista est� vazia
			return null; //retorna refer�ncia null caso alguma das condi��es seja satisfeita
		}

		noAuxiliar = this.primeiro; //obt�m a refer�ncia para o primeiro n� da lista
		
		
		/* No la�o de repeti��o abaixo � feita uma busca na lista pelo n� que cont�m um objeto com a mesma refer�ncia do objeto
		 * recebido na fun��o. Se, nesse processo, o fim da lista for atingido e ainda assim o n� desejado n�o tenha sido encontrado
		 * � retornada uma refer�ncia null.
		 */
		while(obj.equals(noAuxiliar.getObj()) == false){ //enquanto n�o for encontrado um objeto na lista com a mesma refer�ncia do objeto informado o la�o de repeti��o � executado
			noAuxiliar = noAuxiliar.getProximo(); //obt�m a refer�ncia para o pr�ximo n�
			
			if(noAuxiliar == null){ //verifica se a refer�ncia do n� � null, isto �, se foi atingido o fim da lista
				return null; //retorna refer�ncia null caso a condi��o seja verdadeira
			}
		}
		
		
		return noAuxiliar; //retorna a refer�ncia para o n� desejado
	}
	
	/**
	 * Permuta os objetos especificados entre os seus respectivos n�s na lista encadeada, retornando <code>true</code>, apenas se
	 * o processo for realizado com sucesso.
	 *
	 * @param obj refer�ncia para um dos objetos que ser� permutado
	 * @param obj2 refer�ncia para um dos objetos que ser� permutado
	 * @return <code>true</code>, apenas se a lista n�o estiver vazia e se as refer�ncias de objetos informadas forem v�lidas; <code>false</code>, caso contr�rio
	 */
	/* A constru��o do m�todo abaixo foi fundamental para o processo de ordena��o */
	private boolean permutaObjetosEntreNos(Object obj, Object obj2){
		No no1 = null, no2 = null; //refer�ncia para os n�s
		Object objAuxiliar = null; //refer�ncia auxiliar de objeto para auxiliar no processo de permuta��o
		
		if(this.estaVazia() == true){ //verifica se a lista est� vazia
			return false; //retorna false indicando que n�o foi poss�vel realizar o processo
		}
		
		no1 = this.recuperarNo(obj); //recupera a refer�ncia do n� que cont�m o primeiro objeto
		no2 = this.recuperarNo(obj2); //recupera a refer�ncia do n� que cont�m o segundo objeto
		
		if(no1 == null || no2 == null){ //verifica se algum dos n�s obtidos possui refer�ncia null, isto �, se foi informado um objeto inv�lido
			return false; //retorna false indicando que n�o foi poss�vel realizar o processo
		}
		
		/* Nas linhas abaixo os objetos s�o permutados nos n�s obtidos anteriormente */
		objAuxiliar = no1.getObj(); //guarda a refer�ncia para o objeto contido no primeiro n�
		no1.setObj(no2.getObj()); //o atributo objeto do primeiro n� � configurado para referenciar o objeto contido no segundo n�
		no2.setObj(objAuxiliar); //o atributo objeto do segundo n� � configurado para referenciar o objeto que anteriormente estava no primeiro n�
		
		return true; //retorna true indicando que o processo ocorreu com sucesso
			
	}
	
	/**
	 * Ordena a lista encadeada apenas se ela for composta de objetos que implementem a interface Comparavel.
	 * 
	 * @see br.uefs.ecomp.sacMariana.util.Comparavel
	 * @return <code>true</code>, se a ordena��o ocorrer corretamente; <code>false</code>, caso contr�rio.
	 */
	public boolean ordenar(){
		Comparavel objComparavel1 = null, objComparavel2 = null; //refer�ncias do tipo Comparavel para auxiliar na ordena��o
		No noAuxiliar1 = null, noAuxiliar2 = null; //refer�ncias auxiliares para n�s da lista encadeada
		int contador=0; //armazenar� a quantidade de vezes que um n� j� foi selecionado na ordena��o
		
		if(this.estaVazia() == true){ //verifica se a lista est� vazia
			return false; //retorna false indicando que n�o foi poss�vel concluir a ordena��o
		}
			
		if(this.primeiro.getObj() instanceof Comparavel == false){ //verifica se o primeiro objeto (e consequentemente toda a lista) n�o implementa a interface Comparavel
			return false; //retorna false indicando que n�o foi poss�vel concluir a ordena��o
		}
		
		noAuxiliar1 = this.primeiro; //obt�m a refer�ncia para o primeiro n� da lista
		
		/* Nas linhas de c�digo a seguir � realizado o processo de ordena��o. No m�todo de ordena��o implementado, cada n� da lista (exceto o �ltimo) �
		 * selecionado e o seu objeto � comparado com todos os outros que est�o em n�s � sua direita na lista, de forma que, se 
		 * o objeto que est� sendo comparado ao objeto selecionado for "maior" que ele, os objetos s�o permutados entre seus n�s. Os conceitos 
		 * para especificar se um objeto � "maior" que outro foram previamente definidos durante a implementa��o da interface Comparavel nesses objetos.
		 * 
		 */
		
		while(noAuxiliar1 != null){ //verifica se a refer�ncia do n� selecionado � n�o nula
			objComparavel1 = (Comparavel) noAuxiliar1.getObj(); //obt�m a refer�ncia para o objeto contido no n� selecionado na itera��o
			noAuxiliar2 = noAuxiliar1.getProximo(); //obt�m a refer�ncia para o n� que sucede o atualmente selecionado
			contador++; //incrementa em um o contador
			
			while(noAuxiliar2 != null){ //verifica se o n� que ser� comparado possui uma refer�ncia nula, isto �, verifica se o fim da lista foi atingido
				objComparavel2 = (Comparavel) noAuxiliar2.getObj(); //obt�m a refer�ncia para o objeto contido no n� que ser� comparado nessa itera��o
							
				if(objComparavel2.comparacao(objComparavel1) == 1){ //compara os objetos
					this.permutaObjetosEntreNos(objComparavel1, objComparavel2); //se a compara��o for v�lida para os crit�rios da ordena��o os objetos s�o permutados de n�s
				}
				
				objComparavel1 = (Comparavel) this.recuperar(contador-1); //obt�m novamente a refer�ncia para o objeto contido no n� selecionado na itera��o, j� que se tiver ocorrido uma troca a refer�ncia n�o estar� mais no n� selecionado
				noAuxiliar2 = noAuxiliar2.getProximo(); //obt�m o pr�ximo n� que ser� comparado
			}
			
			noAuxiliar1 = noAuxiliar1.getProximo(); //seleciona o pr�ximo n� para ser comparado aos demais da lista
		}
		
		return true; //retorna true indicando que o processo ocorreu com sucesso
	}
	
	/**
	 * Retorna o index na lista encadeada de um dado objeto ou -1 caso n�o tenha sido poss�vel encontrar o objeto.
	 * @param obj refer�ncia para objeto que deseja-se obter index
	 * @return index do objeto na lista encadeada; -1 se n�o existir o objeto especificado.
	 */
	public int obterIndex(Object obj){
		int index = 0; //index que ser� retornado
		No noAtual = null; //refer�ncia para o n� atual enquanto percorre a lista
		boolean encontrou = false; //indica se j� foi encontrado o objeto procurado
		
		if(this.estaVazia() == true || obj == null){ //verifica se a lista est� vazia
			return -1; //retorna -1 caso a condi��o seja satisfeita
		}
		
		
		noAtual = this.primeiro; //o n� atual recebe a refer�ncia para o primeiro n� da lista
		
		/* Nesse la�o de repeti��o � feita uma busca em cada n� da lista encadeada pelo objeto especificado. Enquanto ele n�o � encontrado o valor
		 * do index � acrescido de 1.
		 */
		while(noAtual != null && encontrou == false){ //verifica se o fim da lista foi antigido e se o objeto buscado n�o foi encontrado antes de continuar a busc
			if(noAtual.getObj().equals(obj) == false){ //verifica se o objeto contido no n� atual possui a mesma refer�ncia que o objeto recebido por par�metro
				index++; //incrementa um no index
			} else{
				encontrou = true; //modifica o valor booleano da vari�vel indicando que o objeto foi encontrado
			}
			
			noAtual = noAtual.getProximo(); //faz o n� atual da itera��o receber a refer�ncia do pr�ximo n�
		}
		
		if(encontrou == true){ //verifica se o objeto buscado foi encontrado
			return index; //index do objeto buscado
		} else{
			return -1; //retorna -1 caso n�o tenha sido poss�vel encontrar o objeto
		}
	}
	

	/**
	 * Retorna um iterador para percorrer a lista encadeada em sequ�ncia.
	 * 
	 * @return iterador da lista encadeada
	 */
	@Override
	public Iterador iterador() {
		return (new IteradorLista(this.primeiro)); //instancia um novo iterador passando a refer�ncia para o primeiro n� da lista e o retorna
	}

}
