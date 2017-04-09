
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
public class Booking {
    public static void BookSlot(ArrayList<String> data ,int slotNo,String fileName)
    {
        try
        {
            //deleting line from timetable
            File inputFile = new File(fileName);
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = data.get(slotNo-1);
            String currentLine;

            while((currentLine = reader.readLine()) != null) 
            {
                if(currentLine.equals(lineToRemove)) 
                {
                    String newLine = currentLine.replace("available", "Un-available");
                    writer.write(newLine);
                    writer.newLine();
                }
                else
                {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
            writer.close(); 
            reader.close(); 
            inputFile.delete();
            tempFile.renameTo(inputFile);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
