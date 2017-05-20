package customer;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import account.Login;
import utility.BookAppointmentFactory;
import utility.Utils;

import javax.swing.JComboBox;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 Class to be used by the customer to show available slots and make booking
 *
 */
public class CustomerPanelGUI extends JFrame {
	private static Logger logger = Logger.getLogger(CustomerPanelGUI.class.getName());
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JLabel lblUserFirstName;
	private JLabel lblUserLastName;
	private JLabel lblUserPhone;
	private JLabel lblUserAddress;
	private JLabel lblUserName;
	private JPanel profile;
	private JPanel viewbooking;
	private JTable viewBookingTable;
	private JComboBox serviceComboBox;
	private JComboBox activityComboBox;
	private JComboBox dayComboBox;
	private JComboBox employeeComboBox;
	private JTable bookAppointmentTable;
	private JPanel custombooking;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> temp = new ArrayList<String>();
	private JButton btnCancelBooking;
	private JTable cancelBookingTable;
	private JPanel cancelBooking;
	
	/*
	 * Create the frame
	 */
	public CustomerPanelGUI(String[] userData) {
		/*
		 * Set frame deatils
		 */
		setResizable(false);
		setTitle("Appointment Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 446);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();//ContentPane to hold other GUI Components
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 175, 407);
		mainPanel.setPreferredSize(new Dimension(170, 10));
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		//Button to show customer booked slots
		JToggleButton tglbtnWelcome = new JToggleButton("View My Booking");
		tglbtnWelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewbooking.setVisible(true);//Set this panel visible
				//Hide these other panels
				profile.setVisible(false);
				custombooking.setVisible(false);
				cancelBooking.setVisible(false);
				//Call method to display booked slots
				viewMyBooking(userData);
			}
		});
		tglbtnWelcome.setBounds(10, 82, 160, 23);
		mainPanel.add(tglbtnWelcome);

		//Button to display a panel to provide components to make a booking
		JToggleButton tglbtnBookCustomAppointment = new JToggleButton(
				"Book Appointment");
		tglbtnBookCustomAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Reset dropdowns
				serviceComboBox.setSelectedIndex(0);
				activityComboBox.setSelectedIndex(0);
				dayComboBox.setSelectedIndex(0);
				employeeComboBox.setSelectedIndex(0);
				//Set this panel visible to make booking by customer
				custombooking.setVisible(true);
				//Hide these other panels
				profile.setVisible(false);
				viewbooking.setVisible(false);
				cancelBooking.setVisible(false);
			}
		});
		tglbtnBookCustomAppointment.setBounds(10, 149, 160, 23);
		mainPanel.add(tglbtnBookCustomAppointment);

		//Buton to display a panel for cancelling booked slots
		JToggleButton tglbtnCancelBooking = new JToggleButton("Cancel Booking");
		tglbtnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Set this panel  visible
				profile.setVisible(false);
				//Hide other panels
				viewbooking.setVisible(false);
				custombooking.setVisible(false);
				cancelBooking.setVisible(true);
				//Call method to display customer booked slots in preparation to cancel booked slots
				viewToCancelMyBooking(userData);
			}
		});
		tglbtnCancelBooking.setBounds(10, 208, 160, 23);
		mainPanel.add(tglbtnCancelBooking);

		//Button to log out
		JToggleButton tglbtnLogout = new JToggleButton("Logout");
		tglbtnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);//Display the login form
				dispose();//Close the customer dashboard
			}
		});
		tglbtnLogout.setBounds(10, 266, 160, 23);
		mainPanel.add(tglbtnLogout);
		
		//Button to display a panel to view customers profile
		JToggleButton tglbtnViewMyProfile = new JToggleButton("View My Profile");
		tglbtnViewMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Display the panel to show customer profile
				profile.setVisible(true);
				//Hide other panels
				viewbooking.setVisible(false);
				custombooking.setVisible(false);
				cancelBooking.setVisible(false);
			}
		});
		tglbtnViewMyProfile.setBounds(10, 28, 160, 23);
		mainPanel.add(tglbtnViewMyProfile);

		JPanel startPanel = new JPanel();
		startPanel.setBounds(175, 0, 582, 407);
		contentPane.add(startPanel);
		startPanel.setLayout(null);

		profile = new JPanel();//Panel to display  customer profile
		profile.setBounds(0, 0, 584, 406);
		startPanel.add(profile);
		profile.setLayout(new BorderLayout(0, 0));

		JPanel profilePanel = new JPanel();
		profile.add(profilePanel, BorderLayout.NORTH);

		JLabel lblMyPersonalProfile = new JLabel(
				"MY PERSONAL PROFILE INFORMATION");
		profilePanel.add(lblMyPersonalProfile);

		JPanel profileDetailsPanel = new JPanel();
		profile.add(profileDetailsPanel, BorderLayout.CENTER);
		profileDetailsPanel.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(33, 55, 106, 14);
		profileDetailsPanel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(33, 93, 106, 14);
		profileDetailsPanel.add(lblLastName);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(33, 131, 106, 14);
		profileDetailsPanel.add(lblPhone);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(33, 171, 106, 14);
		profileDetailsPanel.add(lblAddress);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(33, 211, 106, 14);
		profileDetailsPanel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(33, 250, 106, 14);
		profileDetailsPanel.add(lblPassword);

		lblUserFirstName = new JLabel("First Name");//Field to show first name
		lblUserFirstName.setBounds(149, 55, 307, 14);
		profileDetailsPanel.add(lblUserFirstName);

		lblUserLastName = new JLabel("Last Name");//Field to show last name
		lblUserLastName.setBounds(149, 93, 307, 14);
		profileDetailsPanel.add(lblUserLastName);

		lblUserPhone = new JLabel("Phone");//Field to show phone number
		lblUserPhone.setBounds(149, 131, 307, 14);
		profileDetailsPanel.add(lblUserPhone);

		lblUserAddress = new JLabel("Address");//Field to show address
		lblUserAddress.setBounds(149, 171, 307, 14);
		profileDetailsPanel.add(lblUserAddress);

		lblUserName = new JLabel("Username");//Field to show username
		lblUserName.setBounds(149, 211, 307, 14);
		profileDetailsPanel.add(lblUserName);

		passwordField = new JPasswordField();//Field to show password
		passwordField.setEditable(false);
		passwordField.setBounds(149, 247, 307, 20);
		profileDetailsPanel.add(passwordField);

		viewbooking = new JPanel();
		viewbooking.setVisible(false);
		viewbooking.setBounds(0, 0, 584, 406);
		startPanel.add(viewbooking);
		viewbooking.setLayout(null);

		JScrollPane viewBookingScrollPane = new JScrollPane();
		viewBookingScrollPane.setBounds(10, 11, 564, 350);
		viewbooking.add(viewBookingScrollPane);

		viewBookingTable = new JTable(); //Table to dispaly the booked slots
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		viewBookingTable.setRowHeight(25);
		//Define the table model
		viewBookingTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Employee Name", "Day Booked", "Service", "Activity ",	"Time Available" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		viewBookingTable.getColumnModel().getColumn(0).setPreferredWidth(85);
		viewBookingScrollPane.setViewportView(viewBookingTable);

		custombooking = new JPanel();//Panel to containing the components for booking a slot
		custombooking.setVisible(false);
		custombooking.setBounds(0, 0, 584, 407);
		startPanel.add(custombooking);
		custombooking.setLayout(null);

		JLabel lblSelectService = new JLabel("Select Service");
		lblSelectService.setBounds(10, 11, 127, 14);
		custombooking.add(lblSelectService);

		JLabel lblSelectActivity = new JLabel("Select Activity");
		lblSelectActivity.setBounds(10, 46, 127, 14);
		custombooking.add(lblSelectActivity);

		JLabel lblSelectDayOf = new JLabel("Select Day of Appointment");
		lblSelectDayOf.setBounds(10, 87, 158, 14);
		custombooking.add(lblSelectDayOf);

		JLabel lblSelectEmployee = new JLabel("Select Employee");
		lblSelectEmployee.setBounds(10, 130, 158, 14);
		custombooking.add(lblSelectEmployee);

		serviceComboBox = new JComboBox();//Drop down to select the service to book
		serviceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (serviceComboBox.getSelectedIndex() != 0) {//Check that the selected service is not the first item 
					try {
						//Display the activities in this service
						activity(serviceComboBox.getSelectedItem().toString());
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			}
		});
		serviceComboBox.setBounds(172, 8, 168, 20);
		custombooking.add(serviceComboBox);
		services();

		activityComboBox = new JComboBox();//Dropdown to display activity to book
		activityComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Activity"}));
		activityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (activityComboBox.getSelectedIndex() != 0) {//Check that the activity selected is not the first item
					try {
						//Display the available days of appointment
						dayOfApp(serviceComboBox.getSelectedItem().toString(),
								activityComboBox.getSelectedItem().toString());
					} catch (Exception e) {
						logger.log(Level.SEVERE, e.getMessage());
					}
				}
			}
		});
		activityComboBox.setBounds(172, 43, 168, 20);
		custombooking.add(activityComboBox);

		dayComboBox = new JComboBox();//Drop down to select the day of appointment
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Day"}));
		dayComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (dayComboBox.getSelectedIndex() != 0) {//Check that the day selected is not the first item
					try {
						//Display employees available for booking this day
						employee(serviceComboBox.getSelectedItem().toString(),
								activityComboBox.getSelectedItem().toString(),
								dayComboBox.getSelectedItem().toString());
					} catch (Exception e) {
						logger.log(Level.SEVERE, e.getMessage());
					}
				}
			}
		});
		dayComboBox.setBounds(172, 84, 168, 20);
		custombooking.add(dayComboBox);

		employeeComboBox = new JComboBox();//Drop down to select the employee to book the appointment
		employeeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Employee"}));
		employeeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (employeeComboBox.getSelectedIndex() != 0) {//Check that the employee selected is not the first item
					try {
						//Display the available slots for booking according to the details selected earlier by the customer
						availableSlots(serviceComboBox.getSelectedItem().toString(),
								activityComboBox.getSelectedItem().toString(),
								dayComboBox.getSelectedItem().toString(),
								employeeComboBox.getSelectedItem().toString());
					} catch (Exception e) {
						logger.log(Level.SEVERE, e.getMessage());
						
					}
				}
			}
		});
		employeeComboBox.setBounds(172, 127, 168, 20);
		custombooking.add(employeeComboBox);

		JScrollPane bookAppointmentScrollPane = new JScrollPane();
		bookAppointmentScrollPane.setBounds(10, 155, 564, 211);
		custombooking.add(bookAppointmentScrollPane);

		bookAppointmentTable = new JTable();// Table to display the available slots for booking according to the details selected by the customer
		//Define the model for the table
		bookAppointmentTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Employee", "Day", "Service", "Time", "Availability", "Book" }) {
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
				};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] { false,false,false,false,false,true };
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		bookAppointmentTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		bookAppointmentTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		bookAppointmentTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		bookAppointmentTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		bookAppointmentTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		bookAppointmentTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		bookAppointmentTable.setRowHeight(25);
		bookAppointmentScrollPane.setViewportView(bookAppointmentTable);

		JLabel lblSelectTime = new JLabel("Select Time Above and Save");
		lblSelectTime.setBounds(261, 377, 197, 14);
		custombooking.add(lblSelectTime);

		//Declare and initialize a button to save the booked slots
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String service = serviceComboBox.getSelectedItem().toString();//Set the service name to be the selected service
				
				ArrayList<String> toBookList = new ArrayList<>();//List to contain all the slots to book
				
				int row = 0;
				int rows = bookAppointmentTable.getRowCount(); //Get the number of rows in the table
				String selected = "";
				for(row = 0; row < rows; row ++){//Loop through every row in the table
					boolean status = (boolean) bookAppointmentTable.getModel().getValueAt(row, 5);//Boolean should be true if the slot is ticked
					if(status == true){//Check the status of the slot
						//Set the details of the selected slot
						selected = bookAppointmentTable.getModel().getValueAt(row, 0).toString() + ","+
								bookAppointmentTable.getModel().getValueAt(row, 1).toString() + ","+
								bookAppointmentTable.getModel().getValueAt(row, 2).toString() + ","+
								bookAppointmentTable.getModel().getValueAt(row, 3).toString()+ ","+
								bookAppointmentTable.getModel().getValueAt(row, 4).toString();
						toBookList.add(selected);//Add the selected slot on the list
					}
				}
				new BookAppointmentFactory();//Call the class to make the booking
				BookAppointmentFactory.doBooking("Customer", userData, service, toBookList);//Call the method to make booking by the customer
				
				//Reset the table to display the remaining available slots after some have been booked
				if (employeeComboBox.getSelectedIndex() != 0) {
					try {
						availableSlots(serviceComboBox.getSelectedItem().toString(),
								activityComboBox.getSelectedItem().toString(),
								dayComboBox.getSelectedItem().toString(),
								employeeComboBox.getSelectedItem().toString());
					} catch (Exception e) {
						logger.log(Level.SEVERE, e.getMessage());
					}
				}

			}
		});
		btnSave.setBounds(485, 373, 89, 23);
		custombooking.add(btnSave);
		
		cancelBooking = new JPanel();//Panel to display the components for cancelling a booked slot
		cancelBooking.setBounds(10, 0, 572, 406);
		startPanel.add(cancelBooking);
		cancelBooking.setLayout(null);
		
		//Initialize a button to cancel the booked slots
		btnCancelBooking = new JButton("Cancel Booking");
		btnCancelBooking.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<HashMap<String, String>> listToCancel = new ArrayList<>();//List to contain the slots to be cancelled
				
				String service = "", activity = "", day = "", empName = "", time = "";
				
				int row = 0;
				int rows = cancelBookingTable.getRowCount(); //Get the number of rows in the table
				boolean cancelled = false;//Boolean should be true if the slots are cancelled successfully
				for(row = 0; row < rows; row++){
					boolean status = (boolean) cancelBookingTable.getModel().getValueAt(row, 5);//Get boolean of the slot
					if(status == true){//Check the status of the boolean if true
						
						//Get details of the slots to cancell
						time = cancelBookingTable.getModel().getValueAt(row, 4).toString();
						activity = cancelBookingTable.getModel().getValueAt(row, 3).toString();
						service = cancelBookingTable.getModel().getValueAt(row, 2).toString();
						day = cancelBookingTable.getModel().getValueAt(row, 1).toString();
						empName = cancelBookingTable.getModel().getValueAt(row, 0).toString();
						
						//Add the slot details in the map
						HashMap<String, String> map = new HashMap<>();
						map.put("time", time);
						map.put("activity", activity);
						map.put("service", service);
						map.put("day", day);
						map.put("empName", empName);
						
						listToCancel.add(map);//Add the map to the list of slots to cancell
						
						CustomerUtils customer = new CustomerUtils();//Cretae the object of the class
						cancelled = customer.CancelMyBooking(userData, listToCancel);//Call the method to complete cancellation of booked slots
					}

				}
				//Display the remaining slots after some have been cancelled
				viewToCancelMyBooking(userData);
				if(cancelled == true){
					JOptionPane.showMessageDialog(null, "Successfully Cancelled"); //Display success message if the slots are cancelled
				}else{
					JOptionPane.showMessageDialog(null, "Error occurred in the process. Check if you selected row to cancel");//Display error message if the slots are not cancelled
				}
			}
		});
		btnCancelBooking.setBounds(426, 362, 136, 23);
		cancelBooking.add(btnCancelBooking);
		
		JScrollPane cancelBookingScrollPane = new JScrollPane();
		cancelBookingScrollPane.setBounds(0, 0, 562, 356);
		cancelBooking.add(cancelBookingScrollPane);
		
		cancelBookingTable = new JTable();//Table to display booked slots to cancell
		cancelBookingTable.setRowHeight(25);
		//Define the table model
		cancelBookingTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Employee Name", "Day Booked", "Service", "Activity ","Time Available", "Cancel" }) {
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
				};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		cancelBookingScrollPane.setViewportView(cancelBookingTable);
		
		//Call the method to display customer details
		userDetails(userData);
		
	}

	//Function to read and dispaly slots when the user wants to slots
	protected void viewToCancelMyBooking(String[] userData) {
		ArrayList<HashMap<String, String>> bookedSlots = new ArrayList<>();//List to contain all the slots booked
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"BookingSummaries.txt"));//Open the file and give access to the buffer reader
			line = "";
			int lineCount = 0;//Initialize counter to count number of slots
			while ((line = br.readLine()) != null) {//Loop through every line in the file that is not null
				lineCount++;
				String arr[] = line.split(",");//Separate the line withj commas and add the records in the string array
				if (arr[1].equals(userData[0]) && arr[2].equals(userData[1])) {//Check if the read line has matches the details of the user loged in
					HashMap<String, String> mapTimeTable = new HashMap<>();//Map to contain the values if user details match
					//Put the values in a hashmap with correct keys
					mapTimeTable.put("EmpName", arr[4]);
					mapTimeTable.put("Day", arr[5]);
					mapTimeTable.put("Service", arr[6]);
					mapTimeTable.put("Activity", arr[7]);
					mapTimeTable.put("timeSlot", arr[8]);
					mapTimeTable.put("lineNumber", String.valueOf(lineCount));

					bookedSlots.add(mapTimeTable);//Add the map into the list

				}
			}
			br.close();//Close the buffer reader
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

		//Define table model to add rows
		DefaultTableModel model = (DefaultTableModel) cancelBookingTable.getModel();
		Object[] rowData = new Object[6];//Set column number to six
		model.setRowCount(0);//Remove all the rows to prepare for new data

		for (int a = 0; a < bookedSlots.size(); a++) {//Loop through every item in the list
			HashMap<String, String> map = bookedSlots.get(a);//Set map value to be the current list item
			//Set the column data with appropriate values from the map
			rowData[0] = map.get("EmpName");
			rowData[1] = map.get("Day");
			rowData[2] = map.get("Service");
			rowData[3] = map.get("Activity");
			rowData[4] = map.get("timeSlot");
			rowData[5] = false;
			model.addRow(rowData);//Add the roe to the model
		}
	}

	//Function to display the user deatils in the profile
	public void userDetails(String[] userData) {
		for (int i = 0; i < userData.length; i++)//Loop through every arrY ITEM

		{
			if (i == 0) {
				lblUserFirstName.setText(userData[i]);//Set first name is the 1st array item
			} else if (i == 1) {
				lblUserLastName.setText(userData[i]);;//Set last name is the 2nd array item
			} else if (i == 2) {
				lblUserPhone.setText(userData[i]);;//Set phone is the 3rd array item
			} else if (i == 3) {
				lblUserAddress.setText(userData[i]);;//Set address is the 4th array item
			} else if (i == 4) {
				lblUserName.setText(userData[i]);;//Set username is the 5th array item
			} else if (i == 5) {
				passwordField.setText(userData[i]);;//Set password is the 6th array item
			}
		}
	}

	//Method to show View booked slots 
	public void viewMyBooking(String[] userData) {
		ArrayList<HashMap<String, String>> bookedSlots = new CustomerUtils().viewMyBooking(userData);
		//Array list to contain the booked slots
		
		//Define the ,odel for the table
		DefaultTableModel model = (DefaultTableModel) viewBookingTable.getModel();
		Object[] rowData = new Object[5];//Define 5 columns for the table
		model.setRowCount(0);//Remove all rows to prepare for new data

		for (int a = 0; a < bookedSlots.size(); a++) {//Loop through every list item
			HashMap<String, String> map = bookedSlots.get(a);//Set list item to the map
			//Set column values with correct map values
			rowData[0] = map.get("EmpName");
			rowData[1] = map.get("Day");
			rowData[2] = map.get("Service");
			rowData[3] = map.get("Activity");
			rowData[4] = map.get("timeSlot");
			model.addRow(rowData);//Add row to the table model
		}
	}

	//Method to read available services in the system
	public void services() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("services.txt"));//Open the file in read mode and give access to the buffer reader
			ArrayList<String> services = new ArrayList<String>();//List to contain all the services
			String line = "";
			while ((line = br.readLine()) != null) {//Loop through every list item in the file that is not null
				services.add(line);//Add the service to the list
			}
			serviceComboBox.addItem("Select Services");//Set the first item tp this dropdown
			for (int a = 0; a < services.size(); a++) {//Loop through every item in the list
				serviceComboBox.addItem(services.get(a));//Display the list item in the dropdown
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());//Logger
		}

	}

	//Function to display all the availble activities in a service
	public void activity(String service) {
		ArrayList<String> serviceNames = Utils.getActivities(service);//List with all the activities in the service
		activityComboBox.removeAllItems();//Remove all the items in the dropdown to prepare for new data
		activityComboBox.addItem("Select Activity");//Set the first item
		for (int a = 0; a < serviceNames.size(); a++) {//Loop through every 
			activityComboBox.addItem(serviceNames.get(a));//Display list item in the dropdown
		}

	}

	
	//Function to dispay available days of apppointment
	public void dayOfApp(String service, String activity) {
		ArrayList<String> activityDays = Utils.getActivityAppointmentDays(
				service, activity);//List to comtain all the available days of appointment
		dayComboBox.removeAllItems();//Remove all items in the dropdown
		dayComboBox.addItem("Select Day");//Set the first item in the dropdown
		for (int a = 0; a < activityDays.size(); a++) {//Loop through every item in the list
			dayComboBox.addItem(activityDays.get(a));//Display the lkist item in the dropdown
		}

	}

	//Function to display all the avialable employees for booking
	public void employee(String service, String activity, String day) {
		service += ".txt";
		ArrayList<String> employeeNames = Utils.getEmployeeNames(service,
				activity, day);//List to contain all the employees available according to the details selected by the user
		employeeComboBox.removeAllItems();//Remove all the items in the dropdown
		employeeComboBox.addItem("Select Employee");//Set the first item in the dropdown
		for (int a = 0; a < employeeNames.size(); a++) {//Loop through every list items
			if(employeeNames.get(a)!=null&&!employeeNames.get(a).equalsIgnoreCase("null")){//Check if the employee is not null
			employeeComboBox.addItem(employeeNames.get(a));//Display the employee name on the dropdown
			}
		}

	}

	//Function to display the available slots for bookibg
	public void availableSlots(String service, String activity, String day,
		String empName) {
		list.clear();//Remove list items
		temp.clear();//Remove list items from the temporary list
		
		try {
			String fileName = service + ".txt";//Set the filename
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName));//Open the file in read mode and give access to the buffer reader
			String line = "";
			while ((line = br.readLine()) != null) {//Read every line that is not null
				list.add(line);//Add the line in the list
			}

			for (int a = 0; a < list.size(); a++) {//Loop every list item
				String recs[] = list.get(a).split(",");//Separate the line with a comma
				if (!recs[0].equals("null") && recs[4].equals("available")//Checlk if employee is not null and the slot is availab;e
						&& (recs[1].toLowerCase().equals(day)
								&& (recs[0].toLowerCase().equals(empName) || empName
										.equals("*")) && (recs[2].toLowerCase()
								.equals(activity)))) { //Check all validation that the slot can be booked
					temp.add(list.get(a));//Add the list item in the temporary list
				}
			}
			br.close();//Close the buffer reader
			int i;

			//Define model for the table
			DefaultTableModel model = (DefaultTableModel) bookAppointmentTable.getModel();
			model.setRowCount(0);//Remove all the rows from the table
			Object[] rowData = new Object[6];//Set column number for the table
			String recs[] = null;
			for (i = 0; i < temp.size(); i++) {//Loop through every item in the temporary list
				recs = temp.get(i).split(",");//Separate list items with commas
				//Set the table columns with appropriate data
				rowData[0] = recs[0];
				rowData[1] = recs[1];
				rowData[2] = recs[2];
				rowData[3] = recs[3];
				rowData[4] = recs[4];
				rowData[5] = false;
				model.addRow(rowData);//Add the row to the table model
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());//Logger
		}

	}
}
