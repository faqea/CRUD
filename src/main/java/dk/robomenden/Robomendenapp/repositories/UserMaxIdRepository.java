package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.UserMaxId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMaxIdRepository extends JpaRepository<UserMaxId,Integer> {

    @Query("SELECT max(e.id) FROM UserMaxId e")
    Integer getMaxId();
}
