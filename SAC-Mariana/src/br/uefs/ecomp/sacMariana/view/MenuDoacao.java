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
 * Classe respons�vel por moldar um menu com as op��es dispon�veis para gerenciamento de doa��es no sistema e exibir
 * das principais telas utilizadas nesse processo. 
 * 
 * @author Valmir Vinicius
 *
 */
public class MenuDoacao {
	
	/** Controller respons�vel por efetivar as solicita��es realizadas. */
	private SACMarianaController controller;
	
	/**
	 * Constr�i um menu para gerenciamento de doa��es e atribui ao atributo "controller" uma refer�ncia de Controller 
	 * j� instanciado. 
	 *	
	 * @param controller refer�ncia para o controller do sistema
	 */
	public MenuDoacao(SACMarianaController controller){
		this.controller = controller; //atribui a refer�nca do controller ao atributo "controller"
	}
	
	/**
	 * M�todo est�tico que exibe as op��es iniciais para gerenciamento de doa��es no sistema.
	 */
	public static void exibeOpcoesIniciasDoacao(){
		/* As op��es iniciais s�o mostradas na tela. */
		
		System.out.println("O que voc� deseja fazer?");
		System.out.println("1- Efetuar doa��o");
		System.out.println("2- Alterar doa��o");
		System.out.println("3- Remover doa��o");
		System.out.println("4- Listar doa��es ordenadas");
		System.out.println("5- Listar doa��es de um doador");
	}
	
