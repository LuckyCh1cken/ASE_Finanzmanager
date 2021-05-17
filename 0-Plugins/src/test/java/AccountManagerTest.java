import Transaction.Transaction;
import aggregates.Aggregate_Account;
import entities.Entitie_Wallet;
import junit.framework.Assert;
import org.junit.Test;
import services.AccountManager;
import valueobjects.VO_AccountName;
import valueobjects.VO_Password;
import valueobjects.VO_SpendingType;
import valueobjects.VO_Transaction;

import java.util.Date;

public class AccountManagerTest {

    @Test
    public void testlogin() {
        Repository_Bridge_Account accounts = new Repository_Bridge_Account();
        accounts.loadAllAccounts();

        AccountManager accountManager = new AccountManager(accounts);

        //Wenn schon Registriert ist neu registrieren unmöglich, dann einloggen
        //Falls nicht registriert wird man nach registration automatisch eingeloggt
        if(!accountManager.register("Felix", "Felix")){
            accountManager.login("Felix", "Felix");
        }
        Assert.assertEquals("Felix", accountManager.getAccount().getAccountName().getName());
    }

    @Test
    public void testlogout() {
        Repository_Bridge_Account accounts = new Repository_Bridge_Account();
        accounts.loadAllAccounts();

        AccountManager accountManager = new AccountManager(accounts);
        if(!accountManager.register("Felix", "Felix")){
            accountManager.login("Felix", "Felix");
        }
        accountManager.logout();
        Assert.assertNull(accountManager.getAccount());
    }

    @Test
    public void testaddTransaction() {
        Repository_Bridge_Account accounts = new Repository_Bridge_Account();
        accounts.loadAllAccounts();

        AccountManager accountManager = new AccountManager(accounts);
        if(!accountManager.register("Felix", "Felix")){
            accountManager.login("Felix", "Felix");
        }

        VO_SpendingType spendingType = new VO_SpendingType("SONSTIGES");
        VO_Transaction transaction= new VO_Transaction(10, spendingType, new Date());
        accountManager.addTransaction(transaction);
        Assert.assertEquals(10.0, accountManager.getTransactions().get(0).getValue());
    }

}
