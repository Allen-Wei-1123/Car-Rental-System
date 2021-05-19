import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.sql.*;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
public class Rent {

	private JFrame frame;
	private JTextField CarIDField;
	private JTextField FeeField;
	private JTextField DateField;
	private JTable table;
	private JButton RENT;
	private JTable table_1;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rent window = new Rent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			//1.Get Connection to Database
		Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
			Statement myStmt = myConn.createStatement();
			ResultSet myrs = myStmt.executeQuery("select * from CarsInfo;");
			while(myrs.next()) {
				System.out.println(myrs.getString("CarNumber"));
			}
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	//public static String[][] informations = new String[100][5];
	Vector<Vector<String>> informations = new Vector<Vector<String>>();
	public void SetUpTable() {
		try {
			//1.Get Connection to Database
			Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
			Statement myStmt = myConn.createStatement();
			//ResultSet myrs = myStmt.executeQuery("select * from CarsInfo;");
			PreparedStatement statement = myConn.prepareStatement("select * from CarsInfo where Available = (?)");
			statement.setString(1, "Yes");
			ResultSet myrs = statement.executeQuery();
			int ind =0 ;
			while(myrs.next()) {
				String carnum = myrs.getString("CarNumber");
				String make = myrs.getString("Make");
				String available = myrs.getString("Available");
				String LP = myrs.getString("LicensePlate");
				int val = myrs.getInt("Price");
				String val_ = Integer.toString(val);
				String arr[] = {carnum,make,available,LP,val_};
				System.out.print(LP);
				Vector<String> tmp = new Vector<String>();
				tmp.add(carnum);
				tmp.add(make);
				tmp.add(available);
				tmp.add(LP);
				tmp.add(val_);
				informations.add(tmp);
			}
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	Vector<Integer> CustomerList = new Vector<Integer>();
	public void GetAllCustomers() {
		try {
			Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
			String val = "Select * from customers;";
			PreparedStatement stmt = myConn.prepareStatement(val);
			ResultSet customerlist_ = stmt.executeQuery();
			while(customerlist_.next()) {
				int idnum = customerlist_.getInt("id");
				CustomerList.add(idnum);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Rent() {
		GetAllCustomers();
		SetUpTable();
		initialize();
	}
	public  void changeVisible() {
		frame.setVisible(true);
	}
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	public Integer SelectedCustomer;
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(false);
		frame.setSize(600,390);
		JLabel title = new JLabel("Rent a Car",SwingConstants.CENTER);
		title.setForeground(Color.BLUE);
		title.setFont(new Font("Lithos Pro", Font.PLAIN, 20));
		title.setBounds(148, 18, 170, 27);
		frame.getContentPane().add(title);
		
		JLabel CarID = new JLabel("Car ID");
		CarID.setFont(new Font("Dialog", Font.PLAIN, 12));
		CarID.setBounds(6, 73, 61, 16);
		frame.getContentPane().add(CarID);
		
		JLabel customerID = new JLabel("Customer ID");
		customerID.setFont(new Font("Dialog", Font.PLAIN, 12));
		customerID.setBounds(6, 111, 79, 16);
		frame.getContentPane().add(customerID);
		
		JLabel lblRetailFee = new JLabel("Retail Fee");
		lblRetailFee.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRetailFee.setBounds(6, 149, 61, 16);
		frame.getContentPane().add(lblRetailFee);
		
		JLabel lblDate = new JLabel("Days");
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDate.setBounds(6, 187, 61, 16);
		frame.getContentPane().add(lblDate);
		
		CarIDField = new JTextField();
		CarIDField.setBounds(93, 68, 130, 26);
		frame.getContentPane().add(CarIDField);
		CarIDField.setColumns(10);
		CarIDField.setEditable(false);
		
		FeeField = new JTextField();
		FeeField.setColumns(10);
		FeeField.setBounds(93, 144, 130, 26);
		FeeField.setEditable(false);
		frame.getContentPane().add(FeeField);
		
		DateField = new JTextField();
		DateField.setColumns(10);
		DateField.setBounds(93, 182, 130, 26);
		frame.getContentPane().add(DateField);
		
		
		//String columns[] = {"Car Number","Make","Available","License Plate","Price"};
		Vector<String>columns = new Vector<String>();
		columns.add("Car Number");
		columns.add("Make");
		columns.add("Available");
		columns.add("License Plate");
		columns.add("Price");
		table = new JTable(informations,columns){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};;
		table.setBounds(235, 57, 324, 300);
		frame.getContentPane().add(table);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
            		int row = table.getSelectedRow();
            		String carnum = table.getModel().getValueAt(row, 0).toString();
            		String make = table.getModel().getValueAt(row, 1).toString();
            		String avai = table.getModel().getValueAt(row, 2).toString();
            		String LP = table.getModel().getValueAt(row, 3).toString();
            		String price = table.getModel().getValueAt(row, 4).toString();
            		
            		CarIDField.setText(carnum);
            		FeeField.setText(price);
            		
            		
            }       
          });  
		
		//submit button
		RENT = new JButton("Rent");
		RENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String carID = CarIDField.getText();
			//	String customer = CustIDField.getText();
				String fees = FeeField.getText();
				String date = DateField.getText();
				
//				if(carID.isEmpty() || customer.isEmpty() || fees.isEmpty() || date.isEmpty() || due.isEmpty()) {
//					JOptionPane.showMessageDialog(null, "Some fields are empty");
//					return;
//				}
				try {
					Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					
					PreparedStatement statement = myConn.prepareStatement("insert into RentRecords(customerid,carid,days) values (?,?,?);");
					statement.setInt(1, SelectedCustomer);
					statement.setString(2, carID);
					int date_ = Integer.parseInt(date);
					statement.setInt(3, date_);
					
					
					statement.execute();
					String val = "update CarsInfo Set Available = (?) where CarNumber = (?)";
					PreparedStatement stmt2 = myConn.prepareStatement(val);
					stmt2.setString(1, "NO");
					stmt2.setString(2, carID);
					
					stmt2.execute();
					myConn.close();
					
					JOptionPane.showMessageDialog(null,"success!");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		RENT.setBounds(53, 314, 130, 43);
		frame.getContentPane().add(RENT);
		
		table_1 = new JTable();
		table_1.setBounds(317, 88, 1, 1);
		frame.getContentPane().add(table_1);
		
		JComboBox comboBox = new JComboBox(CustomerList);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectedCustomer = (Integer) comboBox.getSelectedItem();
				System.out.print(SelectedCustomer);
			}
		});
		comboBox.setBounds(93, 107, 130, 27);
		comboBox.setSelectedIndex(0);
		frame.getContentPane().add(comboBox);
		//SelectedCustomer = (Integer) comboBox.getSelectedItem();
		
		//System.out.print("it is "+comboBox.getSelectedItem().toString());
		
		
	}
}
