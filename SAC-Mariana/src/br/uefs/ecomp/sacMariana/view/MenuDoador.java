package br.uefs.ecomp.sacMariana.view;
import java.io.IOException;
import java.util.Date;
import br.uefs.ecomp.sacMariana.controller.SACMarianaController;
import br.uefs.ecomp.sacMariana.model.Doador;
import br.uefs.ecomp.sacMariana.util.Console;
import br.uefs.ecomp.sacMariana.util.Iterador;
import br.uefs.ecomp.sacMariana.util.PegaData;

/**
 * Classe responsável por moldar um menu com as opções disponíveis para gerenciamento de doadores no sistema e exibir
 * das principais telas utilizadas nesse processo. 
 * 
 * @author Valmir Vinicius
 */
public class MenuDoador {
	
	/** Controller responsável por efetivar as solicitações realizadas. */
	private SACMarianaController controller;
	
	/**
	 * Constrói um menu para gerenciamento de doadores e atribui ao atributo "controller" uma referência de Controller 
	 * já instanciado. 
	 *
	 * @param controller referência para o controller do sistema
	 */
	public MenuDoador(SACMarianaController controller){
		this.controller = controller; //atribui a referênca do controller ao atributo "controller"
	}
	
	/**
	 * Método estático que exibe as opções iniciais para gerenciamento de doadores no sistema.
	 */
	public static void exibeOpcoesIniciasDoador(){
		/* As opções iniciais são mostradas na tela. */
		System.out.println("O que você deseja fazer?");
		System.out.println("1- Cadastrar doador");
		System.out.println("2- Alterar informações de um doador cadastrado");
		System.out.println("3- Remover doador");
		System.out.println("4- Listar todos os doadores do sistema");
		System.out.println("5- Listar todos os doadores do sistema com respectivo percentual de doações");
	}
	
	/**
	 * Método estático que exibe as opções de tipos de doadores
	 */
	public static void exibeOpcoesTiposDeDoadores(){
		/* As opções de tipos de doadores são mostradas na tela. */
		System.out.println("Qual o tipo do doador?");
		System.out.println("1- Pessoa física");
		System.out.println("2- Pessoa juridíca");
	}
	
	/**
	 * Método estático que exibe as opções para alteração no cadastro de um doador.
	 */
	public static void exibeOpcoesAlterarDoador(){
		/* As opções para alteração do cadastro de um doador são exibidas na tela. */
		System.out.println("Qual informação do doador deseja alterar?");
		System.out.println("1- Nome");
		System.out.println("2- Data de nascimento");
		System.out.println("3- Tipo");
		System.out.println("4- Endereço");
	}
	
