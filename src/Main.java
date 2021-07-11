/**
 * Grupo 1 - SSC0103-2021
 * @author Alcino Salviano Cavalcanti			11892963
 * @author Calvin Suzuki de Camargo				11232420
 * @author Gabriel Takeshi Miyake Batistella	11232198
 * @author Pedro Henrique Raymundi				11795634
 */

public class Main {

	public static void main(String[] args) {
		// Testing
		String[] ocorrencias = { "Chato", "Feio", "Esquisito" };
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Pessoa calvin = new Aluno(11232420, "Calvin Suzuki de Camargo", 54/100, "019", ocorrencias, notas);
		
		System.out.println(calvin.toString());	

	}

}