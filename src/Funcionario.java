
public class Funcionario extends Pessoa {
	
	private Double salario;
	private String[] horarios;
	private int reclamacoes;
	
	Funcionario(long register, String nome, float freq, Double salario, String[] horarios, int reclamacoes) {
		super(register, nome, freq);
		this.salario = salario;
		this.horarios = horarios;
		this.reclamacoes = reclamacoes;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequência: " + getFreq();
		str += "\nSalario: " + salario;		
		str += "\nHorarios: ";		
		for (int i=0; i<horarios.length; i++)
			str += "\n " + horarios[i];		
		str += "\nReclamacoes: " + reclamacoes;
		
		return str;
	}
	
	public Double getSalario() {
		return salario;
	}
	
	public String[] getHorario() {
		return horarios;
	}
	
	public int getReclam() {
		return reclamacoes;
	}
	
	void setSalario(Double salario) {
		this.salario = salario;
	}
	
	void setHorario(String[] horarios) {
		this.horarios = horarios;
	}
	
	void setReclam(int reclamacoes) {
		this.reclamacoes = reclamacoes;
	}

}
