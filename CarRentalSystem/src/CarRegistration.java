import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CarRegistration {

	private JFrame frame;
	private JTextField CarRegNo;
	private JTextField CarMake;
	private JTextField LicensePlate;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarRegistration window = new CarRegistration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
//		try {
//			//1.Get Connection to Database
//		Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
//			Statement myStmt = myConn.createStatement();
//			ResultSet myrs = myStmt.executeQuery("select * from CarsInfo;");
//			while(myrs.next()) {
//				System.out.println(myrs.getString("CarNumber"));
//			}
//			
//		}catch(Exception exc) {
//			exc.printStackTrace();
//		}
		
	}

	/**
	 * Create the application.
	 */
	
	//public static String[][] informations = new String[100][5];
	Vector<Vector<String>> informations = new Vector<Vector<String>>();
	private JTextField Price;
	public void GetTable() {
		try {
			//1.Get Connection to Database
		Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
			Statement myStmt = myConn.createStatement();
			ResultSet myrs = myStmt.executeQuery("select * from CarsInfo;");
			int ind =0 ;
			while(myrs.next()) {
				String carnum = myrs.getString("CarNumber");
				String make = myrs.getString("Make");
				String available = myrs.getString("Available");
				String LP = myrs.getString("LicensePlate");
				int val = myrs.getInt("Price");
				String val_ = Integer.toString(val);
				String arr[] = {carnum,make,available,LP,val_};
				
				Vector<String>tmp = new Vector<String>();
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
	
	public CarRegistration() {
		GetTable();
		initialize();
	}

	public  void changeVisible() {
		frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	
	public int rowindex=-1;
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(600,390);
		frame.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Car Registration",SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Lithos Pro", Font.PLAIN, 20));
		lblNewLabel.setBounds(82, 6, 263, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Car Reg No");
		lblNewLabel_1.setBounds(16, 87, 84, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Make");
		lblNewLabel_1_1.setBounds(16, 125, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("License");
		lblNewLabel_1_2.setBounds(16, 160, 74, 16);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Available");
		lblNewLabel_1_3.setBounds(16, 193, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		CarRegNo = new JTextField();
		CarRegNo.setBounds(102, 87, 130, 26);
		frame.getContentPane().add(CarRegNo);
		CarRegNo.setColumns(10);
		
		CarMake = new JTextField();
		CarMake.setColumns(10);
		CarMake.setBounds(102, 122, 130, 26);
		frame.getContentPane().add(CarMake);
		
		LicensePlate = new JTextField();
		LicensePlate.setColumns(10);
		LicensePlate.setBounds(102, 155, 130, 26);
		frame.getContentPane().add(LicensePlate);
		
		JButton ADD = new JButton("ADD");
		
		ADD.setBounds(16, 248, 117, 29);
		frame.getContentPane().add(ADD);
		
		JButton EDIT = new JButton("EDIT");
		EDIT.setBounds(16, 274, 117, 29);
		frame.getContentPane().add(EDIT);
		
		JButton DELETE = new JButton("DELETE");
		
		DELETE.setBounds(133, 248, 109, 29);
		frame.getContentPane().add(DELETE);
		
		JButton CLEAR = new JButton("CLEAR");
		CLEAR.setBounds(133, 274, 109, 29);
		frame.getContentPane().add(CLEAR);
		
		JRadioButton RadioOn = new JRadioButton("Yes");
		RadioOn.setBounds(102, 193, 54, 16);
		frame.getContentPane().add(RadioOn);
		
		JRadioButton RadioOff = new JRadioButton("No");
		RadioOff.setBounds(178, 191, 54, 20);
		frame.getContentPane().add(RadioOff);
		
		ButtonGroup G1 = new ButtonGroup();
		G1.add(RadioOn);
		G1.add(RadioOff);
		
		//String columns[] = {"Car Number","Make","Available","License Plate","Price"};
		Vector<String> columns = new Vector<String>();
		columns.add("Car Number");
		columns.add("Make");
		columns.add("Available");
		columns.add("License Plate");
		columns.add("Price");
		
		
		DefaultTableModel model = new DefaultTableModel(informations,columns);
		
		
		model.setColumnIdentifiers(columns);
		table = new JTable(model);
		
		  
		
		table.setBounds(280, 80, 300, 197);
		//table.setSize(200,200);
		
		frame.getContentPane().add(table);
		
		frame.getContentPane().add(table);
		
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(16, 220, 61, 16);
		frame.getContentPane().add(lblPrice);
		
		Price = new JTextField();
		Price.setColumns(10);
		Price.setBounds(102, 215, 130, 26);
		frame.getContentPane().add(Price);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
            		int row = table.getSelectedRow();
            		rowindex = row;
            		int [] cols = table.getSelectedColumns();
            		
//            		System.out.print(table.getModel().getValueAt(row));
            		String value = table.getModel().getValueAt(row, 0).toString();
            		
            		//System.out.print(value);
            		CarRegNo.setText(table.getModel().getValueAt(row, 0).toString());
            		CarMake.setText(table.getModel().getValueAt(row, 1).toString());
            		LicensePlate.setText(table.getModel().getValueAt(row, 3).toString());
            		String availability = table.getModel().getValueAt(row, 2).toString();
            		String price_ = table.getModel().getValueAt(row, 4).toString();
            		Price.setText(price_);
//            		System.out.print(b);
            		if(availability.toLowerCase() == "yesyes") {
            			RadioOn.setSelected(true);
            		}else {
//            			System.out.print(availability.toLowerCase());
            			RadioOff.setSelected(true);
            		}
            }       
          });  
		
		//button actions
		//ADD
		
		
		
		ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CarNo = CarRegNo.getText();
				String Make = CarMake.getText();
				String available = "NO";
				if(RadioOn.isSelected()) {
					available = "YES";
				}
				String LP = LicensePlate.getText();
				String price = Price.getText();
				int price_ = Integer.parseInt(price);
				//String cmnd = "Insert into CarsInfo (CarNumber,Make,Available,LicensePlate) values ("+CarNo+","+Make+","+available+","+LP+");";
				String cmnd = "Insert into CarsInfo (CarNumber,Make,Available,LicensePlate,Price) values (?,?,?,?,?);";
				try {
					Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					PreparedStatement prepared = myConn.prepareStatement(cmnd);
					prepared.setString(1, CarNo);
					prepared.setString(2, Make);
					prepared.setString(3, available);
					prepared.setString(4, LP);
					prepared.setInt(5, price_);
					prepared.execute();
					myConn.close();
					
					Vector<String> newobj = new Vector<String>();
					newobj.add(CarNo);
					newobj.add(Make);
					newobj.add(available);
					newobj.add(LP);
					newobj.add(price);
					
					
					
					
					model.addRow(newobj);
					table.setModel(model);
					
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		
		CLEAR.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				CarRegNo.setText("");
				CarMake.setText("");
				LicensePlate.setText("");
				Price.setText("");
			}
		});
		
		
		DELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CarNo = CarRegNo.getText();
				String Make = CarMake.getText();
				String available = "NO";
				if(RadioOn.isSelected()) {
					available = "YES";
				}
				String LP = LicensePlate.getText();
				try {
					Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					String cmnd = "delete from CarsInfo where CarNumber = (?)";
					PreparedStatement prepared = myConn.prepareStatement(cmnd);
					prepared.setString(1,CarNo);
					//prepared.execute();
					int row = table.getSelectedRow();
					if(row == -1) {
						System.out.print("is zero");
						return;
					}
					System.out.print(table.getSelectedRow());
					model.removeRow(table.getSelectedRow());
					table.setModel(model);
					myConn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Something is wrong");  
				}
				
			}
		});
		
		
		EDIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CarNo = CarRegNo.getText();
				String Make = CarMake.getText();
				String available = "NO";
				String price = Price.getText();
				int price_ = Integer.parseInt(price);
				if(RadioOn.isSelected()) {
					available = "YES";
				}
				String LP = LicensePlate.getText();
				System.out.print(CarNo+" "+Make);
				try {
					Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					//myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					String cmnd = "update CarsInfo Set CarNumber = (?) ,Make = (?), Available = (?) ,LicensePlate = (?) ,Price =(?) Where CarNumber = (?)";
					
					PreparedStatement prepared = myConn.prepareStatement(cmnd);
					prepared.setString(1,CarNo);
					prepared.setString(2,Make);
					prepared.setString(3,available);
					prepared.setString(4,LP);
					prepared.setInt(5, price_);
					prepared.setString(6,CarNo);
					prepared.execute();
					myConn.close();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
}
