package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileSave {


    public static void fileWrite() throws IOException {

        File file = new File("Database_Data.txt");
//        if (file.exists()) {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            ArrayList<String> readName = Database.readCustom("name");
            ArrayList<String> readMemType = Database.readCustom("mem_type");
            ArrayList<String> readCity = Database.readCustom("city");
            ArrayList<String> readGender = Database.readCustom("city");

            printWriter.println("Exported Data from the Database");
            printWriter.println("-------------------------------------");
            int counter = 1;
            for(int count=0; count<=readName.size()-1; count++){
                printWriter.println("["+counter+"]  \t\n"+" Full Name : "+readName.get(count)+" \n "+"Gender : "+readGender.get(count)+" \n "+"Member Type : "+readMemType.get(count)+" \n "+"City : "+readCity.get(count));
                printWriter.println("");
                counter++;
            }

            printWriter.close();
//        }
    }
}
