package account;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bookingSystemGUI.Main;
import customer.CustomerPanelGUI;
import utility.Utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register extends JFrame {
	private static Logger logger = Logger.getLogger(CustomerPanelGUI.class.getName());

	private JPanel contentPane;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField phoneTextField;
	private JTextField addressTextField;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JPasswordField verifyPasswordField;
	/**
	 * Create the frame.
	 */
	public Register() {
		setResizable(false);
		setTitle("Customer Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 365);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(105, 23, 94, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(105, 65, 94, 14);
		contentPane.add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(130, 100, 94, 14);
		contentPane.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(120, 135, 94, 14);
		contentPane.add(lblAddress);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(110, 169, 94, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(110, 205, 96, 14);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(63, 235, 120, 14);
		contentPane.add(lblConfirmPassword);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(183, 20, 138, 20);
		contentPane.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(183, 62, 138, 20);
		contentPane.add(lastNameTextField);
		
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(183, 99, 138, 20);
		contentPane.add(phoneTextField);
		
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(183, 134, 138, 20);
		contentPane.add(addressTextField);
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(183, 167, 138, 20);
		contentPane.add(usernameTextField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 202, 138, 20);
		contentPane.add(passwordField);
		
		verifyPasswordField = new JPasswordField();
		verifyPasswordField.setBounds(183, 232, 138, 20);
		contentPane.add(verifyPasswordField);
		
		JButton btnRegisterCustomer = new JButton("Register Customer");
		btnRegisterCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		        try
		        {
		        	String firstName = firstNameTextField.getText().trim();
		        	String lastName = lastNameTextField.getText().trim();
		        	String phone = phoneTextField.getText().trim();
		        	String address = addressTextField.getText().trim();
		        	String username = usernameTextField.getText().trim();
		        	char[] password = passwordField.getPassword();
		        	String passwordInput = String.valueOf(password);
		        	char[] verifyPassword = verifyPasswordField.getPassword();
		        	
		        	if (!Utils.validateInput(firstName, "^[A-Za-z]+$", "Please enter a valid name i.e. Tom")){
		        		firstNameTextField.grabFocus();
		        		return;
		        	}
		        	if(!Utils.validateInput(lastName, "^[A-Za-z]+$", "Please enter a valid name i.e. Smith")){
		        		lastNameTextField.grabFocus();
		        		return;
		        	}
		        	if(!Utils.validateInput(phone, "^[0-9]{10}$", "Please enter a 10-digit Phone Number i.e 0412345678")){
		        		phoneTextField.grabFocus();
		        		return;
		        	}
		        	if(!Utils.validateInput(address, "^[\\d]+([.]|[-]|\\s)[A-Za-z]*([.]|[-]|\\s)[A-Za-z]*$", "Please enter a valid address i.e. 1 Sesame St")){
		        		addressTextField.grabFocus();
		        		return;
		        	}
		        	if(!Utils.validateInput(username, "^[\\w]{1,15}$", "Please enter a valid username (Max length: 15)")){
		        		usernameTextField.grabFocus();
		        		return;
		        	}
		        	if(!Utils.validateUsername(username)){
		        		JOptionPane.showMessageDialog(null, "Please enter a unique Username.", "Username already exists.", JOptionPane.WARNING_MESSAGE);
		        		usernameTextField.grabFocus();
		        		return;
		        	}
		        	if(!Utils.validateInput(passwordInput, "^[\\w]{1,15}$", "Please enter a valid username (Max length: 15)")){
		        		passwordField.grabFocus();
		        		return;
		        	}
		        	
		        	
		        	
		        	
		        	if(Arrays.equals(password, verifyPassword) == false){
		        		JOptionPane.showMessageDialog(null, "Please Confirm Password.");
		        		verifyPasswordField.grabFocus();
		        		return;
		        	}
		        	
		            FileWriter fw=new FileWriter("customerinfo.txt",true);          //open text file in writer append mode
		            BufferedWriter bw=new BufferedWriter(fw);       //gave access of file to buffer writer
		            
		            bw.write(firstName);        //write string input from console directly to file
		            bw.write(",");              //append , for easiness n data extraction
		            
		            bw.write(lastName);        //write string input from console directly to file
		            bw.write(",");              //append , for easiness n data extraction
		            
		            bw.write(phone);
		            bw.write(",");
		            
		        	bw.write(address);
		            bw.write(",");
		            
		            bw.write(username);
		            bw.write(",");
		            
		            bw.write(password);
		            
		            bw.newLine();       //after all data inserted an endline to make file ready for next registration
		            bw.close();
		            JOptionPane.showMessageDialog(null, "Registered Successfully. Please Login");
		            new Login().setVisible(true);
		            dispose();
		        }
		        catch(IOException e1)        //catch any exception occur during this process
		        {	
		        	logger.log(Level.SEVERE, e1.getMessage());
		            System.out.println("There is an exception in File Handling: "+e1);
		        }
		    
			}
		});
		btnRegisterCustomer.setBounds(255, 290, 164, 23);
		contentPane.add(btnRegisterCustomer);
		
		JButton btnCanncel = new JButton("Cancel");
		btnCanncel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().setVisible(true);
				dispose();
			}
		});
		btnCanncel.setBounds(25, 290, 80, 23);
		contentPane.add(btnCanncel);
	}
}
