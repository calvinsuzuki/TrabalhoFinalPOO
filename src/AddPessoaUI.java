import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.CardLayout;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import exceptions.RegistroUsadoException;
import exceptions.UsuarioLogadoInvalidoException;

/** 
 * Implementa a tela para adicionar uma pessoa
 * @author Alcino Salviano Cavalcanti, 11892963
 * @author Calvin Suzuki de Camargo, 11232420
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 * @author Pedro Henrique Raymundi, 11795634
 */
public class AddPessoaUI extends JFrame {

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

	private JLabel lblTurmaAluno;
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
	 * Construtor da classe AddPessoaUI - Cria o frame
	 * @param escola - Escola - objeto escola a que estamos nos referindo
	 * @param contaLogada - Pessoa - pessoa que está logada
	 * @param paginaPrincipal - PagPrincipalUI - frame que chamou o construtor
	 */
    public AddPessoaUI(Escola escola, Pessoa contaLogada, PagPrincipalUI paginaPrincipal){
    	JFrame frame = this;
        setBackground(Color.WHITE);
        setResizable(false);
        setType(Type.UTILITY);
        setTitle("Adicionar Pessoa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 770, 540);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addVetoableChangeListener("focusedWindow", new VetoableChangeListener() {
			private boolean focado = false;
			public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
				if(evt.getNewValue() == frame) {
					focado = true;
				}
				if(focado && (evt.getNewValue() != frame) && (evt.getNewValue() != null) && !(evt.getNewValue() instanceof JDialog)) {
					frame.dispose();
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
		titlePane.setBackground(new Color(218, 165, 32));
		titlePane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 758, 120);
		titlePane.setLayout(new GridLayout(3, 2));

        lblRegisterA = new JLabel("Nº do Registro:");
		lblRegisterA.setForeground(new Color(0, 0, 0));
		lblRegisterA.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        txtRegisterA = new JTextField();
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
	            	txtRegisterA.repaint();
	            	txtRegisterA.revalidate();
	            }           
	        }
	    });
		txtRegisterA.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtRegisterA.getText().equals("")) {
					txtRegisterA.setText("Insira o número do Registro");
					txtRegisterA.repaint();
	                txtRegisterA.revalidate();
				}
			}
		});

		lblRegisterP = new JLabel("Nº do Registro:");
		lblRegisterP.setForeground(new Color(0, 0, 0));
		lblRegisterP.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        txtRegisterP = new JTextField();
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
	            	txtRegisterP.repaint();
	            	txtRegisterP.revalidate();
	            }           
	        }
	    });
		txtRegisterP.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtRegisterP.getText().equals("")) {
					txtRegisterP.setText("Insira o número do Registro");
					txtRegisterP.repaint();
	                txtRegisterP.revalidate();
				}
			}
		});

		lblRegisterZ = new JLabel("Nº do Registro:");
		lblRegisterZ.setForeground(new Color(0, 0, 0));
		lblRegisterZ.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        txtRegisterZ = new JTextField();
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
	            	txtRegisterZ.repaint();
	            	txtRegisterZ.revalidate();
	            }           
	        }
	    });
		txtRegisterZ.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtRegisterZ.getText().equals("")) {
					txtRegisterZ.setText("Insira o número do Registro");
					txtRegisterZ.repaint();
	                txtRegisterZ.revalidate();
				}
			}
		});

		lblRegisterD = new JLabel("Nº do Registro:");
		lblRegisterD.setForeground(new Color(0, 0, 0));
		lblRegisterD.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        txtRegisterD = new JTextField();
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
	            	txtRegisterD.repaint();
	            	txtRegisterD.revalidate();
	            }           
	        }
	    });
		txtRegisterD.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtRegisterD.getText().equals("")) {
					txtRegisterD.setText("Insira o número do Registro");
					txtRegisterD.repaint();
	                txtRegisterD.revalidate();
				}
			}
		});

        lblNameA = new JLabel("Nome:");
		lblNameA.setForeground(new Color(0, 0, 0));
		lblNameA.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        txtNameA = new JTextField();
		txtNameA.setFont(new Font("Arial", Font.BOLD, 20));
		txtNameA.setBackground(new Color(211, 211, 211));
		txtNameA.setForeground(new Color(105, 105, 105));
		txtNameA.setText("Insira o nome");
        txtNameA.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtNameA.getText().equals("Insira o nome")) {
	            	txtNameA.setText("");
	            	txtNameA.repaint();
	            	txtNameA.revalidate();
	            }           
	        }
	    });
        txtNameA.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtNameA.getText().equals("")) {
					txtNameA.setText("Insira o nome");
					txtNameA.repaint();
	                txtNameA.revalidate();
				}
			}
		});

		lblNameP = new JLabel("Nome:");
		lblNameP.setForeground(new Color(0, 0, 0));
		lblNameP.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        txtNameP = new JTextField();
		txtNameP.setFont(new Font("Arial", Font.BOLD, 20));
		txtNameP.setBackground(new Color(211, 211, 211));
		txtNameP.setForeground(new Color(105, 105, 105));
		txtNameP.setText("Insira o nome");
        txtNameP.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtNameP.getText().equals("Insira o nome")) {
	            	txtNameP.setText("");
	            	txtNameP.repaint();
	            	txtNameP.revalidate();
	            }           
	        }
	    });
        txtNameP.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtNameP.getText().equals("")) {
					txtNameP.setText("Insira o nome");
					txtNameP.repaint();
	                txtNameP.revalidate();
				}
			}
		});

		lblNameZ = new JLabel("Nome:");
		lblNameZ.setForeground(new Color(0, 0, 0));
		lblNameZ.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        txtNameZ = new JTextField();
		txtNameZ.setFont(new Font("Arial", Font.BOLD, 20));
		txtNameZ.setBackground(new Color(211, 211, 211));
		txtNameZ.setForeground(new Color(105, 105, 105));
		txtNameZ.setText("Insira o nome");
        txtNameZ.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtNameZ.getText().equals("Insira o nome")) {
	            	txtNameZ.setText("");
	            	txtNameZ.repaint();
	            	txtNameZ.revalidate();
	            }           
	        }
	    });
        txtNameZ.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtNameZ.getText().equals("")) {
					txtNameZ.setText("Insira o nome");
					txtNameZ.repaint();
	                txtNameZ.revalidate();
				}
			}
		});

		lblNameD = new JLabel("Nome:");
		lblNameD.setForeground(new Color(0, 0, 0));
		lblNameD.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        txtNameD = new JTextField();
		txtNameD.setFont(new Font("Arial", Font.BOLD, 20));
		txtNameD.setBackground(new Color(211, 211, 211));
		txtNameD.setForeground(new Color(105, 105, 105));
		txtNameD.setText("Insira o nome");
        txtNameD.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtNameD.getText().equals("Insira o nome")) {
	            	txtNameD.setText("");
	            	txtNameD.repaint();
	            	txtNameD.revalidate();
	            }           
	        }
	    });
        txtNameD.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtNameD.getText().equals("")) {
					txtNameD.setText("Insira o nome");
					txtNameD.repaint();
	                txtNameD.revalidate();
				}
			}
		});

		lblTurmaAluno = new JLabel("Turma do Aluno:");
		lblTurmaAluno.setForeground(new Color(0, 0, 0));
		lblTurmaAluno.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

		txtTurmaAluno = new JTextField();
		txtTurmaAluno.setFont(new Font("Arial", Font.BOLD, 20));
		txtTurmaAluno.setBackground(new Color(211, 211, 211));
		txtTurmaAluno.setForeground(new Color(105, 105, 105));
		txtTurmaAluno.setText("Insira a turma");
		txtTurmaAluno.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtTurmaAluno.getText().equals("Insira a turma")) {
					txtTurmaAluno.setText("");
					txtTurmaAluno.repaint();
					txtTurmaAluno.revalidate();
				}           
			}
		});
		txtTurmaAluno.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtTurmaAluno.getText().equals("")) {
					txtTurmaAluno.setText("Insira a turma");
					txtTurmaAluno.repaint();
					txtTurmaAluno.revalidate();
				}
			}
		});

		lblSalarioP = new JLabel("Salário:");
		lblSalarioP.setForeground(new Color(0, 0, 0));
		lblSalarioP.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

		txtSalarioP = new JTextField();
		txtSalarioP.setFont(new Font("Arial", Font.BOLD, 20));
		txtSalarioP.setBackground(new Color(211, 211, 211));
		txtSalarioP.setForeground(new Color(105, 105, 105));
		txtSalarioP.setText("Insira o salário (R$)");
		txtSalarioP.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtSalarioP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtSalarioP.getText().equals("Insira o salário (R$)")) {
						txtSalarioP.setText("");
						txtSalarioP.repaint();
						txtSalarioP.revalidate();
				}           
			}
		});
		txtSalarioP.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtSalarioP.getText().equals("")) {
					txtSalarioP.setText("Insira o salário (R$)");
					txtSalarioP.repaint();
	                txtSalarioP.revalidate();
				}
			}
		});

		lblSalarioZ = new JLabel("Salário:");
		lblSalarioZ.setForeground(new Color(0, 0, 0));
		lblSalarioZ.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

		txtSalarioZ = new JTextField();
		txtSalarioZ.setFont(new Font("Arial", Font.BOLD, 20));
		txtSalarioZ.setBackground(new Color(211, 211, 211));
		txtSalarioZ.setForeground(new Color(105, 105, 105));
		txtSalarioZ.setText("Insira o salário (R$)");
		txtSalarioZ.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtSalarioZ.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtSalarioZ.getText().equals("Insira o salário (R$)")) {
						txtSalarioZ.setText("");
						txtSalarioZ.repaint();
						txtSalarioZ.revalidate();
				}           
			}
		});
		txtSalarioZ.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtSalarioZ.getText().equals("")) {
					txtSalarioZ.setText("Insira o salário (R$)");
					txtSalarioZ.repaint();
	                txtSalarioZ.revalidate();
				}
			}
		});

		lblSalarioD = new JLabel("Salário:");
		lblSalarioD.setForeground(new Color(0, 0, 0));
		lblSalarioD.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

		txtSalarioD = new JTextField();
		txtSalarioD.setFont(new Font("Arial", Font.BOLD, 20));
		txtSalarioD.setBackground(new Color(211, 211, 211));
		txtSalarioD.setForeground(new Color(105, 105, 105));
		txtSalarioD.setText("Insira o salário (R$)");
		txtSalarioD.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtSalarioD.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtSalarioD.getText().equals("Insira o salário (R$)")) {
						txtSalarioD.setText("");
						txtSalarioD.repaint();
						txtSalarioD.revalidate();
				}           
			}
		});
		txtSalarioD.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtSalarioD.getText().equals("")) {
					txtSalarioD.setText("Insira o salário (R$)");
					txtSalarioD.repaint();
	                txtSalarioD.revalidate();
				}
			}
		});

		lblTurmaProf = new JLabel("Turmas:");
		lblTurmaProf.setForeground(new Color(0, 0, 0));
		lblTurmaProf.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

		txtTurmaProf = new JTextField();
		txtTurmaProf.setFont(new Font("Arial", Font.BOLD, 20));
		txtTurmaProf.setBackground(new Color(211, 211, 211));
		txtTurmaProf.setForeground(new Color(105, 105, 105));
		txtTurmaProf.setText("turmaXX,turmaYY");
		txtTurmaProf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtTurmaProf.getText().equals("turmaXX,turmaYY")) {
					txtTurmaProf.setText("");
					txtTurmaProf.repaint();
					txtTurmaProf.revalidate();
				}           
			}
		});
		txtTurmaProf.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtTurmaProf.getText().equals("")) {
					txtTurmaProf.setText("turmaXX,turmaYY");
					txtTurmaProf.repaint();
	                txtTurmaProf.revalidate();
				}
			}
		});

		lblFuncao = new JLabel("Função do zelador:");
		lblFuncao.setForeground(new Color(0, 0, 0));
		lblFuncao.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

		txtFuncao = new JTextField();
		txtFuncao.setFont(new Font("Arial", Font.BOLD, 20));
		txtFuncao.setBackground(new Color(211, 211, 211));
		txtFuncao.setForeground(new Color(105, 105, 105));
		txtFuncao.setText("Insira a função");
		txtFuncao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(txtFuncao.getText().equals("Insira a função")) {
					txtFuncao.setText("");
					txtFuncao.repaint();
					txtFuncao.revalidate();
				}           
			}
		});
		txtFuncao.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtFuncao.getText().equals("")) {
					txtFuncao.setText("Insira a função");
					txtFuncao.repaint();
	                txtFuncao.revalidate();
				}
			}
		});

		addButtonA = new JButton("Adicionar");
		addButtonA.setFont(new Font("Arial", Font.BOLD, 22));
		addButtonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(txtRegisterA.getText().equals("Insira o número do Registro") || txtNameA.getText().equals("Insira o nome") || txtTurmaAluno.getText().equals("Insira a turma")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos indicados!", "ERRO", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Double[] notas = new Double[6];
				for (int i = 0; i < 6; i++) {
					notas[i] = 0.0;
				}
				Pessoa alunx = new Aluno(Long.parseLong(txtRegisterA.getText()), txtNameA.getText(), 0, "§", txtTurmaAluno.getText(), 0, notas);
				try {
					escola.adicionaPessoa(contaLogada, alunx);
					JOptionPane.showMessageDialog(null, "Aluno adicionado ao sistema!", "Operação Concluída", JOptionPane.INFORMATION_MESSAGE);
					paginaPrincipal.addPessoaFinished(escola, contaLogada);
					dispose();
				} catch (RegistroUsadoException e) {
					JOptionPane.showMessageDialog(null, "Registro já existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioLogadoInvalidoException e) {
					JOptionPane.showMessageDialog(null, "Você não tem permissão para adicionar uma pessoa!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		addButtonP = new JButton("Adicionar");
		addButtonP.setFont(new Font("Arial", Font.BOLD, 22));
		addButtonP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(txtRegisterP.getText().equals("Insira o número do Registro") || txtNameP.getText().equals("Insira o nome") || txtSalarioP.getText().equals("Insira o salário (R$)") || txtTurmaProf.getText().equals("turmaXX,turmaYY")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos indicados!", "ERRO", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String[] split = txtTurmaProf.getText().split(",");
				String[] turmas = new String[2];
				turmas[0] = split[0];
				turmas[1] = split[1];
				Pessoa professxr = new Professor(Long.parseLong(txtRegisterP.getText()), txtNameP.getText(), 0, "§", Double.parseDouble(txtSalarioP.getText()), 0, turmas);
				try {
					escola.adicionaPessoa(contaLogada, professxr);
					JOptionPane.showMessageDialog(null, "Professor adicionado ao sistema!", "Operação Concluída", JOptionPane.INFORMATION_MESSAGE);
					paginaPrincipal.addPessoaFinished(escola, contaLogada);
					dispose();
				} catch (RegistroUsadoException e) {
					JOptionPane.showMessageDialog(null, "Registro já existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioLogadoInvalidoException e) {
					JOptionPane.showMessageDialog(null, "Você não tem permissão para adicionar uma pessoa!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		addButtonZ = new JButton("Adicionar");
		addButtonZ.setFont(new Font("Arial", Font.BOLD, 22));
		addButtonZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(txtRegisterZ.getText().equals("Insira o número do Registro") || txtNameZ.getText().equals("Insira o nome") || txtSalarioZ.getText().equals("Insira o salário (R$)") || txtFuncao.getText().equals("Insira a função")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos indicados!", "ERRO", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Pessoa zeladxr = new Zelador(Long.parseLong(txtRegisterZ.getText()), txtNameZ.getText(), 0, "§", Double.parseDouble(txtSalarioZ.getText()), 0, txtFuncao.getText());
				try {
					escola.adicionaPessoa(contaLogada, zeladxr);
					JOptionPane.showMessageDialog(null, "Zelador adicionado ao sistema!", "Operação Concluída", JOptionPane.INFORMATION_MESSAGE);
					paginaPrincipal.addPessoaFinished(escola, contaLogada);
					dispose();
				} catch (RegistroUsadoException e) {
					JOptionPane.showMessageDialog(null, "Registro já existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioLogadoInvalidoException e) {
					JOptionPane.showMessageDialog(null, "Você não tem permissão para adicionar uma pessoa!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		addButtonD = new JButton("Adicionar");
		addButtonD.setFont(new Font("Arial", Font.BOLD, 22));
		addButtonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(txtRegisterD.getText().equals("Insira o número do Registro") || txtNameD.getText().equals("Insira o nome") || txtSalarioD.getText().equals("Insira o salário (R$)")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos indicados!", "ERRO", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Pessoa diretxr = new Diretor(Long.parseLong(txtRegisterD.getText()), txtNameD.getText(), 0, "§", Double.parseDouble(txtSalarioD.getText()), 0);
				try {
					escola.adicionaPessoa(contaLogada, diretxr);
					JOptionPane.showMessageDialog(null, "Diretor adicionado ao sistema!", "Operação Concluída", JOptionPane.INFORMATION_MESSAGE);
					paginaPrincipal.addPessoaFinished(escola, contaLogada);
					dispose();
				} catch (RegistroUsadoException e) {
					JOptionPane.showMessageDialog(null, "Registro já existe!", "ERRO", JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioLogadoInvalidoException e) {
					JOptionPane.showMessageDialog(null, "Você não tem permissão para adicionar uma pessoa!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		lblFunc = new JLabel("Função:");
		lblFunc.setForeground(new Color(0, 0, 0));
		lblFunc.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 28));
		lblFunc2 = new JLabel("");
		lblFunc2.setForeground(new Color(0, 0, 0));
		lblFunc2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

		aluno = new JRadioButton("Aluno", false);
		aluno.setFont(new Font("Arial", Font.BOLD, 22));
		aluno.setOpaque(false);
		aluno.setHorizontalAlignment(SwingConstants.CENTER);
		professor = new JRadioButton("Professor", false);
		professor.setFont(new Font("Arial", Font.BOLD, 22));
		professor.setOpaque(false);
		professor.setHorizontalAlignment(SwingConstants.CENTER);
		zelador = new JRadioButton("Zelador", false);
		zelador.setFont(new Font("Arial", Font.BOLD, 22));
		zelador.setOpaque(false);
		zelador.setHorizontalAlignment(SwingConstants.CENTER);
		diretor = new JRadioButton("Diretor", false);
		diretor.setFont(new Font("Arial", Font.BOLD, 22));
		diretor.setOpaque(false);
		diretor.setHorizontalAlignment(SwingConstants.CENTER);

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
		blankPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		blankPane.setLayout(null);

		JPanel vazioA = new JPanel();
		vazioA.setBackground(new Color(240, 230, 140));
		
		alunoPane = new JPanel();
		alunoPane.setForeground(new Color(0, 0, 0));
		alunoPane.setBackground(new Color(240, 230, 140));
		alunoPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		alunoPane.setLayout(new GridLayout(10, 1));
		alunoPane.add(lblRegisterA);
		alunoPane.add(txtRegisterA);
		alunoPane.add(lblNameA);
		alunoPane.add(txtNameA);
		alunoPane.add(lblTurmaAluno);
		alunoPane.add(txtTurmaAluno);
		alunoPane.add(vazioA);
		alunoPane.add(addButtonA);

		JPanel vazioP = new JPanel();
		vazioP.setBackground(new Color(240, 230, 140));
		
		professorPane = new JPanel();
		professorPane.setForeground(new Color(0, 0, 0));
		professorPane.setBackground(new Color(240, 230, 140));
		professorPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		professorPane.setLayout(new GridLayout(10, 1));
		professorPane.add(lblRegisterP);
		professorPane.add(txtRegisterP);
		professorPane.add(lblNameP);
		professorPane.add(txtNameP);
		professorPane.add(lblSalarioP);
		professorPane.add(txtSalarioP);
		professorPane.add(lblTurmaProf);
		professorPane.add(txtTurmaProf);
		professorPane.add(vazioP);
		professorPane.add(addButtonP);

		JPanel vazioZ = new JPanel();
		vazioZ.setBackground(new Color(240, 230, 140));
		
		zeladorPane = new JPanel();
		zeladorPane.setForeground(new Color(0, 0, 0));
		zeladorPane.setBackground(new Color(240, 230, 140));
		zeladorPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		zeladorPane.setLayout(new GridLayout(10, 1));
		zeladorPane.add(lblRegisterZ);
		zeladorPane.add(txtRegisterZ);
		zeladorPane.add(lblNameZ);
		zeladorPane.add(txtNameZ);
		zeladorPane.add(lblSalarioZ);
		zeladorPane.add(txtSalarioZ);
		zeladorPane.add(lblFuncao);
		zeladorPane.add(txtFuncao);
		zeladorPane.add(vazioZ);
		zeladorPane.add(addButtonZ);

		JPanel vazioD = new JPanel();
		vazioD.setBackground(new Color(240, 230, 140));
		
		diretorPane = new JPanel();
		diretorPane.setForeground(new Color(0, 0, 0));
		diretorPane.setBackground(new Color(240, 230, 140));
		diretorPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		diretorPane.setLayout(new GridLayout(10, 1));
		diretorPane.add(lblRegisterD);
		diretorPane.add(txtRegisterD);
		diretorPane.add(lblNameD);
		diretorPane.add(txtNameD);
		diretorPane.add(lblSalarioD);
		diretorPane.add(txtSalarioD);
		diretorPane.add(vazioD);
		diretorPane.add(addButtonD);

		cardsPane = new JPanel();
		cardsPane.setForeground(new Color(0, 0, 0));
		cardsPane.setBackground(new Color(240, 230, 140));
		cardsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		cardsPane.setBounds(0, 116, 758, 389);
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
				titlePane.setBackground(new Color(46, 129, 255));

			}
			if (professor.isSelected()){

				CardLayout cl = (CardLayout)(cardsPane.getLayout());
				cl.show(cardsPane, (String) professor.getText());
				titlePane.setBackground(new Color(255, 146, 46));

			}
			if (zelador.isSelected()){

				CardLayout cl = (CardLayout)(cardsPane.getLayout());
				cl.show(cardsPane, (String) zelador.getText());
				titlePane.setBackground(new Color(106, 218, 88));

			}
			if (diretor.isSelected()){

				CardLayout cl = (CardLayout)(cardsPane.getLayout());
				cl.show(cardsPane, (String) diretor.getText());
				titlePane.setBackground(new Color(172, 40, 255));
				
			} 
		}
	}
}
