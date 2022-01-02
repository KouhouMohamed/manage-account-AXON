package ma.enset.core.dtos

data class CreateAccountRequestDto(
        var initialBalance : Double,
        var currency: String
)

data class CreditAccountRequestDto(
        var accountId : String,
        var balance : Double,
        var currency: String
)

data class DebitAccountRequestDto(
        var accountId : String,
        var balance : Double,
        var currency: String
)