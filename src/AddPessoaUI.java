import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import exceptions.RegistroUsadoException;
import exceptions.UsuarioLogadoInvalidoException;

public class AddPessoaUI extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JLabel lblRegister;
    private JPanel titlePane;
    private JLabel lblName;
    private JTextField txtRegister;
    private JTextField txtName;
	private JLabel lblFunc, lblFunc2;
	private JRadioButton aluno;
	private JRadioButton professor;
	private JRadioButton zelador;
	private JRadioButton diretor;

	private JLabel turmaAluno;
	private JTextField txtTurmaAluno;

	private JLabel lblSalario;
	private JTextField txtSalario;

	private JLabel lblFuncao;
	private JTextField txtFuncao;

	private JLabel lblTurmaProf;
	private JTextField txtTurmaProf;

	private JButton addButton;



    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int ocorrencias = 1;
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Escola escolaX = new Escola();
		Diretor diretorY = new Diretor(9999, "Y", (float) 90/100, "123", 5000.00, ocorrencias);
		Aluno alunoZ = new Aluno(123, "Z", (float) 54/100, "123", "019", ocorrencias, notas );
		GerenciadorDados ga = new GerenciadorDados();
		
		//leitura do banco de dados FAZER ISSO NA INICIALIZA��O DO PROGRAMA
		ga.leAdicionaPessoasArquivos(escolaX, "src/baseDados.csv");
				
		//uso de adicionaPessoa, primeiramente sem permiss�o, depois com
		try {
			escolaX.adicionaPessoa(diretorY, alunoZ);
			escolaX.adicionaPessoa(diretorY, diretorY);
		} catch (RegistroUsadoException e) {
			System.out.println(e.getMessage());
		} catch (UsuarioLogadoInvalidoException e) {
			System.out.println(e.getMessage());
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPessoaUI frame = new AddPessoaUI(escolaX, diretorY, new PagPrincipalUI(escolaX, diretorY));
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
    public AddPessoaUI(Escola escola, Pessoa contaLogada, PagPrincipalUI paginaPrincipal){
        setBackground(Color.WHITE);
        setResizable(false);
        setType(Type.UTILITY);
        setTitle("Adicionar Pessoa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 770, 500);

        contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new GridLayout(8, 2));
		setContentPane(contentPane);

        titlePane = new JPanel();
		titlePane.setForeground(new Color(0, 0, 0));
		titlePane.setBackground(new Color(218, 165, 32));
		titlePane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 1327, 120);
		titlePane.setLayout(null);
	

        lblRegister = new JLabel("N. do Registro:");
		lblRegister.setBounds(61, 236, 262, 48);
		lblRegister.setForeground(new Color(0, 0, 0));

        txtRegister = new JTextField();
		txtRegister.setBounds(333, 239, 340, 38);
		txtRegister.setFont(new Font("Arial", Font.BOLD, 20));
		txtRegister.setBackground(new Color(211, 211, 211));
		txtRegister.setForeground(new Color(105, 105, 105));
		txtRegister.setText("Insira o número do Registro");
        txtRegister.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtRegister.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtRegister.getText().equals("Insira o número do Registro")) {
	            	txtRegister.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });


        lblName = new JLabel("Nome:");
		lblName.setBounds(61, 236, 262, 48);
		lblName.setForeground(new Color(0, 0, 0));

        txtName = new JTextField();
		txtName.setBounds(333, 239, 340, 38);
		txtName.setFont(new Font("Arial", Font.BOLD, 20));
		txtName.setBackground(new Color(211, 211, 211));
		txtName.setForeground(new Color(105, 105, 105));
		txtName.setText("Insira o nome");
        txtName.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtName.getText().equals("Insira o nome")) {
	            	txtName.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });





		turmaAluno = new JLabel("Turma do Aluno:");
		turmaAluno.setBounds(61, 236, 262, 48);
		turmaAluno.setForeground(new Color(0, 0, 0));

		txtTurmaAluno = new JTextField();
		txtTurmaAluno.setBounds(333, 239, 340, 38);
		txtTurmaAluno.setFont(new Font("Arial", Font.BOLD, 20));
		txtTurmaAluno.setBackground(new Color(211, 211, 211));
		txtTurmaAluno.setForeground(new Color(105, 105, 105));
		txtTurmaAluno.setText("Insira a turma");
		txtTurmaAluno.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
			if(txtTurmaAluno.getText().equals("Insira a turma")) {
				txtTurmaAluno.setText("");
				repaint();
				revalidate();
				}           
			}
		});






		lblSalario = new JLabel("Salário:");
		lblSalario.setBounds(61, 236, 262, 48);
		lblSalario.setForeground(new Color(0, 0, 0));

		txtSalario = new JTextField();
		txtSalario.setBounds(333, 239, 340, 38);
		txtSalario.setFont(new Font("Arial", Font.BOLD, 20));
		txtSalario.setBackground(new Color(211, 211, 211));
		txtSalario.setForeground(new Color(105, 105, 105));
		txtSalario.setText("Insira o salário (R$)");
		txtSalario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtSalario.getText().equals("Insira o salário (R$)")) {
						txtSalario.setText("");
						repaint();
						revalidate();
				}           
			}
		});



		lblTurmaProf = new JLabel("Turmas:");
		lblTurmaProf.setBounds(61, 236, 262, 48);
		lblTurmaProf.setForeground(new Color(0, 0, 0));

		txtTurmaProf = new JTextField();
		txtTurmaProf.setBounds(333, 239, 340, 38);
		txtTurmaProf.setFont(new Font("Arial", Font.BOLD, 20));
		txtTurmaProf.setBackground(new Color(211, 211, 211));
		txtTurmaProf.setForeground(new Color(105, 105, 105));
		txtTurmaProf.setText("turmaXX,turmaYY");
		txtTurmaProf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtTurmaProf.getText().equals("turmaXX,turmaYY")) {
					txtTurmaProf.setText("");
					repaint();
					revalidate();
				}           
			}
		});


		lblFuncao = new JLabel("Função:");
		lblFuncao.setBounds(61, 236, 262, 48);
		lblFuncao.setForeground(new Color(0, 0, 0));

		txtFuncao = new JTextField();
		txtFuncao.setBounds(333, 239, 340, 38);
		txtFuncao.setFont(new Font("Arial", Font.BOLD, 20));
		txtFuncao.setBackground(new Color(211, 211, 211));
		txtFuncao.setForeground(new Color(105, 105, 105));
		txtFuncao.setText("Insira a função");
		txtFuncao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtFuncao.getText().equals("Insira a função")) {
					txtFuncao.setText("");
					repaint();
					revalidate();
				}           
			}
		});

		addButton = new JButton("Adicionar");
		addButton.setFont(new Font("Arial", Font.BOLD, 22));
		addButton.setBounds(261, 368, 216, 48);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				if (aluno.isSelected()){
					Double[] notas = new Double[3];
					Pessoa alunx = new Aluno(Long.parseLong(txtRegister.getText()), txtName.getText(), 0, "�", txtTurmaAluno.getText(), 0, notas);
					try {
						escola.adicionaPessoa(contaLogada, alunx);
						JOptionPane.showMessageDialog(null, "Aluno adicionado ao sistema!", "Operação Concluída", JOptionPane.INFORMATION_MESSAGE);
						paginaPrincipal.addPessoaFinished(escola, contaLogada);
						dispose();
					} catch (RegistroUsadoException e) {
						JOptionPane.showMessageDialog(null, "Registro j� existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
					} catch (UsuarioLogadoInvalidoException e) {
						JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o para adicionar uma pessoa!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (professor.isSelected()){
					String[] split = txtTurmaProf.getText().split(",");
					String[] turmas = new String[2];
					turmas[0] = split[0];
					turmas[1] = split[1];
					Pessoa professxr = new Professor(Long.parseLong(txtRegister.getText()), txtName.getText(), 0, "�", Double.parseDouble(txtSalario.getText()), 0, turmas);
					try {
						escola.adicionaPessoa(contaLogada, professxr);
						JOptionPane.showMessageDialog(null, "Professor adicionado ao sistema!", "Operação Concluída", JOptionPane.INFORMATION_MESSAGE);
						paginaPrincipal.addPessoaFinished(escola, contaLogada);
						dispose();
					} catch (RegistroUsadoException e) {
						JOptionPane.showMessageDialog(null, "Registro j� existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
					} catch (UsuarioLogadoInvalidoException e) {
						JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o para adicionar uma pessoa!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (zelador.isSelected()){
					Pessoa zeladxr = new Zelador(Long.parseLong(txtRegister.getText()), txtName.getText(), 0, "�", Double.parseDouble(txtSalario.getText()), 0, txtFuncao.getText());
					try {
						escola.adicionaPessoa(contaLogada, zeladxr);
						JOptionPane.showMessageDialog(null, "Zelador adicionado ao sistema!", "Operação Concluída", JOptionPane.INFORMATION_MESSAGE);
						paginaPrincipal.addPessoaFinished(escola, contaLogada);
						dispose();
					} catch (RegistroUsadoException e) {
						JOptionPane.showMessageDialog(null, "Registro j� existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
					} catch (UsuarioLogadoInvalidoException e) {
						JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o para adicionar uma pessoa!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (diretor.isSelected()){
					Pessoa diretxr = new Diretor(Long.parseLong(txtRegister.getText()), txtName.getText(), 0, "�", Double.parseDouble(txtSalario.getText()), 0);
					try {
						escola.adicionaPessoa(contaLogada, diretxr);
						JOptionPane.showMessageDialog(null, "Diretor adicionado ao sistema!", "Operação Concluída", JOptionPane.INFORMATION_MESSAGE);
						paginaPrincipal.addPessoaFinished(escola, contaLogada);
						dispose();
					} catch (RegistroUsadoException e) {
						JOptionPane.showMessageDialog(null, "Registro j� existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
					} catch (UsuarioLogadoInvalidoException e) {
						JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o para adicionar uma pessoa!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});



		lblFunc = new JLabel("Função:");
		lblFunc.setBounds(61, 236, 262, 48);
		lblFunc.setForeground(new Color(0, 0, 0));
		lblFunc2 = new JLabel("");
		lblFunc2.setBounds(61, 236, 262, 48);
		lblFunc2.setForeground(new Color(0, 0, 0));

		aluno = new JRadioButton("Aluno", false);
		aluno.setBackground(new Color(240, 230, 140));
		professor = new JRadioButton("Professor", false);
		professor.setBackground(new Color(240, 230, 140));
		zelador = new JRadioButton("Zelador", false);
		zelador.setBackground(new Color(240, 230, 140));
		diretor = new JRadioButton("Diretor", false);
		diretor.setBackground(new Color(240, 230, 140));


		contentPane.add(lblFunc);
		contentPane.add(lblFunc2);
		contentPane.add(aluno);
		contentPane.add(professor);
		contentPane.add(zelador);
		contentPane.add(diretor);


		aluno.addItemListener(new RadioButtonHandler());
		professor.addItemListener(new RadioButtonHandler());
		zelador.addItemListener(new RadioButtonHandler());
		diretor.addItemListener(new RadioButtonHandler());


		contentPane.add(lblRegister);
		contentPane.add(txtRegister);
        contentPane.add(lblName);
		contentPane.add(txtName);


    }

	private class RadioButtonHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
		
			
			if (aluno.isSelected()){
				
				
				contentPane.add(turmaAluno);
				contentPane.add(txtTurmaAluno);
				contentPane.add(addButton);

			}
			if (professor.isSelected()){

				
				contentPane.add(lblSalario);
				contentPane.add(txtSalario);
				contentPane.add(lblTurmaProf);
				contentPane.add(txtTurmaProf);
				contentPane.add(addButton);

			}
			if (zelador.isSelected()){

				
				contentPane.add(lblSalario);
				contentPane.add(txtSalario);
				contentPane.add(lblFuncao);
				contentPane.add(txtFuncao);
				contentPane.add(addButton);

			}
			if (diretor.isSelected()){

			
				contentPane.add(lblSalario);
				contentPane.add(txtSalario);
				contentPane.add(addButton);

			}
		}
	}



}
