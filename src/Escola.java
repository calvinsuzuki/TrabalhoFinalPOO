import java.util.ArrayList;
import exceptions.RegistroUsadoException;
import exceptions.UsuarioLogadoInvalidoException;

public class Escola {
	
	private int nPessoas = 0;
	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	/*
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
	 * */
	public String[] imprimePessoas() {
		String[] impressoes = new String[nPessoas];
		for(int i = 0; i < nPessoas; i++) {
			impressoes[i] = "Nome: " + pessoas.get(i).nome + "\nNº de Registro: " + 
					pessoas.get(i).register + "\nFrequência: " + pessoas.get(i).freq;

		}
		return impressoes;
	}
	
	public Pessoa buscaPessoa(int registro) {
		int index = -1;
		
		for(int i = 0; i < nPessoas; i++) {
			if(pessoas.get(i).register == registro) {
				index = i;
				break;
			}
		}
		
		return pessoas.get(index);
	}
	
	public ArrayList<Pessoa> buscaPessoa(String nome) {
		ArrayList<Pessoa> pessoasPesquisadas = new ArrayList<Pessoa>();
		
		for(int i = 0; i < nPessoas; i++) {
			if(pessoas.get(i).nome.equals(nome)) {
				pessoasPesquisadas.add(pessoas.get(i));
				break;
			}
		}
		
		return pessoasPesquisadas;
	}
	
	public String infoPessoa(Pessoa pessoaLogada, Pessoa pessoaRequerida) {
		String r;
		if(pessoaLogada instanceof Diretor || pessoaLogada.getClass().equals(pessoaRequerida.getClass())) {
			r = pessoaRequerida.toString()+'\n';
		} else {
			r = "Nome: " + pessoaRequerida.nome + "\nNº de Registro: " + 
					pessoaRequerida.register + "\nFrequência: " + pessoaRequerida.freq;
		}
		return r;
	}
	
	public int getNPessoas() {
		return nPessoas;
	}
	
	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}
}
