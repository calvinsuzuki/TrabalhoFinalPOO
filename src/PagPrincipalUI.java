import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagPrincipalUI extends JFrame {
	
	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel cardsPane;
	private JPanel[] listPanes;
	private JScrollPane[] scrollPanes;
	private JButton[] btnsPessoa;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String[] ocorrencias = { "Chato", "Feio", "Esquisito" };
		String[] horarios = { "1h", "3h", "5h" };
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Escola escolaX = new Escola();
		Diretor diretorY = new Diretor(9999, "Y", (float) 90/100, 5000.00, horarios, ocorrencias);
		Aluno alunoZ = new Aluno(123, "Z", (float) 54/100, "019", ocorrencias, notas );
		GerenciadorDados ga = new GerenciadorDados();
		
		//leitura do banco de dados FAZER ISSO NA INICIALIZAÇÃO DO PROGRAMA
		ga.leAdicionaPessoasArquivos(escolaX, "src/baseDados.csv");
		
		//uso de adicionaPessoa, primeiramente sem permissão, depois com
		escolaX.adicionaPessoa(alunoZ, alunoZ);
		escolaX.adicionaPessoa(diretorY, alunoZ);
		escolaX.adicionaPessoa(diretorY, diretorY);
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagPrincipalUI frame = new PagPrincipalUI(escolaX, diretorY);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PagPrincipalUI(Escola sistema, Pessoa contaLogada) {
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(60, 30, 1340, 780);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		titlePane = new JPanel();
		titlePane.setForeground(new Color(0, 0, 0));
		titlePane.setBackground(new Color(218, 165, 32));
		titlePane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 1327, 120);
		titlePane.setLayout(null);
		
		cardsPane = new JPanel();
		cardsPane.setForeground(new Color(0, 0, 0));
		cardsPane.setBackground(new Color(240, 230, 140));
		cardsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		cardsPane.setBounds(-1, 118, 1328, 628);
		cardsPane.setLayout(new CardLayout());
		refreshListPessoas(sistema, contaLogada);
		
		contentPane.add(titlePane);
		contentPane.add(cardsPane);
	}
	
	private void refreshListPessoas(Escola sistema, Pessoa contaLogada) {
		/*String[] dadosPessoas = sistema.imprimePessoas();*/
		String[] dadosPessoas = new String[] {"Nome salsasdasd", "Nome asdasdasd", "nome asdasdascaceec"};
		/*int numPessoas = sistema.getNPessoas();*/
		int numPessoas = 3;
		int numPaginas = numPessoas/27 + 1;
		int numPessoasUltimaPagina = numPessoas - (numPaginas-1)*27;
		
		listPanes = new JPanel[numPaginas];
		btnsPessoa = new JButton[numPessoas];
		scrollPanes = new JScrollPane[numPaginas];
		
		for(int i=0 ; i<numPaginas ; i++) {
			listPanes[i] = new JPanel();
			listPanes[i].setForeground(new Color(0, 0, 0));
			listPanes[i].setBackground(new Color(240, 230, 140));
			listPanes[i].setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
			listPanes[i].setPreferredSize(new Dimension(1310, 1500));
			listPanes[i].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			listPanes[i].setAutoscrolls(true);
			
			int numPessoasNessaPagina;
			if(i == numPaginas-1) {
				numPessoasNessaPagina = numPessoasUltimaPagina;
			} else {
				numPessoasNessaPagina = 27;
			}
			
			for(int j=0 ; j<numPessoasNessaPagina ; j++) {
				btnsPessoa[27*i + j] = new JButton(dadosPessoas[27*i + j]);
				/*btnsPessoa[27*i + j].setBounds(261, 368, 216, 48);*/
				btnsPessoa[27*i + j].setFont(new Font("Papyrus", Font.BOLD, 18));
				btnsPessoa[27*i + j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							
							throw new Exception();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Registro ou Senha INVÁLIDO!", "ERRO no Login", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				listPanes[i].add(btnsPessoa[27*i + j]);
			}
			
			scrollPanes[i] = new JScrollPane(listPanes[i]);
			scrollPanes[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPanes[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanes[i].setBounds(-1, 118, 1328, 628);
			
			cardsPane.add(scrollPanes[i]);
		}
	}
}
