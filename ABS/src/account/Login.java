package account;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bookingSystemGUI.Main;
import business.BusinessOwnerPanel;
import customer.CustomerPanelGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class that determines user levels and authenticate them into the Appointment Booking System.
 *
 */
public class Login extends JFrame {
	private static Logger logger = Logger.getLogger(CustomerPanelGUI.class.getName());
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JComboBox userTypeComboBox;
	public boolean OwnerFlag;
	public boolean CustomerFlag;
	public boolean authenticFlag;

	/**
	 * Create the frame.
	 */

	public Login() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 230);
		setLocationRelativeTo(null); //Will make the frame centered on the computer screen
		
		//Initialize content pane that holds all GUI components
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		/*Declare and Initialize a panel to contain lblLogin,lblUsename, lblPassword, usernameField, passwordField
		userTypeComboBox and tglbtnLogin*/
		JPanel loginPanel = new JPanel();
		//Add the created panel to contentPane
		contentPane.add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login Type:");
		lblLogin.setBounds(35, 17, 76, 14);
		loginPanel.add(lblLogin);
		
		JLabel lblUsename = new JLabel("Username:");
		lblUsename.setBounds(35, 59, 76, 14);
		loginPanel.add(lblUsename);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(35, 83, 83, 14);
		loginPanel.add(lblPassword);
		
		usernameField = new JTextField();//User will type their username here
		usernameField.setBounds(112, 56, 127, 20);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();//User will type their password here
		passwordField.setBounds(112, 81, 127, 20);
		loginPanel.add(passwordField);
		
		userTypeComboBox = new JComboBox();//User will select their user types here to determine security level.
		userTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"---Select User---", "Business Owner", "Customer"}));
		userTypeComboBox.setBounds(112, 14, 127, 20);
		loginPanel.add(userTypeComboBox);
		
		JToggleButton tglbtnLogin = new JToggleButton("Login"); 
		/*This will authenticate users by matching the typed details in respective files and either create CustomerPanelGUI object or BusinessOwnerPanel object*/
		tglbtnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				if(userTypeComboBox.getSelectedIndex() == 0){//If the selected item is the first in the list the application will ask user to select Login Type
					JOptionPane.showMessageDialog(null, "Please select Login Type !!");//Message displayed when Login type is not selected.
					return;
				}else{
					String File=""; //Declare file to read user data for login.
			        String Password;
			        String UserName ="";
			        
			        if(userTypeComboBox.getSelectedIndex() == 1){ //If Login type selected is "Customer" File to read will be "business.txt"
			        	File = "business.txt";
			        	OwnerFlag=true;            //true due to owner entry
	                    CustomerFlag = false;     //false due to non customer entry
			        }
			        else{
			        	File = "customerinfo.txt"; 
			        	OwnerFlag=false;         //false due to non owner entry
	                    CustomerFlag = true;    //true due to customer entry
			        }
			        
			        UserName = usernameField.getText().trim(); //Set UserName to details type in usernameField by user.
			        Password = passwordField.getText().trim();//Set Password to details type in passwordField by user.
			        if (UserName.equals("") || Password.equals("")){ //Verify that both username and password were entered by user.
			        	JOptionPane.showMessageDialog(null, "Please enter both username and password !!");
			        	return; //Execution stops in case either username or password or both were not entered
			        }else{
			        	try //Start try catch to handle IOException for reading the File.
			            {
			                String line="",arr[];
			                FileReader fr=new FileReader(File); // Open file read mode
			                BufferedReader br=new BufferedReader(fr); //Read file content line by line
			                while((line=br.readLine())!=null) //Read only lines that are not null and leave the null lines
			                {
			                    arr=line.split(",");    //Separate the words read in the line by a comma and add these words in a String array.
			                    //The array contains firstName, lastName, phone, address, username and password in this order
			                    if(arr[4].equals(UserName.toLowerCase())) //Check whether the fifth array item matches the username entered by user
			                    {
			                        if(arr[5].equals(Password))  //Check whether the sixth array item matches the password entered by user
			                        {
			                           if(OwnerFlag==true){ //If Login Type selected was Business Owner
			                        	    authenticFlag = true;
			                            	new BusinessOwnerPanel(arr).setVisible(true);
			                                dispose();
			                                break; //break out of loop since user has been found
			                            }
			                            else if(CustomerFlag == true){//If Login Type selected was Customer
			                            	authenticFlag = true;
			                            	new CustomerPanelGUI(arr).setVisible(true); 
			                                dispose();
			                                break; //break out of loop since user has been found
			                            }            
			                        }
			                    }
			                }
			                //authenticFlag is still false if no matches found therefore username or password entered are wrong
			                if(authenticFlag == false){
			                	JOptionPane.showMessageDialog(null, "Wrong username or password.");
			                }
			            }catch(IOException e){
			            	logger.log(Level.SEVERE, e.getMessage());
			            }
			        	
			        	
			        }
			        
				}
			}
		});
		tglbtnLogin.setBounds(150, 140, 90, 23);
		loginPanel.add(tglbtnLogin);
		
		//Declare and initialize a button to cancel login form.
		JToggleButton tglbtnExit = new JToggleButton("Cancel");
		tglbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Main();
				dispose(); // Close login form and go back to Main form
			}
		});
		tglbtnExit.setBounds(35, 140, 90, 23);
		loginPanel.add(tglbtnExit);
	}
	
}
