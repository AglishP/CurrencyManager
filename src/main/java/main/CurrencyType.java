package main;

public enum CurrencyType {
    RUB("rub"),
    USD("usd"),
    EUR("eur"),
    NONE("");

    private String currencyName;

    CurrencyType(String name) {
        currencyName = name;
    }

    public String getName() {
        return currencyName;
    }
}
