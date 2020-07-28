package sample;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class Database {

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

    public static DBObject defaultMember(DefaultMember user){
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getID());
        docBuilder.append("name", user.getName());
        return docBuilder.get();
    }

    public static DBObject studentMember(StudentMember user){
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getID());
        docBuilder.append("name", user.getName());
        docBuilder.append("school",user.getSchool());
        return docBuilder.get();
    }

    public static DBObject over60Member(Over60Member user){
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getID());
        docBuilder.append("name", user.getName());
        docBuilder.append("age",user.getAge());
        return docBuilder.get();
    }



    public static DBCollection DefaultMemberCreate(int id, String name){

        DefaultMember defaultMember = new DefaultMember(id,name);

        DBObject doc = sample.Database.defaultMember(defaultMember);
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        col.insert(doc);
        return col;
    }

    public static DBCollection StudentMemberCreate(int id, String name, String schoolName){

        StudentMember studentMember = new StudentMember(id,name,schoolName);

        DBObject doc = sample.Database.studentMember(studentMember);
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        col.insert(doc);
        return col;
    }

    public static DBCollection Over60MemberCreate(int id, String name, int age){

        Over60Member over60Member = new Over60Member(id,name,age);

        DBObject doc = sample.Database.over60Member(over60Member);
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        col.insert(doc);
        return col;
    }

    public static int getCount(){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        int count = (int) col.count();
        return count;
    }


    //finding the member with the ID
    public static void findMember(int id){
        DB db = sample.Database.Dbconfig();
        DBCollection tableDetails = db.getCollection("users");
        BasicDBObject queryDetails = new BasicDBObject();
        queryDetails.put("_id",id);
        DBCursor cursor = tableDetails.find(queryDetails);
        DBObject oneDetails;
        oneDetails = cursor.next();
        String data = oneDetails.get("name").toString();
        System.out.println(data);
    }

    //deleting a user
    public static void deleteUser(String name){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBObject query = BasicDBObjectBuilder.start().add("name", name).get();
        col.remove(query);
    }

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

    public static void printAll(){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find();
        while (cursor.hasNext()){
            System.out.println(cursor.next().get("name").toString());
        }
    }

    public static void sorting(String object, int number){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find().sort(new BasicDBObject(object,number));
        while (cursor.hasNext()){
            System.out.println(cursor.next().get("name").toString());
        }
    }

    public static ArrayList<String> readAll(){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find();
        ArrayList<String> nameArray = new ArrayList<>();
        while (cursor.hasNext()){
            nameArray.add(cursor.next().get("name").toString());
        }
        return nameArray;
    }




}
