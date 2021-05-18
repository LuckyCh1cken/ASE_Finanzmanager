package Mocks;

import aggregates.Aggregate_Account;
import repositories.Repository_Account;

import java.util.ArrayList;
import java.util.List;

public class Repository_Bridge_Account_Mock implements Repository_Account {

    List<Aggregate_Account> accounts = new ArrayList<>();

    @Override
    public void addAccount(Aggregate_Account newAccount) {
        accounts.add(newAccount);
    }

    @Override
    public Aggregate_Account getAccountById(String id) {
        return accounts.stream().filter(account -> account.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Aggregate_Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public boolean loadAllAccounts() {
        return true;
    }

    @Override
    public boolean saveChanges() {
        return true;
    }
}
