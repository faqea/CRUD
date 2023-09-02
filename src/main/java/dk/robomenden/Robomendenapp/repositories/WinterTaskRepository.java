package dk.robomenden.Robomendenapp.repositories;


import dk.robomenden.Robomendenapp.models.WinterTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WinterTaskRepository extends JpaRepository<WinterTask, Integer> {
    @Query("SELECT max(e.id) FROM WinterTask e")
    Integer getMaxId();

    Optional<WinterTask> findByClient(String client);
}
