/** 
 * Implementa a classe Diretor
 * @author Calvin Suzuki de Camargo, 11232420
 */
public class Diretor extends Funcionario {

	/**
	 * Construtor da classe diretor
	 * @param register - long - Registro na escola
	 * @param nome - String - Nome da pessoa
	 * @param freq - double - Frequencia da pessoa
	 * @param senha - String - A senha da conta
	 * @param salario - double - Valor do salario
	 * @param reclamacoes - int - Quantidade de reclamacoes
	 */
	Diretor(long register, String nome, double freq, String senha, double salario, int reclamacoes) {
		super(register, nome, freq, senha, salario, reclamacoes);
	}
	
	/**
	 * @return string com todas as características do diretor
	 */
	@Override
	public String toString() {
		String str;
		
		str = "Diretor";
		str += "\nNome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequencia: " + getFreq();
		str += "\nSalario: " + getSalario();
		str += "\nReclamacoes: " + getReclam();
		
		return str;
	}
	
}
