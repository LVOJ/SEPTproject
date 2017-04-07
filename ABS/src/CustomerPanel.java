import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.nio.file.*;

public class CustomerPanel {
    private static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
    
    public static void BookAppointment(String[] data2)
    {
    	Booking book = new Booking();
        ArrayList<String> data = new ArrayList<>();
        try
        {
            FileReader fr=new FileReader("employeeinfo.txt");
            BufferedReader br=new BufferedReader(fr);
            String line="";
            int i = 1;
        	String selectedService = validateService();
        	String selectedDay = validateDay();

        	ArrayList<String> employeeNames = getEmployeeNames();
        	String selectedEmployee = validateEmployees(employeeNames);

            lineGenerator();
            System.out.printf("%1s%10s%20s%12s\n","Employee Name","Day","Time Available","Status");
            lineGenerator();
            //System.out.println("Employee Name\tDay\tTime Available\tStatus");
            
            List<String> appointment = data;
            while( (line = br.readLine())!= null )
            {
            	boolean relevantTimeslot = false;
            	data.add(line);     //save list of bookings
                String arr[] = line.split(",");
            	if (arr[4].contains(selectedService)){
                	if(arr[1].contains(selectedDay)){
                		if(arr[0].contains(selectedEmployee)){
                    		relevantTimeslot = true;
                		}else if (selectedEmployee.equals("*")){
                        	relevantTimeslot = true;
                        }

                	}
                }
                if(relevantTimeslot == true){
                	System.out.printf("%1s%1s%20s%15s%20s\n",i+"-",arr[0],arr[1],arr[2],arr[3]);
                    i++;
                }
                
                //System.out.println(i+"-"+arr[0]+"\t\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]);

            }
            br.close();
            System.out.println("\nSelect appointment time i.e (1,2)");
            String select=in.next();
            String[] selected = select.split(",");
            int choice = 0;
            for(int a=0;a<selected.length;a++){
                choice = Integer.parseInt(selected[a]);
            
                if(choice<=data.size())
                {
                    String arr[]=data.get(choice-1).split(",");
                    if(arr[3].equals("available"))
                    {
                        book.BookSlot(data,choice);
                        BufferedWriter writer2 = new BufferedWriter(new FileWriter("BookingSummaries.txt",true));
                        String s=data.get(choice-1);
                        s=s.replace("available", "");
                        s=s.replace(",", " ");
                        writer2.write("Customer "+data2[0]+" "+data2[1]+" booked Appointment on "+s);
                        writer2.newLine();
                        System.out.println("Customer "+data2[0]+" "+data2[1]+" booked Appointment on "+s);
                        writer2.close();
                    }
                    else
                        System.out.println("Slot Already Booked");
                }
                else
                    System.out.println("Not Valid Slot Try Again!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
    }
    public static void ShowBookingTimeTable()
    {
    	try
        {
            FileReader fr=new FileReader("employeeinfo.txt");
            BufferedReader br=new BufferedReader(fr);
            String line="";
            System.out.println("Employee Name\tDay\tTime Available\tStatus");
            while((line=br.readLine())!=null)
            {
                String arr[]=line.split(",");
                System.out.println(arr[0]+"\t\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]);
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
}

    static boolean showSpecificTimeSlots(int line){
    	return false;
	}
    
	public static void lineGenerator(){
	    for(int i = 0; i < 60; i++){
	        System.out.printf("-");
	    }
	    System.out.printf("\n");
	}
	private static ArrayList<String> getEmployeeNames(){
		String line="";
		int i = 0;
		ArrayList<String> employeeNames = new ArrayList<String>();
		try {
			FileReader fr;
			fr = new FileReader("employeeinfo.txt");
	        BufferedReader br = new BufferedReader(fr);
			while( (line = br.readLine())!= null ){
				String arr[] = line.split(",");
				String employee = arr[0].toLowerCase();
				if (!employeeNames.contains(employee)) {
					employeeNames.add(i, employee);
					i++;
				}				

			}
			/*br.close();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeNames;  
	}
	private static String validateEmployees(ArrayList<String> employeeNames){
		boolean validEmployee = false;
    	String selectedEmployee = null;
    	do {
            System.out.println("\nSelect an Employee (Enter * if you want to see all):");
            String selectEmployee = in.next();
            if (employeeNames.contains(selectEmployee.toLowerCase())){
	            	validEmployee = true;
    		}

            if (validEmployee){
            	selectedEmployee = selectEmployee.toLowerCase();
            }
    	}while(!validEmployee);
		return selectedEmployee;
		
	}
	private static String validateService(){
		String selectedService = null;
	 	boolean validService = false;
    	do {	
        	System.out.println("\nSelect service i.e. (Haircut, Wash, Colour):");
        	String selectService = in.next();
            switch(selectService.toLowerCase()){
	            case "haircut":
	            case "wash":
	            case "colour":
	            	validService = true;
	            	break;
            	default: 
            		break;
            }
            if (validService){
            	selectedService = selectService.toLowerCase();
            }
    	}while(!validService);
    	
    	return selectedService;
	}
	private static String validateDay(){
		boolean validDay = false;
		String selectedDay = null;
    	do {

            System.out.println("\nSelect day of appointment i.e.(Monday, Tuesday etc.):");
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
}
 