import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerPanel {
    private static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
    
    public static void BookAppointment(String[] data2)
    {
       
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
        
}}

