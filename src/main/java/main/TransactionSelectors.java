package main;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionSelectors {

    public static List<Transaction> walletSelector(List<Transaction> list, WalletType wallet) {
        return list.stream()
                .filter(t -> t.getWalletType().equals(wallet))
                .collect(Collectors.toList());
    }
}
