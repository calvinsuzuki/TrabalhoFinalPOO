
public class Diretor extends Funcionario {
	
	Diretor(long register, String nome, float freq, Double salario, String[] horarios, String[] reclamacoes) {
		super(register, nome, freq, salario, horarios, reclamacoes);
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + nome;
		str += "\nNº de Registro: " + register;
		str += "\nFrequencia: " + freq;
		str += "\nSalario: " + salario;	
		str += "\nTurmas: ";		
		str += "\nHorarios: ";		
		for (int i=0; i<horarios.length; i++)
			str += "\n " + horarios[i];		
		str += "\nReclamacoes: ";		
		for (int i=0; i<reclamacoes.length; i++)
			str += "\n " + reclamacoes[i];
		
		return str;
	}
	
}
