
public class Aluno extends Pessoa {
	
	String turma;
	String[] ocorr;
	Double[] notas;
	
	Aluno(long _register, String _nome, float _freq,
			String _turma, String[] _ocorrencias, Double[] _notas) {
		super(_register, _nome, _freq);
		turma = _turma;
		ocorr = _ocorrencias;
		notas = _notas;
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
	
	void setTurma(String _turma) {
		turma = _turma;
	}
	
	void setOcorr(String[] _ocorr) {
		ocorr = _ocorr;
	}
	
	void setNotas(Double[] _notas) {
		notas = _notas;
	}
	

}
