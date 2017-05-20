package business;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * 
 This is the class that Business Owner uses to input business details : Businness Name, 
 *
 */
public class BusinessName extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField BusinessNameTextField;
	private JTextField BusLocationTextField;
	private JTextField BusinessTelTextFied;
	String[] userData;
	
	/**
	 * create Jdialog
	 */
	public BusinessName(JFrame frame, String[] userData) {
		super(frame, true);
		this.userData = userData;
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setTitle("Business Details");
		//Display an icon image in the window.
		setIconImage(Toolkit.getDefaultToolkit().getImage(BusinessName.class.getResource("/icon.png")));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblBusinessName = new JLabel("Business Name:");
		lblBusinessName.setBounds(46, 65, 162, 25);
		contentPanel.add(lblBusinessName);
		
		JLabel lblBusinessLocation = new JLabel("Business Location:");
		lblBusinessLocation.setBounds(46, 105, 162, 25);
		contentPanel.add(lblBusinessLocation);
		
		JLabel lblBusinessTelephone = new JLabel("Business Telephone");
		lblBusinessTelephone.setBounds(46, 141, 151, 25);
		contentPanel.add(lblBusinessTelephone);
		
		BusinessNameTextField = new JTextField(); // Field where the Business Owner will input the business name
		BusinessNameTextField.setBounds(164, 67, 217, 20);
		contentPanel.add(BusinessNameTextField);
		BusinessNameTextField.setColumns(10);
		
		BusLocationTextField = new JTextField(); //Field where the Business Owner will input the business location
		BusLocationTextField.setColumns(10);
		BusLocationTextField.setBounds(164, 107, 217, 20);
		contentPanel.add(BusLocationTextField);
		
		BusinessTelTextFied = new JTextField();//Field where the Business Owner will input the business telephone number
		BusinessTelTextFied.setColumns(10);
		BusinessTelTextFied.setBounds(164, 143, 217, 20);
		contentPanel.add(BusinessTelTextFied);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				//Declare and initialize a button to save the business details
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String businessName = BusinessNameTextField.getText().trim();
						String businessLocation = BusLocationTextField.getText().trim();
						String businessTelephone = BusinessTelTextFied.getText().trim();
						String businessOwner = userData[4];
						
						//String containing business details as they will be saved in the file.
						String details = businessName+","+businessLocation+","+businessTelephone+","+businessOwner;
						
						//Check that business details were entered : Name, Location and Telephone
						if(businessName.equals("") || businessLocation.equals("") || businessTelephone.equals("")){
							JOptionPane.showMessageDialog(null, "Enter Business Name and Details");
							return;
						}
						
						try{
							 //Open the file containing business details for read mode and give access to BufferedReader
				        	BufferedReader br = new BufferedReader(new FileReader("businessinfo.txt"));
				        	
				        	int count = 0; // Initialize counter that will determine if business details exists
				        	
				        	ArrayList<String> list = new ArrayList<>(); //List to contain the lines read by BufferedReader
				        	String line = "";
				        	while((line=br.readLine())!=null){
					        	list.add(line);
					        	count++; // Increase count if the line read is not null but contains business info
					        }
				        	br.close();
				        	//If count is greater than zero that means that business details exists and the details will be updated
					        if(count > 0){
					        	//Open the file containing business details for write mode and give access to BufferedWriter
						        BufferedWriter bw = new BufferedWriter(new FileWriter("businessinfo.txt"));
								for(int i = 0; i < list.size(); i++){
						        	String recs[] = list.get(i).split(",");
						        	if(recs[3].equals(userData[4])){ // if the name of business owner from  business details read matches the business owner logged in the system
						        		bw.write(details); //write new details
							        	bw.newLine();
						        	}else{
						        		bw.write(list.get(i)); //write the old details because no changes were made here
							        	bw.newLine();
						        	}
						        }
					        	bw.close();
					        	JOptionPane.showMessageDialog(null, "Business Details Updated Successfully");
					        }else{ // The counter is still zero and this means business details did not exist in the file and therefore they will be inserted.

					        	//Open the file containing business details for write mode and give access to BufferedWriter
					        	BufferedWriter bw = new BufferedWriter(new FileWriter("BusinessInfo.txt"));
					        	bw.write(details); // write the business details
					        	bw.newLine();
					        	JOptionPane.showMessageDialog(null, "Business Details Saved Successfully");
					        	bw.close();
					        }
				        	
						}catch(Exception e2){
							e2.printStackTrace();
						}
					dispose();
					}
				});
				okButton.setActionCommand("OK"); //This button will perform when ENTER key is pressed 
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);//This button will perform when ENTER key is pressed 
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose(); //Close the business details entry form
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		//Call method that sets the details in appropriate textfields
		fillBusinessData();
	}
	private void fillBusinessData(){
		
		String[] businessData = getBusinessDetails(userData); // Set businessData array to details return by this method
		if(businessData != null){ //Here businessData array should contain some details
			BusinessNameTextField.setText(businessData[0]); // Fisrt array should contain business name
			BusLocationTextField.setText(businessData[1]);// Second array should contain business Location
			BusinessTelTextFied.setText(businessData[2]);// Third array should contain business telephone
		}
	}
	
	//Method that reads business details from "BusinessInfo.txt" and return a string array
	public String[] getBusinessDetails(String[] userData){
		String[] businessData = null;
		try{
        	BufferedReader br = new BufferedReader(new FileReader("BusinessInfo.txt")); //Open file in read mode
	        String line = "";
	        while((line=br.readLine())!=null){
	        	String recs[] = line.split(",");
	        	if(recs[3].equals(userData[4])){// if the name of business owner from  business details read matches the business owner logged in the system
	        		businessData = line.split(","); //Separate the line read by comma and add the splits in array.
	        	}
	        }
	        br.close();//Close  BufferedReader to release file
	        return businessData;
		}catch(Exception e2){
			e2.printStackTrace();
			return null;
		}
	}
	
}
