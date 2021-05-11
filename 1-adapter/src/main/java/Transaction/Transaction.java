package Transaction;

public class Transaction {

    public final double value;
    public final String spendingType;
    public final String date;


    public Transaction(double value, String spendingType, String date) {
        this.value = value;
        this.spendingType = spendingType;
        this.date = date;
    }


}
