import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
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
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class InfoPessoaUI extends JFrame {

	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel infoPane;
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
	private JLabel lblFrequenciaNum;
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

	private boolean ehAPessoa;
	private double mediaCiencias;
	private double mediaMatematica;
	private double mediaPortugues;
	private double mediaGeral;
	
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
		
		//leitura do banco de dados FAZER ISSO NA INICIALIZA��O DO PROGRAMA
		ga.leAdicionaPessoasArquivos(escolaX, "src/baseDados.csv");
				
		//uso de adicionaPessoa, primeiramente sem permiss�o, depois com
		escolaX.adicionaPessoa(alunoZ, alunoZ);
		escolaX.adicionaPessoa(diretorY, alunoZ);
		escolaX.adicionaPessoa(diretorY, diretorY);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoPessoaUI frame = new InfoPessoaUI(escolaX, alunoZ, alunoZ, new PagPrincipalUI(escolaX, diretorY));
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
	public InfoPessoaUI(Escola sistema, Aluno aluno, Pessoa contaLogada, PagPrincipalUI paginaPrincipal) {
		ehAPessoa = contaLogada.toString().equals(aluno.toString());
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		if(ehAPessoa) {
			setTitle("Suas Infos");
		} else {
			setTitle("Infos do Aluno");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 500);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		titlePane = new JPanel();
		titlePane.setForeground(new Color(0, 0, 0));
		titlePane.setBackground(new Color(240, 230, 140));
		titlePane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 758, 130);
		titlePane.setLayout(null);
		
		lblNome = new JLabel("Nome : " + aluno.getNome());
		lblNome.setForeground(new Color(0, 0, 0));
		lblNome.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 32));
		lblNome.setBounds(32, 20, 706, 48);
		
		lblRegistro = new JLabel("Registro : " + aluno.getRegister());
		lblRegistro.setForeground(new Color(0, 0, 0));
		lblRegistro.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 26));
		lblRegistro.setBounds(68, 72, 365, 46);
		
		lblTurma = new JLabel("Turma : " + aluno.getTurma());
		lblTurma.setForeground(new Color(0, 0, 0));
		lblTurma.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 26));
		lblTurma.setBounds(465, 72, 273, 46);
		
		titlePane.add(lblNome);
		titlePane.add(lblRegistro);
		titlePane.add(lblTurma);
		
		infoPane = new JPanel();
		infoPane.setForeground(new Color(0, 0, 0));
		infoPane.setBackground(new Color(240, 230, 140));
		infoPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		infoPane.setBounds(0, 126, 758, 339);
		infoPane.setLayout(null);
		
		lblNotas = new JLabel("Notas :");
		lblNotas.setForeground(new Color(0, 0, 0));
		lblNotas.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 22));
		lblNotas.setBounds(32, 34, 80, 40);
		
		lblCiencias = new JLabel("Ci�ncias");
		lblCiencias.setForeground(new Color(0, 0, 0));
		lblCiencias.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblCiencias.setBounds(144, 60, 95, 24);
		
		lblMatematica = new JLabel("Matem�tica");
		lblMatematica.setForeground(new Color(0, 0, 0));
		lblMatematica.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblMatematica.setBounds(122, 111, 117, 24);
		
		lblPortugues = new JLabel("Portugu�s");
		lblPortugues.setForeground(new Color(0, 0, 0));
		lblPortugues.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblPortugues.setBounds(134, 162, 105, 24);
		
		lblProva1 = new JLabel("Prova 1");
		lblProva1.setForeground(new Color(0, 0, 0));
		lblProva1.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblProva1.setBounds(259, 22, 76, 24);
		
		lblProva2 = new JLabel("Prova 2");
		lblProva2.setForeground(new Color(0, 0, 0));
		lblProva2.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblProva2.setBounds(377, 22, 76, 24);
		
		spnCienciasProva1 = new JSpinner(new SpinnerNumberModel((double)aluno.getNotas()[0], (double)0, (double)10, (double)0.1));
		spnCienciasProva1.setFont(new Font("Papyrus", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnCienciasProva1.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnCienciasProva1.setEnabled(!ehAPessoa);
		spnCienciasProva1.setBounds(249, 50, 90, 45);
		spnCienciasProva1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				mediaCiencias = (((double)spnCienciasProva1.getValue()) + ((double)spnCienciasProva2.getValue()))/2;
				lblMediaCiencias.setText(String.format("%.1f", mediaCiencias));
				mediaGeral = (mediaCiencias + mediaMatematica + mediaPortugues)/3;
				lblMediaGeralNum.setText(String.format("%.2f", mediaGeral));
			}
		});
		
		spnCienciasProva2 = new JSpinner(new SpinnerNumberModel((double)aluno.getNotas()[1], (double)0, (double)10, (double)0.1));
		spnCienciasProva2.setFont(new Font("Papyrus", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnCienciasProva2.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnCienciasProva2.setEnabled(!ehAPessoa);
		spnCienciasProva2.setBounds(367, 50, 90, 45);
		spnCienciasProva2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				mediaCiencias = (((double)spnCienciasProva1.getValue()) + ((double)spnCienciasProva2.getValue()))/2;
				lblMediaCiencias.setText(String.format("%.1f", mediaCiencias));
				mediaGeral = (mediaCiencias + mediaMatematica + mediaPortugues)/3;
				lblMediaGeralNum.setText(String.format("%.2f", mediaGeral));
			}
		});
		
		spnMatematicaProva1 = new JSpinner(new SpinnerNumberModel((double)aluno.getNotas()[2], (double)0, (double)10, (double)0.1));
		spnMatematicaProva1.setFont(new Font("Papyrus", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnMatematicaProva1.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnMatematicaProva1.setEnabled(!ehAPessoa);
		spnMatematicaProva1.setBounds(249, 100, 90, 45);
		spnMatematicaProva1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				mediaMatematica = (((double)spnMatematicaProva1.getValue()) + ((double)spnMatematicaProva2.getValue()))/2;
				lblMediaMatematica.setText(String.format("%.1f", mediaMatematica));
				mediaGeral = (mediaCiencias + mediaMatematica + mediaPortugues)/3;
				lblMediaGeralNum.setText(String.format("%.2f", mediaGeral));
			}
		});
		
		spnMatematicaProva2 = new JSpinner(new SpinnerNumberModel((double)aluno.getNotas()[3], (double)0, (double)10, (double)0.1));
		spnMatematicaProva2.setFont(new Font("Papyrus", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnMatematicaProva2.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnMatematicaProva2.setEnabled(!ehAPessoa);
		spnMatematicaProva2.setBounds(367, 100, 90, 45);
		spnMatematicaProva2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				mediaMatematica = (((double)spnMatematicaProva1.getValue()) + ((double)spnMatematicaProva2.getValue()))/2;
				lblMediaMatematica.setText(String.format("%.1f", mediaMatematica));
				mediaGeral = (mediaCiencias + mediaMatematica + mediaPortugues)/3;
				lblMediaGeralNum.setText(String.format("%.2f", mediaGeral));
			}
		});
		
		spnPortuguesProva1 = new JSpinner(new SpinnerNumberModel((double)aluno.getNotas()[4], (double)0, (double)10, (double)0.1));
		spnPortuguesProva1.setFont(new Font("Papyrus", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnPortuguesProva1.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnPortuguesProva1.setEnabled(!ehAPessoa);
		spnPortuguesProva1.setBounds(249, 150, 90, 45);
		spnPortuguesProva1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				mediaPortugues = (((double)spnPortuguesProva1.getValue()) + ((double)spnPortuguesProva2.getValue()))/2;
				lblMediaPortugues.setText(String.format("%.1f", mediaPortugues));
				mediaGeral = (mediaCiencias + mediaMatematica + mediaPortugues)/3;
				lblMediaGeralNum.setText(String.format("%.2f", mediaGeral));
			}
		});
		
		spnPortuguesProva2 = new JSpinner(new SpinnerNumberModel((double)aluno.getNotas()[5], (double)0, (double)10, (double)0.1));
		spnPortuguesProva2.setFont(new Font("Papyrus", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnPortuguesProva2.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnPortuguesProva2.setEnabled(!ehAPessoa);
		spnPortuguesProva2.setBounds(367, 150, 90, 45);
		spnPortuguesProva2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				mediaPortugues = (((double)spnPortuguesProva1.getValue()) + ((double)spnPortuguesProva2.getValue()))/2;
				lblMediaPortugues.setText(String.format("%.1f", mediaPortugues));
				mediaGeral = (mediaCiencias + mediaMatematica + mediaPortugues)/3;
				lblMediaGeralNum.setText(String.format("%.2f", mediaGeral));
			}
		});
		
		lblMedia = new JLabel("M�dia");
		lblMedia.setForeground(new Color(0, 0, 0));
		lblMedia.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 20));
		lblMedia.setBounds(489, 22, 68, 24);
		
		mediaCiencias = (aluno.getNotas()[0]+aluno.getNotas()[1])/2;
		lblMediaCiencias = new JLabel(String.format("%.1f", mediaCiencias));
		lblMediaCiencias.setForeground(new Color(0, 0, 0));
		lblMediaCiencias.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 24));
		lblMediaCiencias.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaCiencias.setBounds(489, 59, 56, 24);
		
		mediaMatematica = (aluno.getNotas()[2]+aluno.getNotas()[3])/2;
		lblMediaMatematica = new JLabel(String.format("%.1f", mediaMatematica));
		lblMediaMatematica.setForeground(new Color(0, 0, 0));
		lblMediaMatematica.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 24));
		lblMediaMatematica.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaMatematica.setBounds(489, 109, 56, 24);
		
		mediaPortugues = (aluno.getNotas()[4]+aluno.getNotas()[5])/2;
		lblMediaPortugues = new JLabel(String.format("%.1f", mediaPortugues));
		lblMediaPortugues.setForeground(new Color(0, 0, 0));
		lblMediaPortugues.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 24));
		lblMediaPortugues.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaPortugues.setBounds(489, 159, 56, 24);
		
		lblMediaGeral = new JLabel("M�dia Geral :");
		lblMediaGeral.setForeground(new Color(0, 0, 0));
		lblMediaGeral.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 24));
		lblMediaGeral.setBounds(572, 84, 168, 42);
		
		mediaGeral = (mediaCiencias+mediaMatematica+mediaPortugues)/3;
		lblMediaGeralNum = new JLabel(String.format("%.2f", mediaGeral));
		lblMediaGeralNum.setForeground(new Color(0, 0, 0));
		lblMediaGeralNum.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 26));
		lblMediaGeralNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaGeralNum.setBounds(572, 116, 140, 42);
		
		lblFrequencia = new JLabel("Frequ�ncia :");
		lblFrequencia.setForeground(new Color(0, 0, 0));
		lblFrequencia.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 22));
		lblFrequencia.setBounds(32, 217, 128, 32);
		
		lblFrequenciaNum = new JLabel(((int)(aluno.getFreq()*100)) + "%");
		lblFrequenciaNum.setForeground(new Color(0, 0, 0));
		lblFrequenciaNum.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 16));
		lblFrequenciaNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequenciaNum.setBounds(175 + 2*((int)(aluno.getFreq()*100)), 210, 42, 16);
		
		Dictionary<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		for(int i=0 ; i<=100 ; i+=25) {
			JLabel label = new JLabel(i + "%");
			label.setForeground(new Color(0, 0, 0));
			label.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 12));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setPreferredSize(new Dimension(34, 12));
			labelTable.put(i, label);
		}
		sldFrequencia = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(aluno.getFreq()*100));
		sldFrequencia.setForeground(new Color(0, 0, 0));
		sldFrequencia.setBackground(new Color(240, 230, 140));
		sldFrequencia.setMinorTickSpacing(5);
		sldFrequencia.setMajorTickSpacing(25);
		sldFrequencia.setPaintTicks(true);
		sldFrequencia.setLabelTable(labelTable);
		sldFrequencia.setPaintLabels(true);
		sldFrequencia.setEnabled(!ehAPessoa);
		sldFrequencia.setBounds(180, 223, 230, 47);
		sldFrequencia.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				lblFrequenciaNum.setText(sldFrequencia.getValue() + "%");
				lblFrequenciaNum.setBounds(175 + 2*sldFrequencia.getValue(), 210, 42, 16);
			}
		});
		
		lblOcorrencias = new JLabel("Ocorr�ncias :");
		lblOcorrencias.setForeground(new Color(0, 0, 0));
		lblOcorrencias.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 22));
		lblOcorrencias.setBounds(466, 217, 144, 32);
		
		spnOcorrencias = new JSpinner(new SpinnerNumberModel());
		((SpinnerNumberModel)spnOcorrencias.getModel()).setValue(aluno.getOcorr());
		((SpinnerNumberModel)spnOcorrencias.getModel()).setMinimum(0);
		((SpinnerNumberModel)spnOcorrencias.getModel()).setStepSize(1);
		spnOcorrencias.setFont(new Font("Papyrus", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnOcorrencias.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnOcorrencias.setEnabled(!ehAPessoa);
		spnOcorrencias.setBounds(620, 217, 72, 32);
		
		btnSalvar = new JButton("Salvar Modifica��es");
		btnSalvar.setFont(new Font("Papyrus", Font.BOLD, 18));
		btnSalvar.setEnabled(!ehAPessoa);
		btnSalvar.setBounds(130, 274, 210, 44);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				double[] notas = new double[] {(double)spnCienciasProva1.getValue(), (double)spnCienciasProva2.getValue(), (double)spnMatematicaProva1.getValue(), (double)spnMatematicaProva2.getValue(), (double)spnPortuguesProva1.getValue(), (double)spnPortuguesProva2.getValue()};
				/*sistema.mudaAluno(aluno, (double)sldFrequencia.getValue()/100f, notas, (int)spnOcorrencias.getValue());*/
				JOptionPane.showMessageDialog(null, "Informa��es do Aluno atualizadas", "Modifica��es SALVAS", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		btnRemover = new JButton("Remover Aluno");
		btnRemover.setFont(new Font("Papyrus", Font.BOLD, 18));
		btnRemover.setEnabled(!ehAPessoa);
		btnRemover.setBounds(430, 274, 210, 44);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int certeza = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja remover o Aluno?", "Confirme a a��o", JOptionPane.YES_NO_OPTION);
				if(certeza == JOptionPane.YES_OPTION) {
					sistema.removePessoa(contaLogada, aluno.getRegister());
					JOptionPane.showMessageDialog(null, "Aluno removido do Sistema", "Remo��o realizada com SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		
		infoPane.add(lblNotas);
		infoPane.add(lblCiencias);
		infoPane.add(lblMatematica);
		infoPane.add(lblPortugues);
		infoPane.add(lblProva1);
		infoPane.add(lblProva2);
		infoPane.add(lblMedia);
		infoPane.add(lblMediaCiencias);
		infoPane.add(lblMediaMatematica);
		infoPane.add(lblMediaPortugues);
		infoPane.add(lblMediaGeral);
		infoPane.add(lblMediaGeralNum);
		infoPane.add(lblFrequencia);
		infoPane.add(lblFrequenciaNum);
		infoPane.add(lblOcorrencias);
		infoPane.add(spnCienciasProva1);
		infoPane.add(spnCienciasProva2);
		infoPane.add(spnMatematicaProva1);
		infoPane.add(spnMatematicaProva2);
		infoPane.add(spnPortuguesProva1);
		infoPane.add(spnPortuguesProva2);
		infoPane.add(spnOcorrencias);
		infoPane.add(sldFrequencia);
		infoPane.add(btnSalvar);
		infoPane.add(btnRemover);
		
		contentPane.add(titlePane);
		contentPane.add(infoPane);
	}
	
	public InfoPessoaUI(Escola sistema, Professor professor, Pessoa contaLogada) {
		ehAPessoa = contaLogada.toString().equals(professor.toString());
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
		ehAPessoa = contaLogada.toString().equals(zelador.toString());
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
		ehAPessoa = contaLogada.toString().equals(diretor.toString());
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
