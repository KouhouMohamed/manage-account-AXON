package ma.enset.commandservice.services;

import ma.enset.cqrsaxon.dtos.CreateAccountRequestDto;
import ma.enset.cqrsaxon.dtos.CreditAccountRequestDto;
import ma.enset.cqrsaxon.dtos.DebitAccountRequestDto;

import java.util.concurrent.CompletableFuture;

public interface CommandService {
        CompletableFuture<String> createAccount(CreateAccountRequestDto accountRequestDTO);
        CompletableFuture<String> debitAccount(DebitAccountRequestDto debitAccountRequestDTO);
        CompletableFuture<String> creditAccount(CreditAccountRequestDto creditAccountRequestDTO);

}
