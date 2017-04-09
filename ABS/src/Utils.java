import java.util.Scanner;

public class Utils {
	private static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
	public static String validateInput(String message, String regex){
	    	boolean validInput = false;
	    	String input = null;
	    	do {
	    		System.out.println(message);
	    		in.useDelimiter("\r\n");
			input = in.next();
	    	if (input.matches(regex)){
	    		validInput = true;
	    	}
	    }while (!validInput);
		
		return input;
	}
	public static String validateIntegerInput(String message, int min, int max){
    	boolean validInput = false;
    	String input = null;
    	String regex = "[" + min + "-" + max + "]";
    	do {
    		System.out.println(message);
    		in.useDelimiter("\r\n");
		input = in.next();
    	if (input.matches(regex)){
    		validInput = true;
    	}
    }while (!validInput);
	
	return input;
}
}
