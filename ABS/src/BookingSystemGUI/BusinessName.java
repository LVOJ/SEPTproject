package BookingSystemGUI;

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

public class BusinessName extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	String[] userData;
	
	public BusinessName(JFrame frame, String[] userData) {
		super(frame, true);
		this.userData = userData;
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setTitle("Business Details");
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
		
		textField = new JTextField();
		textField.setBounds(164, 67, 217, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(164, 107, 217, 20);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(164, 143, 217, 20);
		contentPanel.add(textField_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String businessName = textField.getText().trim();
						String businessLocation = textField_1.getText().trim();
						String businessTelephone = textField_2.getText().trim();
						String businessOwner = userData[4];
						String details = businessName+","+businessLocation+","+businessTelephone+","+businessOwner;
						if(businessName.equals("") || businessLocation.equals("") || businessTelephone.equals("")){
							JOptionPane.showMessageDialog(null, "Enter Business Name and Details");
							return;
						}
						
						try{
				        	BufferedReader br = new BufferedReader(new FileReader("businessinfo.txt"));
				        	int count = 0;
				        	ArrayList<String> list = new ArrayList<>();
				        	String line = "";
				        	while((line=br.readLine())!=null){
					        	list.add(line);
					        	count++;
					        }
				        	br.close();
					        if(count > 0){
						        BufferedWriter bw = new BufferedWriter(new FileWriter("businessinfo.txt"));
								for(int i = 0; i < list.size(); i++){
						        	String recs[] = list.get(i).split(",");
						        	if(recs[3].equals(userData[4])){
						        		bw.write(details);
							        	bw.newLine();
						        	}else{
						        		bw.write(list.get(i));
							        	bw.newLine();
						        	}
						        }
					        	bw.close();
					        	JOptionPane.showMessageDialog(null, "Business Details Updated Successfully");
					        }else{
					        	BufferedWriter bw = new BufferedWriter(new FileWriter("BusinessInfo.txt"));
					        	bw.write(details);
					        	bw.newLine();
					        	JOptionPane.showMessageDialog(null, "Business Details Saved Successfully");
					        	bw.close();
					        }
				        	//services();
				        	
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
		fillBusinessData();
	}
	private void fillBusinessData(){
		String[] businessData = getBusinessDetails(userData);
		if(businessData != null){
			textField.setText(businessData[0]);
			textField_1.setText(businessData[1]);
			textField_2.setText(businessData[2]);
		}
	}
	public String[] getBusinessDetails(String[] userData){
		String[] businessData = null;
		try{
        	BufferedReader br = new BufferedReader(new FileReader("BusinessInfo.txt"));
	        String line = "";
	        while((line=br.readLine())!=null){
	        	String recs[] = line.split(",");
	        	if(recs[3].equals(userData[4])){
	        		businessData = line.split(",");
	        	}
	        }
	        br.close();
	        return businessData;
		}catch(Exception e2){
			e2.printStackTrace();
			return null;
		}
	}
	
}
