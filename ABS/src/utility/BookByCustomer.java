package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * 
	Class that will make a booking by a customer
 *
 */
public class BookByCustomer implements BookAppointment {

	public BookByCustomer() {

	}

	//The method that will complete the booking process
	//It should receive a list of slots to book and service selected by customer
	public void doBooking(String[] userData, String service,
			ArrayList<String> toBookList) {
		
		ArrayList<String> list = new ArrayList<>();//ArrayList to contain lines read in the service file
		String fileName = service + ".txt"; //Initialize the file name

		String selected = "";
		for (int i = 0; i < toBookList.size(); i++) { //Loop through every item to be booked 
			try {
				selected = toBookList.get(i); //set selected item is the current item in the list to be booked
				String recs[];
				while (true) {
					recs = selected.split(",");//Separate the selected item with comma and add them in string array

					if (recs[4].equals("Un-available")) { //Check if the slot is un-available
						System.out.println("Booking Un-Available"); //Tell the user the slot is not available
						System.out.println("Please select another");//Tell the user to select another item
					} else {
						recs[4] = "Un-available"; //Set the slot un-available
						break;
					}

				}

				String modified = recs[0] + "," + recs[1] + "," + recs[2] + ","
						+ recs[3] + "," + recs[4]; //Set the line to modify un-available ....recs[4] 

				BufferedReader br = new BufferedReader(new FileReader(fileName)); //Open the file in read mode and give access to BufferedReader
				String line = "";
				while ((line = br.readLine()) != null) { //Loop through every line in the file that is not null
					list.add(line);//Add the line read in the list
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));//Open the file in write mode and give access to the BufferedWriter
				for (int a = 0; a < list.size(); a++) {//Loop through every read item item in the list
					if (list.get(a).equals(selected)) { //Check if the current list item equals to the line to be modified , made un-available
						list.set(a, modified); //Change the value at this index equal to modified
						bw.write(list.get(a)); //write the list item in the file
						bw.newLine();// write a new line in the file
					} else {
						bw.write(list.get(a)); //No changes made but write the list item in the file
						bw.newLine();//write the new line
					}
				}
				bw.close();//Close the BufferedWriter
				
				BufferedWriter writer2 = new BufferedWriter(new FileWriter(
						"BookingSummaries.txt", true)); //Open the "BookingSummaries.txt" file in write mode and give access to then  BufferedWriter
				writer2.write("Customer," + userData[0] + "," + userData[1]
						+ ",booked Appointment on," + recs[0] + "," + recs[1]
						+ "," + /* servicename */service + "," + recs[2] + ","
						+ recs[3]); //Write a booked slot in the BookingSummaries with details of the service and customer details
				writer2.newLine(); //write new line
				writer2.close();// close the BufferedWriter
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		JOptionPane.showMessageDialog(null, "Successfully Booked By Customer"); //Display a success message to the customer
	}
}
