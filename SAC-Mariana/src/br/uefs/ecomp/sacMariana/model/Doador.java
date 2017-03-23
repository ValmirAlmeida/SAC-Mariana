package br.uefs.ecomp.sacMariana.model;
import java.util.Date;

/**
 * Classe respons�vel por moldar um doador no sistema. Possui m�todos para obten��o, configura��o, gera��o e altera��o
 * dos atributos de um doador.
 * 
 * @author Valmir Vinicius
 * @see java.util.Date
 * @see java.lang.String
 * 
 */
public class Doador {

	/** Endere�o do doador. */
	private Endereco endereco;
	
	/** Tipo do doador. */
	private String tipoDoador;
	
	/** CPF/CNPJ do doador. */
	private String numCadastroDoador;
	
	/** Nome do doador. */
	private String nomeDoador;
	
	/** Data de nascimento ou funda��o. */
	private Date dataNasc;
	
	/** Valor total monet�rio doado. */
	private float totalDoadoMonetario;
	
	/** Quantidade total n�o monet�ria doada. */
	private int totalDoadoNaoMonetario;
	
	/** Percentual em rela��o a todas as doa��es monet�rias. */
	private float percentualDoacoesMonetarias;
	
	/** Percentual em rela��o a todas as doa��es n�o monet�rias. */
	private float percentualDoacoesNaoMonetarias;

	
	/**
	 * Inicializa os atributos de um novo Doador com as informa��es recebidas e instancia um novo endere�o
	 * para esse doador.
	 *
	 * @param numCadastro CPF/CNPJ do doador
	 * @param nomeDoador nome do doador
	 * @param dataNasc data de nascimento/funda��o
	 * @param rua rua do endere�o do doador
	 * @param numero n�mero do endere�o do doador
	 * @param bairro bairro do endere�o do doador
	 * @param cep cep do endere�o do doador
	 * @param cidade cidade do endere�o do doador
	 * @param estado estado do endere�o do doador
	 * @param pais pa�s do endere�o do doador
	 * @param tipoDoador tipo de doador
	 */
	public Doador(String numCadastro, String nomeDoador, Date dataNasc, String rua, int numero, String bairro, String cep, String cidade, String estado, String pais, String tipoDoador){
		this.endereco = new Endereco(rua, numero, bairro, cep, cidade, estado, pais); //instancia um novo objeto do tipo Endereco e atribui a refer�ncia deste objeto ao atributo "endereco"
		
		/* Alguns atributos do Doador s�o inicializado com as respectivas informa��es recebidas durante o instanciamento do objeto e 
		 * e outros com valores padr�es iniciais.  */
		this.tipoDoador = tipoDoador;
		this.numCadastroDoador = numCadastro;
		this.nomeDoador = nomeDoador;
		this.dataNasc = dataNasc;
		this.totalDoadoMonetario = 0;
		this.totalDoadoNaoMonetario = 0;
		this.percentualDoacoesMonetarias = 0;
		this.percentualDoacoesNaoMonetarias = 0;
	}
	
	/**
	 * M�todo para obten��o do tipo de doador, isto �, pessoa f�sica ou jur�dica..
	 *
	 * @return tipo de doador
	 */
	public String getTipoDoador(){
		return this.tipoDoador; //retorna a refer�ncia do atributo "tipoDoador"
	}
	
	/**
	 * M�todo para obten��o do CPF/CNPJ do doador.
	 *
	 * @return CPF/CNPJ do doador.
	 */
	public String getNumCadastroDoador(){
		return this.numCadastroDoador; //retorna a refer�ncia do atributo "numCadastroDoador"
	}
	
	/**
	 * M�todo para obten��o do nome do doador.
	 *
	 * @return o nome do doador
	 */
	public String getNomeDoador(){
		return this.nomeDoador; //retorna a refer�ncia do atributo "nomeDoador"
	}
	
	/**
	 * M�todo para obten��o da data de nascimento/funda��o do doador.
	 *
	 * @return data de nascimento ou funda��o
	 */
	public Date getDataNasc(){
		return this.dataNasc; //retorna a refer�ncia do atributo "dataNasc"
	}

	/**
	 * M�todo para obten��o do endere�o do doador.
	 *
	 * @return endere�o do doador
	 */
	public Endereco getEndereco(){
		return this.endereco; //retorna a refer�ncia do atributo "endereco"
	}
	
	/**
	 * M�todo para obten��o do valor monet�rio total doado.
	 *
	 * @return valor monet�rio total doado
	 */
	public float getTotalDoadoMonetario() {
		return this.totalDoadoMonetario; //retorna o valor real do atributo "totalDodadoMonetario"
	}
	
	/**
	 * M�todo para obten��o da quantidade n�o monet�ria total doada.
	 *
	 * @return quantidade n�o monet�ria total doada
	 */
	public int getTotalDoadoNaoMonetario() {
		return this.totalDoadoNaoMonetario; //retorna o valor inteiro do atributo "totalDoadoNaoMonetario"
	}
	
