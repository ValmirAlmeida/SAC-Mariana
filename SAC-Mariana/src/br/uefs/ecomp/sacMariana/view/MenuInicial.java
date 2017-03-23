package br.uefs.ecomp.sacMariana.view;

/**
 * Classe responsável por molda o menu principal exibido ao usuário.
 * @author Valmir Vinicius
 *
 */
public class MenuInicial {
	
	/**
	 * Exibe as opções principais disponíveis no sistema.
	 */
	public static void exibeOpcoesIniciais(){
		System.out.println("Bem vindo ao SAC-Mariana!"); //exibe uma mensagem inicial
		
		/* As primeiras opções são exibidas. */
		System.out.println("O que deseja fazer?");
		System.out.printf("1- Gerenciar produtos\n2- Gerenciar doadores\n3- Gerenciar doações\n4- Sair do programa");
	}
	
	
}
