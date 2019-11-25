package main;

import java.util.*;

public class UserInput {

    private static String askQuastion(String quastion) {
        Scanner inputStream = new Scanner(System.in);
        System.out.println(quastion);
        return inputStream.nextLine();
//        if (inputCommand.length > 0) {
//            return inputCommand[0];
//        } else {
//            return "";
//        }
    }

    public static WalletType getWalletType() {
        WalletType wallet = null;
        while (wallet == null) {
            String ans = askQuastion(Quastion.WALLET_TYPE);
            wallet = Utils.convertToWallet(ans);
        }
        return wallet;
    }

    public static CurrencyType getCurrencyFrom() {
        CurrencyType currency = CurrencyType.NONE;
        while (currency.equals(CurrencyType.NONE)) {
            String ans = askQuastion(Quastion.CURRENCY_FROM);
            currency = Utils.convertToCurrency(ans);
        }
        return currency;
    }

    public static CurrencyType getCurrencyTo() {
        CurrencyType currency = CurrencyType.NONE;
        while (currency.equals(CurrencyType.NONE)) {
            String ans = askQuastion(Quastion.CURRENCY_TO);
            currency = Utils.convertToCurrency(ans);
        }
        return currency;
    }

    public static double getAmountFrom() {
        return Double.parseDouble(askQuastion(Quastion.AMOUNT_FROM));
    }

    public static double getAmountTo() {
        return Double.parseDouble(askQuastion(Quastion.AMOUNT_TO));
    }

    public static double getRate() {
        return Double.parseDouble(askQuastion(Quastion.RATE));
    }

    public static boolean useRate() {
        String ans = askQuastion(Quastion.USE_RATE);
        if (ans.toLowerCase().startsWith("y")) {
            return true;
        }
        return false;
    }

    public static WorkType getWorkType() {
        String answer = askQuastion(Quastion.WORK_TYPE);
        if (answer.equals("")) {
            return WorkType.EMPTY;
        }

        String type;
        List<String> args = new ArrayList<>();
        if (answer.contains(" ")) {
            String[] arr = answer.split(" ");
            type = arr[0];
            args = Arrays.asList(arr).subList(1, arr.length);
        } else {
            type = answer;
        }

        WorkType wType = Arrays.asList(WorkType.values()).stream()
                .filter(t -> t.getName().equals(type))
                .findFirst().orElse(WorkType.EMPTY);
        wType.setArgs(args);

        return wType;
    }

    public static OptionalInt selectRates(List<Transaction> list) {
        System.out.println("Rates:");
        list.forEach(t -> System.out.println(t.getRate()));
        int id = 0;
        while (id <= 0) {
            try {
                id = Integer.parseInt(askQuastion(Quastion.INPUT_NUMBER));
                if (id > 0 || id < list.size()) {
                    return OptionalInt.of(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return OptionalInt.empty();
    }
}
