import aggregates.Aggregate_Account;
import entities.Entitie_Wallet;
import repositories.Repository_Account;
import services.AccountManager;
import valueobjects.VO_AccountName;
import valueobjects.VO_Password;
import valueobjects.VO_SpendingType;
import valueobjects.VO_Transaction;

import java.util.Date;

public class Finanzmanager {

    public static void main(String[] args) {

        Repository_Account accounts = new Repository_Bridge_Account();
        AccountManager accountManager = new AccountManager(accounts);
        Aggregate_Account testAccount = new Aggregate_Account(new VO_AccountName("Felix"), new VO_Password("Felix", false), new Entitie_Wallet());

        testAccount.getWallet().addTransaction(new VO_Transaction(1000, new VO_SpendingType("WOHNEN"), new Date()));
        testAccount.getWallet().addTransaction(new VO_Transaction(10200, new VO_SpendingType("WOHNEN"), new Date()));
        testAccount.getWallet().addTransaction(new VO_Transaction(1000, new VO_SpendingType("WOHNEN"), new Date()));
        testAccount.getWallet().addTransaction(new VO_Transaction(10020, new VO_SpendingType("WOHNEN"), new Date()));
        testAccount.getWallet().addTransaction(new VO_Transaction(10200, new VO_SpendingType("WOHNEN"), new Date()));


        accounts.addAccount(testAccount);

        System.out.println(accountManager.login("Felix", "Felix"));


    }
}
