package CommandLine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerPanel {
	public CustomerPanel(){
		
	}
    private static Scanner in=new Scanner(System.in);           //scanner object to input from user in console
    
    public static void BookAppointment(String[] data2) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("services.txt"));
        ArrayList<String> services = new ArrayList<String>();
        ArrayList<String> activities = new ArrayList<String>();
        String line = "";
        while((line=br.readLine())!=null){
            services.add(line);
        }
        System.out.println("Services");
        for(int a=0;a<services.size();a++){
            System.out.println((a+1)+". "+services.get(a));
        }
        String choice = Utils.validateIntegerInput("Select a Service: ", 1,4);
        int selection = Integer.parseInt(choice);
        String fileName = services.get(selection-1);
        
        System.out.println("Activities");
        for(int a=0;a<activities.size();a++){
            System.out.println((a+1)+". "+activities.get(a));
        }
        System.out.print("Select an Activity: ");
        choice = Utils.validateIntegerInput("Select a Service: ", 1,4);
        String activity = activities.get(selection-1);
        
        fileName = fileName.toLowerCase();
        String service = fileName;
        fileName = fileName+".txt";
        br.close();
        Booking book = new Booking();
        ArrayList<String> data=new ArrayList<>();
        try
        {
            FileReader fr=new FileReader(fileName);
            br=new BufferedReader(fr);
            line="";
            System.out.printf("%1s%10s%25s%20s%12s\n","Employee Name","Day","Activity","Time Available","Status");
            int i=1;
            while((line=br.readLine())!=null)
            {
                data.add(line);     //save list of bookings
                String arr[]=line.split(",");
                System.out.printf("%1s%1s%20s%25s%15s%20s\n",i+". ",arr[0],arr[1],arr[2],arr[3],arr[4]);
                i++;
            }
            br.close();
            String select = Utils.validateInput("\nSelect Time i.e 1,2", "[\\d][,][\\d][)]");
            String[] selected = select.split(",");
            selection = 0;
            for(int a=0;a<selected.length;a++){
            	selection = Integer.parseInt(selected[a]);
                if(selection<=data.size())
                {
                    String arr[]=data.get(selection-1).split(",");
                    if(arr[4].equals("available"))
                    {
                        book.BookSlot(data,selection,fileName);
                        BufferedWriter writer2 = new BufferedWriter(new FileWriter("BookingSummaries.txt",true));
                        String s=data.get(selection-1);
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
    public void showBookingTimeTable(String[] customer) throws IOException
    {
        //BufferedReader br = new BufferedReader(new FileReader("BookingSummaries.txt"));
        ArrayList<HashMap<String, String>> bookedTimes = BookingTimeTable(customer);
        System.out.println("My Booked Services and Activities");
        if (bookedTimes.size() == 0) {
			System.out.println("\nYou have no bookings currently.. Book Below\n");
			BookCustomAppointment(customer);
			return;
		}
        
        System.out.printf("%1s%10s%25s%25s%20s\n","Employee Name","Day","Service","Activity","Time Available");
        for(int a = 0; a < bookedTimes.size(); a++){
        	HashMap<String, String> map = bookedTimes.get(a);
        	System.out.printf("%1s%20s%25s%25s%15s",map.get("EmpName"),map.get("Day"),map.get("Service"),map.get("Activity"),map.get("timeSlot"));
        	System.out.println();
        }
        
    }
    public static void BookCustomAppointment(String[] data2) throws FileNotFoundException, IOException{
        
        
        String choice = "";
        String fileName = "";
        String empName = "";
        boolean empSet = false;
        BufferedReader br = new BufferedReader(new FileReader("services.txt"));
        ArrayList<String> services = new ArrayList<String>();
        String line = "";
        while((line=br.readLine())!=null){
            services.add(line);
        }
        System.out.println("Services");
        for(int a=0;a<services.size();a++){
            System.out.println((a+1)+". "+services.get(a));
        }
        
        choice = Utils.validateIntegerInput("Select a Service: ", 1,services.size());
        int selection = Integer.parseInt(choice);
       	fileName = services.get(selection-1);

    	
    	fileName = fileName.toLowerCase();
        String service = fileName;
        fileName = fileName+".txt";
        br.close();
        boolean repeat = true, daySet = false ,activitySet =false;
        String day = "";
        String activity="";
        
        ArrayList<String> list = new ArrayList<String>();
        
        br = new BufferedReader(new FileReader(fileName));
        line = "";
        while((line=br.readLine())!=null){
            list.add(line);
        }
        ArrayList<String> temp = new ArrayList<String>();
        
        while(repeat){
        	ArrayList<String> serviceNames = Utils.getActivities(service);
        	int activityChoice = 0;
        	int dayChoice = 0;
        	int empChoice = 0;
        	if(activitySet==false){
				System.out.println("\nActivities");
				
		        for(int a = 0; a < serviceNames.size(); a++){
                	System.out.println((a+1)+". "+serviceNames.get(a));
                }
		        choice = Utils.validateIntegerInput("Select an activity", 1, serviceNames.size());
		        
     	        activityChoice = Integer.parseInt(choice);
     	        activity = serviceNames.get(activityChoice - 1);
     	        System.out.println("You Selected this activity "+activity);
     	        activitySet = true;
        	}
            if(daySet==false){
            	System.out.println("\ndays of appointment");
    			ArrayList<String> activityDays = Utils.getActivityAppointmentDays(service, activity);
    			for(int a = 0; a < activityDays.size(); a++){
                	System.out.println((a+1)+". "+activityDays.get(a));
                }
    			choice = Utils.validateIntegerInput("Select a day of appointment", 1, activityDays.size());
    			dayChoice = Integer.parseInt(choice);
    			day = activityDays.get(dayChoice - 1);
     	        System.out.println("You Selected this day "+day);
     	        daySet = true;
            }
            if(empSet==false){
            	System.out.println("\nEmployees");
                ArrayList<String> employeeNames = Utils.getEmployeeNames(fileName, activity, day); //checks if employee name exists ---
                for(int a = 0; a < employeeNames.size(); a++){
                	System.out.println((a+1)+". "+employeeNames.get(a));
                }
                choice = Utils.validateIntegerInput("Select employee to work for you", 1, employeeNames.size());
                empChoice = Integer.parseInt(choice);
    			empName = employeeNames.get(empChoice - 1);
     	        System.out.println("You Selected this employee "+empName);
     	        empSet = true;
     	        
            }
            
            int one = 0;
            for(int a=0;a<list.size();a++){
                String recs[] = list.get(a).split(",");
                if(recs[4].equals("available") && (recs[1].toLowerCase().equals(day) && (recs[0].toLowerCase().equals(empName) || empName.equals("*"))&&(recs[2].toLowerCase().equals(activity)))){
                    daySet = true;
                    activitySet=true;
                    empSet = true;
                    temp.add(list.get(a));
                    one++;
                }
                if(one<1){
                    if(recs[1].toLowerCase().equals(day) && !recs[0].toLowerCase().equals(empName)){
                        daySet = true;
                        empSet = false;
                    }
                    else if(!recs[1].toLowerCase().equals(day) && recs[0].toLowerCase().equals(empName)){
                        daySet = false;
                        empSet = true;
                    }
                }
            }
            if(daySet==true && empSet==true && activitySet==true){
                repeat = false;
                break;
            }
            if(daySet==false){
                System.out.println("Please Enter Some Other Day!");
                repeat = true;
            }
            
            if(empSet == false){
                System.out.println("Please Select Some Other Employee!");
                repeat = true;
            }
            
        }
        br.close();
        int i;
        System.out.println("\nDisplaying available time slots only");
        for (i = 0; i < temp.size(); i++) {
            System.out.println((i+1)+"."+temp.get(i));
        }
        int select = 0;
       // System.out.print("Select From Above: ");
        String selected;
        String recs[];
        while(true){
            int max = temp.size();
            choice = Utils.validateIntegerInput("Select From Above: ", 1, max);
            selection = Integer.parseInt(choice);
	        selected = temp.get(selection-1);
	        recs = selected.split(",");
	        
	        if(recs[4].equals("Un-available")){
	            System.out.println("Booking Un-Available");
	            System.out.println("Please select another");
	        }else{
	        	recs[4] = "Un-available";
	        	break;
	        }
            
        }
        
        String modified = recs[0]+","+recs[1]+","+recs[2]+","+ recs[3] + "," + recs[4];
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for(int a=0;a<list.size();a++){
            if(list.get(a).equals(selected)){
                list.set(a, modified);
                bw.write(list.get(a));
                bw.newLine();
            }
            else{
                bw.write(list.get(a));
                bw.newLine();
            }
        }
        bw.close();
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("BookingSummaries.txt",true));
        writer2.write("Customer,"+data2[0]+","+data2[1]+",booked Appointment on,"+recs[0]+","+recs[1]+","+/*servicename*/service+","+recs[2] +"," + recs[3]);
        writer2.newLine();
        writer2.close();
        System.out.println("You have Successfully Booked an appointment");
        
    }
	
    public void cancelBooking(String[] data2) throws FileNotFoundException, IOException{
    	System.out.println("My current Bookings are:");
    	ArrayList<HashMap<String, String>> bookedTimes = BookingTimeTable(data2);
        int ab = 0;
        for(ab = 0; ab < bookedTimes.size(); ab++){
        	HashMap<String, String> map = bookedTimes.get(ab);
        	System.out.printf("%1d%10s%10s%25s%25s%15s",(ab+1),map.get("EmpName"),map.get("Day"),map.get("Service"),map.get("Activity"),map.get("timeSlot"));
        	System.out.println();
        }
        if (ab == 0) {
			System.out.println("\nYou have no bookings currently. Book Below\n");
			BookCustomAppointment(data2);
			return;
		}
        int max = bookedTimes.size();
        String choice = Utils.validateIntegerInput("Select from above to cancel: ", 1,max);
        int selection = Integer.parseInt(choice);

        ArrayList<String> bookedSlotsList = new ArrayList<>();
		try{
			
			BufferedReader br = new BufferedReader(new FileReader("BookingSummaries.txt"));
	        String line = "";
	        while((line=br.readLine())!=null){
	        	bookedSlotsList.add(line);
	        }
	        br.close();
		}catch(Exception e){
			e.printStackTrace();
		}

        String fileName = "", empName = "",day = "", activityName = "", timeSlot = "";
        HashMap<String, String> map = bookedTimes.get(selection-1);
        fileName = map.get("Service");
        fileName = fileName.toLowerCase();
        fileName = fileName+".txt";
        String lineNumber = map.get("lineNumber");
        empName = map.get("EmpName");
        day = map.get("Day");
        activityName = map.get("Activity");
        timeSlot = map.get("timeSlot");
        
        File bookingSummaries = new File("BookingSummaries.txt");
        File tempBookingSummaries = new File("tempBookingSummaries.txt");
        BufferedReader reader = new BufferedReader(new FileReader(bookingSummaries));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempBookingSummaries));
        
        String lineToRemove = bookedSlotsList.get(Integer.parseInt(lineNumber) -1);
        String currentLine;
        
        while((currentLine = reader.readLine()) != null){
        	//trim newline when comparingwith lineToRemove
        	String trimmedLine = currentLine.trim();
        	if(trimmedLine.equals(lineToRemove)) continue;
        	writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        bookingSummaries.delete();
        tempBookingSummaries.renameTo(bookingSummaries);
        tempBookingSummaries.delete();
        
        File serviceFile = new File(fileName);
        File tempServiceFile = new File("tempServiceFile.txt");
        reader = new BufferedReader(new FileReader(serviceFile));
        writer = new BufferedWriter(new FileWriter(tempServiceFile));
        
        String lineToRename = empName+","+day+","+activityName+","+timeSlot+",Un-available";
        
        String currentLine1;
        
        while((currentLine1 = reader.readLine()) != null){
        	String trimmedLine = currentLine1.trim();
        	String[] trimmed = trimmedLine.split(",");
        	if(trimmed[4].equals("Un-available")){
        		lineToRename = empName+","+day+","+activityName+","+timeSlot+",available";
        		currentLine1 = lineToRename;
        	}
        	
        	writer.write(currentLine1 + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        serviceFile.delete();
        tempServiceFile.renameTo(serviceFile);
        tempServiceFile.delete();
        
        System.out.println("You have successfuly cancelled a booked time");
        System.out.println("My current Bookings are:");
    	bookedTimes = BookingTimeTable(data2);
        ab = 0;
        for(ab = 0; ab < bookedTimes.size(); ab++){
        	map = bookedTimes.get(ab);
        	System.out.printf("%1d%10s%10s%25s%25s%15s",(ab+1),map.get("EmpName"),map.get("Day"),map.get("Service"),map.get("Activity"),map.get("timeSlot"));
        	System.out.println();
        }
        if (ab == 0) {
			System.out.println("\nYou have no bookings currently. Book Below\n");
			BookCustomAppointment(data2);
			return;
		}
        
        
    }
	public ArrayList<HashMap<String, String>> BookingTimeTable(String[] data2) {
		ArrayList<HashMap<String, String>> bookedSlots = new ArrayList<>();
		String line = ""; 
		try
        {
        	BufferedReader br = new BufferedReader(new FileReader("BookingSummaries.txt"));
            line="";
            int lineCount = 0;
            while((line=br.readLine())!=null)
            {
            	lineCount ++;
            	System.out.println();
                String arr[]=line.split(",");
                if(arr[1].equals(data2[0]) && arr[2].equals(data2[1])){
                	HashMap<String, String> mapTimeTable = new HashMap<>();
                	mapTimeTable.put("EmpName", arr[4]);
                	mapTimeTable.put("Day", arr[5]);
                	mapTimeTable.put("Service", arr[6]);
                	mapTimeTable.put("Activity", arr[7]);
                	mapTimeTable.put("timeSlot", arr[8]);
                	mapTimeTable.put("lineNumber", String.valueOf(lineCount));

                	bookedSlots.add(mapTimeTable);
                	
                }
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		return bookedSlots;

	}
}

