import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class GerenciadorDados {
	String row = new String();
	
	public void processaLinhaAluno(Escola escola, String[] dados) {
		String[] ocorrencias = { "Chato", "Feio", "Esquisito" };
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Aluno novoAluno = new Aluno(Long.parseLong(dados[1]), dados[2], Float.parseFloat(dados[3]), dados[4], ocorrencias, notas);
		escola.adicionaPessoa(novoAluno);
	}
	
	public void leAdicionaPessoasArquivos(Escola escola, String nomeArquivo) {
		BufferedReader leitorcsv;
		try {
			leitorcsv = new BufferedReader(new FileReader(nomeArquivo));
			while ((row = leitorcsv.readLine()) != null) {
			    String[] dados = row.split(",");
			    
			    if(dados.length == 0) {
			    	leitorcsv.close();
			    	return;
			    }
			    
			    if( dados[0].equals("ALUNO")) {
			    	
			    	processaLinhaAluno(escola, dados);
			    }
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void escreveAlunosArquivo(Aluno aluno, FileWriter escritorcsv) throws IOException {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		escritorcsv.append("ALUNO"+ ',' +String.valueOf(aluno.register) + ',' + aluno.nome + ',');
		escritorcsv.append(String.valueOf(df.format(aluno.freq)) + ',' + aluno.turma + '\n');
	}
	
	public void escrevePessoasArquivo(Escola escola, String nomeArquivo) {
		try {
			FileWriter escritorcsv = new FileWriter(nomeArquivo);
			for (int i = 0; i < escola.nPessoas; i++) {
				if(escola.pessoas.get(i).getClass().equals(Aluno.class)) {
					escreveAlunosArquivo((Aluno) escola.pessoas.get(i), escritorcsv);
				}
			}
			escritorcsv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
	}
}
