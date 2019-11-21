package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionStore implements Serializable {

    private TransactionStore() {
    }

    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction newItem) {
        transactions.add(newItem);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void removeTransaction(Transaction item) {
        transactions.remove(item);
    }

    public void removeTransaction(int id) {
        transactions.remove(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionStore that = (TransactionStore) o;
        return Objects.equals(getTransactions(), that.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactions());
    }
}
