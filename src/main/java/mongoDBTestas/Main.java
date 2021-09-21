package mongoDBTestas;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import mongoDBTestas.client.MongoClientProvider;
import mongoDBTestas.domain.Transaction;
import mongoDBTestas.domain.User;
import org.bson.Document;
public class Main {

    public static void main(String[] args) {
        try(MongoClient mongoClientWithCodec = MongoClientProvider.getMongoClientWithCodec()) {
            MongoDatabase newMongoDatabase = mongoClientWithCodec.getDatabase("moneyDB");
            MongoCollection<User> users = newMongoDatabase.getCollection("users", User.class);
            MongoCollection<Transaction> transactions = newMongoDatabase.getCollection("transactions", Transaction.class);
            users.insertOne(new User(null, "Raimondas Siupienis", 123456789, 5000D));
            users.insertOne(new User(null, "Antanas Fontanas", 987654321, 10000D));
            transactions.insertOne(new Transaction(null, 123456789, 987654321, 2000D));
            transactions.insertOne(new Transaction(null, 123456789, 987654321, 2000D));
            MongoCursor<User> iterator = users.find().iterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

}
