
public class Zelador extends Funcionario {
	
	private String funcao;
	
	Zelador(long register, String nome, float freq, Double salario, String[] horarios, String[] reclamacoes, String funcao) {
		super(register, nome, freq, salario, horarios, reclamacoes);
		this.funcao = funcao;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + nome;
		str += "\nNº de Registro: " + register;
		str += "\nFrequencia: " + freq;
		str += "\nSalario: " + salario;	
		for (int i=0; i<horarios.length; i++)
			str += "\n " + horarios[i];	
		str += "\nHorarios: ";		
		for (int i=0; i<horarios.length; i++)
			str += "\n " + horarios[i];		
		str += "\nReclamacoes: ";		
		for (int i=0; i<reclamacoes.length; i++)
			str += "\n " + reclamacoes[i];
		
		return str;
	}
	
	void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	String getFuncao() {
		return funcao;
	}

}
