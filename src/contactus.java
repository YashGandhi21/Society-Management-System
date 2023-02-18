import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class contactus extends JFrame {

	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					contactus frame = new contactus();
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
	public contactus() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblraza = new JLabel("<html>Raza Pathan<br>9921724838<br>ewajaahat@gmail.com</html>");
		lblraza.setForeground(Color.BLACK);
		lblraza.setFont(new Font("Cookie", Font.PLAIN, 25));
		lblraza.setBounds(249, 45, 165, 87);
		contentPane.add(lblraza);
		
		JLabel lblswapnil = new JLabel("<html>Swapnil Karkhanis<br>8087422817<br>srk.karkhanis@gmail.com</html>");
		lblswapnil.setForeground(Color.BLACK);
		lblswapnil.setFont(new Font("Cookie", Font.PLAIN, 25));
		lblswapnil.setBounds(20, 45, 229, 87);
		contentPane.add(lblswapnil);
		
		JLabel lblyash = new JLabel("<html>Yash Gandhi<br>8983493019<br>ygandhi2325.yg@gmail.com</html>");
		lblyash.setForeground(Color.BLACK);
		lblyash.setFont(new Font("Cookie", Font.PLAIN, 25));
		lblyash.setBounds(20, 155, 212, 87);
		contentPane.add(lblyash);
		
		JLabel lblomkar = new JLabel("<html>Omkar Nalawade<br>8308866624<br>omkarvn51@gmail.com</html>");
		lblomkar.setForeground(Color.BLACK);
		lblomkar.setFont(new Font("Cookie", Font.PLAIN, 25));
		lblomkar.setBounds(249, 155, 229, 87);
		contentPane.add(lblomkar);
		
		JLabel lblDevelopers = new JLabel("Developers:");
		lblDevelopers.setForeground(Color.BLACK);
		lblDevelopers.setFont(new Font("Cookie", Font.PLAIN, 35));
		lblDevelopers.setBounds(20, 10, 139, 26);
		contentPane.add(lblDevelopers);
		
		
	}
}
