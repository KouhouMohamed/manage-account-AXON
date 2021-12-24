package ma.enset.cqrsaxon.command.controllers;

import lombok.AllArgsConstructor;
import ma.enset.cqrsaxon.command.services.CommandService;
import ma.enset.cqrsaxon.commonapi.commands.AccountCreateCommand;
import ma.enset.cqrsaxon.commonapi.commands.AccountCreditCommand;
import ma.enset.cqrsaxon.commonapi.dtos.CreateAccountRequestDto;
import ma.enset.cqrsaxon.commonapi.dtos.CreditAccountRequestDto;
import ma.enset.cqrsaxon.commonapi.dtos.DebitAccountRequestDto;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class CommandController {
    private CommandService commandService;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDto request){
        CompletableFuture<String> response = commandService.createAccount(request);
        return response;
    }

    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDto request){
        CompletableFuture<String> response = commandService.debitAccount(request);
        return response;
    }

    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDto request){
        CompletableFuture<String> response = commandService.creditAccount(request);
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> response = new ResponseEntity<String>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return response;
    }
}
