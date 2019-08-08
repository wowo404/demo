package org.liu.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoTest {

    public static MongoDatabase connect(){
        List<ServerAddress> addresses = new ArrayList<>();
        ServerAddress address = new ServerAddress("127.0.0.1", 27017);
        addresses.add(address);

        List<MongoCredential> credentials = new ArrayList<>();
        MongoCredential credential = MongoCredential.createScramSha1Credential("root", "admin", "root".toCharArray());
        credentials.add(credential);

        MongoClient client = new MongoClient(addresses, credentials);

        return client.getDatabase("liu");
    }

    public static void insertDocument(){
        MongoCollection<Document> collection = connect().getCollection("identityInfo");

        Document document = new Document("name", "JustingLiu").append("idCardNo", "360782198709020035").append("age", 30).append("birthday", new Date());

        collection.insertOne(document);
    }

    public static void updateDocument(){
        MongoCollection<Document> collection = connect().getCollection("identityInfo");
        collection.updateMany(Filters.eq("age", 30), new Document("$set", new Document("age", 40)));
    }

    public static void deleteDocument(){
        MongoCollection<Document> collection = connect().getCollection("identityInfo");
        Bson bson = Filters.eq("age", 30);
        //删除符合条件的第一个文档
        DeleteResult deleteResult = collection.deleteOne(bson);
        System.out.println("已删除document条数:" + deleteResult.getDeletedCount());
        //删除所有符合条件的文档
        // collection.deleteMany (Filters.eq("likes", 200));
    }

    public static void findDocument(){
        MongoCollection<Document> collection = connect().getCollection("identityInfo");
        FindIterable<Document> documents = collection.find();//检索所有文档
//        Bson bson = Filters.eq("age", 30);
//        FindIterable<Document> documents1 = collection.find(bson);//检索符合条件的文档
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        deleteDocument();
    }

}
