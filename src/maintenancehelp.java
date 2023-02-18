import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class maintenancehelp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maintenancehelp frame = new maintenancehelp();
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
	public maintenancehelp() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 852, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Calculation of maintenance was done on the basis of following factors:");
		lblNewLabel.setFont(new Font("Cookie", Font.PLAIN, 38));
		lblNewLabel.setBounds(25, 10, 805, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html><b>Base Maintenance :</b><br>1 BHK : Rs. 1000<br>2 BHK : Rs. 1200<br>3 BHK : Rs. 1400<br><b>Sinking Fund :</b><br> 25% of Base Maintenance<br><b>Parking :</b><br>Rs. 50 per parking slot owner by tenant<br><b>Non-occupancy :</b><br>Rs. 500 if tenant type is rental, no charge for owner<br><b>Fine :</b><br>No fine levied if payment is done on or before due date.<br>If payment date exceeds due date, the number of months passed since due date are taken into consideration with Rs. 50 charge applied for each month</html>");
		lblNewLabel_1.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(30, 66, 756, 438);
		contentPane.add(lblNewLabel_1);
	}

}