	/**
	 * M�todo para obten��o do percentual de doa��es monet�rias
	 *
	 * @return percentual de doacoes monetarias
	 */
	public float getPercentualDoacoesMonetarias(){
		return this.percentualDoacoesMonetarias; //retorna o valor real do atributo "percentualDoacoesMonetarias"
	}
	
	/**
	 * M�todo para obten��o do percentual de doa��es n�o monet�rias.
	 *
	 * @return percentual de doacoes nao monetarias
	 */
	public float getPercentualDoacoesNaoMonetarias(){
		return this.percentualDoacoesNaoMonetarias; //retorna o valor real do atributo "percentualDoacoesNaoMonetarias"
	}
	
	/**
	 * Configura o tipo de doador, isto �, pessoa f�sica ou jur�dica. 
	 *
	 * @param tipoDoador tipo do doador 
	 */
	public void setTipoDoador(String tipoDoador){
		this.tipoDoador = tipoDoador; //atribui a refer�ncia recebida por par�metro ao atributo "tipoDoador"
	}
	
	/**
	 * Configura o nome do doador.
	 *
	 * @param nomeDoador nome do doador
	 */
	public void setNomeDoador(String nomeDoador){
		this.nomeDoador = nomeDoador;  //atribui a refer�ncia recebida por par�metro ao atributo "nomeDoador"
	}
	
	/**
	 * Configura a data de nascimento/funda��o.
	 *
	 * @param dataNasc data de nascimento/funda��o
	 */
	public void setDataNasc(Date dataNasc){
		this.dataNasc = dataNasc;  //atribui a refer�ncia recebida por par�metro ao atributo "dataNasc"
	}
	
	
	/**
	 * Configura o endere�o do doador.
	 *
	 * @param rua nome da rua
	 * @param numero n�mero do endere�o
	 * @param bairro nome do bairro
	 * @param cep the cep do endere�o
	 * @param cidade nome da cidade
	 * @param estado nome do estado
	 * @param pais nome do pa�s
	 */
	public void setEndereco(String rua, int numero, String bairro, String cep, String cidade, String estado, String pais){
		/* Atribui as respectivas informa��es recebidas por par�metro ao atributo "endereco". */
		this.endereco.setRua(rua);
		this.endereco.setNumero(numero);
		this.endereco.setBairro(bairro);
		this.endereco.setCep(cep);
		this.endereco.setCidade(cidade);
		this.endereco.setEstado(estado);
		this.endereco.setPais(pais);
	}
	
	/**
	 * Configura o percentual de doa��es monet�rias em rela��o a todas as doa��es monet�rias do sistema.
	 *
	 * @param percentualMonetario percentual de doa��es monet�rias
	 */
	public void setPercentualDoacoesMonetarias(float percentualMonetario){
		this.percentualDoacoesMonetarias = percentualMonetario; //atribui o valor real recebido por par�metro ao atributo "percentualDoacoesMonetarias"
	}
	
	/**
	 * Configura o percentual de doa��es n�o monet�rias em rela��o a todas as doa��es n�o monet�rias do sistema.
	 *
	 * @param percentualNaoMonetario percentual de doa��es n�o monet�rias
	 */
	public void setPercentualDoacoesNaoMonetarias(float percentualNaoMonetario){
		this.percentualDoacoesNaoMonetarias = percentualNaoMonetario; //atribui o valor real recebido por par�metro ao atributo "percentualDoacoesNaoMonetarias"
	}


	/**
	 * Atualiza o valor total doado monet�rio.
	 *
	 * @param valor valor que ser� atualizado
	 * @param incrementar true, se for para acrescer o valor. false, se for para diminuir o valor.
	 */
	public void atualizaTotalDoadoMonetario(float valor, boolean incrementar) {
		if(incrementar == true){ //verifica se ser� realizada uma opera��o de incremento no valor monet�rio total doado
			this.totalDoadoMonetario += valor; //caso a condi��o seja verdadeira acrescenta o valor recebido por par�metro no atributo
		}else{
			this.totalDoadoMonetario -= valor; //caso a condi��o n�o seja verdadeira diminui o valor recebido por par�metro do atributo
		}
	}

	/**
	 * Atualiza a quantidade total doada n�o monet�ria.
	 *
	 * @param quantidade quantidade que ser� atualizada
	 * @param incrementar true, se for para acrescer a quantidade. false, se for para diminuir a quantidade.
	 */
	public void atualizaTotalDoadoNaoMonetario(int quantidade, boolean incrementar) {
		if(incrementar == true){ //verifica se ser� realizada uma opera��o de incremento na quantidade n�o monet�ria total doada
			this.totalDoadoNaoMonetario += quantidade; //caso a condi��o seja verdadeira acrescenta a quantidade recebida por par�metro no atributo
		} else{
			this.totalDoadoNaoMonetario -= quantidade; //caso a condi��o n�o seja verdadeira diminui a quantidade recebida por par�metro do atributo
		}
	}

}
