package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.ProducerMaxId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerMaxIdRepository extends JpaRepository<ProducerMaxId, Integer> {

    @Query("SELECT max(e.id) FROM ProducerMaxId e")
    Integer getMaxId();
}
