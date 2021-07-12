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
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Escola escolaX = new Escola();
		GerenciadorDados ga = new GerenciadorDados();
		
		ga.leAdicionaPessoasArquivos(escolaX, "src/baseDados.csv");
		escolaX.adicionaPessoa(new Aluno(123, "Z", 54/100, "019", ocorrencias, notas ));
		escolaX.imprimePessoas(new Aluno(10000, "ALUNO USUARIO", 54/100, "019", ocorrencias, notas ));
		ga.escrevePessoasArquivo(escolaX, "src/baseDados.csv");

	}

}