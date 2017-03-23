package br.uefs.ecomp.sacMariana.model;
import java.util.Date;

/**
 * Classe responsável por moldar um produto no sistema. Possui métodos para obtenção, configuração, geração e alteração
 * dos atributos desse produto.
 * 
 * @author Valmir Vinicius
 * @see java.util.Date
 * @see java.lang.String
 * @see java.lang.Integer
 */
public class Produto {
	
	/** Identificador único do produto no sistema. */
	private Integer idProduto;
	
	/** Tipo do produto.  */
	private String tipoProduto;
	
	/** Nome do produto. */
	private String nomeProduto;
	
	/** Data de cadastro do produto no sistema. */
	private Date dataCadastroProduto;
	
	/** Contador de cadastro de produtos no sistema. */
	private static int contadorProdutos = 1;
	
	/**
	 * Inicializa os atributos de um novo Produto com as informações recebidas e gera um identificador único 
	 * para o produto no sistema.
	 *
	 * @param nomeProduto nome do produto
	 * @param tipoProduto tipo do produto
	 * @param dataCadastroProduto data de cadastro do produto
	 */
	public Produto(String nomeProduto, String tipoProduto,  Date dataCadastroProduto){
		this.geraIdProduto(); //chamada para método que vai gerar o identificador único do produto no sistema

		/* Todos os atributos do produto são inicializado com as respectivas informações recebidas durante o instanciamento
		 * do objeto. */
		this.tipoProduto = tipoProduto;
		this.nomeProduto = nomeProduto;
		this.dataCadastroProduto = dataCadastroProduto;
		
	}
	
	/**
	 * Gera um número de identificação único (ID do produto) para a produto no sistema.
	 */
	private void geraIdProduto(){
		this.idProduto = contadorProdutos; //o ID do produto recebe a quantidade de produtos cadastrados
		contadorProdutos++; //incrementa com um a quantidade de produtos cadastrados
	}

	/**
	 * Método para obtenção do identificador único (ID do produto) do produto no sistema. 
	 *
	 * @return ID do produto
	 */
	public Integer getIdProduto(){
		return this.idProduto; //retorna a referência do atributo "idProduto"
	}
	
	/**
	 * Método para obtenção do tipo de produto.
	 *
	 * @return tipo do produto
	 */
	public String getTipoProduto(){
		return this.tipoProduto; //retorna a referência do atributo "tipoProduto"
	}
	
	/**
	 * Método para obtenção do nome do produto.
	 *
	 * @return nome do produto
	 */
	public String getNomeProduto(){
		return this.nomeProduto; //retorna a referência do atributo "nomeProduto"
	}
	
	/**
	 * Método para obtenção da data de cadastro do produto.
	 *
	 * @return data de cadastro do produto
	 */
	public Date getDataCadastroProduto(){
		return this.dataCadastroProduto; //retorna a referência do atributo "dataCadastroProduto"
	}
	
	/**
	 * Configura o tipo do produto.
	 *
	 * @param tipoProduto tipo do produto
	 */
	public void setTipoProduto(String tipoProduto){
		this.tipoProduto = tipoProduto; //atribui a referência recebida por parâmetro ao atributo "tipoProduto"
	}
	
	/**
	 * Configura o nome do produto.
	 *
	 * @param nomeProduto nome do produto
	 */
	public void setNomeProduto(String nomeProduto){
		this.nomeProduto = nomeProduto; //atribui a referência recebida por parâmetro ao atributo "nomeProduto"
	}
	
	/**
	 * Configura a data de cadastro do produto.
	 *
	 * @param dataCadastroProduto data de cadastro do produto
	 */
	public void setDataCadastroProduto(Date dataCadastroProduto){
		this.dataCadastroProduto = dataCadastroProduto; //atribui a referência recebida por parâmetro ao atributo "dataCadastroProduto"
	}

}
