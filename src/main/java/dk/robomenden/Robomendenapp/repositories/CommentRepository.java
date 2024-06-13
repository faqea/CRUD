package dk.robomenden.Robomendenapp.repositories;

import dk.robomenden.Robomendenapp.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
}
