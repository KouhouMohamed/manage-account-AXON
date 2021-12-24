package ma.enset.cqrsaxon.command.aggregate;

import lombok.extern.slf4j.Slf4j;
import ma.enset.cqrsaxon.commonapi.commands.AccountCreateCommand;
import ma.enset.cqrsaxon.commonapi.commands.AccountCreditCommand;
import ma.enset.cqrsaxon.commonapi.commands.AccountDebitCommand;
import ma.enset.cqrsaxon.commonapi.enums.AccountStatus;
import ma.enset.cqrsaxon.commonapi.events.AccountActivatedEvent;
import ma.enset.cqrsaxon.commonapi.events.AccountCreatedEvent;
import ma.enset.cqrsaxon.commonapi.events.AccountCreditedEvent;
import ma.enset.cqrsaxon.commonapi.events.AccountDebitedEvent;
import ma.enset.cqrsaxon.commonapi.exceptions.InsufficientAmountException;
import ma.enset.cqrsaxon.commonapi.exceptions.NegativeBalanceException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;

    private double amount;
    private String currency;
    private AccountStatus status;

    // this constructor is required by AXON framework
    public AccountAggregate(){}

    @CommandHandler
    public AccountAggregate(AccountCreateCommand command){
        if(command.getInitialBalance()<0) throw new NegativeBalanceException("Account balance can not be negative => "+command.getInitialBalance());
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                command.getCurrency(),
                AccountStatus.CREATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("AccountCreatedEvent Occurred");
        this.accountId = event.getId();
        this.currency = event.getCurrency();
        this.amount = event.getInitialBalance();
        this.status = event.getStatus();

        // after create an account we need to activate it
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        log.info("AccountActivatedEvent Occurred");
        this.accountId = event.getId();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(AccountCreditCommand command){
        if (command.getBalance() < 0) throw new NegativeBalanceException("Account can not be credited by a negative balance => "+command.getBalance());
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getBalance(),
                command.getCurrency(),
                AccountStatus.CREDITED
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        log.info("AccountCreditedEvent Occurred");
        this.accountId = event.getId();
        this.currency = event.getCurrency();
        this.amount = this.amount + event.getBalance();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(AccountDebitCommand command){
        if (command.getBalance() < 0) throw new NegativeBalanceException("Account can not be debited by a negative balance => "+command.getBalance());
        if (command.getBalance() > this.amount) throw new InsufficientAmountException("Account's amount is not sufficient to make the debit operation");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getBalance(),
                command.getCurrency(),
                AccountStatus.DEBITED
        ));
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        log.info("AccountCreditedEvent Occurred");
        this.accountId = event.getId();
        this.currency = event.getCurrency();
        this.amount = this.amount - event.getBalance();
        this.status = event.getStatus();
    }

}
