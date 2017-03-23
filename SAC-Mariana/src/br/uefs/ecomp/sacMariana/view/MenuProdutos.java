package br.uefs.ecomp.sacMariana.view;
import java.io.IOException;
import java.util.Date;
import br.uefs.ecomp.sacMariana.controller.SACMarianaController;
import br.uefs.ecomp.sacMariana.model.Produto;
import br.uefs.ecomp.sacMariana.util.Console;
import br.uefs.ecomp.sacMariana.util.Iterador;
import br.uefs.ecomp.sacMariana.util.PegaData;



/**
 * Classe respons�vel por moldar um menu com as op��es dispon�veis para gerenciamento de produtos no sistema e exibir
 * das principais telas utilizadas nesse processo. 
 * 
 * @author Valmir Vinicius
 * @see java.util.Date
 * @see br.uefs.ecomp.sacMariana.controller.SACMarianaController
 * @see br.uefs.ecomp.sacMariana.model.Produto
 * @see br.uefs.ecomp.sacMariana.util.Console
 * @see br.uefs.ecomp.sacMariana.util.Iterador
 * @see br.uefs.ecomp.sacMariana.util.PegaData
 * 
 */
public class MenuProdutos {
	
	/** Controller respons�vel por efetivar as solicita��es realizadas. */
	private SACMarianaController controller;
	
	/**
	 * Constr�i um menu para gerenciamento de produtos e atribui ao atributo "controller" uma refer�ncia de 
	 * Controller j� instanciado. 
	 *
	 * @param controller refer�ncia para o controller do sistema
	 */
	public MenuProdutos(SACMarianaController controller){
		this.controller = controller; //atribui a refer�nca do controller ao atributo "controller"
	}

	
	/**
	 * M�todo est�tico que exibe as op��es iniciais para gerenciamento de produtos no sistema.
	 */
	public static void exibeOpcoesIniciasProduto(){
		/* As op��es iniciais s�o mostradas na tela. */
		System.out.println("O que voc� deseja fazer?");
		System.out.println("1- Cadastrar Produto");
		System.out.println("2- Alterar produto cadastrado");
		System.out.println("3- Remover produto cadastrado");
		System.out.println("4- Listar produtos");
	}
	
	
	/**
	 * M�todo est�tico que exibe as op��es para altera��o do cadastro de um produto.
	 */
	private static void exibeOpcoesAlterarProduto(){
		/* As op��es de altera��o s�o mostradas na tela. */
		System.out.println("Qual informa��o voc� deseja alterar?");
		System.out.println("1- Nome");
		System.out.println("2- Data");
		System.out.println("3- Tipo");
	}
	
	
	/**
	 * M�todo est�tico que exibe os tipos de produtos que podem ser cadastrados no sistema.
	 */
	private static void exibeTiposProduto(){
		/* As op��es para tipo de produto s�o mostradas na tela. */
		System.out.println("1- Alimento");
		System.out.println("2- Vestuario");
		System.out.println("3- Limpeza");
		System.out.println("4- Monet�ria");
		System.out.println("5- Outros");

	}

