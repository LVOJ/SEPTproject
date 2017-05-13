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

public class BusinessHours extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	String[] userData;
	private JTextField textField_5;
	private JTextField textField_4;
	private JTextField textField_3;
	
	public BusinessHours(JFrame frame, String[] userData) {
		super(frame, true);
		this.userData = userData;
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setTitle("Business Working Hours");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BusinessHours.class.getResource("/icon.png")));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblBusinessName = new JLabel("Monday");
		lblBusinessName.setBounds(10, 11, 162, 25);
		contentPanel.add(lblBusinessName);
		
		JLabel lblBusinessLocation = new JLabel("Tuesday");
		lblBusinessLocation.setBounds(10, 39, 162, 25);
		contentPanel.add(lblBusinessLocation);
		
		JLabel lblBusinessTelephone = new JLabel("Wednesday");
		lblBusinessTelephone.setBounds(10, 75, 151, 25);
		contentPanel.add(lblBusinessTelephone);
		
		textField = new JTextField();
		textField.setBounds(128, 13, 217, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(128, 41, 217, 20);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(128, 77, 217, 20);
		contentPanel.add(textField_2);
		
		JLabel lblSaturday = new JLabel("Saturday");
		lblSaturday.setBounds(10, 169, 151, 25);
		contentPanel.add(lblSaturday);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(128, 171, 217, 20);
		contentPanel.add(textField_5);
		
		JLabel lblFriday = new JLabel("Friday");
		lblFriday.setBounds(10, 133, 162, 25);
		contentPanel.add(lblFriday);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(128, 135, 217, 20);
		contentPanel.add(textField_4);
		
		JLabel lblThursday = new JLabel("Thursday");
		lblThursday.setBounds(10, 105, 162, 25);
		contentPanel.add(lblThursday);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(128, 107, 217, 20);
		contentPanel.add(textField_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String Monday = textField.getText().trim();
						String Tuesday = textField_1.getText().trim();
						String Wednesday = textField_2.getText().trim();
						String Thursday = textField_3.getText().trim();
						String Friday = textField_4.getText().trim();
						String Saturday = textField_5.getText().trim();
						String businessOwner = userData[4];
						if(Monday.equals("") || Tuesday.equals("") || Wednesday.equals("") || Thursday.equals("") 
								|| Friday.equals("") || Saturday.equals("")){
							JOptionPane.showMessageDialog(null, "Enter Business Hours To Proceed");
							return;
						}
						ArrayList<String> hoursList = new ArrayList<>();
						hoursList.add("monday,"+Monday+","+businessOwner);
						hoursList.add("tuesday,"+Tuesday+","+businessOwner);
						hoursList.add("wednesday,"+Wednesday+","+businessOwner);
						hoursList.add("thursday,"+Thursday+","+businessOwner);
						hoursList.add("friday,"+Friday+","+businessOwner);
						hoursList.add("saturday,"+Saturday+","+businessOwner);
						try{
				        	BufferedWriter bw=new BufferedWriter(new FileWriter("businesshours.txt"));
				        	for(int i = 0; i < hoursList.size(); i++){
				        		bw.write(hoursList.get(i));
					        	bw.newLine();
					        }
				        	JOptionPane.showMessageDialog(null, "Business Details Saved Successfully");
				        	bw.close();
						}catch(Exception e2){
							e2.printStackTrace();
						}
					dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		fillBusinessHours();
	}
	private void fillBusinessHours(){
		ArrayList<String> businessHours = getBusinessDetails(userData);
		if(businessHours.size() > 5){
			textField.setText(businessHours.get(0));
			textField_1.setText(businessHours.get(1));
			textField_2.setText(businessHours.get(2));
			textField_3.setText(businessHours.get(3));
			textField_4.setText(businessHours.get(4));
			textField_5.setText(businessHours.get(5));
		}
	}
	public ArrayList<String> getBusinessDetails(String[] userData){
		String[] businessData = null;
		ArrayList<String> businessHours = new ArrayList<>();
		try{
        	BufferedReader br = new BufferedReader(new FileReader("businesshours.txt"));
	        String line = "";
	        while((line=br.readLine())!=null){
	        	String recs[] = line.split(",");
	        	if(recs[2].equals(userData[4])){
	        		businessData = line.split(",");
	        		businessHours.add(businessData[1]);
	        	}
	        }
	        br.close();
	        return businessHours;
		}catch(Exception e2){
			e2.printStackTrace();
			return null;
		}
	}
	
}
