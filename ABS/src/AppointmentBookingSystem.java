
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class AppointmentBookingSystem {

    public static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
    
    private static String data2[];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
    	String input = "";
        
        Login log = new Login();
        OwnerPanel owner = new OwnerPanel();
        CustomerPanel customer = new CustomerPanel();
        Registration reg = new Registration();
        boolean flag=false,terminate=false;
        while(!terminate)
        {
	        input = Utils.validateIntegerInput("Welcome to Appointment Booking System\n1. Login\n2. Register\n3. Terminate", 1, 3);
	        int choice = Integer.parseInt(input);
	        do
	        {
	            flag=false;
	            switch(choice)
	            {
	                case 1:
	                {
	                    data2=log.login();
	                    boolean OwnerFlag = log.getOwnerFlag();
	                    boolean CustomerFlag = log.getCustomerFlag();
	                    //if login data is owner's data
	                    if(OwnerFlag)
	                    {
	                        boolean flag1;
	                        input = Utils.validateIntegerInput("Welcome to Owner's Panel\n1. Add new Employee Working Time\n2. Look Summaries of Bookings\n3. Show All Worker's avalaibility next week\n4. Add New Service\n5. Delete Service", 1, 5);
	                        int choice1 = Integer.parseInt(input);
	                        do
	                        {
	                            flag1=false;
	                            switch(choice1)
	                            {
	                                case 1:
	                                {
	                                    owner.AddEmployeeWorkingTime();  //add new employee access only available to Owner
	                                    break;
	                                }
	                                case 2:
	                                {
	                                    owner.ShowSummaries();
	                                    break;
	                                }
	                                case 3:
	                                {
	                                    owner.showWorkingTimeTable();
	                                    break;
	                                }
	                               
	                            }
	                        }
	                        while(flag1);
	                    }
	                    else if(CustomerFlag)   //else its customer's login data
	                    {		
	                    	String label = "";
	                        System.out.println("Customer Successfully Logged in.\n");
	                        System.out.println("Customer Details:\n");
	                        for(int i=0;i<data2.length;i++)
	                        	
	                        {
	                        	if (i == 0){
	                        		label = "First Name:";
	                        	} else if (i == 1){
	                        		label = "Last Name:";
	                        	} else if (i == 2){
	                        		label = "Phone:\t";
	                        	} else if (i == 3){
	                        		label = "Address:";
	                        	} else if (i == 4){
	                        		label = "Username:";
	                        	}else if (i == 5){
	                        		label = "Password:";
	                        	}
	                        	 System.out.print(label + "\t"+data2[i]+"\n");
	                        }
	                        boolean flag1;
	                        input = Utils.validateIntegerInput("\nWelcome to Customer's Panel\n1. Show Booking Time Slots\n2. Book Custom Appointment\n3. Cancel Booking\n", 1, 3);
	                        int choice2 = Integer.parseInt(input);
	                        
	                        do
	                        {
	                            flag1=false;
	                            switch(choice2)
	                            {
	                                case 1:
	                                {
	                                    customer.showBookingTimeTable();
	                                    break;
	                                }
	//                                case 2:
	//                                {
	//                                    customer.BookAppointment(data2);
	//                                    break;
	//                                }
	                                case 2:
	                                {
	                                    customer.BookCustomAppointment(data2);
	                                    break;
	                                }
	                                case 3:
	                                {
	                                    customer.cancelBooking();
	                                    break;
	                                }
	                            }
	                        }
	                        while(flag1);
	                        
	                        
	                    }
	                    break;
	                    
	                }   
	                case 2:
	                {
	                    //register new customer
	                    reg.Register();
	                    break;
	                }
	                case 3:
	                {
	                    terminate=true;     //terminate flag will true so it will terminate main while loop
	                    break;
	                }
	                default:
	                {
	                    flag=true;
	                    System.out.println("Enter Valid Input");
	                }
	            }
	            
	        }
	        while(flag);    //if user enter wrong input it will try make him again 
        }
    }
    
}
