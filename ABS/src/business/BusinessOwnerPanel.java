package business;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
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

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import utility.Utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JPasswordField;

import account.Login;

public class BusinessOwnerPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel bookingsummaries;
	private JTextField textField_5;
	private JPanel newservice;
	private JPanel deleteservice;
	private JComboBox servicedeleteCombo;
	private JTable table_1;
	private JComboBox comboBox_1;
	private JPanel empAvailable;
	private JPanel updateEmpWaorkingTime;
	private JComboBox comboBox_3;
	private JPanel empWaorkingTime;
	private JTable table_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JComboBox selectService;
	private JComboBox selectActivity;
	private JComboBox selectDay;
	private JComboBox selectEmp;
	private JTable table_4;
	private JTextField selectCustomer;
	private JPanel bookForCustomer;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> temp = new ArrayList<>();
	String[] userData;
	private Image img;
	private JLabel businessTitle;
	private JComboBox comboBox_6;
	private JComboBox comboBox_7;
	private JPanel addActivity;
	private JTextField textField_6;
	private JComboBox comboBox_8;
	private FileChannel chanel;
	private FileLock lock;
	private JComboBox comboBox_16;
	private JComboBox comboBox_17;
	private JComboBox comboBox;
	private JComboBox comboBox_13;
	private ArrayList<String> daysList = new ArrayList<>();
	private JComboBox comboBox_2;
	private JComboBox comboBox_9;
	private JComboBox comboBox_10;
	private JComboBox comboBox_11;
	private JComboBox comboBox_12;

	public BusinessOwnerPanel(String[] userData) {
		setResizable(false);
		this.userData = userData;

		daysList.add("monday");
		daysList.add("tuesday");
		daysList.add("wednesday");
		daysList.add("thursday");
		daysList.add("friday");

		setTitle("Appointment Booking System");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				BusinessOwnerPanel.class.getResource("/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 517);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(102, 204, 102));
		setJMenuBar(menuBar);

		JMenu mnBusiness = new JMenu("Business");
		menuBar.add(mnBusiness);

		JMenuItem mntmDetails = new JMenuItem("Details");
		mntmDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BusinessName(BusinessOwnerPanel.this, userData)
						.setVisible(true);
				businessTitle.setText(fillBusinessData());
			}
		});
		mnBusiness.add(mntmDetails);

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

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(200, 10));
		panel_1.setBackground(new Color(192, 192, 192));
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(null);

		JButton btnNewEmployeeWorking = new JButton("Employee Working Time");
		btnNewEmployeeWorking.setForeground(Color.BLACK);
		btnNewEmployeeWorking.setBackground(SystemColor.activeCaption);
		btnNewEmployeeWorking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(empWaorkingTime);
			}
		});
		btnNewEmployeeWorking.setBounds(10, 102, 180, 23);
		panel_1.add(btnNewEmployeeWorking);

		JButton btnViewBookingSummaries = new JButton("View Booking Summaries");
		btnViewBookingSummaries.setForeground(Color.BLACK);
		btnViewBookingSummaries.setBackground(SystemColor.activeCaption);
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
					DefaultTableModel model = (DefaultTableModel) table
							.getModel();
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
					e.printStackTrace();
				}

			}
		});
		btnViewBookingSummaries.setBounds(10, 222, 180, 23);
		panel_1.add(btnViewBookingSummaries);

		JButton btnNewService = new JButton("New Service");
		btnNewService.setForeground(Color.BLACK);
		btnNewService.setBackground(SystemColor.activeCaption);
		btnNewService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(newservice);
			}
		});
		btnNewService.setBounds(10, 11, 180, 23);
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
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setBackground(SystemColor.activeCaption);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(10, 373, 180, 23);
		panel_1.add(btnLogout);

		JButton btnNewButton = new JButton("Delete Service");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(deleteservice);
			}
		});
		btnNewButton.setBounds(10, 306, 180, 23);
		panel_1.add(btnNewButton);

		JButton btnEmployeesAvailable = new JButton("Employees Available");
		btnEmployeesAvailable.setForeground(Color.BLACK);
		btnEmployeesAvailable.setBackground(SystemColor.activeCaption);
		btnEmployeesAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(empAvailable);
			}
		});
		btnEmployeesAvailable.setBounds(10, 177, 180, 23);
		panel_1.add(btnEmployeesAvailable);

		JButton btnAddActivityTime = new JButton("Add Service Activity");
		btnAddActivityTime.setForeground(Color.BLACK);
		btnAddActivityTime.setBackground(SystemColor.activeCaption);
		btnAddActivityTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelToSee(addActivity);// addActivity
			}
		});
		btnAddActivityTime.setBounds(10, 60, 180, 23);
		panel_1.add(btnAddActivityTime);

		JButton btnUpdateWorkingTime = new JButton("Update Working Time");
		btnUpdateWorkingTime.setForeground(Color.BLACK);
		btnUpdateWorkingTime.setBackground(SystemColor.activeCaption);
		btnUpdateWorkingTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(updateEmpWaorkingTime);
			}
		});
		btnUpdateWorkingTime.setBounds(10, 136, 180, 23);
		panel_1.add(btnUpdateWorkingTime);

		JButton btnBookForCustomer = new JButton("Book For Customer");
		btnBookForCustomer.setForeground(Color.BLACK);
		btnBookForCustomer.setBackground(SystemColor.activeCaption);
		btnBookForCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToSee(bookForCustomer);

			}
		});
		btnBookForCustomer.setBounds(10, 258, 180, 23);
		panel_1.add(btnBookForCustomer);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		bookingsummaries = new JPanel();
		bookingsummaries.setVisible(false);
		bookingsummaries.setBounds(0, 500, 557, 373);
		panel_2.add(bookingsummaries);
		bookingsummaries.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 537, 315);
		bookingsummaries.add(scrollPane);

		table = new JTable();
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Customer", "Employee", "Day", "Service", "Activity",
				"Time Slot" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		scrollPane.setViewportView(table);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(0, 0, 557, 24);
		bookingsummaries.add(panel_5);

		JLabel lblBookingSummary = new JLabel("Booking Summary");
		panel_5.add(lblBookingSummary);

		newservice = new JPanel();
		newservice.setVisible(false);
		newservice.setBounds(0, 500, 557, 350);
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

				String serviceName = textField_5.getText().trim().toLowerCase();
				if (serviceName.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Service Name");
					return;
				}
				// serviceName +=".txt";

				ArrayList<String> services = new ArrayList<String>();
				try {
					FileWriter fw = new FileWriter("services.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);

					BufferedReader br = new BufferedReader(new FileReader(
							"services.txt"));
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
					e2.printStackTrace();
				}
				dispose();
				new BusinessOwnerPanel(userData).setVisible(true);
			}
		});
		btnSave.setBounds(159, 169, 147, 23);
		newservice.add(btnSave);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_6.setBounds(0, 0, 557, 24);
		newservice.add(panel_6);

		JLabel lblNewServiceRegistration = new JLabel(
				"New Service Registration");
		panel_6.add(lblNewServiceRegistration);

		deleteservice = new JPanel();
		deleteservice.setVisible(false);
		deleteservice.setBounds(0, 500, 557, 350);
		panel_2.add(deleteservice);
		deleteservice.setLayout(null);

		JButton btnDelete = new JButton("Delete Service");
		btnDelete.setBounds(182, 131, 183, 23);
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (servicedeleteCombo.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please Select Service To Delete");
					return;
				}
				try {
					String serviceName = servicedeleteCombo.getSelectedItem()
							.toString();

					// Delete service from services list
					BufferedReader reader = new BufferedReader(new FileReader(
							"services.txt"));

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
					BufferedWriter writer = new BufferedWriter(new FileWriter(
							"services.txt"));
					for (int a = 0; a < list.size(); a++) {
						writer.write(list.get(a));
						writer.newLine();

					}
					writer.close();
					reader.close();
					// Delete service from services list
					reader = new BufferedReader(new FileReader(
							"BookingSummaries.txt"));

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

					writer = new BufferedWriter(new FileWriter(
							"BookingSummaries.txt"));

					for (int a = 0; a < list.size(); a++) {
						writer.write(list.get(a));
						writer.newLine();
					}
					writer.close();
					reader.close();
					String fileName = serviceName + ".txt";
					File file = new File(fileName);
					chanel = new RandomAccessFile(file, "rw").getChannel();

					try {
						lock = chanel.tryLock();

					} catch (OverlappingFileLockException es) {
						closeLock();
					}
					closeLock();
					deleteFile(file);
					// services();
					JOptionPane.showMessageDialog(null,
							"Service Deleted Successfully");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose();
				new BusinessOwnerPanel(userData).setVisible(true);
			}
		});
		deleteservice.add(btnDelete);

		servicedeleteCombo = new JComboBox();
		servicedeleteCombo.setModel(new DefaultComboBoxModel(
				new String[] { "--Select Service--" }));
		servicedeleteCombo.setBounds(182, 35, 183, 20);
		deleteservice.add(servicedeleteCombo);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setBounds(0, 0, 557, 24);
		deleteservice.add(panel_7);

		JLabel lblDeleteServicesHere = new JLabel("Delete Services Here");
		panel_7.add(lblDeleteServicesHere);

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.LIGHT_GRAY);
		panel_12.setBounds(0, 185, 557, 24);
		deleteservice.add(panel_12);

		JLabel lblDeleteActivityHere = new JLabel("Delete Activity Here Here");
		panel_12.add(lblDeleteActivityHere);

		comboBox_6 = new JComboBox();
		comboBox_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_6.getSelectedIndex() != 0) {
					ArrayList<String> serviceNames = Utils
							.getActivities(comboBox_6.getSelectedItem()
									.toString());
					comboBox_7.removeAllItems();
					comboBox_7.addItem("Select Activity");
					for (int a = 0; a < serviceNames.size(); a++) {
						comboBox_7.addItem(serviceNames.get(a));
					}
				}
			}
		});
		comboBox_6.setBounds(182, 220, 183, 20);
		deleteservice.add(comboBox_6);

		JButton btnDeleteActivity = new JButton("Delete Activity");
		btnDeleteActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_6.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Select Service Where to delete Activity");
					return;
				}
				if (comboBox_7.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Select Activity to delete Activity");
					return;
				}
				String service = comboBox_6.getSelectedItem().toString();
				String activity = comboBox_7.getSelectedItem().toString();

				String fileName = service + ".txt";
				try {
					ArrayList<String> list = new ArrayList<>();
					BufferedReader br = new BufferedReader(new FileReader(
							fileName));
					String currentLine = "";
					while ((currentLine = br.readLine()) != null) {
						String[] recs = currentLine.split(",");
						if (!recs[2].equals(activity)) {
							list.add(currentLine);
						}
					}
					br.close();
					BufferedWriter bw = new BufferedWriter(new FileWriter(
							fileName));
					for (int a = 0; a < list.size(); a++) {
						bw.write(list.get(a)
								+ System.getProperty("line.separator"));
					}
					bw.close();

					list.clear();
					br = new BufferedReader(new FileReader(
							"BookingSummaries.txt"));
					currentLine = "";
					while ((currentLine = br.readLine()) != null) {
						String[] recs = currentLine.split(",");
						if (!recs[6].equals(service)
								|| !recs[7].equals(activity)) {
							list.add(currentLine);
						}
					}
					br.close();
					bw = new BufferedWriter(new FileWriter(
							"BookingSummaries.txt"));
					for (int a = 0; a < list.size(); a++) {
						bw.write(list.get(a)
								+ System.getProperty("line.separator"));
					}
					bw.close();
					JOptionPane.showMessageDialog(null,
							"Activity Deleted Successfully");
					if (comboBox_6.getSelectedIndex() != 0) {
						ArrayList<String> serviceNames = Utils
								.getActivities(comboBox_6.getSelectedItem()
										.toString());
						comboBox_7.removeAllItems();
						comboBox_7.addItem("Select Activity");
						for (int a = 0; a < serviceNames.size(); a++) {
							comboBox_7.addItem(serviceNames.get(a));
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnDeleteActivity.setBounds(182, 316, 183, 23);
		deleteservice.add(btnDeleteActivity);

		comboBox_7 = new JComboBox();
		comboBox_7.setBounds(182, 270, 183, 20);
		deleteservice.add(comboBox_7);

		JLabel lblSelectService_2 = new JLabel("Select Service");
		lblSelectService_2.setBounds(21, 223, 151, 14);
		deleteservice.add(lblSelectService_2);

		JLabel lblSelectActivity = new JLabel("Select Activity");
		lblSelectActivity.setBounds(21, 273, 151, 14);
		deleteservice.add(lblSelectActivity);

		empAvailable = new JPanel();
		empAvailable.setVisible(false);
		empAvailable.setBounds(0, 500, 557, 362);
		panel_2.add(empAvailable);
		empAvailable.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 71, 517, 280);
		empAvailable.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setRowHeight(25);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Employee Name", "Day", "Activity",
						"Time Available", "Status" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

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
		comboBox_1.setBounds(106, 35, 152, 25);
		empAvailable.add(comboBox_1);

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

		comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_2.getSelectedIndex() != 0) {
					try {
						empAvailableParticularDay(comboBox_1.getSelectedItem()
								.toString(), comboBox_2.getSelectedItem()
								.toString());
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}
			}
		});
		comboBox_2.addItem("Select Day");
		for (int a = 0; a < daysList.size(); a++) {
			comboBox_2.addItem(daysList.get(a));
		}
		comboBox_2.setBounds(413, 35, 114, 25);
		empAvailable.add(comboBox_2);

		updateEmpWaorkingTime = new JPanel();
		updateEmpWaorkingTime.setVisible(false);
		updateEmpWaorkingTime.setBounds(0, 0, 557, 421);
		panel_2.add(updateEmpWaorkingTime);
		updateEmpWaorkingTime.setLayout(null);

		comboBox_4 = new JComboBox();
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_4.getSelectedIndex() != 0) {
					empWorkingTimeNotNull(comboBox_4.getSelectedItem()
							.toString());
					employees(comboBox_4.getSelectedItem().toString());
				}
			}
		});
		comboBox_4.setBounds(158, 42, 202, 20);
		updateEmpWaorkingTime.add(comboBox_4);

		JLabel label_1 = new JLabel("Select Service");
		label_1.setBounds(51, 43, 102, 14);
		updateEmpWaorkingTime.add(label_1);

		JLabel label_2 = new JLabel("Employee Name");
		label_2.setBounds(24, 331, 136, 14);
		updateEmpWaorkingTime.add(label_2);

		comboBox_5 = new JComboBox();
		comboBox_5.setEditable(true);
		comboBox_5.setBounds(138, 327, 182, 20);
		updateEmpWaorkingTime.add(comboBox_5);

		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_4.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please Select Service To Proceed");
					return;
				}
				if (comboBox_5.getSelectedItem().toString().trim().equals("")
						|| comboBox_3.getSelectedItem().toString().trim()
								.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Enter Employee name to proceed");
					return;
				}
				if (comboBox_10.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please Select Start Time To Proceed");
					return;
				}
				if (comboBox_11.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please Select End Time To Proceed");
					return;
				}
				if (comboBox_12.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please Select Duration To Proceed");
					return;
				}
				
				String newtimeAvailable = comboBox_10.getSelectedItem().toString()+"-"+comboBox_11.getSelectedItem().toString();
				int duration = 0;
				if(comboBox_12.getSelectedIndex() == 1){
					duration = 30;
				}if(comboBox_12.getSelectedIndex() == 2){
					duration = 60;
				}

				String service = comboBox_4.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = -1;
				row = table_3.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null,
							"Please Select row to update to proceed");
					return;
				}
				String emp,   activity, startTime;
				emp = table_3.getModel().getValueAt(row, 0).toString();
				activity = table_3.getModel().getValueAt(row, 1).toString();
				startTime = table_3.getModel().getValueAt(row, 2).toString();

				String newstartTime = comboBox_10.getSelectedItem().toString();
				String newendTime = comboBox_11.getSelectedItem().toString();
				
				try {
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					Date date1 = format.parse(newstartTime);
					Date date2 = format.parse(newendTime);
					long diff = date2.getTime() - date1.getTime();
					long diffMinutes = diff / (60 * 1000);
					
					int numOfSlots;
					if(diffMinutes % duration != 0){
						JOptionPane.showMessageDialog(null, "The time difference should not have remainders for this to work");
						return;
					}
					
					ArrayList<String> list = new ArrayList<>();
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					String currentLine = "";
					while ((currentLine = br.readLine()) != null) {
						String recs[] = currentLine.split(",");
						if (recs[0].equals(emp) && recs[2].equals(activity)) {
							//Do Not add this line
						}else{
							list.add(currentLine);
						}
					}
					br.close();
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
					for (int a = 0; a < list.size(); a++) {
						bw.write(list.get(a)+ System.getProperty("line.separator"));
					}
					bw.close();
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
					for (int a = 0; a < daysList.size(); a++) {
						numOfSlots = (int) (diffMinutes / duration);
						date1 = format.parse(startTime);
						for(int d = 0; d < numOfSlots; d++ ){
							Calendar cal = Calendar.getInstance();
					        cal.setTime(date1);
					        Time timeStart = new Time (cal.getTime().getTime());
					        
					        cal.add(Calendar.MINUTE, duration);
					        Time timeEnd = new Time (cal.getTime().getTime());
					        writer.write(emp + "," + daysList.get(a) + ","
									+ activity + "," + timeStart.toString().substring(0, 5) + "-" + timeEnd.toString().substring(0, 5)
									+ ",available");
							writer.newLine();
					       date1 = new Date(timeEnd.getTime());
						}
						
					}
					writer.close();
					
					list = new ArrayList<>();
					br = new BufferedReader(new FileReader("employeeworkingtime.txt"));
					while ((currentLine = br.readLine()) != null) {
						String recs[] = currentLine.split(",");
						if (recs[0].equals(emp) && recs[1].equals(service) && recs[2].equals(activity)) {
							list.add(emp+","+service+","+activity+","+newstartTime+","+newendTime+","+duration);
						}else{
							list.add(currentLine);
						}
					}
					br.close();
					bw = new BufferedWriter(new FileWriter("employeeworkingtime.txt"));
					for (int a = 0; a < list.size(); a++) {
						bw.write(list.get(a)+ System.getProperty("line.separator"));
					}
					bw.close();

				
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Employee working time updated successfully");
				empWorkingTimeNotNull(comboBox_4.getSelectedItem().toString());
				employees(comboBox_4.getSelectedItem().toString());
			}
		});
		button_2.setBounds(354, 335, 102, 23);
		updateEmpWaorkingTime.add(button_2);

		JButton button_3 = new JButton("Delete");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_4.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,	"Please Select Service To Proceed");
					return;
				}
				
				String service = comboBox_4.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = -1;
				row = table_3.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Please Select row to update to proceed");
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(null, "You are deleting employee working time. Proceed", "Confirm", 2);
				if(confirm == 0){
					String emp,   activity;
					emp = table_3.getModel().getValueAt(row, 0).toString();
					activity = table_3.getModel().getValueAt(row, 1).toString();
	
					try {
						ArrayList<String> list = new ArrayList<>();
						BufferedReader br = new BufferedReader(new FileReader(fileName));
						String currentLine = "";
						while ((currentLine = br.readLine()) != null) {
							String recs[] = currentLine.split(",");
							if (recs[0].equals(emp) && recs[2].equals(activity)) {
								//Do Not add this line
							}else{
								list.add(currentLine);
							}
						}
						br.close();
						
						BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
						for (int a = 0; a < list.size(); a++) {
							bw.write(list.get(a)+ System.getProperty("line.separator"));
						}
						bw.close();
						
						list = new ArrayList<>();
						br = new BufferedReader(new FileReader("employeeworkingtime.txt"));
						while ((currentLine = br.readLine()) != null) {
							String recs[] = currentLine.split(",");
							if (recs[0].equals(emp) && recs[1].equals(service) && recs[2].equals(activity)) {
							
							}else{
								list.add(currentLine);
							}
						}
						br.close();
						bw = new BufferedWriter(new FileWriter("employeeworkingtime.txt"));
						for (int a = 0; a < list.size(); a++) {
							bw.write(list.get(a)+ System.getProperty("line.separator"));
						}
						bw.close();
	
					
					} catch (IOException  e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Employee working time deleted successfully");
					empWorkingTimeNotNull(comboBox_4.getSelectedItem().toString());
					employees(comboBox_4.getSelectedItem().toString());
				}
			}
		});
		button_3.setBounds(354, 373, 102, 23);
		updateEmpWaorkingTime.add(button_3);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 73, 527, 232);
		updateEmpWaorkingTime.add(scrollPane_6);

		table_3 = new JTable();
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_3.getSelectedRow();
				String employee = table_3.getModel().getValueAt(row, 0)
						.toString();
				String startTime = table_3.getModel().getValueAt(row, 2)
						.toString();
				String endTime = table_3.getModel().getValueAt(row, 3)
						.toString();
				String duration = table_3.getModel().getValueAt(row, 4)
						.toString()+" min";
				comboBox_5.setSelectedItem(employee);
				comboBox_10.setSelectedItem(startTime);
				comboBox_11.setSelectedItem(endTime);
				comboBox_12.setSelectedItem(duration);
			}
		});
		table_3.setRowHeight(25);
		table_3.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
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
		scrollPane_6.setViewportView(table_3);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.LIGHT_GRAY);
		panel_9.setBounds(0, 0, 557, 24);
		updateEmpWaorkingTime.add(panel_9);

		JLabel lblUpdateEmployeeWorking = new JLabel(
				"Update Employee Working Time");
		panel_9.add(lblUpdateEmployeeWorking);
		
		comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] {
				"Start Time", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
				"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
				"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
				"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
				"23:30" }));
		comboBox_10.setBounds(24, 358, 129, 20);
		updateEmpWaorkingTime.add(comboBox_10);
		
		comboBox_11 = new JComboBox();
		comboBox_11.setModel(new DefaultComboBoxModel(new String[] {
				"Start Time", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
				"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
				"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
				"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
				"23:30" }));
		comboBox_11.setBounds(205, 358, 115, 20);
		updateEmpWaorkingTime.add(comboBox_11);
		
		comboBox_12 = new JComboBox();
		comboBox_12.setModel(new DefaultComboBoxModel(new String[] {"Select duration", "30 min", "60 min"}));
		comboBox_12.setBounds(205, 387, 115, 20);
		updateEmpWaorkingTime.add(comboBox_12);
		
		JLabel lblDuration_1 = new JLabel("Duration");
		lblDuration_1.setBounds(24, 390, 115, 14);
		updateEmpWaorkingTime.add(lblDuration_1);

		empWaorkingTime = new JPanel();
		empWaorkingTime.setVisible(false);
		empWaorkingTime.setBounds(0, 0, 557, 396);
		empWaorkingTime.setLayout(null);
		panel_2.add(empWaorkingTime);

		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please select servicde");
					return;
				}
				if (comboBox_13.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please select activity");
					return;
				}
				if (comboBox_16.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please select starting time");
					return;
				}
				if (comboBox_17.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please select ending time");
					return;
				}
				if (comboBox_9.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Please select duration");
					return;
				}
				
				if (comboBox_3.getSelectedItem().toString().trim().equals("")
						|| comboBox_3.getSelectedItem().toString().trim()
								.equals("null")) {
					JOptionPane.showMessageDialog(null,
							"Enter Employee name to proceed");
					return;
				}
				String service = comboBox.getSelectedItem().toString();
				String activity = comboBox_13.getSelectedItem().toString();
				String startTime = comboBox_16.getSelectedItem().toString();
				String endTime = comboBox_17.getSelectedItem().toString();
				String employee = comboBox_3.getSelectedItem().toString();
				
				int duration = 0;
				if(comboBox_9.getSelectedIndex() == 1){
					duration = 30;
				}if(comboBox_9.getSelectedIndex() == 2){
					duration = 60;
				}

				try {
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					Date date1 = format.parse(startTime);
					Date date2 = format.parse(endTime);
					long diff = date2.getTime() - date1.getTime();
					long diffMinutes = diff / (60 * 1000);
					
					int numOfSlots;
					if(diffMinutes % duration != 0){
						JOptionPane.showMessageDialog(null, "The difference should not have remainders for this to work");
						return;
					}else{
					}

					BufferedWriter writer = new BufferedWriter(new FileWriter("employeeworkingtime.txt", true));
					writer.write(employee + "," + service + ","+ activity + "," + startTime + "," + endTime+","+duration);
					writer.newLine();
					writer.close();
					
					String fileName = service + ".txt";
					
					writer = new BufferedWriter(new FileWriter(fileName, true));
					for (int a = 0; a < daysList.size(); a++) {
						numOfSlots = (int) (diffMinutes / duration);
						date1 = format.parse(startTime);
						for(int d = 0; d < numOfSlots; d++ ){
							Calendar cal = Calendar.getInstance();
					        cal.setTime(date1);
					        Time timeStart = new Time (cal.getTime().getTime());
					        
					        cal.add(Calendar.MINUTE, duration);
					        Time timeEnd = new Time (cal.getTime().getTime());
					        writer.write(employee + "," + daysList.get(a) + ","
									+ activity + "," + timeStart.toString().substring(0, 5) + "-" + timeEnd.toString().substring(0, 5)
									+ ",available");
							writer.newLine();
					       date1 = new Date(timeEnd.getTime());
						}
						
					}

					writer.close();
					JOptionPane.showMessageDialog(null,"Employee time added successfully");
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSave_1.setBounds(250, 348, 102, 23);
		empWaorkingTime.add(btnSave_1);

		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(22, 303, 125, 14);
		empWaorkingTime.add(lblEmployeeName);

		comboBox_3 = new JComboBox();
		comboBox_3.setEditable(true);
		comboBox_3.setBounds(161, 300, 191, 20);
		empWaorkingTime.add(comboBox_3);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.LIGHT_GRAY);
		panel_10.setBounds(0, 0, 557, 24);
		empWaorkingTime.add(panel_10);

		JLabel lblAssignEmployeeWorking = new JLabel(
				"Assign Employee Working Time");
		panel_10.add(lblAssignEmployeeWorking);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != 0) {
					try {
						activity(comboBox.getSelectedItem().toString());
						employees(comboBox.getSelectedItem().toString());
					} catch (Exception e1) {
						// e.printStackTrace();
					}
				}
			}
		});
		comboBox.setBounds(161, 59, 191, 24);
		empWaorkingTime.add(comboBox);

		JLabel label = new JLabel("Select Service");
		label.setBounds(22, 64, 116, 14);
		empWaorkingTime.add(label);

		JLabel label_8 = new JLabel("Select Activity");
		label_8.setBounds(22, 106, 116, 14);
		empWaorkingTime.add(label_8);

		comboBox_13 = new JComboBox();
		comboBox_13.setModel(new DefaultComboBoxModel(
				new String[] { "Select activity" }));
		comboBox_13.setBounds(161, 101, 191, 24);
		empWaorkingTime.add(comboBox_13);

		JLabel lblStartinftime = new JLabel("Starting Time");
		lblStartinftime.setBounds(22, 158, 116, 14);
		empWaorkingTime.add(lblStartinftime);

		comboBox_16 = new JComboBox();
		comboBox_16.setModel(new DefaultComboBoxModel(new String[] {
				"Start Time", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
				"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
				"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
				"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
				"23:30" }));
		comboBox_16.setBounds(161, 155, 191, 24);
		empWaorkingTime.add(comboBox_16);

		comboBox_17 = new JComboBox();
		comboBox_17.setModel(new DefaultComboBoxModel(new String[] {
				"Start Time", "00:00", "00:30", "01:00", "01:30", "02:00",
				"02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
				"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
				"16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
				"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
				"23:30" }));
		comboBox_17.setBounds(161, 209, 191, 24);
		empWaorkingTime.add(comboBox_17);

		JLabel lblEndingTime = new JLabel("Ending time");
		lblEndingTime.setBounds(22, 214, 132, 14);
		empWaorkingTime.add(lblEndingTime);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(22, 258, 132, 14);
		empWaorkingTime.add(lblDuration);
		
		comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"Select duration", "30 min", "60 min"}));
		comboBox_9.setBounds(161, 253, 191, 24);
		empWaorkingTime.add(comboBox_9);

		bookForCustomer = new JPanel();
		bookForCustomer.setVisible(false);
		bookForCustomer.setBounds(0, 0, 557, 407);
		panel_2.add(bookForCustomer);
		bookForCustomer.setLayout(null);

		JLabel label_3 = new JLabel("Select Service");
		label_3.setBounds(20, 47, 127, 14);
		bookForCustomer.add(label_3);

		selectService = new JComboBox();
		selectService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectService.getSelectedIndex() != 0) {
					try {
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

		selectActivity = new JComboBox();
		selectActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectActivity.getSelectedIndex() != 0) {
					try {
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

		selectDay = new JComboBox();
		selectDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectDay.getSelectedIndex() != 0) {
					try {
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

		selectEmp = new JComboBox();
		selectEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		selectEmp.setBounds(113, 116, 143, 20);
		bookForCustomer.add(selectEmp);

		JLabel label_7 = new JLabel("Select Time Above and Save");
		label_7.setBounds(26, 382, 197, 14);
		bookForCustomer.add(label_7);

		JButton button_1 = new JButton("Save");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectCustomer.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Enter Customer To Book For");
					return;
				}
				String customerInfo = selectCustomer.getText();
				String[] customerData = customerInfo.split(",");
				if (customerData.length != 2) {
					JOptionPane.showMessageDialog(null,
							"Enter Customer's 2 names separated by comma");
					return;
				}
				String service = selectService.getSelectedItem().toString();
				String fileName = service + ".txt";
				int row = 0;
				int rows = table_4.getRowCount();
				String selected = "";
				for (row = 0; row < rows; row++) {
					try {
						boolean status = (boolean) table_4.getModel()
								.getValueAt(row, 5);
						if (status == true) {
							selected = table_4.getModel().getValueAt(row, 0)
									.toString()
									+ ","
									+ table_4.getModel().getValueAt(row, 1)
											.toString()
									+ ","
									+ table_4.getModel().getValueAt(row, 2)
											.toString()
									+ ","
									+ table_4.getModel().getValueAt(row, 3)
											.toString()
									+ ","
									+ table_4.getModel().getValueAt(row, 4)
											.toString();
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

							String modified = recs[0] + "," + recs[1] + ","
									+ recs[2] + "," + recs[3] + "," + recs[4];

							BufferedWriter bw = new BufferedWriter(
									new FileWriter(fileName));
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
							BufferedWriter writer2 = new BufferedWriter(
									new FileWriter("BookingSummaries.txt", true));
							writer2.write("Customer," + customerData[0] + ","
									+ customerData[1]
									+ ",booked Appointment on," + recs[0] + ","
									+ recs[1] + "," + /* servicename */service
									+ "," + recs[2] + "," + recs[3]);
							writer2.newLine();
							writer2.close();
						}
					} catch (Exception e1) {
						// e.printStackTrace();
					}

				}

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
				JOptionPane.showMessageDialog(null, "Successfully Booked");

			}
		});
		button_1.setBounds(363, 373, 89, 23);
		bookForCustomer.add(button_1);

		JLabel lblSelectCustomer = new JLabel("Enter Customer");
		lblSelectCustomer.setBounds(260, 120, 100, 14);
		bookForCustomer.add(lblSelectCustomer);

		selectCustomer = new JTextField();
		selectCustomer.setBounds(363, 117, 184, 20);
		bookForCustomer.add(selectCustomer);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(10, 148, 537, 223);
		bookForCustomer.add(scrollPane_7);

		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
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

		table_4.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(80);
		table_4.getColumnModel().getColumn(4).setPreferredWidth(70);
		table_4.getColumnModel().getColumn(5).setPreferredWidth(50);
		table_4.setRowHeight(25);

		scrollPane_7.setViewportView(table_4);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.LIGHT_GRAY);
		panel_11.setBounds(0, 0, 557, 24);
		bookForCustomer.add(panel_11);

		JLabel lblMakeBookingFor = new JLabel("Make Booking For Customer");
		panel_11.add(lblMakeBookingFor);

		addActivity = new JPanel();
		addActivity.setVisible(true);
		addActivity.setBounds(0, 0, 557, 407);
		panel_2.add(addActivity);
		addActivity.setLayout(null);

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.LIGHT_GRAY);
		panel_13.setBounds(0, 0, 557, 24);
		addActivity.add(panel_13);

		JLabel lblAddServiceActivitieshere = new JLabel(
				"Add Service Activities Here");
		panel_13.add(lblAddServiceActivitieshere);

		comboBox_8 = new JComboBox();
		comboBox_8.setBounds(173, 75, 185, 24);
		addActivity.add(comboBox_8);

		JLabel lblSelectService_3 = new JLabel("Select Service");
		lblSelectService_3.setBounds(27, 80, 136, 14);
		addActivity.add(lblSelectService_3);

		JLabel lblNumberOfActivities_1 = new JLabel("Number of Activities");
		lblNumberOfActivities_1.setBounds(27, 120, 136, 14);
		addActivity.add(lblNumberOfActivities_1);

		JLabel lblActivityName = new JLabel("Activity Name");
		lblActivityName.setBounds(27, 163, 136, 14);
		addActivity.add(lblActivityName);

		textField_6 = new JTextField();
		textField_6.setBounds(173, 127, 185, 20);
		addActivity.add(textField_6);
		textField_6.setColumns(10);

		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(173, 158, 185, 144);
		addActivity.add(scrollPane_8);

		JTextArea textArea = new JTextArea();
		scrollPane_8.setViewportView(textArea);

		JButton btnSaveActivities = new JButton("Save Activities");
		btnSaveActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_8.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null,
							"Select Service Name to proceed");
					return;
				}

				String service = comboBox_8.getSelectedItem().toString();
				String fileName = service + ".txt";
				String num = textField_6.getText().trim();
				String activities = textArea.getText().trim();

				if (num.equals("") || activities.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Enter Number of activities To Register");
					return;
				}

				try {
					int number = Integer.parseInt(num);
					String[] activitiesArr = activities.split(",");
					if (activitiesArr.length != number) {
						JOptionPane
								.showMessageDialog(null,
										"Enter correct number of activities, separate by [,]");
						return;
					}
					FileWriter fw = new FileWriter(fileName, true);
					BufferedWriter bw = new BufferedWriter(fw);

					for (int a = 0; a < number; a++) {
						bw.write("null,null," + activitiesArr[a].toLowerCase()
								+ ",null,available");
						bw.newLine();
					}
					bw.close();

					JOptionPane.showMessageDialog(null,
							"Service Entered successfully");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSaveActivities.setForeground(Color.BLACK);
		btnSaveActivities.setBackground(SystemColor.activeCaption);
		btnSaveActivities.setBounds(178, 343, 180, 23);
		addActivity.add(btnSaveActivities);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JLabel lblNewLabel_1 = new JLabel();
		img = new ImageIcon(this.getClass().getResource("/icon.png"))
				.getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		Image bi;
		try {
			bi = null;
			bi = ImageIO.read(this.getClass().getResource("/icon.png"));
			lblNewLabel_1.setIcon(new ImageIcon(bi
					.getScaledInstance(50, 36, 36)));
		} catch (Exception e) {

		}
		panel.add(lblNewLabel_1);

		businessTitle = new JLabel(fillBusinessData());
		businessTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(businessTitle);
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
			e.printStackTrace();
		}

	}

	private String fillBusinessData() {
		String businessDetails = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"BusinessInfo.txt"));
			String line = "";
			while ((line = br.readLine()) != null) {
				String recs[] = line.split(",");
				if (recs[3].equals(userData[4])) {
					businessDetails = "<html><font color='green'>Name:</font> "
							+ "<font color='red'>" + recs[0] + "</font> "
							+ "<font color='green'>Location:</font> "
							+ "<font color='red'>" + recs[1] + "</font> "
							+ "<font color='green'>Telephone:</font> "
							+ "<font color='red'>" + recs[2]
							+ "</font> </html>";
				}
			}
			br.close();
			return businessDetails;
		} catch (Exception e2) {
			e2.printStackTrace();
			return businessDetails;
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
			servicedeleteCombo.removeAllItems();
			comboBox_1.removeAllItems();
			comboBox_4.removeAllItems();
			selectService.removeAllItems();
			comboBox_6.removeAllItems();
			comboBox_8.removeAllItems();
			comboBox.removeAllItems();
			servicedeleteCombo.addItem("Select Service");
			comboBox_4.addItem("Select Services");
			selectService.addItem("Select Services");
			comboBox_6.addItem("Select Services");
			comboBox_8.addItem("Select Services");
			comboBox.addItem("Select Services");
			for (int a = 0; a < services.size(); a++) {
				servicedeleteCombo.addItem(services.get(a));
				comboBox_1.addItem(services.get(a));
				comboBox_4.addItem(services.get(a));
				selectService.addItem(services.get(a));
				comboBox_6.addItem(services.get(a));
				comboBox_8.addItem(services.get(a));
				comboBox.addItem(services.get(a));
			}

		} catch (IOException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
				if (!recs[0].equals("null") && recs[4].equals("available")) {
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
			e.printStackTrace();
		}

	}

	public void empWorkingTimeNotNull(String service) {
		try {
			String fileName = "employeeworkingtime.txt";
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName));
			String line = "";
			ArrayList<String> list = new ArrayList<>();
			String recs[] = null;
			while ((line = br.readLine()) != null) {
				recs = line.split(",");
				if (recs[1].equals(service)) {
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
				rowData[1] = recs[2];
				rowData[2] = recs[3];
				rowData[3] = recs[4];
				rowData[4] = recs[5];
				model.addRow(rowData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void panelToSee(JPanel panel) {
		addActivity.setVisible(false);
		bookingsummaries.setVisible(false);
		newservice.setVisible(false);
		deleteservice.setVisible(false);
		empAvailable.setVisible(false);
		empWaorkingTime.setVisible(false);
		updateEmpWaorkingTime.setVisible(false);
		bookForCustomer.setVisible(false);
		addActivity.setVisible(false);
		panel.setVisible(true);
		panel.setBounds(0, 0, 557, 407);
	}

	public void activity(String service) {
		ArrayList<String> serviceNames = Utils.getActivities(service);
		selectActivity.removeAllItems();
		comboBox_13.removeAllItems();
		comboBox_13.addItem("Select Activity");
		selectActivity.addItem("Select Activity");
		for (int a = 0; a < serviceNames.size(); a++) {
			selectActivity.addItem(serviceNames.get(a));
			comboBox_13.addItem(serviceNames.get(a));
		}

	}

	public void dayOfApp(String service, String activity) {
		ArrayList<String> activityDays = Utils.getActivityAppointmentDays(
				service, activity);
		selectDay.removeAllItems();
		selectDay.addItem("Select Day");
		for (int a = 0; a < activityDays.size(); a++) {
			selectDay.addItem(activityDays.get(a));
		}

	}

	public void employee(String service, String activity, String day) {
		service += ".txt";
		ArrayList<String> employeeNames = Utils.getEmployeeNames(service,
				activity, day);
		selectEmp.removeAllItems();
		;
		selectEmp.addItem("Select Employee");
		for (int a = 0; a < employeeNames.size(); a++) {
			if (!employeeNames.get(a).equals("null")) {
				selectEmp.addItem(employeeNames.get(a));
			}
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
				if (!recs[0].equals("null")
						&& recs[4].equals("available")
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
			e.printStackTrace();
		}

	}

	private void closeLock() {
		try {
			lock.release();
		} catch (Exception e) {
		}
		try {
			lock.release();
			chanel.close();
		} catch (Exception e) {
		}

	}

	private void deleteFile(File file) {
		try {
			file.delete();
		} catch (Exception e) {
		}
	}
}
