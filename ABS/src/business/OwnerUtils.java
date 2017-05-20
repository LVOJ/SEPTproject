package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

/**
 * 
 Class with some of Business owner functions
 *
 */
public class OwnerUtils {

	public OwnerUtils() {

	}

	//Function to register a new service
	public boolean newService(String serviceName) {

		boolean serviceRegistred = false; // Boolean should be true if the service is successfully registered
		ArrayList<String> services = new ArrayList<String>(); //List to contain all services registered in the system
		try {
			FileWriter fw = new FileWriter("services.txt", true);// Open file in write mode
			BufferedWriter bw = new BufferedWriter(fw);// Give access to BufferedWriter

			BufferedReader br = new BufferedReader(new FileReader(
					"services.txt")); //Open the file in read mode and give access to BufferedReader
			String line = "";
			while ((line = br.readLine()) != null) { //Loop through every line in the service.txt file.
				services.add(line); // Add the line read into the list
			}
			br.close(); //Close the BufferedReader
			
			if (!services.contains(serviceName)) { //Check whether the new service entered by the user exists
				bw.write(serviceName); // write the service in the file if it does not exits
				bw.newLine(); //write a new line in preparation for the next service
				serviceRegistred = true; //Set the boolean true after writing the file
			} else {
				serviceRegistred = false;// Set the boolean false if the service already exists
			}

			bw.close();//Close the BufferedWriter

		} catch (Exception e2) {
			serviceRegistred = false;// Set the boolean false
			e2.printStackTrace();
		}
		return serviceRegistred;

	}

	//Function to add employee working times
	//The function should have Arraylist with HashMap and second list containing days
	public boolean empWorkingTime(
			ArrayList<HashMap<String, String>> activityToAdd,
			ArrayList<String> daysList) {
		
		Boolean timeRegistered = false; // Boolean should be true if the employee working time is successfully added
		
		for (int list = 0; list < activityToAdd.size(); list++) { //Loop through every item in the list activityToAdd
			
			HashMap<String, String> recordMap = activityToAdd.get(list);// Set the Hashmap the the current list item in the activityToAdd
			/*Get all the values from the hashmap using the correct keys*/
			String service = recordMap.get("service");
			String activity = recordMap.get("activity");
			String startTime = recordMap.get("startTime");
			String endTime = recordMap.get("endTime");
			String employee = recordMap.get("employee");
			int duration = Integer.parseInt(recordMap.get("duration"));
			
			try {
				SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // Initialize a date format to get time in hours and minutes
				Date date1 = format.parse(startTime); //Parse the starttime and see if it is in the correct format
				Date date2 = format.parse(endTime);//Parse the endtime and see if it is in the correct format
				long diff = date2.getTime() - date1.getTime(); //Get the difference of the startTime and endTime in milliseconds
				long diffMinutes = diff / (60 * 1000); // Convert the differnce into minutes

				int numOfSlots;
				if (diffMinutes % duration != 0) { // Check if the working time supplied and the activities duration will produce whole number of timeslots
					JOptionPane
							.showMessageDialog(
									null,
									"The difference should not have remainders for this to work.\nThis will cause error");
					//Error message if timeslots will have remainders. THhey should be 1, 2, 3, 4 etc and not 1.2345, 245.23 ....
					timeRegistered = false; //Boolean is set to false since the working time has not been added
					return timeRegistered; //Stop program execution.
				}

				BufferedWriter writer = new BufferedWriter(new FileWriter(
						"employeeworkingtime.txt", true)); //Open the "employeeworkingtime.txt" file in write mode and give access to the BufferedWriter 
				writer.write(employee + "," + service + "," + activity + ","
						+ startTime + "," + endTime + "," + duration); //Write the employee working time record in the "employeeworkingtime.txt" file 
				writer.newLine(); //write a new line for the next record
				writer.close(); //Close the BufferedWriter

				String fileName = service + ".txt"; //Initialize a service name file to write the activities working time

				writer = new BufferedWriter(new FileWriter(fileName, true)); // open the file in the write mode and give access to the BufferedWriter
				for (int a = 0; a < daysList.size(); a++) { //Loop through the dayList Monday - Friday
					numOfSlots = (int) (diffMinutes / duration); // Determine the number of slots to enter ccording to the duration and working time
					date1 = format.parse(startTime);
					for (int d = 0; d < numOfSlots; d++) { //Loop writing every timeslot so that customers will be able to book
						Calendar cal = Calendar.getInstance(); //Initialize calendar instance
						cal.setTime(date1);
						Time timeStart = new Time(cal.getTime().getTime()); //Starting time of a timeslot

						cal.add(Calendar.MINUTE, duration); //Add duration to get the ending of the timeslot
						Time timeEnd = new Time(cal.getTime().getTime()); //Get time instance
						writer.write(employee + "," + daysList.get(a) + ","
								+ activity + ","
								+ timeStart.toString().substring(0, 5) + "-"
								+ timeEnd.toString().substring(0, 5)
								+ ",available"); //Write the timeslot in the file 
						writer.newLine(); //wtrite the new line
						
						date1 = new Date(timeEnd.getTime()); //Set the end time of the current timeslot be the start time of the next timeslot
					}

				}

				writer.close();
				timeRegistered = true; // Set the boolean to true since the employee working time has been added successfully
			} catch (IOException | ParseException e) {
				timeRegistered = false;
				e.printStackTrace();
			}
		}
		return timeRegistered;
	}

	//The function to read the employees available
	//It will return an ArrayList
	public ArrayList<String> empAvailable(String service) {
		try {
			String fileName = service + ".txt"; //Set the file name from the service selected by the user
			BufferedReader br;
			br = new BufferedReader(new FileReader(fileName)); //Open the file in read mode and give access to the BufferedReader
			String line = "";
			
			
			ArrayList<String> list = new ArrayList<>(); //Declare the list to hold the records read
			String recs[] = null;
			while ((line = br.readLine()) != null) { //Loop through every line that is not null in the file
				recs = line.split(",");// Separate the line with a comma and add the records in a String array
				if (!recs[0].equals("null") && recs[4].equals("available")) { //Check if the 1st array item is null and the 5th array item is equal to available
					list.add(line); //Add the line in the list is the record shows the employee us available
				}
			}

			br.close(); //Close the BufferedReader
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	//Function to read the booking summaries and returns an Arraylist
	public ArrayList<String> ShowBookingSummaries() {
		FileReader fr;
		try {
			fr = new FileReader("BookingSummaries.txt"); //Open the file in read mode 
			BufferedReader br = new BufferedReader(fr);// Give access to the BufferedReader
			String line = "";
			ArrayList<String> bookings = new ArrayList<>();//Arraylist to contain the records
			while ((line = br.readLine()) != null) { //Loop through every line in the file that is not null
				bookings.add(line); //Add the line in the list
			}
			br.close();//Close the BufferedReader
			return bookings;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
