
public class Aluno extends Pessoa {
	
	private String turma;
	private int ocorr;
	private Double[] notas;
	
	Aluno(long register, String nome, float freq, String turma, int ocorrencias, Double[] notas) {
		super(register, nome, freq);
		this.turma = turma;
		this.ocorr = ocorrencias;
		this.notas = notas;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + getNome();
		str += "\nNº de Registro: " + getRegister();
		str += "\nFrequência: " + getFreq();
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
