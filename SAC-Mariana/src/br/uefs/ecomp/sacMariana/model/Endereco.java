package br.uefs.ecomp.sacMariana.model;

// TODO: Auto-generated Javadoc
/**
 * Classe responsável por moldar um endereço no sistema. Possui métodos para obtenção e configuração dos atributos desse
 * endereço.
 * 
 * @author Valmir Vinicius
 * @see java.lang.String
 */
public class Endereco {

	/** Nome da rua. */
	private String rua;
	
	/** Número da casa. */
	private int numero;
	
	/** Nome do bairro. */
	private String bairro;
	
	/** CEP da localidade. */
	private String cep;
	
	/** Nome da cidade. */
	private String cidade;
	
	/** Nome do estado. */
	private String estado;
	
	/** Nome do país. */
	private String pais;
	
	/**
	 * Inicializa os atributos de novo endereço com as informações recebidas.
	 *
	 * @param rua nome da rua presente no endereço
	 * @param numero número presente no endereço
	 * @param bairro nome do bairro presente no endereço
	 * @param cep cep presente no endereço
	 * @param cidade nome da cidade presente no endereço
	 * @param estado nome do estado presente no endereço
	 * @param pais nome do país presente no endereço
	 */
	public Endereco(String rua, int numero, String bairro, String cep, String cidade, String estado, String pais){
		/* Todos os atributos do endereço são inicializado com as respectivas informações recebidas durante o instanciamento
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
	 * Método para obtenção do nome da rua que faz parte do endereço.
	 *
	 * @return nome da rua
	 */
	public String getRua() {
		return this.rua; //retorna a referência do atributo "rua"
	}

	/**
	 * Método para obtenção do número que faz parte do endereço.
	 *
	 * @return número do endereço
	 */
	public int getNumero() {
		return this.numero; //retorna o inteiro correspondenete ao atributo "numero"
	}

	/**
	 * Método para obtenção do nome do bairro que faz parte do endereço.
	 *
	 * @return nome do bairro
	 */
	public String getBairro() {
		return this.bairro; //retorna a referência do atributo "bairro"
	}

	/**
	 * Método para obtenção do CEP que faz parte do endereço.
	 *
	 * @return CEP do endereço
	 */
	public String getCep() {
		return this.cep; //retorna a referência do atributo "cep"
	}

	/**
	 * Método para obtenção do nome da cidade que faz parte do endereço.
	 *
	 * @return nome da cidade
	 */
	public String getCidade() {
		return this.cidade; //retorna a referência do atributo "cidade"
	}

	/**
	 * Método para obtenção do nome do estado que faz parte do endereço.
	 *
	 * @return nome do estado
	 */
	public String getEstado() {
		return this.estado; //retorna a referência do atributo "estado"
	}

	/**
	 * Método para obtenção do nome do país que faz parte do endereço.
	 *
	 * @return nome do país
	 */
	public String getPais() {
		return this.pais; //retorna a referência do atributo "pais"
	}

	/**
	 * Configura a rua do endereço. 
	 *
	 * @param rua nome da rua
	 */
	public void setRua(String rua){
		this.rua = rua; //atribui a referência recebida por parâmetro ao atributo "rua"
	}
	
	/**
	 * Configura o número do endereço.
	 *
	 * @param numero número do endereço
	 */
	public void setNumero(int numero){
		this.numero = numero; //atribui o inteiro recebido por parâmetro ao atributo "numero"
	}
	
	/**
	 * Configura o nome bairro do endereço.
	 *
	 * @param bairro nome do bairro
	 */
	public void setBairro(String bairro){
		this.bairro = bairro; //atribui a referência recebida por parâmetro ao atributo "bairro"
	}
	
	/**
	 * Configura o CEP do endereço.
	 *
	 * @param cep cep do endereço
	 */
	public void setCep(String cep){
		this.cep = cep; //atribui a referência recebida por parâmetro ao atributo "cep"
	}
	
	/**
	 * Configura o nome da cidade do endereço.
	 *
	 * @param cidade nome da cidade
	 */
	public void setCidade(String cidade){
		this.cidade = cidade; //atribui a referência recebida por parâmetro ao atributo "cep"
	}
	
	/**
	 * Configura o nome do estado do endereço.
	 *
	 * @param estado nome do estado
	 */
	public void setEstado(String estado){
		this.estado = estado; //atribui a referência recebida por parâmetro ao atributo "estado"
	}
	
	/**
	 * Configura o nome do país do endereço.
	 *
	 * @param pais nome do país
	 */
	public void setPais(String pais){
		this.pais = pais; //atribui a referência recebida por parâmetro ao atributo "pais"
	} 
	

}
