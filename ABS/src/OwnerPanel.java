
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class OwnerPanel {
    private static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
    /**
     * function to add new employee
     */
    public static void AddEmployee()
    {
        try
        {
            FileWriter fw=new FileWriter("employeeinfo.txt",true);          //open text file in writer append mode
            BufferedWriter bw=new BufferedWriter(fw);       //gave access of file to buffer writer
            System.out.println("Enter First Name");       
            bw.write(in.next());        //write string input from console directly to file
            bw.write(",");              //append , for easiness n data extraction
            System.out.println("Enter Last Name");       
            bw.write(in.next());        //write string input from console directly to file
            bw.write(",");              //append , for easiness n data extraction
            System.out.println("Enter Phone");
            bw.write(in.next());
            bw.write(",");
            System.out.println("Enter Address");
            bw.write(in.next());
            bw.write(",");
            System.out.println("Enter Availability time");
            bw.write(in.next());
            bw.newLine();       //after all data inserted an endline to make file redy for next registration
            bw.close();
        }
        catch(Exception e)
        {
             System.out.println("Ther is an exception in File Handling: "+e);
        }
    }
    public static void AddWorkingTime(){
   	
    	 try
         {
             FileWriter fw=new FileWriter("TimeTable.txt",true);          //open text file in writer append mode
             BufferedWriter bw=new BufferedWriter(fw);       //gave access of file to buffer writer
             System.out.println("Enter Date(i.e DD/mm/yyyy):");
             bw.write(in.next());
             bw.write(",");
             System.out.println("Start Time (i.e 2:00):");
             bw.write(in.next());
             bw.write(",");
             System.out.println("am/pm?");
             bw.write(in.next());
             bw.write(",");
             System.out.println("End Time (i.e 4:00):");
             bw.write(in.next());
             bw.write(",");
             System.out.println("am/pm?");
             bw.write(in.next());
             bw.write(",");
             bw.write("available");
             bw.newLine();       //after all data inserted an endline to make file redy for next registration
             bw.close();
         }
         catch(Exception e)
         {
              System.out.println("Ther is an exception in File Handling: "+e);
         }
    }
   
    public static void showWorkingTimeTable()
    {
      
    }
    public static void ShowSummaries()
    {}
      
}

