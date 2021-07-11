
public class Funcionario extends Pessoa {
	
	protected Double salario;
	protected String[] horarios;
	protected String[] reclamacoes;
	
	Funcionario(long register, String nome, float freq, Double salario, String[] horarios, String[] reclamacoes) {
		super(register, nome, freq);
		this.salario = salario;
		this.horarios = horarios;
		this.reclamacoes = reclamacoes;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + nome;
		str += "\nNº de Registro: " + register;
		str += "\nFrequência: " + freq;
		str += "\nSalario: " + salario;		
		str += "\nHorarios: ";		
		for (int i=0; i<horarios.length; i++)
			str += "\n " + horarios[i];		
		str += "\nReclamacoes: ";		
		for (int i=0; i<reclamacoes.length; i++)
			str += "\n " + reclamacoes[i];
		
		return str;
	}
	
	public Double getSalario() {
		return salario;
	}
	
	public String[] getHorario() {
		return horarios;
	}
	
	public String[] getReclam() {
		return reclamacoes;
	}
	
	void setSalario(Double salario) {
		this.salario = salario;
	}
	
	void setHorario(String[] horarios) {
		this.horarios = horarios;
	}
	
	void setReclam(String[] reclamacoes) {
		this.reclamacoes = reclamacoes;
	}

}
