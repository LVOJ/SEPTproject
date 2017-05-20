package utility;

import java.util.ArrayList;
//Booking interface that will be implemented by all classes that will be used to make a booking 
public interface BookAppointment {

	//Method that will be used to make a booking
	public void doBooking(String[] userData, String service, ArrayList<String> toBookList);
}
