
public class Diretor extends Funcionario {
	
	Diretor(long register, String nome, float freq, Double salario, String[] horarios, int reclamacoes) {
		super(register, nome, freq, salario, horarios, reclamacoes);
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequencia: " + getFreq();
		str += "\nSalario: " + getSalario();
		str += "\nHorarios: ";
		for (int i=0; i<getHorario().length; i++)
			str += "\n " + getHorario()[i];		
		str += "\nReclamacoes: " + getReclam();
		
		return str;
	}
	
}
