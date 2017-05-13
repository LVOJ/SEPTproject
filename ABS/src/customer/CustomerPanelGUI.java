package customer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.IIOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;

import java.awt.Dimension;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import account.Login;
import utility.Utils;

import javax.swing.JComboBox;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

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
	
	public CustomerPanelGUI(String[] userData) {
		setResizable(false);
		setTitle("Appointment Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 446);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 175, 407);
		mainPanel.setPreferredSize(new Dimension(170, 10));
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		JToggleButton tglbtnWelcome = new JToggleButton("View My Booking");
		tglbtnWelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile.setVisible(false);
				viewbooking.setVisible(true);
				custombooking.setVisible(false);
				cancelBooking.setVisible(false);
				viewMyBooking(userData);
			}
		});
		tglbtnWelcome.setBounds(10, 82, 160, 23);
		mainPanel.add(tglbtnWelcome);

		JToggleButton tglbtnBookCustomAppointment = new JToggleButton(
				"Book Appointment");
		tglbtnBookCustomAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serviceComboBox.setSelectedIndex(0);
				activityComboBox.setSelectedIndex(0);
				dayComboBox.setSelectedIndex(0);
				employeeComboBox.setSelectedIndex(0);
				profile.setVisible(false);
				viewbooking.setVisible(false);
				custombooking.setVisible(true);
				cancelBooking.setVisible(false);
			}
		});
		tglbtnBookCustomAppointment.setBounds(10, 149, 160, 23);
		mainPanel.add(tglbtnBookCustomAppointment);

		JToggleButton tglbtnCancelBooking = new JToggleButton("Cancel Booking");
		tglbtnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile.setVisible(false);
				viewbooking.setVisible(false);
				custombooking.setVisible(false);
				cancelBooking.setVisible(true);
				viewToCancelMyBooking(userData);
			}
		});
		tglbtnCancelBooking.setBounds(10, 208, 160, 23);
		mainPanel.add(tglbtnCancelBooking);

		JToggleButton tglbtnLogout = new JToggleButton("Logout");
		tglbtnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		tglbtnLogout.setBounds(10, 266, 160, 23);
		mainPanel.add(tglbtnLogout);
		
		JToggleButton tglbtnViewMyProfile = new JToggleButton("View My Profile");
		tglbtnViewMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile.setVisible(true);
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

		profile = new JPanel();
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

		lblUserFirstName = new JLabel("First Name");
		lblUserFirstName.setBounds(149, 55, 307, 14);
		profileDetailsPanel.add(lblUserFirstName);

		lblUserLastName = new JLabel("Last Name");
		lblUserLastName.setBounds(149, 93, 307, 14);
		profileDetailsPanel.add(lblUserLastName);

		lblUserPhone = new JLabel("Phone");
		lblUserPhone.setBounds(149, 131, 307, 14);
		profileDetailsPanel.add(lblUserPhone);

		lblUserAddress = new JLabel("Address");
		lblUserAddress.setBounds(149, 171, 307, 14);
		profileDetailsPanel.add(lblUserAddress);

		lblUserName = new JLabel("Username");
		lblUserName.setBounds(149, 211, 307, 14);
		profileDetailsPanel.add(lblUserName);

		passwordField = new JPasswordField();
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

		viewBookingTable = new JTable();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		viewBookingTable.setRowHeight(25);
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

		custombooking = new JPanel();
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

		serviceComboBox = new JComboBox();
		serviceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (serviceComboBox.getSelectedIndex() != 0) {
					try {
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

		activityComboBox = new JComboBox();
		activityComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Activity"}));
		activityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (activityComboBox.getSelectedIndex() != 0) {
					try {
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

		dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Day"}));
		dayComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (dayComboBox.getSelectedIndex() != 0) {
					try {
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

		employeeComboBox = new JComboBox();
		employeeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Employee"}));
		employeeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		employeeComboBox.setBounds(172, 127, 168, 20);
		custombooking.add(employeeComboBox);

		JScrollPane bookAppointmentScrollPane = new JScrollPane();
		bookAppointmentScrollPane.setBounds(10, 155, 564, 211);
		custombooking.add(bookAppointmentScrollPane);

		bookAppointmentTable = new JTable();
		//table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String service = serviceComboBox.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = 0;
				int rows = bookAppointmentTable.getRowCount();
				String selected = "";
				for(row = 0; row < rows; row ++){
					try{
					boolean status = (boolean) bookAppointmentTable.getModel().getValueAt(row, 5);
					if(status == true){
						selected = bookAppointmentTable.getModel().getValueAt(row, 0).toString() + ","+
								bookAppointmentTable.getModel().getValueAt(row, 1).toString() + ","+
								bookAppointmentTable.getModel().getValueAt(row, 2).toString() + ","+
								bookAppointmentTable.getModel().getValueAt(row, 3).toString()+ ","+
								bookAppointmentTable.getModel().getValueAt(row, 4).toString();
						String recs[];
						while (true) {
							recs = selected.split(",");

							if (recs[4].equals("Un-available")) {
								System.out.println("Booking Un-Available");
								System.out.println("Please select another");
							} else {
								recs[4] = "Un-available";
								break;
							}

						}

						String modified = recs[0] + "," + recs[1] + "," + recs[2]
								+ "," + recs[3] + "," + recs[4];
						
						BufferedWriter bw = new BufferedWriter(new FileWriter(
								fileName));
						for (int a = 0; a < list.size(); a++) {
							if (list.get(a).equals(selected)) {
								list.set(a, modified);
								bw.write(list.get(a));
								bw.newLine();
							} else {
								bw.write(list.get(a));
								bw.newLine();
							}
						}
						bw.close();
						BufferedWriter writer2 = new BufferedWriter(new FileWriter(
								"BookingSummaries.txt", true));
						writer2.write("Customer," + userData[0] + "," + userData[1]
								+ ",booked Appointment on," + recs[0] + ","
								+ recs[1] + "," + /* servicename */service + ","
								+ recs[2] + "," + recs[3]);
						writer2.newLine();
						writer2.close();
					}
					}catch(Exception e){
						logger.log(Level.SEVERE, e.getMessage());
					}
					
				}
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
				JOptionPane.showMessageDialog(null, "Successfully Booked");

			}
		});
		btnSave.setBounds(485, 373, 89, 23);
		custombooking.add(btnSave);
		
		cancelBooking = new JPanel();
		cancelBooking.setBounds(10, 0, 572, 406);
		startPanel.add(cancelBooking);
		cancelBooking.setLayout(null);
		
		btnCancelBooking = new JButton("Cancel Booking");
		btnCancelBooking.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent arg0) {
				
				String service = "", activity = "", day = "", empName = "", time = "";
				
				int row = 0;
				int rows = cancelBookingTable.getRowCount();
				for(row = 0; row < rows; row++){
					boolean status = (boolean) cancelBookingTable.getModel().getValueAt(row, 5);
					if(status == true){
						time = cancelBookingTable.getModel().getValueAt(row, 4).toString();
						activity = cancelBookingTable.getModel().getValueAt(row, 3).toString();
						service = cancelBookingTable.getModel().getValueAt(row, 2).toString();
						day = cancelBookingTable.getModel().getValueAt(row, 1).toString();
						empName = cancelBookingTable.getModel().getValueAt(row, 0).toString();
						
				        ArrayList<String> bookedSlotsList = new ArrayList<>();
						try{
							
							BufferedReader br = new BufferedReader(new FileReader("BookingSummaries.txt"));
					        String line = "";
					        while((line=br.readLine())!=null){
					        	bookedSlotsList.add(line);
					        }
					        br.close();
						
					        File bookingSummaries = new File("BookingSummaries.txt");
					        File tempBookingSummaries = new File("tempBookingSummaries.txt");
					        BufferedReader reader = new BufferedReader(new FileReader(bookingSummaries));
					        BufferedWriter writer = new BufferedWriter(new FileWriter(tempBookingSummaries));
					        
					        String lineToRemove = "Customer,"+userData[0]+","+userData[1]+",booked Appointment on,"+empName+","+day+","+service+","+activity+","+time;
					        String currentLine;
					        
					        while((currentLine = reader.readLine()) != null){
					        	//trim newline when comparingwith lineToRemove
					        	String trimmedLine = currentLine.trim();
					        	if(trimmedLine.equals(lineToRemove)) continue;
					        	writer.write(currentLine + System.getProperty("line.separator"));
					        }
					        writer.close();
					        reader.close();
					        
					        bookingSummaries.delete();
					        tempBookingSummaries.renameTo(bookingSummaries);
					        tempBookingSummaries.delete();
					        
					        String fileName = service+".txt"; 
					        ArrayList<String> list = new ArrayList<String>();
					        br = new BufferedReader(new FileReader(fileName));
					        line = "";
					        String [] recs = null;
					        String lineToRename = empName+","+day+","+activity+","+time;
					        String modify = "";
					        while((line=br.readLine())!=null){
					        	recs = line.split(",");
					        	modify = recs[0]+","+recs[1]+","+recs[2]+","+ recs[3];
					        	if(modify.equals(lineToRename)){
					        		recs[4] = "available";
					        		modify += ","+recs[4];
					        	}else{
					        		modify = line;
					        	}
					            list.add(modify);
					        }
					        
					        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
					        for(int a=0;a<list.size();a++){
				                bw.write(list.get(a));
				                bw.newLine();
					        }
					        bw.close();
						}catch(IOException e){
							logger.log(Level.SEVERE, e.getMessage());
						}
					}

				}
				viewToCancelMyBooking(userData);
				JOptionPane.showMessageDialog(null, "Successfully Cancelled");
			}
		});
		btnCancelBooking.setBounds(426, 362, 136, 23);
		cancelBooking.add(btnCancelBooking);
		
		JScrollPane cancelBookingScrollPane = new JScrollPane();
		cancelBookingScrollPane.setBounds(0, 0, 562, 356);
		cancelBooking.add(cancelBookingScrollPane);
		
		cancelBookingTable = new JTable();
		cancelBookingTable.setRowHeight(25);
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
		
		userDetails(userData);
		
	}

	protected void viewToCancelMyBooking(String[] userData) {
		ArrayList<HashMap<String, String>> bookedSlots = new ArrayList<>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"BookingSummaries.txt"));
			line = "";
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				lineCount++;
				String arr[] = line.split(",");
				if (arr[1].equals(userData[0]) && arr[2].equals(userData[1])) {
					HashMap<String, String> mapTimeTable = new HashMap<>();
					mapTimeTable.put("EmpName", arr[4]);
					mapTimeTable.put("Day", arr[5]);
					mapTimeTable.put("Service", arr[6]);
					mapTimeTable.put("Activity", arr[7]);
					mapTimeTable.put("timeSlot", arr[8]);
					mapTimeTable.put("lineNumber", String.valueOf(lineCount));

					bookedSlots.add(mapTimeTable);

				}
			}
			br.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

		DefaultTableModel model = (DefaultTableModel) cancelBookingTable.getModel();
		Object[] rowData = new Object[6];
		model.setRowCount(0);

		for (int a = 0; a < bookedSlots.size(); a++) {
			HashMap<String, String> map = bookedSlots.get(a);
			rowData[0] = map.get("EmpName");
			rowData[1] = map.get("Day");
			rowData[2] = map.get("Service");
			rowData[3] = map.get("Activity");
			rowData[4] = map.get("timeSlot");
			rowData[5] = false;
			model.addRow(rowData);
		}
	}

	public void userDetails(String[] userData) {
		for (int i = 0; i < userData.length; i++)

		{
			if (i == 0) {
				lblUserFirstName.setText(userData[i]);
			} else if (i == 1) {
				lblUserLastName.setText(userData[i]);
			} else if (i == 2) {
				lblUserPhone.setText(userData[i]);
			} else if (i == 3) {
				lblUserAddress.setText(userData[i]);
			} else if (i == 4) {
				lblUserName.setText(userData[i]);
			} else if (i == 5) {
				passwordField.setText(userData[i]);
			}
		}
	}

	public void viewMyBooking(String[] userData) {
		ArrayList<HashMap<String, String>> bookedSlots = new ArrayList<>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"BookingSummaries.txt"));
			line = "";
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				lineCount++;
				String arr[] = line.split(",");
				if (arr[1].equals(userData[0]) && arr[2].equals(userData[1])) {
					HashMap<String, String> mapTimeTable = new HashMap<>();
					mapTimeTable.put("EmpName", arr[4]);
					mapTimeTable.put("Day", arr[5]);
					mapTimeTable.put("Service", arr[6]);
					mapTimeTable.put("Activity", arr[7]);
					mapTimeTable.put("timeSlot", arr[8]);
					mapTimeTable.put("lineNumber", String.valueOf(lineCount));

					bookedSlots.add(mapTimeTable);

				}
			}
			br.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

		DefaultTableModel model = (DefaultTableModel) viewBookingTable.getModel();
		Object[] rowData = new Object[5];
		model.setRowCount(0);

		for (int a = 0; a < bookedSlots.size(); a++) {
			HashMap<String, String> map = bookedSlots.get(a);
			rowData[0] = map.get("EmpName");
			rowData[1] = map.get("Day");
			rowData[2] = map.get("Service");
			rowData[3] = map.get("Activity");
			rowData[4] = map.get("timeSlot");
			model.addRow(rowData);
		}
	}

	public void services() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("services.txt"));
			ArrayList<String> services = new ArrayList<String>();
			String line = "";
			while ((line = br.readLine()) != null) {
				services.add(line);
			}
			serviceComboBox.addItem("Select Services");
			for (int a = 0; a < services.size(); a++) {
				serviceComboBox.addItem(services.get(a));
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	public void activity(String service) {
		ArrayList<String> serviceNames = Utils.getActivities(service);
		activityComboBox.removeAllItems();
		activityComboBox.addItem("Select Activity");
		for (int a = 0; a < serviceNames.size(); a++) {
			activityComboBox.addItem(serviceNames.get(a));
		}

	}

	public void dayOfApp(String service, String activity) {
		ArrayList<String> activityDays = Utils.getActivityAppointmentDays(
				service, activity);
		dayComboBox.removeAllItems();
		dayComboBox.addItem("Select Day");
		for (int a = 0; a < activityDays.size(); a++) {
			dayComboBox.addItem(activityDays.get(a));
		}

	}

	public void employee(String service, String activity, String day) {
		service += ".txt";
		ArrayList<String> employeeNames = Utils.getEmployeeNames(service,
				activity, day);
		employeeComboBox.removeAllItems();
		employeeComboBox.addItem("Select Employee");
		for (int a = 0; a < employeeNames.size(); a++) {
			if(employeeNames.get(a)!=null&&!employeeNames.get(a).equalsIgnoreCase("null")){
			employeeComboBox.addItem(employeeNames.get(a));
			System.out.println("in"+ employeeNames.get(a));
			}
			System.out.println("out"+ employeeNames.get(a));
		}

	}

	public void availableSlots(String service, String activity, String day,
		String empName) {
		list.clear();
		temp.clear();
		
		try {
			String fileName = service + ".txt";
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = br.readLine()) != null) {
				list.add(line);
			}

			int one = 0;
			for (int a = 0; a < list.size(); a++) {
				String recs[] = list.get(a).split(",");
				if (!recs[0].equals("null") && recs[4].equals("available")
						&& (recs[1].toLowerCase().equals(day)
								&& (recs[0].toLowerCase().equals(empName) || empName
										.equals("*")) && (recs[2].toLowerCase()
								.equals(activity)))) {
					temp.add(list.get(a));
					one++;
				}
			}
			br.close();
			int i;

			DefaultTableModel model = (DefaultTableModel) bookAppointmentTable.getModel();
			model.setRowCount(0);
			Object[] rowData = new Object[6];
			String recs[] = null;
			for (i = 0; i < temp.size(); i++) {
				recs = temp.get(i).split(",");
				rowData[0] = recs[0];
				rowData[1] = recs[1];
				rowData[2] = recs[2];
				rowData[3] = recs[3];
				rowData[4] = recs[4];
				rowData[5] = false;
				model.addRow(rowData);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}
}
