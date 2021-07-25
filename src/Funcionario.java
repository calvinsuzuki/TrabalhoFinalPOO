/** 
 * Implementa a classe Funcionario
 * @author Alcino Salviano Cavalcanti, 11892963
 * @author Calvin Suzuki de Camargo, 11232420
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 * @author Pedro Henrique Raymundi, 11795634
 */
public class Funcionario extends Pessoa {
	
	private Double salario;
	private int reclamacoes;
	
	/**
	 * Construtor da classe funcionario
	 * @param register - long - Registro na escola
	 * @param nome - String - Nome da pessoa
	 * @param freq - double - Frequencia da pessoa
	 * @param senha - String - A senha da conta
	 * @param salario - double - Valor do salario
	 * @param reclamacoes - int - Quantidade de reclamacoes
	 */
	Funcionario(long register, String nome, double freq, String senha, double salario, int reclamacoes) {
		super(register, nome, freq, senha);
		this.salario = salario;
		this.reclamacoes = reclamacoes;
	}
	
	/**
	 * @return string com todas as características do funcionario
	 */
	@Override
	public String toString() {
		String str;
		
		str = "Funcionario";
		str += "\nNome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequência: " + getFreq();
		str += "\nSalario: " + salario;			
		str += "\nReclamacoes: " + reclamacoes;
		
		return str;
	}
	
	/**
	 * @return salario
	 */
	public Double getSalario() {
		return salario;
	}
	
	/**
	 * @return número de reclamações
	 */
	public int getReclam() {
		return reclamacoes;
	}
	
	/**
	 * @param novo salario
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	/**
	 * @param novo número de reclamações
	 */
	public void setReclam(int reclamacoes) {
		this.reclamacoes = reclamacoes;
	}

}
