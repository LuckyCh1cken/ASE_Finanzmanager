package services;

import aggregates.Aggregate_Account;
import domainservices.Domain_Service_Account;
import entities.Entitie_Wallet;
import repositories.Repository_Account;
import valueobjects.VO_AccountName;
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

    @Override
    public boolean register(String accountName, String password) {
        if(currentAccount != null){
            throw new RuntimeException("User is already logged in");
        }

        List<Aggregate_Account> accounts = accountRepository.getAllAccounts();

        VO_Password hashedPassword = new VO_Password(password, false);

        for(Aggregate_Account account: accounts){
            if(account.getAccountName().getName().equals(accountName)){
                return false;
            }
        }

        VO_AccountName vo_accountName = new VO_AccountName(accountName);
        Entitie_Wallet wallet =  new Entitie_Wallet();

        Aggregate_Account newAccount = new Aggregate_Account(vo_accountName, hashedPassword, wallet);

        currentAccount = newAccount;
        accountRepository.addAccount(newAccount);
        accountRepository.saveChanges();

        return true;
    }
}
