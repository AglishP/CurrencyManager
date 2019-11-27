package actions;

import main.*;

import java.util.List;

public class SummaryAction extends AbstractAction {

    public void run() {
        TransactionStore store = CStore.getInstance();
        List<Transaction> list = store.getTransactions();

        // total rub
        Output.showMessage(String.format("Total rub %s", getTotalAmountFrom(list)));
        Output.showMessage(String.format("Total eur %s", getTotalAmountTo(list, CurrencyType.EUR)));
        Output.showMessage(String.format("Total usd %s", getTotalAmountTo(list, CurrencyType.USD)));
        Output.showMessage(Message.STARS);
        Output.showMessage(String.format("Total eur %s in debit", getAmountToByWallet(list, CurrencyType.EUR, WalletType.DEBIT_CARD)));
        Output.showMessage(String.format("Total usd %s in debit", getAmountToByWallet(list, CurrencyType.USD, WalletType.DEBIT_CARD)));
        Output.showMessage(Message.STARS);
        Output.showMessage(String.format("Total eur %s in cash", getAmountToByWallet(list, CurrencyType.EUR, WalletType.CASH)));
        Output.showMessage(String.format("Total usd %s in cash", getAmountToByWallet(list, CurrencyType.USD, WalletType.CASH)));

    }

    private double getAmountToByWallet(List<Transaction> list, CurrencyType type, WalletType wallet) {
        return list.stream()
                .filter(t -> t.getWalletType().equals(wallet))
                .filter(t -> t.getCurrencyTo().equals(type))
                .mapToDouble(Transaction::getAmountFrom)
                .sum();
    }

    private double getTotalAmountTo(List<Transaction> list, CurrencyType type) {
        return list.stream()
                .filter(t -> t.getCurrencyTo().equals(type))
                .mapToDouble(Transaction::getAmountTo)
                .sum();
    }

    private double getTotalAmountFrom(List<Transaction> list) {
        return list.stream()
                .filter(t -> t.getCurrencyFrom().equals(CurrencyType.RUB))
                .mapToDouble(Transaction::getAmountFrom)
                .sum();
    }
}
