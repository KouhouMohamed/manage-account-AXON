package ma.enset.cqrsaxon.commonapi.queries;

import lombok.Getter;

public class GetAccountQuery {
    @Getter private String accountId;

    public GetAccountQuery(String id) {
        this.accountId = id;
    }
}
