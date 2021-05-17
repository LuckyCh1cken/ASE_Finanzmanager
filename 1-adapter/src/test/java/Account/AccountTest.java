package Account;

import Transaction.Transaction;
import junit.framework.TestCase;
import org.junit.Assert;
import valueobjects.VO_Password;

import java.util.ArrayList;

public class AccountTest extends TestCase {

    VO_Password password = new VO_Password("efef", false);
    public void testToJson() {
        Account account = new Account("efef", password.getHashedPassword() , new ArrayList<Transaction>());
        Assert.assertEquals(account.toJson().toString(), "{\"name\":\"efef\",\"transactions\":[],\"hpw\":\"7b2617e8f2aec38b2f5f4de7c4d24078f3aa2f141ab0db4436b6173700ecbebd\"}");
    }
}