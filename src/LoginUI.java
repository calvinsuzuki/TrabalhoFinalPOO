import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Image;

public class LoginUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblRegistro;
	private JLabel lblSenha;
	private JTextField txtRegistro;
	private JPasswordField txtSenha;
	private JButton btnLogin;
	private JCheckBox chckbxVerSenha;
	
	private Pessoa contaLogada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI(/*Escola sistema*/) {
		
		setType(Type.UTILITY);
		setTitle("Login Page\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 500);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblRegistro = new JLabel("Nº do Registro:");
		lblRegistro.setBounds(61, 236, 262, 48);
		lblRegistro.setForeground(new Color(0, 0, 0));
		lblRegistro.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 35));
		
		txtRegistro = new JTextField();
		txtRegistro.setBounds(333, 239, 340, 38);
		txtRegistro.setFont(new Font("Papyrus", Font.BOLD, 20));
		txtRegistro.setBackground(new Color(211, 211, 211));
		txtRegistro.setForeground(new Color(105, 105, 105));
		txtRegistro.setText("Insira o número do Registro");
		txtRegistro.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent evt) {
	        	char c = evt.getKeyChar();
	            if (!(((c >= '0') && (c <= '9')) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE))) {
	              getToolkit().beep();
	              evt.consume();
	            }
	        }
		});
		txtRegistro.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtRegistro.getText().equals("Insira o número do Registro")) {
	            	txtRegistro.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(188, 295, 135, 48);
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 35));
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(333, 299, 340, 38);
		txtSenha.setFont(new Font("Papyrus", Font.BOLD, 20));
		txtSenha.setForeground(new Color(105, 105, 105));
		txtSenha.setBackground(new Color(211, 211, 211));
		txtSenha.setEchoChar((char)0);
		txtSenha.setText("Insira a Senha");
		txtSenha.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if((new String(txtSenha.getPassword())).equals("Insira a Senha")) {
	            	txtSenha.setText("");
	            	txtSenha.setEchoChar("*".charAt(0));
	                repaint();
	                revalidate();
	            }           
	        }
	    });
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(261, 368, 216, 48);
		btnLogin.setFont(new Font("Papyrus", Font.BOLD, 22));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					/*contaLogada = sistema.checkLogin(txtRegistro.getText(), new String(txtSenha.getPassword()));*/
					throw new Exception();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Registro ou Senha INVÁLIDO!", "ERRO no Login", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		chckbxVerSenha = new JCheckBox();
		chckbxVerSenha.setBounds(679, 302, 32, 32);
		chckbxVerSenha.setBackground(new Color(240, 230, 140));
		chckbxVerSenha.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\eyeX.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		chckbxVerSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (chckbxVerSenha.isSelected()) {
					txtSenha.setEchoChar((char)0);
					chckbxVerSenha.setIcon(new ImageIcon(new ImageIcon(".\\UI Icons\\eye.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));
				} else {
					if(!(new String(txtSenha.getPassword())).equals("Insira a Senha")) {
						txtSenha.setEchoChar("*".charAt(0));
					}
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
