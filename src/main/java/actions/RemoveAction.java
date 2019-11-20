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

        CurrencyType currencyTypeFrom = UserInput.getCurrencyFrom();

        boolean stillWork = true;
        while (stillWork) {
            List<Transaction> rates = getRates(list, currencyTypeFrom);

            OptionalInt selectedId = selectRate(rates);
            if (selectedId.isPresent()) {
                int transactionID = selectedId.getAsInt() - 1;
                Transaction transaction = store.getTransactions().get(transactionID);
                double amount = UserInput.getAmountFrom();
                double currentAmount = transaction.getAmountTo();
                if (amount < currentAmount) {
                    double newAmount = Utils.roundMe(currentAmount - amount);
                    transaction.setAmountTo(newAmount);
                    store.removeTransaction(transactionID);
                    store.addTransaction(transaction);
                    Output.showMessage(transaction.toString());
                    stillWork = false;
                } else if (amount == currentAmount) {
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
