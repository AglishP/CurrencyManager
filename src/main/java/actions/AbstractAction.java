package actions;

import java.util.List;

public abstract class AbstractAction implements IRunnable {

    List<String> args;

    @Override
    public abstract void run();

    @Override
    public void setArgs(List<String> args) {
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }
}
