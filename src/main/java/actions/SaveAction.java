package actions;

import main.CStore;
import main.Utils;

public class SaveAction extends AbstractAction {

    @Override
    public void run() {
        Utils.save(CStore.getInstance());
        Utils.saveCSV(CStore.getInstance());
    }
}
