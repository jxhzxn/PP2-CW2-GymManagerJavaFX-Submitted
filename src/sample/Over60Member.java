package sample;

public class Over60Member extends DefaultMember{

    public Over60Member(int mem_no, String name, int age,String memType,String gender,String city){
        super(mem_no,name,memType,gender,city);
        this.age = age;
    }

    private int age;

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

}
