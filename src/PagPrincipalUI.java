import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagPrincipalUI extends JFrame {
	
	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel cardsPane;
	private JPanel[] listPanes;
	private JScrollPane[] scrollPanes;
	private JLabel lblAddPessoa;
	private JTextField txtBusca;
	private JButton btnBusca;
	private JButton[] btnsPessoa;
	private JButton btnAddPessoa;

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
				if(txtBusca.getText().equals("")) {
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
				} finally {
					refreshListPessoas(pessoasEncontradas, contaLogada);
				}
			}
		});
		
		cardsPane = new JPanel();
		cardsPane.setForeground(new Color(0, 0, 0));
		cardsPane.setBackground(new Color(240, 230, 140));
		cardsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		cardsPane.setBounds(-1, 118, 1330, 628);
		cardsPane.setLayout(new CardLayout());
		refreshListPessoas(sistema, contaLogada);
		
		titlePane.add(txtBusca);
		titlePane.add(btnBusca);
		contentPane.add(titlePane);
		contentPane.add(cardsPane);
	}
	
	private void refreshListPessoas(Escola sistema, Pessoa contaLogada) {
		cardsPane.removeAll();
		cardsPane.repaint();
		cardsPane.revalidate();
		
		int numPessoas = sistema.getNPessoas();
		int numPaginas = numPessoas/27 + 1;
		int numPessoasUltimaPagina = numPessoas - (numPaginas-1)*27;
		ArrayList<Pessoa> pessoas = sistema.getPessoas();
		String[] dadosPessoas = sistema.imprimePessoas();
		
		listPanes = new JPanel[numPaginas];
		btnsPessoa = new JButton[numPessoas];
		scrollPanes = new JScrollPane[numPaginas];
		
		for(int i=0 ; i<numPaginas ; i++) {
			listPanes[i] = new JPanel();
			listPanes[i].setForeground(new Color(0, 0, 0));
			listPanes[i].setBackground(new Color(240, 230, 140));
			listPanes[i].setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
			listPanes[i].setPreferredSize(new Dimension(1310, 1500));
			listPanes[i].setLayout(new FlowLayout(FlowLayout.LEFT, 26, 26));
			listPanes[i].setAutoscrolls(true);
			
			int numPessoasNessaPagina;
			if(i == numPaginas-1) {
				numPessoasNessaPagina = numPessoasUltimaPagina;
			} else {
				numPessoasNessaPagina = 27;
			}
			
			for(int j=0 ; j<numPessoasNessaPagina ; j++) {
				int pessoaIndex = 27*i + j;
				String infoFormatada = "<html>" + dadosPessoas[pessoaIndex].replaceAll("\\n", "<br/>") + "</html>";
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
							JOptionPane.showMessageDialog(null, "Você não tem permissão para ver essas informações!", "ACESSO NEGADO", JOptionPane.ERROR_MESSAGE);
						}
					}
				});*/
				listPanes[i].add(btnsPessoa[27*i + j]);
			}
			
			if(i == numPaginas-1 && contaLogada instanceof Diretor) {
				btnAddPessoa = new JButton();
				btnAddPessoa.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\plus.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				btnAddPessoa.setPreferredSize(new Dimension(60, 60));
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
				
				lblAddPessoa = new JLabel("<html>Adicionar<br/>nova Pessoa</html>");
				lblAddPessoa.setForeground(new Color(140, 140, 140));
				lblAddPessoa.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 30));
				lblAddPessoa.setPreferredSize(new Dimension(240, 100));
				
				listPanes[i].add(btnAddPessoa);
				listPanes[i].add(lblAddPessoa);
			}
			
			scrollPanes[i] = new JScrollPane(listPanes[i]);
			scrollPanes[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPanes[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanes[i].setPreferredSize(new Dimension(1330, 628));
			
			cardsPane.add(scrollPanes[i]);
		}
	}
	
	private void refreshListPessoas(ArrayList<Pessoa> pessoasParaMostrar, Pessoa contaLogada) {
		cardsPane.removeAll();
		cardsPane.repaint();
		cardsPane.revalidate();
		
		int numPessoas = pessoasParaMostrar.size();
		System.out.println(numPessoas);
		int numPaginas = numPessoas/27 + 1;
		int numPessoasUltimaPagina = numPessoas - (numPaginas-1)*27;
		String[] dadosPessoas = new String[numPessoas];
		for(int i=0 ; i<numPessoas ; i++) {
			dadosPessoas[i] = pessoasParaMostrar.get(i).getClass().toString() + "\nNome: " + pessoasParaMostrar.get(i).getNome() + "\nNº de Registro: " + pessoasParaMostrar.get(i).getRegister();
		}
		
		listPanes = new JPanel[numPaginas];
		btnsPessoa = new JButton[numPessoas];
		scrollPanes = new JScrollPane[numPaginas];
		
		for(int i=0 ; i<numPaginas ; i++) {
			listPanes[i] = new JPanel();
			listPanes[i].setForeground(new Color(0, 0, 0));
			listPanes[i].setBackground(new Color(240, 230, 140));
			listPanes[i].setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
			listPanes[i].setPreferredSize(new Dimension(1310, 1500));
			listPanes[i].setLayout(new FlowLayout(FlowLayout.LEFT, 26, 26));
			listPanes[i].setAutoscrolls(true);
			
			int numPessoasNessaPagina;
			if(i == numPaginas-1) {
				numPessoasNessaPagina = numPessoasUltimaPagina;
			} else {
				numPessoasNessaPagina = 27;
			}
			
			for(int j=0 ; j<numPessoasNessaPagina ; j++) {
				int pessoaIndex = 27*i + j;
				String infoFormatada = "<html>" + dadosPessoas[pessoaIndex].replaceAll("\\n", "<br/>") + "</html>";
				btnsPessoa[pessoaIndex] = new JButton(infoFormatada);
				btnsPessoa[pessoaIndex].setHorizontalAlignment(SwingConstants.LEFT);
				btnsPessoa[pessoaIndex].setFont(new Font("Papyrus", Font.BOLD, 18));
				btnsPessoa[pessoaIndex].setPreferredSize(new Dimension(400, 100));
				/*btnsPessoa[pessoaIndex].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(checkPermission(contaLogada, pessoasParaMostrar.get(pessoaIndex))) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										InfoPessoaUI frame = new InfoPessoaUI(pessoasParaMostrar.get(pessoaIndex), contaLogada);
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
				});*/
				listPanes[i].add(btnsPessoa[27*i + j]);
			}
			
			if(i == numPaginas-1 && contaLogada instanceof Diretor) {
				btnAddPessoa = new JButton();
				btnAddPessoa.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\plus.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				btnAddPessoa.setPreferredSize(new Dimension(60, 60));
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
				
				lblAddPessoa = new JLabel("<html>Adicionar<br/>nova Pessoa</html>");
				lblAddPessoa.setForeground(new Color(140, 140, 140));
				lblAddPessoa.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 30));
				lblAddPessoa.setPreferredSize(new Dimension(240, 100));
				
				listPanes[i].add(btnAddPessoa);
				listPanes[i].add(lblAddPessoa);
			}
			
			scrollPanes[i] = new JScrollPane(listPanes[i]);
			scrollPanes[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPanes[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanes[i].setPreferredSize(new Dimension(1330, 628));
			
			cardsPane.add(scrollPanes[i]);
		}
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
