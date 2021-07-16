
public class Funcionario extends Pessoa {
	
	private Double salario;
	private int reclamacoes;
	
	Funcionario(long register, String nome, double freq, Double salario, int reclamacoes) {
		super(register, nome, freq);
		this.salario = salario;
		this.reclamacoes = reclamacoes;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + getNome();
		str += "\nN� de Registro: " + getRegister();
		str += "\nFrequ�ncia: " + getFreq();
		str += "\nSalario: " + salario;			
		str += "\nReclamacoes: " + reclamacoes;
		
		return str;
	}
	
	public Double getSalario() {
		return salario;
	}
	
	public int getReclam() {
		return reclamacoes;
	}
	
	void setSalario(Double salario) {
		this.salario = salario;
	}
	
	void setReclam(int reclamacoes) {
		this.reclamacoes = reclamacoes;
	}

}
