package actions;

import main.*;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class RemoveAction extends AbstractAction {

    @Override
    public void run() {

        TransactionStore store = CStore.getInstance();
        List<Transaction> list = store.getTransactions();

        // select wallet type
        Arrays.stream(WalletType.values())
                .map(WalletType::getName)
                .forEach(Output::showMessage);
        WalletType wallet = UserInput.getWalletType();

        // select currency to
        list = TransactionSelectors.walletSelector(list, wallet);
        Output.printList(list);
        Output.showMessage(Message.STARS);
        list.stream()
                .map(Transaction::getCurrencyTo)
                .distinct()
                .map(CurrencyType::getName)
                .forEach(Output::showMessage);
        CurrencyType currencyTypeTo = UserInput.getCurrencyTo();

        List<Transaction> listWithCurrencyTo = filterByCurrency(list, currencyTypeTo);
        OptionalInt selectedRateId = selectRate(listWithCurrencyTo);
        if (selectedRateId.isPresent()
                && selectedRateId.getAsInt() - 1 <= store.getTransactions().size()) {
            int transactionID = selectedRateId.getAsInt() - 1;
            Transaction transaction = store.getTransactions().get(transactionID);
            Output.showMessage(transaction.toString());
            double amount = UserInput.getAmountTo();
            double currentAmount = transaction.getAmountTo();
            if (Math.abs(amount - currentAmount) > 0.1) {
                double newAmount = Utils.roundMe(currentAmount - amount);
                transaction.setAmountTo(newAmount);
                store.removeTransaction(transactionID);
                store.addTransaction(transaction);
                Output.showMessage(transaction.toString());
            } else if (Math.abs(amount - currentAmount) <= 0.1) {
                store.removeTransaction(transaction);
            } else {
                Output.showMessage(Message.INPUT_BIGGER_THAN_AMOUNT);
            }
        }
    }

    private OptionalInt selectRate(List<Transaction> list) {
        return UserInput.selectRates(list);
    }

    private List<Transaction> filterByCurrency(List<Transaction> list, CurrencyType currency) {
        return list.stream()
                .filter(t -> t.getCurrencyTo().equals(currency))
                .collect(Collectors.toList());
    }
}
