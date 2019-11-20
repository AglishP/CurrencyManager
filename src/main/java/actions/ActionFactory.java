package actions;


import main.WorkType;

public class  ActionFactory {

    private WorkType workType;
    private static IRunnable instance;

    public ActionFactory(WorkType item) {
        workType = item;
        instance = buildInstance(workType);
    }

    public static IRunnable getInstance(WorkType work) {

        return buildInstance(work);
    }

    private static IRunnable buildInstance(WorkType work) {
        IRunnable action;
        switch (work) {
            case ADD:
                action = new AddNewTransactionAction();
                break;
            case ADD_ONE_LINE:
                action = new AddNewTransactionShortAction();
                break;
            case REMOVE:
                action = new RemoveAction();
                break;
            case SAVE:
                action = new SaveAction();
                break;
            case VIEW:
                action = new ViewAction();
                break;
            case SUMMARY:
                action = new SummaryAction();
                break;
            case HELP:
                action = new HelpAction();
                break;
            case EXIT:
                action = new ExitAction();
                break;
            case EMPTY:
                action = new EmptyAction();
                break;
            default:
                action = null;
        }
        action.setArgs(work.getArgs());
        return action;
    }

}
