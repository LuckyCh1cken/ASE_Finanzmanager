package Transaction;

import valueobjects.VO_Transaction;

import java.util.function.Function;

public class Transaction_Mapper implements Function<VO_Transaction, Transaction> {

    private Transaction map(VO_Transaction transaction) {
        return new Transaction(transaction.getValue(), transaction.getSpendingType().toString(), transaction.getDate().toString());
    }

    @Override
    public Transaction apply(VO_Transaction transaction) {
        return map(transaction);
    }

}
