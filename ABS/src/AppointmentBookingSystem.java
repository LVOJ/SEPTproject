import java.util.Scanner;
public class AppointmentBookingSystem {

    public static Scanner in=new Scanner(System.in);           //scanner object to input from user in console
    
    private static String data2[];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login log = new Login();
        OwnerPanel owner = new OwnerPanel();
        CustomerPanel customer = new CustomerPanel();
        Registration reg = new Registration();
        boolean flag=false,terminate=false;
        while(!terminate)
        {
        System.out.println("Welcome to Appointment Booking System:");
        System.out.println(" 1. Login");
        System.out.println(" 2. Register");
        System.out.println(" 3. Terminate");
        do
        {
            flag=false;
            switch(in.nextInt())
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
                        System.out.println("Welcome to Owner's Panel:");
                        System.out.println(" 1. Add new Employee Working Time");
                        //real functionality will be in in part 2                      
                        System.out.println(" 2. Look Summaries of Bookings");
                        System.out.println(" 3. Show All Worker's avalaibility next week");
                        //till here
                        do
                        {
                            flag1=false;
                            switch(in.nextInt())
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
                                    owner.ShowWorkingTimeTable();
                                    break;
                                }
                            }
                        }
                        while(flag1);
                    }
                    else if(CustomerFlag)   //else its customer's login data
                    {
                        System.out.println("\nCustomer Successfully Logged in.\n");
                        for(int i=0;i<data2.length;i++)
                        {
                            System.out.print(data2[i]+" ");
                        }
                        boolean flag1;
                          do
	                        {
		                        System.out.println("Welcome to Customer's Panel:");
		                        System.out.println(" 1. Show All Booking Time Slots");
		                        System.out.println(" 2. Book Appointment");   
		                        System.out.println(" 3. Terminate"); 
	                            flag1=true;
	                            switch(in.nextInt())
	                            {
	                                case 1:
	                                {
	                                    customer.ShowBookingTimeTable();
	                                    break;
	                                }
	                                case 2:
	                                {
	                                    customer.BookAppointment(data2);
	                                    break;
	                                }
	                                case 3:
	                                {	
	                                	flag1 = false;
	                                    terminate = true;     //terminate flag will true so it will terminate main while loop
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

