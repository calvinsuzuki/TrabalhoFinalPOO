import java.awt.EventQueue;

/** 
 * Grupo 1 - SSC0103-2021
 * @author Alcino Salviano Cavalcanti, 11892963
 * @author Calvin Suzuki de Camargo, 11232420
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 * @author Pedro Henrique Raymundi, 11795634
 */
public class Main {

	public static void main(String[] args) {
		Escola escola = new Escola();
		GerenciadorDados ga = new GerenciadorDados();
		ga.leAdicionaPessoasArquivos(escola, "src/baseDadosRandom.csv");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI(escola);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}