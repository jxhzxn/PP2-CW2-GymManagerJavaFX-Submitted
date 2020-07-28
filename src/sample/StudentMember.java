package sample;

public class StudentMember extends DefaultMember{
    private String schoolName;

    public StudentMember(int mem_no, String name, String schoolName,String memType,String gender){
        super(mem_no,name,memType,gender);
        this.schoolName = schoolName;
    }


    public String getSchool(){
        return schoolName;
    }

    public void setSchool(String schoolName){
        this.schoolName = schoolName;
    }

}
