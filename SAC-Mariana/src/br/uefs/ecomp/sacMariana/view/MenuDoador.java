package br.uefs.ecomp.sacMariana.view;
import java.io.IOException;
import java.util.Date;
import br.uefs.ecomp.sacMariana.controller.SACMarianaController;
import br.uefs.ecomp.sacMariana.model.Doador;
import br.uefs.ecomp.sacMariana.util.Console;
import br.uefs.ecomp.sacMariana.util.Iterador;
import br.uefs.ecomp.sacMariana.util.PegaData;

/**
 * Classe respons�vel por moldar um menu com as op��es dispon�veis para gerenciamento de doadores no sistema e exibir
 * das principais telas utilizadas nesse processo. 
 * 
 * @author Valmir Vinicius
 */
public class MenuDoador {
	
	/** Controller respons�vel por efetivar as solicita��es realizadas. */
	private SACMarianaController controller;
	
	/**
	 * Constr�i um menu para gerenciamento de doadores e atribui ao atributo "controller" uma refer�ncia de Controller 
	 * j� instanciado. 
	 *
	 * @param controller refer�ncia para o controller do sistema
	 */
	public MenuDoador(SACMarianaController controller){
		this.controller = controller; //atribui a refer�nca do controller ao atributo "controller"
	}
	
	/**
	 * M�todo est�tico que exibe as op��es iniciais para gerenciamento de doadores no sistema.
	 */
	public static void exibeOpcoesIniciasDoador(){
		/* As op��es iniciais s�o mostradas na tela. */
		System.out.println("O que voc� deseja fazer?");
		System.out.println("1- Cadastrar doador");
		System.out.println("2- Alterar informa��es de um doador cadastrado");
		System.out.println("3- Remover doador");
		System.out.println("4- Listar todos os doadores do sistema");
		System.out.println("5- Listar todos os doadores do sistema com respectivo percentual de doa��es");
	}
	
	/**
	 * M�todo est�tico que exibe as op��es de tipos de doadores
	 */
	public static void exibeOpcoesTiposDeDoadores(){
		/* As op��es de tipos de doadores s�o mostradas na tela. */
		System.out.println("Qual o tipo do doador?");
		System.out.println("1- Pessoa f�sica");
		System.out.println("2- Pessoa jurid�ca");
	}
	
	/**
	 * M�todo est�tico que exibe as op��es para altera��o no cadastro de um doador.
	 */
	public static void exibeOpcoesAlterarDoador(){
		/* As op��es para altera��o do cadastro de um doador s�o exibidas na tela. */
		System.out.println("Qual informa��o do doador deseja alterar?");
		System.out.println("1- Nome");
		System.out.println("2- Data de nascimento");
		System.out.println("3- Tipo");
		System.out.println("4- Endere�o");
	}
	
