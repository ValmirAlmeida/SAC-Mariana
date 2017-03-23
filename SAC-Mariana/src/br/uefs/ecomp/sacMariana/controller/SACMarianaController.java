package br.uefs.ecomp.sacMariana.controller;
import java.util.Date;
import br.uefs.ecomp.sacMariana.model.Produto;
import br.uefs.ecomp.sacMariana.model.Doador;
import br.uefs.ecomp.sacMariana.util.Iterador;
import br.uefs.ecomp.sacMariana.util.ListaEncadeada;
import br.uefs.ecomp.sacMariana.model.Doacao;

/**
 * Classe que faz parte do Controller, respons�vel pela intermedia��o da troca de informa��es entre a View e o Model. 
 * 
 * @author Valmir Vinicius
 * @see br.uefs.ecomp.sacMariana.util.ListaEncadeada
 * @see br.uefs.ecomp.sacMariana.util.Iterador
 * @see br.uefs.ecomp.sacMariana.model.Produto
 * @see br.uefs.ecomp.sacMariana.model.Doador
 * @see br.uefs.ecomp.sacMariana.model.Doacao
 */
public class SACMarianaController {
	
	/** Lista encadeada de produtos */
	private ListaEncadeada listaProdutos;
	
	/** Lista encadeada de doadores */
	private ListaEncadeada listaDoadores;
	
	/** Lista encadeada de produtos */
	private ListaEncadeada listaDoacoes;

	/**
	 * Instancia as listas encadeadas utilizadas para armazenamento de dados.
	 */
	public SACMarianaController(){
		this.listaProdutos = new ListaEncadeada();
		this.listaDoadores = new ListaEncadeada();;
		this.listaDoacoes = new ListaEncadeada();
	
	}

	/**
	/* Cadastra um novo produto no sistema. 
	 * @param nomeProduto nome do produto cadastrado
	 * @param tipoProduto tipo do produto cadastrado
	 * @param dataCadastroProduto data de cadastro do produto
	 * @return ref�rncia para o produto cadastrado
	 */
	public Produto cadastrarProduto(String nomeProduto, String tipoProduto, Date dataCadastroProduto) {
		Produto novoProduto = new Produto(nomeProduto, tipoProduto, dataCadastroProduto); //instancia um novo objeto do tipo produto
		 
		listaProdutos.inserirFinal(novoProduto); //insere o objeto produto instanciado na lista encadeada espec�fica
		
        return novoProduto; //retorna a refer�ncia para o objeto produto cadastrado
    }
	
	/**
	 * Altera o cadastro de um produto no sistema, caso o ID informado seja v�lido.
	 * @param idProduto identifica��o �nica do produto no sistema
	 * @param nomeProduto nome que estar� no produto ap�s a altera��o
	 * @param tipoProduto tipo que estar� no produto ap�s a altera��p
	 * @param dataCadastroProduto data de cadastro que estar� no produto ap�s a altera��o
	 * @return refer�ncia para o produto editado, se o ID informado for v�lido; <code>null</code>, caso contr�rio.
	 */
	public Produto editarProduto(Integer idProduto, String nomeProduto, String tipoProduto, Date dataCadastroProduto) {
		Produto auxiliarProduto = null; //auxiliar com refer�ncia para o produto editado
		int indexProduto = 0; //index para localiza��o do produto que ser� editado
		
		indexProduto = this.listaProdutos.obterIndex(this.obterProduto(idProduto)); //obt�m o index do produto
		
		if(indexProduto == -1){ //verifica se o index obtido foi -1, isto �, se n�o existe um produto com ID informado no sistema
			return null; //retorna uma refer�ncia nula caso a condi��o seja satisfeita
		}else{
			auxiliarProduto = (Produto) this.listaProdutos.recuperar(indexProduto); //recupera a refer�ncia para o objeto que se deseja editar e atribui � vari�vel auxiliar
			
			/* Impede que o tipo do produto seja alterado de monet�rio para n�o monet�rio e vice e versa. Essa medida previne
			 * poss�veis erros futuros relacionados ao c�lculo do percentual de doa��es.
			 */
			if(auxiliarProduto.getTipoProduto().equals("Monetario") == true && tipoProduto.equals("Monetario") == false){
				return null;
			} else if(auxiliarProduto.getTipoProduto().equals("Monetario") == false && tipoProduto.equals("Monetario") == true){
				return null;
			}
			
			/* Atribui as novas informa��es ao produto que se deseja editar. */
			auxiliarProduto.setNomeProduto(nomeProduto);
			auxiliarProduto.setTipoProduto(tipoProduto);
			auxiliarProduto.setDataCadastroProduto(dataCadastroProduto);
			
			return auxiliarProduto; //retorna a refer�ncia para o produto editado
		}
	}

