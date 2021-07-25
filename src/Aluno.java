/** 
 * Implementa a classe Aluno
 * @author Alcino Salviano Cavalcanti, 11892963
 * @author Calvin Suzuki de Camargo, 11232420
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 * @author Pedro Henrique Raymundi, 11795634
 */
public class Aluno extends Pessoa {
	
	private String turma;
	private int ocorr;
	private Double[] notas;
	
	/**
	 * Construtor da classe aluno
	 * @param register - long - Registro na escola
	 * @param nome - String - Nome da pessoa
	 * @param freq - double - Frequencia da pessoa
	 * @param senha - String - A senha da conta
	 * @param turma - String - Valor do salario
	 * @param reclamacoes - int - Quantidade de reclamacoes
	 * @param turmas - String[] - Turmas que o professor ministra aulas
	 */
	Aluno(long register, String nome, double freq, String senha, String turma, int ocorrencias, Double[] notas) {
		super(register, nome, freq, senha);
		this.turma = turma;
		this.ocorr = ocorrencias;
		this.notas = notas;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Aluno";
		str += "\nNome: " + getNome();
		str += "\nN� de Registro: " + getRegister();
		str += "\nFrequ�ncia: " + getFreq();
		str += "\nTurma: " + turma;			
		str += "\nNotas: ";		
		for (int i=0; i<notas.length; i++)
			str += "\n " + notas[i];
		str += "\nOcorrencias: " + ocorr;
		
		return str;
	}
	
	public String getTurma() {
		return turma;
	}
	
	public int getOcorr() {
		return ocorr;
	}
	
	public Double[] getNotas() {
		return notas;
	}
	
	void setTurma(String turma) {
		this.turma = turma;
	}
	
	void setOcorr(int ocorr) {
		this.ocorr = ocorr;
	}
	
	void setNotas(Double[] notas) {
		this.notas = notas;
	}
	

}
