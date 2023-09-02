package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.BuildMarketMaxId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildMarketMaxIdRepository extends JpaRepository<BuildMarketMaxId, Integer> {

    @Query("SELECT max(e.id) FROM BuildMarketMaxId e")
    Integer getMaxId();
}
