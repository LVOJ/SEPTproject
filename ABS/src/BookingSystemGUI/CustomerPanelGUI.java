package BookingSystemGUI;

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
import javax.swing.JComboBox;

import CommandLine.Utils;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class CustomerPanelGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JLabel lblKilimoKCornelius;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JPanel profile;
	private JPanel viewbooking;
	private JTable table;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JTable table_1;
	private JPanel custombooking;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> temp = new ArrayList<String>();
	private JButton btnCancelBooking;
	private JTable table_2;
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

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 175, 407);
		panel_2.setPreferredSize(new Dimension(170, 10));
		contentPane.add(panel_2);
		panel_2.setLayout(null);

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
		panel_2.add(tglbtnWelcome);

		JToggleButton tglbtnBookCustomAppointment = new JToggleButton(
				"Book Appointment");
		tglbtnBookCustomAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				comboBox_2.setSelectedIndex(0);
				comboBox_3.setSelectedIndex(0);
				profile.setVisible(false);
				viewbooking.setVisible(false);
				custombooking.setVisible(true);
				cancelBooking.setVisible(false);
			}
		});
		tglbtnBookCustomAppointment.setBounds(10, 149, 160, 23);
		panel_2.add(tglbtnBookCustomAppointment);

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
		panel_2.add(tglbtnCancelBooking);

		JToggleButton tglbtnLogout = new JToggleButton("Logout");
		tglbtnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		tglbtnLogout.setBounds(10, 266, 160, 23);
		panel_2.add(tglbtnLogout);
		
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
		panel_2.add(tglbtnViewMyProfile);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(175, 0, 582, 407);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		profile = new JPanel();
		profile.setBounds(0, 0, 584, 406);
		panel_3.add(profile);
		profile.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		profile.add(panel_1, BorderLayout.NORTH);

		JLabel lblMyPersonalProfile = new JLabel(
				"MY PERSONAL PROFILE INFORMATION");
		panel_1.add(lblMyPersonalProfile);

		JPanel panel_4 = new JPanel();
		profile.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(33, 55, 106, 14);
		panel_4.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(33, 93, 106, 14);
		panel_4.add(lblLastName);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(33, 131, 106, 14);
		panel_4.add(lblPhone);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(33, 171, 106, 14);
		panel_4.add(lblAddress);

		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(33, 211, 106, 14);
		panel_4.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(33, 250, 106, 14);
		panel_4.add(lblPassword);

		lblKilimoKCornelius = new JLabel("KILIMO K CORNELIUS");
		lblKilimoKCornelius.setBounds(149, 55, 307, 14);
		panel_4.add(lblKilimoKCornelius);

		label = new JLabel("KILIMO K CORNELIUS");
		label.setBounds(149, 93, 307, 14);
		panel_4.add(label);

		label_1 = new JLabel("KILIMO K CORNELIUS");
		label_1.setBounds(149, 131, 307, 14);
		panel_4.add(label_1);

		label_2 = new JLabel("KILIMO K CORNELIUS");
		label_2.setBounds(149, 171, 307, 14);
		panel_4.add(label_2);

		label_3 = new JLabel("KILIMO K CORNELIUS");
		label_3.setBounds(149, 211, 307, 14);
		panel_4.add(label_3);

		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(149, 247, 307, 20);
		panel_4.add(passwordField);

		viewbooking = new JPanel();
		viewbooking.setVisible(false);
		viewbooking.setBounds(0, 0, 584, 406);
		panel_3.add(viewbooking);
		viewbooking.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 350);
		viewbooking.add(scrollPane);

		table = new JTable();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Employee Name", "Day Booked", "Service", "Activity ",	"Time Available" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		scrollPane.setViewportView(table);

		custombooking = new JPanel();
		custombooking.setVisible(false);
		custombooking.setBounds(0, 0, 584, 407);
		panel_3.add(custombooking);
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

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() != 0) {
					try {
						activity(comboBox.getSelectedItem().toString());
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			}
		});
		comboBox.setBounds(172, 8, 168, 20);
		custombooking.add(comboBox);
		services();

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select Activity"}));
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_1.getSelectedIndex() != 0) {
					try {
						dayOfApp(comboBox.getSelectedItem().toString(),
								comboBox_1.getSelectedItem().toString());
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			}
		});
		comboBox_1.setBounds(172, 43, 168, 20);
		custombooking.add(comboBox_1);

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Select Day"}));
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_2.getSelectedIndex() != 0) {
					try {
						employee(comboBox.getSelectedItem().toString(),
								comboBox_1.getSelectedItem().toString(),
								comboBox_2.getSelectedItem().toString());
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			}
		});
		comboBox_2.setBounds(172, 84, 168, 20);
		custombooking.add(comboBox_2);

		comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Select Employee"}));
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_3.getSelectedIndex() != 0) {
					try {
						availableSlots(comboBox.getSelectedItem().toString(),
								comboBox_1.getSelectedItem().toString(),
								comboBox_2.getSelectedItem().toString(),
								comboBox_3.getSelectedItem().toString());
					} catch (Exception e) {
						//e.printStackTrace();
						
					}
				}
			}
		});
		comboBox_3.setBounds(172, 127, 168, 20);
		custombooking.add(comboBox_3);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 155, 564, 211);
		custombooking.add(scrollPane_1);

		table_1 = new JTable();
		//table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
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

		table_1.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(80);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(70);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(50);
		table_1.setRowHeight(25);
		scrollPane_1.setViewportView(table_1);

		JLabel lblSelectTime = new JLabel("Select Time Above and Save");
		lblSelectTime.setBounds(261, 377, 197, 14);
		custombooking.add(lblSelectTime);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String service = comboBox.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = 0;
				int rows = table_1.getRowCount();
				String selected = "";
				for(row = 0; row < rows; row ++){
					try{
					boolean status = (boolean) table_1.getModel().getValueAt(row, 5);
					if(status == true){
						selected = table_1.getModel().getValueAt(row, 0).toString() + ","+
								table_1.getModel().getValueAt(row, 1).toString() + ","+
								table_1.getModel().getValueAt(row, 2).toString() + ","+
								table_1.getModel().getValueAt(row, 3).toString()+ ","+
								table_1.getModel().getValueAt(row, 4).toString();
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
						//e.printStackTrace();
					}
					
				}
				if (comboBox_3.getSelectedIndex() != 0) {
					try {
						availableSlots(comboBox.getSelectedItem().toString(),
								comboBox_1.getSelectedItem().toString(),
								comboBox_2.getSelectedItem().toString(),
								comboBox_3.getSelectedItem().toString());
					} catch (Exception e) {
						//e.printStackTrace();
						
					}
				}
				JOptionPane.showMessageDialog(null, "Successfully Booked");

			}
		});
		btnSave.setBounds(485, 373, 89, 23);
		custombooking.add(btnSave);
		
		cancelBooking = new JPanel();
		cancelBooking.setBounds(10, 0, 572, 406);
		panel_3.add(cancelBooking);
		cancelBooking.setLayout(null);
		
		btnCancelBooking = new JButton("Cancel Booking");
		btnCancelBooking.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent arg0) {
				
				String service = "", activity = "", day = "", empName = "", time = "";
				
				int row = 0;
				int rows = table_2.getRowCount();
				for(row = 0; row < rows; row++){
					boolean status = (boolean) table_2.getModel().getValueAt(row, 5);
					if(status == true){
						time = table_2.getModel().getValueAt(row, 4).toString();
						activity = table_2.getModel().getValueAt(row, 3).toString();
						service = table_2.getModel().getValueAt(row, 2).toString();
						day = table_2.getModel().getValueAt(row, 1).toString();
						empName = table_2.getModel().getValueAt(row, 0).toString();
						
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
							e.printStackTrace();
						}
					}

				}
				viewToCancelMyBooking(userData);
				JOptionPane.showMessageDialog(null, "Successfully Cancelled");
			}
		});
		btnCancelBooking.setBounds(426, 362, 136, 23);
		cancelBooking.add(btnCancelBooking);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 562, 356);
		cancelBooking.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setRowHeight(25);
		table_2.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
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
		
		scrollPane_2.setViewportView(table_2);
		
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
			e.printStackTrace();
		}

		DefaultTableModel model = (DefaultTableModel) table_2.getModel();
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
				lblKilimoKCornelius.setText(userData[i]);
			} else if (i == 1) {
				label.setText(userData[i]);
			} else if (i == 2) {
				label_1.setText(userData[i]);
			} else if (i == 3) {
				label_2.setText(userData[i]);
			} else if (i == 4) {
				label_3.setText(userData[i]);
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
			e.printStackTrace();
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();
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
			comboBox.addItem("Select Services");
			for (int a = 0; a < services.size(); a++) {
				comboBox.addItem(services.get(a));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void activity(String service) {
		ArrayList<String> serviceNames = Utils.getActivities(service);
		comboBox_1.removeAllItems();
		;
		comboBox_1.addItem("Select Activity");
		for (int a = 0; a < serviceNames.size(); a++) {
			comboBox_1.addItem(serviceNames.get(a));
		}

	}

	public void dayOfApp(String service, String activity) {
		ArrayList<String> activityDays = Utils.getActivityAppointmentDays(
				service, activity);
		comboBox_2.removeAllItems();
		;
		comboBox_2.addItem("Select Day");
		for (int a = 0; a < activityDays.size(); a++) {
			comboBox_2.addItem(activityDays.get(a));
		}

	}

	public void employee(String service, String activity, String day) {
		service += ".txt";
		ArrayList<String> employeeNames = Utils.getEmployeeNames(service,
				activity, day);
		comboBox_3.removeAllItems();
		;
		comboBox_3.addItem("Select Employee");
		for (int a = 0; a < employeeNames.size(); a++) {
			comboBox_3.addItem(employeeNames.get(a));
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

			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
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
			e.printStackTrace();
		}

	}
}
