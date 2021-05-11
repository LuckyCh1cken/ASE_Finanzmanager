package aggregates;

import entities.Entitie_Wallet;
import valueobjects.VO_AccountName;
import valueobjects.VO_Password;

import java.util.Objects;
import java.util.UUID;

public class Aggregate_Account {

    private final String uuid;
    private final VO_AccountName accountName;
    private final VO_Password password;
    private final Entitie_Wallet wallet;

    public VO_AccountName getAccountName() {
        return accountName;
    }

    public VO_Password getPassword() {
        return password;
    }

    public Entitie_Wallet getWallet() {
        return wallet;
    }

    public Aggregate_Account(VO_AccountName accountName, VO_Password password, Entitie_Wallet wallet) {
        this.password = password;
        this.uuid = UUID.randomUUID().toString();
        this.accountName = accountName;
        this.wallet = wallet;
    }

    public String getId() { return this.uuid; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Aggregate_Account aggregateAccount) {
            return this.getId().equals(aggregateAccount.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uuid);
    }
}
