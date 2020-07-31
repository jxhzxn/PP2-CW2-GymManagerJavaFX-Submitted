package sample;

import com.mongodb.*;
import sun.plugin.util.ProgressMonitor;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Database {

    //method to Configure and connect to the local MongoDB
    public static DB Dbconfig(){
        try {
            MongoClient mongo = new MongoClient( "localhost" , 27017 );
            DB db = mongo.getDB("local");
            return db;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    //method to create the Objects for the Default Member in MongoDB
    public static DBObject defaultMember(DefaultMember user){
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getID());
        docBuilder.append("name", user.getName());
        docBuilder.append("gender", user.getGender());
        docBuilder.append("city", user.getCity());
        docBuilder.append("mem_type", user.getMemType());
        return docBuilder.get();
    }

    //method to create the Objects for the Student Member in MongoDB
    public static DBObject studentMember(StudentMember user){
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getID());
        docBuilder.append("name", user.getName());
        docBuilder.append("gender", user.getGender());
        docBuilder.append("city", user.getCity());
        docBuilder.append("school",user.getSchool());
        docBuilder.append("mem_type", user.getMemType());
        return docBuilder.get();
    }

    //method to create the Objects for the Over60 Member in MongoDB
    public static DBObject over60Member(Over60Member user){
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getID());
        docBuilder.append("name", user.getName());
        docBuilder.append("gender", user.getGender());
        docBuilder.append("city", user.getCity());
        docBuilder.append("age",user.getAge());
        docBuilder.append("mem_type", user.getMemType());
        return docBuilder.get();
    }

    //method to insert the DefaultMember data into MongoDB
    public static DBCollection DefaultMemberCreate(int id, String name,String gender,String city){

        DefaultMember defaultMember = new DefaultMember(id,name,"DEFAULT",gender,city);

        DBObject doc = sample.Database.defaultMember(defaultMember);
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        col.insert(doc);
        return col;
    }

    //method to insert the StudentMember data into MongoDB
    public static DBCollection StudentMemberCreate(int id, String name, String schoolName,String gender,String city){

        StudentMember studentMember = new StudentMember(id,name,schoolName,"STUDENT",gender,city);

        DBObject doc = sample.Database.studentMember(studentMember);
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        col.insert(doc);
        return col;
    }

    //method to insert the Over60Member data into MongoDB
    public static DBCollection Over60MemberCreate(int id, String name, int age,String gender,String city){

        Over60Member over60Member = new Over60Member(id,name,age,"OVER 60",gender,city);

        DBObject doc = sample.Database.over60Member(over60Member);
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        col.insert(doc);
        return col;
    }

    //method to get the total number of registrations from MongoDB
    public static int getCount(){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        int count = (int) col.count();
        return count;
    }

    //method to delete a member from MongoDB
    public static void deleteUser(String name){
        try{
            DB db = sample.Database.Dbconfig();
            DBCollection col = db.getCollection("users");
            DBObject query = BasicDBObjectBuilder.start().add("name", name.toUpperCase()).get();
            DBCursor cursor = col.find(query);
            DBCursor cursor1 = col.find(query);

            String memType = cursor.next().get("mem_type").toString();
            String memName = cursor1.next().get("name").toString();

            int count = 100-Database.getCount();

            System.out.println(" ");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Succesfully Deleted '"+memName+"' who is a "+memType+" Member");
            System.out.println("-------------------------------------------------------------");
            System.out.println(" ");
            col.remove(query);
            System.out.println("Number of Registrations Available : " + (100 - Database.getCount()));

            System.out.println("");

        }catch (NoSuchElementException e){
            System.out.println("");
            System.out.println("-------------------");
            System.out.println("'"+name+"' is not a registered Member");
            System.out.println("-------------------");
            System.out.println("");
        }

    }

    //method to get the Last Registered Member's ID
    public static String getLastID(){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find();
        ArrayList<String> idArray = new ArrayList<>();
        while (cursor.hasNext()){
            idArray.add(cursor.next().get("_id").toString());
        }
        if(sample.Database.getCount()==0){
            return "0";
        }else{
            return idArray.get(idArray.size()-1);
        }
    }

    //method to print all the Registered Members on the console
    public static void printAll(){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find();
        DBCursor cursor1 = col.find();
        DBCursor cursor2 = col.find();
        DBCursor cursor3 = col.find();
        int count = 1;

        while (cursor.hasNext()){
            System.out.println("["+count+"]  \t\n"+" Full Name : "+cursor.next().get("name").toString()+" \n "+"Gender : "+cursor1.next().get("gender").toString()+" \n "+"City : "+cursor2.next().get("city").toString()+" \n "+"Member Type : "+cursor3.next().get("mem_type").toString());
            System.out.println("");
            count++;
        }
    }

    //method to do the sorting with the name in ascending or descending order
    public static void sorting(String object, int number){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find().sort(new BasicDBObject(object,number));
        while (cursor.hasNext()){
            System.out.println(cursor.next().get("name").toString());
        }
    }

    //method to read the value from the Database with the user's Input
    public static ArrayList<String> readCustom(String key){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find();
        ArrayList<String> nameArray = new ArrayList<>();
        while (cursor.hasNext()){
            nameArray.add(cursor.next().get(key).toString());
        }
        return nameArray;
    }




    //last two methods to get the name from the user and search all the other details from MongoDB, this is used in the GUI Searching section
    public static DBObject readNameSearch(String name){
        DB db = Database.Dbconfig();
        DBCollection col = db.getCollection("users");
//        DBObject dbObject = BasicDBObjectBuilder.start().add("name",name).get();
        DBObject dbObject = new BasicDBObject("name", name).append("name", new BasicDBObject("$regex", String.valueOf(name)));
        return dbObject;
    }


    public static ArrayList<String> nameSearch(DBObject object, String key){
        DB db = Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find(object);
        ArrayList<String> foundNames = new ArrayList<>();
        while(cursor.hasNext()){
            String searched = cursor.next().get(key).toString();
            foundNames.add(searched);
        }
        return foundNames;
    }
}
