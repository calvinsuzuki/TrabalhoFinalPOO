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
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

public class InfoPessoaUI extends JFrame {

	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel infoPane;
	private JLabel lblNome;
	private JLabel lblRegistro;
	private JLabel lblTurma;
	private JLabel lblSalario;
	private JLabel lblTurmas;
	private JLabel lblFuncao;
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
	private JLabel lblReclamacoes;
	private JSpinner spnCienciasProva1;
	private JSpinner spnCienciasProva2;
	private JSpinner spnMatematicaProva1;
	private JSpinner spnMatematicaProva2;
	private JSpinner spnPortuguesProva1;
	private JSpinner spnPortuguesProva2;
	private JSpinner spnOcorrencias;
	private JSpinner spnReclamacoes;
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
		Diretor diretorY = new Diretor(9999, "Y", (double) 90/100, "senha", 5000.00, ocorrencias);
		Professor professorW = new Professor(010101, "W", (double) 0.95, "senha", 1000, ocorrencias, new String[] {"A", "B", "C"});
		Zelador zeladorZ = new Zelador(121212, "Z", (double) 0.21, "senha", 102.45, ocorrencias, "sei la po");
		Aluno alunoZ = new Aluno(123, "Z", (double) 54/100, "senha", "019", ocorrencias, notas );
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
					InfoPessoaUI frame = new InfoPessoaUI(escolaX, professorW, diretorY, new PagPrincipalUI(escolaX, diretorY));
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
	public InfoPessoaUI(Escola sistema, Pessoa pessoa, Pessoa contaLogada, PagPrincipalUI paginaPrincipal) {
		if(pessoa instanceof Aluno) {
			InfoAlunoUI(sistema, (Aluno)pessoa, contaLogada, paginaPrincipal);
		} else if(pessoa instanceof Professor) {
			InfoProfessorUI(sistema, (Professor)pessoa, contaLogada, paginaPrincipal);
		} else if(pessoa instanceof Zelador) {
			InfoZeladorUI(sistema, (Zelador)pessoa, contaLogada, paginaPrincipal);
		} else if(pessoa instanceof Diretor) {
			InfoDiretorUI(sistema, (Diretor)pessoa, contaLogada, paginaPrincipal);
		}
	}
	
