package ma.enset.queryservice.repositories;


import ma.enset.queryservice.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
