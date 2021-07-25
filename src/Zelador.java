/** 
 * Implementa a classe Zelador
 * @author Alcino Salviano Cavalcanti, 11892963
 * @author Calvin Suzuki de Camargo, 11232420
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 * @author Pedro Henrique Raymundi, 11795634
 */
public class Zelador extends Funcionario {
	
	private String funcao;
	
	/**
	 * Construtor da classe funcionario
	 * @param register - long - Registro na escola
	 * @param nome - String - Nome da pessoa
	 * @param freq - double - Frequencia da pessoa
	 * @param senha - String - A senha da conta
	 * @param salario - double - Valor do salario
	 * @param reclamacoes - int - Quantidade de reclamacoes
	 * @param funcao - String - Funcao que o zelador empregar� na escola
	 */
	Zelador(long register, String nome, double freq, String senha, double salario, int reclamacoes, String funcao) {
		super(register, nome, freq, senha, salario, reclamacoes);
		this.funcao = funcao;
	}
	
	/**
	 * @return string com todas as caracter�sticas do zelador
	 */
	@Override
	public String toString() {
		String str;

		str = "Zelador";
		str += "\nNome: " + getNome();
		str += "\nN� de Registro: " + getRegister();
		str += "\nFrequencia: " + getFreq();
		str += "\nSalario: " + getSalario();
		str += "\nFun��o: " + funcao;	
		str += "\nReclamacoes: " + getReclam();
		
		return str;
	}
	
	/**
	 * @return fun��o
	 */
	public String getFuncao() {
		return funcao;
	}
	
	/**
	 * @param nova fun��o
	 */
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}
