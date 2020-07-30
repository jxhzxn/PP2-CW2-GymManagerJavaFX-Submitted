package sample;

//Over60Member class which is the subclass of the DefaultMember class
public class Over60Member extends DefaultMember{
    private int age;

    //Constructor of the Over60Member Class
    public Over60Member(int mem_no, String name, int age,String memType,String gender,String city){
        super(mem_no,name,memType,gender,city);
        this.age = age;
    }

    //getter to get the age
    public int getAge(){
        return age;
    }

    //setter to set the age
    public void setAge(int age){
        this.age = age;
    }

}
