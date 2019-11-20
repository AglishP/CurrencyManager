package main;

public enum WalletType {

    CASH("cash"),
    DEBIT_CARD("debit");

    private String name;

    WalletType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
