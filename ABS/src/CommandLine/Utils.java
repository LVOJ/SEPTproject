package CommandLine;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
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
	
	public static ArrayList<String> getEmployeeNames(String fileName, String activityName, String activityDayName){
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
				String day = arr[1].toLowerCase();
				String activity = arr[2].toLowerCase();
				if(activityName.equals(activity) && activityDayName.equals(day)){
					if (!employeeNames.contains(employee)) {
						employeeNames.add(i, employee);
						i++;
					}	
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

	public static ArrayList<String> getActivities(String serviceName){
		ArrayList<String> serviceActivities = new ArrayList<>();
		serviceName += ".txt";
		try{
			
			BufferedReader br = new BufferedReader(new FileReader(serviceName));
	        String line = "";
	        while((line=br.readLine())!=null){
	        	String[] activities = line.split(",");
	        	String particularActivity = activities[2];
	        	if(!serviceActivities.contains(particularActivity)){
	        		serviceActivities.add(particularActivity);
	            }
	        }
	        
	       // getActivityAppointmentDays()
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return serviceActivities;
	}

	public static ArrayList<String> getActivityAppointmentDays(String serviceName, String activityName){
		ArrayList<String> activityAppointmentDays = new ArrayList<>();
		serviceName += ".txt";
		try{
			BufferedReader br = new BufferedReader(new FileReader(serviceName));
	        String line = "";
	        while((line=br.readLine())!=null){
	        	String[] activities = line.split(",");
	        	String appointmentDay = activities[1];
	        	String particularActivity = activities[2];
	        	
	        	if(particularActivity.equals(activityName)){
		        	if(!activityAppointmentDays.contains(appointmentDay)){
		        		activityAppointmentDays.add(appointmentDay);
		        	}
	        	}
	        	
	        }
	       
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return activityAppointmentDays;
	}
	
}
