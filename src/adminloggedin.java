import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class adminloggedin extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminloggedin frame = new adminloggedin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public adminloggedin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(SystemColor.controlHighlight);
		menubar.setLayout(null);
		menubar.setBounds(0, 0, 850, 30);
		contentPane.add(menubar);
		
		JLabel lblWelcomeAdmin = new JLabel("Administrator Home Page");
		lblWelcomeAdmin.setForeground(Color.WHITE);
		lblWelcomeAdmin.setFont(new Font("Cookie", Font.BOLD, 45));
		lblWelcomeAdmin.setBounds(41, 44, 448, 68);
		contentPane.add(lblWelcomeAdmin);
		
		JButton btnNewButton = new JButton("Insert record");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new insertrecord().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnNewButton.setBounds(85, 157, 184, 57);
		contentPane.add(btnNewButton);
		
		JButton btnViewRecords = new JButton("Search record");
		btnViewRecords.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnViewRecords.setBackground(SystemColor.controlHighlight);
		btnViewRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new displayrecord().setVisible(true);
			}
		});
		btnViewRecords.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnViewRecords.setBounds(558, 157, 184, 57);
		contentPane.add(btnViewRecords);
		
		JButton btnMaintenance = new JButton("Maintenance");
		btnMaintenance.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnMaintenance.setBackground(SystemColor.controlHighlight);
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new maintenancegenerator().setVisible(true);
			}
		});
		btnMaintenance.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnMaintenance.setBounds(319, 257, 184, 57);
		contentPane.add(btnMaintenance);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBorder(null);
		btnLogout.setBackground(SystemColor.controlHighlight);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				launch logpage = new launch();
				logpage.setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Cookie", Font.PLAIN, 22));
		btnLogout.setBounds(700, 0, 150, 30);
		menubar.add(btnLogout);
		
		JButton btnCreateAdmin = new JButton("Create admin");
		btnCreateAdmin.setBorder(null);
		btnCreateAdmin.setBackground(SystemColor.controlHighlight);
		btnCreateAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new createadmin().setVisible(true);
			}
		});
		btnCreateAdmin.setFont(new Font("Cookie", Font.PLAIN, 22));
		btnCreateAdmin.setBounds(550, 0, 150, 30);
		menubar.add(btnCreateAdmin);
		
		JButton btnNewButton_1 = new JButton("Update record");
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNewButton_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new updaterecord().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnNewButton_1.setBounds(85, 257, 184, 57);
		contentPane.add(btnNewButton_1);
		
		JButton btnDeleteRecords = new JButton("Delete record");
		btnDeleteRecords.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnDeleteRecords.setBackground(SystemColor.controlHighlight);
		btnDeleteRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new deleterecord().setVisible(true);
			}
		});
		btnDeleteRecords.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnDeleteRecords.setBounds(558, 257, 184, 57);
		contentPane.add(btnDeleteRecords);
		
		JButton btnNewButton_2 = new JButton("Register complaint");
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNewButton_2.setBackground(SystemColor.controlHighlight);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new registercomplaint().setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnNewButton_2.setBounds(85, 355, 184, 57);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Display complaint");
		btnNewButton_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNewButton_3.setBackground(SystemColor.controlHighlight);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new displaycomplaint().setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnNewButton_3.setBounds(558, 355, 184, 57);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel);
	}

	protected void hideForm() {
		this.setVisible(false);
	}
}
