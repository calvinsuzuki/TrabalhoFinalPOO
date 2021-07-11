
public class Professor extends Funcionario {

	private String[] turmas;
	
	Professor(long register, String nome, float freq, Double salario, String[] horarios, String[] reclamacoes, String[] turmas) {
		super(register, nome, freq, salario, horarios, reclamacoes);
		this.turmas = turmas;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + nome;
		str += "\nNº de Registro: " + register;
		str += "\nFrequencia: " + freq;
		str += "\nSalario: " + salario;	
		str += "\nTurmas: ";		
		for (int i=0; i<turmas.length; i++)
			str += "\n " + turmas[i];	
		str += "\nHorarios: ";		
		for (int i=0; i<horarios.length; i++)
			str += "\n " + horarios[i];		
		str += "\nReclamacoes: ";		
		for (int i=0; i<reclamacoes.length; i++)
			str += "\n " + reclamacoes[i];
		
		return str;
	}
	
	void setTurmas(String[] turmas) {
		this.turmas = turmas;
	}
	
	String[] getTurmas() {
		return turmas;
	}
		
}
