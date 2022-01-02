package ma.enset.queryservice.repositories;

import ma.enset.queryservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
