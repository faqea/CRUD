package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.BuyerMaxId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerMaxIdRepository extends JpaRepository<BuyerMaxId, Integer> {

    @Query("SELECT max(e.id) FROM BuyerMaxId e")
    Integer getMaxId();
}
