package ma.enset.commandservice.services;

import ma.enset.core.commands.AccountCreateCommand;
import ma.enset.core.commands.AccountCreditCommand;
import ma.enset.core.commands.AccountDebitCommand;
import ma.enset.core.dtos.CreateAccountRequestDto;
import ma.enset.core.dtos.CreditAccountRequestDto;
import ma.enset.core.dtos.DebitAccountRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CommandServiceImpl implements CommandService{
    private CommandGateway commandGateway;

    public CommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(CreateAccountRequestDto accountRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new AccountCreateCommand(
                UUID.randomUUID().toString(),
                accountRequestDTO.getCurrency(),
                accountRequestDTO.getInitialBalance()
        ));
        return response;
    }

    @Override
    public CompletableFuture<String> debitAccount(DebitAccountRequestDto debitAccountRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new AccountDebitCommand(
                debitAccountRequestDTO.getAccountId(),
                debitAccountRequestDTO.getCurrency(),
                debitAccountRequestDTO.getBalance()
        ));
        return response;
    }

    @Override
    public CompletableFuture<String> creditAccount(CreditAccountRequestDto creditAccountRequestDTO) {

        CompletableFuture<String> response = commandGateway.send(new AccountCreditCommand(
                creditAccountRequestDTO.getAccountId(),
                creditAccountRequestDTO.getCurrency(),
                creditAccountRequestDTO.getBalance()
        ));
        return response;
    }
}
