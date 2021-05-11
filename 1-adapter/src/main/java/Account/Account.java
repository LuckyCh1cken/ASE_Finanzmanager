package Account;

import Transaction.Transaction;
import org.json.simple.JSONObject;

import java.util.List;

public class Account {

    public final String name;
    public  final String hashedPassword;
    public final List<Transaction> transactions;

    public Account(String name, String hashedPassword, List<Transaction> transactions) {
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.transactions = transactions;
    }


    public JSONObject toJson() {
        JSONObject entry = new JSONObject();

        entry.put("name", name);
        entry.put("hpw", hashedPassword);
        entry.put("transactions", transactions);

        return entry;
    }
}
