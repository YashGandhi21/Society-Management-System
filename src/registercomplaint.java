import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.Insets;

public class registercomplaint extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registercomplaint frame = new registercomplaint();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String buildingid;
	JTextArea textAreacomplaint;
	String buildingname, flatno;
	JComboBox bldg_name, flat_no;
	private JLabel label;
	private JLabel label_1;
	public registercomplaint() {
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
		bldg_name.setBounds(314, 76, 109, 31);
		contentPane.add(bldg_name);
		
		flat_no = new JComboBox();
		flat_no.setBorder(null);
		flat_no.setBackground(SystemColor.controlHighlight);
		flat_no.setEditable(true);
		flat_no.addItem("--Select--");
		flat_no.setMaximumRowCount(15);
	    flat_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flatno = (String) flat_no.getSelectedItem();
			}
		});
	    flat_no.setFont(new Font("Cookie", Font.PLAIN, 28));
	    flat_no.setBounds(581, 76, 109, 30);
		contentPane.add(flat_no);
		
		textAreacomplaint = new JTextArea();
		textAreacomplaint.setMargin(new Insets(5, 10, 5, 10));
		textAreacomplaint.setLineWrap(true);
		textAreacomplaint.setWrapStyleWord(true);
		textAreacomplaint.setFont(new Font("Cookie", Font.PLAIN, 28));
		textAreacomplaint.setBounds(88, 164, 654, 245);
		contentPane.add(textAreacomplaint);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBorder(null);
		btnSubmit.setBackground(SystemColor.controlHighlight);
		btnSubmit.setFont(new Font("Cookie", Font.PLAIN, 28));
		btnSubmit.setBounds(212, 435, 130, 43);
		btnSubmit.addActionListener(new RegisterComplaint());
		contentPane.add(btnSubmit);
		
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
		btnNewButton.setBounds(467, 435, 130, 43);
		contentPane.add(btnNewButton);
		
		label = new JLabel("Select building name :");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cookie", Font.PLAIN, 28));
		label.setBounds(127, 76, 194, 31);
		contentPane.add(label);
		
		label_1 = new JLabel();
		label_1.setForeground(Color.WHITE);
		label_1.setText("Enter flat no:");
		label_1.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_1.setBounds(463, 73, 122, 37);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel.setBounds(0, 0, 836, 513);
		contentPane.add(lblNewLabel);
	}
	protected void hideForm() {
		this.setVisible(false);
	}
	
	class RegisterComplaint implements ActionListener
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
					String findname = "select owner_name from building where bldg_name = '"+buildingname+"' and flat_no = '"+flatno+"'";
					ResultSet rs = stmt.executeQuery(findname);
					String tenantname = null;
					if(rs.next())
					{
						tenantname = rs.getString("owner_name");
					}
					String complaint = textAreacomplaint.getText();
					buildingid = buildingname + "-" + flatno;
					String query = "insert into complaints(bldg_id,owner_name,complaint,status) values(?,?,?,?)";
					PreparedStatement ps  = conn.prepareStatement(query);
					ps.setString(1, buildingid);
					ps.setString(2, tenantname);
					ps.setString(3, complaint);
					ps.setString(4, "Unresolved");
					if(complaint.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Complaint field cannot be empty!");
					}
					else
					{
						if(complaint.length() <= 250)
						{
							ps.executeUpdate();
							conn.close();
							hideForm();
							new adminloggedin().setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please enter complaint in less than 250 characters");
						}
					}
				}
						
	        }
	        catch (Exception e)
	        {
	            System.out.print(e.getMessage());
	        }
        }
       
    }
}
