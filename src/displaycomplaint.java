import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JCheckBox;

public class displaycomplaint extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					displaycomplaint frame = new displaycomplaint();
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
	String buildingname, flatno, complaintid;
	JTextArea textAreacomplaint;
	JComboBox bldg_name, flat_no, comboBoxcid;
	private JLabel label;
	private JLabel label_1;
	private JButton btnUpdateStatus;
	JCheckBox chckbxResolved;
	public displaycomplaint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				chckbxResolved.setEnabled(true);
				btnUpdateStatus.setEnabled(true);
				chckbxResolved.setSelected(false);
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
		bldg_name.setBounds(254, 111, 109, 31);
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
				comboBoxcid.setEnabled(true);
				comboBoxcid.removeAllItems();
				chckbxResolved.setEnabled(true);
				btnUpdateStatus.setEnabled(true);
				chckbxResolved.setSelected(false);
				comboBoxcid.addItem("--Select--");
				comboBoxcid.setEnabled(true);
				try
			    {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
					Statement stmt = conn.createStatement();
					String buildingid = buildingname + "-" + flatno;
					String sql = "select c_id from complaints where bldg_id = '"+buildingid+"'";
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
						comboBoxcid.addItem(stringArray[i]);
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
	    flat_no.setFont(new Font("Cookie", Font.PLAIN, 28));
	    flat_no.setBounds(563, 111, 109, 30);
		contentPane.add(flat_no);
		
		JLabel lblSelectComplaintid = new JLabel("Select Complaint-ID :");
		lblSelectComplaintid.setForeground(Color.WHITE);
		lblSelectComplaintid.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblSelectComplaintid.setBounds(52, 156, 194, 39);
		contentPane.add(lblSelectComplaintid);
		
		comboBoxcid = new JComboBox();
		comboBoxcid.setBorder(null);
		comboBoxcid.setBackground(SystemColor.controlHighlight);
		comboBoxcid.setEditable(true);
		comboBoxcid.setEnabled(false);
		comboBoxcid.addItem("--Select--");
		comboBoxcid.setMaximumRowCount(15);
		comboBoxcid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complaintid = (String) comboBoxcid.getSelectedItem();
				chckbxResolved.setEnabled(true);
				btnUpdateStatus.setEnabled(true);
				chckbxResolved.setSelected(false);
				try
		        {
		        	 Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
					 Statement stmt = conn.createStatement();
					 String buildingid = buildingname + "-" + flatno;
					 String stat = "Unresolved";
					 String query = "select * from complaints where bldg_id='"+buildingid+"' and c_id='"+complaintid+"'";
					 ResultSet rs = stmt.executeQuery(query);
					 
					 if(rs.next())
					 {
						 textAreacomplaint.setText(rs.getString("complaint"));
						 if(rs.getString("status").equals("Unresolved"))
						 {
							 chckbxResolved.setSelected(false);
						 }
						 else
						 {
							 chckbxResolved.setSelected(true);
							 chckbxResolved.setEnabled(false);
							 btnUpdateStatus.setEnabled(false);
						 }
						 conn.close();
						 stmt.close();
						 rs.close();
					 }
		         }
		         catch (Exception e1)
		         {
		             System.out.print(e1.getMessage());
		         }
			}
		});
		comboBoxcid.setFont(new Font("Cookie", Font.PLAIN, 28));
		comboBoxcid.setBounds(254, 160, 109, 29);
		contentPane.add(comboBoxcid);
		
		textAreacomplaint = new JTextArea();
		textAreacomplaint.setWrapStyleWord(true);
		textAreacomplaint.setMargin(new Insets(5, 10, 5, 10));
		textAreacomplaint.setLineWrap(true);
		textAreacomplaint.setFont(new Font("Cookie", Font.PLAIN, 28));
		textAreacomplaint.setBounds(100, 204, 627, 216);
		contentPane.add(textAreacomplaint);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideForm();
				new adminloggedin().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnNewButton.setBounds(52, 36, 103, 39);
		contentPane.add(btnNewButton);
		
		label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setText("Enter flat no:");
		label.setFont(new Font("Cookie", Font.PLAIN, 28));
		label.setBounds(435, 108, 122, 37);
		contentPane.add(label);
		
		label_1 = new JLabel("Select building name :");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_1.setBounds(52, 111, 194, 31);
		contentPane.add(label_1);
		
		chckbxResolved = new JCheckBox("Resolved");
		chckbxResolved.setBackground(SystemColor.controlHighlight);
		chckbxResolved.setFont(new Font("Cookie", Font.PLAIN, 28));
		chckbxResolved.setForeground(SystemColor.controlHighlight);
		chckbxResolved.setBounds(435, 167, 109, 21);
		chckbxResolved.setOpaque(false);
		contentPane.add(chckbxResolved);
		
		btnUpdateStatus = new JButton("Update status");
		btnUpdateStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        	 Class.forName("com.mysql.cj.jdbc.Driver");
					 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1?allowPublicKeyRetrieval=true&useSSL=false", "root" , "root");
					 Statement stmt = conn.createStatement();
					 String stat = "Resolved";
					 if(chckbxResolved.isSelected())
					 {
						 String query = "update complaints set status = '"+stat+"' where c_id = '"+complaintid+"'";
						 PreparedStatement ps = conn.prepareStatement(query);
						 ps.executeUpdate();
						 JOptionPane.showMessageDialog(null, "Complaint marked as resolved");
						 hideForm();
						 new adminloggedin().setVisible(true);
					 }
		         }
		         catch (Exception e1)
		         {
		             System.out.print(e1.getMessage());
		         }
			}
		});
		btnUpdateStatus.setBackground(SystemColor.controlHighlight);
		btnUpdateStatus.setForeground(SystemColor.desktop);
		btnUpdateStatus.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnUpdateStatus.setBounds(563, 160, 164, 29);
		contentPane.add(btnUpdateStatus);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel);
	}

	protected void hideForm() {
		this.setVisible(false);
	}
}