	/**
	 * Proporciona a listagem de todos os produtos cadastrados no sistema. 
	 * @return iterador que possibilitar� acesso a cada produto cadastrado
	 */
	public Iterador listarProdutos() {
		return this.listaProdutos.iterador(); //retorna o iterador para a lista encadeada de produtos
    }
	
	/**
	 * Remove um produto do sistema por meio do seu ID. O resultado � <code>true</code> apenas se foi poss�vel remover
	 * o cadastro do produto.
	 * @param idProduto identifica��o �nica do produto no sistema
	 * @return <code>true</code>, se o produto foi removido com sucesso; <code>false</code>, caso contr�rio.
	 */
	public boolean removerProduto(Integer idProduto) {
		int indexProduto = 0; //index do produto que ser� removido

		indexProduto = this.listaProdutos.obterIndex(this.obterProduto(idProduto)); //obt�m index do produto
		
		if(indexProduto == -1){ //verifica se o index obtido foi -1, isto �, se n�o foi poss�vel encontrar o index do produto
			return false; //se a condi��o for verdadeira retorna um booleano false, indicando que n�o foi poss�vel efetuar a remo��o 
		}else{
			return !(this.listaProdutos.remover(indexProduto) == null); //verifica se foi poss�vel remover o produto, retornando booleano true se a opera��o foi realizada com sucesso ou false se houve erro 
		}
	}
	

	
	/**
	 * Retorna a refer�ncia para o cadastro de um produto, caso o ID recebido seja v�lido.
	 * @param idProduto ID do produto que se deseja obter a refer�ncia
	 * @return refer�ncia para produto, caso o ID informado seja v�lido; <code>null</code>, caso seja informado um ID inv�lido.
	 */
	public Produto obterProduto(Integer idProduto){
		Iterador iteradorProdutos = null; //iterador para lista de produtos
		Produto auxiliarProduto = null; //auxiliar com refer�ncia para produto
		
		iteradorProdutos = this.listaProdutos.iterador(); //obt�m um iterador da lista de produtos

		/* No la�o de repeti��o abaixo a lista de produtos � percorrida e quando � encontrado um produto que possua
		 * o ID informado a refer�ncia para esse produto � retornada.
		 */
		while(iteradorProdutos.temProximo() == true){ //verifica se h� mais elementos na lista, isto �, se o fim n�o foi atingido
			auxiliarProduto = (Produto) iteradorProdutos.obterProximo(); //obt�m a refer�ncia para o pr�ximo produto
			
			if(auxiliarProduto.getIdProduto().equals(idProduto) == true){ //verifica se o ID informado � igual ao ID do produto selecionado
				return auxiliarProduto; //retorna a refer�ncia do produto
			}
		}
		
		return null; //retorna null indicando que n�o foi poss�vel encontrar o produto
	}
	
