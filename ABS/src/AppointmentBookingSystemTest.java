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
import java.util.logging.Level;
import java.util.logging.Logger;

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
         
            try {
                OwnerPanel.AddEmployeeWorkingTime();
            } catch (IOException ex) {
                Logger.getLogger(AppointmentBookingSystemTest.class.getName()).log(Level.SEVERE, null, ex);
            }
         
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
    	
    	System.setOut(new PrintStream(outContent));
    	OwnerPanel.ShowSummaries();
        
        assertEquals(readData().trim(),outContent.toString().trim());
        
    }
    
    public String readData(){
    	String data="";
    	 try
         {
             FileReader fr=new FileReader("BookingSummaries.txt");
             BufferedReader br=new BufferedReader(fr);
             String line="";
             while((line=br.readLine())!=null)
             {
                data+=line+"\n";
             }
             br.close();
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
    	 return data;

    }

    

    /**
     * Test of showWorkingTimeTable method, of class AppointmentBookingSystem.
     */
    @Test 
    public void testShowWorkingTimeTable() throws IOException {
    	System.setOut(new PrintStream(outContent));
        OwnerPanel.showWorkingTimeTable();
        
        String expec=getTimeTable().trim().split("\n")[1].trim();
        String actual=outContent.toString().trim().split("\n")[1].trim();
        
        assertEquals(expec, actual);
    }
    
 private String getTimeTable(){
    	
    	StringBuffer time=new StringBuffer();
            try
            {
                FileReader fr=new FileReader("employeeinfo.txt");
                BufferedReader br=new BufferedReader(fr);
                String line="";
                time.append("Employee Name\tDay\tStatus\n");
                while((line=br.readLine())!=null)
                {
                    String arr[]=line.split(",");
                    time.append(arr[0]+"\t\t"+arr[1]+"\t"+arr[4]+"\n");
                }
                br.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return time.toString();
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
    	System.out.println("BookSlot");
        ArrayList<String> data = null;
        int slotNo = 0;
        String s = "";
        Booking.BookSlot(data, slotNo,s);
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
    public void testMain() throws IOException {
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
    
   
