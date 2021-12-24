package ma.enset.cqrsaxon.commonapi.events;

import lombok.Getter;
import ma.enset.cqrsaxon.commonapi.enums.AccountStatus;

import java.util.Date;

public class AccountCreatedEvent extends BaseEvent<String>{
    @Getter private  double initialBalance;
    @Getter private  String currency;
    @Getter private  AccountStatus status;
    public AccountCreatedEvent(String id, double initialBalance, String currency, AccountStatus status, Date eventDate) {
        super(id, eventDate);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.status = status;
    }
}
