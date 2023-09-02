package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.MaxId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxIdRepository extends JpaRepository<MaxId, Integer> {

    @Query("SELECT max(e.id) FROM MaxId e")
    Integer getMaxId();
}
