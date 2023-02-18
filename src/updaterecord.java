import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
public class updaterecord extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldtenantid;
	private JTextField textFieldtenantname;
	private JTextField textFieldphno;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updaterecord frame = new updaterecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	String buildingname, flatno;
	public JComboBox tnttype,flttype,parkno;
	JComboBox flat_no, bldg_name;
	JRadioButton rdbtnMale, rdbtnFemale;
	
	public updaterecord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Select building name :");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cookie", Font.PLAIN, 28));
		label.setBounds(44, 25, 194, 31);
		contentPane.add(label);
		
		bldg_name = new JComboBox();
		bldg_name.setBorder(null);
		bldg_name.setBackground(SystemColor.controlHighlight);
		bldg_name.addItem("--Select--");
		bldg_name.setMaximumRowCount(15);
	    try
        {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
			Statement stmt = conn.createStatement();
			String sql = "select bldg_name from building";
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<String> list = new ArrayList<String>();
			while(rs.next())
			{
				list.add(rs.getString(1));
			}
			HashSet<String> hs = new HashSet<>();
			hs.addAll(list);
			list.clear();
			list.addAll(hs);
			String[] stringArray = list.toArray(new String[0]);
			Arrays.sort(stringArray);
			for(int i=0; i<stringArray.length; i++)
			{
				bldg_name.addItem(stringArray[i]);
			}
			rs.close();
			stmt.close();
			conn.close();    
         }
         catch (Exception e)
         {
             System.out.print(e.getMessage());
         }

		bldg_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildingname = (String) bldg_name.getSelectedItem();
				flat_no.removeAllItems();
				flat_no.addItem("--Select--");
				flat_no.setEnabled(true);
				try
			    {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
					Statement stmt = conn.createStatement();
					String sql = "select flat_no from building where bldg_name = '"+buildingname+"'";
					ResultSet rs = stmt.executeQuery(sql);
					ArrayList<String> list = new ArrayList<String>();
					while(rs.next())
					{
						list.add(rs.getString(1));
					}
					HashSet<String> hs = new HashSet<>();
					hs.addAll(list);
					list.clear();
					list.addAll(hs);
					String[] stringArray = list.toArray(new String[0]);
					Arrays.sort(stringArray);
					for(int i=0; i<stringArray.length; i++)
					{
						flat_no.addItem(stringArray[i]);
					}
					rs.close();
					stmt.close();
					conn.close();    
		        }
		        catch (Exception e1)
		        {
		            System.out.print(e1.getMessage());
		        }
			}
		});
		bldg_name.setEditable(true);
		bldg_name.setFont(new Font("Cookie", Font.PLAIN, 28));
		bldg_name.setBounds(232, 23, 109, 31);
		contentPane.add(bldg_name);
		
		flat_no = new JComboBox();
		flat_no.setBorder(null);
		flat_no.setBackground(SystemColor.controlHighlight);
		flat_no.setEditable(true);
		flat_no.setEnabled(false);
		flat_no.addItem("--Select--");
		flat_no.setMaximumRowCount(15);
	    flat_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flatno = (String) flat_no.getSelectedItem();
			}
		});
	    flat_no.setFont(new Font("Cookie", Font.PLAIN, 28));
	    flat_no.setBounds(523, 24, 109, 30);
		contentPane.add(flat_no);
		
		JLabel label_1 = new JLabel();
		label_1.setForeground(Color.WHITE);
		label_1.setText("Enter flat no:");
		label_1.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_1.setBounds(400, 22, 122, 37);
		contentPane.add(label_1);
		
		JButton buttonSearch = new JButton("Search");
		buttonSearch.setBorder(null);
		buttonSearch.setBackground(SystemColor.controlHighlight);
		buttonSearch.setFont(new Font("Cookie", Font.PLAIN, 28));
		buttonSearch.setBounds(679, 25, 109, 31);
		contentPane.add(buttonSearch);
		buttonSearch.addActionListener(new DisplayRecord());
		
		
		JLabel label_2 = new JLabel("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_2.setBounds(20, 84, 808, 13);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Details :");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Cookie", Font.PLAIN, 35));
		label_3.setBounds(369, 107, 109, 31);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Tenant ID :");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_4.setBounds(61, 163, 122, 24);
		contentPane.add(label_4);
		
		textFieldtenantid = new JTextField();
		textFieldtenantid.setEditable(false);
		textFieldtenantid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldtenantid.setColumns(10);
		textFieldtenantid.setBounds(203, 160, 122, 31);
		contentPane.add(textFieldtenantid);
		
		JLabel label_5 = new JLabel("Tenant type :");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_5.setBounds(515, 160, 115, 31);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Tenant name :");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_6.setBounds(61, 218, 126, 24);
		contentPane.add(label_6);
		
		textFieldtenantname = new JTextField();
		textFieldtenantname.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldtenantname.setColumns(10);
		textFieldtenantname.setBounds(203, 214, 286, 32);
		contentPane.add(textFieldtenantname);
		
		JLabel label_7 = new JLabel("Flat type :");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_7.setBounds(515, 215, 86, 31);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("Phone no :");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_8.setBounds(61, 276, 101, 24);
		contentPane.add(label_8);
		
		textFieldphno = new JTextField();
		textFieldphno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldphno.setColumns(10);
		textFieldphno.setBounds(203, 273, 169, 31);
		contentPane.add(textFieldphno);
		
		JLabel label_9 = new JLabel("No. of parking slots :");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_9.setBounds(515, 273, 175, 31);
		contentPane.add(label_9);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBorder(null);
		btnBack.setBackground(SystemColor.controlHighlight);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new adminloggedin().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnBack.setBounds(484, 403, 109, 37);
		contentPane.add(btnBack);
		 
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(SystemColor.controlHighlight);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnUpdate.setBounds(203, 403, 96, 37);
		contentPane.add(btnUpdate);
		
		String[] tenanttype = new String[] {"--Select--","Owner","Rental"};
		JComboBox comboBoxtenanttype = new JComboBox(tenanttype);
		comboBoxtenanttype.setBackground(SystemColor.controlHighlight);
		comboBoxtenanttype.setBorder(null);
		comboBoxtenanttype.setFont(new Font("Cookie", Font.PLAIN, 28));
		comboBoxtenanttype.setEditable(true);
		comboBoxtenanttype.setBounds(698, 160, 109, 31);
		contentPane.add(comboBoxtenanttype);
		tnttype = comboBoxtenanttype;
		
		String[] flattype = new String[] {"--Select--","1 BHK", "2 BHK", "3 BHK"};
		JComboBox comboBoxflattype = new JComboBox(flattype);
		comboBoxflattype.setEnabled(false);
		comboBoxflattype.setFont(new Font("Cookie", Font.PLAIN, 28));
		comboBoxflattype.setBounds(698, 214, 109, 32);
		contentPane.add(comboBoxflattype);
		flttype = comboBoxflattype;
		
		String[] noofparking = new String[] {"--Select--","0","1","2"};
		JComboBox comboBoxparking = new JComboBox(noofparking);
		comboBoxparking.setBackground(SystemColor.controlHighlight);
		comboBoxparking.setBorder(null);
		comboBoxparking.setFont(new Font("Cookie", Font.PLAIN, 28));
		comboBoxparking.setEditable(true);
		comboBoxparking.setBounds(698, 273, 109, 29);
		contentPane.add(comboBoxparking);
		btnUpdate.addActionListener(new UpdateRecord());
		parkno = comboBoxparking;
		
		JLabel label_10 = new JLabel("Select gender :");
		label_10.setForeground(Color.WHITE);
		label_10.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_10.setBounds(61, 324, 126, 24);
		contentPane.add(label_10);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setIconTextGap(8);
		rdbtnMale.setForeground(Color.WHITE);
		rdbtnMale.setFont(new Font("Cookie", Font.PLAIN, 28));
		rdbtnMale.setContentAreaFilled(false);
		rdbtnMale.setBorderPainted(false);
		rdbtnMale.setBackground(SystemColor.controlHighlight);
		rdbtnMale.setBounds(204, 326, 101, 21);
		contentPane.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setOpaque(false);
		rdbtnFemale.setIconTextGap(8);
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Cookie", Font.PLAIN, 28));
		rdbtnFemale.setBackground(SystemColor.controlHighlight);
		rdbtnFemale.setBounds(204, 356, 101, 21);
		contentPane.add(rdbtnFemale);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnMale);
		bg.add(rdbtnFemale);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel);
	}

	protected void hideForm() {
		this.setVisible(false);
	}
	
	class DisplayRecord implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {	
	        try
	        {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
				 Statement stmt = conn.createStatement();
				 if(bldg_name.getSelectedItem().equals("--Select--"))
				 {
					 JOptionPane.showMessageDialog(null, "Select building name");
				 }
				 else if(flat_no.getSelectedItem().equals("--Select--"))
				 {
					 JOptionPane.showMessageDialog(null, "Select flat number");
				 }
				 else
				 {
					 String query = "select * from building where bldg_name='"+buildingname+"' and flat_no='"+flat_no.getSelectedItem()+"'";
					 ResultSet rs = stmt.executeQuery(query);
					 
					 if(rs.next())
					 {
						 String tenantid = rs.getString("bldg_id");
						 String tenanttype = rs.getString("tenant_type");
						 String tenantname = rs.getString("owner_name");
						 String flattype = rs.getString("flat_type");
						 String phno = rs.getString("phn_no");
						 String no_of_parking = rs.getString("no_of_parkings");
						 String gender = rs.getString("gender");
						 
						 textFieldtenantid.setText(tenantid);
						 textFieldtenantname.setText(tenantname);
						 textFieldphno.setText(phno);
						 tnttype.setSelectedItem(tenanttype);
						 flttype.setSelectedItem(flattype);
						 parkno.setSelectedItem(no_of_parking);
						 if(gender.equals("Male"))
						 {
							 rdbtnMale.setSelected(true);
						 }
						 else
						 {
							 rdbtnFemale.setSelected(true);
						 }
						 
						 bldg_name.setEditable(false);
						 bldg_name.setEnabled(false);
						 flat_no.setEditable(false);
						 flat_no.setEnabled(false);
						 conn.close();
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(null, "Record does not exist!");
						 flat_no.setSelectedItem("--Select--");
					 }
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
	
	class UpdateRecord implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {	
	        try
	        {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
				 Statement stmt = conn.createStatement();
				 String tenantname = textFieldtenantname.getText();
				 String phno = textFieldphno.getText();
				 String b_id = bldg_name.getSelectedItem() + "-" + flat_no.getSelectedItem(); 
				 String sql = "select * from building where bldg_id = '"+b_id+"'";
				 ResultSet rs = stmt.executeQuery(sql);
				 String Gender = null;
				 if(rdbtnMale.isSelected())
				 {
					 Gender = rdbtnMale.getText();
				 }
				 else
				 {
					 Gender = rdbtnFemale.getText();
				 }
				 if(rs.next())
				 {
					 if(tenantname.isEmpty())
					 {
						 JOptionPane.showMessageDialog(null, "Tenant name cannot be empty!");						 
					 }
					 if(phno.isEmpty())
					 {
						 JOptionPane.showMessageDialog(null, "Phone number cannot be empty!");
					 }
					 else if(phno.length() != 10)
					 {
						 JOptionPane.showMessageDialog(null, "Incorrect phone number!");
						 textFieldphno.setText("");
					 }
					 else
					 {
						 String query = "update building set owner_name = '"+tenantname+"', tenant_type = '"+tnttype.getSelectedItem()+"', flat_type = '"+flttype.getSelectedItem()+"', no_of_parkings = '"+parkno.getSelectedItem()+"', phn_no = '"+phno+"', gender = '"+Gender+"' where bldg_id = '"+textFieldtenantid.getText()+"'";
						 PreparedStatement ps = conn.prepareStatement(query);
						 ps.executeUpdate();
						 JOptionPane.showMessageDialog(null, "Record updated successfully!");
						 conn.close();
						 hideForm();
						 new adminloggedin().setVisible(true);
					 }
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "Record does not exist!");
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
