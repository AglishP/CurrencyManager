package actions;

import main.CStore;
import main.Output;
import main.Transaction;
import main.UserInput;

public class AddNewTransactionAction extends AbstractAction {

  public void run() {
      Transaction transaction = new Transaction();
      transaction.setCurrencyFrom(UserInput.getCurrencyFrom());
      transaction.setCurrencyTo(UserInput.getCurrencyTo());
      transaction.setAmountFrom(UserInput.getAmountFrom());
      if (UserInput.useRate()) {
          transaction.setRate(UserInput.getRate());
      } else {
          transaction.setAmountTo(UserInput.getAmountTo());
      }
      transaction.setWalletType(UserInput.getWalletType());

      if (transaction.ckeck()) {
          CStore.getInstance().addTransaction(transaction);
          Output.showMessage(transaction.toString());
      }
  }
}
