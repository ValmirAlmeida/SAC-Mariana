package br.uefs.ecomp.sacMariana.view;
import java.io.IOException;
import java.util.Date;
import br.uefs.ecomp.sacMariana.controller.SACMarianaController;
import br.uefs.ecomp.sacMariana.model.Doacao;
import br.uefs.ecomp.sacMariana.model.Doador;
import br.uefs.ecomp.sacMariana.model.Produto;
import br.uefs.ecomp.sacMariana.util.Console;
import br.uefs.ecomp.sacMariana.util.Iterador;
import br.uefs.ecomp.sacMariana.util.PegaData;

/**
 * Classe responsável por moldar um menu com as opções disponíveis para gerenciamento de doações no sistema e exibir
 * das principais telas utilizadas nesse processo. 
 * 
 * @author Valmir Vinicius
 *
 */
public class MenuDoacao {
	
	/** Controller responsável por efetivar as solicitações realizadas. */
	private SACMarianaController controller;
	
	/**
	 * Constrói um menu para gerenciamento de doações e atribui ao atributo "controller" uma referência de Controller 
	 * já instanciado. 
	 *	
	 * @param controller referência para o controller do sistema
	 */
	public MenuDoacao(SACMarianaController controller){
		this.controller = controller; //atribui a referênca do controller ao atributo "controller"
	}
	
	/**
	 * Método estático que exibe as opções iniciais para gerenciamento de doações no sistema.
	 */
	public static void exibeOpcoesIniciasDoacao(){
		/* As opções iniciais são mostradas na tela. */
		
		System.out.println("O que você deseja fazer?");
		System.out.println("1- Efetuar doação");
		System.out.println("2- Alterar doação");
		System.out.println("3- Remover doação");
		System.out.println("4- Listar doações ordenadas");
		System.out.println("5- Listar doações de um doador");
	}
	
