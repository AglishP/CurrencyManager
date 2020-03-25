package main;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Paths;

public class Utils {

    private static final String STORE_FILE_NAME = "currency.store";
    private static final String CSV_FILE_NAME = "csv.store";

    public static double roundMe(double value) {
        return Math.floor(value*100)/100d;
    }

    public static CurrencyType convertToCurrency(String str) {
        str = str.toLowerCase();
        if (str.equals(CurrencyType.RUB.getName())) {
            return CurrencyType.RUB;
        } else if (str.equals(CurrencyType.USD.getName())) {
            return CurrencyType.USD;
        } else if (str.equals(CurrencyType.EUR.getName())) {
            return CurrencyType.EUR;
        } else {
            return CurrencyType.NONE;
        }
    }

    public static boolean convertToBoolean(String str) {
        str = str.toLowerCase();
        if (str.equals("y") || str.equals("yes")) {
            return true;
        } else if (str.equals("n") || str.equals("no")) {
            return false;
        }

        try {
            boolean res = Boolean.parseBoolean(str);
            return res;
        } catch (Exception e) {
            throw new RuntimeException("can't parse to boolean this:" + str);
        }
    }

    public static AddTransactionWayType convertToTransactionWay(String str) {
        if (str.equals("from")) {
            return AddTransactionWayType.FROM;
        } else if (str.equals("to")) {
            return AddTransactionWayType.TO;
        }
        return AddTransactionWayType.NONE;
    }

    public static WalletType convertToWallet(String str) {
        if (str.equals(WalletType.CASH.getName())) {
            return WalletType.CASH;
        } else if (str.equals(WalletType.DEBIT_CARD.getName())) {
            return WalletType.DEBIT_CARD;
        }
        return null;
    }

    public static void save(TransactionStore store) {

        File storeFile = new File(STORE_FILE_NAME);
        if (!storeFile.exists()) {
            if (storeFile.isDirectory()) {
                try {
                    storeFile.delete();
                    storeFile.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(storeFile);
              ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(store);
            oos.close();
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCSV(TransactionStore store){
        File storeFile = new File(CSV_FILE_NAME);
        if (!storeFile.exists()) {
            if (storeFile.isDirectory()) {
                try {
                    storeFile.delete();
                    storeFile.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try (FileWriter fw = new FileWriter(storeFile); PrintWriter pw = new PrintWriter(fw)) {
            Gson gson = new Gson();
            store.getTransactions().stream()
                    .map(t -> gson.toJson(t, Transaction.class))
                    .forEach(s -> pw.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TransactionStore loadTransactionStore() {
        TransactionStore ds = null;

        File storeFile = Paths.get(STORE_FILE_NAME).toAbsolutePath().toFile();
        if (!storeFile.exists()) {
            try {
                storeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ds;
        }
        if (storeFile.isDirectory()) {
            if (storeFile.isDirectory()) {
                storeFile.delete();
            }
            return ds;
        }
        if (storeFile.length() == 0) {
            return ds;
        }
        try (FileInputStream fis = new FileInputStream(storeFile); ObjectInputStream oin = new ObjectInputStream(fis)) {
            ds = (TransactionStore) oin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return ds;
    }

    public static TransactionStore loadStoreFromCSV() {
        TransactionStore ds = new TransactionStore();

        File storeFile = Paths.get(CSV_FILE_NAME).toAbsolutePath().toFile();
        if (!storeFile.exists()) {
            Output.showMessage(Message.FILE_DOESNT_EXISTS);
            return ds;
        }
        if (storeFile.isDirectory()) {
            Output.showMessage(Message.FILE_IS_FOLDER);
           return ds;
        }
        if (storeFile.length() == 0) {
            Output.showMessage(Message.FILE_EMPTY);
            return ds;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            Gson gson = new Gson();
            String line;
            while ((line = br.readLine()) != null) {
                Transaction t = gson.fromJson(line, Transaction.class);
                ds.addTransaction(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
