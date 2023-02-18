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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;

public class displayrecord extends JFrame {

	private JPanel contentPane;
	private JLabel lblflat_no;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					displayrecord frame = new displayrecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	String buildingname, flatno;
	private JTextField textFieldtenantname;
	private JTextField textFieldtenantid;
	private JTextField textFieldphno;
	private JTextField textFieldtenanttype;
	private JTextField textFieldflattype;
	private JTextField textFieldparking;
	JComboBox flat_no, bldg_name;
	private JTextField textFieldgender;
	
	public displayrecord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterBuildingName = new JLabel("Select building name :");
		lblEnterBuildingName.setForeground(Color.WHITE);
		lblEnterBuildingName.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblEnterBuildingName.setBounds(44, 23, 194, 31);
		contentPane.add(lblEnterBuildingName);
		
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
	    flat_no.setBounds(523, 24, 109, 29);
		contentPane.add(flat_no);
		
		lblflat_no = new JLabel();
		lblflat_no.setForeground(Color.WHITE);
		lblflat_no.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblflat_no.setText("Enter flat no:");
		lblflat_no.setBounds(400, 20, 122, 37);
		contentPane.add(lblflat_no);
		
		JLabel label = new JLabel("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 10));
		label.setBounds(20, 82, 808, 13);
		contentPane.add(label);
		
		JLabel lblDetails = new JLabel("Details :");
		lblDetails.setForeground(Color.WHITE);
		lblDetails.setFont(new Font("Cookie", Font.PLAIN, 35));
		lblDetails.setBounds(349, 98, 109, 31);
		contentPane.add(lblDetails);
		
		JLabel lbltenantname = new JLabel("Tenant name :");
		lbltenantname.setForeground(Color.WHITE);
		lbltenantname.setFont(new Font("Cookie", Font.PLAIN, 28));
		lbltenantname.setBounds(44, 208, 126, 24);
		contentPane.add(lbltenantname);
		
		textFieldtenantname = new JTextField();
		textFieldtenantname.setEditable(false);
		textFieldtenantname.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldtenantname.setColumns(10);
		textFieldtenantname.setBounds(184, 204, 286, 32);
		contentPane.add(textFieldtenantname);
		
		JLabel label_2 = new JLabel("Tenant type :");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_2.setBounds(504, 139, 115, 31);
		contentPane.add(label_2);
		
		JLabel lblTenantId = new JLabel("Tenant ID :");
		lblTenantId.setForeground(Color.WHITE);
		lblTenantId.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblTenantId.setBounds(44, 143, 122, 24);
		contentPane.add(lblTenantId);
		
		textFieldtenantid = new JTextField();
		textFieldtenantid.setEditable(false);
		textFieldtenantid.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldtenantid.setBounds(184, 139, 122, 31);
		contentPane.add(textFieldtenantid);
		textFieldtenantid.setColumns(10);
		
		JLabel label_3 = new JLabel("Phone no :");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_3.setBounds(44, 281, 100, 24);
		contentPane.add(label_3);
		
		textFieldphno = new JTextField();
		textFieldphno.setEditable(false);
		textFieldphno.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldphno.setColumns(10);
		textFieldphno.setBounds(184, 277, 169, 31);
		contentPane.add(textFieldphno);
		
		JLabel label_4 = new JLabel("Flat type :");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_4.setBounds(504, 204, 86, 32);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("No. of parking slots :");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_5.setBounds(504, 275, 175, 37);
		contentPane.add(label_5);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBorder(null);
		btnSearch.setBackground(SystemColor.controlHighlight);
		btnSearch.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnSearch.setBounds(679, 23, 109, 31);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new DisplayRecord());
		
		
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
		btnCancel.setBounds(349, 381, 109, 37);
		contentPane.add(btnCancel);
		
		textFieldtenanttype = new JTextField();
		textFieldtenanttype.setEditable(false);
		textFieldtenanttype.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldtenanttype.setBounds(690, 138, 108, 28);
		contentPane.add(textFieldtenanttype);
		textFieldtenanttype.setColumns(10);
		
		textFieldflattype = new JTextField();
		textFieldflattype.setEditable(false);
		textFieldflattype.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldflattype.setBounds(691, 203, 107, 32);
		contentPane.add(textFieldflattype);
		textFieldflattype.setColumns(10);
		
		textFieldparking = new JTextField();
		textFieldparking.setEditable(false);
		textFieldparking.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldparking.setBounds(691, 276, 107, 29);
		contentPane.add(textFieldparking);
		textFieldparking.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblGender.setBounds(44, 340, 75, 20);
		contentPane.add(lblGender);
		
		textFieldgender = new JTextField();
		textFieldgender.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldgender.setEditable(false);
		textFieldgender.setColumns(10);
		textFieldgender.setBounds(185, 334, 107, 32);
		contentPane.add(textFieldgender);
		
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
						 textFieldtenanttype.setText(tenanttype);
						 textFieldflattype.setText(flattype);
						 textFieldparking.setText(no_of_parking);
						 textFieldgender.setText(gender);
						 
						 conn.close();
						 stmt.close();
						 rs.close();
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(null, "Record not found");
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