	/**
	 * Método estático que exibe as opções para alteração no cadastro de uma doação.
	 */
	private static void exibeOpcoesAlterarDoacao(){
		/* As opções para alteração do cadastro de uma doação são exibidas na tela. */
		
		System.out.println("Qual a informação da doação deseja alterar?");
		System.out.println("1- Doador");
		System.out.println("2- Produto");
		System.out.println("3- Quantidade doada");
		System.out.println("4- Data da doação");
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de cadastro de uma doação.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void realizacaoDeDoacao() throws IOException{
		Doador doador = null; //referência para o doador que realizará a doação
		Produto produtoDoado = null; //referência para o produto que será doado
		Number quantidadeDoada = null; //quantidade que será doada
		Doacao doacaoEfetuada = null; //referência para a doação realizada
		

		System.out.println("Informe o CPF/CNPJ do doador que fará a doação");
		doador = controller.obterDoador(Console.readString()); //obtém a referência de um doador a partir da entrada do seu CPF/CNPJ
			
		if(doador == null){ //verifica se a referência obtida é nula
			System.out.println("O CPF/CNPJ informado é inválido"); //exibe mensagem informando sobre erro durante a operação
			return; //finaliza o método
		}
	
		
		System.out.println("Informe o ID do produto que será doado");
		produtoDoado = controller.obterProduto(Console.readInt()); //obtém a referência de um produto a partir da entrada do seu ID
			
		if(produtoDoado == null){ //verifica se a referência obtida é nula
			System.out.println("O ID do produto informado é inválido"); //exibe mensagem informando sobre erro durante a operação
			return; //finaliza o método
		}
		
		
		if(produtoDoado.getTipoProduto().equals("Monetario") == true){ //verifica se o produto a ser doado é do tipo monetário
			System.out.println("Informe o valor que será doado"); 
			quantidadeDoada = Console.readFloat(); //obtém do usuário o valor monetário doado
		} else{
			System.out.println("Informe a quantidade que será doada");
			quantidadeDoada = Console.readInt(); //obtém do usuário a quantidade doada
		}

		
		doacaoEfetuada = controller.efetuarDoacao(doador, produtoDoado, quantidadeDoada, new Date()); //solicita ao controller o cadastro de uma nova doação e obtém a referência para esse cadastro
		
		if(doacaoEfetuada == null){ //verifica se a referência obtida é null
			System.out.println("\nErro ao realizar doação: verifique se as informações digitadas são válidas\n"); //exibe mensagem de erro
		} else{
			System.out.printf("\nA doação foi realizada e cadastrada no sistema com o ID %s\n", doacaoEfetuada.getIdDoacao().toString()); //exibe mensagem informado que a operação foi concluida com sucesso
		}
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de alteração do cadastro de uma doação.
	 *
	 * @throws NumberFormatException sinaliza que houve uma tentativa de converter uma String para um dos tipos numéricos porém a String não possuía o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void realizarAlteracaoDoacao() throws NumberFormatException, IOException{
		Integer idDoacao = null; //id da doação que será alterada
		Produto produto = null; //referência para produto caso o usuário deseje alterar o produto da doação
		Doador doador = null; //referência para doador caso o usuário deseje alterar o doador da doação
		Doacao doacaoAlteracao = null; //referência para a doação alterada
		int opcaoAlterarDoacao = 0; //opção escolhida para alteração da doação
		
		
		System.out.println("Informe o ID da doação que deseja alterar");
		idDoacao = Console.readInt(); //obtém o id da doação
		
		doacaoAlteracao = controller.obterDoacao(idDoacao); //obtém a referência para uma doação a partir do id
		
		if(doacaoAlteracao == null){ //verifica se a referência obtida é null
			System.out.println("O ID da doação informado é inválido"); //informa que o id informado é inválido
			return; //encerra o método 
		}
		
		exibeOpcoesAlterarDoacao(); //exibe opções de alteração da doação
		
		do{
			opcaoAlterarDoacao = Console.readInt(); //obtém a opção pra alteração da doação
			
			if(opcaoAlterarDoacao < 1 || opcaoAlterarDoacao > 4){ //verifica se a opção é inválida
				System.out.println("Opção inválida. Tente novamente."); //exibe mensagem de caso a opção não seja válida
			}
			
		}while(opcaoAlterarDoacao < 1 || opcaoAlterarDoacao > 4); //executa a operação de solicitar o tipo até que a opção inserida seja válida
		
		if(opcaoAlterarDoacao == 1){ //verifica se a opção inserida é 1
			
			do{
				System.out.println("Informe o CPF/CNPJ do novo doador");
				doador = controller.obterDoador(Console.readString()); //obtém a referência de um novo doador para a doação a partir da entrada do seu CPF/CNPJ
				
				if(doador == null){ //verifica se a referência obtida é null
					System.out.println("O CPF/CNPJ informado é inválido. Tente novamente. "); //exibe mensagem de erro
				}
				
			}while(doador == null); //repete a operação até que um CPF/CNPJ válido seja inserido
			
			doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doador, doacaoAlteracao.getProduto(), doacaoAlteracao.getQuantidadeDoada(), doacaoAlteracao.getDataDoacao()); //solicita do controller a alteração dos dados e recebe a referência para a nova doação
			
		} else if(opcaoAlterarDoacao == 2){ //verifica se a opção inserida é 2
			
			do{
				System.out.println("Informe o ID do novo produto");
				produto = controller.obterProduto(Console.readInt()); //obtém a referência de um novo produto para a doação a partir da entrada do seu ID
				
				if(produto == null){ //verifica se a referência obtida é null
					System.out.println("O ID informado é inválido. Tente novamente. "); //exibe mensagem de erro
				}
				
			}while(produto == null); //repete a operação até que um ID válido seja inserido
			
			doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doacaoAlteracao.getDoador(), produto, doacaoAlteracao.getQuantidadeDoada(), doacaoAlteracao.getDataDoacao()); //solicita do controller a alteração dos dados e recebe a referência para a nova doação

		} else if(opcaoAlterarDoacao == 3){ //verifica se a opção inserida é 3
			
			if(doacaoAlteracao.isMonetaria() == true){ //verifica se a doação é do tipo monetária
				System.out.println("Informe o novo valor da doação");
				doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doacaoAlteracao.getDoador(), doacaoAlteracao.getProduto(), Console.readFloat(), doacaoAlteracao.getDataDoacao()); //recebe do usuário o novo valor da doação, solicita do controller a alteração dos dados e recebe a referência para o objeto alterado
			} else if(doacaoAlteracao.isMonetaria() == false){ //verifica se a doação é do tipo não monetária
				System.out.println("Informe a nova quantidade da doação");
				doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doacaoAlteracao.getDoador(), doacaoAlteracao.getProduto(), Console.readInt(), doacaoAlteracao.getDataDoacao()); //recebe do usuário a nova quantidade doada, solicita do controller a alteração dos dados e recebe a referência para o objeto alterado
			}
			
		} else if(opcaoAlterarDoacao == 4){ //verifica se a opção inserida é 4
			System.out.println("Informe a nova data da doação");
			doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doacaoAlteracao.getDoador(), doacaoAlteracao.getProduto(), doacaoAlteracao.getQuantidadeDoada(), PegaData.pegaData()); //recebe a nova data da doação, solicita do controller a alteração dos dados e obtém a referência para o objeto alterado
		}
		
		
		if(doacaoAlteracao == null){ //verifica se foi obtida uma referência null
			System.out.println("Erro ao alterar a doação\n\n"); //exibe mensagem de erro 
		} else{
			System.out.printf("A doação com ID %s foi alterada com sucesso\n\n", doacaoAlteracao.getIdDoacao().toString()); //exibe mensagem informando que a doação ocorreu com sucesso
		}
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de remoção do cadastro de uma doação.
	 *
	 * @throws NumberFormatException sinaliza que houve uma tentativa de converter uma String para um dos tipos numéricos porém a String não possuía o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void efetuarRemocaoDoacao() throws NumberFormatException, IOException{
		Integer idDoacao = null; //id da doação que será removida
		
		System.out.println("Informe o ID da doação que deseja remover");
		idDoacao = Console.readInt(); //obtém o id da doação que se deseja remover
		
		if(controller.removerDoacao(idDoacao) == true){ //verifica se o processo de remoção ocorreu com sucesso
			System.out.println("A doação foi removida com sucesso\n"); //exibe mensagem informado que a doação foi removida
		}else{
			System.out.println("Erro ao remover doação: ID informado é inválido\n"); //exibe mensagem relatando erro durante o processo de remoção
		}
	}
	
	/**
	 * Realiza a interação com o usuário durante a listagem de todas as doações do sistema de maneira ordenada e separadas
	 * em monetárias e não monetárias.
	 */
	public void efetuarListagemDoacoesOrdenadas(){
		Iterador iteradorDoacoes = null; //iterador para a lista de doações
		Doacao doacaoListagem = null; //referência auxiliar para listar doações
		
		
		System.out.println("\n- LISTAGEM COM TODOS AS DOAÇÕES MONETÁRIAS DO SISTEMA ORDENADAS -\n");
	
		
		iteradorDoacoes = this.controller.listarDoacoesOrdenadas(true, null); //solicita do controller o iterador para uma lista ordenada com todas as doações monetárias do sistema 
		
		if(iteradorDoacoes == null){ //verifica se foi obtida uma referência nula para o iterador, isto é, se houve erro durante a ordenação 
			System.out.println("Erro durante a ordenação\n"); //exibe mensagem de erro
		} else{
			
			/* No laço de repetição abaixo a lista é percorrida até que o fim seja alcançado e as informações
			 * referentes às doações monetárias são exibidas.
			 */
			
			while(iteradorDoacoes.temProximo() == true){ //verifica se o fim da lista foi atingido
				doacaoListagem = (Doacao) iteradorDoacoes.obterProximo(); //obtém a referência para uma doação
				
				/* As informações da doação são exibidas na tela. */
				System.out.printf("* DOAÇÃO DE ID %s *\n", doacaoListagem.getIdDoacao().toString());
				System.out.printf("- NOME DO DOADOR: %s\n", doacaoListagem.getDoador().getNomeDoador());
				System.out.printf("- PRODUTO DOADO: %s\n", doacaoListagem.getProduto().getNomeProduto());
				System.out.printf("- QUANTIDADE DOADA: %f\n", doacaoListagem.getQuantidadeDoada().floatValue());
				System.out.printf("- DATA DA DOAÇÃO: %s\n\n", doacaoListagem.getDataDoacao().toString());
			}
		}

		
		System.out.println("\n- LISTAGEM COM TODOS AS DOAÇÕES NÃO MONETÁRIAS DO SISTEMA ORDENADAS -\n");
		
		iteradorDoacoes = this.controller.listarDoacoesOrdenadas(false, null); //solicita do controller o iterador para uma lista ordenada com todas as doações não monetárias do sistema 
		
		if(iteradorDoacoes == null){
			System.out.println("Não há doações não monetárias");
		} else{
			
			/* No laço de repetição abaixo a lista é percorrida até que o fim seja alcançado e as informações
			 * referentes às doações monetárias são exibidas.
			 */
			
			while(iteradorDoacoes.temProximo() == true){ //verifica se o fim da lista foi atingido
				doacaoListagem = (Doacao) iteradorDoacoes.obterProximo(); //obtém a referência para uma doação
				
				/* As informações da doação são exibidas na tela. */

				System.out.printf("* DOAÇÃO DE ID %s *\n", doacaoListagem.getIdDoacao().toString());
				System.out.printf("- NOME DO DOADOR: %s\n", doacaoListagem.getDoador().getNomeDoador());
				System.out.printf("- PRODUTO DOADO: %s\n", doacaoListagem.getProduto().getNomeProduto());
				System.out.printf("- QUANTIDADE DOADA: %d\n", doacaoListagem.getQuantidadeDoada().intValue());
				System.out.printf("- DATA DA DOAÇÃO: %s\n\n", doacaoListagem.getDataDoacao().toString());
			}
		}

		
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de listagem de todas as doações de um doador especificado,
	 * essa listagem é separada em doações monetárias e não monetárias.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void efetuarListagemDoacoesDeUmDoador() throws IOException{
		Iterador iteradorDoacoesDeDoador = null; //iterador para percorrer uma lista de doacoes
		String numCadastro = null; //CPF/CNPJ do doador que se deseja obter as doações
		Doacao doacaoListagem = null; //referência auxiliar para o processo de exibir uma doação
		Doador auxiliarDoador = null; //referência auxiliar para doador utilizada para buscar as doações
		
		System.out.println("Informe o CPF/CNPJ do doador");
		numCadastro = Console.readString(); //obtém o CPF/CNPJ desejado
		
		auxiliarDoador = this.controller.obterDoador(numCadastro); //obtém a referência para um doador
		
		if(auxiliarDoador == null){ //verifica se a referência obtida é null, istto é, se o CPF/CNPJ informado é inválido
			System.out.println("Impossível listar doações: o CPF/CNPJ informado não é válido"); //exibe uma mensagem de erro na tela
			return; //encerra o método
		}
		
		iteradorDoacoesDeDoador = this.controller.listarDoacoesOrdenadas(true, auxiliarDoador); //solicita do controller o iterador para uma lista de doações monetárias do doador especificado
		if(iteradorDoacoesDeDoador == null){ //verifica se foi possível obter um iterador, isto é, se a ordenação ocorreu corretamente
			System.out.println("Erro ao realizar ordenação"); //exibe mensagem de erro 
			return; //encerra o método
		}
		
		if(iteradorDoacoesDeDoador.temProximo() == false){ //verifica se o iterador obtido é de uma lista vazia, isto é, se não há doações monetárias do doador informado
			System.out.println("Não há doações monetárias cadastradas para o doador informado \n"); //exibe mensagem de erro
		} else{
			
			System.out.printf("\n* DOAÇÕES MONÉTÁRIAS DO DOADOR %s | CPF/CPNPJ: %s *\n", auxiliarDoador.getNomeDoador(), auxiliarDoador.getNumCadastroDoador());

			/* No laço de repetição abaixo a lista é percorrida até que o fim seja alcançado e as informações
			 * referentes às doações monetárias do doador especificado são exibidas.
			 */
			
			while(iteradorDoacoesDeDoador.temProximo() == true){ //verifica se ainda há elementos na lista, isto é, se não chegou ainda no fim
				doacaoListagem = (Doacao) iteradorDoacoesDeDoador.obterProximo(); //obtém a referência para a próxima doação da lista
				
				/* As informações da doação são exibidas na tela. */

				System.out.printf("- ID DA DOAÇÃO: %s\n", doacaoListagem.getIdDoacao().toString());
				System.out.printf("- PRODUTO DOADO: %s\n", doacaoListagem.getProduto().getNomeProduto());
				System.out.printf("- QUANTIDADE DOADA: %s\n", doacaoListagem.getQuantidadeDoada().floatValue());
				System.out.printf("- DATA DA DOAÇÃO: %s\n\n", doacaoListagem.getDataDoacao().toString());
			}
		}
		
	

		
		iteradorDoacoesDeDoador = this.controller.listarDoacoesOrdenadas(false, auxiliarDoador); //solicita do controller o iterador para uma lista de doações não monetárias do doador especificado
	
		if(iteradorDoacoesDeDoador == null){ //verifica se foi possível obter um iterador, isto é, se a ordenação ocorreu corretamente
			System.out.println("Erro ao realizar ordenação"); //exibe mensagem de erro
			return; //encerra o método
		}
		
		if(iteradorDoacoesDeDoador.temProximo() == false){ //verifica se o iterador obtido é de uma lista vazia, isto é, se não há doações não monetárias do doador informado
			System.out.println("Não há doações não monetárias cadastradas para o doador informado \n"); //exibe mensagem de erro
		} else{
			
			System.out.printf("\n* DOAÇÕES NÃO MONÉTARIAS DO DOADOR %s | CPF/CPNPJ: %s *\n", auxiliarDoador.getNomeDoador(), auxiliarDoador.getNumCadastroDoador());

			/* No laço de repetição abaixo a lista é percorrida até que o fim seja alcançado e as informações
			 * referentes às doações não monetárias do doador especificado são exibidas.
			 */
			
			while(iteradorDoacoesDeDoador.temProximo() == true){ //verifica se ainda há elementos na lista, isto é, se não chegou ainda no fim
				doacaoListagem = (Doacao) iteradorDoacoesDeDoador.obterProximo();//obtém a referência para a próxima doação da lista
			
				/* As informações da doação são exibidas na tela. */

				System.out.printf("- ID DA DOAÇÃO: %s\n", doacaoListagem.getIdDoacao().toString());
				System.out.printf("- PRODUTO DOADO: %s\n", doacaoListagem.getProduto().getNomeProduto());
				System.out.printf("- QUANTIDADE DOADA: %s\n", doacaoListagem.getQuantidadeDoada().intValue());
				System.out.printf("- DATA DA DOAÇÃO: %s\n\n", doacaoListagem.getDataDoacao().toString());
			}
		}
		

	}

}
