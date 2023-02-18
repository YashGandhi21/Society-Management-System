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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;

public class deleterecord extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleterecord frame = new deleterecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	String buildingname, flatno;
	JComboBox bldg_name, flat_no;
	
	
	public deleterecord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuildingName = new JLabel("Select building name :");
		lblBuildingName.setForeground(Color.WHITE);
		lblBuildingName.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblBuildingName.setBounds(141, 212, 194, 31);
		contentPane.add(lblBuildingName);
		
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
		bldg_name.setBounds(330, 212, 109, 31);
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
	    flat_no.setBounds(596, 212, 109, 30);
		contentPane.add(flat_no);
		
		JLabel lblFlatNo = new JLabel();
		lblFlatNo.setForeground(Color.WHITE);
		lblFlatNo.setText("Enter flat no:");
		lblFlatNo.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblFlatNo.setBounds(477, 209, 122, 37);
		contentPane.add(lblFlatNo);
		
		JButton buttonDelete = new JButton("Delete");
		buttonDelete.setBorder(null);
		buttonDelete.setBackground(SystemColor.controlHighlight);
		buttonDelete.setFont(new Font("Cookie", Font.PLAIN, 28));
		buttonDelete.setBounds(156, 295, 122, 37);
		contentPane.add(buttonDelete);
		buttonDelete.addActionListener(new DeleteRecord());
		
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
		btnBack.setBounds(343, 296, 122, 37);
		contentPane.add(btnBack);
		
		JButton btnNewButton = new JButton("Undo");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new restore().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnNewButton.setBounds(539, 295, 122, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblRecordUpdationMenu = new JLabel("Record Deletion Menu");
		lblRecordUpdationMenu.setForeground(Color.WHITE);
		lblRecordUpdationMenu.setFont(new Font("Cookie", Font.BOLD, 50));
		lblRecordUpdationMenu.setBounds(199, 54, 410, 56);
		contentPane.add(lblRecordUpdationMenu);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel);
	}

	protected void hideForm() {
		this.setVisible(false);
	}
	
	class DeleteRecord implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {	
	        try
	        {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
				 Statement stmt1 = conn.createStatement();
				 Statement stmt2 = conn.createStatement();
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
					 String b_id = bldg_name.getSelectedItem() + "-" + flat_no.getSelectedItem();
					 String sql = "select * from building where bldg_id = '"+b_id+"'";
					 ResultSet rs = stmt1.executeQuery(sql);
					 if(rs.next())
					 {
						 String query = "delete from building where bldg_id = '"+b_id+"'";
						 PreparedStatement ps = conn.prepareStatement(query);
						 ps.executeUpdate();
						 JOptionPane.showMessageDialog(null, "Record deleted successfully!");
						 conn.close();
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(null, "Record does not exist!");
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
