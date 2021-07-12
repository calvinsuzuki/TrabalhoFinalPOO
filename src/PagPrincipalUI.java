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
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagPrincipalUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel listPane;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagPrincipalUI frame = new PagPrincipalUI();
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
	public PagPrincipalUI() {
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
		
		contentPane.add(titlePane);
		contentPane.add(scrollPane);
	}
}
