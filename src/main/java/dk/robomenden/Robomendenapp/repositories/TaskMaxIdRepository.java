package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.TaskMaxId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMaxIdRepository extends JpaRepository<TaskMaxId, Integer> {

    @Query("SELECT max(e.id) FROM TaskMaxId e")
    Integer getMaxId();

}
