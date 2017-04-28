package BookingSystemGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import CommandLine.Utils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Register() {
		setResizable(false);
		setTitle("Appointment Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 337);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(74, 43, 94, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(74, 85, 94, 14);
		contentPane.add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(74, 122, 94, 14);
		contentPane.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(74, 154, 94, 14);
		contentPane.add(lblAddress);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(74, 190, 94, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(72, 225, 96, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(163, 40, 138, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 82, 138, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(163, 119, 138, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(163, 154, 138, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(163, 187, 138, 20);
		contentPane.add(textField_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 222, 138, 20);
		contentPane.add(passwordField);
		
		JButton btnRegisterCustomer = new JButton("Register Customer");
		btnRegisterCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		        try
		        {
		        	String firstName = textField.getText().trim();
		        	String lastName = textField_1.getText().trim();
		        	String phone = textField_2.getText().trim();
		        	String address = textField_3.getText().trim();
		        	String username = textField_4.getText().trim();
		        	String password = passwordField.getText().trim();
		        	if(firstName.equals("")){
		        		JOptionPane.showMessageDialog(null, "Enter First name");
		        		return;
		        	}if(lastName.equals("")){
		        		JOptionPane.showMessageDialog(null, "Enter Last name");
		        		return;
		        	}if(phone.equals("")){
		        		JOptionPane.showMessageDialog(null, "Enter Phone");
		        		return;
		        	}if(address.equals("")){
		        		JOptionPane.showMessageDialog(null, "Enter address");
		        		return;
		        	}if(username.equals("")){
		        		JOptionPane.showMessageDialog(null, "Enter Username");
		        		return;
		        	}if(password.equals("")){
		        		JOptionPane.showMessageDialog(null, "Enter Password");
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
		            JOptionPane.showMessageDialog(null, "Registred Successfully. Please Login");
		            new Login().setVisible(true);
		            dispose();
		        }
		        catch(IOException e1)        //catch any exception occur during this process
		        {
		            System.out.println("Theer is an exception in File Handling: "+e1);
		        }
		    
			}
		});
		btnRegisterCustomer.setBounds(212, 264, 164, 23);
		contentPane.add(btnRegisterCustomer);
		
		JButton btnCanncel = new JButton("Canncel");
		btnCanncel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().setVisible(true);
				dispose();
			}
		});
		btnCanncel.setBounds(30, 264, 138, 23);
		contentPane.add(btnCanncel);
	}
}
