import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class aboutus extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aboutus frame = new aboutus();
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
	public aboutus() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("<html>Swapnil Karkhanis<br>Raza Pathan<br>Omkar Nalawade<br>Yash Gandhi<br></html>");
		lblNewLabel.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblNewLabel.setBounds(48, 67, 166, 164);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Developed by:");
		lblNewLabel_1.setFont(new Font("Cookie", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(23, 20, 179, 29);
		contentPane.add(lblNewLabel_1);
		
		
		
		
	}

}
