
public class Pessoa {
	
	private long register;
	private String nome;
	private double freq;
	
	/**
	 * Construtor da classe pessoa
	 * @param _register - Numero de registro na escola
	 * @param _nome - Nome da pessoa
	 * @param _freq - Frequencia da pessoa
	 */
	Pessoa(long _register, String _nome, float _freq) {
		register = _register;
		nome = _nome;
		freq = _freq;
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
	
	public void setFreq(float _freq) {
		freq = _freq;
		return;
	}

}
