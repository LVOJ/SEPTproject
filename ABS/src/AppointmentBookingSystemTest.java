import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AppointmentBookingSystemTest {
    
	Login login;
	AppointmentBookingSystem system;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream old;
    public AppointmentBookingSystemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    	old=System.out;
    	system=new AppointmentBookingSystem();
    	login=new Login();
    	
    	System.setOut(new PrintStream(outContent));
    }
    
    
    @After
    public void tearDown() {
    	System.setOut(old);
    	System.out.flush();
    	System.gc();
    	
    }

    /**
     * Test of AddWorkingTime method, of class AppointmentBookingSystem.
     */
    @Test
    public void testAddEmployeeWorkingTime() {
        
    	int length = getEmployeeLength();
    	String data="a a a a a";
    	 System.setIn(new ByteArrayInputStream(String.valueOf(data).getBytes()));

         AppointmentBookingSystem.in=new Scanner(System.in);
         
         OwnerPanel.AddEmployeeWorkingTime();
         
         assertEquals(length, getEmployeeLength());
    	
    }

    public int getEmployeeLength(){
    	int length=0;
    	try{
    		Scanner input=new Scanner(new File("employeeinfo.txt"));
    		while(input.hasNextLine()){
    			length++;
    			input.nextLine();
    		}
    		input.close();
    	}
    	catch(Exception e){
    		
    	}
    	return length;
    }
 
    
    /**
     * Test of ShowSummaries method, of class AppointmentBookingSystem.
     */
    @Test 
    public void testShowSummaries() {
    	
       
        
    }
    
    

    

    /**
     * Test of showWorkingTimeTable method, of class AppointmentBookingSystem.
     */
    @Test 
    public void testShowWorkingTimeTable() {
    	
    }
    
    

    /**
     * Test of showBookingTimeTable method, of class AppointmentBookingSystem.
     */
    @Test
    public void testShowBookingTimeTable() {
        
    	
    	
    }
  

    /**
     * Test of BookSlot method, of class AppointmentBookingSystem.
     */
    @Test @Ignore
    public void testBookSlot() {
        
    }

    /**
     * Test of BookAppointment method, of class AppointmentBookingSystem.
     */
    @Test 
    public void testBookAppointment() {
      
    }

    /**
     * Test of main method, of class AppointmentBookingSystem.
    */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        String data="3";
        System.setIn(new ByteArrayInputStream(String.valueOf(data).getBytes()));

        AppointmentBookingSystem.in=new Scanner(System.in);
        
        
        
        AppointmentBookingSystem.main(args);
        String call="main\n";
        call+="Welcome to Appointment Booking System"+"\n";
        call+="1. Login"+"\n";
        call+="2. Register"+"\n";
        call+="3. Terminate"+"\n";
        
        
        String expect="2. Register";
        
        String actual=outContent.toString().split("\n")[3].trim();
        assertEquals(expect, actual);
    }
    @Test
    public  void testLogin(){
    	String data="2\n"
    			+ "sjjjjjjjjjjjdf\njjsfjjjjjjjjjjjjjjf\n"
    			+ "2\n"
    			+ "sjjjjjjjjjjjdf\njjsfjjjjjjjjjjjjjjf\n"
    			+ "2\n"
    			+ "sjjjjjjjjjjjdf\njjsfjjjjjjjjjjjjjjf\n";
    	
    //	System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(String.valueOf(data).getBytes()));

        AppointmentBookingSystem.in=new Scanner(System.in);
        
        
    	Login.login();
    	
    	String last=outContent.toString().split("\n")[outContent.toString().split("\n").length-1];
    	
    	assertEquals("You entered Wrong Wresname or password 3 times Good Bye!", last.trim());
    }
    
}
    
   
