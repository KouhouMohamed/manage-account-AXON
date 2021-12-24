package ma.enset.queryservice.web;

import lombok.AllArgsConstructor;
import ma.enset.cqrsaxon.commonapi.queries.GetAccountQuery;
import ma.enset.cqrsaxon.commonapi.queries.GetAllAccountsQuery;
import ma.enset.queryservice.entities.Account;
import ma.enset.queryservice.repositories.AccountRepository;
import ma.enset.queryservice.service.AccountServiceHandler;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/queries/account")
@AllArgsConstructor
public class AccountController {
    QueryGateway queryGateway;

    @GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public List<Account> getAllAccount(){
        return queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
    }
    @GetMapping(path = "/get-account/{id}", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public Account getAccount(@PathVariable String id) throws ExecutionException, InterruptedException {
        GetAccountQuery query = new GetAccountQuery(id);
        CompletableFuture<Account> response = queryGateway.query(query, ResponseTypes.instanceOf((Account.class)));
        return response.get();
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
