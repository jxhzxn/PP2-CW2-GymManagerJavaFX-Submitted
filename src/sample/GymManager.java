package sample;

import java.io.IOException;

public interface GymManager {
    public int menuOption();
    public void functionChoose(int option);
    public void addMember();
    public void deleteMember();
    public void printAll();
    public void sorting();
    public void fileSave() throws IOException;
    public void openGUI();
}
