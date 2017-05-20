package business;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import utility.BookAppointmentFactory;
import utility.Utils;
import account.Login;

/**
 * 
 Class that provides Business Owner Dashboard
 *
 */
public class BusinessOwnerPanel extends JFrame {

	//Declare private variables that will be used in this class
	private JPanel contentPane;
	private JTable table;
	private JPanel bookingsummaries;
	private JTextField newServiceTextField;
	private JPanel newservice;
	private JPanel deleteservice;
	private JComboBox servicedeleteCombo;
	private JTable empTable;
	private JComboBox EmpAvailableSelctService;
	private JPanel empAvailable;
	private JPanel updateEmpWaorkingTime;
	private JComboBox empNamesWorkingTime;
	private JPanel empWorkingTime;
	private JTable DurationTable;
	private JComboBox comboBoxService;
	private JComboBox EmployeeComboBox;
	private JComboBox selectService;
	private JComboBox selectActivity;
	private JComboBox selectDay;
	private JComboBox selectEmp;
	private JTable bookingAvailableSlotsTable;
	private JTextField selectCustomer;
	private JPanel bookForCustomer;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> temp = new ArrayList<>();
	String[] userData;
	private Image img;
	private JLabel businessTitle;
	private JComboBox ComboBoxServiceName;
	private JComboBox deleteActivityComboBox;
	private JPanel addActivity;
	private JComboBox ServiceToAddActivity;
	private FileChannel chanel;
	private FileLock lock;
	private JComboBox StartTimeComboBox;
	private JComboBox EndTimeComboBox;
	private JComboBox selectServiceToAddEmpTime;
	private ArrayList<String> daysList = new ArrayList<>();
	private JComboBox empAvailSelectDay;
	private JComboBox SelectDurationComboBox;
	private JComboBox StarTimeComboBox1;
	private JComboBox EndTimeComboBox1;
	private JTextField activityNameField;
	private JTable activitySelectTable;

