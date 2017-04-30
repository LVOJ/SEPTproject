package BookingSystemGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import CommandLine.Utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BusinessOwnerPanel extends JFrame {
	private String select=null;
	private static Logger logger = Logger.getLogger(BusinessOwnerPanel.class.getName());
	private JPanel contentPane;
	private JPanel contentsPane;
	private JTextField textField_1;
	private JTextArea textField_2;
	private JTextArea textField_3;
	private JTextArea textField_4;
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private JComboBox comboBoxactive;
	private JTable table;
	private JButton Addactivity;
	private JPanel addsactivity;
	private JPanel bookingsummaries;
	private JPanel addActivity;
	private JTextField textField_5;
	private JPanel newservice;
	private JPanel deleteservice;
	private JComboBox servicedeleteCombo;
	private JTable table_1;
	private JComboBox comboBox_1;
	private JPanel empAvailable;
	private JPanel updateEmpWaorkingTime;
	private JTable table_2;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JComboBox durationCombo;
	private JPanel empWaorkingTime;
	private JTable table_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JTextField textField;
	private JComboBox selectService;
	private JComboBox selectActivity;
	private JComboBox selectDay;
	private JComboBox selectEmp;
	private JTable table_4;
	private JTextField selectCustomer;
	private JPanel bookForCustomer;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> temp = new ArrayList<>();

	public BusinessOwnerPanel(String[] userData) {
		setResizable(false);
		setTitle("Appointment Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 446);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(200, 10));
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(null);

		JButton btnNewEmployeeWorking = new JButton("Schedule Employee");
		btnNewEmployeeWorking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(empWaorkingTime);
			}
		});
		btnNewEmployeeWorking.setBounds(10, 102, 190, 23);
		panel_1.add(btnNewEmployeeWorking);

		JButton btnViewBookingSummaries = new JButton("View Booking Summaries");
		btnViewBookingSummaries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(bookingsummaries);
				try {
					FileReader fr = new FileReader("BookingSummaries.txt");
					BufferedReader br = new BufferedReader(fr);
					String line = "";
					ArrayList<String> bookings = new ArrayList<>();
					while ((line = br.readLine()) != null) {
						bookings.add(line);
					}
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					Object[] rowData = new Object[6];
					model.setRowCount(0);

					String[] recs = null;
					for (int i = 0; i < bookings.size(); i++) {
						recs = bookings.get(i).split(",");
						rowData[0] = recs[1] + " " + recs[2];
						rowData[1] = recs[4];
						rowData[2] = recs[5];
						rowData[3] = recs[6];
						rowData[4] = recs[7];
						rowData[5] = recs[8];
						model.addRow(rowData);
					}
					br.close();
				} catch (Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
				}

			}
		});
		btnViewBookingSummaries.setBounds(10, 222, 190, 23);
		panel_1.add(btnViewBookingSummaries);

		JButton btnNewService = new JButton("New Service");
		btnNewService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(newservice);
			}
		});
		btnNewService.setBounds(10, 11, 190, 23);
		panel_1.add(btnNewService);

		JButton btnDeleteService = new JButton("Delete Service");
		btnDeleteService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(deleteservice);
			}
		});
		btnDeleteService.setBounds(10, 500, 190, 23);
		panel_1.add(btnDeleteService);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(10, 373, 190, 23);
		panel_1.add(btnLogout);

		JButton btnNewButton = new JButton("Delete Service");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(deleteservice);
			}
		});
		btnNewButton.setBounds(10, 306, 190, 23);
		panel_1.add(btnNewButton);

		JButton btnEmployeesAvailable = new JButton("Employees Available");
		btnEmployeesAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(empAvailable);
			}
		});
		btnEmployeesAvailable.setBounds(10, 177, 190, 23);
		panel_1.add(btnEmployeesAvailable);

		JButton btnAddActivityTime = new JButton("Add Activity");
		btnAddActivityTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(addActivity);
			}
		});
		btnAddActivityTime.setBounds(10, 70, 190, 23);
		panel_1.add(btnAddActivityTime);
		JButton btnAddActivity = new JButton("Add Activity Time");
		btnAddActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(addsactivity);
			}
		});
		btnAddActivity.setBounds(10, 40, 190, 23);
		panel_1.add(btnAddActivity);

		JButton btnUpdateWorkingTime = new JButton("Update Working Time");
		btnUpdateWorkingTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(updateEmpWaorkingTime);
			}
		});
		btnUpdateWorkingTime.setBounds(10, 136, 190, 23);
		panel_1.add(btnUpdateWorkingTime);

		JButton btnBookForCustomer = new JButton("Book For Customer");
		btnBookForCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(bookForCustomer);

			}
		});
		btnBookForCustomer.setBounds(10, 258, 190, 23);
		panel_1.add(btnBookForCustomer);

		// Main panel for add activity simple
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		bookingsummaries = new JPanel();
		bookingsummaries.setVisible(false);

		addActivity = new JPanel();
		addActivity.setBounds(0, 0, 547, 373);
		panel_2.add(addActivity);
		addActivity.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		addActivity.add(panel_3, BorderLayout.NORTH);

		JLabel lblAddNewEmployee = new JLabel("Add Activity Time");
		panel_3.add(lblAddNewEmployee);

		JPanel panel_4 = new JPanel();
		addActivity.add(panel_4, BorderLayout.CENTER);

		comboBox = new JComboBox();
		comboBox.setBounds(112, 45, 153, 20);
		JLabel lblNumberOfActivities = new JLabel("Number Of Activities ");
		lblNumberOfActivities.setBounds(297, 48, 137, 14);
		comboBoxactive = new JComboBox();
		comboBoxactive.setBounds(390, 45, 153, 20);
		comboBox1 = new JComboBox();
		comboBox1.setBounds(112, 45, 153, 20);
		textField_1 = new JTextField();
		textField_1.setBounds(446, 45, 72, 20);
		textField_1.setColumns(10);

		JLabel lblWorkingDays = new JLabel("Working Days");
		lblWorkingDays.setBounds(82, 89, 102, 14);

		JLabel lblNewLabel = new JLabel("Enter Activities");
		lblNewLabel.setBounds(82, 177, 90, 14);

		JLabel lblWorkingTimes = new JLabel("Working Times");
		lblWorkingTimes.setBounds(82, 273, 121, 14);

		// ADD activity button
		JButton btnRegister = new JButton("Add Activity");
		btnRegister.setBounds(425, 323, 120, 23);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int duration = 0;
				if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Please select service name");
					return;
				}
				String num = textField_1.getText().trim();
				String activities = textField_3.getText().trim();
				String[] activitiesArr = activities.split(",");
				

				String fileName = "serviceactivity.txt";
				String name = "NULL";

				try {
					
					for (int i = 0; i < activitiesArr.length; i++) {
						if (!Utility.validateInput(activitiesArr[i], "([\\w][,]*)+", "Please enter an activity.")) {
							textField_3.grabFocus();
							return;
						}
					}
					if (!Utility.validateInput(num, "[\\d]+", "Please enter number of activities")) {
						textField_1.grabFocus();
						return;
					}

					
					int number = Integer.parseInt(num);
					if (activitiesArr.length != number || activitiesArr.length == 0) {
						JOptionPane.showMessageDialog(null, "Enter correct number of activities, separate by [,]");
						return;
					}
					else {
						FileWriter fw = new FileWriter(fileName, true); // open
																		// text
																		// file
																		// in
																		// writer
																		// append
																		// mode
						BufferedWriter bw = new BufferedWriter(fw); // gave
																	// access of
																	// file to
																	// buffer
																	// writer
						for (int a = 0; a < number; a++) {
							//String workingTimes[] = workingtimesArr[a].split(",");

							//int numOfSlots = 0;
							// System.out.println(name+","+dayArr[a]+","+activitiesArr[a]+","+workingTimes[1]+"-"+workingTimes[2]+",available");
							//SimpleDateFormat format = new SimpleDateFormat("HH:mm");
							//Date date1 = format.parse(workingTimes[0]);
							//Date date2 = format.parse(workingTimes[1]);
							//long diff = date2.getTime() - date1.getTime();
							//long diffMinutes = diff / (60 * 1000);
							//if (diffMinutes % duration != 0) {
								//JOptionPane.showMessageDialog(null,
									//	"The difference should not have remainders for this to work");
								//return;
							//}
							//else {
								//numOfSlots = (int) (diffMinutes / duration);
								//for (int d = 0; d < number; d++) {

									//Calendar cal = Calendar.getInstance();
									//cal.setTime(date1);
									//Time timeStart = new Time(cal.getTime().getTime());

									//cal.add(Calendar.MINUTE, duration);
									//Time timeEnd = new Time(cal.getTime().getTime());

									// System.out.println(name.toLowerCase()
									// +","+ dayArr[a].toLowerCase() +","+
									// activitiesArr[a].toLowerCase()
									// +","+timeStart+"-"+timeEnd+",available");
									bw.write(activitiesArr[a].toLowerCase()+comboBox.getSelectedItem().toString()+"aoa");
									bw.newLine();
									//date1 = new Date(timeEnd.getTime());
								//}
							}
						//}
						bw.close();
					}

					JOptionPane.showMessageDialog(null, "Service Entered successfully");
				} catch (Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
			}
		});

		panel_4.setLayout(null);
		panel_4.add(lblNewLabel);
		panel_4.add(lblNumberOfActivities);
		panel_4.add(btnRegister);
		panel_4.add(textField_1);
		// JScrollPane scrollPane_2 = new JScrollPane();
		// scrollPane_2.setBounds(182, 83, 208, 54);
		// panel_4.add(scrollPane_2);
		// Enter Workdays
		// Enter timing
		// Enter Activities
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(182, 171, 208, 63);
		panel_4.add(scrollPane_3);

		textField_3 = new JTextArea();
		scrollPane_3.setViewportView(textField_3);
		textField_3.setColumns(10);

		JLabel lblSelectServiced = new JLabel("Select Service");
		lblSelectServiced.setBounds(22, 48, 124, 14);
		panel_4.add(lblSelectServiced);

		addsactivity = new JPanel();
		addsactivity.setBounds(0, 0, 547, 400);
		panel_2.add(addsactivity);
		addsactivity.setLayout(new BorderLayout(0, 0));
		// bookingsummaries.setBounds(0, 0, 547, 373);

		JPanel panel_10 = new JPanel();
		addsactivity.add(panel_10, BorderLayout.NORTH);

		JLabel lblAddac = new JLabel("Add Activity");
		panel_10.add(lblAddac);

		JPanel panel_11 = new JPanel();
		addsactivity.add(panel_11, BorderLayout.CENTER);
		JLabel lblactivity = new JLabel("Select Activity:");
		lblactivity.setBounds(297, 48, 120, 14);

		JButton btnRegister2 = new JButton("Add Activity details");
		btnRegister2.setBounds(425, 323, 120, 23);
		btnRegister2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int duration = 0;
				if(comboBoxactive.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "");
				}
				if (durationCombo.getSelectedIndex() == 0) {
					duration = 30;
				}
				if (durationCombo.getSelectedIndex() == 1) {
					duration = 60;
				}
				String fileName=comboBox1.getSelectedItem().toString()+".txt";
				String days = textField_2.getText().trim();
				String workingtimes = textField_4.getText().trim();
				String name = "NULL";
				String[] dayArr = days.split(",");
				String[] workingtimesArr = workingtimes.split(";");
				for (int i = 0; i < dayArr.length; i++) {
					if (!Utility.validateInput(dayArr[i], "[\\w]+", "Please enter a weekday i.e. Monday-Friday")) {
						textField_2.grabFocus();
						return;
					}
					if (!Utility.validateDay(dayArr[i], "Please enter a valid weekday i.e. Monday-Friday")) {
						textField_2.grabFocus();
						return;
					}
					if (!Utility.validateInput(workingtimes, "^([\\d]{2}[:][\\d]{2}[,][\\d]{2}[:][\\d]{2}[,]*[;])+$",
							"Please enter a time i.e. 06:00,09:00; Start Time:End Time;")) {
						textField_4.grabFocus();
						return;
					}
				}
				if (dayArr.length != 1 || dayArr.length == 0) {
					JOptionPane.showMessageDialog(null, "Enter correct number of days, separate by [,]");
					return;
				}
				if (workingtimesArr.length != 1 || workingtimesArr.length == 0) {
					JOptionPane.showMessageDialog(null, "Enter correct number of working time, separate by [,]");
					return;
				}

				if (workingtimesArr.length != 1) {
					JOptionPane.showMessageDialog(null, "Enter correct number of working times, separated by [,]");
					return;
				}
				else {
					FileWriter fw=null;
					try {
						fw = new FileWriter(fileName, true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // open
																	// text
																	// file
																	// in
																	// writer
																	// append
																	// mode
					BufferedWriter bw = new BufferedWriter(fw); // gave
																// access of
																// file to
																// buffer
																// writer
					for (int a = 0; a < 1; a++) {
						String workingTimes[] = workingtimesArr[a].split(",");

						int numOfSlots = 0;
						// System.out.println(name+","+dayArr[a]+","+activitiesArr[a]+","+workingTimes[1]+"-"+workingTimes[2]+",available");
						SimpleDateFormat format = new SimpleDateFormat("HH:mm");
						Date date1 = null;
						try {
							date1 = format.parse(workingTimes[0]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Date date2 = null;
						try {
							date2 = format.parse(workingTimes[1]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						long diff = date2.getTime() - date1.getTime();
						long diffMinutes = diff / (60 * 1000);
						if (diffMinutes % duration != 0) {
							JOptionPane.showMessageDialog(null,
									"The difference should not have remainders for this to work");
							return;
						} else {
							numOfSlots = (int) (diffMinutes / duration);
							for (int d = 0; d < numOfSlots; d++) {

								Calendar cal = Calendar.getInstance();
								cal.setTime(date1);
								Time timeStart = new Time(cal.getTime().getTime());

								cal.add(Calendar.MINUTE, duration);
								Time timeEnd = new Time(cal.getTime().getTime());

								// System.out.println(name.toLowerCase()
								// +","+ dayArr[a].toLowerCase() +","+
								// activitiesArr[a].toLowerCase()
								// +","+timeStart+"-"+timeEnd+",available");
								try {
									bw.write(name.toLowerCase() + "," + dayArr[a].toLowerCase() + ","
											+ comboBoxactive.getSelectedItem().toString() + "," + timeStart + "-" + timeEnd
											+ ",available");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								try {
									bw.newLine();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								date1 = new Date(timeEnd.getTime());
							}
						}
					}
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				

				JOptionPane.showMessageDialog(null, "Activity Entered successfully");
			} 
			}
		});
		JLabel lblSelectServi = new JLabel("Select Service");
		lblSelectServi.setBounds(22, 48, 124, 14);
		panel_11.add(lblSelectServi);
		panel_11.setLayout(null);
		panel_11.add(comboBox1);
		panel_4.add(comboBox);
		panel_11.add(lblWorkingDays);
		panel_11.add(lblWorkingTimes);
		panel_11.add(btnRegister2);
		panel_11.add(lblactivity);
		panel_11.add(comboBoxactive);

		JScrollPane scrollPane_22 = new JScrollPane();
		scrollPane_22.setBounds(182, 83, 208, 54);
		panel_11.add(scrollPane_22);
		textField_2 = new JTextArea();
		scrollPane_22.setViewportView(textField_2);
		textField_2.setColumns(10);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(182, 254, 211, 61);
		panel_11.add(scrollPane_4);

		textField_4 = new JTextArea();
		scrollPane_4.setViewportView(textField_4);
		textField_4.setColumns(10);

		JLabel label = new JLabel("Activity Duration (minutes)");
		label.setBounds(22, 325, 150, 14);
		panel_11.add(label);

		durationCombo = new JComboBox();
		durationCombo.setModel(new DefaultComboBoxModel(new String[] { "30 min", "60 min" }));
		durationCombo.setBounds(182, 325, 208, 20);
		panel_11.add(durationCombo);
		panel_2.add(bookingsummaries);
		bookingsummaries.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 527, 328);
		bookingsummaries.add(scrollPane);

		table = new JTable();
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Customer", "Employee", "Day", "Service", "Activity", "Time Slot" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		scrollPane.setViewportView(table);

		newservice = new JPanel();
		newservice.setVisible(false);
		newservice.setBounds(0, 0, 547, 350);
		panel_2.add(newservice);
		newservice.setLayout(null);

		textField_5 = new JTextField();
		textField_5.setText("");
		textField_5.setBounds(159, 138, 147, 20);
		newservice.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblServiceName = new JLabel("Service Name");
		lblServiceName.setBounds(42, 140, 107, 17);
		newservice.add(lblServiceName);

		JButton btnSave = new JButton("Register Service");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String serviceName = textField_5.getText().trim();
				if (serviceName.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Service Name");
					return;
				}
				if (!Utility.validateInput(serviceName, "^[A-Za-z]+$",
						"Please enter valid service name e.g. hairdressing")) {
					textField_5.grabFocus();
					return;
				}
				// serviceName +=".txt";

				ArrayList<String> services = new ArrayList<String>();
				try {
					FileWriter fw = new FileWriter("services.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);

					BufferedReader br = new BufferedReader(new FileReader("services.txt"));
					String line = "";
					while ((line = br.readLine()) != null) {
						services.add(line);
					}
					if (!services.contains(serviceName)) {
						bw.write(serviceName);
						bw.newLine();
						JOptionPane.showMessageDialog(null, "Service created");
					} else {
						JOptionPane.showMessageDialog(null, "Service exists");
					}

					bw.close();
					// services();

				} catch (Exception e2) {
					logger.log(Level.SEVERE, e2.getMessage());
				}
				dispose();
				new BusinessOwnerPanel(userData).setVisible(true);
			}
		});
		btnSave.setBounds(159, 169, 147, 23);
		newservice.add(btnSave);

		deleteservice = new JPanel();
		deleteservice.setVisible(false);
		deleteservice.setBounds(0, 0, 547, 350);
		panel_2.add(deleteservice);
		deleteservice.setLayout(null);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(81, 155, 183, 23);
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (servicedeleteCombo.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Please Select Service To Delete");
					return;
				}
				try {
					String serviceName = servicedeleteCombo.getSelectedItem().toString();

					// Delete service from services list
					BufferedReader reader = new BufferedReader(new FileReader("services.txt"));

					String lineToRemove = serviceName;
					String currentLine;
					ArrayList<String> list = new ArrayList<>();
					while ((currentLine = reader.readLine()) != null) {
						if (!currentLine.equals(lineToRemove)) {
							list.add(currentLine);

						} else {
							System.out.println("Serice " + currentLine);
						}
					}
					System.out.println("Services " + list);
					BufferedWriter writer = new BufferedWriter(new FileWriter("services.txt"));
					for (int a = 0; a < list.size(); a++) {
						writer.write(list.get(a));
						writer.newLine();

					}
					writer.close();
					reader.close();
					// Delete service from services list
					reader = new BufferedReader(new FileReader("BookingSummaries.txt"));

					lineToRemove = serviceName;
					list.clear();
					String[] recs;
					while ((currentLine = reader.readLine()) != null) {
						recs = currentLine.split(",");
						if (!recs[6].equals(lineToRemove)) {
							list.add(currentLine);
						} else {
							System.out.println("Serice " + currentLine);
						}
					}

					writer = new BufferedWriter(new FileWriter("BookingSummaries.txt"));

					for (int a = 0; a < list.size(); a++) {
						writer.write(list.get(a));
						writer.newLine();
					}
					writer.close();
					reader.close();
					String fileName = serviceName + ".txt";
					File file = new File(fileName);
					file.delete();
					// services();
					JOptionPane.showMessageDialog(null, "Deleted Successfully");
				} catch (IOException e1) {
					logger.log(Level.SEVERE, e1.getMessage());
				}
				dispose();
				new BusinessOwnerPanel(userData).setVisible(true);
			}
		});
		deleteservice.add(btnDelete);

		servicedeleteCombo = new JComboBox();
		servicedeleteCombo.setModel(new DefaultComboBoxModel(new String[] { "--Select Service--" }));
		servicedeleteCombo.setBounds(81, 59, 183, 20);
		deleteservice.add(servicedeleteCombo);

		empAvailable = new JPanel();
		empAvailable.setVisible(false);
		empAvailable.setBounds(10, 0, 537, 362);
		panel_2.add(empAvailable);
		empAvailable.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 46, 517, 305);
		empAvailable.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setRowHeight(25);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Employee Name", "Day", "Activity", "Time Available", "Status" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);

		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				empAvailable(comboBox_1.getSelectedItem().toString());
			}
		});
		comboBox_1.setBounds(202, 11, 194, 25);
		empAvailable.add(comboBox_1);

		JLabel lblSelectService = new JLabel("Select Service");
		lblSelectService.setBounds(74, 11, 118, 25);
		empAvailable.add(lblSelectService);

		updateEmpWaorkingTime = new JPanel();
		updateEmpWaorkingTime.setVisible(false);
		updateEmpWaorkingTime.setBounds(0, 0, 547, 407);
		panel_2.add(updateEmpWaorkingTime);
		updateEmpWaorkingTime.setLayout(null);

		comboBox_4 = new JComboBox();
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_4.getSelectedIndex() != 0) {
					empWorkingTimeNotNull(comboBox_4.getSelectedItem().toString());
					employees(comboBox_4.getSelectedItem().toString());
				}
			}
		});
		comboBox_4.setBounds(153, 24, 202, 20);
		updateEmpWaorkingTime.add(comboBox_4);

		JLabel label_1 = new JLabel("Select Service");
		label_1.setBounds(46, 25, 102, 14);
		updateEmpWaorkingTime.add(label_1);

		JLabel label_2 = new JLabel("Employee Name");
		label_2.setBounds(24, 339, 136, 14);
		updateEmpWaorkingTime.add(label_2);

		comboBox_5 = new JComboBox();
		comboBox_5.setEditable(true);
		comboBox_5.setBounds(138, 335, 182, 20);
		updateEmpWaorkingTime.add(comboBox_5);

		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_4.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Please Select Service To Proceed");
					return;
				}
				if (comboBox_5.getSelectedItem().toString().trim().equals("")
						|| comboBox_3.getSelectedItem().toString().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Employee name to proceed");
					return;
				}
				if (textField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter new Working time to proceed");
					return;
				}

				String newtimeAvailable = textField.getText().replace(",", "-");

				String service = comboBox_4.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = -1;
				row = table_3.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Please Select row to update to proceed");
					return;
				}
				String selected = "";
				String modify = "";

				selected = table_3.getModel().getValueAt(row, 0).toString() + ","
						+ table_3.getModel().getValueAt(row, 1).toString() + ","
						+ table_3.getModel().getValueAt(row, 2).toString() + ","
						+ table_3.getModel().getValueAt(row, 3).toString() + ","
						+ table_3.getModel().getValueAt(row, 4).toString();

				modify = comboBox_5.getSelectedItem().toString() + ","
						+ table_3.getModel().getValueAt(row, 1).toString() + ","
						+ table_3.getModel().getValueAt(row, 2).toString() + "," + newtimeAvailable + ","
						+ table_3.getModel().getValueAt(row, 4).toString();

				try {
					ArrayList<String> list = new ArrayList<>();
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					String currentLine = "";
					while ((currentLine = br.readLine()) != null) {
						list.add(currentLine);
					}
					br.close();
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
					for (int a = 0; a < list.size(); a++) {
						if (list.get(a).equals(selected)) {
							bw.write(modify + System.getProperty("line.separator"));
						} else {
							bw.write(list.get(a) + System.getProperty("line.separator"));
						}
					}
					bw.close();

				} catch (IOException e1) {
					logger.log(Level.SEVERE, e1.getMessage());
				}
				JOptionPane.showMessageDialog(null, "Employee working time updated successfully");
				empWorkingTimeNotNull(comboBox_4.getSelectedItem().toString());
				employees(comboBox_4.getSelectedItem().toString());
				textField.setText("");
			}
		});
		button_2.setBounds(354, 335, 102, 23);
		updateEmpWaorkingTime.add(button_2);

		JButton button_3 = new JButton("Delete");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_4.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Please Select Service To Proceed");
					return;
				}
				String service = comboBox_4.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = -1;
				row = table_3.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Please Select row to delete to proceed");
					return;
				}
				String selected = "";
				selected = table_3.getModel().getValueAt(row, 0).toString() + ","
						+ table_3.getModel().getValueAt(row, 1).toString() + ","
						+ table_3.getModel().getValueAt(row, 2).toString() + ","
						+ table_3.getModel().getValueAt(row, 3).toString() + ","
						+ table_3.getModel().getValueAt(row, 4).toString();

				try {
					ArrayList<String> list = new ArrayList<>();
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					String currentLine = "";
					while ((currentLine = br.readLine()) != null) {
						if (!currentLine.equals(selected)) {
							list.add(currentLine);
						}
					}
					br.close();
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
					for (int a = 0; a < list.size(); a++) {
						bw.write(list.get(a) + System.getProperty("line.separator"));
					}
					bw.close();

				} catch (IOException e1) {
					logger.log(Level.SEVERE, e1.getMessage());
				}
				JOptionPane.showMessageDialog(null, "Employee working time deleted successfully");
				empWorkingTimeNotNull(comboBox_4.getSelectedItem().toString());
				employees(comboBox_4.getSelectedItem().toString());
				textField.setText("");
			}
		});
		button_3.setBounds(354, 373, 102, 23);
		updateEmpWaorkingTime.add(button_3);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 73, 527, 251);
		updateEmpWaorkingTime.add(scrollPane_6);

		table_3 = new JTable();
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_3.getSelectedRow();
				String employee = table_3.getModel().getValueAt(row, 0).toString();
				String timeAvailable = table_3.getModel().getValueAt(row, 3).toString();
				comboBox_5.setSelectedItem(employee);
				String[] slitTime = timeAvailable.split("-");
				textField.setText(slitTime[0] + "," + slitTime[1]);
			}
		});
		table_3.setRowHeight(25);
		table_3.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Employee Name", "Day", "Activity", "Time Available", "Status" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_6.setViewportView(table_3);

		JLabel lblTimeAvailable = new JLabel("Time Available");
		lblTimeAvailable.setBounds(24, 377, 108, 14);
		updateEmpWaorkingTime.add(lblTimeAvailable);

		textField = new JTextField();
		textField.setSize(179, 20);
		textField.setLocation(141, 377);
		updateEmpWaorkingTime.add(textField);

		empWaorkingTime = new JPanel();
		empWaorkingTime.setVisible(false);
		empWaorkingTime.setBounds(0, 0, 547, 396);
		empWaorkingTime.setLayout(null);
		panel_2.add(empWaorkingTime);

		comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_2.getSelectedIndex() != 0) {
					empWorkingTime(comboBox_2.getSelectedItem().toString());
					employees(comboBox_2.getSelectedItem().toString());
				}
			}
		});
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox1.getSelectedIndex() != 0) {
					comboBoxactive.removeAllItems();
					comboBoxactive.addItem("Select Activity");
					String filename = "serviceactivity.txt";
					BufferedReader br;
					select=comboBox1.getSelectedItem().toString();
					try {
						br = new BufferedReader(new FileReader(filename));
					} catch (FileNotFoundException e) {
						return;
					}
					ArrayList<String> services = new ArrayList<String>();
					String line = "";
					try {
						while ((line = br.readLine()) != null) {
							services.add(line);
						}
					} catch (IOException e) {
						return;
					}
					for (int i = 0; i < services.size(); i++) {
						if (services.get(i).endsWith(comboBox1.getSelectedItem().toString() + "aoa")) {
							String a = services.get(i);
							int as = services.get(i).length() - comboBox1.getSelectedItem().toString().length() - 3;
							a = a.substring(0, as);
							comboBoxactive.addItem(a);
						}
					}

				}
			}
		});
		comboBox_2.setBounds(156, 11, 202, 20);
		empWaorkingTime.add(comboBox_2);

		JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_2.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Please Select Service To Proceed");
					return;
				}
				if (comboBox_3.getSelectedItem().toString().trim().equals("")
						|| comboBox_3.getSelectedItem().toString().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Employee name to proceed");
					return;
				}
				String employeeName = comboBox_3.getSelectedItem().toString().trim();
				if (!Utility.validateInput(employeeName, "[A-Za-z]+", "Please enter a valid name")) {
					comboBox.grabFocus();
					return;
				}
				String service = comboBox_2.getSelectedItem().toString();
				String fileName = service + ".txt";
				int rows = table_2.getRowCount();
				int row = 0;
				String selected = "";
				String modify = "";
				for (row = 0; row < rows; row++) {
					boolean status = (boolean) table_2.getModel().getValueAt(row, 5);
					if (status == true) {
						selected = table_2.getModel().getValueAt(row, 0).toString() + ","
								+ table_2.getModel().getValueAt(row, 1).toString() + ","
								+ table_2.getModel().getValueAt(row, 2).toString() + ","
								+ table_2.getModel().getValueAt(row, 3).toString() + ","
								+ table_2.getModel().getValueAt(row, 4).toString();
						modify = comboBox_3.getSelectedItem().toString() + ","
								+ table_2.getModel().getValueAt(row, 1).toString() + ","
								+ table_2.getModel().getValueAt(row, 2).toString() + ","
								+ table_2.getModel().getValueAt(row, 3).toString() + ","
								+ table_2.getModel().getValueAt(row, 4).toString();

						try {
							ArrayList<String> list = new ArrayList<>();
							BufferedReader br = new BufferedReader(new FileReader(fileName));
							String currentLine = "";
							while ((currentLine = br.readLine()) != null) {
								list.add(currentLine);
							}
							br.close();
							BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
							for (int a = 0; a < list.size(); a++) {
								if (list.get(a).equals(selected)) {
									bw.write(modify + System.getProperty("line.separator"));
								} else {
									bw.write(list.get(a) + System.getProperty("line.separator"));
								}
							}
							bw.close();

						} catch (IOException e) {
							logger.log(Level.SEVERE, e.getMessage());
						}

					}
				}
				JOptionPane.showMessageDialog(null, "Employee Assigned activity successfully");
				empWorkingTime(comboBox_2.getSelectedItem().toString());
				employees(comboBox_2.getSelectedItem().toString());
			}
		});
		button.setBounds(348, 289, 102, 23);
		empWaorkingTime.add(button);

		JLabel lblSelectService_1 = new JLabel("Select Service");
		lblSelectService_1.setBounds(22, 14, 102, 14);
		empWaorkingTime.add(lblSelectService_1);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 42, 527, 236);
		empWaorkingTime.add(scrollPane_5);

		table_2 = new JTable();
		table_2.setRowHeight(25);
		table_2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Employee Name", "Day", "Activity", "Time Available", "Status", "Select" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_5.setViewportView(table_2);

		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(36, 293, 136, 14);
		empWaorkingTime.add(lblEmployeeName);

		comboBox_3 = new JComboBox();
		comboBox_3.setEditable(true);
		comboBox_3.setBounds(156, 290, 182, 20);
		empWaorkingTime.add(comboBox_3);

		bookForCustomer = new JPanel();
		bookForCustomer.setVisible(false);
		bookForCustomer.setBounds(0, 0, 547, 407);
		panel_2.add(bookForCustomer);
		bookForCustomer.setLayout(null);

		JLabel label_3 = new JLabel("Select Service");
		label_3.setBounds(10, 14, 127, 14);
		bookForCustomer.add(label_3);

		selectService = new JComboBox();
		selectService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectService.getSelectedIndex() != 0) {
					try {
						activity(selectService.getSelectedItem().toString());
					} catch (Exception e1) {
						logger.log(Level.SEVERE, e1.getMessage());
					}
				}
			}
		});
		selectService.setBounds(103, 11, 143, 20);
		bookForCustomer.add(selectService);

		JLabel label_4 = new JLabel("Select Activity");
		label_4.setBounds(276, 14, 120, 14);
		bookForCustomer.add(label_4);

		selectActivity = new JComboBox();
		selectActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectActivity.getSelectedIndex() != 0) {
					try {
						dayOfApp(selectService.getSelectedItem().toString(),
								selectActivity.getSelectedItem().toString());
					} catch (Exception e1) {
						logger.log(Level.SEVERE, e1.getMessage());
					}
				}
			}
		});
		selectActivity.setBounds(401, 11, 136, 20);
		bookForCustomer.add(selectActivity);

		JLabel label_5 = new JLabel("Select Day of Appointment");
		label_5.setBounds(10, 49, 158, 14);
		bookForCustomer.add(label_5);

		selectDay = new JComboBox();
		selectDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectDay.getSelectedIndex() != 0) {
					try {
						employee(selectService.getSelectedItem().toString(),
								selectActivity.getSelectedItem().toString(), selectDay.getSelectedItem().toString());
					} catch (Exception e1) {
						logger.log(Level.SEVERE, e1.getMessage());
					}
				}
			}
		});
		selectDay.setBounds(178, 47, 172, 20);
		bookForCustomer.add(selectDay);

		JLabel label_6 = new JLabel("Select Employee");
		label_6.setBounds(10, 87, 158, 14);
		bookForCustomer.add(label_6);

		selectEmp = new JComboBox();
		selectEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectEmp.getSelectedIndex() != 0) {
					try {
						availableSlots(selectService.getSelectedItem().toString(),
								selectActivity.getSelectedItem().toString(), selectDay.getSelectedItem().toString(),
								selectEmp.getSelectedItem().toString());
					} catch (Exception e1) {
						logger.log(Level.SEVERE, e1.getMessage());

					}
				}
			}
		});
		selectEmp.setBounds(129, 83, 221, 20);
		bookForCustomer.add(selectEmp);

		JLabel label_7 = new JLabel("Select Time Above and Save");
		label_7.setBounds(26, 382, 197, 14);
		bookForCustomer.add(label_7);

		JButton button_1 = new JButton("Save");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectCustomer.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Select Customer To Book For");
					return;
				}
				String customerName = selectCustomer.getText().trim();
				if (!Utility.validateInput(customerName, "^[A-Za-z]+$", "Please enter a valid name i.e. Jim")) {
					selectCustomer.grabFocus();
					return;
				}
				String service = selectService.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = 0;
				int rows = table_4.getRowCount();
				String selected = "";
				for (row = 0; row < rows; row++) {
					try {
						boolean status = (boolean) table_4.getModel().getValueAt(row, 5);
						if (status == true) {
							selected = table_4.getModel().getValueAt(row, 0).toString() + ","
									+ table_4.getModel().getValueAt(row, 1).toString() + ","
									+ table_4.getModel().getValueAt(row, 2).toString() + ","
									+ table_4.getModel().getValueAt(row, 3).toString() + ","
									+ table_4.getModel().getValueAt(row, 4).toString();
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

							String modified = recs[0] + "," + recs[1] + "," + recs[2] + "," + recs[3] + "," + recs[4];

							BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
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
							BufferedWriter writer2 = new BufferedWriter(new FileWriter("BookingSummaries.txt", true));
							String customerInfo = selectCustomer.getText();
							String[] customerData = customerInfo.split(",");
							writer2.write("Customer," + customerData[0] + "," + customerData[1]
									+ ",booked Appointment on," + recs[0] + "," + recs[1] + ","
									+ /* servicename */service + "," + recs[2] + "," + recs[3]);
							writer2.newLine();
							writer2.close();
						}
					} catch (Exception e1) {
						// e.printStackTrace();
					}

				}

				if (selectEmp.getSelectedIndex() != 0) {
					try {
						availableSlots(selectService.getSelectedItem().toString(),
								selectActivity.getSelectedItem().toString(), selectDay.getSelectedItem().toString(),
								selectEmp.getSelectedItem().toString());
					} catch (Exception e1) {
						logger.log(Level.SEVERE, e1.getMessage());

					}
				}
				selectCustomer.setText("");
				JOptionPane.showMessageDialog(null, "Successfully Booked");

			}
		});
		button_1.setBounds(363, 373, 89, 23);
		bookForCustomer.add(button_1);

		JLabel lblSelectCustomer = new JLabel("Enter Customer");
		lblSelectCustomer.setBounds(10, 123, 100, 14);
		bookForCustomer.add(lblSelectCustomer);

		selectCustomer = new JTextField();
		selectCustomer.setBounds(129, 120, 221, 20);
		bookForCustomer.add(selectCustomer);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(10, 148, 537, 223);
		bookForCustomer.add(scrollPane_7);

		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Employee", "Day", "Service", "Time", "Availability", "Book" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table_4.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(80);
		table_4.getColumnModel().getColumn(4).setPreferredWidth(70);
		table_4.getColumnModel().getColumn(5).setPreferredWidth(50);
		table_4.setRowHeight(25);

		scrollPane_7.setViewportView(table_4);
		services();

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
			comboBox.removeAllItems();
			comboBox1.removeAllItems();
			servicedeleteCombo.removeAllItems();
			comboBox_1.removeAllItems();
			comboBox_2.removeAllItems();
			comboBox_4.removeAllItems();
			selectService.removeAllItems();
			servicedeleteCombo.addItem("Select Service");
			comboBox.addItem("Select Services");
			comboBoxactive.addItem("Select Activity");
			comboBox1.addItem("Select Sercvice");
			comboBox_2.addItem("Select Services");
			comboBox_4.addItem("Select Services");
			selectService.addItem("Select Services");
			for (int a = 0; a < services.size(); a++) {
				comboBox.addItem(services.get(a));
				comboBox1.addItem(services.get(a));
				servicedeleteCombo.addItem(services.get(a));
				comboBox_1.addItem(services.get(a));
				comboBox_2.addItem(services.get(a));
				comboBox_4.addItem(services.get(a));
				selectService.addItem(services.get(a));
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	public void employees(String service) {
		String fileName = service + ".txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			ArrayList<String> employeees = new ArrayList<String>();
			String line = "";
			String[] recs = null;
			while ((line = br.readLine()) != null) {
				recs = line.split(",");
				if (!employeees.contains(recs[0]) && !recs[0].equals("null")) {
					employeees.add(recs[0]);
				}
			}
			comboBox_5.removeAllItems();
			comboBox_3.removeAllItems();
			for (int a = 0; a < employeees.size(); a++) {
				comboBox_3.addItem(employeees.get(a));
				comboBox_5.addItem(employeees.get(a));
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	public void empAvailable(String service) {
		try {
			String fileName = service + ".txt";
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName));
			String line = "";
			ArrayList<String> list = new ArrayList<>();
			String recs[] = null;
			while ((line = br.readLine()) != null) {
				recs = line.split(",");
				if (recs[0].equals("null") || recs[4].equals("available")) {
					list.add(line);
				}
			}

			br.close();
			int i;

			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
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
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	public void empWorkingTime(String service) {
		try {
			String fileName = service + ".txt";
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName));
			String line = "";
			ArrayList<String> list = new ArrayList<>();
			String recs[] = null;
			while ((line = br.readLine()) != null) {
				recs = line.split(",");
				if (recs[0].equals("null")) {
					list.add(line);
				}
			}

			br.close();
			int i;

			DefaultTableModel model = (DefaultTableModel) table_2.getModel();
			model.setRowCount(0);
			Object[] rowData = new Object[6];
			for (i = 0; i < list.size(); i++) {
				recs = list.get(i).split(",");
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

	public void empWorkingTimeNotNull(String service) {
		try {
			String fileName = service + ".txt";
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName));
			String line = "";
			ArrayList<String> list = new ArrayList<>();
			String recs[] = null;
			while ((line = br.readLine()) != null) {
				recs = line.split(",");
				if (!recs[0].equals("null")) {
					list.add(line);
				}
			}

			br.close();
			int i;

			DefaultTableModel model = (DefaultTableModel) table_3.getModel();
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
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	public void panelToSee(JPanel panel) {
		addActivity.setVisible(false);
		addsactivity.setVisible(false);
		bookingsummaries.setVisible(false);
		newservice.setVisible(false);
		deleteservice.setVisible(false);
		empAvailable.setVisible(false);
		empWaorkingTime.setVisible(false);
		updateEmpWaorkingTime.setVisible(false);
		bookForCustomer.setVisible(false);
		System.out.println(panel + " hows");
		panel.setVisible(true);
		panel.setBounds(0, 0, 547, 407);
		System.out.println(panel + " hows");
	}

	public void activity(String service) {
		ArrayList<String> serviceNames = Utils.getActivities(service);
		selectActivity.removeAllItems();
		selectActivity.addItem("Select Activity");
		for (int a = 0; a < serviceNames.size(); a++) {
			selectActivity.addItem(serviceNames.get(a));
		}

	}

	public void dayOfApp(String service, String activity) {
		ArrayList<String> activityDays = Utils.getActivityAppointmentDays(service, activity);
		selectDay.removeAllItems();
		selectDay.addItem("Select Day");
		for (int a = 0; a < activityDays.size(); a++) {
			selectDay.addItem(activityDays.get(a));
		}

	}

	public void employee(String service, String activity, String day) {
		service += ".txt";
		ArrayList<String> employeeNames = Utils.getEmployeeNames(service, activity, day);
		selectEmp.removeAllItems();
		;
		selectEmp.addItem("Select Employee");
		for (int a = 0; a < employeeNames.size(); a++) {
			selectEmp.addItem(employeeNames.get(a));
		}

	}

	public void availableSlots(String service, String activity, String day, String empName) {
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
								&& (recs[0].toLowerCase().equals(empName) || empName.equals("*"))
								&& (recs[2].toLowerCase().equals(activity)))) {
					temp.add(list.get(a));
					one++;
				}
			}
			br.close();
			int i;

			DefaultTableModel model = (DefaultTableModel) table_4.getModel();
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
	// public void customers(){
	// String fileName = "customerinfo.txt";
	// BufferedReader br;
	// try {
	// br = new BufferedReader(new FileReader(fileName));
	// String line = "";
	// String[] recs = null;
	// selectCustomer.removeAllItems();
	// selectCustomer.addItem("Select Customer");
	// while ((line = br.readLine()) != null) {
	// recs = line.split(",");
	// selectCustomer.addItem(recs[0]+","+recs[1]);
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// }
}
