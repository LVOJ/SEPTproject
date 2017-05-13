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

public class Login extends JFrame {
	private static Logger logger = Logger.getLogger(CustomerPanelGUI.class.getName());
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JComboBox comboBox;
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
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login Type:");
		lblLogin.setBounds(35, 17, 76, 14);
		panel_2.add(lblLogin);
		
		JLabel lblUsename = new JLabel("Username:");
		lblUsename.setBounds(35, 59, 76, 14);
		panel_2.add(lblUsename);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(35, 83, 83, 14);
		panel_2.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(112, 56, 127, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(112, 81, 127, 20);
		panel_2.add(passwordField);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"---Select User---", "Business Owner", "Customer"}));
		comboBox.setBounds(112, 14, 127, 20);
		panel_2.add(comboBox);
		
		JToggleButton tglbtnLogin = new JToggleButton("Login");
		tglbtnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				if(comboBox.getSelectedIndex() == 0){
					JOptionPane.showMessageDialog(null, "Please select Login Type !!");
					return;
				}else{
					String File="";
			        String Password;
			        String UserName ="";
			        
			        if(comboBox.getSelectedIndex() == 1){
			        	File = "business.txt"; 
			        	OwnerFlag=false;         //false due to owner entry
	                    CustomerFlag = true;
			        }
			        else{
			        	File = "customerinfo.txt";
			        	OwnerFlag=true;            //false due to non owner
	                    CustomerFlag = false;
			        }
			        
			        UserName = textField.getText().trim();
			        Password = passwordField.getText().trim();
			        if (UserName.equals("") || Password.equals("")){
			        	JOptionPane.showMessageDialog(null, "Please enter both username and password !!");
			        	return;
			        }else{
			        	try
			            {
			                String line="",arr[];
			                FileReader fr=new FileReader(File);
			                BufferedReader br=new BufferedReader(fr);
			                while((line=br.readLine())!=null)
			                {
			                    arr=line.split(",");       
			                    if(arr[4].equals(UserName.toLowerCase())) 
			                    {
			                        if(arr[5].equals(Password))  
			                        {
			                           if(OwnerFlag==true){
			                        	    authenticFlag = true;
			                                new CustomerPanelGUI(arr).setVisible(true);
			                                dispose();
			                                break;
			                            }
			                            else if(CustomerFlag == true){
			                            	authenticFlag = true;
			                            	new BusinessOwnerPanel(arr).setVisible(true);
			                                dispose();
			                                break;
			                            }            
			                        }
			                    }
			                }
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
		panel_2.add(tglbtnLogin);
		
		JToggleButton tglbtnExit = new JToggleButton("Cancel");
		tglbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Main().setVisible(true);
				dispose();
			}
		});
		tglbtnExit.setBounds(35, 140, 90, 23);
		panel_2.add(tglbtnExit);
	}
	
}
