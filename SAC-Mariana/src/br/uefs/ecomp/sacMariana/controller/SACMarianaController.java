package br.uefs.ecomp.sacMariana.controller;
import java.util.Date;
import br.uefs.ecomp.sacMariana.model.Produto;
import br.uefs.ecomp.sacMariana.model.Doador;
import br.uefs.ecomp.sacMariana.util.Iterador;
import br.uefs.ecomp.sacMariana.util.ListaEncadeada;
import br.uefs.ecomp.sacMariana.model.Doacao;

/**
 * Classe que faz parte do Controller, responsável pela intermediação da troca de informações entre a View e o Model. 
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
	 * @return refêrncia para o produto cadastrado
	 */
	public Produto cadastrarProduto(String nomeProduto, String tipoProduto, Date dataCadastroProduto) {
		Produto novoProduto = new Produto(nomeProduto, tipoProduto, dataCadastroProduto); //instancia um novo objeto do tipo produto
		 
		listaProdutos.inserirFinal(novoProduto); //insere o objeto produto instanciado na lista encadeada específica
		
        return novoProduto; //retorna a referência para o objeto produto cadastrado
    }
	
	/**
	 * Altera o cadastro de um produto no sistema, caso o ID informado seja válido.
	 * @param idProduto identificação única do produto no sistema
	 * @param nomeProduto nome que estará no produto após a alteração
	 * @param tipoProduto tipo que estará no produto após a alteraçãp
	 * @param dataCadastroProduto data de cadastro que estará no produto após a alteração
	 * @return referência para o produto editado, se o ID informado for válido; <code>null</code>, caso contrário.
	 */
	public Produto editarProduto(Integer idProduto, String nomeProduto, String tipoProduto, Date dataCadastroProduto) {
		Produto auxiliarProduto = null; //auxiliar com referência para o produto editado
		int indexProduto = 0; //index para localização do produto que será editado
		
		indexProduto = this.listaProdutos.obterIndex(this.obterProduto(idProduto)); //obtém o index do produto
		
		if(indexProduto == -1){ //verifica se o index obtido foi -1, isto é, se não existe um produto com ID informado no sistema
			return null; //retorna uma referência nula caso a condição seja satisfeita
		}else{
			auxiliarProduto = (Produto) this.listaProdutos.recuperar(indexProduto); //recupera a referência para o objeto que se deseja editar e atribui à variável auxiliar
			
			/* Impede que o tipo do produto seja alterado de monetário para não monetário e vice e versa. Essa medida previne
			 * possíveis erros futuros relacionados ao cálculo do percentual de doações.
			 */
			if(auxiliarProduto.getTipoProduto().equals("Monetario") == true && tipoProduto.equals("Monetario") == false){
				return null;
			} else if(auxiliarProduto.getTipoProduto().equals("Monetario") == false && tipoProduto.equals("Monetario") == true){
				return null;
			}
			
			/* Atribui as novas informações ao produto que se deseja editar. */
			auxiliarProduto.setNomeProduto(nomeProduto);
			auxiliarProduto.setTipoProduto(tipoProduto);
			auxiliarProduto.setDataCadastroProduto(dataCadastroProduto);
			
			return auxiliarProduto; //retorna a referência para o produto editado
		}
	}

	/**
	 * Proporciona a listagem de todos os produtos cadastrados no sistema. 
	 * @return iterador que possibilitará acesso a cada produto cadastrado
	 */
	public Iterador listarProdutos() {
		return this.listaProdutos.iterador(); //retorna o iterador para a lista encadeada de produtos
    }
	
	/**
	 * Remove um produto do sistema por meio do seu ID. O resultado é <code>true</code> apenas se foi possível remover
	 * o cadastro do produto.
	 * @param idProduto identificação única do produto no sistema
	 * @return <code>true</code>, se o produto foi removido com sucesso; <code>false</code>, caso contrário.
	 */
	public boolean removerProduto(Integer idProduto) {
		int indexProduto = 0; //index do produto que será removido

		indexProduto = this.listaProdutos.obterIndex(this.obterProduto(idProduto)); //obtém index do produto
		
		if(indexProduto == -1){ //verifica se o index obtido foi -1, isto é, se não foi possível encontrar o index do produto
			return false; //se a condição for verdadeira retorna um booleano false, indicando que não foi possível efetuar a remoção 
		}else{
			return !(this.listaProdutos.remover(indexProduto) == null); //verifica se foi possível remover o produto, retornando booleano true se a operação foi realizada com sucesso ou false se houve erro 
		}
	}
	

	
	/**
	 * Retorna a referência para o cadastro de um produto, caso o ID recebido seja válido.
	 * @param idProduto ID do produto que se deseja obter a referência
	 * @return referência para produto, caso o ID informado seja válido; <code>null</code>, caso seja informado um ID inválido.
	 */
	public Produto obterProduto(Integer idProduto){
		Iterador iteradorProdutos = null; //iterador para lista de produtos
		Produto auxiliarProduto = null; //auxiliar com referência para produto
		
		iteradorProdutos = this.listaProdutos.iterador(); //obtém um iterador da lista de produtos

		/* No laço de repetição abaixo a lista de produtos é percorrida e quando é encontrado um produto que possua
		 * o ID informado a referência para esse produto é retornada.
		 */
		while(iteradorProdutos.temProximo() == true){ //verifica se há mais elementos na lista, isto é, se o fim não foi atingido
			auxiliarProduto = (Produto) iteradorProdutos.obterProximo(); //obtém a referência para o próximo produto
			
			if(auxiliarProduto.getIdProduto().equals(idProduto) == true){ //verifica se o ID informado é igual ao ID do produto selecionado
				return auxiliarProduto; //retorna a referência do produto
			}
		}
		
		return null; //retorna null indicando que não foi possível encontrar o produto
	}
	
	/**
	 * Cadastra um doador, caso O CPF/CNPJ informado ainda não esteja associado a nenhum cadastro anterior.
	 * @param numCadastro CPF ou CNPJ do doador que se deseja cadastrar
	 * @param nomeDoador nome do doador que se deseja cadastrar
	 * @param dataNascDoador data de nascimento (pessoa física) ou fundação (pessoa jurídica) do doador que se deseja cadastrar
	 * @param rua rua do doador que se deseja cadastrar
	 * @param numero número do endereço do doador que se deseja cadastrar
	 * @param bairro bairro do doador que se deseja cadastrar
	 * @param cep cep do doador que se deseja cadastrar
	 * @param cidade cidade do doador que se deseja cadastrar
	 * @param estado estado do doador que se deseja cadastrar
	 * @param pais país do doador que se deseja cadastrar
	 * @param tipoDoador tipo de doador a ser cadastrado(pessoa física ou jurídica)
	 * @return referência para o novo doador, caso seja possível realizar o cadastro; <code>null</code>, caso já exista cadastro com o CPF/CNPJ informado.
	 */
	public Doador cadastrarDoador(String numCadastro, String nomeDoador, Date dataNascDoador, String rua, int numero, String bairro, String cep, String cidade, String estado, String pais, String tipoDoador) {
		Doador novoDoador = null; //variável auxiliar que conterá referência para o novo cadastro de doador
		
		if(this.listaDoadores.obterIndex(this.obterDoador(numCadastro))!= -1){ //verifica se é possível obter um index válido para o doador na lista encadeada de doadores, isto é, se já existe um cadastro do doador cujo CPF/CNPJ foi informado
			return null; //retorna referência null caso a condição seja satisfeita
		}else{
			novoDoador = new Doador(numCadastro, nomeDoador, dataNascDoador, rua, numero, bairro, cep, cidade, estado, pais, tipoDoador); //instancia um novo objeto do tipo doador
			
			listaDoadores.inserirFinal(novoDoador); //insere o novo objeto doador na lista encadeada específica
			
			return novoDoador; //retorna a referência para o objeto doador cadastrado
		}
		
    }
	
	/**
	 * Altera o cadastro já existente de um doador, caso o CPF/CNPJ recebido seja válido. 
	 * @param numCadastro CPF ou CNPJ do doador que terá seu cadastro alterado
	 * @param nomeDoador nome que estará no cadastro do doador após a alteração
	 * @param dataNasc data de nascimento/fundação que estará no cadastro do doador após a alteração
	 * @param rua rua do endereço que estará no cadastro do doador após a alteração
	 * @param numero número do endereço que estará no cadastro do doador após a alteração
	 * @param bairro bairro do endereço que estará no cadastro do doador após a alteração
	 * @param cep cep do endereço que estará no cadastro do doador após a alteração
	 * @param cidade cidade do endereço que estará no cadastro do doador após a alteração
	 * @param estado estado do endereço que estará no cadastro do doador após a alteração
	 * @param pais país do endereço que estará no cadastro do doador após a alteração
	 * @param tipoDoador tipo de doador (pessoa física ou jurídica) que estará no cadastro após a alteração
	 * @return referência para o doador editado, caso o CPF/CNPJ informado seja válido; <code>null</code>, caso contrário.
	 */
	public Doador editarDoador(String numCadastro, String nomeDoador, Date dataNasc, String rua, int numero, String bairro, String cep, String cidade, String estado, String pais, String tipoDoador) {
		Doador auxiliarDoador = null; //referência auxiliar para acessar o cadastro do doador que será alterado
		int indexDoador = 0; //index para localização do doador que terá o cadastro alterado

		indexDoador = this.listaDoadores.obterIndex(this.obterDoador(numCadastro)); //obtém index do doador
		
		if(indexDoador == -1){ //verifica se o index é inválido, isto é, se não existe um doador com o CPF/CNPJ informado
			return null; //retorna referência nula caso a condição seja satisfeita
		}else{
			auxiliarDoador = (Doador) listaDoadores.recuperar(indexDoador); //obtém a referência para o cadastro do doador
			
			/* Altera os atributos a serem modificados no cadastro do doador com as informações recebidas por parâmetro. */
			auxiliarDoador.setNomeDoador(nomeDoador); 
			auxiliarDoador.setDataNasc(dataNasc); 
			auxiliarDoador.setEndereco(rua, numero, bairro, cep, cidade, estado, pais); 
			auxiliarDoador.setTipoDoador(tipoDoador); 
			
			return auxiliarDoador; //retorna a referência para o cadastro de doador editado
		}
		
    }
	
	/**
	 * Retorna a referência para o cadastro de um doador, caso o CPF/CNPJ recebido seja válido.
	 * @param numCadastro CPF ou CNPJ do doador que se deseja obter
	 * @return referência para o objeto doador desejado, se o CPF/CNPJ informado for válido; <code>null</code>, caso contrário.
	 */
	public Doador obterDoador(String numCadastro) {
		Iterador iteradorDoadores = null; //iterador para lista de doadores
		Doador auxiliarDoador = null; //auxiliar com referência para doador
		
		iteradorDoadores = this.listaDoadores.iterador(); //obtém um iterador da lista de doadores
		
		/* No laço de repetição abaixo a lista de doadores é percorrida e quando é encontrado um doador que possua
		 * o CPF/CNPJ informado a referência para esse produto é retornada.
		 */
		while(iteradorDoadores.temProximo() == true){ //verifica se há mais elementos na lista, isto é, se o fim não foi atingido
			auxiliarDoador = (Doador) iteradorDoadores.obterProximo(); //obtém a referência para o próximo doador
			
			if(auxiliarDoador.getNumCadastroDoador().equals(numCadastro) == true){ //verifica se o CPF/CNPJ informado é igual ao CPF/CNPJ do doador selecionado
				return auxiliarDoador; //retorna a referência do doador
			}
		}
		
		return null;  //retorna null indicando que não foi possível encontrar o doador
	}
	
	/**
	 * Remove um doador por meio de um CPF ou CNPJ informado, retornando <code>true</code> apenas se for possível remover
	 * o cadastro com sucesso.
	 * @param numCadastro CPF ou CNPJ do doador que se deseja remover cadastro
	 * @return <code>true</code>, caso o CPF/CNPJ informado seja válido e o cadastro tenha sido removido com sucesso; <code>false</code>, caso contrário.
	 */
	public boolean removerDoador(String numCadastro) {
		Doador doadorRemocao; //referência auxiliar para o objeto do tipo doador que será removido do sistema
		int indexDoador; //index do doador que se deseja remover
		
		indexDoador = this.listaDoadores.obterIndex(this.obterDoador(numCadastro)); //obtém o index do doador
		
		if(indexDoador == -1){ //verifica se o index é válido, isto é, se existe um doador com o CPF/CNPJ informado
			return false; //retorna um booleano false indicando que não foi possível efetuar a remoção
		}else{
			
			doadorRemocao = this.obterDoador(numCadastro); //obtém referência para o objeto doador que será removido
			
			if(doadorRemocao.getTotalDoadoMonetario() != 0 || doadorRemocao.getTotalDoadoNaoMonetario() != 0){ //verifica se o doador em questão já realizou alguma doação monetária ou não monetária
				return false; //retorna um booleano false indicando que não foi possível efetuar a remoção
			}
			
			return !(this.listaDoadores.remover(indexDoador) == null); //remove o doador da lista e retorna um booleano indicando se a operação foi realizada com sucesso
		}
		
    }	
	
	/**
	 * Proporciona a listagem de todos os doadores cadastrados no sistema.
	 * @return iterador que possibilitará acesso a cada doador cadastrado
	 */
	public Iterador listarDoadores() {		
		return this.listaDoadores.iterador(); //obtém e retorna um iterador para a lista encadeada de doadores
    }
	
	
	/**
	 * Realiza uma nova doação. Retorna a referência para a doação realizada, apenas se a doação ocorrer corretamente.
	 * @param doador referência para o doador que realizará a doação
	 * @param produto referência para o produto que será doado
	 * @param quantidadeDoada quantidade doada
	 * @param dataDoacao data de realização da doação
	 * @return referência para a doação realizada, caso os dados recebidos sejam válidos; <code>null</code>, caso contrário.
	 */
	public Doacao efetuarDoacao(Doador doador, Produto produto, Number quantidadeDoada, Date dataDoacao){
		Doacao novaDoacao = null; //referência para a doação que será cadastrada no sistema
		boolean doacaoMonetaria; //indica se a doação é monetária
		
		if(quantidadeDoada.floatValue() < 0){ //verifica se a quantidade doada é um valor menor que 0, isto é, valor inválido
			return null; //retorna referência nula
		}
		
		if(produto.getTipoProduto().equals("Monetario") == true){ //verifica se o produto doado é monetário
			doacaoMonetaria = true; //altera o estado da variável booleana indicando que trata-se de uma doação monetária
			doador.atualizaTotalDoadoMonetario(quantidadeDoada.floatValue(), true); //atualiza o valor doado no total de doações monetárias realizadas pelo doador
			Doacao.atualizaTotalArrecadadoMonetario(quantidadeDoada.floatValue(), true); //atualiza o valor doado no total de doações monetárias do sistema
		} else{
			doacaoMonetaria = false; //altera o estado da variável booleana indicando que trata-se de uma doação não monetária
			doador.atualizaTotalDoadoNaoMonetario(quantidadeDoada.intValue(), true); //atualiza a quantidade doada no total de doações monetárias realizadas pelo doador
			Doacao.atualizaTotalArrecadadoNaoMonetario(quantidadeDoada.intValue(), true); //atualiza a quantidade doada no total de doações do sistema
		}
		
		novaDoacao = new Doacao(dataDoacao, produto, doador, quantidadeDoada, doacaoMonetaria); //instancia um novo objeto do tipo doação
				
		this.listaDoacoes.inserirFinal(novaDoacao); //insere o objeto do tipo doação na lista encadeada
		
		return novaDoacao; //retorna a referência para o objeto doacao cadastrado
	}
	
	/**
	 * Altera o cadastro de uma doação no sistema, caso a identificação única (ID da doação) informada seja válida. Retorna
	 * a referência para a doação alterada.
	 * @param idDoacao identificador único da doação que se deseja alterar no sistema
	 * @param doador referência para o doador contido no cadastro da doação após a alteração
	 * @param produto referência para o produto contido no cadastro da doação após a alteração
	 * @param quantidadeDoada quantidade doada contida no cadastro da doação após a alteração
	 * @param dataDoacao data da doação contida após a alteração
	 * @return referência para a doação alterada, caso o ID informado seja válido; <code>null</code>, caso contrário.
	 */
	public Doacao alterarDoacao(Integer idDoacao, Doador doador, Produto produto, Number quantidadeDoada, Date dataDoacao){
		Doacao auxiliarDoacao = null; //referência auxiliar para a doação que será alterada
		int indexDoacao = 0; //index para localização da doação que será alterada
		
		indexDoacao = this.listaDoacoes.obterIndex(this.obterDoacao(idDoacao)); //obtém o index da doação a ser alterada
		
		if(indexDoacao == -1){ //verifica se o index é inválido, isto é, se não há cadastro de doação com o ID informado
			return null; //retorna referência nula caso a condição seja satisfeita
		}else{
			auxiliarDoacao = (Doacao) this.listaDoacoes.recuperar(indexDoacao); //obtém a referência para a doação que será editada
			
			/* As condições abaixo impedem a troca do produto de uma doação cujo produto é monetário para um produto não monetário e vice e versa,
			 * isso evita possíveis erros, pois os valores monetários são representados em float e os não monetários em int.
			 */
			if(auxiliarDoacao.getProduto().getTipoProduto().equals("Monetario") == true && produto.getTipoProduto().equals("Monetario") == false){ 
				return null;
			}else if(auxiliarDoacao.getProduto().getTipoProduto().equals("Monetario") == false && produto.getTipoProduto().equals("Monetario") == true){
				return null;
			}
			
			if(auxiliarDoacao.isMonetaria() == true){ //verifica se a doação é monetária
				auxiliarDoacao.getDoador().atualizaTotalDoadoMonetario(auxiliarDoacao.getQuantidadeDoada().floatValue(), false); //remove o valor anteriormente doado do total de doações monetárias do doador
				auxiliarDoacao.getDoador().atualizaTotalDoadoMonetario(quantidadeDoada.floatValue(), true); //adiciona o novo valor ao total de doações monetárias do doador
				Doacao.atualizaTotalArrecadadoMonetario(auxiliarDoacao.getQuantidadeDoada().floatValue(), false); //remove o valor anteriormente doado do total de doações monetárias do sistema
				Doacao.atualizaTotalArrecadadoMonetario(quantidadeDoada.floatValue(), true); //adiciona o novo valor doado ao total de doações monetárias dos sistema
			} else{
				auxiliarDoacao.getDoador().atualizaTotalDoadoNaoMonetario(auxiliarDoacao.getQuantidadeDoada().intValue(), false); //remove a quantidade anteriormente doada do total de doações não monetárias do doador
				auxiliarDoacao.getDoador().atualizaTotalDoadoNaoMonetario(quantidadeDoada.intValue(), true); //adiciona a nova quantidade ao total de doações não monetárias do doador
				Doacao.atualizaTotalArrecadadoNaoMonetario(auxiliarDoacao.getQuantidadeDoada().intValue(), false); //remove a quantidade anteriormente doado do total de doações não monetárias do sistema
				Doacao.atualizaTotalArrecadadoNaoMonetario(quantidadeDoada.intValue(), true); //adiciona a nova quantidade doada ao total de doações não monetárias dos sistema
			}
			
			/* Atualiza os atributos da doação com os novos dados. */
			auxiliarDoacao.setQuantidadeDoada(quantidadeDoada);
			auxiliarDoacao.setDoador(doador);
			auxiliarDoacao.setProduto(produto);
			auxiliarDoacao.setDataDoacao(dataDoacao);
			auxiliarDoacao.setMonetaria(produto.getTipoProduto().equals("Monetario")? true:false);
		
			return auxiliarDoacao; //retorna referência para a doação alterada
		}		

	}

	
	/**
	 * Remove uma doação através da sua identificação única (ID do produto) no sistema. Retorna <code>true</code> apenas se for
	 * possível remover a doação.
	 * @param idDoacao identificação única (ID) da doação no sistema
	 * @return <code>true</code>, se o ID informado for válido; <code>false</code>, caso contrário.
	 */
	public boolean removerDoacao(int idDoacao){
		Doacao doacaoRemovida = null; //referência para a doação que será removida
		int indexDoacao = 0; //index da doação que será removida
		float qtdMonetariaRemovida;
		int qtdNaoMonetariaRemovida;
		
		indexDoacao = this.listaDoacoes.obterIndex(this.obterDoacao(idDoacao)); //obtém o index da doação a ser removida
		
		if(indexDoacao == -1){ //verifica se o index é inválido, isto é, se não existe doação com o ID informado
			return false; //caso a condição seja verdadeira retorna booleano false indicando que não foi possível remover a doação
		}else{
			doacaoRemovida = (Doacao) this.listaDoacoes.recuperar(indexDoacao); 
			
			if(doacaoRemovida.isMonetaria() == true){ //verifica se a doação removida é do tipo monetária
				qtdMonetariaRemovida = doacaoRemovida.getQuantidadeDoada().floatValue();
				Doacao.atualizaTotalArrecadadoMonetario(qtdMonetariaRemovida, false); //remove o valor da doação do total de doações monetárias do sistema
				doacaoRemovida.getDoador().atualizaTotalDoadoMonetario(qtdMonetariaRemovida, false); //remove o valor da doação do total monetário doado pelo doador
			} else{
				qtdNaoMonetariaRemovida = doacaoRemovida.getQuantidadeDoada().intValue();
				Doacao.atualizaTotalArrecadadoNaoMonetario(qtdNaoMonetariaRemovida, false); //remove a quantidade da doação do total de doações não monetárias do sistema
				doacaoRemovida.getDoador().atualizaTotalDoadoNaoMonetario(qtdNaoMonetariaRemovida, false); //remove a quantidade da doação do total não monetário doado pelo doador
			}
						
			return !(this.listaDoacoes.remover(indexDoacao) == null); //retorna booleano true indicando que a remoção ocorreu com sucesso
		}
	}

	/**
	 * Retorna a referência para o cadastro de uma doação, caso a identificação única (ID da doação) informada seja válida.
	 * @param idDoacao identificação única (ID da doação) que se deseja obter referência
	 * @return referência para uma doação, caso o ID informado seja válido; <code>null</code>, caso o ID seja inválido.
	 */
	public Doacao obterDoacao(Integer idDoacao){
		Iterador iteradorDoacoes = null; //iterador para lista de doacoes
		Doacao auxiliarDoacao = null; //referência auxiliar para doacao
		
		iteradorDoacoes = this.listaDoacoes.iterador(); //obtém o iterador da lista de doações
		
		/* No laço de repetição abaixo a lista de doações é percorrida e quando é encontrada doação que possua
		 * o ID informado a referência para essa doação é retornada.
		 */
		
		while(iteradorDoacoes.temProximo() == true){ //verifica se há mais elementos na lista, isto é, se o final não foi atingido
			auxiliarDoacao = (Doacao) iteradorDoacoes.obterProximo(); //obtém a referência para a próxima doação da lista
			
			if(auxiliarDoacao.getIdDoacao().equals(idDoacao) == true){ //verifica se o ID contido no objeto obtido é igual ao id que foi informado
				return auxiliarDoacao; //retorna a referência do objeto encontrado
			}
		}
		
		return null; //retorna null se não foi possível encontrar um objeto
		
	}

	/**
	 * Proporciona a listagem de todas as doações do sistema ordenadas, assim como a listagem de todas as doações (monetárias ou
	 * não monetárias) de um doador específico. 
	 * @param monetario especifica se as doações a serem listadas serão ou não as monetárias, no caso das doações de um doador
	 * @param doador doador que terá as doações listadas; um valor null deve ser informado quando se deseja listar todas as doações do sistema
	 * @return iterador que possibilitará acesso a cada doação; <code>null</code>, caso não seja possível ordenar as doações.
	 */
	public Iterador listarDoacoesOrdenadas(boolean monetario, Doador doador){
		Iterador iteradorDoacoes = null; //iterador para lista encadeada de doações
		ListaEncadeada listaAuxiliar = new ListaEncadeada(); //lista que conterá todas as doações (monetárias ou não monetárias) de um doador
		Doacao auxiliarDoacao = null; //auxiliar que vai referenciar cada cadastro de doação da lista
		Doador auxiliarDoador = null; //auxiliar que vai referenciar cada cadastro de doador da lista
		
		iteradorDoacoes = this.listaDoacoes.iterador(); //obtém o iterador
		
		if(doador == null){ //verifica se a referência de doador informada é null, isto é, se será feita uma listagem de todas as doações do sistema
			
			iteradorDoacoes = this.listaDoacoes.iterador(); //obtém um iterador para lista de doações
			
			while(iteradorDoacoes.temProximo() == true){ //verifica se há mais elementos na lista, isto é, se o final não foi atingido
				auxiliarDoacao = (Doacao) iteradorDoacoes.obterProximo(); //obtém a referência da próxima doacao
				
				if(auxiliarDoacao.isMonetaria() == monetario){ //verifica se o tipo da doação corresponde ao tipo que se deseja listar
					listaAuxiliar.inserirFinal(auxiliarDoacao); //insere a doação na nova lista
				}
			}
			
			if(listaAuxiliar.ordenar() == false){ //verifica se é possível ordenar a lista gerada
				return null; //retorna null caso ocorra algum erro durante a ordenação
			}
			
			
			return listaAuxiliar.iterador(); //retorna iterador para a lista de doações já ordenada
		} else{
			listaAuxiliar = new ListaEncadeada(); //instancia uma nova lista encadeada
			iteradorDoacoes = this.listaDoacoes.iterador(); //obtém um novo iterador para a lista encadeada de doações

			while(iteradorDoacoes.temProximo() == true){ //verifica se ainda existem nós na lista encadeada
				auxiliarDoacao = (Doacao) iteradorDoacoes.obterProximo(); //obtém a referência para o cadastro de uma doação
				auxiliarDoador = auxiliarDoacao.getDoador(); //obtém o doador da doação obtida acima
				
				if(auxiliarDoador.getNumCadastroDoador().equals(doador.getNumCadastroDoador()) && auxiliarDoacao.isMonetaria() == monetario){ //verifica se o CPF/CNPJ do doador que as doações serão listadas é igual ao do doador selecionado da lista e se o tipo de doação (monetária ou não monetária) corresponde ao tipo que se deseja listar 
					listaAuxiliar.inserirFinal(auxiliarDoacao); //insere o cadastro da doação na lista encadeada de doações do doador
				}
			}
			
			if(listaAuxiliar.ordenar() == false){ //verifica se houve erro durante a ordenação da lista
				return null; //retorna referência null caso não tenha sido possível ordenar
			}
			
			return listaAuxiliar.iterador(); //retorna iterador para a lista de doações de um doador já ordenada
		}
	}
	
	/**
	 * Calcula o percentual de doações monetárias e não monetárias de cada doador. O percentual de cada doador
	 * é preenchido com 0, caso não tenham sido realizadas doações.
	 */
	public void calcularPercentualDoadores(){
		Iterador iteradorDoadores = null; //iterador para percorrer a lista encadeada de doadores
		Doador doadorAuxiliar = null; //auxiliar que vai referenciar cada cadastro de doador da lista
		float percentualMonetario = 0, percentualNaoMonetario = 0; //percentuais que serão calculados e atribuidos aos doadores 
		
		iteradorDoadores = this.listaDoadores.iterador(); //obtém o iterador para a lista de doadores
		
		while(iteradorDoadores.temProximo() == true){ //verifica se ainda existem nós na lista encadeada
			doadorAuxiliar = (Doador) iteradorDoadores.obterProximo(); //obtém referência para um doador
		
			if(Doacao.getTotalArrecadadoMonetario() == 0){ //verifica se o total arrecado é 0
				percentualMonetario = 0; //define o percentual como 0 para evitar erros com divisão por 0
			}else{
				percentualMonetario = (doadorAuxiliar.getTotalDoadoMonetario()/Doacao.getTotalArrecadadoMonetario())*100; //calcula o percentual de doações monetárias
			}
			
			if(Doacao.getTotalArrecadadoNaoMonetario() == 0){ //verifica se o total arrecado é 0
				percentualNaoMonetario = 0; //define o percentual como 0 para evitar erros com divisão por 0
			} else{
				/* Calcula o percentual de doações não monetárias. */
				percentualNaoMonetario = ((float) doadorAuxiliar.getTotalDoadoNaoMonetario())/(Doacao.getTotalArrecadadoNaoMonetario());
				percentualNaoMonetario *= 100;
			}
			
			/* Adiciona os valores calculados nos respectivos atributos do doador. */
			doadorAuxiliar.setPercentualDoacoesMonetarias(percentualMonetario);
			doadorAuxiliar.setPercentualDoacoesNaoMonetarias(percentualNaoMonetario);
		}
	}
	
}