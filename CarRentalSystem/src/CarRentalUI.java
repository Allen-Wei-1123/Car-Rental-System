import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarRentalUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarRentalUI window = new CarRentalUI();
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
	public CarRentalUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Car Rental Company");
		title.setFont(new Font("Lithos Pro", Font.PLAIN, 20));
		title.setForeground(Color.BLUE);
		title.setBounds(104, 23, 239, 25);
		
		frame.getContentPane().add(title);
		
		JButton btnNewButton = new JButton("Car Registration");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarRegistration newwind = new CarRegistration();
				newwind.changeVisible();
			}
		});
		
		
		
		btnNewButton.setBounds(104, 63, 239, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer wind = new Customer();
				wind.changeVisible();
			}
		});
		btnCustomer.setBounds(104, 110, 239, 35);
		frame.getContentPane().add(btnCustomer);
		
		JButton btnRentIt = new JButton("Rent It!");
		btnRentIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rent newwind = new Rent();
				newwind.changeVisible();
			}
		});
		btnRentIt.setBounds(104, 157, 239, 35);
		frame.getContentPane().add(btnRentIt);
		
		JButton btnReturn = new JButton("Return");
		
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return newwind = new Return();
				newwind.changeVisible();
			}
		});
		
		
		btnReturn.setBounds(104, 204, 239, 35);
		frame.getContentPane().add(btnReturn);
		
	}
}
