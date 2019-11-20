package actions;

import main.*;

import java.util.List;

public class ViewAction extends AbstractAction {
    @Override
    public void run() {
        List<Transaction> list = CStore.getInstance().getTransactions();
        if (list.isEmpty()) {
            Output.showMessage(Message.EMPTY_TRANSACTION_LIST);
            return;
        }
        Output.printList(list);
    }
}
