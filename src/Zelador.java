
public class Zelador extends Funcionario {
	
	private String funcao;
	
	Zelador(long register, String nome, float freq, Double salario, String[] horarios, int reclamacoes, String funcao) {
		super(register, nome, freq, salario, horarios, reclamacoes);
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
		str += "\nHorarios: ";		
		for (int i=0; i<getHorario().length; i++)
			str += "\n " + getHorario()[i];		
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
