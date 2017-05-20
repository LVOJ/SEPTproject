package utility;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import customer.CustomerPanelGUI;

/**
 * 
 Class that contains most of utilities
 *
 */
public class Utils {
	private static Logger logger = Logger.getLogger(CustomerPanelGUI.class.getName());
	
	//Function to valid user input, the input should macth the regex
	public static boolean validateInput(String textEntered, String regex, String message){
    	boolean validInput = false;//Boolean must be true if the input matches the regex
    	if (textEntered.matches(regex)){//Check whether the input matches the regex
    		validInput = true; //Set boolean is true
    	}else {
    		JOptionPane.showMessageDialog(null, message, "Invalid Input!",
    			    JOptionPane.WARNING_MESSAGE);//Error message if input does not regex
    	}
		
		return validInput;
	}
	
	//Function to validate username to make sure it is unique
	public static boolean validateUsername(String username){
		String fileName = "customerinfo.txt";//Initialize the file to read
		boolean uniqueUsername = false; //Boolean to be true if the username does not exist
		String registeredUsername = ""; //A username that already exists
		BufferedReader br; //Declare BufferedReader
		try {
			br = new BufferedReader(new FileReader(fileName)); //Open the file in read mode and give access to BufferedReader
			String line = "";
			String[] recs = null;
			while ((line = br.readLine()) != null) { //Loop through every line in the file that is not null
				recs = line.split(",");//Separate the line with comma and add the records in a string array
				registeredUsername = recs[4]; //Registrered username is set to array item 5
				registeredUsername.toLowerCase();
				if (registeredUsername.equals(username.toLowerCase())){ //Check if the username read is equal to username entered by user
					return uniqueUsername; //end execution if the username exists and return
				}
			}
			uniqueUsername = true; //Set boolean is equal to true
			br.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		
		return uniqueUsername;

	}
	
	//Function to get available employees for certain activity
	public static ArrayList<String> getEmployeeNames(String fileName, String activityName, String activityDayName){
		String line="";
		ArrayList<String> employeeNames = new ArrayList<String>(); //List to contain employees
		try {
			FileReader fr;
			fr = new FileReader(fileName); //Open the file in read mode
	        BufferedReader br = new BufferedReader(fr); //Give access to BufferedReader
			while( (line = br.readLine())!= null ){ //Loop through every line in the file that is not null
				String arr[] = line.split(",");//Separate the line with a comma
				String employee = arr[0].toLowerCase(); //Set employee is equal to the 1st array item
				String day = arr[1].toLowerCase();//Set day is equal to the 2nd array item
				String activity = arr[2].toLowerCase();//Set activity is equal to the 3rd array item
				if(activityName.equals(activity) && activityDayName.equals(day)){ //Check is activity read equals to activity selected and day read is equal to day selected
					if (!employeeNames.contains(employee)) { //Check if employee list contains the employee read
						employeeNames.add(employee);//Add employee if they do not exist in the list
					}	
				}

			}
			br.close();//Close the BufferedReader
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeNames; 
		
    }

	//Function to get activities from a certain service
	public static ArrayList<String> getActivities(String serviceName){
		ArrayList<String> serviceActivities = new ArrayList<>();//List to contain all activities in the service
		try{
			
			BufferedReader br = new BufferedReader(new FileReader("serviceduration.txt"));//Open the file in read mode and give access to BufferedReader
	        String line = "", recs[];
	        while((line=br.readLine())!=null){//Loop through every line that is not null
	        	recs = line.split(",");//Separate the line with comma and add to String array
	        	if(recs[0].equals(serviceName)){ //Check whether the 1st array item is equal to servicename
	        		serviceActivities.add(recs[1]); //Add the second array item in the list
	            }
	        }
	        br.close();//Close the BufferedReader
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return serviceActivities;
	}

	//Function to available days for the activity to book, should supply service and activity
	public static ArrayList<String> getActivityAppointmentDays(String serviceName, String activityName){
		ArrayList<String> activityAppointmentDays = new ArrayList<>();//List to contain available days
		serviceName += ".txt"; //Initialize a file name
		try{
			BufferedReader br = new BufferedReader(new FileReader(serviceName)); //Open the file in read mode and give access to BufferedReader
	        String line = "";
	        while((line=br.readLine())!=null){//Loop through every line that is not null
	        	String[] activities = line.split(",");//Separate the line by comma and add the records in string array
	        	String appointmentDay = activities[1];//Set appointmentDay equal to 2nd item array
	        	String particularActivity = activities[2]; //Set particularActivity equal to 3rd item array
	        	
	        	if(particularActivity.equals(activityName)){//Check whether activity selected by user is equal to particularActivity
		        	if(!activityAppointmentDays.contains(appointmentDay)){
		        		activityAppointmentDays.add(appointmentDay); //Add appointmentDay in the list
		        	}
	        	}
	        	
	        }
	       br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return activityAppointmentDays;
	}


	/*
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
	*/
	
	/*
	private static Scanner in=new Scanner(System.in);           //scanner object to input from user in console
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
	*/
	/*
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
	*/
	 
	
	/*
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
	*/
	
	/*
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
	*/
	/*
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
*/
	/*
	public static ArrayList<String> validateDayList(int listSize){
		ArrayList<String> dayList = new ArrayList<>();
		boolean validList = false;
		do {
            System.out.println("\nPlease enter "+listSize+" working days, separated by [,]:");
            in.useDelimiter("\r\n");
            String enteredDays = in.next();
            String[] days = enteredDays.split(",");
            if(days.length == listSize){
            	validList = true;
            	for(int a = 0; a < days.length; a++){
            		dayList.add(days[a]);
            	}
            }else{
            	validList = false;
            }
    	}while(!validList);
	    	
		return dayList;
	}
	*/
	/*

	public static ArrayList<String> validateActivityList(int listSize){
		ArrayList<String> activityList = new ArrayList<>();
		boolean validList = false;
		do {
            System.out.println("Please enter "+listSize+" activities, separated by [,]:");
            in.useDelimiter("\r\n");
            String enteredActivities = in.next();
            String[] activities = enteredActivities.split(",");
            if(activities.length == listSize){
            	validList = true;
            	for(int a = 0; a < activities.length; a++){
            		activityList.add(activities[a]);
            	}
            }else{
            	validList = false;
            }
    	}while(!validList);
	    	
		return activityList;
	}
	*/
	
	/*
	public static ArrayList<String> validateWorkingTimeList(int listSize){
		ArrayList<String> workingTimeList = new ArrayList<>();
		boolean validList = false;
		do {
            System.out.println("Please enter "+listSize+" working time i,e [1,06:00,09:00;2,07:00,10:00]:");
            //Semicolon [;] separates working times for different activities
            // [1,06:00,09:00] means activity 1 will start at 06:00 and end at 09:00 and so on for other activities
            in.useDelimiter("\r\n");
            String enteredWorkingTimes = in.next();
            String[] workingTimes = enteredWorkingTimes.split(";");
            if(workingTimes.length == listSize){
            	validList = true;
            	for(int a = 0; a < workingTimes.length; a++){
            		workingTimeList.add(workingTimes[a]);
            	}
            }else{
            	validList = false;
            }
    	}while(!validList);
	    	
		return workingTimeList;
	}
	*/	
}
