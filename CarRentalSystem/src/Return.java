import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Return {

	private JFrame frame;
	private JTextField carid;
	private JTextField custid;
	private JTextField day;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Return window = new Return();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public static String[][] informations = new String[10][3];
	public void SetUpTable() {
		try {
			//1.Get Connection to Database
		Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
			Statement myStmt = myConn.createStatement();
			ResultSet myrs = myStmt.executeQuery("select * from RentRecords;");
			int ind =0 ;
			while(myrs.next()) {
				String carnum = myrs.getString("customerid");
				String make = myrs.getString("carid");
				//String available = myrs.getString("Available");
				int days = myrs.getInt("days");
				
				String val_ = Integer.toString(days);
				String arr[] = {carnum,make,val_};
				informations[ind++]=arr;
			}
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	public Return() {
		SetUpTable();
		initialize();
	}
	public  void changeVisible() {
		frame.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(false);
		
		JLabel lblReturn = new JLabel("Return Car", SwingConstants.CENTER);
		lblReturn.setForeground(Color.BLUE);
		lblReturn.setFont(new Font("Lithos Pro", Font.PLAIN, 20));
		lblReturn.setBounds(88, 18, 263, 41);
		frame.getContentPane().add(lblReturn);
		
		JLabel lblNewLabel = new JLabel("Car ID");
		lblNewLabel.setBounds(21, 58, 85, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(21, 104, 85, 16);
		frame.getContentPane().add(lblCustomerId);
		
		JLabel lblDayElap = new JLabel("Day Elapsed");
		lblDayElap.setBounds(21, 149, 85, 16);
		frame.getContentPane().add(lblDayElap);
		
		carid = new JTextField();
		carid.setBounds(108, 53, 130, 26);
		frame.getContentPane().add(carid);
		carid.setColumns(10);
		
		custid = new JTextField();
		custid.setColumns(10);
		custid.setBounds(108, 99, 130, 26);
		frame.getContentPane().add(custid);
		
		day = new JTextField();
		day.setColumns(10);
		day.setBounds(108, 144, 130, 26);
		frame.getContentPane().add(day);
		
		JButton btnNewButton = new JButton("Return");
		
		btnNewButton.setBounds(88, 225, 67, 26);
		frame.getContentPane().add(btnNewButton);
		
		String columns[] = {"Custom ID","Car ID","Days"};
		table = new JTable(informations,columns);
		table.setBounds(244, 53, 1, 1);
		table.setSize(200,200);
		frame.getContentPane().add(table);
		
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
            		int row = table.getSelectedRow();
            
            		
            		String cust_id = table.getModel().getValueAt(row, 0).toString();
            		String car_id = table.getModel().getValueAt(row, 1).toString();
            		String days = table.getModel().getValueAt(row, 2).toString();
            		
            		
            		custid.setText(cust_id);
            		carid.setText(car_id);
            		day.setText(days);
            		

            		
            }       
          });  
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		try {
					Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					String cmnd = "Delete from RentRecords where customerid = (?) and carid = (?)";
					
					
					PreparedStatement prepared = myConn.prepareStatement(cmnd);
					prepared.setString(1,custid.getText());
					prepared.setString(2, carid.getText());
					
					prepared.execute();
					myConn.close();
					JOptionPane.showMessageDialog(null,"success!");
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}

}
