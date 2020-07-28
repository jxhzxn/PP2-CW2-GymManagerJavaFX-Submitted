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
        docBuilder.append("mem_type", user.getMemType());
        docBuilder.append("gender", user.getGender());
        return docBuilder.get();
    }

    public static DBObject studentMember(StudentMember user){
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getID());
        docBuilder.append("name", user.getName());
        docBuilder.append("gender", user.getGender());
        docBuilder.append("school",user.getSchool());
        docBuilder.append("mem_type", user.getMemType());
        return docBuilder.get();
    }

    public static DBObject over60Member(Over60Member user){
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getID());
        docBuilder.append("name", user.getName());
        docBuilder.append("gender", user.getGender());
        docBuilder.append("age",user.getAge());
        docBuilder.append("mem_type", user.getMemType());
        return docBuilder.get();
    }



    public static DBCollection DefaultMemberCreate(int id, String name,String gender){

        DefaultMember defaultMember = new DefaultMember(id,name,"Default Member",gender);

        DBObject doc = sample.Database.defaultMember(defaultMember);
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        col.insert(doc);
        return col;
    }

    public static DBCollection StudentMemberCreate(int id, String name, String schoolName,String gender){

        StudentMember studentMember = new StudentMember(id,name,schoolName,"Student Member",gender);

        DBObject doc = sample.Database.studentMember(studentMember);
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        col.insert(doc);
        return col;
    }

    public static DBCollection Over60MemberCreate(int id, String name, int age,String gender){

        Over60Member over60Member = new Over60Member(id,name,age,"Over 60 Member",gender);

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
        DBCursor cursor = col.find();
        DBCursor cursor1 = col.find();

        String memType = cursor.next().get("mem_type").toString();
        String memName = cursor1.next().get("name").toString();

        System.out.println(memName+" is deleted who is a "+memType);
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
        DBCursor cursor1 = col.find();
        while (cursor.hasNext()){
            System.out.println(cursor.next().get("name").toString()+"\t-\t"+cursor1.next().get("mem_type").toString());
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

    public static ArrayList<String> readName(){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find();
        ArrayList<String> nameArray = new ArrayList<>();
        while (cursor.hasNext()){
            nameArray.add(cursor.next().get("name").toString());
        }
        return nameArray;
    }

    public static ArrayList<String> readMemType(){
        DB db = sample.Database.Dbconfig();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find();
        ArrayList<String> memTypeArray = new ArrayList<>();
        while (cursor.hasNext()){
            memTypeArray.add(cursor.next().get("mem_type").toString());
        }
        return memTypeArray;
    }




}
