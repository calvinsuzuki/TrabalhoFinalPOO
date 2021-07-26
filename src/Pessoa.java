/** 
 * Implementa a classe Pessoa
 * @author Alcino Salviano Cavalcanti, 11892963
 * @author Calvin Suzuki de Camargo, 11232420
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 * @author Pedro Henrique Raymundi, 11795634
 */
public class Pessoa {
	
	private long register;
	private String nome, senha;
	private double freq;
	
	/**
	 * Construtor da classe pessoa
	 * @param register - long - Numero de registro na escola
	 * @param nome - String - Nome da pessoa
	 * @param freq - double - Frequencia da pessoa
	 * @param senha - String - A senha da conta
	 */
	Pessoa(long register, String nome, double freq, String senha) {
		this.register = register;
		this.nome = nome;
		this.freq = freq;
		this.senha = senha;
	}
	
	/**
	 * @return string com todas as características da pessoa
	 */
	@Override
	public String toString() {
		String str;
		
		str = "Pessoa";
		str += "\nNome: " + getNome();
		str += "\nNº de Registro: " + register;
		str += "\nFrequência: " + freq;
		
		return str;
	}
	
	/**
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * @return registro
	 */
	public long getRegister() {
		return register;
	}
	
	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @return frequencia
	 */
	public double getFreq() {
		return freq;
	}
	
	/**
	 * @param _register - novo registro
	 */
	public void setRegister(long _register) {
		register = _register;
		return;
	}
	
	/**
	 * @param _nome - novo nome
	 */
	public void setNome(String _nome) {
		nome = _nome;
		return;
	}
	
	/**
	 * @param _freq - nova frequencia
	 */
	public void setFreq(double _freq) {
		freq = _freq;
		return;
	}
	
	/**
	 * @param senha - nova senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
		return;
	}
	
}
