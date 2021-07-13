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
	 * @return Arraylist de pessoas na escola
	 * */
	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
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
				if (novaPessoa.register == pessoas.get(i).register) {
					throw new RegistroUsadoException();
				}
		        
		        if (novaPessoa.register < pessoas.get(i).register) {
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
	public void removePessoa(Pessoa pessoaLogada, int registroPessoaRemovida) {
		int index = -1;
		try {
			for(int i = 0; i < nPessoas; i++) {
				if(pessoas.get(i).register == registroPessoaRemovida) {
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
	 * @return Retorna um array de strings com os dados básicos de todos os usuários
	 * */
	public String[] imprimePessoas() {
		String[] impressoes = new String[nPessoas];
		for(int i = 0; i < nPessoas; i++) {
			impressoes[i] = pessoas.get(i).getClass().toString() + "\nNome: " + pessoas.get(i).nome + "\nNº de Registro: " + 
					pessoas.get(i).register;

		}
		return impressoes;
	}
	
	/**
	 * Busca uma pessoa na escola
	 * @param registro - inteiro correspondente ao registro do usuário que está sendo buscado
	 * @return retorna null caso não seja encontrado registro equivalente e retorna a Pessoa caso o
	 * usuário seja encontrado
	 * */
	public Pessoa buscaPessoa(int registro) {
		int index = -1;
		
		for(int i = 0; i < nPessoas; i++) {
			if(pessoas.get(i).register == registro) {
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
			if(pessoas.get(i).nome.equalsIgnoreCase(nome)) {
				pessoasPesquisadas.add(pessoas.get(i));
				break;
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
			r = pessoaRequerida.getClass().toString() + "\nNome: " + pessoaRequerida.nome + "\nNº de Registro: " + 
					pessoaRequerida.register;
		}
		return r;
	}
}
