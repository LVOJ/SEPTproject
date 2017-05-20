package utility;

import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * 
 The class or factory that determines which object to instantiate
 *
 */
public class BookAppointmentFactory {

	public BookAppointmentFactory() {
		
	}
	//Method to make appointment booking
	public static void doBooking(String userType, String[] userData, String service,
			ArrayList<String> toBookList){
		if(userType.equals("Customer")){ //Check whether the booking is to be made by a customer
			new BookByCustomer().doBooking(userData, service, toBookList); //Call the BookByCustomer class
		}else if(userType.equals("Owner")){//Check whether the booking is to be made by the business owner
			new BookByOwner().doBooking(userData, service, toBookList);//Call the BookByOwner class
		}
		
	}

}
