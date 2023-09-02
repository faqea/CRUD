package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.BuildMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildMarketRepository extends JpaRepository<BuildMarket, Integer> {
}
