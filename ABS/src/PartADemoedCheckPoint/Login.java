package PartADemoedCheckPoint;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    private static Scanner in=new Scanner(System.in);           //scanner objext to input from user in console
    private static boolean OwnerFlag;       //owner flag user to determine either login user is owner or not
    private static boolean CustomerFlag;       //owner flag user to determine either login user is owner or not
    /**
     * function to login user either it is Owner or customer
     * @return Login Data as String[] array
     */
    public static String[] login()
    {
        //Scanner in = new Scanner(System.in);
        boolean flag=false;
        String File="";
        String UserName,Password;
        String input ="";

        input = Utils.validateIntegerInput("Choose Login Type\n1. Business Owner\n2. Customer\n", 1, 2);
        int choice = Integer.parseInt(input);
        do
        {
            flag=false;
            switch(choice)
            {
                case 1:
                {
                    File="business.txt";
                    OwnerFlag=true;         //false due to owner entry
                    CustomerFlag = false;
                    break;
                }
                case 2:
                {
                    File="customerinfo.txt";
                    OwnerFlag=false;            //false due to non owner
                    CustomerFlag = true;
                    break;
                }
                default:
                {
                    flag=true;
                    System.out.println("Enter Valid Input");
                }
            }
        }
        while(flag);
        int count=1;
        do
        {
            flag=false;
            System.out.println("UserName :");
            UserName=in.next();     //get input username from user
            System.out.println("Password :");
            Password=in.next();        //get input password from user
            boolean authenticFlag=false;
            try
            {
                String line="",arr[];
                FileReader fr=new FileReader(File);
                BufferedReader br=new BufferedReader(fr);
                while((line=br.readLine())!=null)
                {
                    arr=line.split(",");        //make array of data split on , from file (each line)
                    if(arr[4].equals(UserName)) //compare username with username in file
                    {
                        if(arr[5].equals(Password))   //compare password with password in file
                        {
                            authenticFlag=true; //success authenticate
                            if(OwnerFlag==true){
                                OwnerFlag = true;
                            }
                            else if(CustomerFlag == true){
                                CustomerFlag = true;
                            }
                            return arr;         //return data of user       
                        }
                    }
                }
                if(!authenticFlag)
                {
                    if(count==3)    //check count (how many time user input wrong input
                    {
                        System.out.println("You entered Wrong Username or password 3 times.\n Good Bye!");
                        OwnerFlag = false;
                        CustomerFlag = false;
                        return null;
                    }
                    flag=true;
                    System.out.println("Wrong Username or password.\nPlease Try Again!");
                    count++;    //count ++ if user enter wrong username or password
                }
            }
            catch(IOException e)    //handling excetion
            {
                System.out.println("Ther is an exception in File Handling: "+e);
                return null;
            }
        }
        while(flag);
        return null;
    }
    public boolean getOwnerFlag(){
        return OwnerFlag;
    }
    public boolean getCustomerFlag(){
        return CustomerFlag;
    }
}

