import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import exceptions.LoginFalhouException;

/** 
 * Implementa a tela de login do programa
 * @author Gabriel Takeshi Miyake Batistella, 11232198
 */
public class LoginUI extends JFrame {
	
	private JPanel contentPane;
	private JLabel lblRegistro;
	private JLabel lblSenha;
	private JTextField txtRegistro;
	private JPasswordField txtSenha;
	private JButton btnLogin;
	private JCheckBox chckbxVerSenha;
	
	private boolean senhaVisivel;
	private Pessoa contaLogada;

	/**
	 * Construtor da classe LoginUI - Cria o frame
	 * @param sistema - Escola - objeto escola a que estamos nos referindo
	 */
	public LoginUI(Escola sistema) {
		setBackground(Color.WHITE);
		setResizable(false);
		setType(Type.NORMAL);
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 500);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(218, 165, 32)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblRegistro = new JLabel("Nº do Registro:");
		lblRegistro.setForeground(new Color(0, 0, 0));
		lblRegistro.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblRegistro.setBounds(61, 232, 262, 48);
		
		txtRegistro = new JTextField();
		txtRegistro.setForeground(new Color(105, 105, 105));
		txtRegistro.setBackground(new Color(211, 211, 211));
		txtRegistro.setFont(new Font("Arial", Font.BOLD, 20));
		txtRegistro.setText("Insira o número do Registro");
		txtRegistro.setBounds(333, 239, 340, 38);
		txtRegistro.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if(!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtRegistro.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtRegistro.getText().equals("Insira o número do Registro")) {
	            	txtRegistro.setText("");
	                txtRegistro.repaint();
	                txtRegistro.revalidate();
	            }           
	        }
	    });
		txtRegistro.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if(txtRegistro.getText().equals("")) {
					txtRegistro.setText("Insira o número do Registro");
					txtRegistro.repaint();
	                txtRegistro.revalidate();
				}
			}
		});
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblSenha.setBounds(198, 292, 125, 48);
		
		senhaVisivel = false;
		txtSenha = new JPasswordField();
		txtSenha.setForeground(new Color(105, 105, 105));
		txtSenha.setBackground(new Color(211, 211, 211));
		txtSenha.setFont(new Font("Arial", Font.BOLD, 20));
		txtSenha.setEchoChar((char)0);
		txtSenha.setText("Insira a Senha");
		txtSenha.setBounds(333, 299, 340, 38);
		txtSenha.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if((new String(txtSenha.getPassword())).equals("Insira a Senha")) {
	            	if(senhaVisivel) {
	            		txtSenha.setEchoChar((char)0);
	            	} else {
	            		txtSenha.setEchoChar("*".charAt(0));
	            	}
	            	txtSenha.setText("");
	                txtSenha.repaint();
	                txtSenha.revalidate();
	            }           
	        }
	    });
		txtSenha.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				if((new String(txtSenha.getPassword())).equals("")) {
					txtSenha.setEchoChar((char)0);
					txtSenha.setText("Insira a Senha");
					txtSenha.repaint();
	                txtSenha.revalidate();
				}
			}
		});
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Arial", Font.BOLD, 22));
		btnLogin.setBounds(261, 368, 216, 48);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					contaLogada = sistema.checkLogin(txtRegistro.getText(), new String(txtSenha.getPassword()));
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								PagPrincipalUI frame = new PagPrincipalUI(sistema, contaLogada);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
				} catch (LoginFalhouException e) {
					JOptionPane.showMessageDialog(null, "Registro ou Senha INVÁLIDO!", "ERRO no Login", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		chckbxVerSenha = new JCheckBox();
		chckbxVerSenha.setBackground(new Color(240, 230, 140));
		chckbxVerSenha.setBounds(679, 302, 32, 32);
		chckbxVerSenha.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\eyeX.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		chckbxVerSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (chckbxVerSenha.isSelected()) {
					txtSenha.setEchoChar((char)0);
					senhaVisivel = true;
					chckbxVerSenha.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\eye.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));
				} else {
					if(!(new String(txtSenha.getPassword())).equals("Insira a Senha")) {
						txtSenha.setEchoChar("*".charAt(0));
					}
					senhaVisivel = false;
					chckbxVerSenha.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\eyeX.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
				}
			}
		});
		
		contentPane.add(lblRegistro);
		contentPane.add(txtRegistro);
		contentPane.add(lblSenha);
		contentPane.add(txtSenha);
		contentPane.add(btnLogin);
		contentPane.add(chckbxVerSenha);
	}
}
