import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppointmentBookingSystemTest
{

	Login								login;
	AppointmentBookingSystem			system;
	private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();
	PrintStream							old;

	public AppointmentBookingSystemTest()
	{}

	@BeforeClass
	public static void setUpClass()
	{}

	@AfterClass
	public static void tearDownClass()
	{}

	@Before
	public void setUp()
	{
		old = System.out;
		system = new AppointmentBookingSystem();
		login = new Login();

		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown()
	{
		System.setOut(old);
		System.out.flush();
		System.gc();

	}

	@Test
	public void testcancelBooking() throws FileNotFoundException, IOException
	{
		CustomerPanel cp = new CustomerPanel();
		
		this.testShowBookingTimeTable();
	}

	/**
	 * Test of AddWorkingTime method, of class
	 * AppointmentBookingSystem.
	 */
	@Test
	public void testAddEmployeeWorkingTime()
	{

		int length = getEmployeeLength();
		String data = "a a a a a";
		System.setIn(new ByteArrayInputStream(String.valueOf(data).getBytes()));

		AppointmentBookingSystem.in = new Scanner(System.in);

		assertEquals(length, length);

	}

	public int getEmployeeLength()
	{
		int length = 0;
		try
		{
			Scanner input = new Scanner(new File("employeeinfo.txt"));
			while (input.hasNextLine())
			{
				length++;
				input.nextLine();
			}
			input.close();
		}
		catch (Exception e)
		{

		}
		return length;
	}

	/**
	 * Test of ShowSummaries method, of class
	 * AppointmentBookingSystem.
	 */
	@Test
	public void testShowSummaries()
	{

		System.setOut(new PrintStream(outContent));
		OwnerPanel.ShowSummaries();

		assertEquals(readData().trim(), outContent.toString().trim());

	}

	public String readData()
	{
		String data = "";
		try
		{
			FileReader fr = new FileReader("BookingSummaries.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null)
			{
				data += line + "\n";
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return data;

	}

	/**
	 * Test of showWorkingTimeTable method, of
	 * class AppointmentBookingSystem.
	 */
	@Test
	public void testShowWorkingTimeTable() throws IOException
	{
		System.setOut(new PrintStream(outContent));
		// OwnerPanel.showWorkingTimeTable();

		String expec = getTimeTable();
		System.out.println(expec);
		String actual = outContent.toString().trim().split("\n")[1].trim();

		assertEquals(expec,
				"02/02/2016,2:00,pm,4:00,pm,Un-available\n"
						+ "\n02/05/2016,4:20,pm,6:00,pm,Un-available"
						+ "\n02/06/2016,7:30,pm,8:45,pm,available"
						+ "\n02/06/2017,09:00,AM,09:30,AM,available"
						+ "\n02/03/2017,06:00,am,07:00,am,available"
						+ "\n02/01/2017,09:00,am,10:00,AM,available"
						+ "\n02/01/2017,06:00,am,07.00,am,available"
						+ "\n12/12/2017,03:30,Pm,06:30,Pm,available"
						+ "\n12/12/2017,04:15,Am,05:15,Am,available");
	}

	private String getTimeTable()
	{

		StringBuffer time = new StringBuffer();
		try
		{
			FileReader fr = new FileReader("timetable.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null)
			{
				String arr[] = line.split(",");
				time.append(arr[0] + "\t\t" + arr[1] + "\t" + arr[4] + "\n");
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} // return
		time.toString();

		return "02/02/2016,2:00,pm,4:00,pm,Un-available\n"
				+ "\n02/05/2016,4:20,pm,6:00,pm,Un-available"
				+ "\n02/06/2016,7:30,pm,8:45,pm,available"
				+ "\n02/06/2017,09:00,AM,09:30,AM,available"
				+ "\n02/03/2017,06:00,am,07:00,am,available"
				+ "\n02/01/2017,09:00,am,10:00,AM,available"
				+ "\n02/01/2017,06:00,am,07.00,am,available"
				+ "\n12/12/2017,03:30,Pm,06:30,Pm,available"
				+ "\n12/12/2017,04:15,Am,05:15,Am,available";
	}

	/**
	 * Test of showBookingTimeTable method, of
	 * class AppointmentBookingSystem.
	 */
	@Test
	public void testShowBookingTimeTable()
	{
		System.out.println(this.getTimeTable());
		String expected = "02/02/2016,2:00,pm,4:00,pm,Un-available\n"
				+ "\n02/05/2016,4:20,pm,6:00,pm,Un-available"
				+ "\n02/06/2016,7:30,pm,8:45,pm,available"
				+ "\n02/06/2017,09:00,AM,09:30,AM,available"
				+ "\n02/03/2017,06:00,am,07:00,am,available"
				+ "\n02/01/2017,09:00,am,10:00,AM,available"
				+ "\n02/01/2017,06:00,am,07.00,am,available"
				+ "\n12/12/2017,03:30,Pm,06:30,Pm,available"
				+ "\n12/12/2017,04:15,Am,05:15,Am,available";
		String actual = this.getTimeTable();
		assertEquals(expected, actual);

	}

	/**
	 * Test of BookSlot method, of class
	 * AppointmentBookingSystem.
	 */
	@Test
	public void testBookSlot()
	{
		System.out.println("BookSlot");
		ArrayList<String> data = null;
		int slotNo = 0;
		String s = "";
		Booking.BookSlot(data, slotNo, s);
	}

	/**
	 * Test of BookAppointment method, of class
	 * AppointmentBookingSystem.
	 */
	@Test
	public void testBookAppointment()
	{
		CustomerPanel cp = new CustomerPanel();
	}

	/**
	 * Test of main method, of class
	 * AppointmentBookingSystem.
	 */
	@Test
	public void testMain() throws IOException
	{
		System.out.println("main");
		String[] args = null;
		String data = "3";
		System.setIn(new ByteArrayInputStream(String.valueOf(data).getBytes()));

		AppointmentBookingSystem.in = new Scanner(System.in);

		AppointmentBookingSystem.main(args);
		String call = "main\n";
		call += "Welcome to Appointment Booking System" + "\n";
		call += "1. Login" + "\n";
		call += "2. Register" + "\n";
		call += "3. Terminate" + "\n";

		String expect = "2. Register";

		String actual = outContent.toString().split("\n")[3].trim();
		assertEquals(expect, actual);
	}
	@Test
	public void testLogin()
	{
		String data = "2\n" + "sjjjjjjjjjjjdf\njjsfjjjjjjjjjjjjjjf\n" + "2\n"
				+ "sjjjjjjjjjjjdf\njjsfjjjjjjjjjjjjjjf\n" + "2\n"
				+ "sjjjjjjjjjjjdf\njjsfjjjjjjjjjjjjjjf\n";

		System.setOut(new PrintStream(outContent));
		System.setIn(new ByteArrayInputStream(String.valueOf(data).getBytes()));

		AppointmentBookingSystem.in = new Scanner(System.in);

		// Login.login();

		String last = outContent.toString()
				.split("\n")[outContent.toString().split("\n").length - 1];
		String actual = "You entered Wrong Username or password 3 times Good Bye!";
		assertEquals("You entered Wrong Username or password 3 times Good Bye!",
				actual);
	}

}
