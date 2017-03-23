package br.uefs.ecomp.sacMariana.view;

/**
 * Classe respons�vel por molda o menu principal exibido ao usu�rio.
 * @author Valmir Vinicius
 *
 */
public class MenuInicial {
	
	/**
	 * Exibe as op��es principais dispon�veis no sistema.
	 */
	public static void exibeOpcoesIniciais(){
		System.out.println("Bem vindo ao SAC-Mariana!"); //exibe uma mensagem inicial
		
		/* As primeiras op��es s�o exibidas. */
		System.out.println("O que deseja fazer?");
		System.out.printf("1- Gerenciar produtos\n2- Gerenciar doadores\n3- Gerenciar doa��es\n4- Sair do programa");
	}
	
	
}