	/**
	 * 
	 create frame
	 */
	public BusinessOwnerPanel(String[] userData) {
		setResizable(false);
		this.userData = userData;

		//Business working days in a list
		daysList.add("monday");
		daysList.add("tuesday");
		daysList.add("wednesday");
		daysList.add("thursday");
		daysList.add("friday");

		setTitle("Appointment Booking System");
		//Display an icon image in the window.
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				BusinessOwnerPanel.class.getResource("/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 517);
		setLocationRelativeTo(null); // Set window location centered in the  screen

		//Menu bar for navigation to Business details registration or Business Working Hours
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(102, 204, 102));
		setJMenuBar(menuBar);

		JMenu mnBusiness = new JMenu("Business");
		menuBar.add(mnBusiness);

		//This will navigate business owner to Business details registration
		JMenuItem mntmDetails = new JMenuItem("Details");
		mntmDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BusinessName(BusinessOwnerPanel.this, userData)
						.setVisible(true);
				businessTitle.setText(fillBusinessData());
			}
		});
		mnBusiness.add(mntmDetails);

		//This will navigate business owner to Business Working Hours setting
		JMenuItem mntmWorkingHours = new JMenuItem("Working Hours");
		mntmWorkingHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BusinessHours(BusinessOwnerPanel.this, userData)
						.setVisible(true);
			}
		});
		mnBusiness.add(mntmWorkingHours);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		//Panel to show business details
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JLabel lblNewLabel_1 = new JLabel();
		img = new ImageIcon(this.getClass().getResource("/icon.png"))
				.getImage();//Display the icon image 
		lblNewLabel_1.setIcon(new ImageIcon(img));
		Image bi;
		try {
			bi = null;
			bi = ImageIO.read(this.getClass().getResource("/icon.png"));
			lblNewLabel_1.setIcon(new ImageIcon(bi
					.getScaledInstance(50, 36, 36)));//Set size for the image to fit on the JLabel
		} catch (Exception e) {

		}
		panel.add(lblNewLabel_1);

		businessTitle = new JLabel(fillBusinessData());//Label to show business details
		businessTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(businessTitle);
		
		
		//This panel will contain all the buttons that will display the appropriate panels according to the activity the business owner wants to perform
		JPanel leftPanelForNav = new JPanel();
		leftPanelForNav.setPreferredSize(new Dimension(200, 10));
		leftPanelForNav.setBackground(new Color(192, 192, 192));
		contentPane.add(leftPanelForNav, BorderLayout.WEST); //Float the panel to the left side of the window
		leftPanelForNav.setLayout(null);

		//This will display a panel to define Employee Working Time by entering all the required activity details
		JButton btnNewEmployeeWorking = new JButton("Employee Working Time");
		btnNewEmployeeWorking.setForeground(Color.BLACK); //Text color in this button
		btnNewEmployeeWorking.setBackground(SystemColor.activeCaption); //Button color
		btnNewEmployeeWorking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(empWorkingTime); //Display this panel and hide other panels
			}
		});
		btnNewEmployeeWorking.setBounds(10, 130, 180, 23);
		leftPanelForNav.add(btnNewEmployeeWorking);
		
		//This will display a panel for Viewing Booking Summaries made by customers or business owner on behalf of the customers
		JButton btnViewBookingSummaries = new JButton("Show Booking Summaries");
		btnViewBookingSummaries.setForeground(Color.BLACK);
		btnViewBookingSummaries.setBackground(SystemColor.activeCaption);
		btnViewBookingSummaries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(bookingsummaries); //Display this panel and hide other panels
				try {
					ArrayList<String> bookings = new OwnerUtils().ShowBookingSummaries(); //List with all booking records
					
					DefaultTableModel model = (DefaultTableModel) table
							.getModel(); //declare a model for the model
					Object[] rowData = new Object[6]; //This table should have 6 colums
					model.setRowCount(0);

					String[] recs = null;
					for (int i = 0; i < bookings.size(); i++) { //Loop through each list item in the bookings ArrayList
						recs = bookings.get(i).split(",");  //Separate the current list item with comma and add the splits in a string array
						/*
						 * Set the string arrays to the appropriate columns in the table
						 */
						rowData[0] = recs[1] + " " + recs[2];
						rowData[1] = recs[4];
						rowData[2] = recs[5];
						rowData[3] = recs[6];
						rowData[4] = recs[7];
						rowData[5] = recs[8];
						model.addRow(rowData); //Add the row to the table
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnViewBookingSummaries.setBounds(10, 290, 180, 23);
		leftPanelForNav.add(btnViewBookingSummaries);

		//This will display a panel to register a new service to the business by entering the service name
		JButton btnNewService = new JButton("New Service");
		btnNewService.setForeground(Color.BLACK);
		btnNewService.setBackground(SystemColor.activeCaption);
		btnNewService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(newservice);//Display this panel and hide other panels
			}
		});
		btnNewService.setBounds(10, 11, 180, 23);
		leftPanelForNav.add(btnNewService);

		//This will display a panel to register a new service to the business by entering the service name
		JButton btnDeleteService = new JButton("Delete Service");
		btnDeleteService.setBackground(SystemColor.activeCaption);
		btnDeleteService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(deleteservice);//Display this panel and hide other panels
			}
		});
		btnDeleteService.setBounds(10, 57, 180, 23);
		leftPanelForNav.add(btnDeleteService);

		//Declare and initialize a button that will enable the business owner to logout (Close business Owner dashboard)
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setBackground(SystemColor.activeCaption);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true); //Direct the user to login form after logout
				dispose(); //Close the window after logout
			}
		});
		btnLogout.setBounds(10, 373, 180, 23);
		leftPanelForNav.add(btnLogout);
		
		//This will display a panel to delete an existing service by selecting the service name
		JButton btnEmployeesAvailable = new JButton("Employees Available");
		btnEmployeesAvailable.setForeground(Color.BLACK);
		btnEmployeesAvailable.setBackground(SystemColor.activeCaption);
		btnEmployeesAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(empAvailable);//Display this panel and hide other panels
			}
		});
		btnEmployeesAvailable.setBounds(10, 210, 180, 23);
		leftPanelForNav.add(btnEmployeesAvailable);

		//This will display a panel to add a activity to a service
		JButton btnAddActivityTime = new JButton("Add Service Activity");
		btnAddActivityTime.setForeground(Color.BLACK);
		btnAddActivityTime.setBackground(SystemColor.activeCaption);
		btnAddActivityTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(addActivity);//Display this panel and hide other panels
			}
		});
		btnAddActivityTime.setBounds(10, 91, 180, 23);
		leftPanelForNav.add(btnAddActivityTime);

		//This will display a panel to update working time
		JButton btnUpdateWorkingTime = new JButton("Update Working Time");
		btnUpdateWorkingTime.setForeground(Color.BLACK);
		btnUpdateWorkingTime.setBackground(SystemColor.activeCaption);
		btnUpdateWorkingTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(updateEmpWaorkingTime);//Display this panel and hide other panels
			}
		});
		btnUpdateWorkingTime.setBounds(10, 170, 180, 23);
		leftPanelForNav.add(btnUpdateWorkingTime);

		//This will display a panel to book for a customer
		JButton btnBookForCustomer = new JButton("Book For Customer");
		btnBookForCustomer.setForeground(Color.BLACK);
		btnBookForCustomer.setBackground(SystemColor.activeCaption);
		btnBookForCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(bookForCustomer);//Display this panel and hide other panels

			}
		});
		btnBookForCustomer.setBounds(10, 250, 180, 23);
		leftPanelForNav.add(btnBookForCustomer);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		newservice = new JPanel(); //Panel to register a new service
		newservice.setBounds(0, 0, 557, 350);
		panel_2.add(newservice);
		newservice.setLayout(null);

		deleteservice = new JPanel(); //Panel to delete a service
		deleteservice.setVisible(false);
		deleteservice.setBounds(0, 0, 557, 350);
		panel_2.add(deleteservice);
		deleteservice.setLayout(null);

		addActivity = new JPanel();//Panel to add Activities for a service
		addActivity.setVisible(false);
		addActivity.setBounds(0, 0, 557, 407);
		panel_2.add(addActivity);
		addActivity.setLayout(null);

		empWorkingTime = new JPanel(); //Panel to assign employees task and working time by selecting activities they will be responsible for
		empWorkingTime.setVisible(false);
		empWorkingTime.setBounds(0, 0, 557, 396);
		empWorkingTime.setLayout(null);
		panel_2.add(empWorkingTime);

		updateEmpWaorkingTime = new JPanel();//Panel to update employees activities and working time
		updateEmpWaorkingTime.setVisible(false);
		updateEmpWaorkingTime.setBounds(0, 0, 557, 421);
		panel_2.add(updateEmpWaorkingTime);
		updateEmpWaorkingTime.setLayout(null);

		empAvailable = new JPanel(); //Panel to show all the available employees
		empAvailable.setVisible(false);
		empAvailable.setBounds(0, 0, 557, 362);
		panel_2.add(empAvailable);
		empAvailable.setLayout(null);

		bookingsummaries = new JPanel();//Panel to show all employees who have been booked to perform an activity
		bookingsummaries.setVisible(false);
		bookingsummaries.setBounds(0, 0, 557, 373);
		panel_2.add(bookingsummaries);
		bookingsummaries.setLayout(null);

		bookForCustomer = new JPanel();// Panel where the business will be able to book an appointment on behalf of a customer
		bookForCustomer.setVisible(false);
		bookForCustomer.setBounds(0, 0, 557, 407);
		panel_2.add(bookForCustomer);
		bookForCustomer.setLayout(null);

		/**
		 newservice panel components 
		 */

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_6.setBounds(0, 0, 557, 24);
		newservice.add(panel_6);

		JLabel lblNewServiceRegistration = new JLabel(
				"New Service Registration");
		panel_6.add(lblNewServiceRegistration);

		newServiceTextField = new JTextField(); //Textfield to provide service name when adding a new service
		newServiceTextField.setText("");
		newServiceTextField.setBounds(159, 138, 147, 20);
		newservice.add(newServiceTextField);
		newServiceTextField.setColumns(10);

		JLabel lblServiceName = new JLabel("Service Name");
		lblServiceName.setBounds(42, 140, 107, 17);
		newservice.add(lblServiceName);

		//Declare and initialize a button to register a new service into the appointment booking system
		JButton btnSave = new JButton("Register Service");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serviceName = newServiceTextField.getText().trim().toLowerCase();
				//Check whether the user has provided a service name and if not display a message that a name should be provided
				if (serviceName.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Service Name");
					return;
				}
				boolean serviceRegistred = false; // A boolean to tell if the service has been registered successfully
				//if the service is successfully rgisterd the serviceRegistredwill be true
				serviceRegistred = new OwnerUtils().newService(serviceName);
				
				//If the service has been registered successfully display a success message to the user
				if(serviceRegistred == true){
					JOptionPane.showMessageDialog(null, "Service created");
					dispose();
					new BusinessOwnerPanel(userData).setVisible(true);
				}else{
					//Here the service was not registred so the user has to repeat the process.
					JOptionPane.showMessageDialog(null, "There is an error in the process");
				}
			}
		});
		btnSave.setBounds(159, 169, 147, 23);
		newservice.add(btnSave);

		/**
		 * Delete service panel components
		 */
		JPanel serviceDeletePanel = new JPanel();
		serviceDeletePanel.setBackground(Color.LIGHT_GRAY);
		serviceDeletePanel.setBounds(0, 0, 557, 24);
		deleteservice.add(serviceDeletePanel);

		servicedeleteCombo = new JComboBox();// Dropdown to select the service to delete
		servicedeleteCombo.setModel(new DefaultComboBoxModel(
				new String[] { "--Select Service--" }));
		servicedeleteCombo.setBounds(182, 35, 183, 20);
		deleteservice.add(servicedeleteCombo);

		//Declare and initialize a button to delete a service
		JButton btnDelete = new JButton("Delete Service");
		btnDelete.setBounds(182, 131, 183, 23);
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//Check whether the service to be deleted has been selected by the user
				if (servicedeleteCombo.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please Select Service To Delete"); //Tell the user to select the service t delete
					return;
				}
				try {
					//Set the selected service to a String variable
					String serviceName = servicedeleteCombo.getSelectedItem()
							.toString();

					// Delete service from services list
					BufferedReader reader = new BufferedReader(new FileReader(
							"services.txt")); //Open the file in read mode and give access to BufferedReader

					String lineToRemove = serviceName; //Set the linetoremove is equal to the selected service
					String currentLine;
					ArrayList<String> list = new ArrayList<>(); //List to contain all the read services
					while ((currentLine = reader.readLine()) != null) { //Loop through all the lines of the file leaving the null ones
						if (!currentLine.equals(lineToRemove)) { //Check whether the current line being read is equal to the selected service for deletion
							list.add(currentLine); // If the line is not equal to the selected service add it to the list

						}
					}
					BufferedWriter writer = new BufferedWriter(new FileWriter(
							"services.txt"));//Open the file in write mode and give access to BufferedWriter
					for (int a = 0; a < list.size(); a++) { // Loop through the list and writing the list items in the file
						writer.write(list.get(a)); //Write the list item
						writer.newLine();//Write a new line for the next item for ease of manipulation

					}
					writer.close(); //Close the BufferedWriter to release the file
					reader.close(); //Close the BufferedReader to release the file
					// Delete service from services list
					
					reader = new BufferedReader(new FileReader(
							"BookingSummaries.txt"));//Open the file in read mode and give access to BufferedReader

					lineToRemove = serviceName;
					list.clear();
					String[] recs;
					while ((currentLine = reader.readLine()) != null) { //Loop through all the lines of the file leaving the null ones
						recs = currentLine.split(",");// Separate the read line and add the in a String array
						if (!recs[6].equals(lineToRemove)) { //Check whether the seventh array item being read is equal to the selected service for deletion
							list.add(currentLine);// If the seventh array item is not equal to the selected service add it to the list
						}
					}

					writer = new BufferedWriter(new FileWriter(
							"BookingSummaries.txt"));//Open the file in write mode and give access to BufferedWriter

					for (int a = 0; a < list.size(); a++) {// Loop through the list and writing the list items in the file
						writer.write(list.get(a));//Write the list item
						writer.newLine();//Write a new line for the next item for ease of manipulation
					}
					writer.close(); //Close the BufferedWriter to release the file
					reader.close(); //Close the BufferedReader to release the file
					
					String fileName = serviceName + ".txt"; //Intialize the text file to be deleted
					File file = new File(fileName); //Access the file path
					chanel = new RandomAccessFile(file, "rw").getChannel(); //Assign the file to a file chanenel

					try {
						lock = chanel.tryLock(); //Try to lock the file so that it is not used by another program

					} catch (OverlappingFileLockException es) {
						closeLock();
					}
					closeLock(); //Call the method to release the file for deleting
					deleteFile(file); //Call the delete function to delete the file after it has been released
					// services();
					JOptionPane.showMessageDialog(null,
							"Service Deleted Successfully"); //Display a success message that the service has been deleted
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose(); //Close the window
				new BusinessOwnerPanel(userData).setVisible(true); //Open the window again to force a refresh
			}
		});
		deleteservice.add(btnDelete);
		
		JPanel activitydeletePanel = new JPanel();
		activitydeletePanel.setBackground(Color.LIGHT_GRAY);
		activitydeletePanel.setBounds(0, 185, 557, 24);
		deleteservice.add(activitydeletePanel);

		JLabel lblDeleteActivityHere = new JLabel("Delete Activity Here Here");
		activitydeletePanel.add(lblDeleteActivityHere);

		JLabel lblSelectService_2 = new JLabel("Select Service");
		lblSelectService_2.setBounds(21, 223, 151, 14);
		deleteservice.add(lblSelectService_2);

		JLabel lblSelectActivity = new JLabel("Select Activity");
		lblSelectActivity.setBounds(21, 273, 151, 14);
		deleteservice.add(lblSelectActivity);
		
		//Initialize a dropdown to select service with the activity the user wants to delete
		ComboBoxServiceName = new JComboBox();
		ComboBoxServiceName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If the selected item is not the first one call the method to display activities in this service
				if (ComboBoxServiceName.getSelectedIndex() != 0) {
					ArrayList<String> serviceNames = Utils
							.getActivities(ComboBoxServiceName.getSelectedItem()
									.toString()); //ArrayList with activity list from this service
					deleteActivityComboBox.removeAllItems(); // Remove existing items in this dropdown to add the read list
					deleteActivityComboBox.addItem("Select Activity");
					for (int a = 0; a < serviceNames.size(); a++) {  //Loop through each list item (activity) and display them in a dropdowm
						deleteActivityComboBox.addItem(serviceNames.get(a));
					}
				}
			}
		});
		ComboBoxServiceName.setBounds(182, 220, 183, 20);
		deleteservice.add(ComboBoxServiceName);
		
		//Initialize a dropdown to select the activity the user wants to delete
		deleteActivityComboBox = new JComboBox();
		deleteActivityComboBox.setBounds(182, 270, 183, 20);
		deleteservice.add(deleteActivityComboBox);
		
		//Declare and initialize a buttom to delete the activuty
		JButton btnDeleteActivity = new JButton("Delete Activity");
		btnDeleteActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//Check whether the user has selected the service containing the activity to delete
				if (ComboBoxServiceName.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Select Service Where to delete Activity");
					return;
				}
				//Check whether the user has selected the activity the user wants to delete
				if (deleteActivityComboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Select Activity to delete Activity");
					return;
				}
				String service = ComboBoxServiceName.getSelectedItem().toString(); // set service is equal to the service selected
				String activity = deleteActivityComboBox.getSelectedItem().toString();//Set activity equal to the activity selected

				String fileName = service + ".txt"; //Initialize a file to delete the activity from
				try {
					ArrayList<String> list = new ArrayList<>(); //List to contain the read lines from the fileName file
					BufferedReader br = new BufferedReader(new FileReader( //Open the file in read mode and give access to the BufferReader
							fileName));
					String currentLine = "";
					while ((currentLine = br.readLine()) != null) { //Loop through every line in the file that is not null and add the read lines into the list
						String[] recs = currentLine.split(","); //Separate the read line by comma and put the records in a String array
						if (!recs[2].equals(activity)) { //Check if the third item does not match the activity to delete
							list.add(currentLine); //Add the line into the list if the array item does not match the activity to delete
						}
					}
					br.close();// Close the BufferedReader TO release the file
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(//Open the file in write mode and give access to the BufferedWriter
							fileName));
					for (int a = 0; a < list.size(); a++) { //Loop through each item added in the list
						bw.write(list.get(a) //Write the list item in the file
								+ System.getProperty("line.separator")); // Add a newline to prepare for the next line to ease the manipulation of the file
					}
					bw.close();// Close the BufferedReader TO release the file
					/*
					 * now delete the activity from booked list too
					 */
					list.clear(); //Remove all the items added to the list to prapeare other items to be added
					br = new BufferedReader(new FileReader(
							"BookingSummaries.txt"));//Open the "BookingSummaries.txt" file in write mode and give access to the BufferedWriter
					currentLine = "";
					while ((currentLine = br.readLine()) != null) { //Loop through every line that is not null
						String[] recs = currentLine.split(",");//Separate the read line by comma and put the records in a String array
						if (!recs[6].equals(service)
								|| !recs[7].equals(activity)) { /*Check whether the seventh item in array matches the service selected by the user and the 8th
								matches the activity selected*/ 
							list.add(currentLine); //If service and activity do not match the records add the line in the list
						}
					}
					br.close();// Close the BufferedReader TO release the file
					bw = new BufferedWriter(new FileWriter(
							"BookingSummaries.txt"));//Open the "BookingSummaries.txt" file in write mode and give access to the BufferedWriter
					for (int a = 0; a < list.size(); a++) {//Loop through each item added in the list
						bw.write(list.get(a)//Write the list item in the file
								+ System.getProperty("line.separator"));// Add a newline to prepare for the next line to ease the manipulation of the file
					}
					bw.close();// Close the BufferedReader TO release the file
					
					
					list.clear(); //Remove all the items added to the list to prapeare other items to be added
					br = new BufferedReader(new FileReader(
							"serviceduration.txt"));//Open the "serviceduration.txt" file in write mode and give access to the BufferedWriter
					currentLine = "";
					while ((currentLine = br.readLine()) != null) { //Loop through every line that is not null
						String[] recs = currentLine.split(",");//Separate the read line by comma and put the records in a String array
						if (!recs[0].equals(service)
								|| !recs[1].equals(activity)) { /*Check whether the seventh item in array matches the service selected by the user and the 8th
								matches the activity selected*/ 
							list.add(currentLine); //If service and activity do not match the records add the line in the list
						}
					}
					br.close();// Close the BufferedReader TO release the file
					bw = new BufferedWriter(new FileWriter(
							"serviceduration.txt"));//Open the "serviceduration.txt" file in write mode and give access to the BufferedWriter
					for (int a = 0; a < list.size(); a++) {//Loop through each item added in the list
						bw.write(list.get(a)//Write the list item in the file
								+ System.getProperty("line.separator"));// Add a newline to prepare for the next line to ease the manipulation of the file
					}
					bw.close();// Close the BufferedReader TO release the file
					
					JOptionPane.showMessageDialog(null,
							"Activity Deleted Successfully"); //Display a success message that the activity has been deleted 
					
					//Read the remaining activities from the service and display them in a dropdowm
					if (ComboBoxServiceName.getSelectedIndex() != 0) {
						ArrayList<String> activityNames = Utils
								.getActivities(ComboBoxServiceName.getSelectedItem()
										.toString());
						deleteActivityComboBox.removeAllItems();//Remove all items from the dropdown
						deleteActivityComboBox.addItem("Select Activity");//Set the first item in the dropdowmn
						
						for (int a = 0; a < activityNames.size(); a++) {//Loop through every item in the list
							deleteActivityComboBox.addItem(activityNames.get(a));//Add the list item to the dropdown
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnDeleteActivity.setBounds(182, 316, 183, 23);
		deleteservice.add(btnDeleteActivity);
		
		/**
		 * Panel to add activity components
		 */
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(28, 192, 132, 14);
		addActivity.add(lblDuration);
		
		SelectDurationComboBox = new JComboBox();//Dropdown for the owner to select durtion for the activity
		SelectDurationComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select duration", "30 min", "60 min"}));
		SelectDurationComboBox.setBounds(167, 187, 191, 24);
		addActivity.add(SelectDurationComboBox);
		

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.LIGHT_GRAY);
		panel_13.setBounds(0, 0, 557, 24);
		addActivity.add(panel_13);

		JLabel lblAddServiceActivitieshere = new JLabel(
				"Add Service Activities Here");
		panel_13.add(lblAddServiceActivitieshere);

		ServiceToAddActivity = new JComboBox();//Dropdown for the owner to select service where to add activity
		ServiceToAddActivity.setBounds(173, 75, 185, 24);
		addActivity.add(ServiceToAddActivity);

		JLabel lblSelectService_3 = new JLabel("Select Service");
		lblSelectService_3.setBounds(27, 80, 136, 14);
		addActivity.add(lblSelectService_3);

		JLabel lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBounds(27, 138, 136, 14);
		addActivity.add(lblActivityName);
		
		activityNameField = new JTextField();//Field to enter activity name
		activityNameField.setBounds(173, 135, 185, 20);
		addActivity.add(activityNameField);
		activityNameField.setColumns(10);
		
		//Button to save activity
		JButton btnSaveActivities = new JButton("Save Activities");
		btnSaveActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ServiceToAddActivity.getSelectedIndex() == 0) {//Check whether the service where to add activity is selected
					JOptionPane.showMessageDialog(null,
							"Select Service Name to proceed");
					return;
				}
				if (SelectDurationComboBox.getSelectedIndex() == 0) {//Check whether the activity duration is sselected
					JOptionPane.showMessageDialog(null,
							"Select duration to proceed");
					return;
				}

				String service = ServiceToAddActivity.getSelectedItem().toString();//Initialize the service name with the selected service
				String fileName = service + ".txt";//Initialize a file for the service
				String activity = activityNameField.getText().toLowerCase().trim();//Initialize the activity with the activity entered by the user
				if(activity.equals("")){//Check whether activity was entered
					JOptionPane.showMessageDialog(null,
							"Fill activity name to proceed.");
					return;
				}
				
				int duration = 0;
				/*
				 Initialize duration according to the one selected by the user
				 */
				if (SelectDurationComboBox.getSelectedIndex() == 1) {
					duration = 30;
				}if (SelectDurationComboBox.getSelectedIndex() == 2) {
					duration = 60;
				}
				try {
					File file = new File(fileName);
					file.createNewFile();
					BufferedReader reader = new BufferedReader(new FileReader(fileName));//Open the file in read mode and give access to the BufferedReader
					String line = "", recs[] = null;
					while( (line = reader.readLine()) != null){//Loop through every line that is not null
						recs = line.split(",");//Separate the line with a comma and add the records to a string array
						if(recs[2].equals(activity)){//Check whether the activity entered exists
							JOptionPane.showMessageDialog(null, "The activity name entered exists. Please enter again.");
							return;
						}
					}
					reader.close();//Close the BufferedReader
					
					FileWriter fw = new FileWriter("serviceduration.txt", true);//Open the file in read mode
					BufferedWriter bw = new BufferedWriter(fw);//Give access to the BufferedWriter

					bw.write(service+","+activity+","+duration);//write the activity and its details in the file "serviceduration.txt"
					bw.newLine();//write a new line
					
					bw.close();//Close the BufferedWriter
					JOptionPane.showMessageDialog(null,
							"Activity Saved successfully");//Display a success message to the user
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSaveActivities.setForeground(Color.BLACK);
		btnSaveActivities.setBackground(SystemColor.activeCaption);
		btnSaveActivities.setBounds(178, 258, 180, 23);
		addActivity.add(btnSaveActivities);

		/**
		 * Employee working times panel components
		 */
		selectServiceToAddEmpTime = new JComboBox();//Drop down to select service  to add employee working time 
		selectServiceToAddEmpTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectServiceToAddEmpTime.getSelectedIndex() != 0) {
					try {
						activityTable(selectServiceToAddEmpTime.getSelectedItem().toString());//Method to display activities in this service
						employees(selectServiceToAddEmpTime.getSelectedItem().toString());//Methos to show employees 
					} catch (Exception e1) {
						// e.printStackTrace();
					}
				}
			}
		});
		selectServiceToAddEmpTime.setBounds(161, 59, 191, 24);
		empWorkingTime.add(selectServiceToAddEmpTime);


		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(161, 106, 191, 92);
		empWorkingTime.add(scrollPane_2);
		
		activitySelectTable = new JTable();//Table to select activities to add employee working time 
		activitySelectTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "Activity"//Set column names
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class//Set data types for the columns
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false//Set if columns can be editted by the user
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		activitySelectTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		activitySelectTable.getColumnModel().getColumn(0).setMaxWidth(50);
		scrollPane_2.setViewportView(activitySelectTable);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(22, 323, 125, 14);
		empWorkingTime.add(lblEmployeeName);

		empNamesWorkingTime = new JComboBox();//dropdown to enter or select the employee name
		empNamesWorkingTime.setEditable(true);
		empNamesWorkingTime.setBounds(161, 320, 191, 20);
		empWorkingTime.add(empNamesWorkingTime);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.LIGHT_GRAY);
		panel_10.setBounds(0, 0, 557, 24);
		empWorkingTime.add(panel_10);

		JLabel lblAssignEmployeeWorking = new JLabel(
				"Assign Employee Working Time");
		panel_10.add(lblAssignEmployeeWorking);

		JLabel label = new JLabel("Select Service");
		label.setBounds(22, 64, 116, 14);
		empWorkingTime.add(label);

		JLabel label_8 = new JLabel("Select Activity");
		label_8.setBounds(22, 106, 116, 14);
		empWorkingTime.add(label_8);

		JLabel lblStartinftime = new JLabel("Starting Time");
		lblStartinftime.setBounds(22, 213, 116, 14);
		empWorkingTime.add(lblStartinftime);

		StartTimeComboBox = new JComboBox();//Drop down to select starting time  for the employee working time
		StartTimeComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Start Time", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
				"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
				"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
				"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
				"23:30" }));
		StartTimeComboBox.setBounds(161, 210, 191, 24);
		empWorkingTime.add(StartTimeComboBox);

		EndTimeComboBox = new JComboBox();//Drop down to select ending time for the employee working time
		EndTimeComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Start Time", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
				"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
				"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
				"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
				"23:30" }));
		EndTimeComboBox.setBounds(161, 264, 191, 24);
		empWorkingTime.add(EndTimeComboBox);

		JLabel lblEndingTime = new JLabel("Ending time");
		lblEndingTime.setBounds(22, 269, 132, 14);
		empWorkingTime.add(lblEndingTime);
		
		//Declare and initialize the button to save the employee working times
		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (selectServiceToAddEmpTime.getSelectedIndex() == 0) {//Check whether the user has selected 
					JOptionPane.showMessageDialog(null,
							"Please select servicde");
					return;
				}
				if (StartTimeComboBox.getSelectedIndex() == 0) {//Check whether the user has selected starting time
					JOptionPane.showMessageDialog(null,
							"Please select starting time");
					return;
				}
				if (EndTimeComboBox.getSelectedIndex() == 0) {//Check whether the user has selected ending tiome
					JOptionPane.showMessageDialog(null,
							"Please select ending time");
					return;
				}
				if (empNamesWorkingTime.getSelectedItem().toString().trim().equals("")
						|| empNamesWorkingTime.getSelectedItem().toString().trim()
								.equals("null")) {//Check whether the user has selected or entered empoyee name and it is valid
					JOptionPane.showMessageDialog(null,
							"Enter Employee name to proceed");
					return;
				}
				/*
				 * Assign variables appropriate values
				 */
				String service = selectServiceToAddEmpTime.getSelectedItem().toString();
				String startTime = StartTimeComboBox.getSelectedItem().toString();
				String endTime = EndTimeComboBox.getSelectedItem().toString();
				String employee = empNamesWorkingTime.getSelectedItem().toString().toLowerCase();
				
				int rows = activitySelectTable.getRowCount();//Get the number of rows in the table
				
				Boolean selected = false;//Boolean will be true if the activity is selected
				ArrayList<HashMap<String, String>> activityToAdd = new ArrayList<>();//List to contain activities to be added
				for(int row = 0; row < rows; row++){//Loop through every row in the table
					selected = (Boolean) activitySelectTable.getModel().getValueAt(row, 0);//set boolean is equal to the value of the of 1st column
					String activity = activitySelectTable.getModel().getValueAt(row, 1).toString();////set activity is equal to the value of the of 2nd column
					if(selected == true){//Check if the boolean is true, if true the activity will be added for the employee
						int duration = 0;//Initialize the duration
						try{
							BufferedReader reader = new BufferedReader(new FileReader("serviceduration.txt"));//Open the file in read mode and give access to the BufferedReader 
							String line = "", recs[] = null;
							while( (line = reader.readLine()) != null ){//Loop through every line in the file that is not null
								recs = line.split(",");//Separate the line with a comma and add the records in a string array
								if(recs[0].equals(service) && recs[1].equals(activity)){//Check if the 1st array item equals to service selected and if the 2nd item equals to the activity
									duration = Integer.parseInt(recs[2]);//Set duration is equal to the 3rd array item
									break; //End loop since we have found the duration for the activity
								}
							}
							reader.close();//Close the BufferedReader
						}catch(Exception e){
							e.printStackTrace();
						}
						
						HashMap<String, String> recordMap = new HashMap<>();//Initialize a hashmap to put activity details
						/*
						 * Put the values and appropriate keys in the hashmap
						 */
						recordMap.put("service", service);
						recordMap.put("activity", activity);
						recordMap.put("startTime", startTime);
						recordMap.put("endTime", endTime);
						recordMap.put("employee", employee);
						recordMap.put("duration", String.valueOf(duration));
						
						activityToAdd.add(recordMap); //Add the hashmap tho the list, the hashmap contains activity details
					}
				}
				
				
				Boolean timeRegistered = false; //Boolean will be true if all items in the activityToAddare added, working time for the employee
				OwnerUtils owner = new OwnerUtils();
				timeRegistered = owner.empWorkingTime(activityToAdd, daysList);//Call the method to add employee working time which will return a boolean
				if(timeRegistered == true){ //Check whether the boolean is true
					JOptionPane.showMessageDialog(null,"Employee time added successfully");//Display a success message if the boolean is true
				}else{
					JOptionPane.showMessageDialog(null,"Error occurred in the process, time not added");//Display a error message if the boolean is false
				}

			}
		});
		btnSave_1.setBounds(250, 362, 102, 23);
		empWorkingTime.add(btnSave_1);
		
		/**
		 * updateEmpWaorkingTime panel components
		 */
		comboBoxService = new JComboBox();//Dropdown to select the service with the activity to update EmpWaorkingTime details
		comboBoxService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxService.getSelectedIndex() != 0) {//Check that the selected item is nit the 1st one
					empWorkingTimeNotNull(comboBoxService.getSelectedItem()
							.toString());//Call method to display employee working times
					employees(comboBoxService.getSelectedItem().toString());//Call method to add employee names in a dropdown
				}
			}
		});
		comboBoxService.setBounds(158, 42, 202, 20);
		updateEmpWaorkingTime.add(comboBoxService);

		JLabel label_1 = new JLabel("Select Service");
		label_1.setBounds(51, 43, 102, 14);
		updateEmpWaorkingTime.add(label_1);

		JLabel label_2 = new JLabel("Employee Name");
		label_2.setBounds(24, 331, 136, 14);
		updateEmpWaorkingTime.add(label_2);

		EmployeeComboBox = new JComboBox();//Dropdown to select or enter the new employee name
		EmployeeComboBox.setEditable(true);
		EmployeeComboBox.setBounds(138, 327, 182, 20);
		updateEmpWaorkingTime.add(EmployeeComboBox);

		//Declare and initialize the button to update the employee working time
		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxService.getSelectedIndex() == 0) {//Check whether the selected service is not the first item
					JOptionPane.showMessageDialog(null,
							"Please Select Service To Proceed");
					return;
				}
				if (EmployeeComboBox.getSelectedItem().toString().trim().equals("")
						|| EmployeeComboBox.getSelectedItem().toString().trim()
								.equals("null")) {//Check whether the business owner has entered or selected a valid employee name
					JOptionPane.showMessageDialog(null,
							"Enter Employee name to proceed");
					return;
				}
				if (StarTimeComboBox1.getSelectedIndex() == 0) {//Check whether the selected start time is not the first item
					JOptionPane.showMessageDialog(null,
							"Please Select Start Time To Proceed");
					return;
				}
				if (EndTimeComboBox1.getSelectedIndex() == 0) {//Check whether the selected ending time is not the first item
					JOptionPane.showMessageDialog(null,
							"Please Select End Time To Proceed");
					return;
				}
				//String newtimeAvailable = StarTimeComboBox1.getSelectedItem().toString()+"-"+EndTimeComboBox1.getSelectedItem().toString();
				int duration = 0;//Initialize the duration

				String service = comboBoxService.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = -1;
				row = DurationTable.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null,
							"Please Select row to update to proceed");
					return;
				}
				String emp,   activity, startTime;
				emp = DurationTable.getModel().getValueAt(row, 0).toString();
				activity = DurationTable.getModel().getValueAt(row, 1).toString();
				startTime = DurationTable.getModel().getValueAt(row, 2).toString();

				String newstartTime = StarTimeComboBox1.getSelectedItem().toString();
				String newendTime = EndTimeComboBox1.getSelectedItem().toString();
				String newEmp = EmployeeComboBox.getSelectedItem().toString().toLowerCase();
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader("serviceduration.txt"));//Open the file in read mode and give access to the BufferedReader 
					String line = "", recs[] = null;
					while( (line = reader.readLine()) != null ){//Loop through every line in the file that is not null
						recs = line.split(",");//Separate the line with a comma and add the records in a string array
						if(recs[0].equals(service) && recs[1].equals(activity)){//Check if the 1st array item equals to service selected and if the 2nd item equals to the activity
							duration = Integer.parseInt(recs[2]);//Set duration is equal to the 3rd array item
							break; //End loop since we have found the duration for the activity
						}
					}
					reader.close();//Close the BufferedReader
					
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");//Date format in hours and minutes
					Date date1 = format.parse(newstartTime);
					Date date2 = format.parse(newendTime);
					long diff = date2.getTime() - date1.getTime(); //Get the time difference in milliseconds
					long diffMinutes = diff / (60 * 1000);//Convert the time difference in minutes
					
					int numOfSlots;
					if(diffMinutes % duration != 0){//Make sure the number slots does not return decimal values
						JOptionPane.showMessageDialog(null, "The time difference should not have remainders for this to work");
						return;
					}
					
					ArrayList<String> list = new ArrayList<>();
					BufferedReader br = new BufferedReader(new FileReader(fileName));//Open the file in read mode
					String currentLine = "";
					while ((currentLine = br.readLine()) != null) {//Read every line that is not null
						recs = currentLine.split(",");//Separate the line with a comma and add the records in a string array
						if (recs[0].equals(emp) && recs[2].equals(activity)) {
							//Do Not add this line, This line need to be removed from the file since employee working time will be update by code down here
						}else{
							list.add(currentLine); //Add the line into arraylist, This line does not need to be changed
						}
					}
					br.close();//Close BufferedReader
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));//Open file in write mode
					for (int a = 0; a < list.size(); a++) {//Loop through every list item in the list
						bw.write(list.get(a)+ System.getProperty("line.separator"));//write the list item and write a new line
					}
					bw.close();//Close BufferedWriter
					/*
					 * This code is the one that woill update employee working t
					 */
					BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));//Open file in write mode
					for (int a = 0; a < daysList.size(); a++) {//Loop through every day in the daylist
						numOfSlots = (int) (diffMinutes / duration); //Get the number of slots to write
						date1 = format.parse(startTime);//parse start time and set date1
						for(int d = 0; d < numOfSlots; d++ ){//Loop to write every slot
							Calendar cal = Calendar.getInstance();//Instantiate calendar instance
					        cal.setTime(date1);
					        Time timeStart = new Time (cal.getTime().getTime());//Get start time of the slot
					        
					        cal.add(Calendar.MINUTE, duration);//Add duration to get ending time of the slot
					        Time timeEnd = new Time (cal.getTime().getTime());//Grt ending time of the slot in Time
					        writer.write(newEmp + "," + daysList.get(a) + ","
									+ activity + "," + timeStart.toString().substring(0, 5) + "-" + timeEnd.toString().substring(0, 5)
									+ ",available");//write the slot, Remember we are updating employee working time
							writer.newLine();//write a new line
					       date1 = new Date(timeEnd.getTime());//Set the ending time of this slotb be the starting time of the next slot
						}
						
					}
					writer.close(); //Close the BufferedWriter
					
					/*
					 * Employee working time details are stored in the file employeeworkingtime.txt
					 */
					list = new ArrayList<>();//List to contain lines read from the employeeworkingtime file
					br = new BufferedReader(new FileReader("employeeworkingtime.txt"));//Open the file in read mode and give access to the BufferedReader
					while ((currentLine = br.readLine()) != null) {//Loop through every line that is not null in the file
						recs = currentLine.split(",");//Separate the line with a comma and add the records in a string array
						if (recs[0].equals(emp) && recs[1].equals(service) && recs[2].equals(activity)) { //Check if the line matches the employee working time to be changed
							list.add(newEmp+","+service+","+activity+","+newstartTime+","+newendTime+","+duration);//Add new details in a list
						}else{
							list.add(currentLine);//Add the line as it was read in a list since no changes are needed
						}
					}
					br.close();//Close the BufferedReader
					
					/*
					 * Now we want to write the list items back to the file
					 */
					bw = new BufferedWriter(new FileWriter("employeeworkingtime.txt"));//Open the file in write mode and give access to the BufferedWriter
					for (int a = 0; a < list.size(); a++) {//Loop through every list item
						bw.write(list.get(a)+ System.getProperty("line.separator"));//Write the list item in the file and write a new line to prepare for the next writing
					}
					bw.close();//Close the BufferedWriter

				
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Employee working time updated successfully"); //Display a success message after the employee working time has been updated
				empWorkingTimeNotNull(comboBoxService.getSelectedItem().toString()); //Display employee working time in a table
				employees(comboBoxService.getSelectedItem().toString());//Display employee names in a dropdown 
			}
		});
		button_2.setBounds(354, 335, 102, 23);
		updateEmpWaorkingTime.add(button_2);

		//Declare and initialize a button to delete employee working time
		JButton button_3 = new JButton("Delete");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxService.getSelectedIndex() == 0) { //Check if service is selected by the business owner
					JOptionPane.showMessageDialog(null,	"Please Select Service To Proceed");
					return;
				}
				
				String service = comboBoxService.getSelectedItem().toString(); //Set service is the value selected by the user
				String fileName = service + ".txt"; //Initialize the file to delete the working time from
				int row = -1;
				row = DurationTable.getSelectedRow();
				if (row < 0) { //Check that the user has selected a row to delete
					JOptionPane.showMessageDialog(null, "Please Select row to update to proceed");
					return;
				}
				//Let the user confirm that they want to delete the working time
				int confirm = JOptionPane.showConfirmDialog(null, "You are deleting employee working time. Proceed", "Confirm", 2);
				if(confirm == 0){ //Check that the user has confirmed to delete the employee working time 
					String emp,   activity;
					emp = DurationTable.getModel().getValueAt(row, 0).toString(); //Set the employee name
					activity = DurationTable.getModel().getValueAt(row, 1).toString();//Set the activity name
	
					try {
						ArrayList<String> list = new ArrayList<>(); //List to contain values read from the file
						BufferedReader br = new BufferedReader(new FileReader(fileName));//OPen the file in read mode and give access to the BufferedReader
						String currentLine = "";
						while ((currentLine = br.readLine()) != null) {//loop through rvery line in the file that is not null
							String recs[] = currentLine.split(",");//Separate the line with commas
							if (recs[0].equals(emp) && recs[2].equals(activity)) {
								//Do Not add this line because it has the employee name and the activity to delete
							}else{
								list.add(currentLine); //Add the line to the list
							}
						}
						br.close();//close the buffer reader
						
						BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));//open the file and give access to buffer writer
						for (int a = 0; a < list.size(); a++) {//loop through every item in  the list
							bw.write(list.get(a)+ System.getProperty("line.separator"));//write the current list item in the file and write a new line
						}
						bw.close();//close buffer writer
						
						list = new ArrayList<>();//list to contain line from employee working time
						br = new BufferedReader(new FileReader("employeeworkingtime.txt"));//open the file and give access to buffer reader
						while ((currentLine = br.readLine()) != null) {//loop through every line in the file that is not null
							String recs[] = currentLine.split(",");//separate the line with commas
							if (recs[0].equals(emp) && recs[1].equals(service) && recs[2].equals(activity)) {//check if the line has the employee name and service
							
							}else{
								list.add(currentLine);//add the current line in the list
							}
						}
						br.close();//close buffer reader
						bw = new BufferedWriter(new FileWriter("employeeworkingtime.txt"));//open file and give access to buffer reader
						for (int a = 0; a < list.size(); a++) {//loop through every item in  the list
							bw.write(list.get(a)+ System.getProperty("line.separator"));//write the current list item in the file and write a new line
						}
						bw.close();//close buffer writer
	
					
					} catch (IOException  e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Employee working time deleted successfully"); //Display a success message after the employee working time has been updated
					empWorkingTimeNotNull(comboBoxService.getSelectedItem().toString()); //Display employee working time in a table
					employees(comboBoxService.getSelectedItem().toString());//Display employee names in a dropdown 
				}
			}
		});
		button_3.setBounds(354, 373, 102, 23);
		updateEmpWaorkingTime.add(button_3);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 73, 527, 232);
		updateEmpWaorkingTime.add(scrollPane_6);

		DurationTable = new JTable(); //Table to diaplay employee working toikes
		DurationTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Set action to perform when the table row is clicked
				int row = DurationTable.getSelectedRow();
				String employee = DurationTable.getModel().getValueAt(row, 0)
						.toString();
				String startTime = DurationTable.getModel().getValueAt(row, 2)
						.toString();
				String endTime = DurationTable.getModel().getValueAt(row, 3)
						.toString();
				//Set correct values to appropriate dropdowns
				EmployeeComboBox.setSelectedItem(employee);
				StarTimeComboBox1.setSelectedItem(startTime);
				EndTimeComboBox1.setSelectedItem(endTime);
			}
		});
		DurationTable.setRowHeight(25);
		//Define table model
		DurationTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Employee Name", "Activity", "Start Time", "End Time" , "Duration" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			Class[] columnTypes = new Class[] { Object.class, Object.class,
					Object.class, Object.class , Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_6.setViewportView(DurationTable);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.LIGHT_GRAY);
		panel_9.setBounds(0, 0, 557, 24);
		updateEmpWaorkingTime.add(panel_9);

		JLabel lblUpdateEmployeeWorking = new JLabel(
				"Update Employee Working Time");
		panel_9.add(lblUpdateEmployeeWorking);
		
		StarTimeComboBox1 = new JComboBox(); //Drop down to select starting time when updating employee working time
		StarTimeComboBox1.setModel(new DefaultComboBoxModel(new String[] {
				"Start Time", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
				"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
				"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
				"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
				"23:30" }));
		StarTimeComboBox1.setBounds(24, 358, 129, 20);
		updateEmpWaorkingTime.add(StarTimeComboBox1);
		
		EndTimeComboBox1 = new JComboBox();//Drop down to select ending time when updating employee working time
		EndTimeComboBox1.setModel(new DefaultComboBoxModel(new String[] {
				"Start Time", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
				"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
				"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
				"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
				"23:30" }));
		EndTimeComboBox1.setBounds(205, 358, 115, 20);
		updateEmpWaorkingTime.add(EndTimeComboBox1);


		/**
		 *empAvailable panel components 
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 71, 517, 280);
		empAvailable.add(scrollPane_1);

		empTable = new JTable();// Table to display the available employees
		empTable.setRowHeight(25);
		empTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Employee Name", "Day", "Activity",
						"Time Available", "Status" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(empTable);

		EmpAvailableSelctService = new JComboBox();//Drop down to select service to see employee available
		EmpAvailableSelctService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Call method to display available employees
				empAvailable(EmpAvailableSelctService.getSelectedItem().toString());
			}
		});
		EmpAvailableSelctService.setBounds(106, 35, 152, 25);
		empAvailable.add(EmpAvailableSelctService);

		JLabel lblSelectService = new JLabel("Select Service");
		lblSelectService.setBounds(10, 35, 91, 25);
		empAvailable.add(lblSelectService);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setBounds(0, 0, 557, 24);
		empAvailable.add(panel_8);

		JLabel lblEmployeesAvailableNext = new JLabel(
				"Employees Available Next Week");
		panel_8.add(lblEmployeesAvailableNext);

		JLabel label_11 = new JLabel("Select Service");
		label_11.setBounds(286, 35, 114, 25);
		empAvailable.add(label_11);

		empAvailSelectDay = new JComboBox();//Drop down to select day to see employee available
		empAvailSelectDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (empAvailSelectDay.getSelectedIndex() != 0) {//Check that the day selected is not the first item in the dropdown
					try {
						//Call method to display available employees
						empAvailableParticularDay(EmpAvailableSelctService.getSelectedItem()
								.toString(), empAvailSelectDay.getSelectedItem()
								.toString());
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}
			}
		});
		empAvailSelectDay.addItem("Select Day");
		for (int a = 0; a < daysList.size(); a++) {
			empAvailSelectDay.addItem(daysList.get(a));
		}
		empAvailSelectDay.setBounds(413, 35, 114, 25);
		empAvailable.add(empAvailSelectDay);
		
		/**
		 * bookingsummaries Panel components
		 */
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(0, 0, 557, 24);
		bookingsummaries.add(panel_5);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 537, 315);
		bookingsummaries.add(scrollPane);

		table = new JTable(); //Table to display all booked slots when booking for a customer
		table.setRowHeight(25);
		//Declare columns for the table
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Customer", "Employee", "Day", "Service", "Activity",
				"Time Slot" }) {
			//Define if columns can be editted by the user
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		scrollPane.setViewportView(table);
		

		JLabel lblBookingSummary = new JLabel("Booking Summary");
		panel_5.add(lblBookingSummary);

		JLabel label_3 = new JLabel("Select Service");
		label_3.setBounds(20, 47, 127, 14);
		bookForCustomer.add(label_3);

		selectService = new JComboBox();//dropdown to select the service to book
		selectService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectService.getSelectedIndex() != 0) {//Check that the service selected is not the first item 
					try {
						//Call nethos to display activities in this service
						activity(selectService.getSelectedItem().toString());
					} catch (Exception e1) {
						// e.printStackTrace();
					}
				}
			}
		});
		selectService.setBounds(113, 44, 143, 20);
		bookForCustomer.add(selectService);

		JLabel label_4 = new JLabel("Select Activity");
		label_4.setBounds(286, 47, 120, 14);
		bookForCustomer.add(label_4);

		selectActivity = new JComboBox();//Dropdown to select the activity to book
		selectActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectActivity.getSelectedIndex() != 0) {//Check that the activity selected is not the first item
					try {
						//Call method to display the available days to book
						dayOfApp(selectService.getSelectedItem().toString(),
								selectActivity.getSelectedItem().toString());
					} catch (Exception e1) {
						// e.printStackTrace();
					}
				}
			}
		});
		selectActivity.setBounds(411, 44, 136, 20);
		bookForCustomer.add(selectActivity);

		JLabel label_5 = new JLabel("Select Day of Appointment");
		label_5.setBounds(20, 82, 158, 14);
		bookForCustomer.add(label_5);

		selectDay = new JComboBox();//Drop down to select day of appointment 
		selectDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectDay.getSelectedIndex() != 0) {//Check that the day selcted is not the first item
					try {
						//Call the method to display the available employees for booking the appointment
						employee(selectService.getSelectedItem().toString(),
								selectActivity.getSelectedItem().toString(),
								selectDay.getSelectedItem().toString());
					} catch (Exception e) {
						// e.printStackTrace();
					}
				}
			}
		});
		selectDay.setBounds(188, 80, 172, 20);
		bookForCustomer.add(selectDay);

		JLabel label_6 = new JLabel("Select Employee");
		label_6.setBounds(20, 120, 158, 14);
		bookForCustomer.add(label_6);

		selectEmp = new JComboBox();//Dropdown to select the employee to book the appointment
		selectEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectEmp.getSelectedIndex() != 0) {//Check that the employee selected is nit the first item
					try {
						//Display the available slot to book
						availableSlots(selectService.getSelectedItem()
								.toString(), selectActivity.getSelectedItem()
								.toString(), selectDay.getSelectedItem()
								.toString(), selectEmp.getSelectedItem()
								.toString());
					} catch (Exception e1) {
						// e.printStackTrace();

					}
				}
			}
		});
		selectEmp.setBounds(113, 116, 143, 20);
		bookForCustomer.add(selectEmp);

		JLabel label_7 = new JLabel("Select Time Above and Save");
		label_7.setBounds(26, 382, 197, 14);
		bookForCustomer.add(label_7);

		//Declare the and initialize the button to save the booked slot
		JButton button_1 = new JButton("Save");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectCustomer.getText().trim().equals("")) {//Check that customer name is entered
					JOptionPane.showMessageDialog(null,
							"Enter Customer To Book For");
					return;
				}
				String customerInfo = selectCustomer.getText().trim().toLowerCase();
				String[] customerData = customerInfo.split(",");
				if (customerData.length != 2) {//Check that two names are provided, first name and last name
					JOptionPane.showMessageDialog(null,
							"Enter Customer's 2 names separated by comma");
					return;
				}

				ArrayList<String> toBookList = new ArrayList<>();//List to store the slots to book
				String service = selectService.getSelectedItem().toString();//Get the service name
				int row = 0;
				int rows = bookingAvailableSlotsTable.getRowCount();//Get the number of rows in the table
				String selected = "";
				for (row = 0; row < rows; row++) {//Loop through every row in the table
						boolean status = (boolean) bookingAvailableSlotsTable.getModel()
								.getValueAt(row, 5); //Boolean should be true is the row is ticked to book the slot
						if (status == true) {//Check status of the boolean
							//Set the selected slot details
							selected = bookingAvailableSlotsTable.getModel().getValueAt(row, 0)
									.toString()
									+ ","
									+ bookingAvailableSlotsTable.getModel().getValueAt(row, 1)
											.toString()
									+ ","
									+ bookingAvailableSlotsTable.getModel().getValueAt(row, 2)
											.toString()
									+ ","
									+ bookingAvailableSlotsTable.getModel().getValueAt(row, 3)
											.toString()
									+ ","
									+ bookingAvailableSlotsTable.getModel().getValueAt(row, 4)
											.toString();
							toBookList.add(selected);//Add the selected slot to the list
						}
					}
				new BookAppointmentFactory();//Call class to make the booking
				BookAppointmentFactory.doBooking("Owner", customerData, service, toBookList);//Call the method to make booking by the owner
				
				//Reset the table to show the availble slots after some have been booked
				if (selectEmp.getSelectedIndex() != 0) { 
					try {
						availableSlots(selectService.getSelectedItem()
								.toString(), selectActivity.getSelectedItem()
								.toString(), selectDay.getSelectedItem()
								.toString(), selectEmp.getSelectedItem()
								.toString());
					} catch (Exception e1) {
						// e.printStackTrace();

					}
				}
				selectCustomer.setText("");

			}
		});
		button_1.setBounds(363, 373, 89, 23);
		bookForCustomer.add(button_1);

		JLabel lblSelectCustomer = new JLabel("Enter Customer");
		lblSelectCustomer.setBounds(260, 120, 100, 14);
		bookForCustomer.add(lblSelectCustomer);

		selectCustomer = new JTextField();//Filed to enter customer name to book for
		selectCustomer.setBounds(363, 117, 184, 20);
		bookForCustomer.add(selectCustomer);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(10, 148, 537, 223);
		bookForCustomer.add(scrollPane_7);

		bookingAvailableSlotsTable = new JTable();//Table to show available slots when booking for the customer
		//Define table model
		bookingAvailableSlotsTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Employee", "Day", "Service", "Time", "Availability", "Book" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class,
					Object.class, Object.class, Object.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		bookingAvailableSlotsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		bookingAvailableSlotsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		bookingAvailableSlotsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		bookingAvailableSlotsTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		bookingAvailableSlotsTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		bookingAvailableSlotsTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		bookingAvailableSlotsTable.setRowHeight(25);

		scrollPane_7.setViewportView(bookingAvailableSlotsTable);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.LIGHT_GRAY);
		panel_11.setBounds(0, 0, 557, 24);
		bookForCustomer.add(panel_11);

		JLabel lblMakeBookingFor = new JLabel("Make Booking For Customer");
		panel_11.add(lblMakeBookingFor);

		//Call function to display services in dropdowns
		services();

	}

	protected void empAvailableParticularDay(String service, String day) {
		try {
			String fileName = service + ".txt";
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName));
			String line = "";
			ArrayList<String> list = new ArrayList<>();
			String recs[] = null;
			while ((line = br.readLine()) != null) {
				recs = line.split(",");
				if (!recs[0].equals("null") && recs[1].equals(day)
						&& recs[4].equals("available")) {
					list.add(line);
				}
			}

			br.close();
			int i;

			DefaultTableModel model = (DefaultTableModel) empTable.getModel();
			model.setRowCount(0);
			Object[] rowData = new Object[5];
			for (i = 0; i < list.size(); i++) {
				recs = list.get(i).split(",");
				rowData[0] = recs[0];
				rowData[1] = recs[1];
				rowData[2] = recs[2];
				rowData[3] = recs[3];
				rowData[4] = recs[4];
				model.addRow(rowData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Function to read the business information and display them in the window
	private String fillBusinessData() {
		String businessDetails = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"BusinessInfo.txt"));//Open the BusinessInfo.txt file in read mode and give access to the BufferedReader
			String line = "";
			while ((line = br.readLine()) != null) {//Loop through every line that is not null
				String recs[] = line.split(",");//Separate the line with a comma and add the records in as string array
				if (recs[3].equals(userData[4])) {//Check whether the username read is equal to the usernae of the business owner logged in
					businessDetails = "<html><font color='green'>Name:</font> "
							+ "<font color='red'>" + recs[0] + "</font> "
							+ "<font color='green'>Location:</font> "
							+ "<font color='red'>" + recs[1] + "</font> "
							+ "<font color='green'>Telephone:</font> "
							+ "<font color='red'>" + recs[2]
							+ "</font> </html>"; //Style the business information using HTML
				}
			}
			br.close();//Close the BufferedReader
			return businessDetails; //Return the string with business info
		} catch (Exception e2) {
			e2.printStackTrace();
			return businessDetails;
		}
	}

	//Function to read service in the system and display them in dropdowns
	public void services() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("services.txt"));//Open the file in read mode and give access to the BufferedReader
			ArrayList<String> services = new ArrayList<String>();//List to contain all the services
			String line = "";
			while ((line = br.readLine()) != null) {//Loop through every line that is not null
				services.add(line);//Add the line read in the list
			}
			/*
			 Remove all items in the dropdowns to prepare for the new data
			 */
			servicedeleteCombo.removeAllItems();
			EmpAvailableSelctService.removeAllItems();
			comboBoxService.removeAllItems();
			selectService.removeAllItems();
			ComboBoxServiceName.removeAllItems();
			ServiceToAddActivity.removeAllItems();
			selectServiceToAddEmpTime.removeAllItems();
			/*
			 Set the first items in the dropdowns
			 */
			servicedeleteCombo.addItem("Select Service");
			comboBoxService.addItem("Select Services");
			selectService.addItem("Select Services");
			ComboBoxServiceName.addItem("Select Services");
			ServiceToAddActivity.addItem("Select Services");
			selectServiceToAddEmpTime.addItem("Select Services");
			
			for (int a = 0; a < services.size(); a++) {//Loop through every list item
				/*
				 Add the list item in the dropdowns
				 */
				servicedeleteCombo.addItem(services.get(a));
				EmpAvailableSelctService.addItem(services.get(a));
				comboBoxService.addItem(services.get(a));
				selectService.addItem(services.get(a));
				ComboBoxServiceName.addItem(services.get(a));
				ServiceToAddActivity.addItem(services.get(a));
				selectServiceToAddEmpTime.addItem(services.get(a));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Function to show the available employees in a certain service in a dropdowns
	public void employees(String service) {
		String fileName = service + ".txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			ArrayList<String> employeees = new OwnerUtils().empAvailable(service); //Initialize the list with available employees
			
			EmployeeComboBox.removeAllItems();//Remove all items from the dropdown
			empNamesWorkingTime.removeAllItems();//Remove all items from the dropdown
			
			ArrayList<String> list = new ArrayList<>();
			for (int a = 0; a < employeees.size(); a++) {//Loop through every list item in the list
				String recs[] = employeees.get(a).split(",");
				if(!list.contains(recs[0])){//Check if employee name has been added in dropdown
					list.add(recs[0]);//Separate to get employee name
					empNamesWorkingTime.addItem(recs[0]);//Add the list item in the dropdown
					EmployeeComboBox.addItem(recs[0]);//Add the list item in the dropdown
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Function to show the available employees in a certain service in a table
	public void empAvailable(String service) {
		ArrayList<String> list = new OwnerUtils().empAvailable(service); //Initialize the list with available employees
		int i;

		DefaultTableModel model = (DefaultTableModel) empTable.getModel();//Define model for this table
		model.setRowCount(0);//Remove all rows in the table
		Object[] rowData = new Object[5];//Set column number to 5
		String recs[] = null;
		for (i = 0; i < list.size(); i++) {//Loop through every list item
			recs = list.get(i).split(",");//Separate the current list item with a comma and add them to the string array
			//Set the column data with the appropriate record
			rowData[0] = recs[0];
			rowData[1] = recs[1];
			rowData[2] = recs[2];
			rowData[3] = recs[3];
			rowData[4] = recs[4];
			model.addRow(rowData);//Add row to the table model
		}

	}

	//Function to show employees working times
	public void empWorkingTimeNotNull(String service) {
		try {
			String fileName = "employeeworkingtime.txt"; //Initialize the file to read data
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName));//Open the file in read mode and give access to the BufferedReader
			String line = "";
			ArrayList<String> list = new ArrayList<>();//List to hold the read lines
			String recs[] = null;
			while ((line = br.readLine()) != null) {//Loop through every line in the file that is not  null
				recs = line.split(",");//Separate the read line with a comma and add the records in a string array
				if (recs[1].equals(service)) {//Check whether the second array item is equal to the service selected
					list.add(line);//Add the line in the list
				}
			}

			br.close();
			int i;

			DefaultTableModel model = (DefaultTableModel) DurationTable.getModel();//Define the model for the table
			model.setRowCount(0);//Remove all the rows in the table to prepare for the new data
			Object[] rowData = new Object[5];//set columns number to 5
			for (i = 0; i < list.size(); i++) {//Loop through every list item
				recs = list.get(i).split(",");//Separate the current list item with a comma and put records in a string array
				//Set the column data with the appropriate record
				rowData[0] = recs[0];
				rowData[1] = recs[2];
				rowData[2] = recs[3];
				rowData[3] = recs[4];
				rowData[4] = recs[5];
				model.addRow(rowData);//Add the row to the table model
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Function do control the panel to be visible
	public void panelToSee(JPanel panel) {
		//Hide all the panels
		addActivity.setVisible(false);
		bookingsummaries.setVisible(false);
		newservice.setVisible(false);
		deleteservice.setVisible(false);
		empAvailable.setVisible(false);
		empWorkingTime.setVisible(false);
		updateEmpWaorkingTime.setVisible(false);
		bookForCustomer.setVisible(false);
		//Set the panel visible 
		panel.setVisible(true);
		panel.setBounds(0, 0, 557, 407);//Set location for the panel in the contntpane
	}

	//Function to add activities in a dropdowm
	public void activity(String service) {
		ArrayList<String> serviceNames = Utils.getActivities(service);//Initialize a list to cotain all the activities in a service
		selectActivity.removeAllItems();//Remove all the items from this dropdown to prepare for new items
		selectActivity.addItem("Select Activity");//Set the first item in the dropdown
		for (int a = 0; a < serviceNames.size(); a++) {//Loop through every item in the list 
			selectActivity.addItem(serviceNames.get(a));//Add the list item to the dropdown
		}

	}
	
	//Function to add activity in a table for owner to check activity to add
	public void activityTable(String service) {
		ArrayList<String> serviceNames = Utils.getActivities(service);//Initialize a list to cotain all the activities in a service
		DefaultTableModel model = (DefaultTableModel) activitySelectTable.getModel();//Define model for the table
		model.setRowCount(0);//Remove all rows from the table to prepare for the new data
		Object[] rowData = new Object[6];//Set the number of columns to 6
		for (int i = 0; i < serviceNames.size(); i++) {//Loop through every item in the list
			rowData[0] = false;//Set the first column not selected
			rowData[1] = serviceNames.get(i);
			model.addRow(rowData); //Add row to the table model
		}
	}
	
	//Function to read available days of appointment for a certain activity
	public void dayOfApp(String service, String activity) {
		ArrayList<String> activityDays = Utils.getActivityAppointmentDays(
				service, activity); //Initialize the activityDays list with available days
		selectDay.removeAllItems();//Remove all items in the dropdown to prepare for new items
		selectDay.addItem("Select Day");//Set the first item in the dropdown
		for (int a = 0; a < activityDays.size(); a++) {//Loop throughevery item in the activityDays list
			selectDay.addItem(activityDays.get(a));//add the activityDays
		}

	}

	//Function to read available employee, should receibve service name, activity and day
	public void employee(String service, String activity, String day) {
		service += ".txt";
		ArrayList<String> employeeNames = Utils.getEmployeeNames(service,
				activity, day);//initialize the  employeeNames list with available employee
		selectEmp.removeAllItems(); //Remove all items in this dropdown to prepare to add new items
		selectEmp.addItem("Select Employee");//Set the first item in the dropdown
		for (int a = 0; a < employeeNames.size(); a++) {//Loop through every item in the list so that itb adds employees in the dropdown
			if (!employeeNames.get(a).equals("null")) {//Check if the list item is not rqual to null
				selectEmp.addItem(employeeNames.get(a));//Add the list item in the dropdown
			}
		}

	}

	//Function to view available slots
	public void availableSlots(String service, String activity, String day,
			String empName) {
		list.clear(); //Remove all the items from this list
		temp.clear();//Remove all the items from the temporary file
		try {
			String fileName = service + ".txt"; //Initialize the file name
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName)); //Open the file in read mode and give access to the BufferedReader
			String line = "";
			while ((line = br.readLine()) != null) {//Read every line in the file that is not null
				list.add(line);//Add the read line in the list
			}

			for (int a = 0; a < list.size(); a++) {//Loop through every list item in the list
				String recs[] = list.get(a).split(",");//Separate the list item with a comma and add the records in a string array
				if (!recs[0].equals("null")//Check whether the 1st arrau item is not equal to null
						&& recs[4].equals("available")//Check if the 5th item equals to available
						&& (recs[1].toLowerCase().equals(day)//Check whether the 2nd item equals to the day selected
								&& (recs[0].toLowerCase().equals(empName) || empName //Check if the 1st item equals to the employee selected
										.equals("*")) && (recs[2].toLowerCase()
								.equals(activity)))) {
					temp.add(list.get(a));//Add the list item in the temporary list
				}
			}
			br.close();//Close the BufferedReader
			int i;

			DefaultTableModel model = (DefaultTableModel) bookingAvailableSlotsTable.getModel();//Define model to the table
			model.setRowCount(0);//Delete all rows in the table to prepare to add new data
			Object[] rowData = new Object[6];//Set six columns for the table
			String recs[] = null;
			for (i = 0; i < temp.size(); i++) {//List through every item in the temporary list
				recs = temp.get(i).split(",");//Separate the list item with a comma and add the records in a string array
				/*
				 Set the appropriate record to the columns
				 */
				rowData[0] = recs[0];
				rowData[1] = recs[1];
				rowData[2] = recs[2];
				rowData[3] = recs[3];
				rowData[4] = recs[4];
				rowData[5] = false;
				model.addRow(rowData);//Add the row to the table model
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Function to close file lock
	private void closeLock() {
		try {
			lock.release();//Release the file
		} catch (Exception e) {
		}
		try {
			lock.release();//Release the file
			chanel.close();//Close the file channel
		} catch (Exception e) {
		}

	}

	//Function to delete the file
	private void deleteFile(File file) {
		try {
			file.delete(); //Delete the file
		} catch (Exception e) {
		}
	}
}
