package main;

import actions.*;

import java.nio.file.Paths;

public class CurrencyManager {

    public static void main(String[] args) {

        WorkType workType = WorkType.EMPTY;
        while (!workType.equals(WorkType.EXIT)) {
            workType = UserInput.getWorkType();
            IRunnable action = ActionFactory.getInstance(workType);
            action.run();
        }
    }
}