	/**
	 * Cadastra um doador, caso O CPF/CNPJ informado ainda n�o esteja associado a nenhum cadastro anterior.
	 * @param numCadastro CPF ou CNPJ do doador que se deseja cadastrar
	 * @param nomeDoador nome do doador que se deseja cadastrar
	 * @param dataNascDoador data de nascimento (pessoa f�sica) ou funda��o (pessoa jur�dica) do doador que se deseja cadastrar
	 * @param rua rua do doador que se deseja cadastrar
	 * @param numero n�mero do endere�o do doador que se deseja cadastrar
	 * @param bairro bairro do doador que se deseja cadastrar
	 * @param cep cep do doador que se deseja cadastrar
	 * @param cidade cidade do doador que se deseja cadastrar
	 * @param estado estado do doador que se deseja cadastrar
	 * @param pais pa�s do doador que se deseja cadastrar
	 * @param tipoDoador tipo de doador a ser cadastrado(pessoa f�sica ou jur�dica)
	 * @return refer�ncia para o novo doador, caso seja poss�vel realizar o cadastro; <code>null</code>, caso j� exista cadastro com o CPF/CNPJ informado.
	 */
	public Doador cadastrarDoador(String numCadastro, String nomeDoador, Date dataNascDoador, String rua, int numero, String bairro, String cep, String cidade, String estado, String pais, String tipoDoador) {
		Doador novoDoador = null; //vari�vel auxiliar que conter� refer�ncia para o novo cadastro de doador
		
		if(this.listaDoadores.obterIndex(this.obterDoador(numCadastro))!= -1){ //verifica se � poss�vel obter um index v�lido para o doador na lista encadeada de doadores, isto �, se j� existe um cadastro do doador cujo CPF/CNPJ foi informado
			return null; //retorna refer�ncia null caso a condi��o seja satisfeita
		}else{
			novoDoador = new Doador(numCadastro, nomeDoador, dataNascDoador, rua, numero, bairro, cep, cidade, estado, pais, tipoDoador); //instancia um novo objeto do tipo doador
			
			listaDoadores.inserirFinal(novoDoador); //insere o novo objeto doador na lista encadeada espec�fica
			
			return novoDoador; //retorna a refer�ncia para o objeto doador cadastrado
		}
		
    }
	
	/**
	 * Altera o cadastro j� existente de um doador, caso o CPF/CNPJ recebido seja v�lido. 
	 * @param numCadastro CPF ou CNPJ do doador que ter� seu cadastro alterado
	 * @param nomeDoador nome que estar� no cadastro do doador ap�s a altera��o
	 * @param dataNasc data de nascimento/funda��o que estar� no cadastro do doador ap�s a altera��o
	 * @param rua rua do endere�o que estar� no cadastro do doador ap�s a altera��o
	 * @param numero n�mero do endere�o que estar� no cadastro do doador ap�s a altera��o
	 * @param bairro bairro do endere�o que estar� no cadastro do doador ap�s a altera��o
	 * @param cep cep do endere�o que estar� no cadastro do doador ap�s a altera��o
	 * @param cidade cidade do endere�o que estar� no cadastro do doador ap�s a altera��o
	 * @param estado estado do endere�o que estar� no cadastro do doador ap�s a altera��o
	 * @param pais pa�s do endere�o que estar� no cadastro do doador ap�s a altera��o
	 * @param tipoDoador tipo de doador (pessoa f�sica ou jur�dica) que estar� no cadastro ap�s a altera��o
	 * @return refer�ncia para o doador editado, caso o CPF/CNPJ informado seja v�lido; <code>null</code>, caso contr�rio.
	 */
	public Doador editarDoador(String numCadastro, String nomeDoador, Date dataNasc, String rua, int numero, String bairro, String cep, String cidade, String estado, String pais, String tipoDoador) {
		Doador auxiliarDoador = null; //refer�ncia auxiliar para acessar o cadastro do doador que ser� alterado
		int indexDoador = 0; //index para localiza��o do doador que ter� o cadastro alterado

		indexDoador = this.listaDoadores.obterIndex(this.obterDoador(numCadastro)); //obt�m index do doador
		
		if(indexDoador == -1){ //verifica se o index � inv�lido, isto �, se n�o existe um doador com o CPF/CNPJ informado
			return null; //retorna refer�ncia nula caso a condi��o seja satisfeita
		}else{
			auxiliarDoador = (Doador) listaDoadores.recuperar(indexDoador); //obt�m a refer�ncia para o cadastro do doador
			
			/* Altera os atributos a serem modificados no cadastro do doador com as informa��es recebidas por par�metro. */
			auxiliarDoador.setNomeDoador(nomeDoador); 
			auxiliarDoador.setDataNasc(dataNasc); 
			auxiliarDoador.setEndereco(rua, numero, bairro, cep, cidade, estado, pais); 
			auxiliarDoador.setTipoDoador(tipoDoador); 
			
			return auxiliarDoador; //retorna a refer�ncia para o cadastro de doador editado
		}
		
    }
	
