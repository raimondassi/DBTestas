package mongoDBTestas;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import mongoDBTestas.client.MongoClientProvider;
import mongoDBTestas.domain.Transaction;
import mongoDBTestas.domain.User;
import org.bson.Document;

import java.util.Scanner;
public class Main {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main=new Main();
        try(MongoClient mongoClientWithCodec = MongoClientProvider.getMongoClientWithCodec()) {
            MongoDatabase newMongoDatabase = mongoClientWithCodec.getDatabase("moneyDB");
            MongoCollection<User> users = newMongoDatabase.getCollection("users", User.class);
            MongoCollection<Transaction> transactions = newMongoDatabase.getCollection("transactions", Transaction.class);

            System.out.println("Jei vesi useri spausk 1, jei transakcija spausk 2");
            Integer choice=main.scanner.nextInt();
            if(choice==1){
            main.insertUser(users);}
            if(choice==2){
                main.insertTransaction(transactions);
            }


            //manualy adding to DB
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

    public void insertUser(MongoCollection<User> users) {
        Main main = new Main();
        System.out.println("Isivesk save i klientus: vesk varda, savo saskaitosNr, ir kiek piniguIsidesi i saskaita");
        String name = scanner.next();
        Integer accountNr = scanner.nextInt();
        Double moneyStart = scanner.nextDouble();
        users.insertOne(new User(null, name, accountNr, moneyStart));
    }

    public void insertTransaction(MongoCollection<Transaction> transaction) {
        Main main = new Main();
        System.out.println("Isivesk transakcija: savo saskaitos nr, siuntejo saskaitos nr, kiek siusi pinigu ");
        Integer senderAccountId = scanner.nextInt();
        Integer receipientAccountId = scanner.nextInt();
        Double moneySent = scanner.nextDouble();
        transaction.insertOne(new Transaction(null, senderAccountId, receipientAccountId, moneySent));
    }
}
