package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.Buyer;
import dk.robomenden.Robomendenapp.models.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Integer> {

}
