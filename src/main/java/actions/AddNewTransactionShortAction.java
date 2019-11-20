package actions;

import main.*;

import java.util.List;

public class AddNewTransactionShortAction extends AbstractAction {

    @Override
    public void run() {
        Transaction transaction = new Transaction();

        List<String> args = getArgs();
        if (args == null) {
            Output.showMessage(Message.ARGUMENTS_NULL);
            return;
        }
        if (args.size() != 6) {
            Output.showMessage(Message.WRONG_ARGUMENTS_NUMBER);
            return;
        }

        transaction.setCurrencyFrom(Utils.convertToCurrency(args.get(0)));
        transaction.setAmountFrom(Double.parseDouble(args.get(1)));
        transaction.setCurrencyTo(Utils.convertToCurrency(args.get(2)));

        if (Boolean.parseBoolean(args.get(3))) {
            transaction.setRate(Double.parseDouble(args.get(4)));
        } else {
            transaction.setAmountTo(Double.parseDouble(args.get(4)));
        }
        transaction.setWalletType(Utils.convertToWallet(args.get(5)));
        if (transaction.ckeck()) {
            CStore.getInstance().addTransaction(transaction);
            Output.showMessage(transaction.toString());
        }
    }
}
