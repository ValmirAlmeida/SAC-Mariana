package br.uefs.ecomp.sacMariana.view;
import br.uefs.ecomp.sacMariana.controller.SACMarianaController;
import br.uefs.ecomp.sacMariana.util.Console;
import java.io.IOException;

/**
 * Classe principal para a execução do sistema.
 */
public class Principal {

	/**
	 * Método <code>main</code> que iniciará a execução do sistema.
	 *
	 * @param args the arguments
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		SACMarianaController controller = new SACMarianaController(); //instancia um controller
		MenuProdutos menuProdutos = new MenuProdutos(controller); //instancia um menu de produtos passando o controller instanciado anteriormente
		MenuDoador menuDoadores = new MenuDoador(controller); //instancia um menu de doadores passando o controller instanciado anteriormente
		MenuDoacao menuDoacoes = new MenuDoacao(controller); //instancia um menu de doações passando o controller instanciado anteriormente
		int opcaoMenuPrincipal; //opção escolhida no menu principal
		
		do{

			MenuInicial.exibeOpcoesIniciais(); //exibe as opções inicias do menu principal
			System.out.printf("\n\n");
			opcaoMenuPrincipal = Console.readInt(); //obtém a opção do usuário
			
			switch(opcaoMenuPrincipal){  //seleciona a opção escolhida pelo usuário no menu principal para comparações
				case 1: {
					System.out.println("\n");
					MenuProdutos.exibeOpcoesIniciasProduto(); //exibe as opções iniciais para gerenciamento de produtos
					
					switch(Console.readInt()){ //obtém a opção do usuário para realizar comparações
						case 1: {
							menuProdutos.realizacaoDeCadastroProduto(); //chama método para realização de cadastro de produtos
							break;
						}
						case 2: {
							menuProdutos.alteracaoDeProduto(); //chama método para alteração do cadastro de produtos
							break;
						}
						case 3: {
							menuProdutos.realizacaoDeRemocaoProduto(); //chama método para remoção do cadastro de produtos
							break;
						}
						case 4: {
							menuProdutos.realizacaoDeListagemProdutos(); //chama método para realização de listagem de produtos
							break;
						}
						default: {
							System.out.println("Opção inválida. Tente Novamente.\n\n"); //exibe mensagem de erro
							break;
						}
					}
					
					break;
				}
				case 2: {
					System.out.println("\n");
					MenuDoador.exibeOpcoesIniciasDoador(); //exibe as opções iniciais para gerenciamento de doador 

					switch(Console.readInt()){ //obtém a opção do usuário para realizar comparações
						case 1: { 
							menuDoadores.realizacaoCadastroDoador(); //chama método para realização de cadastro de doador
							break;
						}
						case 2: {
							menuDoadores.alteracaoCadastroDoador(); //chama método para alteração do cadastro de doadores
							break;
						}
						case 3: {
							menuDoadores.efetuarRemocaoDoador(); //chama método para remoção do cadastro de doadores
							break;
						}
						case 4: {
							menuDoadores.realizarListagemDoadores(); //chama método para realização de listagem de doadores
							break;
						}
						case 5: {
							menuDoadores.realizarListagemDoadoresPercentual(); //chama método para realização de listagem de percentuais de doadores
							break;
						}
						default: {
							System.out.println("Opção inválida. Tente Novamente.\n\n"); //exibe mensagem de erro
							break;
						}
					}
					
					break;
				}
				case 3:{
					System.out.println("\n");
					MenuDoacao.exibeOpcoesIniciasDoacao(); //exibe as opções para gerenciamento de doações
					
					switch(Console.readInt()){ //obtém a opção do usuário para realizar comparações
						case 1: {
							menuDoacoes.realizacaoDeDoacao(); //chama método para realização de cadastro de doação 
							break;
						}
						case 2: {
							menuDoacoes.realizarAlteracaoDoacao(); //chama método para realização de alteração no cadastro de uma doação
							break;
						}
						case 3: {
							menuDoacoes.efetuarRemocaoDoacao(); //chama método para remoção do cadastro de uma doação
							break;
						}
						case 4: {
							menuDoacoes.efetuarListagemDoacoesOrdenadas(); //chama método para realização de listagem ordenada de todas as doações
							break;
						}
						case 5: {
							menuDoacoes.efetuarListagemDoacoesDeUmDoador(); //chama método para realização de listagem de todas as doações de um doador
							break;
						}
						default: {
							System.out.println("Opção inválida. Tente Novamente.\n\n"); //exibe mensagem de erro
							break;
						}
					}
					
					break;
				}
				case 4: {
					break; //finaliza a execução do programa
				}
				default: {
					System.out.println("Opção inválida. Tente Novamente.\n\n"); //exibe mensagem de erro
					break;
				}
			}
		
		}while(opcaoMenuPrincipal != 4);
	}
}

		



