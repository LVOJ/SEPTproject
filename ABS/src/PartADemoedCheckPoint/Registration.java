package PartADemoedCheckPoint;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Registration {
    
    /**
     * function to register the new customers
     */
    public void Register()
    {
        try
        {
        	String firstName = "";
        	String lastName = "";
        	String phone = "";
        	String address = "";
        	String username = "";
        	String password = "";
        	
            FileWriter fw=new FileWriter("customerinfo.txt",true);          //open text file in writer append mode
            BufferedWriter bw=new BufferedWriter(fw);       //gave access of file to buffer writer
            
            firstName = Utils.validateInput("Enter First Name i.e. (Tom):", "^[A-Za-z]+$");
            bw.write(firstName);        //write string input from console directly to file
            bw.write(",");              //append , for easiness n data extraction
            
            lastName = Utils.validateInput("Enter Last Name i.e. (Smith):", "^[A-Za-z]+$");       
            bw.write(lastName);        //write string input from console directly to file
            bw.write(",");              //append , for easiness n data extraction
            
            phone = Utils.validateInput("Enter Phone i.e. (0412345678):", "^[0-9]{10}$"); 
            bw.write(phone);
            bw.write(",");
            
        	address = Utils.validateInput("Enter Address i.e. (1 Sesame St):", "^[\\d]+([.]|[-]|\\s)[A-Za-z]*([.]|[-]|\\s)[A-Za-z]*$");
            bw.write(address);
            bw.write(",");
            
            username = Utils.validateInput("Enter Username i.e. (Tsmith1 - Maximum length of 15):", "^[\\w]{1,15}$");
            bw.write(username);
            bw.write(",");
            
            password = Utils.validateInput("Enter Password (Maximum length of 15):", "^[\\w]{1,15}$");
            bw.write(password);
            
            bw.newLine();       //after all data inserted an endline to make file ready for next registration
            bw.close();
        }
        catch(IOException e)        //catch any exception occur during this process
        {
            System.out.println("Theer is an exception in File Handling: "+e);
        }
    }
   
}
