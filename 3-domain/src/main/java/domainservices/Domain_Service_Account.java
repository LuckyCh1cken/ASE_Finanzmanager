package domainservices;

import valueobjects.VO_Transaction;

public interface Domain_Service_Account {

    boolean login(String accountName, String password);
    void logout();
    void addTransaction(VO_Transaction transaction);
    boolean register(String accountName, String password);
}
