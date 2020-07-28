package sample;

public class DefaultMember {
    private int mem_no;
    private String name;
    private String memType;
    private String gender;
    private int counter;

    public DefaultMember(int mem_no, String name,String memType,String gender){
        super();
        this.mem_no = mem_no;
        this.name = name;
        this.memType = memType;
        this.gender = gender;
    }


    public void setID(int id){
        this.mem_no = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getID(){
        return mem_no;
    }

    public String getName(){
        return name;
    }

    public String getMemType(){
        return memType;
    }

    public String getGender(){
        return gender;
    }





}
