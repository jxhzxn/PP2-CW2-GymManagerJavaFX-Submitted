package sample;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //creating the MyGymManager Object
        MyGymManager myGymManager = new MyGymManager();
        //calling the method which has the primary Console Options
        myGymManager.functionChoose(myGymManager.menuOption());

    }
}
