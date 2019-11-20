package actions;

import main.Output;

public class HelpAction extends AbstractAction {
    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        sb.append("***************************\n")
        .append("* add - Add new transaction step by step over terminal quastion *\n")
                .append("* a - Add new transaction by printing all arguments in one line *\n")
                .append("* in predefine positions: *\n")
                .append("* - CurrencyType currency from *\n")
                .append("* - double amount from *\n")
                .append("* - CurrencyType currency to*\n")
                .append("* - boolean  use rate or amount to *\n")
                .append("* - double value of rate or amount *\n")
                .append("* - WalletType type of wallet*\n");
        Output.showMessage(sb.toString());
    }
}
