package actions;

import main.CStore;
import main.Transaction;
import main.TransactionStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MainTest {

    @Test
    public void testTest() {
        TransactionStore store = CStore.getInstance();
        List<Transaction> list = store.getTransactions();
        Assert.assertFalse(list.isEmpty());
    }
}
