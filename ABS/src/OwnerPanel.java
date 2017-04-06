
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class OwnerPanel {
    private static Scanner in=new Scanner(System.in);           //scanner object to input from user in console
    /**
     * function to add new employee
     */

    public static void AddEmployeeWorkingTime()
    {
        try
        {
            FileWriter fw = new FileWriter("employeeinfo.txt",true);          //open text file in writer append mode
            BufferedWriter bw=new BufferedWriter(fw);       //gave access of file to buffer writer
            System.out.println("Employee Name");       
            String name = in.next();             //append , for easiness n data extraction
            System.out.println("Employee Working Day(Monday-Friday)");
            String day = in.next();
            System.out.println("Enter Starting Time i.e (06:00)");
            String start = in.next();
            System.out.println("Enter Ending Time i.e (08:00)");
            String end = in.next();
            System.out.println("Enter the service provided i.e (Haircut, Wash, Colour):");
            String service = in.next();
            int partOne, partTwo;
            int gap = 30;
            String prev = "";
            while(!start.equals(end))
            {
                prev = start;
                String[] starting = start.split(":");
                
                partOne = Integer.parseInt(starting[0]);
                partTwo = Integer.parseInt(starting[1]);
                while(gap>0){
                    
                    if(partTwo<60){
                        partTwo++;
                    }
                    if(partTwo==60){
                        partOne++;
                        partTwo = 00;
                    }
                    gap--;
                }
                gap = 30;
                if(partOne<10 && partTwo<10){
                    start = "0"+partOne+":0"+partTwo;
                }
                else if(partOne<10){
                    start = "0"+partOne+":"+partTwo;
                }else if(partTwo<10){
                    start = partOne+":0"+partTwo;
                }else
                    start = partOne+":"+partTwo;
                String selectedDay = day.toLowerCase();
                String selectedService = service.toLowerCase();
                String selectedEmployee = name.toLowerCase();
                bw.write(selectedEmployee + "," + selectedDay + "," + prev + "-" + start + ",available" + "," + selectedService);
                bw.newLine();
            }
            bw.close();
        }
        catch(Exception e)
        {
        	System.out.println("There is an exception in File Handling: "+e);
        }
    }
   
    public static void ShowWorkingTimeTable()
    {
      
    }
    
    public static void ShowSummaries()
    {
    	
    }
      
}

