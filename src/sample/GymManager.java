package sample;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GymManager {
    public int menuOption();
    public void functionChoose(int option) throws FileNotFoundException;
    public void addMember() throws FileNotFoundException;
    public void deleteMember();
    public void printAll();
    public void sorting();
    public void fileSave() throws IOException;
    public void openGUI();
}
