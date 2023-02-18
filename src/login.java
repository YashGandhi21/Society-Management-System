import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textFielduser;
	private JPasswordField textFieldpass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 510, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administrator Login Page");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cookie", Font.BOLD, 35));
		lblNewLabel.setBounds(78, 49, 382, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblOldUser = new JLabel("Username :");
		lblOldUser.setForeground(Color.WHITE);
		lblOldUser.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblOldUser.setBounds(109, 124, 138, 38);
		contentPane.add(lblOldUser);
		
		textFielduser = new JTextField();
		textFielduser.setFont(new Font("Cookie", Font.PLAIN, 25));
		textFielduser.setBounds(229, 131, 157, 32);
		contentPane.add(textFielduser);
		textFielduser.setColumns(4);
		
		JLabel lblOldPass = new JLabel("Password :");
		lblOldPass.setForeground(Color.WHITE);
		lblOldPass.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblOldPass.setBounds(109, 198, 138, 38);
		contentPane.add(lblOldPass);
		
		textFieldpass = new JPasswordField();
		textFieldpass.setFont(new Font("Cookie", Font.PLAIN, 25));
		textFieldpass.setColumns(4);
		textFieldpass.setBounds(229, 204, 157, 32);
		contentPane.add(textFieldpass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(SystemColor.controlHighlight);
		btnLogin.setFont(new Font("Cookie", Font.PLAIN, 25));
		btnLogin.setBounds(114, 266, 107, 38);
		contentPane.add(btnLogin);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.controlHighlight);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new launch().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Cookie", Font.PLAIN, 25));
		btnBack.setBounds(279, 266, 107, 38);
		contentPane.add(btnBack);
		btnLogin.addActionListener(new MyLogin());
		
		JLabel lblNewLabel1 = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel1.setBounds(0, 0, 496, 383);
		contentPane.add(lblNewLabel1);
	}
	
	
	class MyLogin implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {	
	        try
	        {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
				 Statement stmt = conn.createStatement();
				 String user = textFielduser.getText();
				 String pass = textFieldpass.getText();
				 String sql = "call validate('"+user+"','"+pass+"')";
				 
				 ResultSet rs = stmt.executeQuery(sql);
				 if(user.isEmpty())
				 {
					 JOptionPane.showMessageDialog(null, "Username cannot be empty");
				 }
				 else if(pass.isEmpty())
				 {
					 JOptionPane.showMessageDialog(null, "Password cannot be empty");
				 }
				 else
				 {
					 if (rs.next())
					 {
						 JOptionPane.showMessageDialog(null, "Login successful!");
						 hideForm();
						 new adminloggedin().setVisible(true);
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(null, "Login failed! Account does not exist");
					 }
				 }
				 rs.close();
				 stmt.close();
				 conn.close();
	                 
	         }
	         catch (Exception e)
	         {
	             System.out.print(e.getMessage());
	         }
         }
    }


	public void hideForm() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
