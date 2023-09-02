package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.RobotMaxId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotMaxIdRepository extends JpaRepository<RobotMaxId, Integer> {

    @Query("SELECT max(e.id) FROM RobotMaxId e")
    Integer getMaxId();

}
