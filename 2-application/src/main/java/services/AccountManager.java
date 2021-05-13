package services;

import aggregates.Aggregate_Account;
import domainservices.Domain_Service_Account;
import repositories.Repository_Account;
import valueobjects.VO_Password;
import valueobjects.VO_Transaction;

import java.util.List;

public class AccountManager implements Domain_Service_Account {
    private Aggregate_Account currentAccount = null;
    private final Repository_Account accountRepository;

    public AccountManager(Repository_Account accountRepository) {
        this.accountRepository = accountRepository;
        accountRepository.loadAllAccounts();
    }

    @Override
    public boolean login(String accountName, String password) {

        if(currentAccount != null){
            throw new RuntimeException("User is already logged in");
        }

        List<Aggregate_Account> accounts = accountRepository.getAllAccounts();

        VO_Password hashedPassword = new VO_Password(password, false);


        accounts.forEach(account -> {
            if(account.getAccountName().getName().equals(accountName) && account.getPassword().getHashedPassword().equals(hashedPassword.getHashedPassword())){
                currentAccount = account;
            }
        });
        if(currentAccount == null) return false;
        return true;
    }

    @Override
    public void logout() {
        if(currentAccount == null){
            throw new RuntimeException("You are not logged in");
        }
        else{
            currentAccount = null;
        }
    }

    @Override
    public void addTransaction(VO_Transaction transaction) {

        if(currentAccount == null){
            throw new RuntimeException("You are not logged in, login to add a transaction");
        }
        currentAccount.getWallet().addTransaction(transaction);

        accountRepository.saveChanges();
    }
}
