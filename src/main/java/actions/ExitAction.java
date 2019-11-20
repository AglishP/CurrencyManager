package actions;

import main.Message;
import main.Output;

public class ExitAction extends AbstractAction {

    @Override
    public void run() {
        Output.showMessage(Message.EXIT);
    }
}