	/**
	 * Retorna a refer�ncia para o cadastro de um doador, caso o CPF/CNPJ recebido seja v�lido.
	 * @param numCadastro CPF ou CNPJ do doador que se deseja obter
	 * @return refer�ncia para o objeto doador desejado, se o CPF/CNPJ informado for v�lido; <code>null</code>, caso contr�rio.
	 */
	public Doador obterDoador(String numCadastro) {
		Iterador iteradorDoadores = null; //iterador para lista de doadores
		Doador auxiliarDoador = null; //auxiliar com refer�ncia para doador
		
		iteradorDoadores = this.listaDoadores.iterador(); //obt�m um iterador da lista de doadores
		
		/* No la�o de repeti��o abaixo a lista de doadores � percorrida e quando � encontrado um doador que possua
		 * o CPF/CNPJ informado a refer�ncia para esse produto � retornada.
		 */
		while(iteradorDoadores.temProximo() == true){ //verifica se h� mais elementos na lista, isto �, se o fim n�o foi atingido
			auxiliarDoador = (Doador) iteradorDoadores.obterProximo(); //obt�m a refer�ncia para o pr�ximo doador
			
			if(auxiliarDoador.getNumCadastroDoador().equals(numCadastro) == true){ //verifica se o CPF/CNPJ informado � igual ao CPF/CNPJ do doador selecionado
				return auxiliarDoador; //retorna a refer�ncia do doador
			}
		}
		
		return null;  //retorna null indicando que n�o foi poss�vel encontrar o doador
	}
	
	/**
	 * Remove um doador por meio de um CPF ou CNPJ informado, retornando <code>true</code> apenas se for poss�vel remover
	 * o cadastro com sucesso.
	 * @param numCadastro CPF ou CNPJ do doador que se deseja remover cadastro
	 * @return <code>true</code>, caso o CPF/CNPJ informado seja v�lido e o cadastro tenha sido removido com sucesso; <code>false</code>, caso contr�rio.
	 */
	public boolean removerDoador(String numCadastro) {
		Doador doadorRemocao; //refer�ncia auxiliar para o objeto do tipo doador que ser� removido do sistema
		int indexDoador; //index do doador que se deseja remover
		
		indexDoador = this.listaDoadores.obterIndex(this.obterDoador(numCadastro)); //obt�m o index do doador
		
		if(indexDoador == -1){ //verifica se o index � v�lido, isto �, se existe um doador com o CPF/CNPJ informado
			return false; //retorna um booleano false indicando que n�o foi poss�vel efetuar a remo��o
		}else{
			
			doadorRemocao = this.obterDoador(numCadastro); //obt�m refer�ncia para o objeto doador que ser� removido
			
			if(doadorRemocao.getTotalDoadoMonetario() != 0 || doadorRemocao.getTotalDoadoNaoMonetario() != 0){ //verifica se o doador em quest�o j� realizou alguma doa��o monet�ria ou n�o monet�ria
				return false; //retorna um booleano false indicando que n�o foi poss�vel efetuar a remo��o
			}
			
			return !(this.listaDoadores.remover(indexDoador) == null); //remove o doador da lista e retorna um booleano indicando se a opera��o foi realizada com sucesso
		}
		
    }	
	
