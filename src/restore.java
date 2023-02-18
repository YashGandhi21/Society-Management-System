import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
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

public class restore extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					restore frame = new restore();
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
	JComboBox flat_no, bldg_name;
	String flatno, buildingname;
	public restore() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Select building name :");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cookie", Font.PLAIN, 28));
		label.setBounds(117, 174, 194, 31);
		contentPane.add(label);
		
		bldg_name = new JComboBox();
		bldg_name.setBackground(SystemColor.controlHighlight);
		bldg_name.setBorder(null);
		bldg_name.addItem("--Select--");
		bldg_name.setMaximumRowCount(15);
	    try
        {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
			Statement stmt = conn.createStatement();
			String sql = "select rbldg_name from recycle";
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
					String sql = "select rflat_no from recycle where rbldg_name = '"+buildingname+"'";
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
		bldg_name.setBounds(305, 174, 109, 31);
		contentPane.add(bldg_name);
		
		flat_no = new JComboBox();
		flat_no.setBackground(SystemColor.controlHighlight);
		flat_no.setBorder(null);
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
	    flat_no.setBounds(571, 174, 109, 30);
		contentPane.add(flat_no);
		
		JLabel label_1 = new JLabel();
		label_1.setForeground(Color.WHITE);
		label_1.setText("Enter flat no:");
		label_1.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_1.setBounds(453, 171, 122, 37);
		contentPane.add(label_1);
		
		
		JButton btnRestore = new JButton("Restore");
		btnRestore.setBackground(SystemColor.controlHighlight);
		btnRestore.setBorder(null);
		btnRestore.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnRestore.setBounds(473, 268, 132, 37);
		contentPane.add(btnRestore);
		btnRestore.addActionListener(new Restore());
		
		JButton button_1 = new JButton("Back");
		button_1.setBackground(SystemColor.controlHighlight);
		button_1.setBorder(null);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new deleterecord().setVisible(true);
			}
		});
		button_1.setFont(new Font("Cookie", Font.PLAIN, 28));
		button_1.setBounds(224, 268, 122, 37);
		contentPane.add(button_1);
		
		JLabel lblRecordRestorationMenu = new JLabel("Record Restoration Menu");
		lblRecordRestorationMenu.setForeground(Color.WHITE);
		lblRecordRestorationMenu.setFont(new Font("Cookie", Font.PLAIN, 45));
		lblRecordRestorationMenu.setBounds(243, 43, 413, 46);
		contentPane.add(lblRecordRestorationMenu);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel);
	}
	
	class Restore implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {	
	        try
	        {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
				 Statement stmt = conn.createStatement();
				 PreparedStatement ps;
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
					 String query = "select * from recycle where rbldg_name='"+buildingname+"' and rflat_no='"+flatno+"'";
					 ResultSet rs1 = stmt.executeQuery(query);
					 
					 if(rs1.next())
					 {
						 String tenantid = rs1.getString("rbldg_id");
						 String bldgname = rs1.getString("rbldg_name");
						 String ownerflatno = rs1.getString("rflat_no");
						 String tenanttype = rs1.getString("rtenant_type");
						 String tenantname = rs1.getString("rowner_name");
						 String flattype = rs1.getString("rflat_type");
						 String phno = rs1.getString("rphn");
						 String no_of_parking = rs1.getString("rnopark");
						 
						 String restorerecord = "call insertrecord(?,?,?,?,?,?,?,?)";
						 ps = conn.prepareStatement(restorerecord);
						 ps.setString(1, tenantid);
						 ps.setString(2, bldgname);
						 ps.setString(3, ownerflatno);
						 ps.setString(4, tenantname);
						 ps.setString(5, tenanttype);
						 ps.setString(6, flattype);
						 ps.setString(7, no_of_parking);
						 ps.setString(8, phno);
						 ps.executeUpdate();
						 
						 String clearrecycle = "delete from recycle where rbldg_id = '"+tenantid+"'";
						 PreparedStatement delete = conn.prepareStatement(clearrecycle);
						 delete.executeUpdate();
						 JOptionPane.showMessageDialog(null, "Record restored successfully!");
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(null, "Record not found!");
					 }
					 stmt.close();
					 conn.close();
				 }
				 
				 
	                 
	         }
	         catch (Exception e)
	         {
	             System.out.print(e.getMessage());
	         }
         }
    }
	
	protected void hideForm() {
		this.setVisible(false);
	}
}
