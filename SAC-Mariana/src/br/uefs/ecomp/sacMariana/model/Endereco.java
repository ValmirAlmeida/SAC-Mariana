package br.uefs.ecomp.sacMariana.model;

// TODO: Auto-generated Javadoc
/**
 * Classe respons�vel por moldar um endere�o no sistema. Possui m�todos para obten��o e configura��o dos atributos desse
 * endere�o.
 * 
 * @author Valmir Vinicius
 * @see java.lang.String
 */
public class Endereco {

	/** Nome da rua. */
	private String rua;
	
	/** N�mero da casa. */
	private int numero;
	
	/** Nome do bairro. */
	private String bairro;
	
	/** CEP da localidade. */
	private String cep;
	
	/** Nome da cidade. */
	private String cidade;
	
	/** Nome do estado. */
	private String estado;
	
	/** Nome do pa�s. */
	private String pais;
	
	/**
	 * Inicializa os atributos de novo endere�o com as informa��es recebidas.
	 *
	 * @param rua nome da rua presente no endere�o
	 * @param numero n�mero presente no endere�o
	 * @param bairro nome do bairro presente no endere�o
	 * @param cep cep presente no endere�o
	 * @param cidade nome da cidade presente no endere�o
	 * @param estado nome do estado presente no endere�o
	 * @param pais nome do pa�s presente no endere�o
	 */
	public Endereco(String rua, int numero, String bairro, String cep, String cidade, String estado, String pais){
		/* Todos os atributos do endere�o s�o inicializado com as respectivas informa��es recebidas durante o instanciamento
		 * do objeto. */
		this.rua = rua; 
		this.numero = numero; 
		this.bairro = bairro; 
		this.cep = cep; 
		this.cidade = cidade; 
		this.estado = estado;
		this.pais = pais;
	}
	
	/**
	 * M�todo para obten��o do nome da rua que faz parte do endere�o.
	 *
	 * @return nome da rua
	 */
	public String getRua() {
		return this.rua; //retorna a refer�ncia do atributo "rua"
	}

	/**
	 * M�todo para obten��o do n�mero que faz parte do endere�o.
	 *
	 * @return n�mero do endere�o
	 */
	public int getNumero() {
		return this.numero; //retorna o inteiro correspondenete ao atributo "numero"
	}

	/**
	 * M�todo para obten��o do nome do bairro que faz parte do endere�o.
	 *
	 * @return nome do bairro
	 */
	public String getBairro() {
		return this.bairro; //retorna a refer�ncia do atributo "bairro"
	}

	/**
	 * M�todo para obten��o do CEP que faz parte do endere�o.
	 *
	 * @return CEP do endere�o
	 */
	public String getCep() {
		return this.cep; //retorna a refer�ncia do atributo "cep"
	}

	/**
	 * M�todo para obten��o do nome da cidade que faz parte do endere�o.
	 *
	 * @return nome da cidade
	 */
	public String getCidade() {
		return this.cidade; //retorna a refer�ncia do atributo "cidade"
	}

	/**
	 * M�todo para obten��o do nome do estado que faz parte do endere�o.
	 *
	 * @return nome do estado
	 */
	public String getEstado() {
		return this.estado; //retorna a refer�ncia do atributo "estado"
	}

	/**
	 * M�todo para obten��o do nome do pa�s que faz parte do endere�o.
	 *
	 * @return nome do pa�s
	 */
	public String getPais() {
		return this.pais; //retorna a refer�ncia do atributo "pais"
	}

	/**
	 * Configura a rua do endere�o. 
	 *
	 * @param rua nome da rua
	 */
	public void setRua(String rua){
		this.rua = rua; //atribui a refer�ncia recebida por par�metro ao atributo "rua"
	}
	
	/**
	 * Configura o n�mero do endere�o.
	 *
	 * @param numero n�mero do endere�o
	 */
	public void setNumero(int numero){
		this.numero = numero; //atribui o inteiro recebido por par�metro ao atributo "numero"
	}
	
	/**
	 * Configura o nome bairro do endere�o.
	 *
	 * @param bairro nome do bairro
	 */
	public void setBairro(String bairro){
		this.bairro = bairro; //atribui a refer�ncia recebida por par�metro ao atributo "bairro"
	}
	
	/**
	 * Configura o CEP do endere�o.
	 *
	 * @param cep cep do endere�o
	 */
	public void setCep(String cep){
		this.cep = cep; //atribui a refer�ncia recebida por par�metro ao atributo "cep"
	}
	
	/**
	 * Configura o nome da cidade do endere�o.
	 *
	 * @param cidade nome da cidade
	 */
	public void setCidade(String cidade){
		this.cidade = cidade; //atribui a refer�ncia recebida por par�metro ao atributo "cep"
	}
	
	/**
	 * Configura o nome do estado do endere�o.
	 *
	 * @param estado nome do estado
	 */
	public void setEstado(String estado){
		this.estado = estado; //atribui a refer�ncia recebida por par�metro ao atributo "estado"
	}
	
	/**
	 * Configura o nome do pa�s do endere�o.
	 *
	 * @param pais nome do pa�s
	 */
	public void setPais(String pais){
		this.pais = pais; //atribui a refer�ncia recebida por par�metro ao atributo "pais"
	} 
	

}
