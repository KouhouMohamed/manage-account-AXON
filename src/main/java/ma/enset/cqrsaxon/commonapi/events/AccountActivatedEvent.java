package ma.enset.cqrsaxon.commonapi.events;

import lombok.Getter;
import ma.enset.cqrsaxon.commonapi.enums.AccountStatus;

import java.util.Date;

public class AccountActivatedEvent extends BaseEvent<String>{
    @Getter private  AccountStatus status;
    public AccountActivatedEvent(String id, AccountStatus status, Date eventDate) {
        super(id, eventDate);
        this.status = status;
    }
}
