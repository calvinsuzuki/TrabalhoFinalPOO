import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.RegistroUsadoException;
import exceptions.UsuarioLogadoInvalidoException;

/** 
 * Implementa a classe para gerenciar os dados salvos no arquivo
 * @author Alcino Salviano Cavalcanti, 11892963
 * @author Calvin Suzuki de Camargo, 11232420
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 * @author Pedro Henrique Raymundi, 11795634
 */
public class GerenciadorDados {
	private String row = new String();
	private Diretor classeParaPermissoes = new Diretor(-1, "GA", 0, "senha", 0, 0);
	
	/**
	 * Esse método cria um novo aluno dado um array de strings correlacionado à uma linha do csv
	 * @param escola - escola onde o novo aluno vai ser adicionado
	 * @param dados - os dados de uma linha (que é de um aluno) no csv
	 * */
	private void processaLinhaAluno(Escola escola, String[] dados) {
		Double[] notas = new Double[6];
		for(int i = 7; i < 13; i++ ) {
			notas[i-7] = Double.parseDouble(dados[i]);
		}
		Aluno novoAluno = new Aluno(Long.parseLong(dados[2]), dados[3], Double.parseDouble(dados[4]), dados[1], dados[5], Integer.parseInt(dados[6]), notas);
		
		try {
			escola.adicionaPessoa(classeParaPermissoes, novoAluno);
		} catch (RegistroUsadoException e) {
			System.out.println(e.getMessage());
			return;
		} catch (UsuarioLogadoInvalidoException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	/**
	 * Esse método cria um novo funcionário dado um array de strings correlacionado à uma linha do csv
	 * @param escola - escola onde o novo aluno vai ser adicionado
	 * @param dados - os dados de uma linha (que é de um funcionário) no csv
	 * */
	private void processaLinhaFuncionario(Escola escola, String[] dados) {
		Pessoa novaPessoa = null;
		String[] dadosExtra = new String[dados.length-8];
		
		for(int i = 8; i < dados.length; i++) {
			dadosExtra[i-8] = dados[i];
		}
		
		if(dados[1].equals("PROFESSOR")) {
			novaPessoa = new Professor(Long.parseLong(dados[3]), dados[4], Double.parseDouble(dados[5]), dados[2], Double.parseDouble(dados[6]), Integer.parseInt(dados[7]), dadosExtra);
		} else if(dados[1].equals("ZELADOR")) {
			novaPessoa = new Zelador(Long.parseLong(dados[3]), dados[4], Double.parseDouble(dados[5]), dados[2], Double.parseDouble(dados[6]), Integer.parseInt(dados[7]), dadosExtra[0]);
		} else if(dados[1].equals("DIRETOR")) {
			novaPessoa = new Diretor(Long.parseLong(dados[3]), dados[4], Double.parseDouble(dados[5]), dados[2], Double.parseDouble(dados[6]), Integer.parseInt(dados[7]));
		}
		
		try {
			escola.adicionaPessoa(classeParaPermissoes, novaPessoa);
		}  catch (RegistroUsadoException e) {
			System.out.println(e.getMessage());
			return;
		} catch (UsuarioLogadoInvalidoException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	/**
	 * Coloca todas as pessoas no arquivo csv dentro da escola
	 * @param escola - escola é onde tudo vai ser adicionado
	 * @param nomeArquivo - nomeArquivo é o path do arquivo de dados
	 * */
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
	 * Esse método escreve os dados de um aluno em um csv com a formatação correta
	 * @param aluno - aluno que será escrito
	 * @param escritorcsv - o fileWriter que está apontando para o arquivo csv
	 * @throws IOException - exceção de input e output
	 * */
	private void escreveAlunosArquivo(Aluno aluno, FileWriter escritorcsv) throws IOException {
		Double[] notas = aluno.getNotas();
		
		escritorcsv.append("ALUNO"+ ',' + aluno.getSenha() + ',' +String.valueOf(aluno.getRegister()) + ',' + aluno.getNome() + ',');
		escritorcsv.append(String.valueOf(aluno.getFreq()) + ',' + aluno.getTurma() + ',' + aluno.getOcorr());
		for(int i = 0; i < 6; i++) {
			escritorcsv.append(',' + String.valueOf(notas[i]));
		}
		escritorcsv.append('\n');
	}
	
	/**
	 * Esse método escreve os dados de um professor em um csv com a formatação correta
	 * @param professor - professor que será escrito
	 * @param escritorcsv - o fileWriter que está apontando para o arquivo csv
	 * @throws IOException - exceção de input e output
	 * */
	private void escreveProfessoresArquivo(Professor professor, FileWriter escritorcsv) throws IOException {
		String[] turmas = professor.getTurmas();
		
		escritorcsv.append("PROFESSOR"+ ',' + professor.getSenha() + ',' +String.valueOf(professor.getRegister()) + ',' + professor.getNome() + ',');
		escritorcsv.append(String.valueOf(professor.getFreq()) + ',' + professor.getSalario() + ',' + professor.getReclam());
		for(int i = 0; i < turmas.length; i++) {
			escritorcsv.append(',' + turmas[i]);
		}
		escritorcsv.append('\n');
	}
	
	/**
	 * Esse método escreve os dados de um zelador em um csv com a formatação correta
	 * @param zelador - zelador que será escrito
	 * @param escritorcsv - o fileWriter que está apontando para o arquivo csv
	 * @throws IOException - exceção de input e output
	 * */
	private void escreveZeladoresArquivo(Zelador zelador, FileWriter escritorcsv) throws IOException {
		escritorcsv.append("ZELADOR"+ ',' + zelador.getSenha() + ',' +String.valueOf(zelador.getRegister()) + ',' + zelador.getNome() + ',');
		escritorcsv.append(String.valueOf(zelador.getFreq()) + ',' + zelador.getSalario() + ',' + zelador.getReclam() + ',' + zelador.getFuncao() + '\n');
	}
	
	/**
	 * Esse método escreve os dados de um diretor em um csv com a formatação correta
	 * @param diretor - diretor que será escrito
	 * @param escritorcsv - o fileWriter que está apontando para o arquivo csv
	 * @throws IOException - exceção de input e output
	 * */
	private void escreveDiretoresArquivo(Diretor diretor, FileWriter escritorcsv) throws IOException {
		escritorcsv.append("DIRETOR"+ ',' + diretor.getSenha() + ',' +String.valueOf(diretor.getRegister()) + ',' + diretor.getNome() + ',');
		escritorcsv.append(String.valueOf(diretor.getFreq()) + ',' + diretor.getSalario() + ',' + diretor.getReclam() + '\n');
	}
	
	/**
	 * Esse método escreve os dados de uma escola em um arquivo csv
	 * @param escola - escola de onde os dados que serão escritos são retirados
	 * @param nomeArquivo - nome do arquivo onde tudo isso será salvo
	 * */
	public void escrevePessoasArquivo(Escola escola, String nomeArquivo) {
		ArrayList<Pessoa> pessoas = escola.getPessoas(new boolean[] {true, true, true, true});
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
