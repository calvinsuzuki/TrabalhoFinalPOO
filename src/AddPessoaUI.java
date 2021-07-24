import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;
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
	private JPanel titlePane;
	private JPanel cardsPane;
    private JLabel lblRegisterA;
	private JLabel lblRegisterP;
	private JLabel lblRegisterZ;
	private JLabel lblRegisterD;
    private JTextField txtRegisterA;
	private JTextField txtRegisterP;
	private JTextField txtRegisterZ;
	private JTextField txtRegisterD;
	private JLabel lblNameA;
	private JLabel lblNameP;
	private JLabel lblNameZ;
	private JLabel lblNameD;
    private JTextField txtNameA;
	private JTextField txtNameP;
	private JTextField txtNameZ;
	private JTextField txtNameD;
	private JLabel lblFunc, lblFunc2;
	private JRadioButton aluno;
	private JRadioButton professor;
	private JRadioButton zelador;
	private JRadioButton diretor;
	private ButtonGroup gpOrdenar;
	private JPanel blankPane;
	private JPanel alunoPane;
	private JPanel professorPane;
	private JPanel zeladorPane;
	private JPanel diretorPane;


	private JLabel turmaAluno;
	private JTextField txtTurmaAluno;

	private JLabel lblSalarioP;
	private JLabel lblSalarioZ;
	private JLabel lblSalarioD;
	private JTextField txtSalarioP;
	private JTextField txtSalarioZ;
	private JTextField txtSalarioD;

	private JLabel lblFuncao;
	private JTextField txtFuncao;

	private JLabel lblTurmaProf;
	private JTextField txtTurmaProf;

	private JButton addButtonA;
	private JButton addButtonP;
	private JButton addButtonZ;
	private JButton addButtonD;



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
        setBounds(100, 100, 770, 540);

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
		titlePane.setBounds(0, 0, 770, 120);
		titlePane.setLayout(new GridLayout(3, 2));
	

        lblRegisterA = new JLabel("N. do Registro:");
		lblRegisterA.setBounds(61, 236, 262, 48);
		lblRegisterA.setForeground(new Color(0, 0, 0));

        txtRegisterA = new JTextField();
		txtRegisterA.setBounds(333, 239, 340, 38);
		txtRegisterA.setFont(new Font("Arial", Font.BOLD, 20));
		txtRegisterA.setBackground(new Color(211, 211, 211));
		txtRegisterA.setForeground(new Color(105, 105, 105));
		txtRegisterA.setText("Insira o número do Registro");
        txtRegisterA.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtRegisterA.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtRegisterA.getText().equals("Insira o número do Registro")) {
	            	txtRegisterA.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });

		lblRegisterP = new JLabel("N. do Registro:");
		lblRegisterP.setBounds(61, 236, 262, 48);
		lblRegisterP.setForeground(new Color(0, 0, 0));

        txtRegisterP = new JTextField();
		txtRegisterP.setBounds(333, 239, 340, 38);
		txtRegisterP.setFont(new Font("Arial", Font.BOLD, 20));
		txtRegisterP.setBackground(new Color(211, 211, 211));
		txtRegisterP.setForeground(new Color(105, 105, 105));
		txtRegisterP.setText("Insira o número do Registro");
        txtRegisterP.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtRegisterP.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtRegisterP.getText().equals("Insira o número do Registro")) {
	            	txtRegisterP.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });

		lblRegisterZ = new JLabel("N. do Registro:");
		lblRegisterZ.setBounds(61, 236, 262, 48);
		lblRegisterZ.setForeground(new Color(0, 0, 0));

        txtRegisterZ = new JTextField();
		txtRegisterZ.setBounds(333, 239, 340, 38);
		txtRegisterZ.setFont(new Font("Arial", Font.BOLD, 20));
		txtRegisterZ.setBackground(new Color(211, 211, 211));
		txtRegisterZ.setForeground(new Color(105, 105, 105));
		txtRegisterZ.setText("Insira o número do Registro");
        txtRegisterZ.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtRegisterZ.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtRegisterZ.getText().equals("Insira o número do Registro")) {
	            	txtRegisterZ.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });

		lblRegisterD = new JLabel("N. do Registro:");
		lblRegisterD.setBounds(61, 236, 262, 48);
		lblRegisterD.setForeground(new Color(0, 0, 0));

        txtRegisterD = new JTextField();
		txtRegisterD.setBounds(333, 239, 340, 38);
		txtRegisterD.setFont(new Font("Arial", Font.BOLD, 20));
		txtRegisterD.setBackground(new Color(211, 211, 211));
		txtRegisterD.setForeground(new Color(105, 105, 105));
		txtRegisterD.setText("Insira o número do Registro");
        txtRegisterD.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtRegisterD.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtRegisterD.getText().equals("Insira o número do Registro")) {
	            	txtRegisterD.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });


        lblNameA = new JLabel("Nome:");
		lblNameA.setBounds(61, 236, 262, 48);
		lblNameA.setForeground(new Color(0, 0, 0));

        txtNameA = new JTextField();
		txtNameA.setBounds(333, 239, 340, 38);
		txtNameA.setFont(new Font("Arial", Font.BOLD, 20));
		txtNameA.setBackground(new Color(211, 211, 211));
		txtNameA.setForeground(new Color(105, 105, 105));
		txtNameA.setText("Insira o nome");
        txtNameA.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtNameA.getText().equals("Insira o nome")) {
	            	txtNameA.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });

		lblNameP = new JLabel("Nome:");
		lblNameP.setBounds(61, 236, 262, 48);
		lblNameP.setForeground(new Color(0, 0, 0));

        txtNameP = new JTextField();
		txtNameP.setBounds(333, 239, 340, 38);
		txtNameP.setFont(new Font("Arial", Font.BOLD, 20));
		txtNameP.setBackground(new Color(211, 211, 211));
		txtNameP.setForeground(new Color(105, 105, 105));
		txtNameP.setText("Insira o nome");
        txtNameP.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtNameP.getText().equals("Insira o nome")) {
	            	txtNameP.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });

		lblNameZ = new JLabel("Nome:");
		lblNameZ.setBounds(61, 236, 262, 48);
		lblNameZ.setForeground(new Color(0, 0, 0));

        txtNameZ = new JTextField();
		txtNameZ.setBounds(333, 239, 340, 38);
		txtNameZ.setFont(new Font("Arial", Font.BOLD, 20));
		txtNameZ.setBackground(new Color(211, 211, 211));
		txtNameZ.setForeground(new Color(105, 105, 105));
		txtNameZ.setText("Insira o nome");
        txtNameZ.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtNameZ.getText().equals("Insira o nome")) {
	            	txtNameZ.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });

		lblNameD = new JLabel("Nome:");
		lblNameD.setBounds(61, 236, 262, 48);
		lblNameD.setForeground(new Color(0, 0, 0));

        txtNameD = new JTextField();
		txtNameD.setBounds(333, 239, 340, 38);
		txtNameD.setFont(new Font("Arial", Font.BOLD, 20));
		txtNameD.setBackground(new Color(211, 211, 211));
		txtNameD.setForeground(new Color(105, 105, 105));
		txtNameD.setText("Insira o nome");
        txtNameD.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtNameD.getText().equals("Insira o nome")) {
	            	txtNameD.setText("");
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






		lblSalarioP = new JLabel("Salário:");
		lblSalarioP.setBounds(61, 236, 262, 48);
		lblSalarioP.setForeground(new Color(0, 0, 0));

		txtSalarioP = new JTextField();
		txtSalarioP.setBounds(333, 239, 340, 38);
		txtSalarioP.setFont(new Font("Arial", Font.BOLD, 20));
		txtSalarioP.setBackground(new Color(211, 211, 211));
		txtSalarioP.setForeground(new Color(105, 105, 105));
		txtSalarioP.setText("Insira o salário (R$)");
		txtSalarioP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtSalarioP.getText().equals("Insira o salário (R$)")) {
						txtSalarioP.setText("");
						repaint();
						revalidate();
				}           
			}
		});

		lblSalarioZ = new JLabel("Salário:");
		lblSalarioZ.setBounds(61, 236, 262, 48);
		lblSalarioZ.setForeground(new Color(0, 0, 0));

		txtSalarioZ = new JTextField();
		txtSalarioZ.setBounds(333, 239, 340, 38);
		txtSalarioZ.setFont(new Font("Arial", Font.BOLD, 20));
		txtSalarioZ.setBackground(new Color(211, 211, 211));
		txtSalarioZ.setForeground(new Color(105, 105, 105));
		txtSalarioZ.setText("Insira o salário (R$)");
		txtSalarioZ.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtSalarioZ.getText().equals("Insira o salário (R$)")) {
						txtSalarioZ.setText("");
						repaint();
						revalidate();
				}           
			}
		});

		lblSalarioD = new JLabel("Salário:");
		lblSalarioD.setBounds(61, 236, 262, 48);
		lblSalarioD.setForeground(new Color(0, 0, 0));

		txtSalarioD = new JTextField();
		txtSalarioD.setBounds(333, 239, 340, 38);
		txtSalarioD.setFont(new Font("Arial", Font.BOLD, 20));
		txtSalarioD.setBackground(new Color(211, 211, 211));
		txtSalarioD.setForeground(new Color(105, 105, 105));
		txtSalarioD.setText("Insira o salário (R$)");
		txtSalarioD.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtSalarioD.getText().equals("Insira o salário (R$)")) {
						txtSalarioD.setText("");
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



		addButtonA = new JButton("Adicionar");
		addButtonA.setFont(new Font("Arial", Font.BOLD, 22));
		addButtonA.setBounds(261, 368, 216, 48);
		addButtonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Double[] notas = new Double[6];
				for (int i = 0; i < 6; i++) {
					notas[i] = 0.0;
				}
				Pessoa alunx = new Aluno(Long.parseLong(txtRegisterA.getText()), txtNameA.getText(), 0, "�", txtTurmaAluno.getText(), 0, notas);
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
		});


		addButtonP = new JButton("Adicionar");
		addButtonP.setFont(new Font("Arial", Font.BOLD, 22));
		addButtonP.setBounds(261, 368, 216, 48);
		addButtonP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String[] split = txtTurmaProf.getText().split(",");
				String[] turmas = new String[2];
				turmas[0] = split[0];
				turmas[1] = split[1];
				Pessoa professxr = new Professor(Long.parseLong(txtRegisterP.getText()), txtNameP.getText(), 0, "�", Double.parseDouble(txtSalarioP.getText()), 0, turmas);
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
		});


		addButtonZ = new JButton("Adicionar");
		addButtonZ.setFont(new Font("Arial", Font.BOLD, 22));
		addButtonZ.setBounds(261, 368, 216, 48);
		addButtonZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Pessoa zeladxr = new Zelador(Long.parseLong(txtRegisterZ.getText()), txtNameZ.getText(), 0, "�", Double.parseDouble(txtSalarioZ.getText()), 0, txtFuncao.getText());
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
		});


		addButtonD = new JButton("Adicionar");
		addButtonD.setFont(new Font("Arial", Font.BOLD, 22));
		addButtonD.setBounds(261, 368, 216, 48);
		addButtonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Pessoa diretxr = new Diretor(Long.parseLong(txtRegisterD.getText()), txtNameD.getText(), 0, "�", Double.parseDouble(txtSalarioD.getText()), 0);
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
		});



		lblFunc = new JLabel("Função:");
		lblFunc.setBounds(61, 236, 262, 48);
		lblFunc.setForeground(new Color(0, 0, 0));
		lblFunc2 = new JLabel("");
		lblFunc2.setBounds(61, 236, 262, 48);
		lblFunc2.setForeground(new Color(0, 0, 0));

		aluno = new JRadioButton("Aluno", false);
		aluno.setBackground(new Color(218, 165, 32));
		professor = new JRadioButton("Professor", false);
		professor.setBackground(new Color(218, 165, 32));
		zelador = new JRadioButton("Zelador", false);
		zelador.setBackground(new Color(218, 165, 32));
		diretor = new JRadioButton("Diretor", false);
		diretor.setBackground(new Color(218, 165, 32));

		aluno.addItemListener(new RadioButtonHandler());
		professor.addItemListener(new RadioButtonHandler());
		zelador.addItemListener(new RadioButtonHandler());
		diretor.addItemListener(new RadioButtonHandler());
		
		
		gpOrdenar = new ButtonGroup();
		gpOrdenar.add(aluno);
		gpOrdenar.add(professor);
		gpOrdenar.add(zelador);
		gpOrdenar.add(diretor);


		blankPane = new JPanel();
		blankPane.setForeground(new Color(0, 0, 0));
		blankPane.setBackground(new Color(240, 230, 140));
		blankPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		blankPane.setBounds(0, 0, 1300, 628);
		blankPane.setLayout(new CardLayout());


		alunoPane = new JPanel();
		alunoPane.setForeground(new Color(0, 0, 0));
		alunoPane.setBackground(new Color(240, 230, 140));
		alunoPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		alunoPane.setBounds(0, 0, 760, 540);
		alunoPane.setLayout(new GridLayout(15, 2));
		alunoPane.add(lblRegisterA);
		alunoPane.add(txtRegisterA);
		alunoPane.add(lblNameA);
		alunoPane.add(txtNameA);
		alunoPane.add(turmaAluno);
		alunoPane.add(txtTurmaAluno);
		alunoPane.add(addButtonA);

		professorPane = new JPanel();
		professorPane.setForeground(new Color(0, 0, 0));
		professorPane.setBackground(new Color(240, 230, 140));
		professorPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		professorPane.setBounds(0, 0, 760, 540);
		professorPane.setLayout(new GridLayout(15, 2));
		professorPane.add(lblRegisterP);
		professorPane.add(txtRegisterP);
		professorPane.add(lblNameP);
		professorPane.add(txtNameP);
		professorPane.add(lblSalarioP);
		professorPane.add(txtSalarioP);
		professorPane.add(lblTurmaProf);
		professorPane.add(txtTurmaProf);
		professorPane.add(addButtonP);

		zeladorPane = new JPanel();
		zeladorPane.setForeground(new Color(0, 0, 0));
		zeladorPane.setBackground(new Color(240, 230, 140));
		zeladorPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		zeladorPane.setBounds(0, 0, 760, 540);
		zeladorPane.setLayout(new GridLayout(15, 2));
		zeladorPane.add(lblRegisterZ);
		zeladorPane.add(txtRegisterZ);
		zeladorPane.add(lblNameZ);
		zeladorPane.add(txtNameZ);
		zeladorPane.add(lblSalarioZ);
		zeladorPane.add(txtSalarioZ);
		zeladorPane.add(lblFuncao);
		zeladorPane.add(txtFuncao);
		zeladorPane.add(addButtonZ);

		diretorPane = new JPanel();
		diretorPane.setForeground(new Color(0, 0, 0));
		diretorPane.setBackground(new Color(240, 230, 140));
		diretorPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		diretorPane.setBounds(0, 0, 760, 540);
		diretorPane.setLayout(new GridLayout(15, 2));
		diretorPane.add(lblRegisterD);
		diretorPane.add(txtRegisterD);
		diretorPane.add(lblNameD);
		diretorPane.add(txtNameD);
		diretorPane.add(lblSalarioD);
		diretorPane.add(txtSalarioD);
		diretorPane.add(addButtonD);



		cardsPane = new JPanel();
		cardsPane.setForeground(new Color(0, 0, 0));
		cardsPane.setBackground(new Color(240, 230, 140));
		cardsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		cardsPane.setBounds(-1, 118, 760, 540);
		cardsPane.setLayout(new CardLayout());
		cardsPane.add(blankPane);
		cardsPane.add(alunoPane, aluno.getText());
		cardsPane.add(professorPane, professor.getText());
		cardsPane.add(zeladorPane, zelador.getText());
		cardsPane.add(diretorPane, diretor.getText());



		titlePane.add(lblFunc);
		titlePane.add(lblFunc2);
		titlePane.add(aluno);
		titlePane.add(professor);
		titlePane.add(zelador);
		titlePane.add(diretor);


		contentPane.add(titlePane);
		contentPane.add(cardsPane);



    }

	private class RadioButtonHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
		

			if (aluno.isSelected()){
				
				CardLayout cl = (CardLayout)(cardsPane.getLayout());
				cl.show(cardsPane, (String) aluno.getText());

			}
			if (professor.isSelected()){

				CardLayout cl = (CardLayout)(cardsPane.getLayout());
				cl.show(cardsPane, (String) professor.getText());

			}
			if (zelador.isSelected()){

				CardLayout cl = (CardLayout)(cardsPane.getLayout());
				cl.show(cardsPane, (String) zelador.getText());

			}
			if (diretor.isSelected()){

				CardLayout cl = (CardLayout)(cardsPane.getLayout());
				cl.show(cardsPane, (String) diretor.getText());
				
			} 
		}
	}



}
