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
import javax.swing.JSeparator;
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
import javax.swing.JRadioButtonMenuItem;

public class PagPrincipalUI extends JFrame {
	
	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel cardsPane;
	private JPanel[] topDownPanes;
	private JPanel[] listPanes;
	private JPanel[] previousNextPanes;
	private JScrollPane[] scrollPanes;
	private JTextField txtBusca;
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
	private JRadioButtonMenuItem rdbtnmntmOrdenarAlfabetico;
	private JRadioButtonMenuItem rdbtnmntmOrdenarRegistro;
	

	private boolean[] quaisMostrar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int ocorrencias = 1;
		String[] horarios = { "1h", "3h", "5h" };
		Double[] notas = { 8.0, 5.0, 5.0, 5.2, 5.6, 7.2};
		Escola escolaX = new Escola();
		Diretor diretorY = new Diretor(9999, "Y", (float) 90/100, 5000.00, horarios, ocorrencias);
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
		
		txtBusca = new JTextField();
		txtBusca.setForeground(new Color(105, 105, 105));
		txtBusca.setBackground(new Color(211, 211, 211));
		txtBusca.setFont(new Font("Papyrus", Font.BOLD, 20));
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
				if(txtBusca.getText().isBlank() || txtBusca.getText().equals("Procurar...")) {
					refreshListPessoas(sistema, contaLogada);
					return;
				}
				ArrayList<Pessoa> pessoasEncontradas = null;
				try {
					int registro = Integer.parseInt(txtBusca.getText());
					Pessoa pessoaEncontrada = sistema.buscaPessoa(registro);
					pessoasEncontradas = new ArrayList<Pessoa>();
					if(pessoaEncontrada != null) {
						pessoasEncontradas.add(pessoaEncontrada);
					}
				} catch (Exception e) {
					pessoasEncontradas = sistema.buscaPessoa(txtBusca.getText());
				}
				refreshListPessoas(pessoasEncontradas, contaLogada);
			}
		});
		
		mnbrFiltros = new JMenuBar();
		mnbrFiltros.setBounds(780, 35, 54, 48);
		mnbrFiltros.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 8));
		
		mnFiltros = new JMenu();
		mnFiltros.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\filter.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		mnFiltros.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		mntmReset = new JMenuItem("Resetar  Filtros");
		mntmReset.setFont(new Font("Papyrus", Font.BOLD, 14));
		mntmReset.setHorizontalAlignment(SwingConstants.RIGHT);
		
		mnMostrar = new JMenu("Mostrar:");
		mnMostrar.setFont(new Font("Papyrus", Font.BOLD, 14));
		mnMostrar.setHorizontalAlignment(SwingConstants.RIGHT);
		mnMostrar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		chckbxmntmMostrarAlunos = new JCheckBoxMenuItem("Alunos");
		chckbxmntmMostrarAlunos.setFont(new Font("Papyrus", Font.BOLD, 14));
		
		chckbxmntmMostrarProfessores = new JCheckBoxMenuItem("Professores");
		chckbxmntmMostrarProfessores.setFont(new Font("Papyrus", Font.BOLD, 14));
		
		chckbxmntmMostrarZeladores = new JCheckBoxMenuItem("Zeladores");
		chckbxmntmMostrarZeladores.setFont(new Font("Papyrus", Font.BOLD, 14));
		
		chckbxmntmMostrarDiretores = new JCheckBoxMenuItem("Diretores");
		chckbxmntmMostrarDiretores.setFont(new Font("Papyrus", Font.BOLD, 14));
		
		mnMostrar.add(chckbxmntmMostrarAlunos);
		mnMostrar.add(chckbxmntmMostrarProfessores);
		mnMostrar.add(chckbxmntmMostrarZeladores);
		mnMostrar.add(chckbxmntmMostrarDiretores);
		
		mnOrdenar = new JMenu("Ordenar  por:");
		mnOrdenar.setFont(new Font("Papyrus", Font.BOLD, 14));
		mnOrdenar.setHorizontalAlignment(SwingConstants.RIGHT);
		mnOrdenar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		rdbtnmntmOrdenarAlfabetico = new JRadioButtonMenuItem("Nome");
		rdbtnmntmOrdenarAlfabetico.setFont(new Font("Papyrus", Font.BOLD, 14));
		
		rdbtnmntmOrdenarRegistro = new JRadioButtonMenuItem("Registro");
		rdbtnmntmOrdenarRegistro.setFont(new Font("Papyrus", Font.BOLD, 14));
		
		mnOrdenar.add(rdbtnmntmOrdenarAlfabetico);
		mnOrdenar.add(rdbtnmntmOrdenarRegistro);
		
		mnFiltros.add(mntmReset);
		mnFiltros.add(new JSeparator());
		mnFiltros.add(mnMostrar);
		mnFiltros.add(new JSeparator());
		mnFiltros.add(mnOrdenar);
		
		mnbrFiltros.add(mnFiltros);
		
		titlePane.add(txtBusca);
		titlePane.add(btnBusca);
		titlePane.add(mnbrFiltros);
		
		cardsPane = new JPanel();
		cardsPane.setForeground(new Color(0, 0, 0));
		cardsPane.setBackground(new Color(240, 230, 140));
		cardsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		cardsPane.setBounds(-1, 118, 1330, 628);
		cardsPane.setLayout(new CardLayout());
		refreshListPessoas(sistema, contaLogada);
		
		contentPane.add(titlePane);
		contentPane.add(cardsPane);
	}
	
	private void refreshListPessoas(Escola sistema, Pessoa contaLogada) {
		int numPessoas = sistema.getNPessoas();
		int numPaginas = numPessoas/27 + 1;
		int numPessoasUltimaPagina = numPessoas - (numPaginas-1)*27;
		ArrayList<Pessoa> pessoas = sistema.getPessoas();
		String[] dadosPessoas = sistema.imprimePessoas();
		
		refreshListPessoas(numPessoas, numPaginas, numPessoasUltimaPagina, dadosPessoas, pessoas, contaLogada);
	}
	
	private void refreshListPessoas(ArrayList<Pessoa> pessoasParaMostrar, Pessoa contaLogada) {
		int numPessoas = pessoasParaMostrar.size();
		int numPaginas = numPessoas/27 + 1;
		int numPessoasUltimaPagina = numPessoas - (numPaginas-1)*27;
		String[] dadosPessoas = new String[numPessoas];
		for(int i=0 ; i<numPessoas ; i++) {
			dadosPessoas[i] = pessoasParaMostrar.get(i).getClass().toString() + "\nNome: " + pessoasParaMostrar.get(i).getNome() + "\nN� de Registro: " + pessoasParaMostrar.get(i).getRegister();
		}
		
		refreshListPessoas(numPessoas, numPaginas, numPessoasUltimaPagina, dadosPessoas, pessoasParaMostrar, contaLogada);
	}
	
	private void refreshListPessoas(int numPessoas, int numPaginas, int numPessoasUltimaPagina, String[] dadosPessoas, ArrayList<Pessoa> pessoas, Pessoa contaLogada) {
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
			
			previousNextPanes[i] = new JPanel();
			previousNextPanes[i].setForeground(new Color(0, 0, 0));
			previousNextPanes[i].setBackground(new Color(240, 230, 140));
			previousNextPanes[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			previousNextPanes[i].setPreferredSize(new Dimension(1310, 200));
			previousNextPanes[i].setLayout(new GridLayout(3, 2, 815, 20));
			
			int numPessoasNessaPagina;
			if(i == numPaginas-1) {
				numPessoasNessaPagina = numPessoasUltimaPagina;
			} else {
				numPessoasNessaPagina = 27;
			}
			
			for(int j=0 ; j<numPessoasNessaPagina ; j++) {
				int pessoaIndex = 27*i + j;
				String infoFormatada = "<html>" + dadosPessoas[pessoaIndex].substring(6).replaceAll("\\n", "<br/>") + "</html>";
				btnsPessoa[pessoaIndex] = new JButton(infoFormatada);
				btnsPessoa[pessoaIndex].setHorizontalAlignment(SwingConstants.LEFT);
				btnsPessoa[pessoaIndex].setFont(new Font("Papyrus", Font.BOLD, 18));
				btnsPessoa[pessoaIndex].setPreferredSize(new Dimension(400, 100));
				/*btnsPessoa[pessoaIndex].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(checkPermission(contaLogada, pessoas.get(pessoaIndex))) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										InfoPessoaUI frame = new InfoPessoaUI(pessoas.get(pessoaIndex), contaLogada);
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						} else {
							JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o para ver essas informa��es!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
						}
					}
				});*/
				listPanes[i].add(btnsPessoa[27*i + j]);
			}
			
			if(i == numPaginas-1 && contaLogada instanceof Diretor) {
				btnAddPessoa = new JButton("<html>Adicionar<br/>nova Pessoa</html>");
				btnAddPessoa.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 22));
				btnAddPessoa.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\plus.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				btnAddPessoa.setPreferredSize(new Dimension(220, 80));
				btnAddPessoa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									AddPessoaUI frame = new AddPessoaUI();
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
			previousNextPanes[i].add(new JPanel());
			return;
		}
		btnsPrevious[i] = new JButton("P�gina Anterior");
		btnsPrevious[i].setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 21));
		btnsPrevious[i].setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\previousArrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		btnsPrevious[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				((CardLayout)cardsPane.getLayout()).previous(cardsPane);
			}
		});
		previousNextPanes[i].add(btnsPrevious[i]);
	}
	
	private void addButtonNext(int i, boolean verdade) {
		if(!verdade) {
			btnsNext[i] = null;
			previousNextPanes[i].add(new JPanel());
			return;
		}
		btnsNext[i] = new JButton("Pr�xima P�gina");
		btnsNext[i].setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 21));
		btnsNext[i].setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\nextArrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		btnsNext[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				((CardLayout)cardsPane.getLayout()).next(cardsPane);
			}
		});
		previousNextPanes[i].add(btnsNext[i]);
	}
	
	private void addButtonFirst(int i, boolean verdade) {
		if(!verdade) {
			btnsFirst[i] = null;
			previousNextPanes[i].add(new JPanel());
			return;
		}
		btnsFirst[i] = new JButton("Primeira P�gina");
		btnsFirst[i].setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 21));
		btnsFirst[i].setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\firstArrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		btnsFirst[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				((CardLayout)cardsPane.getLayout()).first(cardsPane);
			}
		});
		previousNextPanes[i].add(btnsFirst[i]);
	}
	
	private void addButtonLast(int i, boolean verdade) {
		if(!verdade) {
			btnsLast[i] = null;
			previousNextPanes[i].add(new JPanel());
			return;
		}
		btnsLast[i] = new JButton("�ltima P�gina");
		btnsLast[i].setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 21));
		btnsLast[i].setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\lastArrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		btnsLast[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				((CardLayout)cardsPane.getLayout()).last(cardsPane);
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
}
