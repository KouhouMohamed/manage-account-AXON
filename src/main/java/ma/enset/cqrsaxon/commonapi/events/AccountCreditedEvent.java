package ma.enset.cqrsaxon.commonapi.events;

import lombok.Getter;
import ma.enset.cqrsaxon.commonapi.enums.AccountStatus;

public class AccountCreditedEvent extends BaseEvent<String>{
    @Getter private  double balance;
    @Getter private  String currency;
    @Getter private  AccountStatus status;
    public AccountCreditedEvent(String id, double balance, String currency, AccountStatus status) {
        super(id);
        this.balance = balance;
        this.currency = currency;
        this.status = status;
    }
}