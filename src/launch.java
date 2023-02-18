import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class launch extends JFrame {

	private JPanel contentPane;
	
		
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					launch frame = new launch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public launch() {
		setTitle("Society Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBorder(null);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new login().setVisible(true);
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Cookie", Font.BOLD, 35));
		btnLogin.setBounds(677, 67, 151, 58);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Society Management System");
		lblNewLabel_1.setForeground(SystemColor.controlHighlight);
		lblNewLabel_1.setFont(new Font("Cookie", lblNewLabel_1.getFont().getStyle() | Font.BOLD, 60));
		lblNewLabel_1.setBounds(79, 30, 624, 121);
		contentPane.add(lblNewLabel_1);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(SystemColor.controlHighlight);
		menubar.setLayout(null);
		menubar.setBounds(0, 0, 850, 30);
		contentPane.add(menubar);
		
		JButton AbtUs = new JButton("About Us");
		AbtUs.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new aboutus().setVisible(true);
			}
		});
		AbtUs.setBorder(null);
		AbtUs.setBackground(SystemColor.controlHighlight);
		AbtUs.setFont(new Font("Cookie", Font.PLAIN, 20));
		AbtUs.setBounds(0,0,100,30);
		menubar.add(AbtUs);
		
		JButton Contact = new JButton("Contact Us");
		Contact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new contactus().setVisible(true);
			}
		});
		Contact.setBorder(null);
		Contact.setBackground(SystemColor.controlHighlight);
		Contact.setFont(new Font("Cookie", Font.PLAIN, 20));
		Contact.setBounds(100,0,100,30);
		menubar.add(Contact);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\background.jpg"));
		lblNewLabel.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel);
	
	}

	
	private void hideForm() {
		setVisible(false);
	}
}
