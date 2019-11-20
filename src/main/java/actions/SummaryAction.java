package actions;

import main.*;

import java.util.List;

public class SummaryAction extends AbstractAction {

    public void run() {
        TransactionStore store = CStore.getInstance();
        List<Transaction> list = store.getTransactions();

        // total rub
        Output.showMessage(String.format("Total rub %s", getTotalAmount(list, CurrencyType.RUB)));

    }

    public double getTotalAmount(List<Transaction> list, CurrencyType type) {
        return list.stream()
                .filter(t -> t.getCurrencyFrom().equals(type))
                .mapToDouble(t -> t.getAmountFrom())
                .sum();
    }
}