	/**
	 * Proporciona a listagem de todos os doadores cadastrados no sistema.
	 * @return iterador que possibilitar� acesso a cada doador cadastrado
	 */
	public Iterador listarDoadores() {		
		return this.listaDoadores.iterador(); //obt�m e retorna um iterador para a lista encadeada de doadores
    }
	
	
	/**
	 * Realiza uma nova doa��o. Retorna a refer�ncia para a doa��o realizada, apenas se a doa��o ocorrer corretamente.
	 * @param doador refer�ncia para o doador que realizar� a doa��o
	 * @param produto refer�ncia para o produto que ser� doado
	 * @param quantidadeDoada quantidade doada
	 * @param dataDoacao data de realiza��o da doa��o
	 * @return refer�ncia para a doa��o realizada, caso os dados recebidos sejam v�lidos; <code>null</code>, caso contr�rio.
	 */
	public Doacao efetuarDoacao(Doador doador, Produto produto, Number quantidadeDoada, Date dataDoacao){
		Doacao novaDoacao = null; //refer�ncia para a doa��o que ser� cadastrada no sistema
		boolean doacaoMonetaria; //indica se a doa��o � monet�ria
		
		if(quantidadeDoada.floatValue() < 0){ //verifica se a quantidade doada � um valor menor que 0, isto �, valor inv�lido
			return null; //retorna refer�ncia nula
		}
		
		if(produto.getTipoProduto().equals("Monetario") == true){ //verifica se o produto doado � monet�rio
			doacaoMonetaria = true; //altera o estado da vari�vel booleana indicando que trata-se de uma doa��o monet�ria
			doador.atualizaTotalDoadoMonetario(quantidadeDoada.floatValue(), true); //atualiza o valor doado no total de doa��es monet�rias realizadas pelo doador
			Doacao.atualizaTotalArrecadadoMonetario(quantidadeDoada.floatValue(), true); //atualiza o valor doado no total de doa��es monet�rias do sistema
		} else{
			doacaoMonetaria = false; //altera o estado da vari�vel booleana indicando que trata-se de uma doa��o n�o monet�ria
			doador.atualizaTotalDoadoNaoMonetario(quantidadeDoada.intValue(), true); //atualiza a quantidade doada no total de doa��es monet�rias realizadas pelo doador
			Doacao.atualizaTotalArrecadadoNaoMonetario(quantidadeDoada.intValue(), true); //atualiza a quantidade doada no total de doa��es do sistema
		}
		
		novaDoacao = new Doacao(dataDoacao, produto, doador, quantidadeDoada, doacaoMonetaria); //instancia um novo objeto do tipo doa��o
				
		this.listaDoacoes.inserirFinal(novaDoacao); //insere o objeto do tipo doa��o na lista encadeada
		
		return novaDoacao; //retorna a refer�ncia para o objeto doacao cadastrado
	}
	
