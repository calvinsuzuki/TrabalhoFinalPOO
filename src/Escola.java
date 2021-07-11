import java.util.ArrayList;

import exceptions.RegistroUsadoException;

public class Escola {
	public int nPessoas = 0;
	ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public void adicionaPessoa(Pessoa novaPessoa) {
		for (int i = 0; i < nPessoas; i++) {
			try {
				if (novaPessoa.register == pessoas.get(i).register) {
					throw new RegistroUsadoException();
				}
			} catch (RegistroUsadoException e) {
				System.out.println(e.getMessage());
				return;
			}
	        
	        if (novaPessoa.register < pessoas.get(i).register) {
	        	pessoas.add(i, novaPessoa);
	        	nPessoas++;
	        	return;
	        }
	    }
		pessoas.add(novaPessoa);
		nPessoas++;
	}
	
	/**
	 * */
	public void imprimePessoas(Pessoa pessoaLogada) {
		for(int i = 0; i < nPessoas; i++) {
			if(pessoaLogada instanceof Pessoa/*MUDAR Pessoa PARA Diretor*/) {
				System.out.println(pessoas.get(i).toString()+'\n'); //<= mudar aqui para printar na GUI
			} else if(pessoas.get(i).getClass().equals(pessoaLogada.getClass())) {
				System.out.println(pessoas.get(i).toString()+'\n'); //<= mudar aqui para printar na GUI
			}

		}
	}
}
