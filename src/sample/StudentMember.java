package sample;

//StudentMember class which is the subclass of the DefaultMember class
public class StudentMember extends DefaultMember{
    private String schoolName;

    //constructor of the StudentMember class
    public StudentMember(int mem_no, String name, String schoolName,String memType,String gender,String city){
        super(mem_no,name,memType,gender,city);
        this.schoolName = schoolName;
    }

    //getter to get the schoolName
    public String getSchool(){
        return schoolName;
    }

    //setter to set the schoolName
    public void setSchool(String schoolName){
        this.schoolName = schoolName;
    }

}
