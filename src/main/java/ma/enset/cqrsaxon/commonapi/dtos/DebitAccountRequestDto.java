package ma.enset.cqrsaxon.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitAccountRequestDto {
    private String accountId;
    private double balance;
    private String currency;
}
