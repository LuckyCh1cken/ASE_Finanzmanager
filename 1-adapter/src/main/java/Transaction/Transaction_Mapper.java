package Transaction;

import valueobjects.VO_Transaction;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

public class Transaction_Mapper implements Function<VO_Transaction, Transaction> {

    private Transaction map(VO_Transaction transaction) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        return new Transaction(transaction.getValue(), transaction.getSpendingType().getSpendingType(), df.format(transaction.getDate()));
    }

    @Override
    public Transaction apply(VO_Transaction transaction) {
        return map(transaction);
    }

}
