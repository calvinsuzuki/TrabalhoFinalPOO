
public class Pessoa {
	
	private long register;
	private String nome, senha;
	private double freq;
	
	/**
	 * Construtor da classe pessoa
	 * @param _register - Numero de registro na escola
	 * @param _nome - Nome da pessoa
	 * @param _freq - Frequencia da pessoa
	 */
	Pessoa(long register, String nome, double freq, String senha) {
		this.register = register;
		this.nome = nome;
		this.freq = freq;
		this.senha = senha;
	}
	
	/**
	 * Funcao retorna os dados de pessoa em String
	 */
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + nome;
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
