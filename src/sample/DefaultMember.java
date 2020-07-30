package sample;

//DefaultMember class, which is the superclass of the both the StudentMember and Over60Member classes
public class DefaultMember {
    private int mem_no;
    private String name;
    private String memType;
    private String gender;
    private String city;

    //Constructor of the DefaultMember class
    public DefaultMember(int mem_no, String name,String memType,String gender,String city){
        super();                    //Defining the super Constructor
        this.mem_no = mem_no;
        this.name = name;
        this.memType = memType;
        this.gender = gender;
        this.city = city;
    }
    
    //setter to set the ID
    public void setID(int id){
        this.mem_no = id;
    }

    //setter to set the Name
    public void setName(String name){
        this.name = name;
    }

    //getter to get the ID
    public int getID(){
        return mem_no;
    }

    //getter to get the Name
    public String getName(){
        return name;
    }

    //getter to get the MemberType
    public String getMemType(){
        return memType;
    }

    //getter to get the Gender
    public String getGender(){
        return gender;
    }

    //getter to get the city
    public String getCity(){
        return city;
    }
}
