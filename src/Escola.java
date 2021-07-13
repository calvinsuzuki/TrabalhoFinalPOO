import java.util.ArrayList;

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
	 * @return Arraylist de pessoas na escola
	 * */
	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
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
	 * @param pessoaLogada - Para poder remover algu�m, pessoaLogada deve ser diretor
	 * @param registroPessoaRemovida - � o registro da pessoa que o m�todo remove, pode ser
	 * obtido pelo usu�rio, ou feita uma busca pelo nome usando outra fun��o e depois passada o registro
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
	 * Fun��o que repassa os dados usados para a impress�o de todos na escola
	 * @return Retorna um array de strings com os dados b�sicos de todos os usu�rios
	 * */
	public String[] imprimePessoas() {
		String[] impressoes = new String[nPessoas];
		for(int i = 0; i < nPessoas; i++) {
			impressoes[i] = pessoas.get(i).getClass().toString() + "\nNome: " + pessoas.get(i).nome + "\nN� de Registro: " + 
					pessoas.get(i).register;

		}
		return impressoes;
	}
	
	/**
	 * Busca uma pessoa na escola
	 * @param registro - inteiro correspondente ao registro do usu�rio que est� sendo buscado
	 * @return retorna null caso n�o seja encontrado registro equivalente e retorna a Pessoa caso o
	 * usu�rio seja encontrado
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
	 * @param nome - nome correspondente ao(s) nome(s) do(s) usu�rio(s) que est�(�o) sendo buscado(s)
	 * @return retorna um outro arraylist com as pessoas na escola com esse nome (podem existir mais de uma).
	 * Esse arraylist vai ter .size(0) se ningu�m for encontrado
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
			r = pessoaRequerida.getClass().toString() + "\nNome: " + pessoaRequerida.nome + "\nN� de Registro: " + 
					pessoaRequerida.register;
		}
		return r;
	}
}
