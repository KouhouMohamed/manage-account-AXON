package ma.enset.commandservice.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commandservice.services.CommandService;
import ma.enset.cqrsaxon.commonapi.dtos.CreateAccountRequestDto;
import ma.enset.cqrsaxon.commonapi.dtos.CreditAccountRequestDto;
import ma.enset.cqrsaxon.commonapi.dtos.DebitAccountRequestDto;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class CommandController {
    private CommandService commandService;
    private EventStore eventStore;

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

    @GetMapping(path = "/event-store/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
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
