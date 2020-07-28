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

            ArrayList<String> readName = sample.Database.readName();
            ArrayList<String> readMemType = sample.Database.readMemType();
            printWriter.println("Exported Data from the Database");
            printWriter.println("-------------------------------------");
            for(int count=0; count<=readName.size()-1; count++){
                printWriter.println(readName.get(count)+"\t\t"+readMemType.get(count));
            }

            printWriter.close();
//        }

    }






}