	/**
	 * M�todo est�tico utilizado para obter uma String contendo o tipo de doador a partir de uma op��o 
	 * inserida pelo usu�rio.
	 *
	 * @param auxiliarTipoDoador opcao de tipo de doador escolhida pelo usu�rio
	 * @return String contendo o tipo de doador, se a op��o inserida for v�lida; <code>null</code>, caso contr�rio.

	 */
	public String tipoDoadorEscolhido(int auxiliarTipoDoador){
		if(auxiliarTipoDoador == 1){ //verifica se a opcao foi 1
			return "Pessoa fisica"; //retorna uma string contendo o tipo "Pessoa fisica"
		} else if(auxiliarTipoDoador == 2){ //verifica se a opcao foi 2
			return "Pessoa juridica"; //retorna uma string contendo o tipo "Pessoa juridica"
		} else{ //verifica se foi informada uma op��o que n�o condiz com nenhum dos casos
			return null; //retorna uma refer�ncia null
		} 
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de cadastro de um doador.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void realizacaoCadastroDoador() throws IOException{
		String nomeDoador = null, numCadastro = null; //Strings para auxiliar no processo de obten��o do nome do doador e do CPF/CNPJ, respectivamente
		int opcaoTipoDoador = 0; //op��o de tipo de doador escolhida pelo usu�rio
		Date dataNascDoador = null; //data de nascimento/funda��o do doador
		Doador doadorCadastro = null; //refer�ncia para o cadastro de doador realizado
		
		System.out.println("Informe o CPF ou CNPJ do doador que ser� cadastrado");
		numCadastro = Console.readString(); //recebe o CPF/CNPJ do doador informado pelo usu�rio

		System.out.println("Informe o nome do doador que deseja cadastrar");
		nomeDoador = Console.readString(); //recebe o nome do doador informado pelo usu�rio
		
		System.out.println("Informe o tipo de doador que deseja cadastrar");
		exibeOpcoesTiposDeDoadores(); //exibe as op��es de tipos de doador dispon�veis
		do{
			opcaoTipoDoador = Console.readInt(); //recebe a op��o de tipo de doador escolhida pelo usu�rio
			
			if(opcaoTipoDoador < 1 || opcaoTipoDoador > 2){ //verifica se a op��o � v�lida
				System.out.println("Tipo de doador escolhido � inv�lido. Tente novamente\n"); //exibe mensagem de erro em caso de valor inv�lido
			}
			
		}while(opcaoTipoDoador < 1 || opcaoTipoDoador > 2); //executa a opera��o de solicitar a op��o do usu�rio at� que o valor inserido seja v�lido
		
		System.out.println("Informe data de nascimento ou funda��o");
		dataNascDoador = PegaData.pegaData(); //recebe a data de nascimento/funda��o do usu�rio
		
		System.out.printf("Informe, respectivamente, os dados do endere�o do doador: Rua, N�mero, Bairro, CEP, Cidade, Estdo, Pais\n");

		doadorCadastro =  controller.cadastrarDoador(numCadastro, nomeDoador, dataNascDoador, Console.readString(), Console.readInt(), Console.readString(), Console.readString(), Console.readString(), Console.readString(), Console.readString(), tipoDoadorEscolhido(opcaoTipoDoador)); //recebe do usu�rio as informa��es do endere�o, envia uma solicita��o de cadastro de doador para o controller, recebe a refer�ncia para o objeto doador instanciado
		
		if(doadorCadastro == null){ //verifica se refer�ncia obtida foi null
			System.out.println("J� existe no sistema um doador com o CPF/CNPJ informado\n\n"); //caso a refer�ncia obtida seja null exibe mensagem de erro
		} else{
			System.out.printf("O doador %s com CPF/CNPJ %s foi cadastrado com sucesso\n\n", doadorCadastro.getNomeDoador(), doadorCadastro.getNumCadastroDoador()); //exibe mensagem informado que o doador foi cadastrado com sucesso
		}
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de altera��o do cadastro de um doador.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void alteracaoCadastroDoador() throws IOException{
		String numCadastro = null; //CPF/CNPJ do usu�rio que ter� as suas informa��es alteradas
		int opcaoAlteracao = 0, opcaoTipoDoador = 0; //op��o para altera��o e op��o para tipo de doador, respectivamente
		Doador doadorAlteracao = null; //refer�ncia para o doador que ser� alterado
		
		System.out.println("Informe o CPF/CNPJ do doador\n");
		numCadastro = Console.readString(); //recebe o CPF/CNPJ do doador que ser� alterado
		
		doadorAlteracao = controller.obterDoador(numCadastro); //obt�m a refer�ncia para o doador que ser� alterado
		
		if(doadorAlteracao == null){ //verifica se a refer�ncia obtida foi null
			System.out.printf("N�o existe um doador com o CPF/CNPJ informado\n"); //exibe mensagem de erro indicando que n�o foi poss�vel localizar o doador com o CPF/CNPJ informado
			return; //encerra o m�todo
		}
		
		exibeOpcoesAlterarDoador(); //exibe as op��es dispon�veis para altera��o do cadastro do doador
		opcaoAlteracao = Console.readInt(); //recebe do usu�rio a op��o para a altera��o
		
		if(opcaoAlteracao == 1){ //verifica se a op��o informada foi 1
			System.out.println("Informe o novo nome do doador\n");
			doadorAlteracao = controller.editarDoador(doadorAlteracao.getNumCadastroDoador(), Console.readString(), doadorAlteracao.getDataNasc(), doadorAlteracao.getEndereco().getRua(), doadorAlteracao.getEndereco().getNumero(), doadorAlteracao.getEndereco().getBairro(), doadorAlteracao.getEndereco().getCep(), doadorAlteracao.getEndereco().getCidade(), doadorAlteracao.getEndereco().getEstado(), doadorAlteracao.getEndereco().getPais(), doadorAlteracao.getTipoDoador()); //recebe do usu�rio o novo nome do doador, solicita do controller a altera��o dos dados e recebe a refer�ncia para o objeto ap�s a altera��o
		} else if(opcaoAlteracao == 2){ //verifica se a op��o informada foi 2
			System.out.println("Informe a nova data de nascimento ou funda��o");
			doadorAlteracao = controller.editarDoador(doadorAlteracao.getNumCadastroDoador(), doadorAlteracao.getNomeDoador(), PegaData.pegaData(), doadorAlteracao.getEndereco().getRua(), doadorAlteracao.getEndereco().getNumero(), doadorAlteracao.getEndereco().getBairro(), doadorAlteracao.getEndereco().getCep(), doadorAlteracao.getEndereco().getCidade(), doadorAlteracao.getEndereco().getEstado(), doadorAlteracao.getEndereco().getPais(), doadorAlteracao.getTipoDoador());  //recebe do usu�rio a nova data de nascimento/funda��o do doador, solicita do controller a altera��o dos dados e recebe a refer�ncia para o objeto ap�s a altera��o
		} else if(opcaoAlteracao == 3){ //verifica se a op��o informada foi 3
			System.out.println("Alterar tipo de doador");
			exibeOpcoesTiposDeDoadores(); //exibe as op��es de tipos de doadores dispon�veis 
	
			do{
				opcaoTipoDoador = Console.readInt(); //recebe do usu�rio a op��o para altera��o do tipo de doador
				
				if(opcaoTipoDoador < 1 || opcaoTipoDoador > 2){ //verifica se a op��o informada � v�lida
					System.out.println("Tipo de doador escolhido � inv�lido. Tente novamente\n"); //exibe mensagem de erro
				}
			}while(opcaoTipoDoador < 1 || opcaoTipoDoador > 2); //executa a opera��o de solicitar o tipo at� que o valor inserido seja v�lido
			
			doadorAlteracao = controller.editarDoador(doadorAlteracao.getNumCadastroDoador(), doadorAlteracao.getNomeDoador(), doadorAlteracao.getDataNasc(), doadorAlteracao.getEndereco().getRua(), doadorAlteracao.getEndereco().getNumero(), doadorAlteracao.getEndereco().getBairro(), doadorAlteracao.getEndereco().getCep(), doadorAlteracao.getEndereco().getCidade(), doadorAlteracao.getEndereco().getEstado(), doadorAlteracao.getEndereco().getPais(), this.tipoDoadorEscolhido(opcaoTipoDoador)); //obt�m a String contendo o tipo de doador por meio da op��o do usu�rio, solicita do controller a altera��o dos dados e recebe a refer�ncia para o objeto ap�s a altera��o
		} else if(opcaoAlteracao == 4){ //verifica se a op��o informada foi 4
			System.out.println("Informe respectivamente as novas informa��es do endere�o: Rua, N�mero, Bairro, CEP, Cidade, Estado, Pa�s\n");
			doadorAlteracao = controller.editarDoador(doadorAlteracao.getNumCadastroDoador(), doadorAlteracao.getNomeDoador(), doadorAlteracao.getDataNasc(), Console.readString(), Console.readInt(), Console.readString(), Console.readString(), Console.readString(), Console.readString(), Console.readString(), doadorAlteracao.getTipoDoador()); //obt�m as novas informa��es do endere�o, solicita do controller a altera��o do cadastro do doador e recebe a refer�ncia do doador alterado
		} else{ //verifica se a entrada n�o condiz com nenhuma das condi��es anteriores
			System.out.println("Op��o inv�lida\n\n"); //exibe uma mensagem avisando sobre a entrada inv�lida
			return; //finaliza o m�todo
		}
		
		if(doadorAlteracao == null){ //verifica se a refer�ncia obtida foi null
			System.out.println("N�o existe um doador com o CPF/CNPJ informado"); //exibe mensagem informado que o CPF/CNPJ � inv�lido
		} else{
			System.out.printf("O cadastro do doador de CPF/CNPJ: %s foi alterado com sucesso\n\n", doadorAlteracao.getNumCadastroDoador()); //exibe mensagem informando que a opera��o ocorreu com sucesso
		}
		
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante o processo de remo��o do cadastro de um doador.
	 *
	 * @throws IOException sinaliza se ocorrer alguma exce��o I/O
	 */
	public void efetuarRemocaoDoador() throws IOException{
		String numCadastro = null; //CPF/CNPJ do doador que ser� removido
		
		System.out.println("Informe o CPF/CNPJ do doador que deseja remover do sistema");
		numCadastro = Console.readString(); //recebe o CPF/CNPJ do doador que ser� removido
		
		if(controller.removerDoador(numCadastro) == true){ //solicita do controller a remo��o e verifica se o processo ocorreu com sucesso
			System.out.println("O cadastro do doador foi removido com sucesso\n\n"); //exibe mensagem informado que o cadastro do doador foi removido
		} else{
			System.out.println("Impossivel remover cadastro do doador: o doador j� realizou uma doa��o ou o CPF/CNPJ informado � inv�lido"); //exibe mensagem informado erro no processo
		}
	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante a listagem de todos os doadores cadastrados no sistema.
	 */
	public void realizarListagemDoadores(){
		Iterador iteradorDoadores = null; //iterador para percorrer a lista de doadores
		Doador doadorListagem = null; //refer�ncia auxiliar para exibi��o das informa��es do doador
		
		iteradorDoadores = this.controller.listarDoadores(); //obt�m o iterador da lista
		
		if(iteradorDoadores.temProximo() == false){ //verifica se a lista est� vazia
			System.out.println("N�o h� cadastro de doadores no sistema\n"); //exibe mensagem indicando que n�o h� doadores cadastrados
			return;
		} else{
			System.out.println("\n- LISTAGEM DE TODOS OS DOADORES DO SISTEMA -\n");
			
			/* O la�o de repeti��o abaixo � executado at� que o fim da lista seja atingido e todos os doadores sejam listados. */
			
			while(iteradorDoadores.temProximo() == true){//verifica se h� pr�ximo elemento
				doadorListagem = (Doador) iteradorDoadores.obterProximo(); //obt�m a refer�ncia para o pr�ximo elemento 
				
				/* As informa��es do doador s�o exibidas em tela. */
				System.out.printf("* DOADOR COM CPF/CNPJ: %s *\n", doadorListagem.getNumCadastroDoador());
				System.out.printf("- NOME: %s\n", doadorListagem.getNomeDoador());
				System.out.printf("- TIPO: %s\n", doadorListagem.getTipoDoador());
				System.out.printf("- DATA DE NASCIMENTO/FUNDA��O: %s\n", doadorListagem.getDataNasc().toString());
				System.out.printf("- ENDERE�O: \n  Rua: %s, n� %d \n  Bairro: %s\n  CEP: %s\n  Cidade: %s\n  Estado: %s\n  Pa�s: %s\n\n\n", doadorListagem.getEndereco().getRua(), doadorListagem.getEndereco().getNumero(), doadorListagem.getEndereco().getBairro(), doadorListagem.getEndereco().getCep(), doadorListagem.getEndereco().getCidade(), doadorListagem.getEndereco().getEstado(), doadorListagem.getEndereco().getPais());
			}
		}

	}
	
	/**
	 * Realiza a intera��o com o usu�rio durante a listagem dos percentuais de doa��es de todos os doadores
	 * cadastrados no sistema.
	 */
	public void realizarListagemDoadoresPercentual(){
		Iterador iteradorDoadores = null; //iterador para percorrer uma lista de doadores
		Doador doadorListagem = null; //refer�ncia auxiliar para exibi��o dos dados do doador
		
		controller.calcularPercentualDoadores(); //solicita do controller o c�lculo dos percentuais
		
		iteradorDoadores = controller.listarDoadores(); //obt�m iterador para lista de doadores

		System.out.println("\n- LISTAGEM DE TODOS OS DOADORES DO SISTEMA COM RESPECTIVOS PERCENTUAIS DE DOA��O -\n");
		

		System.out.println("* PERCENTUAIS EM RELA��O �S DOA��ES MONET�RIAS * \n\n");
		
		/* O la�o de repeti��o abaixo � executado at� que o fim da lista seja atingido. */
		while(iteradorDoadores.temProximo() == true){ //verifica se h� pr�ximo elemento
			doadorListagem = (Doador) iteradorDoadores.obterProximo(); //obt�m a refer�ncia para o pr�ximo doador 

			/* Exibe as informa��es desejadas. */
			System.out.printf("- NOME: %s\n", doadorListagem.getNomeDoador());
			System.out.printf("- CPF/CNPJ: %s\n" , doadorListagem.getNumCadastroDoador());
			System.out.printf("- PERCENTUAL DE DOA��ES MONET�RIAS: %.2f\n\n", doadorListagem.getPercentualDoacoesMonetarias());
		}
		
		
		iteradorDoadores = controller.listarDoadores(); //obt�m iterador para lista de doadores

		System.out.println("\n\n* PERCENTUAIS EM RELA��O �S DOA��ES N�O MONET�RIAS * \n\n");

		/* O la�o de repeti��o abaixo � executado at� que o fim da lista seja atingido. */
		while(iteradorDoadores.temProximo() == true){ //verifica se h� pr�ximo elemento
			doadorListagem = (Doador) iteradorDoadores.obterProximo(); //obt�m a refer�ncia para o pr�ximo doador 
			
			/* Exibe as informa��es desejadas. */
			System.out.printf("- NOME: %s\n", doadorListagem.getNomeDoador());
			System.out.printf("- CPF/CNPJ: %s\n" , doadorListagem.getNumCadastroDoador());
			System.out.printf("- PERCENTUAL DE DOA��ES N�O MONET�RIAS: %.2f\n\n", doadorListagem.getPercentualDoacoesNaoMonetarias());
		}
	}

}
