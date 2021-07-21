import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exceptions.RegistroUsadoException;
import exceptions.UsuarioLogadoInvalidoException;

public class Escola {
	private int nPessoas = 0;
	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	/**
	 * @return n�mero de pessoas na escola
	 * */
	public int getNPessoas() {
		return nPessoas;
	}

	/**
	 * @param classesRetorno- array de booleans com 4 elementos, equivalendo respectivamente � Aluno, Professor,
	 * Zelador, Diretor. Passe 'true' para o boolean equivalente para receber Pessoas condizentes � classe
	 * @return Arraylist de pessoas na escola
	 * */
	public ArrayList<Pessoa> getPessoas(boolean[] classesRetorno) {
		ArrayList<Pessoa> pessoasRetorno = new ArrayList<Pessoa>();
		Pessoa[] classesDesejadas = new Pessoa[4];
		
		if(classesRetorno[0]) {classesDesejadas[0] = new Aluno(0, null, 0, null, null, 0, null);} else {classesDesejadas[0] = new Pessoa(0, null, 0, null);}
		if(classesRetorno[1]) {classesDesejadas[1] = new Professor(0, null, 0, null, 0, 0, null);} else {classesDesejadas[1] = new Pessoa(0, null, 0, null);}
		if(classesRetorno[2]) {classesDesejadas[2] = new Zelador(0, null, 0, null, 0, 0, null);} else {classesDesejadas[2] = new Pessoa(0, null, 0, null);}
		if(classesRetorno[3]) {classesDesejadas[3] = new Diretor(0, null, 0, null, 0, 0);} else {classesDesejadas[3] = new Pessoa(0, null, 0, null);}
		
		for(int i = 0; i < nPessoas; i++) {
			for(int j = 0; j < 4; j++) {
				if(pessoas.get(i).getClass().equals(classesDesejadas[j].getClass())) {
					pessoasRetorno.add(pessoas.get(i));
				}
			}
		}
		
		return pessoasRetorno;
	}
	
