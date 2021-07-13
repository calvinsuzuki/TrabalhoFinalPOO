import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Container;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddPessoaUI extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JLabel lblRegister;
    private JPanel titlePane;
    private JLabel lblName;
    private JLabel lblFreq;
    private JTextField txtRegister;
    private JTextField txtName;
    private JTextField txtFreq;
    private JPanel listPane;
	private JScrollPane scrollPane;



    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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


    /**
	 * Create the frame.
	 */
    public AddPessoaUI(){
        setBackground(Color.WHITE);
        setResizable(false);
        setType(Type.UTILITY);
        setTitle("Adicionar Pessoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 770, 500);

        contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new GridLayout(10, 2));
		setContentPane(contentPane);

        titlePane = new JPanel();
		titlePane.setForeground(new Color(0, 0, 0));
		titlePane.setBackground(new Color(218, 165, 32));
		titlePane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
		titlePane.setBounds(0, 0, 1327, 120);
		titlePane.setLayout(null);
		

        listPane = new JPanel();
		listPane.setForeground(new Color(0, 0, 0));
		listPane.setBackground(new Color(240, 230, 140));
		listPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(218, 165, 32)));
		listPane.setPreferredSize(new Dimension(1310, 1500));
		listPane.setAutoscrolls(true);
		
		scrollPane = new JScrollPane(listPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(-1, 118, 1328, 628);

        lblRegister = new JLabel("N. do Registro:");
		lblRegister.setBounds(61, 236, 262, 48);
		lblRegister.setForeground(new Color(0, 0, 0));
		lblRegister.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 35));

        txtRegister = new JTextField();
		txtRegister.setBounds(333, 239, 340, 38);
		txtRegister.setFont(new Font("Papyrus", Font.BOLD, 20));
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
		lblName.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 35));

        txtName = new JTextField();
		txtName.setBounds(333, 239, 340, 38);
		txtName.setFont(new Font("Papyrus", Font.BOLD, 20));
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



        lblFreq = new JLabel("Frequência:");
		lblFreq.setBounds(61, 236, 262, 48);
		lblFreq.setForeground(new Color(0, 0, 0));
		lblFreq.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 35));

        txtFreq = new JTextField();
		txtFreq.setBounds(333, 239, 340, 38);
		txtFreq.setFont(new Font("Papyrus", Font.BOLD, 20));
		txtFreq.setBackground(new Color(211, 211, 211));
		txtFreq.setForeground(new Color(105, 105, 105));
		txtFreq.setText("Insira a frequência");
        txtFreq.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if(txtFreq.getText().equals("Insira a frequência")) {
	            	txtFreq.setText("");
	                repaint();
	                revalidate();
	            }           
	        }
	    });


        contentPane.add(lblRegister);
		contentPane.add(txtRegister);
        contentPane.add(lblName);
		contentPane.add(txtName);
        contentPane.add(lblFreq);
		contentPane.add(txtFreq);


    }


}
