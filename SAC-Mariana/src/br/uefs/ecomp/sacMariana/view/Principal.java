package br.uefs.ecomp.sacMariana.view;
import br.uefs.ecomp.sacMariana.controller.SACMarianaController;
import br.uefs.ecomp.sacMariana.util.Console;
import java.io.IOException;

/**
 * Classe principal para a execu��o do sistema.
 */
public class Principal {

	/**
	 * M�todo <code>main</code> que iniciar� a execu��o do sistema.
	 *
	 * @param args the arguments
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		SACMarianaController controller = new SACMarianaController(); //instancia um controller
		MenuProdutos menuProdutos = new MenuProdutos(controller); //instancia um menu de produtos passando o controller instanciado anteriormente
		MenuDoador menuDoadores = new MenuDoador(controller); //instancia um menu de doadores passando o controller instanciado anteriormente
		MenuDoacao menuDoacoes = new MenuDoacao(controller); //instancia um menu de doa��es passando o controller instanciado anteriormente
		int opcaoMenuPrincipal; //op��o escolhida no menu principal
		
		do{

			MenuInicial.exibeOpcoesIniciais(); //exibe as op��es inicias do menu principal
			System.out.printf("\n\n");
			opcaoMenuPrincipal = Console.readInt(); //obt�m a op��o do usu�rio
			
			switch(opcaoMenuPrincipal){  //seleciona a op��o escolhida pelo usu�rio no menu principal para compara��es
				case 1: {
					System.out.println("\n");
					MenuProdutos.exibeOpcoesIniciasProduto(); //exibe as op��es iniciais para gerenciamento de produtos
					
					switch(Console.readInt()){ //obt�m a op��o do usu�rio para realizar compara��es
						case 1: {
							menuProdutos.realizacaoDeCadastroProduto(); //chama m�todo para realiza��o de cadastro de produtos
							break;
						}
						case 2: {
							menuProdutos.alteracaoDeProduto(); //chama m�todo para altera��o do cadastro de produtos
							break;
						}
						case 3: {
							menuProdutos.realizacaoDeRemocaoProduto(); //chama m�todo para remo��o do cadastro de produtos
							break;
						}
						case 4: {
							menuProdutos.realizacaoDeListagemProdutos(); //chama m�todo para realiza��o de listagem de produtos
							break;
						}
						default: {
							System.out.println("Op��o inv�lida. Tente Novamente.\n\n"); //exibe mensagem de erro
							break;
						}
					}
					
					break;
				}
				case 2: {
					System.out.println("\n");
					MenuDoador.exibeOpcoesIniciasDoador(); //exibe as op��es iniciais para gerenciamento de doador 

					switch(Console.readInt()){ //obt�m a op��o do usu�rio para realizar compara��es
						case 1: { 
							menuDoadores.realizacaoCadastroDoador(); //chama m�todo para realiza��o de cadastro de doador
							break;
						}
						case 2: {
							menuDoadores.alteracaoCadastroDoador(); //chama m�todo para altera��o do cadastro de doadores
							break;
						}
						case 3: {
							menuDoadores.efetuarRemocaoDoador(); //chama m�todo para remo��o do cadastro de doadores
							break;
						}
						case 4: {
							menuDoadores.realizarListagemDoadores(); //chama m�todo para realiza��o de listagem de doadores
							break;
						}
						case 5: {
							menuDoadores.realizarListagemDoadoresPercentual(); //chama m�todo para realiza��o de listagem de percentuais de doadores
							break;
						}
						default: {
							System.out.println("Op��o inv�lida. Tente Novamente.\n\n"); //exibe mensagem de erro
							break;
						}
					}
					
					break;
				}
				case 3:{
					System.out.println("\n");
					MenuDoacao.exibeOpcoesIniciasDoacao(); //exibe as op��es para gerenciamento de doa��es
					
					switch(Console.readInt()){ //obt�m a op��o do usu�rio para realizar compara��es
						case 1: {
							menuDoacoes.realizacaoDeDoacao(); //chama m�todo para realiza��o de cadastro de doa��o 
							break;
						}
						case 2: {
							menuDoacoes.realizarAlteracaoDoacao(); //chama m�todo para realiza��o de altera��o no cadastro de uma doa��o
							break;
						}
						case 3: {
							menuDoacoes.efetuarRemocaoDoacao(); //chama m�todo para remo��o do cadastro de uma doa��o
							break;
						}
						case 4: {
							menuDoacoes.efetuarListagemDoacoesOrdenadas(); //chama m�todo para realiza��o de listagem ordenada de todas as doa��es
							break;
						}
						case 5: {
							menuDoacoes.efetuarListagemDoacoesDeUmDoador(); //chama m�todo para realiza��o de listagem de todas as doa��es de um doador
							break;
						}
						default: {
							System.out.println("Op��o inv�lida. Tente Novamente.\n\n"); //exibe mensagem de erro
							break;
						}
					}
					
					break;
				}
				case 4: {
					break; //finaliza a execu��o do programa
				}
				default: {
					System.out.println("Op��o inv�lida. Tente Novamente.\n\n"); //exibe mensagem de erro
					break;
				}
			}
		
		}while(opcaoMenuPrincipal != 4);
	}
}

		



