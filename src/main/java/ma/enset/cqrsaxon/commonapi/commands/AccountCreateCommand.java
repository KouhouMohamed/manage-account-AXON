package ma.enset.cqrsaxon.commonapi.commands;

import lombok.Getter;

public class AccountCreateCommand extends BaseCommand<String>{
    @Getter private String currency;
    @Getter private double initialBalance;

    public AccountCreateCommand(String id, String currency, double initialBalance) {
        super(id);
        this.currency = currency;
        this.initialBalance = initialBalance;
    }
}