	/**
	 * Altera o cadastro de uma doa��o no sistema, caso a identifica��o �nica (ID da doa��o) informada seja v�lida. Retorna
	 * a refer�ncia para a doa��o alterada.
	 * @param idDoacao identificador �nico da doa��o que se deseja alterar no sistema
	 * @param doador refer�ncia para o doador contido no cadastro da doa��o ap�s a altera��o
	 * @param produto refer�ncia para o produto contido no cadastro da doa��o ap�s a altera��o
	 * @param quantidadeDoada quantidade doada contida no cadastro da doa��o ap�s a altera��o
	 * @param dataDoacao data da doa��o contida ap�s a altera��o
	 * @return refer�ncia para a doa��o alterada, caso o ID informado seja v�lido; <code>null</code>, caso contr�rio.
	 */
	public Doacao alterarDoacao(Integer idDoacao, Doador doador, Produto produto, Number quantidadeDoada, Date dataDoacao){
		Doacao auxiliarDoacao = null; //refer�ncia auxiliar para a doa��o que ser� alterada
		int indexDoacao = 0; //index para localiza��o da doa��o que ser� alterada
		
		indexDoacao = this.listaDoacoes.obterIndex(this.obterDoacao(idDoacao)); //obt�m o index da doa��o a ser alterada
		
		if(indexDoacao == -1){ //verifica se o index � inv�lido, isto �, se n�o h� cadastro de doa��o com o ID informado
			return null; //retorna refer�ncia nula caso a condi��o seja satisfeita
		}else{
			auxiliarDoacao = (Doacao) this.listaDoacoes.recuperar(indexDoacao); //obt�m a refer�ncia para a doa��o que ser� editada
			
			/* As condi��es abaixo impedem a troca do produto de uma doa��o cujo produto � monet�rio para um produto n�o monet�rio e vice e versa,
			 * isso evita poss�veis erros, pois os valores monet�rios s�o representados em float e os n�o monet�rios em int.
			 */
			if(auxiliarDoacao.getProduto().getTipoProduto().equals("Monetario") == true && produto.getTipoProduto().equals("Monetario") == false){ 
				return null;
			}else if(auxiliarDoacao.getProduto().getTipoProduto().equals("Monetario") == false && produto.getTipoProduto().equals("Monetario") == true){
				return null;
			}
			
			if(auxiliarDoacao.isMonetaria() == true){ //verifica se a doa��o � monet�ria
				auxiliarDoacao.getDoador().atualizaTotalDoadoMonetario(auxiliarDoacao.getQuantidadeDoada().floatValue(), false); //remove o valor anteriormente doado do total de doa��es monet�rias do doador
				auxiliarDoacao.getDoador().atualizaTotalDoadoMonetario(quantidadeDoada.floatValue(), true); //adiciona o novo valor ao total de doa��es monet�rias do doador
				Doacao.atualizaTotalArrecadadoMonetario(auxiliarDoacao.getQuantidadeDoada().floatValue(), false); //remove o valor anteriormente doado do total de doa��es monet�rias do sistema
				Doacao.atualizaTotalArrecadadoMonetario(quantidadeDoada.floatValue(), true); //adiciona o novo valor doado ao total de doa��es monet�rias dos sistema
			} else{
				auxiliarDoacao.getDoador().atualizaTotalDoadoNaoMonetario(auxiliarDoacao.getQuantidadeDoada().intValue(), false); //remove a quantidade anteriormente doada do total de doa��es n�o monet�rias do doador
				auxiliarDoacao.getDoador().atualizaTotalDoadoNaoMonetario(quantidadeDoada.intValue(), true); //adiciona a nova quantidade ao total de doa��es n�o monet�rias do doador
				Doacao.atualizaTotalArrecadadoNaoMonetario(auxiliarDoacao.getQuantidadeDoada().intValue(), false); //remove a quantidade anteriormente doado do total de doa��es n�o monet�rias do sistema
				Doacao.atualizaTotalArrecadadoNaoMonetario(quantidadeDoada.intValue(), true); //adiciona a nova quantidade doada ao total de doa��es n�o monet�rias dos sistema
			}
			
			/* Atualiza os atributos da doa��o com os novos dados. */
			auxiliarDoacao.setQuantidadeDoada(quantidadeDoada);
			auxiliarDoacao.setDoador(doador);
			auxiliarDoacao.setProduto(produto);
			auxiliarDoacao.setDataDoacao(dataDoacao);
			auxiliarDoacao.setMonetaria(produto.getTipoProduto().equals("Monetario")? true:false);
		
			return auxiliarDoacao; //retorna refer�ncia para a doa��o alterada
		}		

	}

	
	/**
	 * Remove uma doa��o atrav�s da sua identifica��o �nica (ID do produto) no sistema. Retorna <code>true</code> apenas se for
	 * poss�vel remover a doa��o.
	 * @param idDoacao identifica��o �nica (ID) da doa��o no sistema
	 * @return <code>true</code>, se o ID informado for v�lido; <code>false</code>, caso contr�rio.
	 */
	public boolean removerDoacao(int idDoacao){
		Doacao doacaoRemovida = null; //refer�ncia para a doa��o que ser� removida
		int indexDoacao = 0; //index da doa��o que ser� removida
		float qtdMonetariaRemovida;
		int qtdNaoMonetariaRemovida;
		
		indexDoacao = this.listaDoacoes.obterIndex(this.obterDoacao(idDoacao)); //obt�m o index da doa��o a ser removida
		
		if(indexDoacao == -1){ //verifica se o index � inv�lido, isto �, se n�o existe doa��o com o ID informado
			return false; //caso a condi��o seja verdadeira retorna booleano false indicando que n�o foi poss�vel remover a doa��o
		}else{
			doacaoRemovida = (Doacao) this.listaDoacoes.recuperar(indexDoacao); 
			
			if(doacaoRemovida.isMonetaria() == true){ //verifica se a doa��o removida � do tipo monet�ria
				qtdMonetariaRemovida = doacaoRemovida.getQuantidadeDoada().floatValue();
				Doacao.atualizaTotalArrecadadoMonetario(qtdMonetariaRemovida, false); //remove o valor da doa��o do total de doa��es monet�rias do sistema
				doacaoRemovida.getDoador().atualizaTotalDoadoMonetario(qtdMonetariaRemovida, false); //remove o valor da doa��o do total monet�rio doado pelo doador
			} else{
				qtdNaoMonetariaRemovida = doacaoRemovida.getQuantidadeDoada().intValue();
				Doacao.atualizaTotalArrecadadoNaoMonetario(qtdNaoMonetariaRemovida, false); //remove a quantidade da doa��o do total de doa��es n�o monet�rias do sistema
				doacaoRemovida.getDoador().atualizaTotalDoadoNaoMonetario(qtdNaoMonetariaRemovida, false); //remove a quantidade da doa��o do total n�o monet�rio doado pelo doador
			}
						
			return !(this.listaDoacoes.remover(indexDoacao) == null); //retorna booleano true indicando que a remo��o ocorreu com sucesso
		}
	}

