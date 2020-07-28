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

            ArrayList<String> readArray = sample.Database.readAll();
            printWriter.println("Exported Data from the Database");
            printWriter.println("-------------------------------------");
            for(int count=0; count<=readArray.size()-1; count++){
                printWriter.println(readArray.get(count));
            }

            printWriter.close();
//        }

    }






}
