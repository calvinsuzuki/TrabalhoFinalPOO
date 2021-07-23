import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import exceptions.RegistroUsadoException;
import exceptions.UsuarioLogadoInvalidoException;

public class PagPrincipalUI extends JFrame {
	
	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel cardsPane;
	private JPanel[] topDownPanes;
	private JPanel[] listPanes;
	private JPanel[] previousNextPanes;
	private JScrollPane[] scrollPanes;
	private JTextField txtBusca;
	private JButton btnUser;
	private JButton btnBusca;
	private JButton[] btnsPessoa;
	private JButton btnAddPessoa;
	private JButton[] btnsPrevious;
	private JButton[] btnsNext;
	private JButton[] btnsFirst;
	private JButton[] btnsLast;
	private JMenuBar mnbrFiltros;
	private JMenu mnFiltros;
	private JMenu mnMostrar;
	private JMenu mnOrdenar;
	private JMenuItem mntmReset;
	private JCheckBoxMenuItem chckbxmntmMostrarAlunos;
	private JCheckBoxMenuItem chckbxmntmMostrarProfessores;
	private JCheckBoxMenuItem chckbxmntmMostrarZeladores;
	private JCheckBoxMenuItem chckbxmntmMostrarDiretores;
	private JRadioButtonMenuItem rdbtnmntmOrdenarRegistro;
	private JRadioButtonMenuItem rdbtnmntmOrdenarAlfabetico;
	private ButtonGroup gpOrdenar;
	
	private boolean[] quaisMostrar;
	private boolean registroOuAlpha;
	private ArrayList<Pessoa> pessoasMarcadas;
	private int pageNumAtual;
	private int pageNumWhileInfoPessoa;
	private int pageNumWhileAddPessoa;
	private int scrollValueWhileInfoPessoa;
	private int scrollValueWhileAddPessoa;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Escola escolaX = new Escola();
		Diretor diretorY = new Diretor(9999, "Y", (double) 90/100, "senha", 5000.00, 1);
		Aluno alunoZ = new Aluno(123, "Z", (double) 54/100, "senha", "019", 0, notas );
		GerenciadorDados ga = new GerenciadorDados();
		
		//leitura do banco de dados FAZER ISSO NA INICIALIZAÇÃO DO PROGRAMA
		ga.leAdicionaPessoasArquivos(escolaX, "src/baseDados.csv");
				
		//uso de adicionaPessoa, primeiramente sem permissão, depois com
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
		setType(Type.NORMAL);
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
		titlePane.setBounds(0, 0, 1328, 120);
		titlePane.setLayout(null);
		