	/**
	 * Retorna a refer�ncia para o cadastro de uma doa��o, caso a identifica��o �nica (ID da doa��o) informada seja v�lida.
	 * @param idDoacao identifica��o �nica (ID da doa��o) que se deseja obter refer�ncia
	 * @return refer�ncia para uma doa��o, caso o ID informado seja v�lido; <code>null</code>, caso o ID seja inv�lido.
	 */
	public Doacao obterDoacao(Integer idDoacao){
		Iterador iteradorDoacoes = null; //iterador para lista de doacoes
		Doacao auxiliarDoacao = null; //refer�ncia auxiliar para doacao
		
		iteradorDoacoes = this.listaDoacoes.iterador(); //obt�m o iterador da lista de doa��es
		
		/* No la�o de repeti��o abaixo a lista de doa��es � percorrida e quando � encontrada doa��o que possua
		 * o ID informado a refer�ncia para essa doa��o � retornada.
		 */
		
		while(iteradorDoacoes.temProximo() == true){ //verifica se h� mais elementos na lista, isto �, se o final n�o foi atingido
			auxiliarDoacao = (Doacao) iteradorDoacoes.obterProximo(); //obt�m a refer�ncia para a pr�xima doa��o da lista
			
			if(auxiliarDoacao.getIdDoacao().equals(idDoacao) == true){ //verifica se o ID contido no objeto obtido � igual ao id que foi informado
				return auxiliarDoacao; //retorna a refer�ncia do objeto encontrado
			}
		}
		
		return null; //retorna null se n�o foi poss�vel encontrar um objeto
		
	}

