
public class Zelador extends Funcionario {
	
	private String funcao;
	
	Zelador(long register, String nome, float freq, Double salario, int reclamacoes, String funcao) {
		super(register, nome, freq, salario, reclamacoes);
		this.funcao = funcao;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequencia: " + getFreq();
		str += "\nSalario: " + getSalario();
		str += "\nFunção: " + funcao;	
		str += "\nReclamacoes: " + getReclam();
		
		return str;
	}
	
	void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	String getFuncao() {
		return funcao;
	}

}