		PagPrincipalUI paginaPrincipal = this;
		btnUser = new JButton();
		btnUser.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\user.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT)));
		btnUser.setBounds(32, 28, 62, 62);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InfoPessoaUI frame = new InfoPessoaUI(sistema, contaLogada, contaLogada, paginaPrincipal);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		txtBusca = new JTextField();
		txtBusca.setForeground(new Color(105, 105, 105));
		txtBusca.setBackground(new Color(211, 211, 211));
		txtBusca.setFont(new Font("Arial", Font.BOLD, 20));
		txtBusca.setText("Procurar...");
		txtBusca.setBounds(880, 32, 330, 54);
		txtBusca.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtBusca.getText().equals("Procurar...")) {
	            	txtBusca.setText("");
	                txtBusca.repaint();
	                txtBusca.revalidate();
	            }           
	        }
	    });
		txtBusca.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtBusca.getText().isBlank()) {
					txtBusca.setText("Procurar...");
					txtBusca.repaint();
	                txtBusca.revalidate();
				}
			}
		});
		
		btnBusca = new JButton();
		btnBusca.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\lupa.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		btnBusca.setBounds(1220, 32, 72, 54);
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				pessoasMarcadas = null;
				if(txtBusca.getText().isBlank() || txtBusca.getText().equals("Procurar...")) {
					if(registroOuAlpha) {
						sistema.sortRegistro();
					} else {
						sistema.sortAlpha();
					}
					refreshListPessoas(sistema, contaLogada, quaisMostrar);
					return;
				}
				try {
					long registro = Long.parseLong(txtBusca.getText());
					Pessoa pessoaEncontrada = sistema.buscaPessoa(registro);
					pessoasMarcadas = new ArrayList<Pessoa>();
					if(pessoaEncontrada != null) {
						pessoasMarcadas.add(pessoaEncontrada);
					}
				} catch (Exception e) {
					pessoasMarcadas = sistema.buscaPessoa(txtBusca.getText());
				}
				if(registroOuAlpha) {
					sistema.sortRegistro(pessoasMarcadas);
				} else {
					sistema.sortAlpha(pessoasMarcadas);
				}
				refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
			}
		});
		
		mnbrFiltros = new JMenuBar();
		mnbrFiltros.setBounds(780, 35, 54, 48);
		mnbrFiltros.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 8));
		
		mnFiltros = new JMenu();
		mnFiltros.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\filter.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		mnFiltros.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		mntmReset = new JMenuItem("Resetar  Filtros") {
	        protected void processMouseEvent(MouseEvent evt) {
	            if (evt.getID() == MouseEvent.MOUSE_RELEASED && contains(evt.getPoint())) {
	                doClick();
	                setArmed(true);
	            } else {
	                super.processMouseEvent(evt);
	            }
	        }
		};
		mntmReset.setFont(new Font("Arial", Font.BOLD, 14));
		mntmReset.setHorizontalAlignment(SwingConstants.RIGHT);
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quaisMostrar = new boolean[] {true, true, true, true};
				chckbxmntmMostrarAlunos.setSelected(true);
				chckbxmntmMostrarProfessores.setSelected(true);
				chckbxmntmMostrarZeladores.setSelected(true);
				chckbxmntmMostrarDiretores.setSelected(true);
				registroOuAlpha = true;
				sistema.sortRegistro();
				rdbtnmntmOrdenarRegistro.setSelected(true);
				rdbtnmntmOrdenarAlfabetico.setSelected(false);
				rdbtnmntmOrdenarRegistro.setEnabled(false);
				rdbtnmntmOrdenarAlfabetico.setEnabled(true);
				if(pessoasMarcadas == null) {
					refreshListPessoas(sistema, contaLogada, quaisMostrar);
				} else {
					sistema.sortRegistro(pessoasMarcadas);
					refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
				}
			}
		});
		
		mnMostrar = new JMenu("Mostrar:");
		mnMostrar.setFont(new Font("Arial", Font.BOLD, 14));
		mnMostrar.setHorizontalAlignment(SwingConstants.RIGHT);
		mnMostrar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		chckbxmntmMostrarAlunos = new JCheckBoxMenuItem("Alunos") {
	        protected void processMouseEvent(MouseEvent evt) {
	            if (evt.getID() == MouseEvent.MOUSE_RELEASED && contains(evt.getPoint())) {
	                doClick();
	                setArmed(true);
	            } else {
	                super.processMouseEvent(evt);
	            }
	        }
		};
		chckbxmntmMostrarAlunos.setFont(new Font("Arial", Font.BOLD, 14));
		chckbxmntmMostrarAlunos.setSelected(true);
		chckbxmntmMostrarAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quaisMostrar[0] = !quaisMostrar[0];
				if(pessoasMarcadas == null) {
					refreshListPessoas(sistema, contaLogada, quaisMostrar);
				} else {
					refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
				}
			}
		});
		
		chckbxmntmMostrarProfessores = new JCheckBoxMenuItem("Professores") {
	        protected void processMouseEvent(MouseEvent evt) {
	            if (evt.getID() == MouseEvent.MOUSE_RELEASED && contains(evt.getPoint())) {
	                doClick();
	                setArmed(true);
	            } else {
	                super.processMouseEvent(evt);
	            }
	        }
		};
		chckbxmntmMostrarProfessores.setFont(new Font("Arial", Font.BOLD, 14));
		chckbxmntmMostrarProfessores.setSelected(true);
		chckbxmntmMostrarProfessores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quaisMostrar[1] = !quaisMostrar[1];
				if(pessoasMarcadas == null) {
					refreshListPessoas(sistema, contaLogada, quaisMostrar);
				} else {
					refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
				}
			}
		});
		
		chckbxmntmMostrarZeladores = new JCheckBoxMenuItem("Zeladores") {
	        protected void processMouseEvent(MouseEvent evt) {
	            if (evt.getID() == MouseEvent.MOUSE_RELEASED && contains(evt.getPoint())) {
	                doClick();
	                setArmed(true);
	            } else {
	                super.processMouseEvent(evt);
	            }
	        }
		};
		chckbxmntmMostrarZeladores.setFont(new Font("Arial", Font.BOLD, 14));
		chckbxmntmMostrarZeladores.setSelected(true);
		chckbxmntmMostrarZeladores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quaisMostrar[2] = !quaisMostrar[2];
				if(pessoasMarcadas == null) {
					refreshListPessoas(sistema, contaLogada, quaisMostrar);
				} else {
					refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
				}
			}
		});
		
		chckbxmntmMostrarDiretores = new JCheckBoxMenuItem("Diretores") {
	        protected void processMouseEvent(MouseEvent evt) {
	            if (evt.getID() == MouseEvent.MOUSE_RELEASED && contains(evt.getPoint())) {
	                doClick();
	                setArmed(true);
	            } else {
	                super.processMouseEvent(evt);
	            }
	        }
		};
		chckbxmntmMostrarDiretores.setFont(new Font("Arial", Font.BOLD, 14));
		chckbxmntmMostrarDiretores.setSelected(true);
		chckbxmntmMostrarDiretores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quaisMostrar[3] = !quaisMostrar[3];
				if(pessoasMarcadas == null) {
					refreshListPessoas(sistema, contaLogada, quaisMostrar);
				} else {
					refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
				}
			}
		});
		
		mnMostrar.add(chckbxmntmMostrarAlunos);
		mnMostrar.add(chckbxmntmMostrarProfessores);
		mnMostrar.add(chckbxmntmMostrarZeladores);
		mnMostrar.add(chckbxmntmMostrarDiretores);
		
		mnOrdenar = new JMenu("Ordenar  por:");
		mnOrdenar.setFont(new Font("Arial", Font.BOLD, 14));
		mnOrdenar.setHorizontalAlignment(SwingConstants.RIGHT);
		mnOrdenar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		gpOrdenar = new ButtonGroup();
		
		rdbtnmntmOrdenarRegistro = new JRadioButtonMenuItem("Registro") {
	        protected void processMouseEvent(MouseEvent evt) {
	            if (evt.getID() == MouseEvent.MOUSE_RELEASED && contains(evt.getPoint())) {
	                doClick();
	                setArmed(true);
	            } else {
	                super.processMouseEvent(evt);
	            }
	        }
		};
		rdbtnmntmOrdenarRegistro.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnmntmOrdenarRegistro.setSelected(true);
		rdbtnmntmOrdenarRegistro.setEnabled(false);
		rdbtnmntmOrdenarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				registroOuAlpha = true;
				sistema.sortRegistro();
				if(pessoasMarcadas == null) {
					refreshListPessoas(sistema, contaLogada, quaisMostrar);
				} else {
					sistema.sortRegistro(pessoasMarcadas);
					refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
				}
				rdbtnmntmOrdenarRegistro.setEnabled(false);
				rdbtnmntmOrdenarAlfabetico.setEnabled(true);
			}
		});
		
		rdbtnmntmOrdenarAlfabetico = new JRadioButtonMenuItem("Nome") {
	        protected void processMouseEvent(MouseEvent evt) {
	            if (evt.getID() == MouseEvent.MOUSE_RELEASED && contains(evt.getPoint())) {
	                doClick();
	                setArmed(true);
	            } else {
	                super.processMouseEvent(evt);
	            }
	        }
		};
		rdbtnmntmOrdenarAlfabetico.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnmntmOrdenarAlfabetico.setSelected(false);
		rdbtnmntmOrdenarAlfabetico.setEnabled(true);
		rdbtnmntmOrdenarAlfabetico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				registroOuAlpha = false;
				sistema.sortAlpha();
				if(pessoasMarcadas == null) {
					refreshListPessoas(sistema, contaLogada, quaisMostrar);
				} else {
					sistema.sortAlpha(pessoasMarcadas);
					refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
				}
				rdbtnmntmOrdenarRegistro.setEnabled(true);
				rdbtnmntmOrdenarAlfabetico.setEnabled(false);
			}
		});
		
		gpOrdenar.add(rdbtnmntmOrdenarRegistro);
		gpOrdenar.add(rdbtnmntmOrdenarAlfabetico);
		
		mnOrdenar.add(rdbtnmntmOrdenarRegistro);
		mnOrdenar.add(rdbtnmntmOrdenarAlfabetico);
		
		mnFiltros.add(mntmReset);
		mnFiltros.add(new JSeparator());
		mnFiltros.add(mnMostrar);
		mnFiltros.add(new JSeparator());
		mnFiltros.add(mnOrdenar);
		
		mnbrFiltros.add(mnFiltros);
		
		titlePane.add(txtBusca);
		titlePane.add(btnUser);
		titlePane.add(btnBusca);
		titlePane.add(mnbrFiltros);
		
		quaisMostrar = new boolean[] {true, true, true, true};
		registroOuAlpha = true;
		sistema.sortRegistro();
		pessoasMarcadas = null;
		cardsPane = new JPanel();
		cardsPane.setForeground(new Color(0, 0, 0));
		cardsPane.setBackground(new Color(240, 230, 140));
		cardsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		cardsPane.setBounds(-1, 118, 1330, 628);
		cardsPane.setLayout(new CardLayout());
		refreshListPessoas(sistema, contaLogada, quaisMostrar);
		
		contentPane.add(titlePane);
		contentPane.add(cardsPane);
	}
	
	private void refreshListPessoas(Escola sistema, Pessoa contaLogada, boolean[] quais) {
		ArrayList<Pessoa> pessoas = sistema.getPessoas(quais);
		String[] dadosPessoas = sistema.imprimePessoas(quais);
		int numPessoas = pessoas.size();
		int numPaginas = numPessoas/27 + 1;
		int numPessoasUltimaPagina = numPessoas - (numPaginas-1)*27;
		
		refreshListPessoas(numPessoas, numPaginas, numPessoasUltimaPagina, dadosPessoas, pessoas, sistema, contaLogada);
	}
	
	private void refreshListPessoas(Escola sistema, ArrayList<Pessoa> pessoasParaMostrar, Pessoa contaLogada, boolean[] quais) {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		for(int i=0 ; i<pessoasParaMostrar.size() ; i++) {
			if(pessoasParaMostrar.get(i) instanceof Aluno) {
				if(quais[0]) {
					pessoas.add(pessoasParaMostrar.get(i));
				}
			} else if(pessoasParaMostrar.get(i) instanceof Professor) {
				if(quais[1]) {
					pessoas.add(pessoasParaMostrar.get(i));
				}
			} else if(pessoasParaMostrar.get(i) instanceof Zelador) {
				if(quais[2]) {
					pessoas.add(pessoasParaMostrar.get(i));
				}
			} else if(pessoasParaMostrar.get(i) instanceof Diretor) {
				if(quais[3]) {
					pessoas.add(pessoasParaMostrar.get(i));
				}
			}
		}
		String[] dadosPessoas = new String[pessoas.size()];
		for(int i=0 ; i<pessoas.size() ; i++) {
			dadosPessoas[i] = pessoas.get(i).getClass().toString() + "\nNome: " + pessoas.get(i).getNome() + "\nNº de Registro: " + pessoas.get(i).getRegister();
		}
		int numPessoas = pessoas.size();
		int numPaginas = numPessoas/27 + 1;
		int numPessoasUltimaPagina = numPessoas - (numPaginas-1)*27;
		
		refreshListPessoas(numPessoas, numPaginas, numPessoasUltimaPagina, dadosPessoas, pessoas, sistema, contaLogada);
	}
	
	private void refreshListPessoas(int numPessoas, int numPaginas, int numPessoasUltimaPagina, String[] dadosPessoas, ArrayList<Pessoa> pessoas, Escola sistema, Pessoa contaLogada) {
		pageNumAtual = 0;
		cardsPane.removeAll();
		cardsPane.repaint();
		cardsPane.revalidate();
		
		topDownPanes = new JPanel[numPaginas];
		listPanes = new JPanel[numPaginas];
		previousNextPanes = new JPanel[numPaginas];
		btnsPessoa = new JButton[numPessoas];
		btnsPrevious = new JButton[numPaginas];
		btnsNext = new JButton[numPaginas];
		btnsFirst = new JButton[numPaginas];
		btnsLast = new JButton[numPaginas];
		scrollPanes = new JScrollPane[numPaginas];
		
		for(int i=0 ; i<numPaginas ; i++) {
			topDownPanes[i] = new JPanel();
			topDownPanes[i].setForeground(new Color(0, 0, 0));
			topDownPanes[i].setBackground(new Color(240, 230, 140));
			topDownPanes[i].setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
			topDownPanes[i].setPreferredSize(new Dimension(1310, 1400));
			topDownPanes[i].setLayout(new BorderLayout());
			topDownPanes[i].setAutoscrolls(true);
			
			listPanes[i] = new JPanel();
			listPanes[i].setForeground(new Color(0, 0, 0));
			listPanes[i].setBackground(new Color(240, 230, 140));
			listPanes[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			listPanes[i].setPreferredSize(new Dimension(1310, 1200));
			listPanes[i].setLayout(new FlowLayout(FlowLayout.LEFT, 26, 26));
			
			int numPessoasNessaPagina;
			if(i == numPaginas-1) {
				numPessoasNessaPagina = numPessoasUltimaPagina;
			} else {
				numPessoasNessaPagina = 27;
			}
			
			for(int j=0 ; j<numPessoasNessaPagina ; j++) {
				PagPrincipalUI paginaPrincipal = this;
				int pessoaIndex = 27*i + j;
				int thisPage = i;
				String infoFormatada = "<html>" + dadosPessoas[pessoaIndex].substring(6).replaceAll("\\n", "<br/>") + "</html>";
				btnsPessoa[pessoaIndex] = new JButton(infoFormatada);
				btnsPessoa[pessoaIndex].setHorizontalAlignment(SwingConstants.LEFT);
				btnsPessoa[pessoaIndex].setFont(new Font("Arial", Font.BOLD, 20));
				btnsPessoa[pessoaIndex].setPreferredSize(new Dimension(400, 100));
				btnsPessoa[pessoaIndex].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(checkPermission(contaLogada, pessoas.get(pessoaIndex))) {
							pageNumWhileInfoPessoa = thisPage;
							scrollValueWhileInfoPessoa = scrollPanes[thisPage].getVerticalScrollBar().getValue();
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										InfoPessoaUI frame = new InfoPessoaUI(sistema, pessoas.get(pessoaIndex), contaLogada, paginaPrincipal);
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						} else {
							JOptionPane.showMessageDialog(null, "Você não tem permissão para ver essas informações!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				listPanes[i].add(btnsPessoa[27*i + j]);
			}
			
			if(i == numPaginas-1 && contaLogada instanceof Diretor) {
				PagPrincipalUI paginaPrincipal = this;
				btnAddPessoa = new JButton("<html>Adicionar<br/>nova Pessoa</html>");
				btnAddPessoa.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
				btnAddPessoa.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\plus.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				btnAddPessoa.setPreferredSize(new Dimension(240, 80));
				btnAddPessoa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pageNumWhileAddPessoa = numPaginas-1;
						scrollValueWhileAddPessoa = scrollPanes[numPaginas-1].getVerticalScrollBar().getValue();
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									AddPessoaUI frame = new AddPessoaUI(sistema, contaLogada, paginaPrincipal);
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				});
				
				listPanes[i].add(btnAddPessoa);
			}
			
			previousNextPanes[i] = new JPanel();
			previousNextPanes[i].setForeground(new Color(0, 0, 0));
			previousNextPanes[i].setBackground(new Color(240, 230, 140));
			previousNextPanes[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			previousNextPanes[i].setPreferredSize(new Dimension(1310, 200));
			previousNextPanes[i].setLayout(new GridLayout(3, 2, 810, 20));
			
			if(numPaginas > 1) {
				if(i == 0) {
					addButtonPrevious(i, false);
					addButtonNext(i, true);
					addButtonFirst(i, false);
					addButtonLast(i, true);
				} else if(i == numPaginas-1) {
					addButtonPrevious(i, true);
					addButtonNext(i, false);
					addButtonFirst(i, true);
					addButtonLast(i, false);
				} else {
					addButtonPrevious(i, true);
					addButtonNext(i, true);
					addButtonFirst(i, true);
					addButtonLast(i, true);
				}
			}
			
			topDownPanes[i].add(listPanes[i], BorderLayout.NORTH);
			topDownPanes[i].add(previousNextPanes[i], BorderLayout.SOUTH);
			
			scrollPanes[i] = new JScrollPane(topDownPanes[i]);
			scrollPanes[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPanes[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanes[i].setPreferredSize(new Dimension(1330, 628));
			
			cardsPane.add(scrollPanes[i]);
		}
	}
	
	private void addButtonPrevious(int i, boolean verdade) {
		if(!verdade) {
			btnsPrevious[i] = null;
			JPanel paneVazio = new JPanel();
			paneVazio.setForeground(new Color(0, 0, 0));
			paneVazio.setBackground(new Color(240, 230, 140));
			paneVazio.setBorder(new EmptyBorder(0, 0, 0, 0));
			previousNextPanes[i].add(paneVazio);
			return;
		}
		btnsPrevious[i] = new JButton("Página Anterior");
		btnsPrevious[i].setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 21));
		btnsPrevious[i].setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\previousArrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		btnsPrevious[i].setHorizontalAlignment(SwingConstants.LEFT);
		btnsPrevious[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				((CardLayout)cardsPane.getLayout()).previous(cardsPane);
				pageNumAtual = pageNumAtual - 1;
				((JScrollPane)cardsPane.getComponent(pageNumAtual)).getVerticalScrollBar().setValue(775);
				((JScrollPane)cardsPane.getComponent(pageNumAtual)).getVerticalScrollBar().setValue(0);
			}
		});
		previousNextPanes[i].add(btnsPrevious[i]);
	}
	
	private void addButtonNext(int i, boolean verdade) {
		if(!verdade) {
			btnsNext[i] = null;
			JPanel paneVazio = new JPanel();
			paneVazio.setForeground(new Color(0, 0, 0));
			paneVazio.setBackground(new Color(240, 230, 140));
			paneVazio.setBorder(new EmptyBorder(0, 0, 0, 0));
			previousNextPanes[i].add(paneVazio);
			return;
		}
		btnsNext[i] = new JButton("Próxima Página");
		btnsNext[i].setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 21));
		btnsNext[i].setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\nextArrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		btnsNext[i].setHorizontalAlignment(SwingConstants.RIGHT);
		btnsNext[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				((CardLayout)cardsPane.getLayout()).next(cardsPane);
				pageNumAtual = pageNumAtual + 1;
				((JScrollPane)cardsPane.getComponent(pageNumAtual)).getVerticalScrollBar().setValue(775);
				((JScrollPane)cardsPane.getComponent(pageNumAtual)).getVerticalScrollBar().setValue(0);
			}
		});
		previousNextPanes[i].add(btnsNext[i]);
	}
	
	private void addButtonFirst(int i, boolean verdade) {
		if(!verdade) {
			btnsFirst[i] = null;
			JPanel paneVazio = new JPanel();
			paneVazio.setForeground(new Color(0, 0, 0));
			paneVazio.setBackground(new Color(240, 230, 140));
			paneVazio.setBorder(new EmptyBorder(0, 0, 0, 0));
			previousNextPanes[i].add(paneVazio);
			return;
		}
		btnsFirst[i] = new JButton("Primeira Página");
		btnsFirst[i].setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 21));
		btnsFirst[i].setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\firstArrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		btnsFirst[i].setHorizontalAlignment(SwingConstants.LEFT);
		btnsFirst[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				((CardLayout)cardsPane.getLayout()).first(cardsPane);
				pageNumAtual = 0;
				((JScrollPane)cardsPane.getComponent(pageNumAtual)).getVerticalScrollBar().setValue(775);
				((JScrollPane)cardsPane.getComponent(pageNumAtual)).getVerticalScrollBar().setValue(0);
			}
		});
		previousNextPanes[i].add(btnsFirst[i]);
	}
	
	private void addButtonLast(int i, boolean verdade) {
		if(!verdade) {
			btnsLast[i] = null;
			JPanel paneVazio = new JPanel();
			paneVazio.setForeground(new Color(0, 0, 0));
			paneVazio.setBackground(new Color(240, 230, 140));
			paneVazio.setBorder(new EmptyBorder(0, 0, 0, 0));
			previousNextPanes[i].add(paneVazio);
			return;
		}
		btnsLast[i] = new JButton("Última Página");
		btnsLast[i].setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 21));
		btnsLast[i].setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\lastArrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		btnsLast[i].setHorizontalAlignment(SwingConstants.RIGHT);
		btnsLast[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				((CardLayout)cardsPane.getLayout()).last(cardsPane);
				pageNumAtual = cardsPane.getComponentCount() - 1;
				((JScrollPane)cardsPane.getComponent(pageNumAtual)).getVerticalScrollBar().setValue(775);
				((JScrollPane)cardsPane.getComponent(pageNumAtual)).getVerticalScrollBar().setValue(0);
			}
		});
		previousNextPanes[i].add(btnsLast[i]);
	}
	
	private boolean checkPermission(Pessoa contaLogada, Pessoa aSerVista) {
		if(contaLogada.toString().equals(aSerVista.toString())) {
			return true;
		}
		if(contaLogada instanceof Diretor) {
			return true;
		}
		if(contaLogada instanceof Professor) {
			if(aSerVista instanceof Aluno) {
				String[] turmasProf = ((Professor) contaLogada).getTurmas();
				String turmaAluno = ((Aluno) aSerVista).getTurma();
				for(int i=0 ; i<turmasProf.length ; i++) {
					if(turmasProf[i].equals(turmaAluno)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void addPessoaFinished(Escola sistema, Pessoa contaLogada) {
		if(pessoasMarcadas == null) {
			refreshListPessoas(sistema, contaLogada, quaisMostrar);
		} else {
			refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
		}
		for(int i=0 ; i<pageNumWhileAddPessoa ; i++) {
			((CardLayout)cardsPane.getLayout()).next(cardsPane);
		}
		pageNumAtual = pageNumWhileAddPessoa;
		((JScrollPane)cardsPane.getComponent(pageNumWhileAddPessoa)).getVerticalScrollBar().setValue(775);
		((JScrollPane)cardsPane.getComponent(pageNumWhileAddPessoa)).getVerticalScrollBar().setValue(scrollValueWhileAddPessoa);
	}
	
	public void infoPessoaFinished(Escola sistema, Pessoa contaLogada) {
		if(pessoasMarcadas == null) {
			refreshListPessoas(sistema, contaLogada, quaisMostrar);
		} else {
			refreshListPessoas(sistema, pessoasMarcadas, contaLogada, quaisMostrar);
		}
		for(int i=0 ; i<pageNumWhileInfoPessoa ; i++) {
			((CardLayout)cardsPane.getLayout()).next(cardsPane);
		}
		pageNumAtual = pageNumWhileInfoPessoa;
		((JScrollPane)cardsPane.getComponent(pageNumWhileInfoPessoa)).getVerticalScrollBar().setValue(775);
		((JScrollPane)cardsPane.getComponent(pageNumWhileInfoPessoa)).getVerticalScrollBar().setValue(scrollValueWhileInfoPessoa);
	}
}
