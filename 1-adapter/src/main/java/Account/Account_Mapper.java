package Account;

import Transaction.*;
import aggregates.Aggregate_Account;
import valueobjects.VO_Transaction;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Account_Mapper implements Function<Aggregate_Account, Account> {

    private Account map(Aggregate_Account account) {
        Transaction_Mapper transactionMapper = new Transaction_Mapper();
        List<Transaction> transactionList = account.getWallet().getTransactionHistory().stream().map(transactionMapper).collect(Collectors.toList());
        return new Account(account.getAccountName().getName(), account.getPassword().getHashedPassword(), transactionList);
    }

    @Override
    public Account apply(Aggregate_Account account) {
        return map(account);
    }

}
