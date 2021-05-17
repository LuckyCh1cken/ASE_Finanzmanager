package domainservices;

import aggregates.Aggregate_Account;
import valueobjects.VO_Transaction;

import java.util.List;

public interface Domain_Service_Account {

    boolean login(String accountName, String password);
    void logout();
    void addTransaction(VO_Transaction transaction);
    boolean register(String accountName, String password);
    Aggregate_Account getAccount();
    List<VO_Transaction> getTransactions();
}
