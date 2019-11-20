package main;

import java.io.Serializable;
import java.util.Objects;

public class Transaction implements Serializable {

    private CurrencyType currencyFrom;
    private CurrencyType currencyTo;
    private WalletType walletType;
    private double amountFrom;
    private double amountTo;
    private double rate;

    public Transaction(){
    }

    public WalletType getWalletType() {
        return walletType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.getAmountFrom(), getAmountFrom()) == 0 &&
                Double.compare(that.getAmountTo(), getAmountTo()) == 0 &&
                Double.compare(that.getRate(), getRate()) == 0 &&
                getCurrencyFrom() == that.getCurrencyFrom() &&
                getCurrencyTo() == that.getCurrencyTo() &&
                getWalletType() == that.getWalletType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrencyFrom(), getCurrencyTo(), getWalletType(), getAmountFrom(), getAmountTo(), getRate());
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }

    public boolean ckeck() {
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("from ")
                .append(amountFrom)
                .append(" of ")
                .append(currencyFrom)
                .append(" -> ")
                .append(amountTo)
                .append(" of ")
                .append(currencyTo)
                .append(" by rate ")
                .append(rate)
                .append(" wallet: ")
                .append(walletType);
        return sb.toString();
    }

    public CurrencyType getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(CurrencyType currencyTo) {
        this.currencyTo = currencyTo;
    }

    public double getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public double getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(double value) {
        amountTo = value;
        if (rate < 0.1) {
            rate = Utils.roundMe(amountFrom/amountTo);
        } else {
            amountFrom = Utils.roundMe(amountTo * rate);
        }
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
        this.amountTo = Utils.roundMe(amountFrom/rate);
    }

    public CurrencyType getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(CurrencyType currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

}
