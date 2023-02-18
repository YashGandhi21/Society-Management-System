import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.text.*;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.SpringLayout;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JMenuBar;

public class maintenancegenerator extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldtenantid;
	private JTextField textFieldtenantname;
	private JTextField textFieldphno;
	public JTable table;
	
    
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maintenancegenerator frame = new maintenancegenerator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	String buildingname, flatno;
	double maintenance = 0,sinkingfund = 0,parkcharge = 0,non_occupancy = 0, total = 0;
	String datefrom = "2018/01/01";
	LocalDate dateFrom = LocalDate.parse(datefrom,DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	LocalDate dateTo = LocalDate.now();
	Period intervalPeriod = Period.between(dateFrom, dateTo);
	int days = intervalPeriod.getDays();
	int months = intervalPeriod.getMonths();
	int years = intervalPeriod.getYears();
    int fine = 0;
	private JTextField textFieldtenanttype;
	private JTextField textFieldparking;
	private JTextField textFieldflattype;
	private JFormattedTextField textFielddate;
	JComboBox bldg_name, flat_no;
	private SpringLayout springLayout;
	private SpringLayout springLayout_1;
	
	
	public maintenancegenerator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JLabel lblEnterBuildingName = new JLabel("Select building name :");
		lblEnterBuildingName.setForeground(Color.WHITE);
		lblEnterBuildingName.setBounds(37, 56, 194, 31);
		lblEnterBuildingName.setFont(new Font("Cookie", Font.PLAIN, 28));
		contentPane.add(lblEnterBuildingName);
		
		JLabel lblflat_no = new JLabel();
		lblflat_no.setForeground(Color.WHITE);
		lblflat_no.setBounds(359, 53, 122, 37);
		lblflat_no.setText("Enter flat no:");
		lblflat_no.setFont(new Font("Cookie", Font.PLAIN, 28));
		contentPane.add(lblflat_no);
		
		JButton btnGenerate = new JButton("Generate bill");
		btnGenerate.setBorder(null);
		btnGenerate.setBackground(SystemColor.controlHighlight);
		btnGenerate.setBounds(937, 55, 160, 31);
		btnGenerate.setFont(new Font("Cookie", Font.PLAIN, 28));
		contentPane.add(btnGenerate);
		btnGenerate.addActionListener(new Generate());
		
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
		bldg_name.setBounds(230, 56, 109, 31);
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
	    flat_no.setBounds(473, 56, 109, 30);
		contentPane.add(flat_no);
		
		JLabel lblTenantId = new JLabel("Tenant ID :");
		lblTenantId.setForeground(Color.WHITE);
		lblTenantId.setBounds(54, 187, 122, 24);
		lblTenantId.setFont(new Font("Cookie", Font.PLAIN, 28));
		contentPane.add(lblTenantId);
		
		textFieldtenantid = new JTextField();
		textFieldtenantid.setBounds(241, 182, 127, 34);
		textFieldtenantid.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldtenantid.setEditable(false);
		textFieldtenantid.setColumns(10);
		contentPane.add(textFieldtenantid);
		
		JLabel lbltenantname = new JLabel("Tenant name :");
		lbltenantname.setForeground(Color.WHITE);
		lbltenantname.setBounds(53, 254, 126, 24);
		lbltenantname.setFont(new Font("Cookie", Font.PLAIN, 28));
		contentPane.add(lbltenantname);
		
		textFieldtenantname = new JTextField();
		textFieldtenantname.setBounds(241, 250, 286, 33);
		textFieldtenantname.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldtenantname.setEditable(false);
		textFieldtenantname.setColumns(10);
		contentPane.add(textFieldtenantname);
		
		JLabel label_2 = new JLabel("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(22, 97, 1086, 13);
		contentPane.add(label_2);
		
		JLabel lblphno = new JLabel("Phone no :");
		lblphno.setForeground(Color.WHITE);
		lblphno.setBounds(54, 515, 122, 24);
		lblphno.setFont(new Font("Cookie", Font.PLAIN, 28));
		contentPane.add(lblphno);
		
		textFieldphno = new JTextField();
		textFieldphno.setBounds(241, 512, 170, 31);
		textFieldphno.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldphno.setEditable(false);
		textFieldphno.setColumns(10);
		contentPane.add(textFieldphno);
	
		Object[][] rowdata = {{"Maintenance",maintenance},{"Sinking Fund",sinkingfund},{"Parking",parkcharge},{"Non-Occupancy",non_occupancy},{"Fine",fine},{"Total",total}};
		String[] columnnames = {"Charges","Rs"};
		
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(593, 210, 380, 323);
		 contentPane.add(scrollPane);
		 
		table = new JTable(rowdata,columnnames);
		table = new JTable(rowdata,columnnames);
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setRowHeight(50);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Cookie", Font.PLAIN, 28));
		scrollPane.setViewportView(table);
		
		JLabel label_1 = new JLabel("Tenant type :");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_1.setBounds(53, 318, 115, 31);
		contentPane.add(label_1);
		
		textFieldtenanttype = new JTextField();
		textFieldtenanttype.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldtenanttype.setEditable(false);
		textFieldtenanttype.setColumns(10);
		textFieldtenanttype.setBounds(240, 318, 108, 31);
		contentPane.add(textFieldtenanttype);
		
		JLabel label_3 = new JLabel("No. of parking slots :");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Cookie", Font.PLAIN, 28));
		label_3.setBounds(54, 451, 175, 31);
		contentPane.add(label_3);
		
		textFieldparking = new JTextField();
		textFieldparking.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldparking.setEditable(false);
		textFieldparking.setColumns(10);
		textFieldparking.setBounds(241, 446, 98, 31);
		contentPane.add(textFieldparking);
		
		JLabel label = new JLabel("Flat type :");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cookie", Font.PLAIN, 28));
		label.setBounds(55, 381, 86, 37);
		contentPane.add(label);
		
		textFieldflattype = new JTextField();
		textFieldflattype.setFont(new Font("Cookie", Font.PLAIN, 28));
		textFieldflattype.setEditable(false);
		textFieldflattype.setColumns(10);
		textFieldflattype.setBounds(241, 383, 108, 32);
		contentPane.add(textFieldflattype);
		
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
		btnBack.setBounds(54, 582, 114, 37);
		contentPane.add(btnBack);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.getJFormattedTextField().setBackground(SystemColor.controlHighlight);
		datePicker.getJFormattedTextField().setBorder(null);
		datePicker.getJFormattedTextField().setPreferredSize(new Dimension(180, 32));
		datePicker.getJFormattedTextField().setFont(new Font("Cookie", Font.PLAIN, 28));
		springLayout = (SpringLayout) datePicker.getLayout();
		datePicker.setAlignmentX(Component.LEFT_ALIGNMENT);
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datefrom = datePicker.getJFormattedTextField().getText();
		        dateFrom = LocalDate.parse(datefrom, formatter);
			}
		});
		datePicker.setBounds(748, 56, 160, 32);
		datePicker.setEnabled(true);
		contentPane.add(datePicker);
		
		JLabel lblSelectDueDate = new JLabel("Select due date:");
		lblSelectDueDate.setForeground(Color.WHITE);
		lblSelectDueDate.setFont(new Font("Cookie", Font.PLAIN, 28));
		lblSelectDueDate.setBounds(600, 56, 160, 31);
		contentPane.add(lblSelectDueDate);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(SystemColor.controlHighlight);
		menubar.setLayout(null);
		menubar.setBounds(0, 0, 1136, 30);
		contentPane.add(menubar);
		
		JButton AbtUs = new JButton("Help");
		AbtUs.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new maintenancehelp().setVisible(true);
			}
		});
		AbtUs.setBorder(null);
		AbtUs.setBackground(SystemColor.controlHighlight);
		AbtUs.setFont(new Font("Cookie", Font.PLAIN, 25));
		AbtUs.setBounds(0,0,100,30);
		menubar.add(AbtUs);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\Images for SMS\\wall.jpg"));
		lblNewLabel.setBounds(0, 0, 1150, 700);
		contentPane.add(lblNewLabel);
	}
	
	protected void hideForm() {
		this.setVisible(false);
	}

	class Generate implements ActionListener
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
						 int no_parking = rs.getInt("no_of_parkings");
						 String no_of_parking = rs.getString("no_of_parkings");
						 
						 if(flattype.equals("1 BHK"))
						 {
							 maintenance = 1000;
						 }
						 else if(flattype.equals("2 BHK"))
						 {
							 maintenance = 1200;
						 }
						 else
						 {
							 maintenance = 1400;
						 }
						 if(tenanttype.equals("Rental"))
						 {
							 non_occupancy = 500;
						 }
						 parkcharge = no_parking*50;
						 sinkingfund = maintenance*0.25;
						 textFieldtenantid.setText(tenantid);
						 textFieldtenantname.setText(tenantname);
						 textFieldphno.setText(phno);
						 textFieldtenanttype.setText(tenanttype);
						 textFieldflattype.setText(flattype);
						 textFieldparking.setText(no_of_parking);
						 intervalPeriod = Period.between(dateFrom, dateTo);
						 days = intervalPeriod.getDays();
					     months = intervalPeriod.getMonths();
					     years = intervalPeriod.getYears();
						 if(years > 0)
						 {
							 months = (months+12)*years;
						 }
						 if(days <= 0 && months <= 0)
						 {
							 fine = 0;
						 }
						 else if(days > 0 && months == 0)
						 {
							 fine = 50;
						 }
						 else if(days >= 0 && months > 0)
						 {
							 fine = (months+1)*50;
						 }
						 else if(days < 0 && months > 0)
						 {
							 fine = months*50;
						 }

						 double total = maintenance + sinkingfund + parkcharge + non_occupancy + fine;
						 Object[][] rowdata = {{"Maintenance",maintenance},{"Sinking Fund",sinkingfund},{"Parking",parkcharge},{"Non-Occupancy",non_occupancy},{"Fine",fine},{"Total",total}};
						 String[] columnnames = {"Charges","Rs"};
						 
						 
						 JScrollPane scrollPane = new JScrollPane();
						 scrollPane.setBounds(593, 210, 380, 323);
						 contentPane.add(scrollPane);
						 
						 table = new JTable(rowdata,columnnames);
						 table.setEnabled(false);
						 table.setFillsViewportHeight(true);
						 table.setRowHeight(50);
						 table.setColumnSelectionAllowed(true);
						 table.setCellSelectionEnabled(true);
						 table.setFont(new Font("Tahoma", Font.PLAIN, 18));
						 scrollPane.setViewportView(table);
						 conn.close();
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(null, "Record not found");
						 
						 flat_no.setSelectedItem("--Select--");
						 textFieldtenantid.setText("");
						 textFieldtenantname.setText("");
						 textFieldphno.setText("");
						 textFieldtenanttype.setText("");
						 textFieldflattype.setText("");
						 textFieldparking.setText("");
						 
						 total = maintenance = sinkingfund = parkcharge = non_occupancy = fine = 0;
						 Object[][] rowdata = {{"Maintenance",maintenance},{"Sinking Fund",sinkingfund},{"Parking",parkcharge},{"Non-Occupancy",non_occupancy},{"Fine",fine},{"Total",total}};
						 String[] columnnames = {"Charges","Rs"};
						 
						 JScrollPane scrollPane = new JScrollPane();
						 scrollPane.setBounds(593, 210, 380, 323);
						 contentPane.add(scrollPane);
						 table = new JTable(rowdata,columnnames);
						 table.setEnabled(false);
						 table.setFillsViewportHeight(true);
						 table.setRowHeight(50);
						 table.setColumnSelectionAllowed(true);
						 table.setCellSelectionEnabled(true);
						 table.setFont(new Font("Tahoma", Font.PLAIN, 18));
						 scrollPane.setViewportView(table);
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
