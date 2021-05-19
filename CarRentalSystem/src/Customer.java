import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Customer {

	private JFrame frame;
	private JTextField name;
	private JTextField address;
	private JTextField mobile;
	private JTextField ssn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
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
	public Customer() {
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
		
		JLabel customerinfo = new JLabel("Customer Info",SwingConstants.CENTER);
		customerinfo.setFont(new Font("Lithos Pro", Font.PLAIN, 20));
		customerinfo.setForeground(Color.BLUE);
		customerinfo.setBounds(104, 22, 247, 31);
		
		frame.getContentPane().add(customerinfo);
		
		JLabel lblUsername = new JLabel("Name");
		lblUsername.setBounds(85, 69, 81, 23);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Address");
		lblPassword.setBounds(85, 141, 81, 23);
		frame.getContentPane().add(lblPassword);
		
		name = new JTextField();
		name.setBounds(164, 65, 207, 31);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(164, 139, 207, 31);
		frame.getContentPane().add(address);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(85, 178, 81, 23);
		frame.getContentPane().add(lblMobile);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(164, 176, 207, 31);
		frame.getContentPane().add(mobile);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(85, 106, 81, 23);
		frame.getContentPane().add(lblId);
		
		ssn = new JTextField();
		ssn.setColumns(10);
		ssn.setBounds(164, 104, 207, 31);
		frame.getContentPane().add(ssn);
		frame.setVisible(false);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name_ = name.getText();
				String ssn_ = ssn.getText();
				String address_ = address.getText();
				String mobile_ = mobile.getText();
				
				if(name_.isEmpty() || ssn_.isEmpty() || address_.isEmpty() || mobile_.isEmpty()) {
					return;
				}
				
				try {
					Connection	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRentals","root","admin12345");
					
					PreparedStatement statement = myConn.prepareStatement("insert into customers(name,ssn,address,mobile) values (?,?,?,?);");
					statement.setString(1, name_);
					statement.setString(2, ssn_);
					statement.setString(3, address_);
					statement.setString(4, mobile_);
					
					statement.execute();
					myConn.close();
					
					JOptionPane.showMessageDialog(null,"success!");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSubmit.setBounds(164, 243, 117, 29);
		frame.getContentPane().add(btnSubmit);
		
		
	}
}
