import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.SystemColor;

public class createadmin extends JFrame {

	private JPanel contentPane;
	private JTextField textFielduser;
	private JPasswordField textFieldpass;
	private JPasswordField textFieldrepass;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createadmin frame = new createadmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public createadmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateNewAdmin = new JLabel("Create new admin account :");
		lblCreateNewAdmin.setForeground(Color.WHITE);
		lblCreateNewAdmin.setFont(new Font("Cookie", Font.BOLD, 40));
		lblCreateNewAdmin.setBounds(157, 37, 411, 46);
		contentPane.add(lblCreateNewAdmin);
		
		JLabel lblNewLabel = new JLabel("Enter username :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblNewLabel.setBounds(87, 147, 136, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterPassword = new JLabel("Enter password :");
		lblEnterPassword.setForeground(Color.WHITE);
		lblEnterPassword.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblEnterPassword.setBounds(87, 211, 135, 32);
		contentPane.add(lblEnterPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-enter password :");
		lblReenterPassword.setForeground(Color.WHITE);
		lblReenterPassword.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblReenterPassword.setBounds(87, 268, 163, 32);
		contentPane.add(lblReenterPassword);
		
		textFielduser = new JTextField();
		textFielduser.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFielduser.setBounds(272, 147, 161, 38);
		contentPane.add(textFielduser);
		textFielduser.setColumns(10);
		
		textFieldpass = new JPasswordField();
		textFieldpass.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldpass.setColumns(10);
		textFieldpass.setBounds(272, 207, 161, 38);
		contentPane.add(textFieldpass);
		
		textFieldrepass = new JPasswordField();
		textFieldrepass.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldrepass.setColumns(10);
		textFieldrepass.setBounds(272, 268, 161, 37);
		contentPane.add(textFieldrepass);
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.setBackground(SystemColor.controlHighlight);
		btnCreateAccount.setBorder(null);
		btnCreateAccount.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnCreateAccount.setBounds(95, 367, 189, 46);
		contentPane.add(btnCreateAccount);
		
		JButton btnCancel = new JButton("Back");
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setBorder(null);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new adminloggedin().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnCancel.setBounds(32, 40, 82, 34);
		contentPane.add(btnCancel);
		
		btnCreateAccount.addActionListener(new CreateAdmin());
		
		JLabel lblNewLabel1 = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel1.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel1);
	}

	protected void hideForm() {
		this.setVisible(false);
	}

	class CreateAdmin implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {	
	        try
	        {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
				 Statement stmt1 = conn.createStatement();
				 Statement stmt2 = conn.createStatement();
				 String user = textFielduser.getText();
				 String pass = textFieldpass.getText();
				 String repass = textFieldrepass.getText();
				 PreparedStatement ps;
				 if(user.isEmpty())
				 {
					 JOptionPane.showMessageDialog(null, "Username cannot be empty!");
				 }
				 if(pass.isEmpty())
				 {
					 JOptionPane.showMessageDialog(null, "Password cannot be empty!");
				 }
				 if(repass.isEmpty())
				 {
					 JOptionPane.showMessageDialog(null, "Please re-enter password!");
				 }
				 else if( !pass.isEmpty() && !repass.isEmpty() && pass.equals(repass))
				 {
					 String sql1 = "select * from login where user='"+user+"'";
					 ResultSet result1 = stmt1.executeQuery(sql1);
					 String sql2 = "select * from login where password='"+pass+"'";
					 ResultSet result2 = stmt2.executeQuery(sql2);
					 if(result1.next())
					 {
						 JOptionPane.showMessageDialog(null, "Username already exists");
						 textFielduser.setText("");
						 textFieldpass.setText("");
						 textFieldrepass.setText("");
					 }
					 else if(result2.next())
					 {
						 JOptionPane.showMessageDialog(null, "Password already exists");
						 textFielduser.setText("");
						 textFieldpass.setText("");
						 textFieldrepass.setText("");
					 }
					 else
					 {
						 String query = "call createadmin(?,?)";
						 ps  = conn.prepareStatement(query);
						 ps.setString(1, user);
						 ps.setString(2, pass);
						 ps.executeUpdate();
						 JOptionPane.showMessageDialog(null, "New admin account created!");
						 conn.close();
						 hideForm();
						 new adminloggedin().setVisible(true);
					 }
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "Passwords do not match");
					 textFieldpass.setText("");
					 textFieldrepass.setText("");
				 }    
	         }
	         catch (Exception e)
	         {
	             System.out.print(e.getMessage());
	         }
         }

		public void hideForm() {
			setVisible(false);
		}
    }
}
