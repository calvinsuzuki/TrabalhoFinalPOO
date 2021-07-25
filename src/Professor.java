/** 
 * Implementa a classe Professor
 * @author Alcino Salviano Cavalcanti, 11892963
 * @author Calvin Suzuki de Camargo, 11232420
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 * @author Pedro Henrique Raymundi, 11795634
 */
public class Professor extends Funcionario {

	private String[] turmas;

	/**
	 * Construtor da classe professor
	 * @param register - long - Registro na escola
	 * @param nome - String - Nome da pessoa
	 * @param freq - double - Frequencia da pessoa
	 * @param senha - String - A senha da conta
	 * @param salario - double - Valor do salario
	 * @param reclamacoes - int - Quantidade de reclamacoes
	 * @param turmas - String[] - Turmas que o professor ministra aulas
	 */
	Professor(long register, String nome, double freq, String senha, double salario, int reclamacoes, String[] turmas) {
		super(register, nome, freq, senha, salario, reclamacoes);
		this.turmas = turmas;
	}
	
	/**
	 * @return string com todas as características do professor
	 */
	@Override
	public String toString() {
		String str;

		str = "Professor";
		str += "\nNome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequencia: " + getFreq();
		str += "\nSalario: " + getSalario();
		str += "\nTurmas: ";
		for (int i=0; i<turmas.length; i++)
			str += "\n " + turmas[i];	
		str += "\nReclamacoes: " + getReclam();
		
		return str;
	}
	
	/**
	 * @return lista com todas as turmas do professor
	 */
	public String[] getTurmas() {
		return turmas;
	}
	
	/**
	 * @param nova lista com todas as turmas do professor
	 */
	public void setTurmas(String[] turmas) {
		this.turmas = turmas;
	}
		
}