	/**
	 * Proporciona a listagem de todas as doa��es do sistema ordenadas, assim como a listagem de todas as doa��es (monet�rias ou
	 * n�o monet�rias) de um doador espec�fico. 
	 * @param monetario especifica se as doa��es a serem listadas ser�o ou n�o as monet�rias, no caso das doa��es de um doador
	 * @param doador doador que ter� as doa��es listadas; um valor null deve ser informado quando se deseja listar todas as doa��es do sistema
	 * @return iterador que possibilitar� acesso a cada doa��o; <code>null</code>, caso n�o seja poss�vel ordenar as doa��es.
	 */
	public Iterador listarDoacoesOrdenadas(boolean monetario, Doador doador){
		Iterador iteradorDoacoes = null; //iterador para lista encadeada de doa��es
		ListaEncadeada listaAuxiliar = new ListaEncadeada(); //lista que conter� todas as doa��es (monet�rias ou n�o monet�rias) de um doador
		Doacao auxiliarDoacao = null; //auxiliar que vai referenciar cada cadastro de doa��o da lista
		Doador auxiliarDoador = null; //auxiliar que vai referenciar cada cadastro de doador da lista
		
		iteradorDoacoes = this.listaDoacoes.iterador(); //obt�m o iterador
		
		if(doador == null){ //verifica se a refer�ncia de doador informada � null, isto �, se ser� feita uma listagem de todas as doa��es do sistema
			
			iteradorDoacoes = this.listaDoacoes.iterador(); //obt�m um iterador para lista de doa��es
			
			while(iteradorDoacoes.temProximo() == true){ //verifica se h� mais elementos na lista, isto �, se o final n�o foi atingido
				auxiliarDoacao = (Doacao) iteradorDoacoes.obterProximo(); //obt�m a refer�ncia da pr�xima doacao
				
				if(auxiliarDoacao.isMonetaria() == monetario){ //verifica se o tipo da doa��o corresponde ao tipo que se deseja listar
					listaAuxiliar.inserirFinal(auxiliarDoacao); //insere a doa��o na nova lista
				}
			}
			
			if(listaAuxiliar.ordenar() == false){ //verifica se � poss�vel ordenar a lista gerada
				return null; //retorna null caso ocorra algum erro durante a ordena��o
			}
			
			
			return listaAuxiliar.iterador(); //retorna iterador para a lista de doa��es j� ordenada
		} else{
			listaAuxiliar = new ListaEncadeada(); //instancia uma nova lista encadeada
			iteradorDoacoes = this.listaDoacoes.iterador(); //obt�m um novo iterador para a lista encadeada de doa��es

			while(iteradorDoacoes.temProximo() == true){ //verifica se ainda existem n�s na lista encadeada
				auxiliarDoacao = (Doacao) iteradorDoacoes.obterProximo(); //obt�m a refer�ncia para o cadastro de uma doa��o
				auxiliarDoador = auxiliarDoacao.getDoador(); //obt�m o doador da doa��o obtida acima
				
				if(auxiliarDoador.getNumCadastroDoador().equals(doador.getNumCadastroDoador()) && auxiliarDoacao.isMonetaria() == monetario){ //verifica se o CPF/CNPJ do doador que as doa��es ser�o listadas � igual ao do doador selecionado da lista e se o tipo de doa��o (monet�ria ou n�o monet�ria) corresponde ao tipo que se deseja listar 
					listaAuxiliar.inserirFinal(auxiliarDoacao); //insere o cadastro da doa��o na lista encadeada de doa��es do doador
				}
			}
			
			if(listaAuxiliar.ordenar() == false){ //verifica se houve erro durante a ordena��o da lista
				return null; //retorna refer�ncia null caso n�o tenha sido poss�vel ordenar
			}
			
			return listaAuxiliar.iterador(); //retorna iterador para a lista de doa��es de um doador j� ordenada
		}
	}
	
	/**
	 * Calcula o percentual de doa��es monet�rias e n�o monet�rias de cada doador. O percentual de cada doador
	 * � preenchido com 0, caso n�o tenham sido realizadas doa��es.
	 */
	public void calcularPercentualDoadores(){
		Iterador iteradorDoadores = null; //iterador para percorrer a lista encadeada de doadores
		Doador doadorAuxiliar = null; //auxiliar que vai referenciar cada cadastro de doador da lista
		float percentualMonetario = 0, percentualNaoMonetario = 0; //percentuais que ser�o calculados e atribuidos aos doadores 
		
		iteradorDoadores = this.listaDoadores.iterador(); //obt�m o iterador para a lista de doadores
		
		while(iteradorDoadores.temProximo() == true){ //verifica se ainda existem n�s na lista encadeada
			doadorAuxiliar = (Doador) iteradorDoadores.obterProximo(); //obt�m refer�ncia para um doador
		
			if(Doacao.getTotalArrecadadoMonetario() == 0){ //verifica se o total arrecado � 0
				percentualMonetario = 0; //define o percentual como 0 para evitar erros com divis�o por 0
			}else{
				percentualMonetario = (doadorAuxiliar.getTotalDoadoMonetario()/Doacao.getTotalArrecadadoMonetario())*100; //calcula o percentual de doa��es monet�rias
			}
			
			if(Doacao.getTotalArrecadadoNaoMonetario() == 0){ //verifica se o total arrecado � 0
				percentualNaoMonetario = 0; //define o percentual como 0 para evitar erros com divis�o por 0
			} else{
				/* Calcula o percentual de doa��es n�o monet�rias. */
				percentualNaoMonetario = ((float) doadorAuxiliar.getTotalDoadoNaoMonetario())/(Doacao.getTotalArrecadadoNaoMonetario());
				percentualNaoMonetario *= 100;
			}
			
			/* Adiciona os valores calculados nos respectivos atributos do doador. */
			doadorAuxiliar.setPercentualDoacoesMonetarias(percentualMonetario);
			doadorAuxiliar.setPercentualDoacoesNaoMonetarias(percentualNaoMonetario);
		}
	}
	
}