	/**
	 * Fun��o para adicionar, ordenadamente por registro, uma pessoa na escola.
	 * @param pessoaLogada - Para poder adicionar algu�m, pessoaLogada deve ser Diretor
	 * @param novaPessoa - pode ser de qualquer subclasse, vai adicionar para o sistema da escola
	 * */
	public void adicionaPessoa(Pessoa pessoaLogada, Pessoa novaPessoa) {
		try {
			if(!(pessoaLogada.getClass().equals(Diretor.class))) {
				throw new UsuarioLogadoInvalidoException();
			}
		
			if(this.buscaPessoa(novaPessoa.getRegister()) != null) {
				throw new RegistroUsadoException();
			}			
			
			pessoas.add(novaPessoa);
			nPessoas++;
		}  catch (RegistroUsadoException e) {
			System.out.println(e.getMessage());
			return;
		} catch (UsuarioLogadoInvalidoException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	/**
	 * Remove uma pessoa da escola com base em seu registro.
	 * @param pessoaLogada - Para poder remover algu�m, pessoaLogada deve ser diretor
	 * @param registroPessoaRemovida - � o registro da pessoa que o m�todo remove, pode ser
	 * obtido pelo usu�rio, ou feita uma busca pelo nome usando outra fun��o e depois passada o registro
	 * aqui
	 * */
	public void removePessoa(Pessoa pessoaLogada, long registroPessoaRemovida) {
		int index = -1;
		try {
			for(int i = 0; i < nPessoas; i++) {
				if(pessoas.get(i).getRegister() == registroPessoaRemovida) {
					index = i;
					break;
				}
			}
			if(index == -1) {
				return;
			}
			if(!(pessoaLogada.getClass().equals(Diretor.class))) {
				throw new UsuarioLogadoInvalidoException();
			}

			pessoas.remove(index);
			nPessoas--;
		}  catch (UsuarioLogadoInvalidoException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	/**
	 * Fun��o que repassa os dados usados para a impress�o de todos na escola
	 * Recebe 'true' para o boolean equivalente para receber string condizentes � classe
	 * Em um array de booleans tamanho 4, sendo respectivamente � Aluno, Professor, Zelador, Diretor. 
	 * @param classesRetorno
	 * @return Retorna um array de strings com os dados b�sicos de todos os usu�rios
	 * */
	public String[] imprimePessoas(boolean[] classesRetorno) {
		ArrayList<Pessoa> pessoasRetorno = new ArrayList<Pessoa>();
		Pessoa[] classesDesejadas = new Pessoa[4];
		
		if(classesRetorno[0]) classesDesejadas[0] = new Aluno(0, null, 0, null, null, 0, null);
		else classesDesejadas[0] = new Pessoa(0, null, 0, null);
		
		if(classesRetorno[1]) classesDesejadas[1] = new Professor(0, null, 0, null, 0, 0, null);
		else classesDesejadas[1] = new Pessoa(0, null, 0, null);
		
		if(classesRetorno[2]) classesDesejadas[2] = new Zelador(0, null, 0, null, 0, 0, null);
		else classesDesejadas[2] = new Pessoa(0, null, 0, null);
		
		if(classesRetorno[3]) classesDesejadas[3] = new Diretor(0, null, 0, null, 0, 0);
		else classesDesejadas[3] = new Pessoa(0, null, 0, null);
		
		for(int i = 0; i < nPessoas; i++) {
			for(int j = 0; j < 4; j++) {
				if(pessoas.get(i).getClass().equals(classesDesejadas[j].getClass())) {
					pessoasRetorno.add(pessoas.get(i));
				}
			}
		}
		
		String[] impressoes = new String[pessoasRetorno.size()];
		
		for(int i = 0; i < pessoasRetorno.size(); i++) {
			impressoes[i] = pessoasRetorno.get(i).getClass().toString() + "\nNome: " + pessoasRetorno.get(i).getNome() + "\nN� de Registro: " + pessoasRetorno.get(i).getRegister();
		}
		return impressoes;
	}
	
	/**
	 * Busca uma pessoa na escola
	 * @param registro - inteiro correspondente ao registro do usu�rio que est� sendo buscado
	 * @return retorna null caso n�o seja encontrado registro equivalente e retorna a Pessoa caso o
	 * usu�rio seja encontrado
	 * */
	public Pessoa buscaPessoa(long registro) {
		int index = -1;
		
		for(int i = 0; i < nPessoas; i++) {
			if(pessoas.get(i).getRegister() == registro) {
				index = i;
				break;
			}
		}
		
		if(index == -1) {return null;}
		
		return pessoas.get(index);
	}
	
	/**
	 * Busca uma pessoa na escola
	 * @param nome - nome correspondente ao(s) nome(s) do(s) usu�rio(s) que est�(�o) sendo buscado(s)
	 * @return retorna um outro arraylist com as pessoas na escola com esse nome (podem existir mais de uma).
	 * Esse arraylist vai ter .size(0) se ningu�m for encontrado
	 * */
	public ArrayList<Pessoa> buscaPessoa(String nome) {
		ArrayList<Pessoa> pessoasPesquisadas = new ArrayList<Pessoa>();
		
		for(int i = 0; i < nPessoas; i++) {
			if(pessoas.get(i).getNome().equalsIgnoreCase(nome)) {
				pessoasPesquisadas.add(pessoas.get(i));
			}
		}
		
		return pessoasPesquisadas;
	}
	
	/**
	 * Dependendo do usu�rio logado, esse m�todo retorna todas as informa��es de uma pessoa na escola
	 * ou apenas as informa��es b�sicas
	 * @param pessoaLogada - Diretor consegue todas as informa��es de todas as pessoas, as demais classes
	 * s� conseguem todas as informa��es de classes iguais a ela (ex: Zelador consegue Zelador)
	 * @return Uma string com as informa��es
	 * */
	public String infoPessoa(Pessoa pessoaLogada, Pessoa pessoaRequerida) {
		String r;
		if(pessoaLogada instanceof Diretor || pessoaLogada.getClass().equals(pessoaRequerida.getClass())) {
			r = pessoaRequerida.toString()+'\n';
		} else {
			r = pessoaRequerida.getClass().toString() + "\nNome: " + pessoaRequerida.getNome() + "\nN� de Registro: " + pessoaRequerida.getRegister();
		}
		return r;
	}
	
	/**
	 * M�todo usado para logar inicialmente um usu�rio no programa
	 * @param txtRegistro - � uma string correspondente ao registro do usu�rio que
	 * est� tentando logar
	 * @param txtSenha - � a senha do usu�rio que est� tentando logar
	 * @return - A pessoa logada ou null, caso tenha algum dado errado no login
	 * */
	public Pessoa checkLogin(String txtRegistro, String txtSenha) {
		Pessoa pessoaLogada = null;
		
		
		if(txtRegistro.matches("-?\\d+")) {
			pessoaLogada = this.buscaPessoa(Integer.parseInt(txtRegistro));
			
			if(pessoaLogada.getSenha().equals("�")) {
				if (pessoaLogada == null) {
					System.out.println("Registro e/ou Senha inseridos n�o �/s�o v�lido(s)");
				} else {
					System.out.println(pessoaLogada.toString());
				}
				return pessoaLogada;
			}
			if(!txtSenha.equals(pessoaLogada.getSenha())) {pessoaLogada = null;}
		} else {
			pessoaLogada = null;
		}
		
		if (pessoaLogada == null) {
			System.out.println("Registro e/ou Senha inseridos n�o �/s�o v�lido(s)");
		} else {
			System.out.println(pessoaLogada.toString());
		}
		return pessoaLogada;
	}
	
	/**
	 * Funcao ordena as pessoas da escola por ordem alfabetica
	 */
	public void sortAlpha() {
		
		// Sorting
		Collections.sort(pessoas, new Comparator<Pessoa>() {
		        @Override
		        public int compare(Pessoa p1, Pessoa p2)
		        {

		            return  p1.getNome().compareTo(p2.getNome());
		        }
		    });
	}
	
	/**
	 * Funcao ordena as pessoas da escola por ordem alfabetica
	 */
	public void sortRegistro() {
		
		// Sorting
		Collections.sort(pessoas, new Comparator<Pessoa>() {
		        @Override
		        public int compare(Pessoa p1, Pessoa p2)
		        {	
		        	int aux = 0;
		        	if (p1.getRegister() == p2.getRegister()) aux = 0;
		        	if (p1.getRegister() > p2.getRegister()) aux = 1;
		        	if (p1.getRegister() < p2.getRegister()) aux = -1;
		        	
		            return aux;
		        }
		    });
	}
}
