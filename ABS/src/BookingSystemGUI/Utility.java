package BookingSystemGUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Utility {
	public static boolean validateInput(String textEntered, String regex, String message){
    	boolean validInput = false;
    	if (textEntered.matches(regex)){
    		validInput = true;
    	}else {
    		JOptionPane.showMessageDialog(null, message, "Invalid Input!",
    			    JOptionPane.WARNING_MESSAGE);
    	}
		
		return validInput;
	}

	public static boolean validateDay(String selectDay, String message){
		boolean validDay = false;
        switch(selectDay.toLowerCase()){
            case "monday":
            case "tuesday":
            case "wednesday":
            case "thursday":
            case "friday":
            	validDay = true;
            	break;
        	default:
        		JOptionPane.showMessageDialog(null, message, "Invalid Input!",
        			    JOptionPane.WARNING_MESSAGE);
        		break;
        }
		return validDay;
	}
	public static boolean validateUsername(String username){
		String fileName = "customerinfo.txt";
		boolean uniqueUsername = false;
		String registeredUsername = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = "";
			String[] recs = null;
			while ((line = br.readLine()) != null) {
				recs = line.split(",");
				registeredUsername = recs[4];
				registeredUsername.toLowerCase();
				if (registeredUsername.equals(username.toLowerCase())){
					return uniqueUsername;
				}
			}
			uniqueUsername = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return uniqueUsername;

	}
}

