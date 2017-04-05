
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registration {
    private static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
    /**
     * function to register the new customers
     */
    public void Register()
    {
        try
        {
            FileWriter fw=new FileWriter("customerinfo.txt",true);          //open text file in writer append mode
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
            System.out.println("Enter UserName");
            bw.write(in.next());
            bw.write(",");
            System.out.println("Enter Password");
            bw.write(in.next());
            bw.newLine();       //after all data inserted an endline to make file ready for next registration
            bw.close();
        }
        catch(IOException e)        //catch any exception occur during this process
        {
            System.out.println("Theer is an exception in File Handling: "+e);
        }
    }
}
