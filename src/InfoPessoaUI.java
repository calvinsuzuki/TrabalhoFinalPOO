import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoPessoaUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblNome;
	private JLabel lblRegistro;
	private JLabel lblTurma;
	private JLabel lblNotas;
	private JLabel lblCiencias;
	private JLabel lblMatematica;
	private JLabel lblPortugues;
	private JLabel lblProva1;
	private JLabel lblProva2;
	private JLabel lblMedia;
	private JLabel lblMediaCiencias;
	private JLabel lblMediaMatematica;
	private JLabel lblMediaPortugues;
	private JLabel lblMediaGeral;
	private JLabel lblMediaGeralNum;
	private JLabel lblFrequencia;
	private JLabel lblOcorrencias;
	private JSpinner spnCienciasProva1;
	private JSpinner spnCienciasProva2;
	private JSpinner spnMatematicaProva1;
	private JSpinner spnMatematicaProva2;
	private JSpinner spnPortuguesProva1;
	private JSpinner spnPortuguesProva2;
	private JSpinner spnOcorrencias;
	private JSlider sldFrequencia;
	private JButton btnSalvar;
	private JButton btnRemover;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int ocorrencias = 1;
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Escola escolaX = new Escola();
		Diretor diretorY = new Diretor(9999, "Y", (float) 90/100, 5000.00, ocorrencias);
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
					InfoPessoaUI frame = new InfoPessoaUI(escolaX, alunoZ, diretorY);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public InfoPessoaUI(Escola sistema, Aluno aluno, Pessoa contaLogada) {
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Infos do Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 500);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new MatteBorder(130, 4, 4, 4, (Color) new Color(218, 165, 32)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblNome = new JLabel("Nome : " + aluno.getNome());
		lblNome.setForeground(new Color(0, 0, 0));
		lblNome.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 32));
		lblNome.setBounds(32, 20, 705, 48);
		
		lblRegistro = new JLabel("Registro : " + aluno.getRegister());
		lblRegistro.setForeground(new Color(0, 0, 0));
		lblRegistro.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 26));
		lblRegistro.setBounds(68, 72, 365, 46);
		
		lblTurma = new JLabel("Turma : " + aluno.getTurma());
		lblTurma.setForeground(new Color(0, 0, 0));
		lblTurma.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 26));
		lblTurma.setBounds(465, 72, 275, 46);
		
		lblNotas = new JLabel("Notas :");
		lblNotas.setForeground(new Color(0, 0, 0));
		lblNotas.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 22));
		lblNotas.setBounds(32, 160, 80, 40);
		
		lblCiencias = new JLabel("Ciências");
		lblCiencias.setForeground(new Color(0, 0, 0));
		lblCiencias.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblCiencias.setBounds(144, 186, 95, 24);
		
		lblMatematica = new JLabel("Matemática");
		lblMatematica.setForeground(new Color(0, 0, 0));
		lblMatematica.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblMatematica.setBounds(122, 237, 117, 24);
		
		lblPortugues = new JLabel("Português");
		lblPortugues.setForeground(new Color(0, 0, 0));
		lblPortugues.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblPortugues.setBounds(134, 288, 105, 24);
		
		lblProva1 = new JLabel("Prova 1");
		lblProva1.setForeground(new Color(0, 0, 0));
		lblProva1.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblProva1.setBounds(259, 148, 76, 24);
		
		lblProva2 = new JLabel("Prova 2");
		lblProva2.setForeground(new Color(0, 0, 0));
		lblProva2.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblProva2.setBounds(377, 148, 76, 24);
		
		spnCienciasProva1 = new JSpinner();
		spnCienciasProva1.setBounds(249, 176, 90, 45);
		
		spnCienciasProva2 = new JSpinner();
		spnCienciasProva2.setBounds(367, 176, 90, 45);
		
		spnMatematicaProva1 = new JSpinner();
		spnMatematicaProva1.setBounds(249, 226, 90, 45);
		
		spnMatematicaProva2 = new JSpinner();
		spnMatematicaProva2.setBounds(367, 226, 90, 45);
		
		spnPortuguesProva1 = new JSpinner();
		spnPortuguesProva1.setBounds(249, 276, 90, 45);
		
		spnPortuguesProva2 = new JSpinner();
		spnPortuguesProva2.setBounds(367, 276, 90, 45);
		
		lblMedia = new JLabel("Média");
		lblMedia.setForeground(new Color(0, 0, 0));
		lblMedia.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblMedia.setBounds(489, 148, 68, 24);
		
		double mediaCiencias = (aluno.getNotas()[0]+aluno.getNotas()[1])/2;
		lblMediaCiencias = new JLabel(String.format("%.1f", mediaCiencias));
		lblMediaCiencias.setForeground(new Color(0, 0, 0));
		lblMediaCiencias.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 24));
		lblMediaCiencias.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaCiencias.setBounds(489, 185, 56, 24);
		
		double mediaMatematica = (aluno.getNotas()[2]+aluno.getNotas()[3])/2;
		lblMediaMatematica = new JLabel(String.format("%.1f", mediaMatematica));
		lblMediaMatematica.setForeground(new Color(0, 0, 0));
		lblMediaMatematica.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 24));
		lblMediaMatematica.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaMatematica.setBounds(489, 235, 56, 24);
		
		double mediaPortugues = (aluno.getNotas()[4]+aluno.getNotas()[5])/2;
		lblMediaPortugues = new JLabel(String.format("%.1f", mediaPortugues));
		lblMediaPortugues.setForeground(new Color(0, 0, 0));
		lblMediaPortugues.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 24));
		lblMediaPortugues.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaPortugues.setBounds(489, 285, 56, 24);
		
		lblMediaGeral = new JLabel("Média Geral :");
		lblMediaGeral.setForeground(new Color(0, 0, 0));
		lblMediaGeral.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 24));
		lblMediaGeral.setBounds(572, 210, 168, 42);
		
		double mediaGeral = (mediaCiencias+mediaMatematica+mediaPortugues)/3;
		lblMediaGeralNum = new JLabel(String.format("%.2f", mediaGeral));
		lblMediaGeralNum.setForeground(new Color(0, 0, 0));
		lblMediaGeralNum.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 26));
		lblMediaGeralNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaGeralNum.setBounds(572, 242, 140, 42);
		
		lblFrequencia = new JLabel("Frequência :");
		lblFrequencia.setForeground(new Color(0, 0, 0));
		lblFrequencia.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 22));
		lblFrequencia.setBounds(32, 348, 124, 33);
		
		sldFrequencia = new JSlider();
		sldFrequencia.setBounds(188, 360, 212, 13);
		
		lblOcorrencias = new JLabel("Ocorrências :");
		lblOcorrencias.setForeground(new Color(0, 0, 0));
		lblOcorrencias.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblOcorrencias.setBounds(466, 349, 137, 24);
		
		spnOcorrencias = new JSpinner();
		spnOcorrencias.setBounds(617, 349, 43, 24);
		
		btnSalvar = new JButton("Salvar Modificações");
		btnSalvar.setBounds(169, 400, 154, 44);
		
		btnRemover = new JButton("Remover Aluno");
		btnRemover.setBounds(447, 400, 154, 44);
		
		contentPane.add(lblNome);
		contentPane.add(lblRegistro);
		contentPane.add(lblTurma);
		contentPane.add(lblNotas);
		contentPane.add(lblCiencias);
		contentPane.add(lblMatematica);
		contentPane.add(lblPortugues);
		contentPane.add(lblProva1);
		contentPane.add(lblProva2);
		contentPane.add(lblMedia);
		contentPane.add(lblMediaCiencias);
		contentPane.add(lblMediaMatematica);
		contentPane.add(lblMediaPortugues);
		contentPane.add(lblMediaGeral);
		contentPane.add(lblMediaGeralNum);
		contentPane.add(lblFrequencia);
		contentPane.add(lblOcorrencias);
		contentPane.add(spnCienciasProva1);
		contentPane.add(spnCienciasProva2);
		contentPane.add(spnMatematicaProva1);
		contentPane.add(spnMatematicaProva2);
		contentPane.add(spnPortuguesProva1);
		contentPane.add(spnPortuguesProva2);
		contentPane.add(spnOcorrencias);
		contentPane.add(sldFrequencia);
		contentPane.add(btnSalvar);
		contentPane.add(btnRemover);
	}
	
	public InfoPessoaUI(Escola sistema, Professor professor, Pessoa contaLogada) {
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Infos do Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 500);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
	}
	
	public InfoPessoaUI(Escola sistema, Zelador zelador, Pessoa contaLogada) {
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Infos do Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 500);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
	}
	
	public InfoPessoaUI(Escola sistema, Diretor diretor, Pessoa contaLogada) {
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Infos do Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 500);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
	}
}
