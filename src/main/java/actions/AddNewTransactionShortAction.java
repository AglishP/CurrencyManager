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
        if (args.size() == 1 && args.get(0).equals("h")) {
            Output.showMessage(Message.FIRST_SHORT_ADD_API);
            Output.showMessage(Message.SECOND_SHORT_ADD_API);
            return;
        }

        if (args.size() != 6) {
            Output.showMessage(Message.WRONG_ARGUMENTS_NUMBER);
            return;
        }

        if (Utils.convertToTransactionWay(args.get(0)) == AddTransactionWayType.FROM) {
            transaction.setCurrencyFrom(Utils.convertToCurrency(args.get(1)));
            transaction.setAmountFrom(Double.parseDouble(args.get(2)));
            transaction.setCurrencyTo(Utils.convertToCurrency(args.get(3)));
        } else if (Utils.convertToTransactionWay(args.get(0)) == AddTransactionWayType.TO) {
            transaction.setCurrencyFrom(Utils.convertToCurrency(args.get(1)));
            transaction.setCurrencyTo(Utils.convertToCurrency(args.get(2)));
            transaction.setAmountTo(Double.parseDouble(args.get(3)));
        }
        transaction.setRate(Double.parseDouble(args.get(4)));

        transaction.setWalletType(Utils.convertToWallet(args.get(5)));
        if (transaction.check()) {
            CStore.getInstance().addTransaction(transaction);
            Output.showMessage(transaction.toString());
        }
    }
}