	/**
	 * Método estático utilizado para obter uma String contendo o tipo de doador a partir de uma opção 
	 * inserida pelo usuário.
	 *
	 * @param auxiliarTipoDoador opcao de tipo de doador escolhida pelo usuário
	 * @return String contendo o tipo de doador, se a opção inserida for válida; <code>null</code>, caso contrário.

	 */
	public String tipoDoadorEscolhido(int auxiliarTipoDoador){
		if(auxiliarTipoDoador == 1){ //verifica se a opcao foi 1
			return "Pessoa fisica"; //retorna uma string contendo o tipo "Pessoa fisica"
		} else if(auxiliarTipoDoador == 2){ //verifica se a opcao foi 2
			return "Pessoa juridica"; //retorna uma string contendo o tipo "Pessoa juridica"
		} else{ //verifica se foi informada uma opção que não condiz com nenhum dos casos
			return null; //retorna uma referência null
		} 
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de cadastro de um doador.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void realizacaoCadastroDoador() throws IOException{
		String nomeDoador = null, numCadastro = null; //Strings para auxiliar no processo de obtenção do nome do doador e do CPF/CNPJ, respectivamente
		int opcaoTipoDoador = 0; //opção de tipo de doador escolhida pelo usuário
		Date dataNascDoador = null; //data de nascimento/fundação do doador
		Doador doadorCadastro = null; //referência para o cadastro de doador realizado
		
		System.out.println("Informe o CPF ou CNPJ do doador que será cadastrado");
		numCadastro = Console.readString(); //recebe o CPF/CNPJ do doador informado pelo usuário

		System.out.println("Informe o nome do doador que deseja cadastrar");
		nomeDoador = Console.readString(); //recebe o nome do doador informado pelo usuário
		
		System.out.println("Informe o tipo de doador que deseja cadastrar");
		exibeOpcoesTiposDeDoadores(); //exibe as opções de tipos de doador disponíveis
		do{
			opcaoTipoDoador = Console.readInt(); //recebe a opção de tipo de doador escolhida pelo usuário
			
			if(opcaoTipoDoador < 1 || opcaoTipoDoador > 2){ //verifica se a opção é válida
				System.out.println("Tipo de doador escolhido é inválido. Tente novamente\n"); //exibe mensagem de erro em caso de valor inválido
			}
			
		}while(opcaoTipoDoador < 1 || opcaoTipoDoador > 2); //executa a operação de solicitar a opção do usuário até que o valor inserido seja válido
		
		System.out.println("Informe data de nascimento ou fundação");
		dataNascDoador = PegaData.pegaData(); //recebe a data de nascimento/fundação do usuário
		
		System.out.printf("Informe, respectivamente, os dados do endereço do doador: Rua, Número, Bairro, CEP, Cidade, Estdo, Pais\n");

		doadorCadastro =  controller.cadastrarDoador(numCadastro, nomeDoador, dataNascDoador, Console.readString(), Console.readInt(), Console.readString(), Console.readString(), Console.readString(), Console.readString(), Console.readString(), tipoDoadorEscolhido(opcaoTipoDoador)); //recebe do usuário as informações do endereço, envia uma solicitação de cadastro de doador para o controller, recebe a referência para o objeto doador instanciado
		
		if(doadorCadastro == null){ //verifica se referência obtida foi null
			System.out.println("Já existe no sistema um doador com o CPF/CNPJ informado\n\n"); //caso a referência obtida seja null exibe mensagem de erro
		} else{
			System.out.printf("O doador %s com CPF/CNPJ %s foi cadastrado com sucesso\n\n", doadorCadastro.getNomeDoador(), doadorCadastro.getNumCadastroDoador()); //exibe mensagem informado que o doador foi cadastrado com sucesso
		}
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de alteração do cadastro de um doador.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void alteracaoCadastroDoador() throws IOException{
		String numCadastro = null; //CPF/CNPJ do usuário que terá as suas informações alteradas
		int opcaoAlteracao = 0, opcaoTipoDoador = 0; //opção para alteração e opção para tipo de doador, respectivamente
		Doador doadorAlteracao = null; //referência para o doador que será alterado
		
		System.out.println("Informe o CPF/CNPJ do doador\n");
		numCadastro = Console.readString(); //recebe o CPF/CNPJ do doador que será alterado
		
		doadorAlteracao = controller.obterDoador(numCadastro); //obtém a referência para o doador que será alterado
		
		if(doadorAlteracao == null){ //verifica se a referência obtida foi null
			System.out.printf("Não existe um doador com o CPF/CNPJ informado\n"); //exibe mensagem de erro indicando que não foi possível localizar o doador com o CPF/CNPJ informado
			return; //encerra o método
		}
		
		exibeOpcoesAlterarDoador(); //exibe as opções disponíveis para alteração do cadastro do doador
		opcaoAlteracao = Console.readInt(); //recebe do usuário a opção para a alteração
		
		if(opcaoAlteracao == 1){ //verifica se a opção informada foi 1
			System.out.println("Informe o novo nome do doador\n");
			doadorAlteracao = controller.editarDoador(doadorAlteracao.getNumCadastroDoador(), Console.readString(), doadorAlteracao.getDataNasc(), doadorAlteracao.getEndereco().getRua(), doadorAlteracao.getEndereco().getNumero(), doadorAlteracao.getEndereco().getBairro(), doadorAlteracao.getEndereco().getCep(), doadorAlteracao.getEndereco().getCidade(), doadorAlteracao.getEndereco().getEstado(), doadorAlteracao.getEndereco().getPais(), doadorAlteracao.getTipoDoador()); //recebe do usuário o novo nome do doador, solicita do controller a alteração dos dados e recebe a referência para o objeto após a alteração
		} else if(opcaoAlteracao == 2){ //verifica se a opção informada foi 2
			System.out.println("Informe a nova data de nascimento ou fundação");
			doadorAlteracao = controller.editarDoador(doadorAlteracao.getNumCadastroDoador(), doadorAlteracao.getNomeDoador(), PegaData.pegaData(), doadorAlteracao.getEndereco().getRua(), doadorAlteracao.getEndereco().getNumero(), doadorAlteracao.getEndereco().getBairro(), doadorAlteracao.getEndereco().getCep(), doadorAlteracao.getEndereco().getCidade(), doadorAlteracao.getEndereco().getEstado(), doadorAlteracao.getEndereco().getPais(), doadorAlteracao.getTipoDoador());  //recebe do usuário a nova data de nascimento/fundação do doador, solicita do controller a alteração dos dados e recebe a referência para o objeto após a alteração
		} else if(opcaoAlteracao == 3){ //verifica se a opção informada foi 3
			System.out.println("Alterar tipo de doador");
			exibeOpcoesTiposDeDoadores(); //exibe as opções de tipos de doadores disponíveis 
	
			do{
				opcaoTipoDoador = Console.readInt(); //recebe do usuário a opção para alteração do tipo de doador
				
				if(opcaoTipoDoador < 1 || opcaoTipoDoador > 2){ //verifica se a opção informada é válida
					System.out.println("Tipo de doador escolhido é inválido. Tente novamente\n"); //exibe mensagem de erro
				}
			}while(opcaoTipoDoador < 1 || opcaoTipoDoador > 2); //executa a operação de solicitar o tipo até que o valor inserido seja válido
			
			doadorAlteracao = controller.editarDoador(doadorAlteracao.getNumCadastroDoador(), doadorAlteracao.getNomeDoador(), doadorAlteracao.getDataNasc(), doadorAlteracao.getEndereco().getRua(), doadorAlteracao.getEndereco().getNumero(), doadorAlteracao.getEndereco().getBairro(), doadorAlteracao.getEndereco().getCep(), doadorAlteracao.getEndereco().getCidade(), doadorAlteracao.getEndereco().getEstado(), doadorAlteracao.getEndereco().getPais(), this.tipoDoadorEscolhido(opcaoTipoDoador)); //obtém a String contendo o tipo de doador por meio da opção do usuário, solicita do controller a alteração dos dados e recebe a referência para o objeto após a alteração
		} else if(opcaoAlteracao == 4){ //verifica se a opção informada foi 4
			System.out.println("Informe respectivamente as novas informações do endereço: Rua, Número, Bairro, CEP, Cidade, Estado, País\n");
			doadorAlteracao = controller.editarDoador(doadorAlteracao.getNumCadastroDoador(), doadorAlteracao.getNomeDoador(), doadorAlteracao.getDataNasc(), Console.readString(), Console.readInt(), Console.readString(), Console.readString(), Console.readString(), Console.readString(), Console.readString(), doadorAlteracao.getTipoDoador()); //obtém as novas informações do endereço, solicita do controller a alteração do cadastro do doador e recebe a referência do doador alterado
		} else{ //verifica se a entrada não condiz com nenhuma das condições anteriores
			System.out.println("Opção inválida\n\n"); //exibe uma mensagem avisando sobre a entrada inválida
			return; //finaliza o método
		}
		
		if(doadorAlteracao == null){ //verifica se a referência obtida foi null
			System.out.println("Não existe um doador com o CPF/CNPJ informado"); //exibe mensagem informado que o CPF/CNPJ é inválido
		} else{
			System.out.printf("O cadastro do doador de CPF/CNPJ: %s foi alterado com sucesso\n\n", doadorAlteracao.getNumCadastroDoador()); //exibe mensagem informando que a operação ocorreu com sucesso
		}
		
	}
	
	/**
	 * Realiza a interação com o usuário durante o processo de remoção do cadastro de um doador.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exceção I/O
	 */
	public void efetuarRemocaoDoador() throws IOException{
		String numCadastro = null; //CPF/CNPJ do doador que será removido
		
		System.out.println("Informe o CPF/CNPJ do doador que deseja remover do sistema");
		numCadastro = Console.readString(); //recebe o CPF/CNPJ do doador que será removido
		
		if(controller.removerDoador(numCadastro) == true){ //solicita do controller a remoção e verifica se o processo ocorreu com sucesso
			System.out.println("O cadastro do doador foi removido com sucesso\n\n"); //exibe mensagem informado que o cadastro do doador foi removido
		} else{
			System.out.println("Impossivel remover cadastro do doador: o doador já realizou uma doação ou o CPF/CNPJ informado é inválido"); //exibe mensagem informado erro no processo
		}
	}
	
	/**
	 * Realiza a interação com o usuário durante a listagem de todos os doadores cadastrados no sistema.
	 */
	public void realizarListagemDoadores(){
		Iterador iteradorDoadores = null; //iterador para percorrer a lista de doadores
		Doador doadorListagem = null; //referência auxiliar para exibição das informações do doador
		
		iteradorDoadores = this.controller.listarDoadores(); //obtém o iterador da lista
		
		if(iteradorDoadores.temProximo() == false){ //verifica se a lista está vazia
			System.out.println("Não há cadastro de doadores no sistema\n"); //exibe mensagem indicando que não há doadores cadastrados
			return;
		} else{
			System.out.println("\n- LISTAGEM DE TODOS OS DOADORES DO SISTEMA -\n");
			
			/* O laço de repetição abaixo é executado até que o fim da lista seja atingido e todos os doadores sejam listados. */
			
			while(iteradorDoadores.temProximo() == true){//verifica se há próximo elemento
				doadorListagem = (Doador) iteradorDoadores.obterProximo(); //obtém a referência para o próximo elemento 
				
				/* As informações do doador são exibidas em tela. */
				System.out.printf("* DOADOR COM CPF/CNPJ: %s *\n", doadorListagem.getNumCadastroDoador());
				System.out.printf("- NOME: %s\n", doadorListagem.getNomeDoador());
				System.out.printf("- TIPO: %s\n", doadorListagem.getTipoDoador());
				System.out.printf("- DATA DE NASCIMENTO/FUNDAÇÃO: %s\n", doadorListagem.getDataNasc().toString());
				System.out.printf("- ENDEREÇO: \n  Rua: %s, nº %d \n  Bairro: %s\n  CEP: %s\n  Cidade: %s\n  Estado: %s\n  País: %s\n\n\n", doadorListagem.getEndereco().getRua(), doadorListagem.getEndereco().getNumero(), doadorListagem.getEndereco().getBairro(), doadorListagem.getEndereco().getCep(), doadorListagem.getEndereco().getCidade(), doadorListagem.getEndereco().getEstado(), doadorListagem.getEndereco().getPais());
			}
		}

	}
	
	/**
	 * Realiza a interação com o usuário durante a listagem dos percentuais de doações de todos os doadores
	 * cadastrados no sistema.
	 */
	public void realizarListagemDoadoresPercentual(){
		Iterador iteradorDoadores = null; //iterador para percorrer uma lista de doadores
		Doador doadorListagem = null; //referência auxiliar para exibição dos dados do doador
		
		controller.calcularPercentualDoadores(); //solicita do controller o cálculo dos percentuais
		
		iteradorDoadores = controller.listarDoadores(); //obtém iterador para lista de doadores

		System.out.println("\n- LISTAGEM DE TODOS OS DOADORES DO SISTEMA COM RESPECTIVOS PERCENTUAIS DE DOAÇÃO -\n");
		

		System.out.println("* PERCENTUAIS EM RELAÇÃO ÀS DOAÇÕES MONETÁRIAS * \n\n");
		
		/* O laço de repetição abaixo é executado até que o fim da lista seja atingido. */
		while(iteradorDoadores.temProximo() == true){ //verifica se há próximo elemento
			doadorListagem = (Doador) iteradorDoadores.obterProximo(); //obtém a referência para o próximo doador 

			/* Exibe as informações desejadas. */
			System.out.printf("- NOME: %s\n", doadorListagem.getNomeDoador());
			System.out.printf("- CPF/CNPJ: %s\n" , doadorListagem.getNumCadastroDoador());
			System.out.printf("- PERCENTUAL DE DOAÇÕES MONETÁRIAS: %.2f\n\n", doadorListagem.getPercentualDoacoesMonetarias());
		}
		
		
		iteradorDoadores = controller.listarDoadores(); //obtém iterador para lista de doadores

		System.out.println("\n\n* PERCENTUAIS EM RELAÇÃO ÀS DOAÇÕES NÃO MONETÁRIAS * \n\n");

		/* O laço de repetição abaixo é executado até que o fim da lista seja atingido. */
		while(iteradorDoadores.temProximo() == true){ //verifica se há próximo elemento
			doadorListagem = (Doador) iteradorDoadores.obterProximo(); //obtém a referência para o próximo doador 
			
			/* Exibe as informações desejadas. */
			System.out.printf("- NOME: %s\n", doadorListagem.getNomeDoador());
			System.out.printf("- CPF/CNPJ: %s\n" , doadorListagem.getNumCadastroDoador());
			System.out.printf("- PERCENTUAL DE DOAÇÕES NÃO MONETÁRIAS: %.2f\n\n", doadorListagem.getPercentualDoacoesNaoMonetarias());
		}
	}

}
