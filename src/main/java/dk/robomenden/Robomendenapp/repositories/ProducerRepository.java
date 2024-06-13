package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {
}