	/**
	 * M�todo est�tico que exibe as op��es para altera��o no cadastro de uma doa��o.
	 */
	private static void exibeOpcoesAlterarDoacao(){
		/* As op��es para altera��o do cadastro de uma doa��o s�o exibidas na tela. */
		
		System.out.println("Qual a informa��o da doa��o deseja alterar?");
		System.out.println("1- Doador");
		System.out.println("2- Produto");
		System.out.println("3- Quantidade doada");
		System.out.println("4- Data da doa��o");
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de cadastro de uma doa��o.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void realizacaoDeDoacao() throws IOException{
		Doador doador = null; //refer�ncia para o doador que realizar� a doa��o
		Produto produtoDoado = null; //refer�ncia para o produto que ser� doado
		Number quantidadeDoada = null; //quantidade que ser� doada
		Doacao doacaoEfetuada = null; //refer�ncia para a doa��o realizada
		

		System.out.println("Informe o CPF/CNPJ do doador que far� a doa��o");
		doador = controller.obterDoador(Console.readString()); //obt�m a refer�ncia de um doador a partir da entrada do seu CPF/CNPJ
			
		if(doador == null){ //verifica se a refer�ncia obtida � nula
			System.out.println("O CPF/CNPJ informado � inv�lido"); //exibe mensagem informando sobre erro durante a opera��o
			return; //finaliza o m�todo
		}
	
		
		System.out.println("Informe o ID do produto que ser� doado");
		produtoDoado = controller.obterProduto(Console.readInt()); //obt�m a refer�ncia de um produto a partir da entrada do seu ID
			
		if(produtoDoado == null){ //verifica se a refer�ncia obtida � nula
			System.out.println("O ID do produto informado � inv�lido"); //exibe mensagem informando sobre erro durante a opera��o
			return; //finaliza o m�todo
		}
		
		
		if(produtoDoado.getTipoProduto().equals("Monetario") == true){ //verifica se o produto a ser doado � do tipo monet�rio
			System.out.println("Informe o valor que ser� doado"); 
			quantidadeDoada = Console.readFloat(); //obt�m do usu�rio o valor monet�rio doado
		} else{
			System.out.println("Informe a quantidade que ser� doada");
			quantidadeDoada = Console.readInt(); //obt�m do usu�rio a quantidade doada
		}

		
		doacaoEfetuada = controller.efetuarDoacao(doador, produtoDoado, quantidadeDoada, new Date()); //solicita ao controller o cadastro de uma nova doa��o e obt�m a refer�ncia para esse cadastro
		
		if(doacaoEfetuada == null){ //verifica se a refer�ncia obtida � null
			System.out.println("\nErro ao realizar doa��o: verifique se as informa��es digitadas s�o v�lidas\n"); //exibe mensagem de erro
		} else{
			System.out.printf("\nA doa��o foi realizada e cadastrada no sistema com o ID %s\n", doacaoEfetuada.getIdDoacao().toString()); //exibe mensagem informado que a opera��o foi concluida com sucesso
		}
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de altera��o do cadastro de uma doa��o.
	 *
	 * @throws NumberFormatException sinaliza que houve uma tentativa de converter uma String para um dos tipos num�ricos por�m a String n�o possu�a o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void realizarAlteracaoDoacao() throws NumberFormatException, IOException{
		Integer idDoacao = null; //id da doa��o que ser� alterada
		Produto produto = null; //refer�ncia para produto caso o usu�rio deseje alterar o produto da doa��o
		Doador doador = null; //refer�ncia para doador caso o usu�rio deseje alterar o doador da doa��o
		Doacao doacaoAlteracao = null; //refer�ncia para a doa��o alterada
		int opcaoAlterarDoacao = 0; //op��o escolhida para altera��o da doa��o
		
		
		System.out.println("Informe o ID da doa��o que deseja alterar");
		idDoacao = Console.readInt(); //obt�m o id da doa��o
		
		doacaoAlteracao = controller.obterDoacao(idDoacao); //obt�m a refer�ncia para uma doa��o a partir do id
		
		if(doacaoAlteracao == null){ //verifica se a refer�ncia obtida � null
			System.out.println("O ID da doa��o informado � inv�lido"); //informa que o id informado � inv�lido
			return; //encerra o m�todo 
		}
		
		exibeOpcoesAlterarDoacao(); //exibe op��es de altera��o da doa��o
		
		do{
			opcaoAlterarDoacao = Console.readInt(); //obt�m a op��o pra altera��o da doa��o
			
			if(opcaoAlterarDoacao < 1 || opcaoAlterarDoacao > 4){ //verifica se a op��o � inv�lida
				System.out.println("Op��o inv�lida. Tente novamente."); //exibe mensagem de caso a op��o n�o seja v�lida
			}
			
		}while(opcaoAlterarDoacao < 1 || opcaoAlterarDoacao > 4); //executa a opera��o de solicitar o tipo at� que a op��o inserida seja v�lida
		
		if(opcaoAlterarDoacao == 1){ //verifica se a op��o inserida � 1
			
			do{
				System.out.println("Informe o CPF/CNPJ do novo doador");
				doador = controller.obterDoador(Console.readString()); //obt�m a refer�ncia de um novo doador para a doa��o a partir da entrada do seu CPF/CNPJ
				
				if(doador == null){ //verifica se a refer�ncia obtida � null
					System.out.println("O CPF/CNPJ informado � inv�lido. Tente novamente. "); //exibe mensagem de erro
				}
				
			}while(doador == null); //repete a opera��o at� que um CPF/CNPJ v�lido seja inserido
			
			doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doador, doacaoAlteracao.getProduto(), doacaoAlteracao.getQuantidadeDoada(), doacaoAlteracao.getDataDoacao()); //solicita do controller a altera��o dos dados e recebe a refer�ncia para a nova doa��o
			
		} else if(opcaoAlterarDoacao == 2){ //verifica se a op��o inserida � 2
			
			do{
				System.out.println("Informe o ID do novo produto");
				produto = controller.obterProduto(Console.readInt()); //obt�m a refer�ncia de um novo produto para a doa��o a partir da entrada do seu ID
				
				if(produto == null){ //verifica se a refer�ncia obtida � null
					System.out.println("O ID informado � inv�lido. Tente novamente. "); //exibe mensagem de erro
				}
				
			}while(produto == null); //repete a opera��o at� que um ID v�lido seja inserido
			
			doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doacaoAlteracao.getDoador(), produto, doacaoAlteracao.getQuantidadeDoada(), doacaoAlteracao.getDataDoacao()); //solicita do controller a altera��o dos dados e recebe a refer�ncia para a nova doa��o

		} else if(opcaoAlterarDoacao == 3){ //verifica se a op��o inserida � 3
			
			if(doacaoAlteracao.isMonetaria() == true){ //verifica se a doa��o � do tipo monet�ria
				System.out.println("Informe o novo valor da doa��o");
				doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doacaoAlteracao.getDoador(), doacaoAlteracao.getProduto(), Console.readFloat(), doacaoAlteracao.getDataDoacao()); //recebe do usu�rio o novo valor da doa��o, solicita do controller a altera��o dos dados e recebe a refer�ncia para o objeto alterado
			} else if(doacaoAlteracao.isMonetaria() == false){ //verifica se a doa��o � do tipo n�o monet�ria
				System.out.println("Informe a nova quantidade da doa��o");
				doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doacaoAlteracao.getDoador(), doacaoAlteracao.getProduto(), Console.readInt(), doacaoAlteracao.getDataDoacao()); //recebe do usu�rio a nova quantidade doada, solicita do controller a altera��o dos dados e recebe a refer�ncia para o objeto alterado
			}
			
		} else if(opcaoAlterarDoacao == 4){ //verifica se a op��o inserida � 4
			System.out.println("Informe a nova data da doa��o");
			doacaoAlteracao = controller.alterarDoacao(doacaoAlteracao.getIdDoacao(), doacaoAlteracao.getDoador(), doacaoAlteracao.getProduto(), doacaoAlteracao.getQuantidadeDoada(), PegaData.pegaData()); //recebe a nova data da doa��o, solicita do controller a altera��o dos dados e obt�m a refer�ncia para o objeto alterado
		}
		
		
		if(doacaoAlteracao == null){ //verifica se foi obtida uma refer�ncia null
			System.out.println("Erro ao alterar a doa��o\n\n"); //exibe mensagem de erro 
		} else{
			System.out.printf("A doa��o com ID %s foi alterada com sucesso\n\n", doacaoAlteracao.getIdDoacao().toString()); //exibe mensagem informando que a doa��o ocorreu com sucesso
		}
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de remo��o do cadastro de uma doa��o.
	 *
	 * @throws NumberFormatException sinaliza que houve uma tentativa de converter uma String para um dos tipos num�ricos por�m a String n�o possu�a o formato adequado
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void efetuarRemocaoDoacao() throws NumberFormatException, IOException{
		Integer idDoacao = null; //id da doa��o que ser� removida
		
		System.out.println("Informe o ID da doa��o que deseja remover");
		idDoacao = Console.readInt(); //obt�m o id da doa��o que se deseja remover
		
		if(controller.removerDoacao(idDoacao) == true){ //verifica se o processo de remo��o ocorreu com sucesso
			System.out.println("A doa��o foi removida com sucesso\n"); //exibe mensagem informado que a doa��o foi removida
		}else{
			System.out.println("Erro ao remover doa��o: ID informado � inv�lido\n"); //exibe mensagem relatando erro durante o processo de remo��o
		}
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante a listagem de todas as doa��es do sistema de maneira ordenada e separadas
	 * em monet�rias e n�o monet�rias.
	 */
	public void efetuarListagemDoacoesOrdenadas(){
		Iterador iteradorDoacoes = null; //iterador para a lista de doa��es
		Doacao doacaoListagem = null; //refer�ncia auxiliar para listar doa��es
		
		
		System.out.println("\n- LISTAGEM COM TODOS AS DOA��ES MONET�RIAS DO SISTEMA ORDENADAS -\n");
	
		
		iteradorDoacoes = this.controller.listarDoacoesOrdenadas(true, null); //solicita do controller o iterador para uma lista ordenada com todas as doa��es monet�rias do sistema 
		
		if(iteradorDoacoes == null){ //verifica se foi obtida uma refer�ncia nula para o iterador, isto �, se houve erro durante a ordena��o 
			System.out.println("Erro durante a ordena��o\n"); //exibe mensagem de erro
		} else{
			
			/* No la�o de repeti��o abaixo a lista � percorrida at� que o fim seja alcan�ado e as informa��es
			 * referentes �s doa��es monet�rias s�o exibidas.
			 */
			
			while(iteradorDoacoes.temProximo() == true){ //verifica se o fim da lista foi atingido
				doacaoListagem = (Doacao) iteradorDoacoes.obterProximo(); //obt�m a refer�ncia para uma doa��o
				
				/* As informa��es da doa��o s�o exibidas na tela. */
				System.out.printf("* DOA��O DE ID %s *\n", doacaoListagem.getIdDoacao().toString());
				System.out.printf("- NOME DO DOADOR: %s\n", doacaoListagem.getDoador().getNomeDoador());
				System.out.printf("- PRODUTO DOADO: %s\n", doacaoListagem.getProduto().getNomeProduto());
				System.out.printf("- QUANTIDADE DOADA: %f\n", doacaoListagem.getQuantidadeDoada().floatValue());
				System.out.printf("- DATA DA DOA��O: %s\n\n", doacaoListagem.getDataDoacao().toString());
			}
		}

		
		System.out.println("\n- LISTAGEM COM TODOS AS DOA��ES N�O MONET�RIAS DO SISTEMA ORDENADAS -\n");
		
		iteradorDoacoes = this.controller.listarDoacoesOrdenadas(false, null); //solicita do controller o iterador para uma lista ordenada com todas as doa��es n�o monet�rias do sistema 
		
		if(iteradorDoacoes == null){
			System.out.println("N�o h� doa��es n�o monet�rias");
		} else{
			
			/* No la�o de repeti��o abaixo a lista � percorrida at� que o fim seja alcan�ado e as informa��es
			 * referentes �s doa��es monet�rias s�o exibidas.
			 */
			
			while(iteradorDoacoes.temProximo() == true){ //verifica se o fim da lista foi atingido
				doacaoListagem = (Doacao) iteradorDoacoes.obterProximo(); //obt�m a refer�ncia para uma doa��o
				
				/* As informa��es da doa��o s�o exibidas na tela. */

				System.out.printf("* DOA��O DE ID %s *\n", doacaoListagem.getIdDoacao().toString());
				System.out.printf("- NOME DO DOADOR: %s\n", doacaoListagem.getDoador().getNomeDoador());
				System.out.printf("- PRODUTO DOADO: %s\n", doacaoListagem.getProduto().getNomeProduto());
				System.out.printf("- QUANTIDADE DOADA: %d\n", doacaoListagem.getQuantidadeDoada().intValue());
				System.out.printf("- DATA DA DOA��O: %s\n\n", doacaoListagem.getDataDoacao().toString());
			}
		}

		
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de listagem de todas as doa��es de um doador especificado,
	 * essa listagem � separada em doa��es monet�rias e n�o monet�rias.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void efetuarListagemDoacoesDeUmDoador() throws IOException{
		Iterador iteradorDoacoesDeDoador = null; //iterador para percorrer uma lista de doacoes
		String numCadastro = null; //CPF/CNPJ do doador que se deseja obter as doa��es
		Doacao doacaoListagem = null; //refer�ncia auxiliar para o processo de exibir uma doa��o
		Doador auxiliarDoador = null; //refer�ncia auxiliar para doador utilizada para buscar as doa��es
		
		System.out.println("Informe o CPF/CNPJ do doador");
		numCadastro = Console.readString(); //obt�m o CPF/CNPJ desejado
		
		auxiliarDoador = this.controller.obterDoador(numCadastro); //obt�m a refer�ncia para um doador
		
		if(auxiliarDoador == null){ //verifica se a refer�ncia obtida � null, istto �, se o CPF/CNPJ informado � inv�lido
			System.out.println("Imposs�vel listar doa��es: o CPF/CNPJ informado n�o � v�lido"); //exibe uma mensagem de erro na tela
			return; //encerra o m�todo
		}
		
		iteradorDoacoesDeDoador = this.controller.listarDoacoesOrdenadas(true, auxiliarDoador); //solicita do controller o iterador para uma lista de doa��es monet�rias do doador especificado
		if(iteradorDoacoesDeDoador == null){ //verifica se foi poss�vel obter um iterador, isto �, se a ordena��o ocorreu corretamente
			System.out.println("Erro ao realizar ordena��o"); //exibe mensagem de erro 
			return; //encerra o m�todo
		}
		
		if(iteradorDoacoesDeDoador.temProximo() == false){ //verifica se o iterador obtido � de uma lista vazia, isto �, se n�o h� doa��es monet�rias do doador informado
			System.out.println("N�o h� doa��es monet�rias cadastradas para o doador informado \n"); //exibe mensagem de erro
		} else{
			
			System.out.printf("\n* DOA��ES MON�T�RIAS DO DOADOR %s | CPF/CPNPJ: %s *\n", auxiliarDoador.getNomeDoador(), auxiliarDoador.getNumCadastroDoador());

			/* No la�o de repeti��o abaixo a lista � percorrida at� que o fim seja alcan�ado e as informa��es
			 * referentes �s doa��es monet�rias do doador especificado s�o exibidas.
			 */
			
			while(iteradorDoacoesDeDoador.temProximo() == true){ //verifica se ainda h� elementos na lista, isto �, se n�o chegou ainda no fim
				doacaoListagem = (Doacao) iteradorDoacoesDeDoador.obterProximo(); //obt�m a refer�ncia para a pr�xima doa��o da lista
				
				/* As informa��es da doa��o s�o exibidas na tela. */

				System.out.printf("- ID DA DOA��O: %s\n", doacaoListagem.getIdDoacao().toString());
				System.out.printf("- PRODUTO DOADO: %s\n", doacaoListagem.getProduto().getNomeProduto());
				System.out.printf("- QUANTIDADE DOADA: %s\n", doacaoListagem.getQuantidadeDoada().floatValue());
				System.out.printf("- DATA DA DOA��O: %s\n\n", doacaoListagem.getDataDoacao().toString());
			}
		}
		
	

		
		iteradorDoacoesDeDoador = this.controller.listarDoacoesOrdenadas(false, auxiliarDoador); //solicita do controller o iterador para uma lista de doa��es n�o monet�rias do doador especificado
	
		if(iteradorDoacoesDeDoador == null){ //verifica se foi poss�vel obter um iterador, isto �, se a ordena��o ocorreu corretamente
			System.out.println("Erro ao realizar ordena��o"); //exibe mensagem de erro
			return; //encerra o m�todo
		}
		
		if(iteradorDoacoesDeDoador.temProximo() == false){ //verifica se o iterador obtido � de uma lista vazia, isto �, se n�o h� doa��es n�o monet�rias do doador informado
			System.out.println("N�o h� doa��es n�o monet�rias cadastradas para o doador informado \n"); //exibe mensagem de erro
		} else{
			
			System.out.printf("\n* DOA��ES N�O MON�TARIAS DO DOADOR %s | CPF/CPNPJ: %s *\n", auxiliarDoador.getNomeDoador(), auxiliarDoador.getNumCadastroDoador());

			/* No la�o de repeti��o abaixo a lista � percorrida at� que o fim seja alcan�ado e as informa��es
			 * referentes �s doa��es n�o monet�rias do doador especificado s�o exibidas.
			 */
			
			while(iteradorDoacoesDeDoador.temProximo() == true){ //verifica se ainda h� elementos na lista, isto �, se n�o chegou ainda no fim
				doacaoListagem = (Doacao) iteradorDoacoesDeDoador.obterProximo();//obt�m a refer�ncia para a pr�xima doa��o da lista
			
				/* As informa��es da doa��o s�o exibidas na tela. */

				System.out.printf("- ID DA DOA��O: %s\n", doacaoListagem.getIdDoacao().toString());
				System.out.printf("- PRODUTO DOADO: %s\n", doacaoListagem.getProduto().getNomeProduto());
				System.out.printf("- QUANTIDADE DOADA: %s\n", doacaoListagem.getQuantidadeDoada().intValue());
				System.out.printf("- DATA DA DOA��O: %s\n\n", doacaoListagem.getDataDoacao().toString());
			}
		}
		

	}

}
