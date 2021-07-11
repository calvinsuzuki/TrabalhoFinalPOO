
public class Aluno extends Pessoa {
	
	protected String turma;
	protected String[] ocorr;
	protected Double[] notas;
	
	Aluno(long register, String nome, float freq, String turma, String[] ocorrencias, Double[] notas) {
		super(register, nome, freq);
		this.turma = turma;
		this.ocorr = ocorrencias;
		this.notas = notas;
	}
	
	@Override
	public String toString() {
		String str;
		
		str = "Nome: " + nome;
		str += "\nNº de Registro: " + register;
		str += "\nFrequência: " + freq;
		str += "\nTurma: " + turma;		
		str += "\nOcorrencias: ";		
		for (int i=0; i<ocorr.length; i++)
			str += "\n " + ocorr[i];		
		str += "\nNotas: ";		
		for (int i=0; i<notas.length; i++)
			str += "\n " + notas[i];
		
		return str;
	}
	
	public String getTurma() {
		return turma;
	}
	
	public String[] getOcorr() {
		return ocorr;
	}
	
	public Double[] getNotas() {
		return notas;
	}
	
	void setTurma(String turma) {
		this.turma = turma;
	}
	
	void setOcorr(String[] ocorr) {
		this.ocorr = ocorr;
	}
	
	void setNotas(Double[] notas) {
		this.notas = notas;
	}
	

}
