import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GerenciadorDados {
	private String row = new String();
	private Diretor classeParaPermissoes = new Diretor(-1, "GA", 0, null, 0);
	
	/**
	 * Esse m�todo cria um novo aluno dado um array de strings correlacionado � uma linha do csv
	 * @param escola - escola onde o novo aluno vai ser adicionado
	 * @param dados - os dados de uma linha (que � de um aluno) no csv
	 * */
	private void processaLinhaAluno(Escola escola, String[] dados) {
		int ocorrencias = 1;
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Aluno novoAluno = new Aluno(Long.parseLong(dados[1]), dados[2], Float.parseFloat(dados[3]), dados[4], ocorrencias, notas);
		escola.adicionaPessoa(classeParaPermissoes, novoAluno);
	}
	
	/**
	 * Esse m�todo cria um novo funcion�rio dado um array de strings correlacionado � uma linha do csv
	 * @param escola - escola onde o novo aluno vai ser adicionado
	 * @param dados - os dados de uma linha (que � de um funcion�rio) no csv
	 * */
	private void processaLinhaFuncionario(Escola escola, String[] dados) {
		Pessoa novaPessoa = null;
		int reclamacoes = 1;
		String[] turmas = {"turma 1", "turma 2"};
		
		if(dados[1].equals("PROFESSOR")) {
			novaPessoa = new Professor(Long.parseLong(dados[2]), dados[3], Float.parseFloat(dados[4]), Double.parseDouble(dados[5]), reclamacoes, turmas);
		} else if(dados[1].equals("ZELADOR")) {
			novaPessoa = new Zelador(Long.parseLong(dados[2]), dados[3], Float.parseFloat(dados[4]), Double.parseDouble(dados[5]), reclamacoes, "mudar aqui");
		} else if(dados[1].equals("DIRETOR")) {
			novaPessoa = new Diretor(Long.parseLong(dados[2]), dados[3], Float.parseFloat(dados[4]), Double.parseDouble(dados[5]), reclamacoes);
		}
		escola.adicionaPessoa(classeParaPermissoes, novaPessoa);
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
			    } else if(dados[0].equals("FUNCIONARIO")) {
			    	processaLinhaFuncionario(escola, dados);
			    }
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Esse m�todo escreve os dados de um aluno em um csv com a formata��o correta
	 * @param aluno - aluno que ser� escrito
	 * @param escritorcsv - o fileWriter que est� apontando para o arquivo csv
	 * */
	private void escreveAlunosArquivo(Aluno aluno, FileWriter escritorcsv) throws IOException {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		escritorcsv.append("ALUNO"+ ',' +String.valueOf(aluno.getRegister()) + ',' + aluno.getNome() + ',');
		escritorcsv.append(String.valueOf(df.format(aluno.getFreq())) + ',' + aluno.getTurma() + '\n');
	}
	
	/**
	 * Esse m�todo escreve os dados de um professor em um csv com a formata��o correta
	 * @param professor - professor que ser� escrito
	 * @param escritorcsv - o fileWriter que est� apontando para o arquivo csv
	 * */
	private void escreveProfessoresArquivo(Professor professor, FileWriter escritorcsv) throws IOException {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		escritorcsv.append("PROFESSOR"+ ',' +String.valueOf(professor.getRegister()) + ',' + professor.getNome() + ',');
		escritorcsv.append(String.valueOf(df.format(professor.getFreq())) + ',' + professor.getSalario() + '\n');
	}
	
	/**
	 * Esse m�todo escreve os dados de um zelador em um csv com a formata��o correta
	 * @param zelador - zelador que ser� escrito
	 * @param escritorcsv - o fileWriter que est� apontando para o arquivo csv
	 * */
	private void escreveZeladoresArquivo(Zelador zelador, FileWriter escritorcsv) throws IOException {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		escritorcsv.append("ZELADOR"+ ',' +String.valueOf(zelador.getRegister()) + ',' + zelador.getNome() + ',');
		escritorcsv.append(String.valueOf(df.format(zelador.getFreq())) + ',' + zelador.getSalario() + '\n');
	}
	
	/**
	 * Esse m�todo escreve os dados de um diretor em um csv com a formata��o correta
	 * @param diretor - diretor que ser� escrito
	 * @param escritorcsv - o fileWriter que est� apontando para o arquivo csv
	 * */
	private void escreveDiretoresArquivo(Diretor diretor, FileWriter escritorcsv) throws IOException {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		escritorcsv.append("DIRETOR"+ ',' +String.valueOf(diretor.getRegister()) + ',' + diretor.getNome() + ',');
		escritorcsv.append(String.valueOf(df.format(diretor.getFreq())) + ',' + diretor.getSalario() + '\n');
	}
	
	/**
	 * Esse m�todo escreve os dados de uma escola em um arquivo csv
	 * @param escola - escola de onde os dados que ser�o escritos s�o retirados
	 * @param nomeArquivo - nome do arquivo onde tudo isso ser� salvo
	 * */
	public void escrevePessoasArquivo(Escola escola, String nomeArquivo) {
		ArrayList<Pessoa> pessoas = escola.getPessoas();
		try {
			FileWriter escritorcsv = new FileWriter(nomeArquivo);
			for (int i = 0; i < escola.getNPessoas(); i++) {
				if(pessoas.get(i).getClass().equals(Aluno.class)) {
					escreveAlunosArquivo((Aluno) pessoas.get(i), escritorcsv);
					
				} else if (pessoas.get(i) instanceof Funcionario) {
					escritorcsv.append("FUNCIONARIO"+ ',');
					if(pessoas.get(i).getClass().equals(Professor.class)) {
						escreveProfessoresArquivo((Professor) pessoas.get(i), escritorcsv);
					} else if(pessoas.get(i).getClass().equals(Zelador.class)) {
						escreveZeladoresArquivo((Zelador) pessoas.get(i), escritorcsv);
					} else if(pessoas.get(i).getClass().equals(Diretor.class)) {
						escreveDiretoresArquivo((Diretor) pessoas.get(i), escritorcsv);
					}
				}
			}
			escritorcsv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
	}
}
