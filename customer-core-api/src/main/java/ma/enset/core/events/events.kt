package ma.enset.core.events

import ma.enset.core.enums.AccountStatus
import java.util.*

abstract class BaseEvent<T>(
        open val id : T,
        open val eventDate : Date
)

data class AccountCreatedEvent(
        override val id : String,
        val initialBalance : Double,
        val currency : String,
        val status: AccountStatus,
        override val eventDate : Date
):BaseEvent<String>(id, eventDate);

data class AccountCreditedEvent(
        override val id : String,
        val balance : Double,
        val currency : String,
        val status: AccountStatus,
        override val eventDate : Date
):BaseEvent<String>(id, eventDate);

data class AccountDebitedEvent(
        override val id : String,
        val balance : Double,
        val currency : String,
        val status: AccountStatus,
        override val eventDate : Date
):BaseEvent<String>(id, eventDate);

data class AccountActivatedEvent(
        override val id : String,
        val status: AccountStatus,
        override val eventDate : Date
):BaseEvent<String>(id, eventDate);