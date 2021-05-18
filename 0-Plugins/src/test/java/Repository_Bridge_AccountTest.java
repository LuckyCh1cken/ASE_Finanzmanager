import Mocks.Repository_Bridge_Account_Mock;
import aggregates.Aggregate_Account;
import entities.Entitie_Wallet;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import repositories.Repository_Account;
import valueobjects.VO_AccountName;
import valueobjects.VO_Password;

public class Repository_Bridge_AccountTest extends TestCase {

    @Test
    public void testAddAccount() {
       Repository_Account accounts = new Repository_Bridge_Account_Mock();
       accounts.loadAllAccounts();
        VO_AccountName name = new VO_AccountName("Felix");
        VO_Password password = new VO_Password("Felix", false);
        Entitie_Wallet wallet = new Entitie_Wallet();
        Aggregate_Account account = new Aggregate_Account(name, password, wallet);
        accounts.addAccount(account);
        Assert.assertEquals(account, accounts.getAllAccounts().get(0));
    }

    @Test
    public void testGetAllAccounts() {
        Repository_Account accounts = new Repository_Bridge_Account_Mock();
        accounts.loadAllAccounts();
        VO_AccountName name = new VO_AccountName("Felix");
        VO_Password password = new VO_Password("Felix", false);
        Entitie_Wallet wallet = new Entitie_Wallet();
        Aggregate_Account account = new Aggregate_Account(name, password, wallet);
        accounts.addAccount(account);
        VO_AccountName name1 = new VO_AccountName("Felix");
        VO_Password password1 = new VO_Password("Felix", false);
        Entitie_Wallet wallet1 = new Entitie_Wallet();
        Aggregate_Account account1 = new Aggregate_Account(name1, password1, wallet1);
        accounts.addAccount(account1);
        Assert.assertEquals(2, accounts.getAllAccounts().size());
    }
}