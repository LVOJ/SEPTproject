package customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import javax.swing.JOptionPane;

/**
 * 
 Class with some of Customer functions
 *
 */
public class CustomerUtils {

	//Function to read the booked slots by the logged in user
	//It return an ArrayList
	public ArrayList<HashMap<String, String>> viewMyBooking(String[] userData) {
		ArrayList<HashMap<String, String>> bookedSlots = new ArrayList<>(); //Arraylist to contain all the booked slots of the user
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"BookingSummaries.txt"));//Open the file in read mode and give access to the BufferedReader
			line = "";
			int lineCount = 0; //Declare count that will increase to keep count of the number of the slots booked by the customer
			while ((line = br.readLine()) != null) {// Loop through all the lines in the file that are not null
				lineCount++;//Let the counter increase by 1 after every loop
				String arr[] = line.split(","); //Separate the line read by a comma and add the records in a String array
				if (arr[1].equals(userData[0]) && arr[2].equals(userData[1])) { //If the 2nd array item matches the firstname of the user and the 3rd array item matches the secondname
					HashMap<String, String> mapTimeTable = new HashMap<>();//Decalre a hashmap that will hold the read read recods
					/*put the values and correct keys in the hashmap*/
					mapTimeTable.put("EmpName", arr[4]);
					mapTimeTable.put("Day", arr[5]);
					mapTimeTable.put("Service", arr[6]);
					mapTimeTable.put("Activity", arr[7]);
					mapTimeTable.put("timeSlot", arr[8]);
					mapTimeTable.put("lineNumber", String.valueOf(lineCount));
					//Add the hashmap to the list to be returned.
					bookedSlots.add(mapTimeTable);

				}
			}
			br.close();
			return bookedSlots;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	//Function to cancel the users booked slot.
	//The function should receive the users data and the list of booked slots to cancel
	public boolean CancelMyBooking(String[] userData,
			ArrayList<HashMap<String, String>> listToCancel) {

		Boolean cancelled = false; //The boolean should be true if all the slots are successfully cancelled
		
		String service = "", activity = "", day = "", empName = "", time = "";

		for (int i = 0; i < listToCancel.size(); i++) { //Loop through every item in the list to cancel
			HashMap<String, String> map = listToCancel.get(i);//Get the current item from the list and add it to a map
			time = map.get("time");
			activity = map.get("activity");
			service = map.get("service");
			day = map.get("day");
			empName = map.get("empName");

			ArrayList<String> bookedSlotsList = new ArrayList<>(); //List to contain all the booked list by all customers
			try {

				BufferedReader br = new BufferedReader(new FileReader(
						"BookingSummaries.txt"));//Open the file in read mode and give access to the BufferedReader
				String line = "";
				while ((line = br.readLine()) != null) {//Loop through every line in the file that is not null
					bookedSlotsList.add(line);// Add the current line to the list containing all the booked slots
				}
				br.close();// Close the BufferedReader to release the file

				File bookingSummaries = new File("BookingSummaries.txt");//Get the file path
				File tempBookingSummaries = new File("tempBookingSummaries.txt");//Get the temporary file path
				BufferedReader reader = new BufferedReader(new FileReader(
						bookingSummaries));//Open the file in read mode and give access to the BufferedReader
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						tempBookingSummaries));//Open the temporary file in read mode and give access to the BufferedReader

				String lineToRemove = "Customer," + userData[0] + ","
						+ userData[1] + ",booked Appointment on," + empName
						+ "," + day + "," + service + "," + activity + ","
						+ time; //Initialize the file to remove
				String currentLine;

				while ((currentLine = reader.readLine()) != null) {//Loop through every line in the file thatis not null
					// trim newline when comparing with lineToRemove
					String trimmedLine = currentLine.trim();
					if (trimmedLine.equals(lineToRemove))//Check if the line read equals to the line to remove
						continue; // Go to the next loop if they match
					
					writer.write(currentLine//write the current line in the temporary file
							+ System.getProperty("line.separator"));//write new line
				}
				writer.close();//close the BufferedWriter
				reader.close();// close the BufferedReader

				bookingSummaries.delete();//delete the original file
				tempBookingSummaries.renameTo(bookingSummaries);//rename the temporary file to the original file
				tempBookingSummaries.delete();//delete the temporary file

				String fileName = service + ".txt"; //Initialize the file where to make the slot available
				ArrayList<String> list = new ArrayList<String>();// List to store the lines read
				br = new BufferedReader(new FileReader(fileName)); //Open the file in read mode and give access to the BufferedReader
				line = "";
				String[] recs = null;
				String lineToRename = empName + "," + day + "," + activity
						+ "," + time; //This is the slot selected by the user to be cancelled
				String modify = "";
				while ((line = br.readLine()) != null) {
					recs = line.split(",");//Separate the line with a comma and put the records in a String array
					modify = recs[0] + "," + recs[1] + "," + recs[2] + ","
							+ recs[3]; // Initialize the string read by BufferedReader 
					if (modify.equals(lineToRename)) { //Check if the read string matches the string selected by the user
						recs[4] = "available"; //If they match make the 5th array item "available"
						modify += "," + recs[4];//Append 5th array item in the modified string
						
						cancelled = true; // Set boolean to true since one slot has been made available
						
					} else {
						modify = line; //Set modify is equal to line read since not change is needed.
					}
					list.add(modify); //Add the line to modify in a list
				}

				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));//Open the file in write mode and give access to the BufferedWriter
				for (int a = 0; a < list.size(); a++) { //Loop through every item in the list 
					bw.write(list.get(a)); //write the current item in the list in the file
					bw.newLine();//write a new line to prepare for the next list item
				}
				bw.close();//Close the BufferedWriter
			} catch (IOException e) {
				e.printStackTrace();
				cancelled = false;
			}
		}
		return cancelled;

	}

}
