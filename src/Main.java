import java.util.ArrayList;

/**
 * Grupo 1 - SSC0103-2021
 * @author Alcino Salviano Cavalcanti			11892963
 * @author Calvin Suzuki de Camargo				11232420
 * @author Gabriel Takeshi Miyake Batistella	11232198
 * @author Pedro Henrique Raymundi				11795634
 */

public class Main {

	public static void main(String[] args) {
		int ocorrencias = 1;
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Escola escolaX = new Escola();
		Diretor diretorY = new Diretor(9999, "Y", (float) 90/100, "senha", 5000.00, ocorrencias);
		Aluno alunoZ = new Aluno(123, "Z", (float) 54/100, "senha", "019", ocorrencias, notas );
		GerenciadorDados ga = new GerenciadorDados();
		
		//leitura do banco de dados FAZER ISSO NA INICIALIZAÇÃO DO PROGRAMA
		ga.leAdicionaPessoasArquivos(escolaX, "src/baseDados.csv");
		
		//uso de adicionaPessoa, primeiramente sem permissão, depois com
		escolaX.adicionaPessoa(alunoZ, alunoZ);
		escolaX.adicionaPessoa(diretorY, alunoZ);
		escolaX.adicionaPessoa(diretorY, diretorY);
		
		//uso de printPessoas
		String[] printPessoas = escolaX.imprimePessoas(new boolean[] {false, true, true, true});
		for(int i = 0; i < printPessoas.length ; i++) {
			if(printPessoas[i] == null) {continue;}
			System.out.println(printPessoas[i]);
		}
		
		
		System.out.println("\n");
		
		//uso de infoPessoa, tanto com permissão total, quanto sem permissão total
		System.out.println(escolaX.infoPessoa(alunoZ, diretorY) + '\n');
		System.out.println(escolaX.infoPessoa(diretorY, diretorY));
		
		
		
		//uso de buscaPessoa pelo registro (com um reg inválido) e com o nome (com um nome válido)
		ArrayList<Pessoa> pessoasPesquisadas = new ArrayList<Pessoa>();
		
		pessoasPesquisadas.add(escolaX.buscaPessoa(0));
		if(pessoasPesquisadas.get(0) == null) {
			System.out.println("Não existe nenhum usuário com esse registro");
		} else {
			//sem um for porque o registro é único por usuário
			System.out.println(escolaX.infoPessoa(pessoasPesquisadas.get(0), diretorY));
		}
		pessoasPesquisadas = escolaX.buscaPessoa("Y");
		if(pessoasPesquisadas.size() == 0) {
			System.out.println("Não existe nenhum usuário com esse registro");
		} else {
			//sem um for porque o registro é único por usuário
			System.out.println(escolaX.infoPessoa(pessoasPesquisadas.get(0), diretorY));
		}
		
		//uso de chacklogin, sendo só a terceira válida e as 2 últimas um login de alguém sem senha
		escolaX.checkLogin("1a3", "senha");
		escolaX.checkLogin("123", "SENHA");
		escolaX.checkLogin("123", "senha");
		escolaX.checkLogin("321", "senha");
		escolaX.checkLogin("321", "rgrejgruy");
		
		//remoção de uma pessoa
		escolaX.removePessoa(diretorY, 123);
		
		//escrita do banco de dados FAZER ISSO NO FINAL DO PROGRAMA
		ga.escrevePessoasArquivo(escolaX, "src/baseDados.csv");

	}

}