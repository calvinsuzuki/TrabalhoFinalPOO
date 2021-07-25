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
	
	@Override
	public String toString() {
		String str;
		
		str = "Pessoa";
		str += "\nNome: " + getNome();
		str += "\nNº de Registro: " + register;
		str += "\nFrequência: " + freq;
		
		return str;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public long getRegister() {
		return register;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double getFreq() {
		return freq;
	}
	
	public void setRegister(long _register) {
		register = _register;
		return;
	}
	
	public void setNome(String _nome) {
		nome = _nome;
		return;
	}
	
	public void setFreq(double _freq) {
		freq = _freq;
		return;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
		return;
	}

}
