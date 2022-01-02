package ma.enset.queryservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.core.enums.OperationType;
import ma.enset.core.events.AccountActivatedEvent;
import ma.enset.core.events.AccountCreatedEvent;
import ma.enset.core.events.AccountCreditedEvent;
import ma.enset.core.events.AccountDebitedEvent;
import ma.enset.core.queries.GetAccountQuery;
import ma.enset.core.queries.GetAllAccountsQuery;
import ma.enset.queryservice.entities.Account;
import ma.enset.queryservice.entities.Operation;
import ma.enset.queryservice.repositories.AccountRepository;
import ma.enset.queryservice.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("Handle Account Created Event");
        // Create Account
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getInitialBalance());
        account.setCurrency(event.getCurrency());
        account.setStatus(event.getStatus());

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("Handle Account Created Event");
        Account account = accountRepository.getById(event.getId());
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("Handle Account Credited Event");

        // update account's balance
        Account account = accountRepository.getById(event.getId());
        account.setStatus(event.getStatus());
        account.setBalance(account.getBalance() + event.getBalance());

        // Create Operation
        Operation operation = new Operation();
        operation.setAmount(event.getBalance());
        operation.setCreatedAt(event.getEventDate());
        operation.setType(OperationType.CREDIT);
        operation.setAccount(account);

        operationRepository.save(operation);
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("Handle Account Debited Event");
        // update account's balance
        Account account = accountRepository.getById(event.getId());
        account.setStatus(event.getStatus());
        account.setBalance(account.getBalance() - event.getBalance());

        // Create Operation
        Operation operation = new Operation();
        operation.setAmount(event.getBalance());
        operation.setCreatedAt(event.getEventDate());
        operation.setType(OperationType.DEBIT);
        operation.setAccount(account);

        operationRepository.save(operation);
        accountRepository.save(account);
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account on(GetAccountQuery query){
        return accountRepository.findById(query.getAccountId()).get();
    }
}
