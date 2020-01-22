package actions;

import main.*;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class RemoveAction extends AbstractAction {

    private double amountDifferent;

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
            Transaction transaction = listWithCurrencyTo.get(transactionID);
            Output.showMessage(transaction.toString());
            double newAmount = UserInput.getAmountTo();
            double wasAmount = transaction.getAmountTo();
            double amountDifferent = Utils.roundMe(wasAmount - newAmount);//Math.abs(newAmount - wasAmount);
            if (amountDifferent > 0.1) {
                store.removeTransaction(transaction);
                transaction.setAmountTo(amountDifferent);
                store.addTransaction(transaction);
                Output.showMessage(transaction.toString());
            } else if (amountDifferent <= 0.1 && amountDifferent >= 0) {
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
