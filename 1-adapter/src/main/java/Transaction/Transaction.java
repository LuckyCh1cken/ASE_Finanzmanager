package Transaction;

import org.json.simple.JSONObject;

public class Transaction {

    public final double value;
    public final String spendingType;
    public final String date;


    public Transaction(double value, String spendingType, String date) {
        this.value = value;
        this.spendingType = spendingType;
        this.date = date;
    }

    public JSONObject toJson() {
        JSONObject entry = new JSONObject();

        entry.put("Value", value);
        entry.put("SpendingType", spendingType);
        entry.put("Date", date);

        return entry;
    }


}
