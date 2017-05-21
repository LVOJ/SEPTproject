package bookingSystemGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import account.Login;
import account.Register;
import customer.CustomerPanelGUI;

import java.util.logging.Logger;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.awt.event.ActionEvent;



/**
 * 
 This is the main class that is launched when the application starts.
 *
 */
public class Main extends JFrame {

	private static Logger logger = Logger.getLogger(CustomerPanelGUI.class.getName());
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.dispose();
				} catch (Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {

		JFrame dialog = new JFrame();
		dialog.setResizable(false); //Diables the frame from being resized by users
		dialog.setDefaultCloseOperation(EXIT_ON_CLOSE);
		dialog.setTitle("Appointment Booking System");
		
		contentPane = new JPanel();
		dialog.setVisible(true);//Sets the dialog visible on the screen.
		dialog.setSize(250, 110);
		
		dialog.setLocationRelativeTo(null);//Sets the dialog centered on the screen.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//The user intends to login so application displays login form and closes this main form
				new Login().setVisible(true);
				dialog.dispose();
			}
		});
		btnLogin.setBounds(25, 25, 85, 30);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//The user intends to register so application displays registration form and closes this main form
				new Register().setVisible(true);
				dialog.dispose();
			}
		});
		btnRegister.setBounds(135, 25, 85, 30);
		contentPane.add(btnRegister); 
	}
}
