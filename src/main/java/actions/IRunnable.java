package actions;

import java.util.ArrayList;
import java.util.List;

public interface IRunnable {

    void run();

    void setArgs(List<String> item);

    List<String> getArgs();
}
