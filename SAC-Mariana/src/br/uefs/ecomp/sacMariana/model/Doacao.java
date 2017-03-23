package br.uefs.ecomp.sacMariana.model;
import java.util.Date;
import br.uefs.ecomp.sacMariana.util.Comparavel;

/**
 * Classe responsável por moldar uma doação no sistema. Possui métodos para obtenção, configuração, geração e alteração
 * dos atributos de uma doação. 
 *
 * @author Valmir Vinicius
 * @see java.lang.Integer
 * @see java.util.Date
 * @see java.lang.Number
 * @see br.uefs.ecomp.sacMariana.model.Produto
 * @see br.uefs.ecomp.sacMariana.model.Doador
 * 
 */
public class Doacao implements Comparavel {

	/** Identificador único da doação no sistema. */
	private Integer idDoacao; 
	
	/** Data na qual foi realizada a doação. */
	private Date dataDoacao;
	
	/** Produto que foi doado. */
	private Produto produto;
	
	/** Doador que realizou a doação. */
	private Doador doador;
	
	/** Quantidade que foi doada na doação em questão. */
	private Number quantidadeDoada;
	
	/** Indica se a doação realizada foi do tipo monetária ou não. */
	private boolean monetaria;
	
	/** Contador do número de doações realizadas.  */
	private static int contadorDoacoes = 1;
	
	/** O valor total arrecadado com todas as doações monetárias. */
	private static float totalArrecadadoMonetario;
	
	/** A quantidade total arrecadada com todas as doações não monetárias. */
	private static int totalArrecadadoNaoMonetario;
	
	/**
	 * Inicializa os atributos de uma nova Doacao com as informações recebidas e gera um identificador único 
	 * para a doação no sistema.
	 *
	 * @param dataDoacao data na qual a doação foi realizada
	 * @param produto o produto doado
	 * @param doador o doador que realizou a doação
	 * @param quantidadeDoada a quantidade que foi doada
	 * @param monetaria indica se a doação foi monetária ou não
	 */
	public Doacao(Date dataDoacao, Produto produto, Doador doador, Number quantidadeDoada, boolean monetaria){
		this.geraIdDoacao(); //chamada para método que vai gerar o identificador único da doação no sistema

		/* Todos os atributos do endereço são inicializado com as respectivas informações recebidas durante o instanciamento
		 * do objeto. */
		this.dataDoacao = dataDoacao; 
		this.produto = produto; 
		this.doador = doador; 
		this.quantidadeDoada = quantidadeDoada; 
		this.monetaria = monetaria; 
	}
	
	/**
	 * Gera um número de identificação único para a doação no sistema.
	 */
	private void geraIdDoacao(){
		this.idDoacao = contadorDoacoes; //o ID da doação recebe a quantidade de doações realizadas
		contadorDoacoes++; //o número total de doaçõe realizadas é incrementado com um
	}
	
	/**
	 * Método para obtenção do identificador único (ID da doação) da doação no sistema.
	 *
	 * @return o ID da doação
	 */
	public Integer getIdDoacao(){
		return this.idDoacao; //retorna a referência do atributo "idDoacao"
	}
	
	/**
	 * Método para obtenção da data de realização da doação.
	 *
	 * @return a data da doação
	 */
	public Date getDataDoacao(){
		return this.dataDoacao; //retorna a referência do atributo "dataDoacao"
	}
	
	/**
	 * Método para obtenção do doador que realizou a doação.
	 *
	 * @return o doador que fez a doação
	 */
	public Doador getDoador(){
		return this.doador; //retorna a referência do atributo "doador"
	}
	
	/**
	 * Método para obtenção da quantidade doada.
	 *
	 * @return quantidade doada
	 */
	public Number getQuantidadeDoada(){
		return this.quantidadeDoada; //retorna a referência do atributo "quantidadeDoada"
	}
	
	/**
	 * Método para obtenção do produto doado.
	 *
	 * @return produto doado
	 */
	public Produto getProduto(){
		return this.produto; //retorna a referência do atributo "produto"
	}
	
	/**
	 * Método para verificar se a doação é monetária.
	 *
	 * @return true, se a doação for monetária. false, se a doação for monetária.
	 */
	public boolean isMonetaria(){
		return this.monetaria; //retorna o valor booleano do atributo "monetaria", indicando se a doação foi monetária ou não.
	}
	
	/**
	 * Método estático para obter o valor total monetário arrecadado em todas as doações.
	 *
	 * @return valor monetário total arrecadado
	 */
	public static float getTotalArrecadadoMonetario(){
		return totalArrecadadoMonetario; //retorna o valor real do atributo estático "totalArrecadadoMonetario"
	}
	
	/**
	 * Método estático para obter a quantidade total não monetária arrecada em todas as doações.
	 *
	 * @return quantidade não monetária total arrecadada
	 */
	public static int getTotalArrecadadoNaoMonetario(){
		return totalArrecadadoNaoMonetario; //retorna a quantidade inteira do atributo estático "totalArrecadadoNaoMonetario"
	}
	

