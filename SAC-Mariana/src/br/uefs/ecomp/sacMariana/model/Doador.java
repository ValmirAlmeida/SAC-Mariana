package br.uefs.ecomp.sacMariana.model;
import java.util.Date;

/**
 * Classe responsável por moldar um doador no sistema. Possui métodos para obtenção, configuração, geração e alteração
 * dos atributos de um doador.
 * 
 * @author Valmir Vinicius
 * @see java.util.Date
 * @see java.lang.String
 * 
 */
public class Doador {

	/** Endereço do doador. */
	private Endereco endereco;
	
	/** Tipo do doador. */
	private String tipoDoador;
	
	/** CPF/CNPJ do doador. */
	private String numCadastroDoador;
	
	/** Nome do doador. */
	private String nomeDoador;
	
	/** Data de nascimento ou fundação. */
	private Date dataNasc;
	
	/** Valor total monetário doado. */
	private float totalDoadoMonetario;
	
	/** Quantidade total não monetária doada. */
	private int totalDoadoNaoMonetario;
	
	/** Percentual em relação a todas as doações monetárias. */
	private float percentualDoacoesMonetarias;
	
	/** Percentual em relação a todas as doações não monetárias. */
	private float percentualDoacoesNaoMonetarias;

	
	/**
	 * Inicializa os atributos de um novo Doador com as informações recebidas e instancia um novo endereço
	 * para esse doador.
	 *
	 * @param numCadastro CPF/CNPJ do doador
	 * @param nomeDoador nome do doador
	 * @param dataNasc data de nascimento/fundação
	 * @param rua rua do endereço do doador
	 * @param numero número do endereço do doador
	 * @param bairro bairro do endereço do doador
	 * @param cep cep do endereço do doador
	 * @param cidade cidade do endereço do doador
	 * @param estado estado do endereço do doador
	 * @param pais país do endereço do doador
	 * @param tipoDoador tipo de doador
	 */
	public Doador(String numCadastro, String nomeDoador, Date dataNasc, String rua, int numero, String bairro, String cep, String cidade, String estado, String pais, String tipoDoador){
		this.endereco = new Endereco(rua, numero, bairro, cep, cidade, estado, pais); //instancia um novo objeto do tipo Endereco e atribui a referência deste objeto ao atributo "endereco"
		
		/* Alguns atributos do Doador são inicializado com as respectivas informações recebidas durante o instanciamento do objeto e 
		 * e outros com valores padrões iniciais.  */
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
	 * Método para obtenção do tipo de doador, isto é, pessoa física ou jurídica..
	 *
	 * @return tipo de doador
	 */
	public String getTipoDoador(){
		return this.tipoDoador; //retorna a referência do atributo "tipoDoador"
	}
	
	/**
	 * Método para obtenção do CPF/CNPJ do doador.
	 *
	 * @return CPF/CNPJ do doador.
	 */
	public String getNumCadastroDoador(){
		return this.numCadastroDoador; //retorna a referência do atributo "numCadastroDoador"
	}
	
	/**
	 * Método para obtenção do nome do doador.
	 *
	 * @return o nome do doador
	 */
	public String getNomeDoador(){
		return this.nomeDoador; //retorna a referência do atributo "nomeDoador"
	}
	
	/**
	 * Método para obtenção da data de nascimento/fundação do doador.
	 *
	 * @return data de nascimento ou fundação
	 */
	public Date getDataNasc(){
		return this.dataNasc; //retorna a referência do atributo "dataNasc"
	}

	/**
	 * Método para obtenção do endereço do doador.
	 *
	 * @return endereço do doador
	 */
	public Endereco getEndereco(){
		return this.endereco; //retorna a referência do atributo "endereco"
	}
	
	/**
	 * Método para obtenção do valor monetário total doado.
	 *
	 * @return valor monetário total doado
	 */
	public float getTotalDoadoMonetario() {
		return this.totalDoadoMonetario; //retorna o valor real do atributo "totalDodadoMonetario"
	}
	
	/**
	 * Método para obtenção da quantidade não monetária total doada.
	 *
	 * @return quantidade não monetária total doada
	 */
	public int getTotalDoadoNaoMonetario() {
		return this.totalDoadoNaoMonetario; //retorna o valor inteiro do atributo "totalDoadoNaoMonetario"
	}
	
	/**
	 * Método para obtenção do percentual de doações monetárias
	 *
	 * @return percentual de doacoes monetarias
	 */
	public float getPercentualDoacoesMonetarias(){
		return this.percentualDoacoesMonetarias; //retorna o valor real do atributo "percentualDoacoesMonetarias"
	}
	
	/**
	 * Método para obtenção do percentual de doações não monetárias.
	 *
	 * @return percentual de doacoes nao monetarias
	 */
	public float getPercentualDoacoesNaoMonetarias(){
		return this.percentualDoacoesNaoMonetarias; //retorna o valor real do atributo "percentualDoacoesNaoMonetarias"
	}
	
	/**
	 * Configura o tipo de doador, isto é, pessoa física ou jurídica. 
	 *
	 * @param tipoDoador tipo do doador 
	 */
	public void setTipoDoador(String tipoDoador){
		this.tipoDoador = tipoDoador; //atribui a referência recebida por parâmetro ao atributo "tipoDoador"
	}
	
	/**
	 * Configura o nome do doador.
	 *
	 * @param nomeDoador nome do doador
	 */
	public void setNomeDoador(String nomeDoador){
		this.nomeDoador = nomeDoador;  //atribui a referência recebida por parâmetro ao atributo "nomeDoador"
	}
	
	/**
	 * Configura a data de nascimento/fundação.
	 *
	 * @param dataNasc data de nascimento/fundação
	 */
	public void setDataNasc(Date dataNasc){
		this.dataNasc = dataNasc;  //atribui a referência recebida por parâmetro ao atributo "dataNasc"
	}
	
	
	/**
	 * Configura o endereço do doador.
	 *
	 * @param rua nome da rua
	 * @param numero número do endereço
	 * @param bairro nome do bairro
	 * @param cep the cep do endereço
	 * @param cidade nome da cidade
	 * @param estado nome do estado
	 * @param pais nome do país
	 */
	public void setEndereco(String rua, int numero, String bairro, String cep, String cidade, String estado, String pais){
		/* Atribui as respectivas informações recebidas por parâmetro ao atributo "endereco". */
		this.endereco.setRua(rua);
		this.endereco.setNumero(numero);
		this.endereco.setBairro(bairro);
		this.endereco.setCep(cep);
		this.endereco.setCidade(cidade);
		this.endereco.setEstado(estado);
		this.endereco.setPais(pais);
	}
	
	/**
	 * Configura o percentual de doações monetárias em relação a todas as doações monetárias do sistema.
	 *
	 * @param percentualMonetario percentual de doações monetárias
	 */
	public void setPercentualDoacoesMonetarias(float percentualMonetario){
		this.percentualDoacoesMonetarias = percentualMonetario; //atribui o valor real recebido por parâmetro ao atributo "percentualDoacoesMonetarias"
	}
	
	/**
	 * Configura o percentual de doações não monetárias em relação a todas as doações não monetárias do sistema.
	 *
	 * @param percentualNaoMonetario percentual de doações não monetárias
	 */
	public void setPercentualDoacoesNaoMonetarias(float percentualNaoMonetario){
		this.percentualDoacoesNaoMonetarias = percentualNaoMonetario; //atribui o valor real recebido por parâmetro ao atributo "percentualDoacoesNaoMonetarias"
	}


	/**
	 * Atualiza o valor total doado monetário.
	 *
	 * @param valor valor que será atualizado
	 * @param incrementar true, se for para acrescer o valor. false, se for para diminuir o valor.
	 */
	public void atualizaTotalDoadoMonetario(float valor, boolean incrementar) {
		if(incrementar == true){ //verifica se será realizada uma operação de incremento no valor monetário total doado
			this.totalDoadoMonetario += valor; //caso a condição seja verdadeira acrescenta o valor recebido por parâmetro no atributo
		}else{
			this.totalDoadoMonetario -= valor; //caso a condição não seja verdadeira diminui o valor recebido por parâmetro do atributo
		}
	}

	/**
	 * Atualiza a quantidade total doada não monetária.
	 *
	 * @param quantidade quantidade que será atualizada
	 * @param incrementar true, se for para acrescer a quantidade. false, se for para diminuir a quantidade.
	 */
	public void atualizaTotalDoadoNaoMonetario(int quantidade, boolean incrementar) {
		if(incrementar == true){ //verifica se será realizada uma operação de incremento na quantidade não monetária total doada
			this.totalDoadoNaoMonetario += quantidade; //caso a condição seja verdadeira acrescenta a quantidade recebida por parâmetro no atributo
		} else{
			this.totalDoadoNaoMonetario -= quantidade; //caso a condição não seja verdadeira diminui a quantidade recebida por parâmetro do atributo
		}
	}

}
