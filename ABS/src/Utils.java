import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	private static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
	public static String validateInput(String message, String regex){
	    	boolean validInput = false;
	    	String input = null;
	    	do {
	    		System.out.println(message);
	    		in.useDelimiter("\r\n");
			input = in.next();
	    	if (input.matches(regex)){
	    		validInput = true;
	    	}
	    }while (!validInput);
		
		return input;
	}
	
	public static String validateIntegerInput(String message, int min, int max){
    	boolean validInput = false;
    	String input = null;
    	String regex = "[" + min + "-" + max + "]";
    	do {
    		System.out.println(message);
    		in.useDelimiter("\r\n");
    		input = in.next();
    	if (input.matches(regex)){
    		validInput = true;
    	}
    }while (!validInput);
	
	return input;
	}
	public static ArrayList<String> getEmployeeNames(String fileName){
		String line="";
		int i = 0;
		ArrayList<String> employeeNames = new ArrayList<String>();
		try {
			FileReader fr;
			fr = new FileReader(fileName);
	        BufferedReader br = new BufferedReader(fr);
			while( (line = br.readLine())!= null ){
				String arr[] = line.split(",");
				String employee = arr[0].toLowerCase();
				if (!employeeNames.contains(employee)) {
					employeeNames.add(i, employee);
					i++;
				}				

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeNames;  
    }
	public static String validateEmployees(ArrayList<String> employeeNames){
		boolean validEmployee = false;
    	String selectedEmployee = null;
    	do {
            System.out.println("\nSelect an Employee (Enter * if you want to see all):");
            in.useDelimiter("\r\n");
            String selectEmployee = in.next();
            if (employeeNames.contains(selectEmployee.toLowerCase())){
            	validEmployee = true;
    		}else if (selectEmployee.equals("*")){
				validEmployee = true;
      		}

            if (validEmployee){
            	selectedEmployee = selectEmployee.toLowerCase();
            }
    	}while(!validEmployee);
		return selectedEmployee;
		
	}
	public static String validateDay(String message){
		boolean validDay = false;
		String selectedDay = null;
    	do {
            System.out.println(message);
            in.useDelimiter("\r\n");
            String selectDay = in.next();
            switch(selectDay.toLowerCase()){
	            case "monday":
	            case "tuesday":
	            case "wednesday":
	            case "thursday":
	            case "friday":
	            	validDay = true;
	            	break;
            	default: 
            		break;
            }
            if (validDay){
            	selectedDay = selectDay.toLowerCase();
            } 
    	}while(!validDay);
    	
    	return selectedDay;
	}
	public static String validateActivity(){
		boolean validActivity = false;
		String selectedActivity = null;
    	do {
            System.out.println("\nPlease enter the activity that you want. i.e Hair cut, Hair wash, Hair colour:");
            in.useDelimiter("\r\n");
            String selectActivity = in.next();
            switch(selectActivity.toLowerCase()){
	            case "hair cut":
	            case "hair wash":
	            case "hair colour":
	            	validActivity = true;
	            	break;
            	default: 
            		break;
            }
            if (validActivity){
            	selectedActivity = selectActivity.toLowerCase();
            } 
    	}while(!validActivity);
    	
    	return selectedActivity;
	}
}
