package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT max(e.id) FROM Task e")
    Integer getMaxId();
}