	public void InfoAlunoUI(Escola sistema, Aluno aluno, Pessoa contaLogada, PagPrincipalUI paginaPrincipal) {
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
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent evt) {
				if(evt.getNewState() == WindowEvent.WINDOW_DEACTIVATED) {
					dispose();
				}
			}
		});
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		titlePane = new JPanel();
		titlePane.setForeground(new Color(0, 0, 0));
		titlePane.setBackground(new Color(46, 129, 255));
		titlePane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 758, 130);
		titlePane.setLayout(null);
		
		lblNome = new JLabel("Nome : " + aluno.getNome());
		lblNome.setForeground(new Color(0, 0, 0));
		lblNome.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 32));
		lblNome.setBounds(32, 20, 706, 48);
		
		lblRegistro = new JLabel("Registro : " + aluno.getRegister());
		lblRegistro.setForeground(new Color(0, 0, 0));
		lblRegistro.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblRegistro.setBounds(68, 72, 365, 46);
		
		lblTurma = new JLabel("Turma : " + aluno.getTurma());
		lblTurma.setForeground(new Color(0, 0, 0));
		lblTurma.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
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
		lblNotas.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblNotas.setBounds(32, 34, 80, 40);
		
		lblCiencias = new JLabel("Ci�ncias");
		lblCiencias.setForeground(new Color(0, 0, 0));
		lblCiencias.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblCiencias.setBounds(144, 60, 95, 24);
		
		lblMatematica = new JLabel("Matem�tica");
		lblMatematica.setForeground(new Color(0, 0, 0));
		lblMatematica.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblMatematica.setBounds(122, 111, 117, 24);
		
		lblPortugues = new JLabel("Portugu�s");
		lblPortugues.setForeground(new Color(0, 0, 0));
		lblPortugues.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblPortugues.setBounds(134, 162, 105, 24);
		
		lblProva1 = new JLabel("Prova 1");
		lblProva1.setForeground(new Color(0, 0, 0));
		lblProva1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblProva1.setBounds(259, 22, 76, 24);
		
		lblProva2 = new JLabel("Prova 2");
		lblProva2.setForeground(new Color(0, 0, 0));
		lblProva2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblProva2.setBounds(377, 22, 76, 24);
		
		spnCienciasProva1 = new JSpinner(new SpinnerNumberModel((double)aluno.getNotas()[0], (double)0, (double)10, (double)0.1));
		spnCienciasProva1.setFont(new Font("Arial", Font.BOLD, 22));
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
		spnCienciasProva2.setFont(new Font("Arial", Font.BOLD, 22));
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
		spnMatematicaProva1.setFont(new Font("Arial", Font.BOLD, 22));
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
		spnMatematicaProva2.setFont(new Font("Arial", Font.BOLD, 22));
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
		spnPortuguesProva1.setFont(new Font("Arial", Font.BOLD, 22));
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
		spnPortuguesProva2.setFont(new Font("Arial", Font.BOLD, 22));
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
		lblMedia.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblMedia.setBounds(489, 22, 68, 24);
		
		mediaCiencias = (aluno.getNotas()[0]+aluno.getNotas()[1])/2;
		lblMediaCiencias = new JLabel(String.format("%.1f", mediaCiencias));
		lblMediaCiencias.setForeground(new Color(0, 0, 0));
		lblMediaCiencias.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		lblMediaCiencias.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaCiencias.setBounds(489, 59, 56, 24);
		
		mediaMatematica = (aluno.getNotas()[2]+aluno.getNotas()[3])/2;
		lblMediaMatematica = new JLabel(String.format("%.1f", mediaMatematica));
		lblMediaMatematica.setForeground(new Color(0, 0, 0));
		lblMediaMatematica.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		lblMediaMatematica.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaMatematica.setBounds(489, 109, 56, 24);
		
		mediaPortugues = (aluno.getNotas()[4]+aluno.getNotas()[5])/2;
		lblMediaPortugues = new JLabel(String.format("%.1f", mediaPortugues));
		lblMediaPortugues.setForeground(new Color(0, 0, 0));
		lblMediaPortugues.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		lblMediaPortugues.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaPortugues.setBounds(489, 159, 56, 24);
		
		lblMediaGeral = new JLabel("M�dia Geral :");
		lblMediaGeral.setForeground(new Color(0, 0, 0));
		lblMediaGeral.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		lblMediaGeral.setBounds(572, 84, 168, 42);
		
		mediaGeral = (mediaCiencias+mediaMatematica+mediaPortugues)/3;
		lblMediaGeralNum = new JLabel(String.format("%.2f", mediaGeral));
		lblMediaGeralNum.setForeground(new Color(0, 0, 0));
		lblMediaGeralNum.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblMediaGeralNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblMediaGeralNum.setBounds(572, 116, 140, 42);
		
		lblFrequencia = new JLabel("Frequ�ncia :");
		lblFrequencia.setForeground(new Color(0, 0, 0));
		lblFrequencia.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblFrequencia.setBounds(32, 217, 132, 32);
		
		lblFrequenciaNum = new JLabel(((int)(aluno.getFreq()*100)) + "%");
		lblFrequenciaNum.setForeground(new Color(0, 0, 0));
		lblFrequenciaNum.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblFrequenciaNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequenciaNum.setBounds(175 + 2*((int)(aluno.getFreq()*100)), 208, 42, 16);
		
		Dictionary<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		for(int i=0 ; i<=100 ; i+=25) {
			JLabel label = new JLabel(i + "%");
			label.setForeground(new Color(0, 0, 0));
			label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
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
				lblFrequenciaNum.setBounds(175 + 2*sldFrequencia.getValue(), 208, 42, 16);
			}
		});
		
		lblOcorrencias = new JLabel("Ocorr�ncias :");
		lblOcorrencias.setForeground(new Color(0, 0, 0));
		lblOcorrencias.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblOcorrencias.setBounds(466, 217, 144, 32);
		
		spnOcorrencias = new JSpinner(new SpinnerNumberModel());
		((SpinnerNumberModel)spnOcorrencias.getModel()).setValue(aluno.getOcorr());
		((SpinnerNumberModel)spnOcorrencias.getModel()).setMinimum(0);
		((SpinnerNumberModel)spnOcorrencias.getModel()).setStepSize(1);
		spnOcorrencias.setFont(new Font("Arial", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnOcorrencias.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnOcorrencias.setEnabled(!ehAPessoa);
		spnOcorrencias.setBounds(620, 217, 72, 32);
		
		btnSalvar = new JButton("Salvar Modifica��es");
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
		btnSalvar.setEnabled(!ehAPessoa);
		btnSalvar.setBounds(130, 274, 210, 44);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aluno.setFreq((double)sldFrequencia.getValue()/100f);
				aluno.setNotas(new Double[] {(double)spnCienciasProva1.getValue(), (double)spnCienciasProva2.getValue(), (double)spnMatematicaProva1.getValue(), (double)spnMatematicaProva2.getValue(), (double)spnPortuguesProva1.getValue(), (double)spnPortuguesProva2.getValue()});
				aluno.setOcorr((int)spnOcorrencias.getValue());
				JOptionPane.showMessageDialog(null, "Informa��es do Aluno atualizadas", "Modifica��es SALVAS", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		btnRemover = new JButton("Remover Aluno");
		btnRemover.setFont(new Font("Arial", Font.BOLD, 18));
		btnRemover.setEnabled(!ehAPessoa);
		btnRemover.setBounds(430, 274, 210, 44);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int certeza = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja remover o Aluno?", "Confirme a a��o", JOptionPane.YES_NO_OPTION);
				if(certeza == JOptionPane.YES_OPTION) {
					sistema.removePessoa(contaLogada, aluno.getRegister());
					JOptionPane.showMessageDialog(null, "Aluno removido do Sistema", "Remo��o realizada com SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					paginaPrincipal.infoPessoaFinished(sistema, contaLogada);
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
	
	public void InfoProfessorUI(Escola sistema, Professor professor, Pessoa contaLogada, PagPrincipalUI paginaPrincipal) {
		ehAPessoa = contaLogada.toString().equals(professor.toString());
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		if(ehAPessoa) {
			setTitle("Suas Infos");
		} else {
			setTitle("Infos do Professor");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 350);
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent evt) {
				if(evt.getNewState() == WindowEvent.WINDOW_DEACTIVATED) {
					dispose();
				}
			}
		});
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		titlePane = new JPanel();
		titlePane.setForeground(new Color(0, 0, 0));
		titlePane.setBackground(new Color(255, 146, 46));
		titlePane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 758, 172);
		titlePane.setLayout(null);
		
		lblNome = new JLabel("Nome : " + professor.getNome());
		lblNome.setForeground(new Color(0, 0, 0));
		lblNome.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 32));
		lblNome.setBounds(32, 20, 706, 48);
		
		lblRegistro = new JLabel("Registro : " + professor.getRegister());
		lblRegistro.setForeground(new Color(0, 0, 0));
		lblRegistro.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblRegistro.setBounds(68, 72, 365, 46);
		
		lblSalario = new JLabel("Sal�rio : $" + String.format("%.2f", professor.getSalario()));
		lblSalario.setForeground(new Color(0, 0, 0));
		lblSalario.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblSalario.setBounds(465, 72, 273, 46);
		
		String turmas = "";
		for(int i=0 ; i<professor.getTurmas().length ; i++) {
			turmas += professor.getTurmas()[i];
			if(i != professor.getTurmas().length - 1) {
				turmas += ", ";
			}
		}
		if(professor.getTurmas().length > 1) {
			lblTurmas = new JLabel("Turmas : " + turmas);
		} else {
			lblTurmas = new JLabel("Turma : " + turmas);
		}
		lblTurmas.setForeground(new Color(0, 0, 0));
		lblTurmas.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblTurmas.setBounds(62, 115, 400, 46);
		
		titlePane.add(lblNome);
		titlePane.add(lblRegistro);
		titlePane.add(lblSalario);
		titlePane.add(lblTurmas);
		
		infoPane = new JPanel();
		infoPane.setForeground(new Color(0, 0, 0));
		infoPane.setBackground(new Color(240, 230, 140));
		infoPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		infoPane.setBounds(0, 168, 758, 147);
		infoPane.setLayout(null);
		
		lblFrequencia = new JLabel("Frequ�ncia :");
		lblFrequencia.setForeground(new Color(0, 0, 0));
		lblFrequencia.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblFrequencia.setBounds(32, 20, 132, 32);
		
		lblFrequenciaNum = new JLabel(((int)(professor.getFreq()*100)) + "%");
		lblFrequenciaNum.setForeground(new Color(0, 0, 0));
		lblFrequenciaNum.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblFrequenciaNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequenciaNum.setBounds(175 + 2*((int)(professor.getFreq()*100)), 11, 42, 16);
		
		Dictionary<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		for(int i=0 ; i<=100 ; i+=25) {
			JLabel label = new JLabel(i + "%");
			label.setForeground(new Color(0, 0, 0));
			label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setPreferredSize(new Dimension(34, 12));
			labelTable.put(i, label);
		}
		sldFrequencia = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(professor.getFreq()*100));
		sldFrequencia.setForeground(new Color(0, 0, 0));
		sldFrequencia.setBackground(new Color(240, 230, 140));
		sldFrequencia.setMinorTickSpacing(5);
		sldFrequencia.setMajorTickSpacing(25);
		sldFrequencia.setPaintTicks(true);
		sldFrequencia.setLabelTable(labelTable);
		sldFrequencia.setPaintLabels(true);
		sldFrequencia.setEnabled(!ehAPessoa);
		sldFrequencia.setBounds(180, 26, 230, 47);
		sldFrequencia.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				lblFrequenciaNum.setText(sldFrequencia.getValue() + "%");
				lblFrequenciaNum.setBounds(175 + 2*sldFrequencia.getValue(), 11, 42, 16);
			}
		});
		
		lblReclamacoes = new JLabel("Reclama��es :");
		lblReclamacoes.setForeground(new Color(0, 0, 0));
		lblReclamacoes.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblReclamacoes.setBounds(456, 20, 156, 32);
		
		spnReclamacoes = new JSpinner(new SpinnerNumberModel());
		((SpinnerNumberModel)spnReclamacoes.getModel()).setValue(professor.getReclam());
		((SpinnerNumberModel)spnReclamacoes.getModel()).setMinimum(0);
		((SpinnerNumberModel)spnReclamacoes.getModel()).setStepSize(1);
		spnReclamacoes.setFont(new Font("Arial", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnReclamacoes.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnReclamacoes.setEnabled(!ehAPessoa);
		spnReclamacoes.setBounds(620, 20, 72, 32);
		
		btnSalvar = new JButton("Salvar Modifica��es");
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
		btnSalvar.setEnabled(!ehAPessoa);
		btnSalvar.setBounds(130, 82, 210, 44);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				professor.setFreq((double)sldFrequencia.getValue()/100f);
				professor.setReclam((int)spnReclamacoes.getValue());
				JOptionPane.showMessageDialog(null, "Informa��es do Professor atualizadas", "Modifica��es SALVAS", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		btnRemover = new JButton("Remover Professor");
		btnRemover.setFont(new Font("Arial", Font.BOLD, 18));
		btnRemover.setEnabled(!ehAPessoa);
		btnRemover.setBounds(430, 82, 210, 44);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int certeza = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja remover o Professor?", "Confirme a a��o", JOptionPane.YES_NO_OPTION);
				if(certeza == JOptionPane.YES_OPTION) {
					sistema.removePessoa(contaLogada, professor.getRegister());
					JOptionPane.showMessageDialog(null, "Professor removido do Sistema", "Remo��o realizada com SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					paginaPrincipal.infoPessoaFinished(sistema, contaLogada);
					dispose();
				}
			}
		});
		
		infoPane.add(lblFrequencia);
		infoPane.add(lblFrequenciaNum);
		infoPane.add(lblReclamacoes);
		infoPane.add(spnReclamacoes);
		infoPane.add(sldFrequencia);
		infoPane.add(btnSalvar);
		infoPane.add(btnRemover);
		
		contentPane.add(titlePane);
		contentPane.add(infoPane);
	}
	
	public void InfoZeladorUI(Escola sistema, Zelador zelador, Pessoa contaLogada, PagPrincipalUI paginaPrincipal) {
		ehAPessoa = contaLogada.toString().equals(zelador.toString());
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		if(ehAPessoa) {
			setTitle("Suas Infos");
		} else {
			setTitle("Infos do Zelador");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 350);
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent evt) {
				if(evt.getNewState() == WindowEvent.WINDOW_DEACTIVATED) {
					dispose();
				}
			}
		});
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		titlePane = new JPanel();
		titlePane.setForeground(new Color(0, 0, 0));
		titlePane.setBackground(new Color(106, 218, 88));
		titlePane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 758, 172);
		titlePane.setLayout(null);
		
		lblNome = new JLabel("Nome : " + zelador.getNome());
		lblNome.setForeground(new Color(0, 0, 0));
		lblNome.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 32));
		lblNome.setBounds(32, 20, 706, 48);
		
		lblRegistro = new JLabel("Registro : " + zelador.getRegister());
		lblRegistro.setForeground(new Color(0, 0, 0));
		lblRegistro.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblRegistro.setBounds(68, 72, 365, 46);
		
		lblSalario = new JLabel("Sal�rio : $" + String.format("%.2f", zelador.getSalario()));
		lblSalario.setForeground(new Color(0, 0, 0));
		lblSalario.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblSalario.setBounds(465, 72, 273, 46);
		
		lblFuncao = new JLabel("Fun��o : " + zelador.getFuncao());
		lblFuncao.setForeground(new Color(0, 0, 0));
		lblFuncao.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblFuncao.setBounds(65, 115, 400, 46);
		
		titlePane.add(lblNome);
		titlePane.add(lblRegistro);
		titlePane.add(lblSalario);
		titlePane.add(lblFuncao);
		
		infoPane = new JPanel();
		infoPane.setForeground(new Color(0, 0, 0));
		infoPane.setBackground(new Color(240, 230, 140));
		infoPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		infoPane.setBounds(0, 168, 758, 147);
		infoPane.setLayout(null);
		
		lblFrequencia = new JLabel("Frequ�ncia :");
		lblFrequencia.setForeground(new Color(0, 0, 0));
		lblFrequencia.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblFrequencia.setBounds(32, 20, 132, 32);
		
		lblFrequenciaNum = new JLabel(((int)(zelador.getFreq()*100)) + "%");
		lblFrequenciaNum.setForeground(new Color(0, 0, 0));
		lblFrequenciaNum.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblFrequenciaNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequenciaNum.setBounds(175 + 2*((int)(zelador.getFreq()*100)), 11, 42, 16);
		
		Dictionary<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		for(int i=0 ; i<=100 ; i+=25) {
			JLabel label = new JLabel(i + "%");
			label.setForeground(new Color(0, 0, 0));
			label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setPreferredSize(new Dimension(34, 12));
			labelTable.put(i, label);
		}
		sldFrequencia = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(zelador.getFreq()*100));
		sldFrequencia.setForeground(new Color(0, 0, 0));
		sldFrequencia.setBackground(new Color(240, 230, 140));
		sldFrequencia.setMinorTickSpacing(5);
		sldFrequencia.setMajorTickSpacing(25);
		sldFrequencia.setPaintTicks(true);
		sldFrequencia.setLabelTable(labelTable);
		sldFrequencia.setPaintLabels(true);
		sldFrequencia.setEnabled(!ehAPessoa);
		sldFrequencia.setBounds(180, 26, 230, 47);
		sldFrequencia.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				lblFrequenciaNum.setText(sldFrequencia.getValue() + "%");
				lblFrequenciaNum.setBounds(175 + 2*sldFrequencia.getValue(), 11, 42, 16);
			}
		});
		
		lblReclamacoes = new JLabel("Reclama��es :");
		lblReclamacoes.setForeground(new Color(0, 0, 0));
		lblReclamacoes.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblReclamacoes.setBounds(456, 20, 156, 32);
		
		spnReclamacoes = new JSpinner(new SpinnerNumberModel());
		((SpinnerNumberModel)spnReclamacoes.getModel()).setValue(zelador.getReclam());
		((SpinnerNumberModel)spnReclamacoes.getModel()).setMinimum(0);
		((SpinnerNumberModel)spnReclamacoes.getModel()).setStepSize(1);
		spnReclamacoes.setFont(new Font("Arial", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnReclamacoes.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnReclamacoes.setEnabled(!ehAPessoa);
		spnReclamacoes.setBounds(620, 20, 72, 32);
		
		btnSalvar = new JButton("Salvar Modifica��es");
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
		btnSalvar.setEnabled(!ehAPessoa);
		btnSalvar.setBounds(130, 82, 210, 44);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				zelador.setFreq((double)sldFrequencia.getValue()/100f);
				zelador.setReclam((int)spnReclamacoes.getValue());
				JOptionPane.showMessageDialog(null, "Informa��es do Zelador atualizadas", "Modifica��es SALVAS", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		btnRemover = new JButton("Remover Zelador");
		btnRemover.setFont(new Font("Arial", Font.BOLD, 18));
		btnRemover.setEnabled(!ehAPessoa);
		btnRemover.setBounds(430, 82, 210, 44);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int certeza = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja remover o Zelador?", "Confirme a a��o", JOptionPane.YES_NO_OPTION);
				if(certeza == JOptionPane.YES_OPTION) {
					sistema.removePessoa(contaLogada, zelador.getRegister());
					JOptionPane.showMessageDialog(null, "Zelador removido do Sistema", "Remo��o realizada com SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					paginaPrincipal.infoPessoaFinished(sistema, contaLogada);
					dispose();
				}
			}
		});
		
		infoPane.add(lblFrequencia);
		infoPane.add(lblFrequenciaNum);
		infoPane.add(lblReclamacoes);
		infoPane.add(spnReclamacoes);
		infoPane.add(sldFrequencia);
		infoPane.add(btnSalvar);
		infoPane.add(btnRemover);
		
		contentPane.add(titlePane);
		contentPane.add(infoPane);
	}
	
	public void InfoDiretorUI(Escola sistema, Diretor diretor, Pessoa contaLogada, PagPrincipalUI paginaPrincipal) {
		ehAPessoa = contaLogada.toString().equals(diretor.toString());
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.UTILITY);
		if(ehAPessoa) {
			setTitle("Suas Infos");
		} else {
			setTitle("Infos do Diretor");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 308);
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent evt) {
				if(evt.getNewState() == WindowEvent.WINDOW_DEACTIVATED) {
					dispose();
				}
			}
		});
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		titlePane = new JPanel();
		titlePane.setForeground(new Color(0, 0, 0));
		titlePane.setBackground(new Color(245, 45, 81));
		titlePane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 758, 130);
		titlePane.setLayout(null);
		
		lblNome = new JLabel("Nome : " + diretor.getNome());
		lblNome.setForeground(new Color(0, 0, 0));
		lblNome.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 32));
		lblNome.setBounds(32, 20, 706, 48);
		
		lblRegistro = new JLabel("Registro : " + diretor.getRegister());
		lblRegistro.setForeground(new Color(0, 0, 0));
		lblRegistro.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblRegistro.setBounds(68, 72, 365, 46);
		
		lblSalario = new JLabel("Sal�rio : $" + String.format("%.2f", diretor.getSalario()));
		lblSalario.setForeground(new Color(0, 0, 0));
		lblSalario.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lblSalario.setBounds(465, 72, 273, 46);
		
		titlePane.add(lblNome);
		titlePane.add(lblRegistro);
		titlePane.add(lblSalario);
		
		infoPane = new JPanel();
		infoPane.setForeground(new Color(0, 0, 0));
		infoPane.setBackground(new Color(240, 230, 140));
		infoPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		infoPane.setBounds(0, 126, 758, 147);
		infoPane.setLayout(null);
		
		lblFrequencia = new JLabel("Frequ�ncia :");
		lblFrequencia.setForeground(new Color(0, 0, 0));
		lblFrequencia.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblFrequencia.setBounds(32, 20, 132, 32);
		
		lblFrequenciaNum = new JLabel(((int)(diretor.getFreq()*100)) + "%");
		lblFrequenciaNum.setForeground(new Color(0, 0, 0));
		lblFrequenciaNum.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblFrequenciaNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequenciaNum.setBounds(175 + 2*((int)(diretor.getFreq()*100)), 11, 42, 16);
		
		Dictionary<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		for(int i=0 ; i<=100 ; i+=25) {
			JLabel label = new JLabel(i + "%");
			label.setForeground(new Color(0, 0, 0));
			label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setPreferredSize(new Dimension(34, 12));
			labelTable.put(i, label);
		}
		sldFrequencia = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(diretor.getFreq()*100));
		sldFrequencia.setForeground(new Color(0, 0, 0));
		sldFrequencia.setBackground(new Color(240, 230, 140));
		sldFrequencia.setMinorTickSpacing(5);
		sldFrequencia.setMajorTickSpacing(25);
		sldFrequencia.setPaintTicks(true);
		sldFrequencia.setLabelTable(labelTable);
		sldFrequencia.setPaintLabels(true);
		sldFrequencia.setBounds(180, 26, 230, 47);
		sldFrequencia.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				lblFrequenciaNum.setText(sldFrequencia.getValue() + "%");
				lblFrequenciaNum.setBounds(175 + 2*sldFrequencia.getValue(), 11, 42, 16);
			}
		});
		
		lblReclamacoes = new JLabel("Reclama��es :");
		lblReclamacoes.setForeground(new Color(0, 0, 0));
		lblReclamacoes.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblReclamacoes.setBounds(456, 20, 156, 32);
		
		spnReclamacoes = new JSpinner(new SpinnerNumberModel());
		((SpinnerNumberModel)spnReclamacoes.getModel()).setValue(diretor.getReclam());
		((SpinnerNumberModel)spnReclamacoes.getModel()).setMinimum(0);
		((SpinnerNumberModel)spnReclamacoes.getModel()).setStepSize(1);
		spnReclamacoes.setFont(new Font("Arial", Font.BOLD, 22));
		((JSpinner.DefaultEditor)spnReclamacoes.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		spnReclamacoes.setBounds(620, 20, 72, 32);
		
		btnSalvar = new JButton("Salvar Modifica��es");
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
		btnSalvar.setBounds(130, 82, 210, 44);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				diretor.setFreq((double)sldFrequencia.getValue()/100f);
				diretor.setReclam((int)spnReclamacoes.getValue());
				JOptionPane.showMessageDialog(null, "Informa��es do Diretor atualizadas", "Modifica��es SALVAS", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		btnRemover = new JButton("Remover Diretor");
		btnRemover.setFont(new Font("Arial", Font.BOLD, 18));
		btnRemover.setEnabled(!ehAPessoa);
		btnRemover.setBounds(430, 82, 210, 44);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int certeza = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja remover o Diretor?", "Confirme a a��o", JOptionPane.YES_NO_OPTION);
				if(certeza == JOptionPane.YES_OPTION) {
					sistema.removePessoa(contaLogada, diretor.getRegister());
					JOptionPane.showMessageDialog(null, "Diretor removido do Sistema", "Remo��o realizada com SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					paginaPrincipal.infoPessoaFinished(sistema, contaLogada);
					dispose();
				}
			}
		});
		
		infoPane.add(lblFrequencia);
		infoPane.add(lblFrequenciaNum);
		infoPane.add(lblReclamacoes);
		infoPane.add(spnReclamacoes);
		infoPane.add(sldFrequencia);
		infoPane.add(btnSalvar);
		infoPane.add(btnRemover);
		
		contentPane.add(titlePane);
		contentPane.add(infoPane);
	}
}
