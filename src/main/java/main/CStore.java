package main;

public class CStore {

    private static TransactionStore instance;

    private CStore() {};

    public static TransactionStore getInstance() {
        if (instance == null) {
//            instance = Utils.loadTransactionStore();
            instance = Utils.loadStoreFromCSV();
            if (instance == null) {
                instance = new TransactionStore();
            }
        }
        return instance;
    }


}
