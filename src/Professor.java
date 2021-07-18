
public class Professor extends Funcionario {

	private String[] turmas;
	
	Professor(long register, String nome, double freq, String senha, double salario, int reclamacoes, String[] turmas) {
		super(register, nome, freq, senha, salario, reclamacoes);
		this.turmas = turmas;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequencia: " + getFreq();
		str += "\nSalario: " + getSalario();
		str += "\nTurmas: ";
		for (int i=0; i<turmas.length; i++)
			str += "\n " + turmas[i];	
		str += "\nReclamacoes: " + getReclam();
		
		return str;
	}
	
	void setTurmas(String[] turmas) {
		this.turmas = turmas;
	}
	
	String[] getTurmas() {
		return turmas;
	}
		
}
