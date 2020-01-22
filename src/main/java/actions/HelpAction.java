package actions;

import main.Output;

public class HelpAction extends AbstractAction {
    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        sb.append("***************************\n")
        .append("* add - Add new transaction step by step over terminal question *\n")
                .append("* a - Command for Add new transaction by printing all arguments in one line *\n")
                .append("* in predefine positions: *\n")
                .append("*   - type from or to*\n")
                .append("*   - CurrencyType currency from *\n")
                .append("*   - double amount from *\n")
                .append("*   - CurrencyType currency to*\n")
                .append("*   - boolean use rate or amount to *\n")
                .append("*   - double value of rate or amount *\n")
                .append("*   - WalletType type: cash or debit*\n")
                .append("*   example: a from rub 70000 eur yes 70 debit")
                .append("___________________________________________")
                .append("* minus - Command for remove transaction pypeline. Just minus")
                .append("* view - Command for view all transactions")
                .append("* summary - Command for smart report");
        Output.showMessage(sb.toString());
    }
}
