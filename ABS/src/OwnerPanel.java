

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OwnerPanel {
    private static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
    /**
     * function to add new employee
     */
    public static void AddEmployeeWorkingTime() throws IOException
    {
    	Scanner in2 = new Scanner(System.in);
        int choice = 0;
        String fileName = "";
        System.out.print("Enter Service: ");
        fileName = in.next();
        fileName = fileName.toLowerCase();
        fileName = fileName+".txt";
        
        try
        {
            FileWriter fw=new FileWriter(fileName,true);          //open text file in writer append mode
            BufferedWriter bw=new BufferedWriter(fw);       //gave access of file to buffer writer
            System.out.println("Employee Name");       
            String name = in.next();             //append , for easiness n data extraction
            System.out.println("Employee Working Day(Monday-Sun)");
            String day = in.next();
            System.out.println("Activity: ");
            String activity = in2.nextLine();
            System.out.println("Enter Starting Time i.e (06:00)");
            String start = in.next();
            System.out.println("Enter Ending Time i.e (06:00)");
            String end = in.next();
            int partOne, partTwo;
            int gap = 30;
            String prev = "";
            while(!start.equals(end)){
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
                bw.write(name+","+day+","+activity+","+prev+"-"+start+",available");
                bw.newLine();
        }
                
                
            bw.close();
        }
        catch(Exception e)
        {
             System.out.println("Ther is an exception in File Handling: "+e);
        }
    }
    
    public static void showWorkingTimeTable() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("services.txt"));
        ArrayList<String> services = new ArrayList<String>();
        String line = "";
        while((line=br.readLine())!=null){
            services.add(line);
        }
        System.out.println("Services");
        for(int a=0;a<services.size();a++){
            System.out.println((a+1)+"- "+services.get(a));
        }
        System.out.print("Select a Service: ");
        int choice = in.nextInt();
        String fileName = services.get(choice-1);
        fileName = fileName.toLowerCase();
        String service = fileName;
        fileName = fileName+".txt";
        br.close();
        try
        {
            FileReader fr=new FileReader(fileName);
            br=new BufferedReader(fr);
            line="";
            System.out.printf("%1s%10s%25s%20s%12s\n","Employee Name","Day","Activity","Time Available","Status");
            while((line=br.readLine())!=null)
            {
                
                
                String arr[]=line.split(",");
                if(arr.length==4)
                    System.out.printf("%1s%20s%25s%15s%20s\n",arr[0],arr[1],arr[2],"",arr[3]);
                else
                    System.out.printf("%1s%20s%25s%15s%20s\n",arr[0],arr[1],arr[2],arr[3],arr[4]);
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void ShowSummaries()
    {
        try
        {
            FileReader fr=new FileReader("BookingSummaries.txt");
            BufferedReader br=new BufferedReader(fr);
            String line="";
            while((line=br.readLine())!=null)
            {
                System.out.print(line+"\n");
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   
}
