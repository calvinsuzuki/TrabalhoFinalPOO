import java.util.ArrayList;

import exceptions.RegistroUsadoException;
import exceptions.UsuarioLogadoInvalidoException;

public class Escola {
	private int nPessoas = 0;
	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	/**
	 * @return número de pessoas na escola
	 * */
	public int getNPessoas() {
		return nPessoas;
	}

	/**
	 * @param classesRetorno- array de booleans com 4 elementos, equivalendo respectivamente à Aluno, Professor,
	 * Zelador, Diretor. Passe 'true' para o boolean equivalente para receber Pessoas condizentes à classe
	 * @return Arraylist de pessoas na escola
	 * */
	public ArrayList<Pessoa> getPessoas(boolean[] classesRetorno) {
		ArrayList<Pessoa> pessoasRetorno = new ArrayList<Pessoa>();
		Pessoa[] classesDesejadas = new Pessoa[4];
		
		if(classesRetorno[0]) {classesDesejadas[0] = new Aluno(0, null, 0, null, 0, null);} else {classesDesejadas[0] = new Pessoa(0, null, 0);}
		if(classesRetorno[1]) {classesDesejadas[1] = new Professor(0, null, 0, 0, 0, null);} else {classesDesejadas[1] = new Pessoa(0, null, 0);}
		if(classesRetorno[2]) {classesDesejadas[2] = new Zelador(0, null, 0, 0, 0, null);} else {classesDesejadas[2] = new Pessoa(0, null, 0);}
		if(classesRetorno[3]) {classesDesejadas[3] = new Diretor(0, null, 0, 0, 0);} else {classesDesejadas[3] = new Pessoa(0, null, 0);}
		
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
	 * Função para adicionar, ordenadamente por registro, uma pessoa na escola.
	 * @param pessoaLogada - Para poder adicionar alguém, pessoaLogada deve ser Diretor
	 * @param novaPessoa - pode ser de qualquer subclasse, vai adicionar para o sistema da escola
	 * */
	public void adicionaPessoa(Pessoa pessoaLogada, Pessoa novaPessoa) {
		try {
			if(!(pessoaLogada.getClass().equals(Diretor.class))) {
				throw new UsuarioLogadoInvalidoException();
			}
		
			for (int i = 0; i < nPessoas; i++) {
				if (novaPessoa.getRegister() == pessoas.get(i).getRegister()) {
					throw new RegistroUsadoException();
				}
		        
		        if (novaPessoa.getRegister() < pessoas.get(i).getRegister()) {
		        	pessoas.add(i, novaPessoa);
		        	nPessoas++;
		        	return;
		        }
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
	 * @param pessoaLogada - Para poder remover alguém, pessoaLogada deve ser diretor
	 * @param registroPessoaRemovida - é o registro da pessoa que o método remove, pode ser
	 * obtido pelo usuário, ou feita uma busca pelo nome usando outra função e depois passada o registro
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
	 * Função que repassa os dados usados para a impressão de todos na escola
	 * @param classesRetorno- array de booleans com 4 elementos, equivalendo respectivamente à Aluno, Professor,
	 * Zelador, Diretor. Passe 'true' para o boolean equivalente para receber string condizentes à classe
	 * @return Retorna um array de strings com os dados básicos de todos os usuários
	 * */
	public String[] imprimePessoas(boolean[] classesRetorno) {
		ArrayList<Pessoa> pessoasRetorno = new ArrayList<Pessoa>();
		Pessoa[] classesDesejadas = new Pessoa[4];
		
		if(classesRetorno[0]) {classesDesejadas[0] = new Aluno(0, null, 0, null, 0, null);} else {classesDesejadas[0] = new Pessoa(0, null, 0);}
		if(classesRetorno[1]) {classesDesejadas[1] = new Professor(0, null, 0, 0, 0, null);} else {classesDesejadas[1] = new Pessoa(0, null, 0);}
		if(classesRetorno[2]) {classesDesejadas[2] = new Zelador(0, null, 0, 0, 0, null);} else {classesDesejadas[2] = new Pessoa(0, null, 0);}
		if(classesRetorno[3]) {classesDesejadas[3] = new Diretor(0, null, 0, 0, 0);} else {classesDesejadas[3] = new Pessoa(0, null, 0);}
		
		for(int i = 0; i < nPessoas; i++) {
			for(int j = 0; j < 4; j++) {
				if(pessoas.get(i).getClass().equals(classesDesejadas[j].getClass())) {
					pessoasRetorno.add(pessoas.get(i));
				}
			}
		}
		
		String[] impressoes = new String[pessoasRetorno.size()];
		
		for(int i = 0; i < pessoasRetorno.size(); i++) {
			impressoes[i] = pessoasRetorno.get(i).getClass().toString() + "\nNome: " + pessoasRetorno.get(i).getNome() + "\nNº de Registro: " + pessoasRetorno.get(i).getRegister();
		}
		return impressoes;
	}
	
	/**
	 * Busca uma pessoa na escola
	 * @param registro - inteiro correspondente ao registro do usuário que está sendo buscado
	 * @return retorna null caso não seja encontrado registro equivalente e retorna a Pessoa caso o
	 * usuário seja encontrado
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
	 * @param nome - nome correspondente ao(s) nome(s) do(s) usuário(s) que está(ão) sendo buscado(s)
	 * @return retorna um outro arraylist com as pessoas na escola com esse nome (podem existir mais de uma).
	 * Esse arraylist vai ter .size(0) se ninguém for encontrado
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
	 * Dependendo do usuário logado, esse método retorna todas as informações de uma pessoa na escola
	 * ou apenas as informações básicas
	 * @param pessoaLogada - Diretor consegue todas as informações de todas as pessoas, as demais classes
	 * só conseguem todas as informações de classes iguais a ela (ex: Zelador consegue Zelador)
	 * @return Uma string com as informações
	 * */
	public String infoPessoa(Pessoa pessoaLogada, Pessoa pessoaRequerida) {
		String r;
		if(pessoaLogada instanceof Diretor || pessoaLogada.getClass().equals(pessoaRequerida.getClass())) {
			r = pessoaRequerida.toString()+'\n';
		} else {
			r = pessoaRequerida.getClass().toString() + "\nNome: " + pessoaRequerida.getNome() + "\nNº de Registro: " + pessoaRequerida.getRegister();
		}
		return r;
	}
}
