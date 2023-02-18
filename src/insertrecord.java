import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
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
import java.util.HashSet;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JRadioButton;

public class insertrecord extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldflatno;
	private JTextField textFieldtenantname;
	private JTextField textFieldphno;
	
	public String buildingname,  parking;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insertrecord frame = new insertrecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	String tenantType, flatType;
	public String getTenantType() {
	    return tenantType;
	}
	public void setTenantType(String nwt) {
	    tenantType = nwt;
	}
	
	public String getFlatType() {
	    return tenantType;
	}
	public void setFlatType(String nwt) {
	    flatType = nwt;
	}
	JComboBox bldg_name, tenant_type, flat_type, no_of_parking;
	JButton btnEnterRecord;
	private JLabel lblSelectGender;
	JRadioButton rdbtnMale,rdbtnFemale;
	public insertrecord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuildingName = new JLabel("Building name :");
		lblBuildingName.setForeground(Color.WHITE);
		lblBuildingName.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblBuildingName.setBounds(44, 46, 157, 32);
		contentPane.add(lblBuildingName);
		
		String[] bldgname = new String[] {"--Select--","A1","A2","A3","A4"};
		bldg_name = new JComboBox(bldgname);
		bldg_name.setBackground(SystemColor.controlHighlight);
		bldg_name.setMaximumRowCount(15);
		bldg_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildingname = (String) bldg_name.getSelectedItem();
			}
		});
		bldg_name.setEditable(true);
		bldg_name.setFont(new Font("Cookie", Font.PLAIN, 28));
		bldg_name.setBounds(182, 47, 109, 31);
		contentPane.add(bldg_name);
		
		
		JLabel lblFlatNo = new JLabel("Flat no :");
		lblFlatNo.setForeground(Color.WHITE);
		lblFlatNo.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblFlatNo.setBounds(509, 53, 86, 19);
		contentPane.add(lblFlatNo);
		
		textFieldflatno = new JTextField();
		textFieldflatno.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldflatno.setBounds(589, 49, 104, 29);
		contentPane.add(textFieldflatno);
		textFieldflatno.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tenant name :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblNewLabel.setBounds(44, 120, 126, 24);
		contentPane.add(lblNewLabel);
		
		textFieldtenantname = new JTextField();
		textFieldtenantname.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldtenantname.setBounds(182, 116, 286, 32);
		contentPane.add(textFieldtenantname);
		textFieldtenantname.setColumns(10);
		
		JLabel lblTenantType = new JLabel("Tenant type :");
		lblTenantType.setForeground(Color.WHITE);
		lblTenantType.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblTenantType.setBounds(44, 184, 115, 24);
		contentPane.add(lblTenantType);
		
		String[] tenanttype = new String[] {"--Select--","Owner","Rental"};
		tenant_type = new JComboBox(tenanttype);
		tenant_type.setBackground(SystemColor.controlHighlight);
		tenant_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenant = (String) tenant_type.getSelectedItem();
				setTenantType(tenant);
			}
		});
		tenant_type.setFont(new Font("Cookie", Font.PLAIN, 28));
		tenant_type.setBounds(182, 180, 115, 32);
		contentPane.add(tenant_type);
		tenant_type.setEditable(true);
		
		
		JLabel lblFlatType = new JLabel("Flat type :");
		lblFlatType.setForeground(Color.WHITE);
		lblFlatType.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblFlatType.setBounds(509, 124, 86, 20);
		contentPane.add(lblFlatType);
		
		String[] flattype = new String[] {"--Select--","1 BHK", "2 BHK", "3 BHK"};
		flat_type = new JComboBox(flattype);
		flat_type.setBackground(SystemColor.controlHighlight);
		flat_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String flat = (String) flat_type.getSelectedItem();
				setFlatType(flat);
			}
		});
		flat_type.setFont(new Font("Cookie", Font.PLAIN, 28));
		flat_type.setBounds(617, 117, 115, 32);
		contentPane.add(flat_type);
		flat_type.setEditable(true);
		
		
		JLabel lblPhoneNo = new JLabel("Phone no :");
		lblPhoneNo.setForeground(Color.WHITE);
		lblPhoneNo.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblPhoneNo.setBounds(44, 255, 104, 24);
		contentPane.add(lblPhoneNo);
		
		String[] noofparking = new String[] {"--Select--","0","1","2"};
		no_of_parking = new JComboBox(noofparking);
		no_of_parking.setBackground(SystemColor.controlHighlight);
		no_of_parking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parking = (String) no_of_parking.getSelectedItem();
			}
		});
		no_of_parking.setEditable(true);
		no_of_parking.setFont(new Font("Cookie", Font.PLAIN, 28));
		no_of_parking.setBounds(692, 190, 109, 27);
		contentPane.add(no_of_parking);
		
		
		JLabel lblNoOfParking = new JLabel("No. of parking slots :");
		lblNoOfParking.setForeground(Color.WHITE);
		lblNoOfParking.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblNoOfParking.setBounds(509, 191, 175, 24);
		contentPane.add(lblNoOfParking);
		
		textFieldphno = new JTextField();
		textFieldphno.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldphno.setBounds(182, 253, 169, 28);
		contentPane.add(textFieldphno);
		textFieldphno.setColumns(10);
		
		btnEnterRecord = new JButton("Enter record");
		btnEnterRecord.setBorder(null);
		btnEnterRecord.setBackground(SystemColor.controlHighlight);
		btnEnterRecord.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnEnterRecord.setBounds(118, 379, 135, 42);
		contentPane.add(btnEnterRecord);
		btnEnterRecord.addActionListener(new InsertRecord());
		
		JButton btnCancel = new JButton("Back");
		btnCancel.setBorder(null);
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new adminloggedin().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnCancel.setBounds(577, 379, 116, 42);
		contentPane.add(btnCancel);
		
		lblSelectGender = new JLabel("Select gender :");
		lblSelectGender.setForeground(Color.WHITE);
		lblSelectGender.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblSelectGender.setBounds(509, 255, 126, 24);
		contentPane.add(lblSelectGender);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBackground(SystemColor.controlHighlight);
		rdbtnMale.setIconTextGap(8);
		rdbtnMale.setForeground(Color.WHITE);
		rdbtnMale.setFont(new Font("Cookie", Font.PLAIN, 28));
		rdbtnMale.setBounds(650, 260, 101, 21);
		//rdbtnMale.setOpaque(false);
		rdbtnMale.setContentAreaFilled(false);
		rdbtnMale.setBorderPainted(false);
		contentPane.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBackground(SystemColor.controlHighlight);
		rdbtnFemale.setOpaque(false);
		rdbtnFemale.setIconTextGap(8);
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Cookie", Font.PLAIN, 28));
		rdbtnFemale.setBounds(650, 290, 101, 21);
		contentPane.add(rdbtnFemale);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnMale);
		bg.add(rdbtnFemale);
		
		JLabel lblNewLabel1 = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel1.setBackground(SystemColor.controlHighlight);
		lblNewLabel1.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel1);
	}

	protected void hideForm() {
		this.setVisible(false);
	}
	
	class InsertRecord implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {	
	        try
	        {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
				 Statement stmt1 = conn.createStatement();
				 String flatno = textFieldflatno.getText();
				 String tenant_name = textFieldtenantname.getText();
				 String phno = textFieldphno.getText();
				 String buildingid = buildingname + "-" + flatno;
				 String gender = null;
				 if(rdbtnMale.isSelected())
				 {
					 gender = rdbtnMale.getText();
				 }
				 else if(rdbtnFemale.isSelected())
				 {
					 gender = rdbtnFemale.getText();
				 }
				 
				 PreparedStatement ps;
				 
				 if(bldg_name.getSelectedItem().equals("--Select--"))
				 {
					 JOptionPane.showMessageDialog(null, "Select building name");
				 }
				 else if(flatno.isEmpty())
				 {
					 JOptionPane.showMessageDialog(null, "Flat number cannot be empty!");
				 }
				 else if(tenant_name.isEmpty())
				 {
					 JOptionPane.showMessageDialog(null, "Tenant name cannot be empty!");
				 }
				 else if(flat_type.getSelectedItem().equals("--Select--"))
				 {
					 JOptionPane.showMessageDialog(null, "Select flat type");
				 }
				 else if(tenant_type.getSelectedItem().equals("--Select--"))
				 {
					 JOptionPane.showMessageDialog(null, "Select tenant type");
				 }
				 
				 else if(no_of_parking.getSelectedItem().equals("--Select--"))
				 {
					 JOptionPane.showMessageDialog(null, "Select no. of parking slots");
				 }
				 else if(phno.isEmpty())
				 {
					 JOptionPane.showMessageDialog(null, "Phone number cannot be empty!");
				 }
				 else if(!rdbtnMale.isSelected() && !rdbtnFemale.isSelected())
				 {
					 JOptionPane.showMessageDialog(null, "Select tenant's gender!");
				 }
				 else if(!flatno.isEmpty() && !tenant_name.isEmpty() && !phno.isEmpty())
				 {
					 String sql1 = "select * from building where bldg_name = '"+buildingname+"' and flat_no = '"+flatno+"'";
					 ResultSet rs = stmt1.executeQuery(sql1);
					 if(rs.next())
					 {
						 JOptionPane.showMessageDialog(null, "Record already exists!");
						 textFieldflatno.setText("");
						 textFieldtenantname.setText("");
						 textFieldphno.setText("");
					 }
					 else if(phno.length() != 10)
					 {
						 JOptionPane.showMessageDialog(null, "Phone number incorrect!");
						 textFieldphno.setText("");
					 }
					 else
					 {
						 String query = "call insertrecord(?,?,?,?,?,?,?,?,?)";
						 ps  = conn.prepareStatement(query);
						 ps.setString(1, buildingid);
						 ps.setString(2, buildingname);
						 ps.setString(3, flatno);
						 ps.setString(4, tenant_name);
						 ps.setString(5, tenantType);
						 ps.setString(6, flatType);
						 ps.setString(7, parking);
						 ps.setString(8, phno);
						 ps.setString(9, gender);
						 ps.executeUpdate();
						 JOptionPane.showMessageDialog(null, "Record inserted successfully!");
						 conn.close();
						 hideForm();
						 new adminloggedin().setVisible(true);
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
}
