import java.awt.EventQueue;

/** 
 * Grupo 1 - SSC0103-2021
 * @author Alcino Salviano Cavalcanti, 11892963
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