package ma.enset.cqrsaxon.commonapi.events;

import lombok.Getter;

import java.util.Date;

public abstract class BaseEvent<T> {
    @Getter private T id;
    @Getter private Date eventDate;
    public BaseEvent(T id, Date date) {
        this.id = id;
        this.eventDate = date;
    }
}
