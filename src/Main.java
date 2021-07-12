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
		String[] ocorrencias = { "Chato", "Feio", "Esquisito" };
		String[] horarios = { "1h", "3h", "5h" };
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Escola escolaX = new Escola();
		Diretor diretorY = new Diretor(9999, "Y", (float) 90/100, 5000.00, horarios, ocorrencias);
		Aluno alunoZ = new Aluno(123, "Z", (float) 54/100, "019", ocorrencias, notas );
		GerenciadorDados ga = new GerenciadorDados();
		
		//leitura do banco de dados FAZER ISSO NA INICIALIZA��O DO PROGRAMA
		ga.leAdicionaPessoasArquivos(escolaX, "src/baseDados.csv");
		
		//uso de adicionaPessoa, primeiramente sem permiss�o, depois com
		escolaX.adicionaPessoa(alunoZ, alunoZ);
		escolaX.adicionaPessoa(diretorY, alunoZ);
		escolaX.adicionaPessoa(diretorY, diretorY);
		
		//uso de printPessoas
		String[] printPessoas = escolaX.imprimePessoas(alunoZ);
		for(int i = 0; i < escolaX.nPessoas; i++) {
			System.out.println(printPessoas[i]);
		}
		
		
		System.out.println("\n");
		
		//uso de infoPessoa, tanto com permiss�o total, quanto sem permiss�o total
		System.out.println(escolaX.infoPessoa(alunoZ, diretorY) + '\n');
		System.out.println(escolaX.infoPessoa(diretorY, diretorY));
		/*
		//uso de buscaPessoa pelo registro (com um reg inv�lido) e com o nome (com um nome v�lido)
		ArrayList<Pessoa> pessoasPesquisadas = new ArrayList<Pessoa>();
		
		pessoasPesquisadas.add(escolaX.buscaPessoa(0));
		if(pessoasPesquisadas.get(0) == null) {
			System.out.println("N�o existe nenhum usu�rio com esse registro");
		} else {
			//sem um for porque o registro � �nico por usu�rio
			System.out.println(escolaX.infoPessoa(pessoasPesquisadas.get(0), diretorY));
		}
		pessoasPesquisadas = escolaX.buscaPessoa("Y");
		if(pessoasPesquisadas.get(0) == null) {
			System.out.println("N�o existe nenhum usu�rio com esse registro");
		} else {
			//sem um for porque o registro � �nico por usu�rio
			System.out.println(escolaX.infoPessoa(pessoasPesquisadas.get(0), diretorY));
		}
		*/
		
		//escrita do banco de dados FAZER ISSO NO FINAL DO PROGRAMA
		ga.escrevePessoasArquivo(escolaX, "src/baseDados.csv");

	}

}