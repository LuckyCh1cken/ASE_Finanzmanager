package repositories;


import aggregates.Aggregate_Account;
import entities.Entitie_Wallet;
import valueobjects.VO_Transaction;

import java.util.List;

public interface Repository_Account {
        void addAccount(Aggregate_Account newAccount);
        Aggregate_Account getAccountById(String id);
        boolean loadallaccounts();

}
