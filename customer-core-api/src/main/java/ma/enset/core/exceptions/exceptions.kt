package ma.enset.core.exceptions

data class InsufficientAmountException(
        override var message : String
):RuntimeException(message)

data class NegativeBalanceException(
        override var message : String
):RuntimeException(message)