	/**
	 * M�todo est�tico utilizado para obter uma String contendo o tipo de produto a partir de uma op��o 
	 * inserida pelo usu�rio.
	 *
	 * @param opcaoTipoProduto opcao de tipo de produto escolhida pelo usu�rio
	 * @return String contendo o tipo de produto, se a op��o inserida for v�lida; <code>null</code>, caso contr�rio.
	 */
	private static String tipoProdutoEscolhido(int opcaoTipoProduto){
		
		if(opcaoTipoProduto == 1){ //verifica se a opcao foi 1
			return "Alimento"; //retorna uma string contendo o tipo "Alimento"
		} else if(opcaoTipoProduto == 2){ //verifica se a opcao foi 2
			return "Vestuario"; //retorna uma string contendo o tipo "Vestuario"
		} else if(opcaoTipoProduto == 3){ //verifica se a opcao foi 3
			return "Limpeza"; //retorna uma string contendo o tipo "Limpeza"
		} else if(opcaoTipoProduto == 4){ //verifica se a opcao foi 4
			return "Monetario"; //retorna uma string contendo o tipo "Monetario"
		} else if(opcaoTipoProduto == 5){ //verifica se a opcao foi 5
			return "Outros"; //retorna uma string contendo o tipo "Outros"
		} else{ //verifica se foi informada uma op��o que n�o condiz com nenhum dos casos
			return null; //retorna uma refer�ncia null
		}
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de cadastro de um produto.
	 *
	 * @throws NumberFormatException sinaliza que houve uma tentativa de converter uma String para um dos tipos num�ricos por�m a String n�o possu�a o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void realizacaoDeCadastroProduto() throws NumberFormatException, IOException{
		String nomeProduto = null; //nome do produto que ser� cadastrado
		int opcaoTipoProduto = 0; //conter� a op��o escolhida pelo usu�rio durante a defini��o do tipo de produto
		Produto cadastrado = null; //refer�ncia para o objeto produto instanciado
		
		System.out.println("Informe o nome do produto que deseja cadastrar"); 
		nomeProduto = Console.readString(); //recebe o nome do produto
		
		System.out.println("Informe o tipo do produto que deseja cadastrar");
		exibeTiposProduto(); //exibe os tipos de produto dispon�veis no sistema
		
		do{
			opcaoTipoProduto = Console.readInt(); //recebe a op��o escolhida pelo usu�rio
			
			if(opcaoTipoProduto < 1 || opcaoTipoProduto > 5){ //verifica se o valor inserido pelo usu�rio � v�lido
				System.out.println("Tipo de produto escolhido � inv�lido. Tente novamente!\n"); //exibe mensagem de erro em caso de valor inv�lido
			}
			
		}while(opcaoTipoProduto < 1 || opcaoTipoProduto > 5); //executa a opera��o de solicitar a op��o do usu�rio at� que o valor inserido seja v�lido
	
	
		cadastrado = controller.cadastrarProduto(nomeProduto, tipoProdutoEscolhido(opcaoTipoProduto), new Date()); //envia uma mensagem para o controller contendo as informa��es do produto e recebe uma refer�ncia para o objeto produto que foi instanciado
		
		System.out.printf("O produto %s foi cadastrado com o ID %s\n\n", cadastrado.getNomeProduto(), cadastrado.getIdProduto().toString()); //exibe uma mensagem em tela informado que o processo de cadastro ocorreu normalmente e exibindo o ID atribuido ao produto
		
		//OBS: A data de cadastro � obtida diretamente do sistema.
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de altera��o do cadastro de um produto.
	 *
	 * @throws NumberFormatException sinaliza que houve uma tentativa de converter uma String para um dos tipos num�ricos por�m a String n�o possu�a o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void alteracaoDeProduto() throws NumberFormatException, IOException{
		int opcaoAlterar = 0, opcaoTipoProduto = 0; //op��o escolhida pelo usu�rio para altera��o e tipo de produto, respectivamente
		Integer idProduto = null; //ID informado pelo usu�rio do produto que deseja alterar
		Produto produtoAlteracao = null; //refer�ncia para o produto que ser� alterado
		
		
		System.out.println("Digite o ID do produto que deseja alterar informa��es");
		idProduto = Console.readInt(); //recebe o ID do produto informado pelo usu�rio
		
		produtoAlteracao = this.controller.obterProduto(idProduto); //obt�m a refer�ncia para o produto que ser� alterado
		
		if(produtoAlteracao == null){ //verifica se a refer�ncia obtida � null
			System.out.println("Erro ao alterar informa��es: o ID inserido � inv�lido\n"); //exibe mensagem de erro informado que n�o foi poss�vel completar a opera��o
			return; //finaliza o m�todo
		}

		exibeOpcoesAlterarProduto(); //exibe as op��es dispon�veis para altera��o de um produto
		
		opcaoAlterar = Console.readInt(); //recebe a op��o escolhida pelo usu�rio para alterar um produto
		

		if(opcaoAlterar == 1){ //verifica se a op��o do usu�rio foi 1
			System.out.println("Digite o novo nome do produto"); //exibe mensagem em tela solicitando um novo nome para o cadastro
			produtoAlteracao = this.controller.editarProduto(produtoAlteracao.getIdProduto(), Console.readString(), produtoAlteracao.getTipoProduto(), produtoAlteracao.getDataCadastroProduto()); //recebe do usu�rio o novo nome, solicita a altera��o ao controller e recebe a refer�ncia para o objeto ap�s a altera��o			
		} else if(opcaoAlterar == 2){ //verifica se a op��o do usu�rio foi 2
			System.out.println("Digite a nova data de cadastro do produto"); //exibe mensagem em tela solicitando a nova data de cadastro do produto
			produtoAlteracao = this.controller.editarProduto(produtoAlteracao.getIdProduto(), produtoAlteracao.getNomeProduto(), produtoAlteracao.getTipoProduto(), PegaData.pegaData()); //recebe do usu�rio a nova data, solicita a altera��o ao controller e recebe a refer�ncia para o objeto ap�s a altera��o			
		} else if(opcaoAlterar == 3){ //verifica se a op��o do usu�rio foi 3
			System.out.println("Alterar tipo de produto"); //exibe mensagem em tela solicitando o novo tipo do produto
			exibeTiposProduto(); //exibe os tipos de produto dispon�veis
			
			do{
				opcaoTipoProduto = Console.readInt(); //recebe do usu�rio a op��o para altera��o do tipo de produto
				
				if(opcaoTipoProduto < 1 || opcaoTipoProduto > 5){ //verifica se a op��o informada � v�lida
					System.out.println("Tipo de doador escolhido � inv�lido. Tente novamente\n"); //exibe mensagem de erro
				}
			}while(opcaoTipoProduto < 1 || opcaoTipoProduto > 5); //executa a opera��o de solicitar o tipo at� que o valor inserido seja v�lido
			
			
			produtoAlteracao = this.controller.editarProduto(produtoAlteracao.getIdProduto(), produtoAlteracao.getNomeProduto(), tipoProdutoEscolhido(opcaoTipoProduto), produtoAlteracao.getDataCadastroProduto()); //utiliza a op��o do usu�rio para obten��o do tipo, solicita a altera��o ao controller e recebe a refer�ncia para o objeto ap�s a altera��o			
		} else{ //verifica se a entrada n�o condiz com nenhuma das condi��es anteriores
			System.out.println("Op��o inv�lida"); //exibe uma mensagem avisando sobre a entrada inv�lida
			return; //finaliza o m�todo
		}
		
		if(produtoAlteracao == null){ //verifica se a refer�ncia obtida � null
			System.out.println("Erro ao realizar altera��o"); //exibe mensagem de erro
		} else{
			System.out.printf("O produto de ID %s foi alterado com sucesso\n\n", produtoAlteracao.getIdProduto().toString()); //exibe uma mensagem indicando que o produto foi alterado com sucesso
		}
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de remo��o do cadastro de um produto.
	 *
	 * @throws NumberFormatException  sinaliza que houve uma tentativa de converter uma String para um dos tipos num�ricos por�m a String n�o possu�a o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void realizacaoDeRemocaoProduto() throws NumberFormatException, IOException{
		Integer idProduto = null; //id do produto que ser� removido do sistema
		
		System.out.println("Digite o ID do produto que deseja remover do sistema");
		idProduto = Console.readInt(); //recebe do usu�rio o id do produto que deseja remover
		
		if(controller.removerProduto(idProduto) == true){ //envia mensagem pro controller solicitando a remo��o do produto e verifica se o processo ocorreu adequadamente
			System.out.printf("O produto de ID %s foi removido com sucesso", idProduto.toString()); //exibe uma mensagem informando que a remo��o ocorreu com sucesso
		} else{
			System.out.println("Imposs�vel remover produto do sistema: o ID informado � invalido\n\n"); //exibe uma mensagem informando erro durante a remo��o
		}
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante a listagem de todos os produtos cadastrados no sistema.
	 */
	public void realizacaoDeListagemProdutos(){
		Iterador iteradorProdutos = null; //iterador para percorrer a lista de produtos
		Produto auxiliarProduto = null; //refer�ncia auxiliar para exibi��o das informa��es do produto
		
		iteradorProdutos = controller.listarProdutos(); //obt�m o iterador da lista
		
		if(iteradorProdutos.temProximo() == false){ //verifica se a lista est� vazia
			System.out.println("N�o h� produtos cadastrados no sistema"); //exibe mensagem indicando que n�o h� produtos cadastrados
			return; //encerra o m�todo
		}else{
			
			System.out.println("\n* LISTAGEM DE TODOS OS PRODUTOS CADASTRADOS NO SISTEMA *\n");

			/* O la�o de repeti��o abaixo � executado at� que o fim da lista seja atingido e todos os produtos sejam listados. */
			while(iteradorProdutos.temProximo() == true){ //verifica se h� pr�ximo elemento
				auxiliarProduto = (Produto) iteradorProdutos.obterProximo(); //obt�m a refer�ncia para o pr�ximo elemento 
		
				/* As informa��es do produto s�o exibidas em tela. */
				System.out.printf("- PRODUTO COM ID %s -\n",  auxiliarProduto.getIdProduto().toString());
				System.out.printf("- NOME: %s\n", auxiliarProduto.getNomeProduto());
				System.out.printf("- TIPO DO PRODUTO: %s\n", auxiliarProduto.getTipoProduto());
				System.out.printf("- DATA DE CADASTRO: %s\n\n", auxiliarProduto.getDataCadastroProduto().toString());
			}
		}
	}


}