	/**
	 * Configura a data da doação.
	 *
	 * @param dataDoacao data de realização da doação
	 */
	public void setDataDoacao(Date dataDoacao){
		this.dataDoacao = dataDoacao; //atribui a referência recebida por parâmetro ao atributo "dataDoacao"
	}
	
	/**
	 * Configura o produto doado.
	 *
	 * @param produto produto doado
	 */
	public void setProduto(Produto produto){
		this.produto = produto; //atribui a referência recebida por parâmetro ao atributo "produto"
	}
	
	/**
	 * Configura o doador.
	 *
	 * @param doador doador que realizou a doação
	 */
	public void setDoador(Doador doador){
		this.doador = doador; //atribui a referência recebida por parâmetro ao atributo "doador"
	}
	
	/**
	 * Configura a quantidade doada.
	 *
	 * @param quantidadeDoada quantidade que foi doada
	 */
	public void setQuantidadeDoada(Number quantidadeDoada){
		this.quantidadeDoada = quantidadeDoada; //atribui a referência recebida por parâmetro ao atributo "produto"
	}
	
	/**
	 * Configura se a doação é monetária.
	 *
	 * @param monetaria indicador se a doação realizada foi monetária ou não
	 */
	public void setMonetaria(boolean monetaria){
		this.monetaria = monetaria; //atribui o valor booleano recebido por parâmetro ao atributo "monetaria" 
	}
	

	/**
	 * Atualiza (incrementando ou decrementando) o valor monetário total arrecadado.
	 *
	 * @param quantidadeDoada valor doado na doação
	 * @param incrementar booleano que indica se será realizado incremento ou decremento
	 */
	public static void atualizaTotalArrecadadoMonetario(float quantidadeDoada, boolean incrementar){
		if(incrementar == true){ //verifica se será realizado incremento
			totalArrecadadoMonetario += quantidadeDoada; //se for uma operação de incremento acrescenta a quantidade no atributo estático "totalArrecadadoMonetario"
		}else{
			totalArrecadadoMonetario -= quantidadeDoada; //se não for uma operação de incremento diminui a quantidade no atributo estático "totalArrecadadoMonetario"
		}
	}
	
	/**
	 * Atualiza (incrementando ou decrementando) a quantidade não monetária total arrecadado.
	 *
	 * @param quantidadeDoada quantidade doada na doação
	 * @param incrementar booleano que indica se será realizado incremento ou decremento
	 */
	public static void atualizaTotalArrecadadoNaoMonetario(int quantidadeDoada, boolean incrementar){
		if(incrementar == true){//verifica se será realizado incremento 
			totalArrecadadoNaoMonetario += quantidadeDoada; //se for uma operação de incremento acrescenta a quantidade no atributo estático "totalArrecadadoNaoMonetario"
		}else{
			totalArrecadadoNaoMonetario -= quantidadeDoada; //se não for uma operação de incremento diminui a quantidade no atributo estático "totalArrecadadoNaoMonetario"
		}
	}



	/**
	 * Realiza a comparação entre os atributos "quantidadeDoada" da doação atual com uma doação especificada.
	 * @return -1, se houver sido doada uma maior quantidade no objeto doacao recebido; 1, se essa quantidade for maior na doação atual; 0, caso as quantidades sejam iguais. 
	 */
	/* Método para realizar a comparação da quantidade doada presente em dois objetos do tipo doacao. Recebe objeto genérico
	 * que é convertido para um objeto doacao e comparando com o objeto doacao que está sendo referenciado no momento da 
	 * chamada do método. Retorna -1 se a quantidade doada no objeto passado for maior do que a do objeto referenciado, 1
	 * se a quantidade do objeto passado for maior e 0 se as quantidades forem iguais.
	 * @see br.uefs.ecomp.sacMariana.util.Comparavel#comparacao(java.lang.Object)
	 */
	@Override
	public int comparacao(Object obj) {
		Doacao auxiliarDoacao = (Doacao) obj; //auxiliarDoacao recebe a referência para o objeto que será comparado
		
		if(this.getQuantidadeDoada().floatValue() < auxiliarDoacao.getQuantidadeDoada().floatValue()){ //verifica se a quantidade doada na doacao passada por parâmetro é maior do que a quantidade doada na doacao atual
			return -1; //caso a condição seja satisfeita retorna -1
		} else if(this.getQuantidadeDoada().floatValue() > auxiliarDoacao.getQuantidadeDoada().floatValue()){ //verifica se a quantidade doada na doacao passada por parâmetro é menor do que a quantidade doada na doacao atual
			return 1; //caso a condição seja satisfeita retorna 1
		} else{
			return 0; //retorna 0 caso nenhuma das condições acima sejam satisfeitas, indicando que as quantidades doadas são iguais
		}
		
		/* OBS: Nas comparações acima são utilizados os valores float das quantidades pois esses possuem maior precisão e são capazes
		 * de possibilitar a comparação para quantidades monetárias e não monetárias. */
		
	}
	


}
