package br.uefs.ecomp.sacMariana.view;
import java.io.IOException;
import java.util.Date;
import br.uefs.ecomp.sacMariana.controller.SACMarianaController;
import br.uefs.ecomp.sacMariana.model.Produto;
import br.uefs.ecomp.sacMariana.util.Console;
import br.uefs.ecomp.sacMariana.util.Iterador;
import br.uefs.ecomp.sacMariana.util.PegaData;



/**
 * Classe responsável por moldar um menu com as opções disponíveis para gerenciamento de produtos no sistema e exibir
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
	
	/** Controller responsável por efetivar as solicitações realizadas. */
	private SACMarianaController controller;
	
	/**
	 * Constrói um menu para gerenciamento de produtos e atribui ao atributo "controller" uma referência de 
	 * Controller já instanciado. 
	 *
	 * @param controller referência para o controller do sistema
	 */
	public MenuProdutos(SACMarianaController controller){
		this.controller = controller; //atribui a referênca do controller ao atributo "controller"
	}

	
	/**
	 * Método estático que exibe as opções iniciais para gerenciamento de produtos no sistema.
	 */
	public static void exibeOpcoesIniciasProduto(){
		/* As opções iniciais são mostradas na tela. */
		System.out.println("O que você deseja fazer?");
		System.out.println("1- Cadastrar Produto");
		System.out.println("2- Alterar produto cadastrado");
		System.out.println("3- Remover produto cadastrado");
		System.out.println("4- Listar produtos");
	}
	
	
	/**
	 * Método estático que exibe as opções para alteração do cadastro de um produto.
	 */
	private static void exibeOpcoesAlterarProduto(){
		/* As opções de alteração são mostradas na tela. */
		System.out.println("Qual informação você deseja alterar?");
		System.out.println("1- Nome");
		System.out.println("2- Data");
		System.out.println("3- Tipo");
	}
	
	
	/**
	 * Método estático que exibe os tipos de produtos que podem ser cadastrados no sistema.
	 */
	private static void exibeTiposProduto(){
		/* As opções para tipo de produto são mostradas na tela. */
		System.out.println("1- Alimento");
		System.out.println("2- Vestuario");
		System.out.println("3- Limpeza");
		System.out.println("4- Monetária");
		System.out.println("5- Outros");

	}

	/**
	 * Método estático utilizado para obter uma String contendo o tipo de produto a partir de uma opção 
	 * inserida pelo usuário.
	 *
	 * @param opcaoTipoProduto opcao de tipo de produto escolhida pelo usuário
	 * @return String contendo o tipo de produto, se a opção inserida for válida; <code>null</code>, caso contrário.
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
		} else{ //verifica se foi informada uma opção que não condiz com nenhum dos casos
			return null; //retorna uma referência null
		}
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de cadastro de um produto.
	 *
	 * @throws NumberFormatException sinaliza que houve uma tentativa de converter uma String para um dos tipos numéricos porém a String não possuía o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void realizacaoDeCadastroProduto() throws NumberFormatException, IOException{
		String nomeProduto = null; //nome do produto que será cadastrado
		int opcaoTipoProduto = 0; //conterá a opção escolhida pelo usuário durante a definição do tipo de produto
		Produto cadastrado = null; //referência para o objeto produto instanciado
		
		System.out.println("Informe o nome do produto que deseja cadastrar"); 
		nomeProduto = Console.readString(); //recebe o nome do produto
		
		System.out.println("Informe o tipo do produto que deseja cadastrar");
		exibeTiposProduto(); //exibe os tipos de produto disponíveis no sistema
		
		do{
			opcaoTipoProduto = Console.readInt(); //recebe a opção escolhida pelo usuário
			
			if(opcaoTipoProduto < 1 || opcaoTipoProduto > 5){ //verifica se o valor inserido pelo usuário é válido
				System.out.println("Tipo de produto escolhido é inválido. Tente novamente!\n"); //exibe mensagem de erro em caso de valor inválido
			}
			
		}while(opcaoTipoProduto < 1 || opcaoTipoProduto > 5); //executa a operação de solicitar a opção do usuário até que o valor inserido seja válido
	
	
		cadastrado = controller.cadastrarProduto(nomeProduto, tipoProdutoEscolhido(opcaoTipoProduto), new Date()); //envia uma mensagem para o controller contendo as informações do produto e recebe uma referência para o objeto produto que foi instanciado
		
		System.out.printf("O produto %s foi cadastrado com o ID %s\n\n", cadastrado.getNomeProduto(), cadastrado.getIdProduto().toString()); //exibe uma mensagem em tela informado que o processo de cadastro ocorreu normalmente e exibindo o ID atribuido ao produto
		
		//OBS: A data de cadastro é obtida diretamente do sistema.
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de alteração do cadastro de um produto.
	 *
	 * @throws NumberFormatException sinaliza que houve uma tentativa de converter uma String para um dos tipos numéricos porém a String não possuía o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void alteracaoDeProduto() throws NumberFormatException, IOException{
		int opcaoAlterar = 0, opcaoTipoProduto = 0; //opção escolhida pelo usuário para alteração e tipo de produto, respectivamente
		Integer idProduto = null; //ID informado pelo usuário do produto que deseja alterar
		Produto produtoAlteracao = null; //referência para o produto que será alterado
		
		
		System.out.println("Digite o ID do produto que deseja alterar informações");
		idProduto = Console.readInt(); //recebe o ID do produto informado pelo usuário
		
		produtoAlteracao = this.controller.obterProduto(idProduto); //obtém a referência para o produto que será alterado
		
		if(produtoAlteracao == null){ //verifica se a referência obtida é null
			System.out.println("Erro ao alterar informações: o ID inserido é inválido\n"); //exibe mensagem de erro informado que não foi possível completar a operação
			return; //finaliza o método
		}

		exibeOpcoesAlterarProduto(); //exibe as opções disponíveis para alteração de um produto
		
		opcaoAlterar = Console.readInt(); //recebe a opção escolhida pelo usuário para alterar um produto
		

		if(opcaoAlterar == 1){ //verifica se a opção do usuário foi 1
			System.out.println("Digite o novo nome do produto"); //exibe mensagem em tela solicitando um novo nome para o cadastro
			produtoAlteracao = this.controller.editarProduto(produtoAlteracao.getIdProduto(), Console.readString(), produtoAlteracao.getTipoProduto(), produtoAlteracao.getDataCadastroProduto()); //recebe do usuário o novo nome, solicita a alteração ao controller e recebe a referência para o objeto após a alteração			
		} else if(opcaoAlterar == 2){ //verifica se a opção do usuário foi 2
			System.out.println("Digite a nova data de cadastro do produto"); //exibe mensagem em tela solicitando a nova data de cadastro do produto
			produtoAlteracao = this.controller.editarProduto(produtoAlteracao.getIdProduto(), produtoAlteracao.getNomeProduto(), produtoAlteracao.getTipoProduto(), PegaData.pegaData()); //recebe do usuário a nova data, solicita a alteração ao controller e recebe a referência para o objeto após a alteração			
		} else if(opcaoAlterar == 3){ //verifica se a opção do usuário foi 3
			System.out.println("Alterar tipo de produto"); //exibe mensagem em tela solicitando o novo tipo do produto
			exibeTiposProduto(); //exibe os tipos de produto disponíveis
			
			do{
				opcaoTipoProduto = Console.readInt(); //recebe do usuário a opção para alteração do tipo de produto
				
				if(opcaoTipoProduto < 1 || opcaoTipoProduto > 5){ //verifica se a opção informada é válida
					System.out.println("Tipo de doador escolhido é inválido. Tente novamente\n"); //exibe mensagem de erro
				}
			}while(opcaoTipoProduto < 1 || opcaoTipoProduto > 5); //executa a operação de solicitar o tipo até que o valor inserido seja válido
			
			
			produtoAlteracao = this.controller.editarProduto(produtoAlteracao.getIdProduto(), produtoAlteracao.getNomeProduto(), tipoProdutoEscolhido(opcaoTipoProduto), produtoAlteracao.getDataCadastroProduto()); //utiliza a opção do usuário para obtenção do tipo, solicita a alteração ao controller e recebe a referência para o objeto após a alteração			
		} else{ //verifica se a entrada não condiz com nenhuma das condições anteriores
			System.out.println("Opção inválida"); //exibe uma mensagem avisando sobre a entrada inválida
			return; //finaliza o método
		}
		
		if(produtoAlteracao == null){ //verifica se a referência obtida é null
			System.out.println("Erro ao realizar alteração"); //exibe mensagem de erro
		} else{
			System.out.printf("O produto de ID %s foi alterado com sucesso\n\n", produtoAlteracao.getIdProduto().toString()); //exibe uma mensagem indicando que o produto foi alterado com sucesso
		}
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de remoção do cadastro de um produto.
	 *
	 * @throws NumberFormatException  sinaliza que houve uma tentativa de converter uma String para um dos tipos numéricos porém a String não possuía o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void realizacaoDeRemocaoProduto() throws NumberFormatException, IOException{
		Integer idProduto = null; //id do produto que será removido do sistema
		
		System.out.println("Digite o ID do produto que deseja remover do sistema");
		idProduto = Console.readInt(); //recebe do usuário o id do produto que deseja remover
		
		if(controller.removerProduto(idProduto) == true){ //envia mensagem pro controller solicitando a remoção do produto e verifica se o processo ocorreu adequadamente
			System.out.printf("O produto de ID %s foi removido com sucesso", idProduto.toString()); //exibe uma mensagem informando que a remoção ocorreu com sucesso
		} else{
			System.out.println("Impossível remover produto do sistema: o ID informado é invalido\n\n"); //exibe uma mensagem informando erro durante a remoção
		}
	}
	
	/**
	 * Realiza a interação com o usuário durante a listagem de todos os produtos cadastrados no sistema.
	 */
	public void realizacaoDeListagemProdutos(){
		Iterador iteradorProdutos = null; //iterador para percorrer a lista de produtos
		Produto auxiliarProduto = null; //referência auxiliar para exibição das informações do produto
		
		iteradorProdutos = controller.listarProdutos(); //obtém o iterador da lista
		
		if(iteradorProdutos.temProximo() == false){ //verifica se a lista está vazia
			System.out.println("Não há produtos cadastrados no sistema"); //exibe mensagem indicando que não há produtos cadastrados
			return; //encerra o método
		}else{
			
			System.out.println("\n* LISTAGEM DE TODOS OS PRODUTOS CADASTRADOS NO SISTEMA *\n");

			/* O laço de repetição abaixo é executado até que o fim da lista seja atingido e todos os produtos sejam listados. */
			while(iteradorProdutos.temProximo() == true){ //verifica se há próximo elemento
				auxiliarProduto = (Produto) iteradorProdutos.obterProximo(); //obtém a referência para o próximo elemento 
		
				/* As informações do produto são exibidas em tela. */
				System.out.printf("- PRODUTO COM ID %s -\n",  auxiliarProduto.getIdProduto().toString());
				System.out.printf("- NOME: %s\n", auxiliarProduto.getNomeProduto());
				System.out.printf("- TIPO DO PRODUTO: %s\n", auxiliarProduto.getTipoProduto());
				System.out.printf("- DATA DE CADASTRO: %s\n\n", auxiliarProduto.getDataCadastroProduto().toString());
			}
		}
	}


}
