package ma.enset.cqrsaxon.commonapi.commands;

import lombok.Getter;

public class AccountDebitCommand extends BaseCommand<String>{
    @Getter private String currency;
    @Getter private double balance;

    public AccountDebitCommand(String id, String currency, double balance) {
        super(id);
        this.currency = currency;
        this.balance = balance;
    }
}
