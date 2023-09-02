package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

    @Query("SELECT max(e.id) FROM Buyer e")
    Integer getMaxId();

}
