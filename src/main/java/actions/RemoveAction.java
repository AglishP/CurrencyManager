package actions;

import main.*;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class RemoveAction extends AbstractAction {

    @Override
    public void run() {

        TransactionStore store = CStore.getInstance();
        List<Transaction> list = store.getTransactions();

        WalletType wallet = UserInput.getWalletType();
        list = TransactionSelectors.walletSelector(list, wallet);
        Output.printList(list);

        CurrencyType currencyTypeTo = UserInput.getCurrencyTo();

        boolean stillWork = true;
        while (stillWork) {
            List<Transaction> rates = getRates(list, currencyTypeTo);

            OptionalInt selectedId = selectRate(rates);
            if (selectedId.isPresent()
                    && selectedId.getAsInt() - 1 <= store.getTransactions().size()) {
                int transactionID = selectedId.getAsInt() - 1;
                Transaction transaction = store.getTransactions().get(transactionID);
                double amount = UserInput.getAmountTo();
                double currentAmount = transaction.getAmountTo();
                if (Math.abs(amount - currentAmount) > 0.1) {
                    double newAmount = Utils.roundMe(currentAmount - amount);
                    transaction.setAmountTo(newAmount);
                    store.removeTransaction(transactionID);
                    store.addTransaction(transaction);
                    Output.showMessage(transaction.toString());
                    stillWork = false;
                } else if (Math.abs(amount - currentAmount) <= 0.1) {
                    store.removeTransaction(transaction);
                    stillWork = false;
                } else {
                    Output.showMessage(Message.INPUT_BIGGER_THAN_AMOUNT);
                }
            }
        }
    }

    private OptionalInt selectRate(List<Transaction> list) {
        return UserInput.selectRates(list);
    }

    private List<Transaction> getRates(List<Transaction> list, CurrencyType currency) {
        return list.stream()
                .filter(t -> t.getCurrencyTo().equals(currency))
                .collect(Collectors.toList());
    }
}
