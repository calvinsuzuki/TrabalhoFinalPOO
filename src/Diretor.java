
public class Diretor extends Funcionario {
	
	Diretor(long register, String nome, double freq, String senha, double salario, int reclamacoes) {
		super(register, nome, freq, senha, salario, reclamacoes);
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequencia: " + getFreq();
		str += "\nSalario: " + getSalario();
		str += "\nReclamacoes: " + getReclam();
		
		return str;
	}
	
}
