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
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class MongoTest {

    public static MongoDatabase connect() {
        //spring.data.mongodb.uri=mongodb://user_test:user_test@172.19.97.2:27017/manufacture_kzsn
//        String host = "172.19.97.2";
//        int port = 27017;
//        String db = "manufacture_kzsn";
//        String username = "user_test";
//        String password = "user_test";
        //开州水泥厂正式环境
        //spring.data.mongodb.uri=mongodb://user_kzsn:user_kzsn@172.16.17.4:27017/manufacture_kzsn
        String host = "172.16.17.4";
        int port = 27017;
        String db = "manufacture_kzsn";
        String username = "user_kzsn";
        String password = "user_kzsn";
        //开发环境
//        String host = "192.168.17.230";
//        int port = 27017;
//        String db = "logistics_test";
//        String username = "root";
//        String password = "root";
        List<ServerAddress> addresses = new ArrayList<>();
        ServerAddress address = new ServerAddress(host, port);
        addresses.add(address);

        List<MongoCredential> credentials = new ArrayList<>();
        MongoCredential credential = MongoCredential.createCredential(username, db, password.toCharArray());
        credentials.add(credential);

        MongoClient client = new MongoClient(addresses, credentials);
        return client.getDatabase(db);
    }

    public static void insertDocument() {
        MongoCollection<Document> collection = connect().getCollection("identityInfo");

        Document document = new Document("name", "JustingLiu").append("idCardNo", "360782198709020035").append("age", 30).append("birthday", new Date());

        collection.insertOne(document);
    }

    public static void updateDocument() {
        MongoCollection<Document> collection = connect().getCollection("identityInfo");
        collection.updateMany(Filters.eq("age", 30), new Document("$set", new Document("age", 40)));
    }

    public static void deleteDocument() {
        MongoCollection<Document> collection = connect().getCollection("identityInfo");
        Bson bson = Filters.eq("age", 30);
        //删除符合条件的第一个文档
        DeleteResult deleteResult = collection.deleteOne(bson);
        System.out.println("已删除document条数:" + deleteResult.getDeletedCount());
        //删除所有符合条件的文档
        // collection.deleteMany (Filters.eq("likes", 200));
    }

    public static void findDocument() {
        MongoCollection<Document> collection = connect().getCollection("test");
        FindIterable<Document> documents = collection.find();//检索所有文档
//        Bson bson = Filters.eq("age", 30);
//        FindIterable<Document> documents1 = collection.find(bson);//检索符合条件的文档
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static final int thread = 2000;
    private static final CyclicBarrier barrier = new CyclicBarrier(thread, () -> {
        log.info("先执行我。。。。。。。。。。");
    });

    public static void main(String[] args) {
        log.info("---------start--------");
        for (int i = 0; i < thread; i++) {
            new Thread(() -> {
                try {
                    barrier.await();
                    findDocument();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }).start();
        }
        log.info("---------------end-----------");
    }

}
