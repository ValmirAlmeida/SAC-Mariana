package br.uefs.ecomp.sacMariana.model;
import java.util.Date;
import br.uefs.ecomp.sacMariana.util.Comparavel;

/**
 * Classe respons�vel por moldar uma doa��o no sistema. Possui m�todos para obten��o, configura��o, gera��o e altera��o
 * dos atributos de uma doa��o. 
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

	/** Identificador �nico da doa��o no sistema. */
	private Integer idDoacao; 
	
	/** Data na qual foi realizada a doa��o. */
	private Date dataDoacao;
	
	/** Produto que foi doado. */
	private Produto produto;
	
	/** Doador que realizou a doa��o. */
	private Doador doador;
	
	/** Quantidade que foi doada na doa��o em quest�o. */
	private Number quantidadeDoada;
	
	/** Indica se a doa��o realizada foi do tipo monet�ria ou n�o. */
	private boolean monetaria;
	
	/** Contador do n�mero de doa��es realizadas.  */
	private static int contadorDoacoes = 1;
	
	/** O valor total arrecadado com todas as doa��es monet�rias. */
	private static float totalArrecadadoMonetario;
	
	/** A quantidade total arrecadada com todas as doa��es n�o monet�rias. */
	private static int totalArrecadadoNaoMonetario;
	
	/**
	 * Inicializa os atributos de uma nova Doacao com as informa��es recebidas e gera um identificador �nico 
	 * para a doa��o no sistema.
	 *
	 * @param dataDoacao data na qual a doa��o foi realizada
	 * @param produto o produto doado
	 * @param doador o doador que realizou a doa��o
	 * @param quantidadeDoada a quantidade que foi doada
	 * @param monetaria indica se a doa��o foi monet�ria ou n�o
	 */
	public Doacao(Date dataDoacao, Produto produto, Doador doador, Number quantidadeDoada, boolean monetaria){
		this.geraIdDoacao(); //chamada para m�todo que vai gerar o identificador �nico da doa��o no sistema

		/* Todos os atributos do endere�o s�o inicializado com as respectivas informa��es recebidas durante o instanciamento
		 * do objeto. */
		this.dataDoacao = dataDoacao; 
		this.produto = produto; 
		this.doador = doador; 
		this.quantidadeDoada = quantidadeDoada; 
		this.monetaria = monetaria; 
	}
	
	/**
	 * Gera um n�mero de identifica��o �nico para a doa��o no sistema.
	 */
	private void geraIdDoacao(){
		this.idDoacao = contadorDoacoes; //o ID da doa��o recebe a quantidade de doa��es realizadas
		contadorDoacoes++; //o n�mero total de doa��e realizadas � incrementado com um
	}
	
	/**
	 * M�todo para obten��o do identificador �nico (ID da doa��o) da doa��o no sistema.
	 *
	 * @return o ID da doa��o
	 */
	public Integer getIdDoacao(){
		return this.idDoacao; //retorna a refer�ncia do atributo "idDoacao"
	}
	
	/**
	 * M�todo para obten��o da data de realiza��o da doa��o.
	 *
	 * @return a data da doa��o
	 */
	public Date getDataDoacao(){
		return this.dataDoacao; //retorna a refer�ncia do atributo "dataDoacao"
	}
	
	/**
	 * M�todo para obten��o do doador que realizou a doa��o.
	 *
	 * @return o doador que fez a doa��o
	 */
	public Doador getDoador(){
		return this.doador; //retorna a refer�ncia do atributo "doador"
	}
	
	/**
	 * M�todo para obten��o da quantidade doada.
	 *
	 * @return quantidade doada
	 */
	public Number getQuantidadeDoada(){
		return this.quantidadeDoada; //retorna a refer�ncia do atributo "quantidadeDoada"
	}
	
	/**
	 * M�todo para obten��o do produto doado.
	 *
	 * @return produto doado
	 */
	public Produto getProduto(){
		return this.produto; //retorna a refer�ncia do atributo "produto"
	}
	
	/**
	 * M�todo para verificar se a doa��o � monet�ria.
	 *
	 * @return true, se a doa��o for monet�ria. false, se a doa��o for monet�ria.
	 */
	public boolean isMonetaria(){
		return this.monetaria; //retorna o valor booleano do atributo "monetaria", indicando se a doa��o foi monet�ria ou n�o.
	}
	
	/**
	 * M�todo est�tico para obter o valor total monet�rio arrecadado em todas as doa��es.
	 *
	 * @return valor monet�rio total arrecadado
	 */
	public static float getTotalArrecadadoMonetario(){
		return totalArrecadadoMonetario; //retorna o valor real do atributo est�tico "totalArrecadadoMonetario"
	}
	
	/**
	 * M�todo est�tico para obter a quantidade total n�o monet�ria arrecada em todas as doa��es.
	 *
	 * @return quantidade n�o monet�ria total arrecadada
	 */
	public static int getTotalArrecadadoNaoMonetario(){
		return totalArrecadadoNaoMonetario; //retorna a quantidade inteira do atributo est�tico "totalArrecadadoNaoMonetario"
	}
	

	/**
	 * Configura a data da doa��o.
	 *
	 * @param dataDoacao data de realiza��o da doa��o
	 */
	public void setDataDoacao(Date dataDoacao){
		this.dataDoacao = dataDoacao; //atribui a refer�ncia recebida por par�metro ao atributo "dataDoacao"
	}
	
	/**
	 * Configura o produto doado.
	 *
	 * @param produto produto doado
	 */
	public void setProduto(Produto produto){
		this.produto = produto; //atribui a refer�ncia recebida por par�metro ao atributo "produto"
	}
	
	/**
	 * Configura o doador.
	 *
	 * @param doador doador que realizou a doa��o
	 */
	public void setDoador(Doador doador){
		this.doador = doador; //atribui a refer�ncia recebida por par�metro ao atributo "doador"
	}
	
	/**
	 * Configura a quantidade doada.
	 *
	 * @param quantidadeDoada quantidade que foi doada
	 */
	public void setQuantidadeDoada(Number quantidadeDoada){
		this.quantidadeDoada = quantidadeDoada; //atribui a refer�ncia recebida por par�metro ao atributo "produto"
	}
	
	/**
	 * Configura se a doa��o � monet�ria.
	 *
	 * @param monetaria indicador se a doa��o realizada foi monet�ria ou n�o
	 */
	public void setMonetaria(boolean monetaria){
		this.monetaria = monetaria; //atribui o valor booleano recebido por par�metro ao atributo "monetaria" 
	}
	

	/**
	 * Atualiza (incrementando ou decrementando) o valor monet�rio total arrecadado.
	 *
	 * @param quantidadeDoada valor doado na doa��o
	 * @param incrementar booleano que indica se ser� realizado incremento ou decremento
	 */
	public static void atualizaTotalArrecadadoMonetario(float quantidadeDoada, boolean incrementar){
		if(incrementar == true){ //verifica se ser� realizado incremento
			totalArrecadadoMonetario += quantidadeDoada; //se for uma opera��o de incremento acrescenta a quantidade no atributo est�tico "totalArrecadadoMonetario"
		}else{
			totalArrecadadoMonetario -= quantidadeDoada; //se n�o for uma opera��o de incremento diminui a quantidade no atributo est�tico "totalArrecadadoMonetario"
		}
	}
	
	/**
	 * Atualiza (incrementando ou decrementando) a quantidade n�o monet�ria total arrecadado.
	 *
	 * @param quantidadeDoada quantidade doada na doa��o
	 * @param incrementar booleano que indica se ser� realizado incremento ou decremento
	 */
	public static void atualizaTotalArrecadadoNaoMonetario(int quantidadeDoada, boolean incrementar){
		if(incrementar == true){//verifica se ser� realizado incremento 
			totalArrecadadoNaoMonetario += quantidadeDoada; //se for uma opera��o de incremento acrescenta a quantidade no atributo est�tico "totalArrecadadoNaoMonetario"
		}else{
			totalArrecadadoNaoMonetario -= quantidadeDoada; //se n�o for uma opera��o de incremento diminui a quantidade no atributo est�tico "totalArrecadadoNaoMonetario"
		}
	}



	/**
	 * Realiza a compara��o entre os atributos "quantidadeDoada" da doa��o atual com uma doa��o especificada.
	 * @return -1, se houver sido doada uma maior quantidade no objeto doacao recebido; 1, se essa quantidade for maior na doa��o atual; 0, caso as quantidades sejam iguais. 
	 */
	/* M�todo para realizar a compara��o da quantidade doada presente em dois objetos do tipo doacao. Recebe objeto gen�rico
	 * que � convertido para um objeto doacao e comparando com o objeto doacao que est� sendo referenciado no momento da 
	 * chamada do m�todo. Retorna -1 se a quantidade doada no objeto passado for maior do que a do objeto referenciado, 1
	 * se a quantidade do objeto passado for maior e 0 se as quantidades forem iguais.
	 * @see br.uefs.ecomp.sacMariana.util.Comparavel#comparacao(java.lang.Object)
	 */
	@Override
	public int comparacao(Object obj) {
		Doacao auxiliarDoacao = (Doacao) obj; //auxiliarDoacao recebe a refer�ncia para o objeto que ser� comparado
		
		if(this.getQuantidadeDoada().floatValue() < auxiliarDoacao.getQuantidadeDoada().floatValue()){ //verifica se a quantidade doada na doacao passada por par�metro � maior do que a quantidade doada na doacao atual
			return -1; //caso a condi��o seja satisfeita retorna -1
		} else if(this.getQuantidadeDoada().floatValue() > auxiliarDoacao.getQuantidadeDoada().floatValue()){ //verifica se a quantidade doada na doacao passada por par�metro � menor do que a quantidade doada na doacao atual
			return 1; //caso a condi��o seja satisfeita retorna 1
		} else{
			return 0; //retorna 0 caso nenhuma das condi��es acima sejam satisfeitas, indicando que as quantidades doadas s�o iguais
		}
		
		/* OBS: Nas compara��es acima s�o utilizados os valores float das quantidades pois esses possuem maior precis�o e s�o capazes
		 * de possibilitar a compara��o para quantidades monet�rias e n�o monet�rias. */
		
	}
	


}
