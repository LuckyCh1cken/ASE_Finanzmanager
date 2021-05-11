package entities;

import repositories.Repository_Account;
import valueobjects.VO_Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entitie_Wallet {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public List<VO_Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    private final List<VO_Transaction> transactionHistory;

    public Entitie_Wallet(){
         this.balance = 0;
        transactionHistory = new ArrayList<>();
    }

    public void addTransaction(VO_Transaction newTransaction){
        transactionHistory.add(newTransaction);
        balance += newTransaction.getValue();
    }

}
