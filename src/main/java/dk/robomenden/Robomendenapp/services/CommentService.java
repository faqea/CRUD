package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.Comments;
import dk.robomenden.Robomendenapp.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BuyerService buyerService;

    @Autowired
    public CommentService(CommentRepository commentRepository, BuyerService buyerService) {
        this.commentRepository = commentRepository;
        this.buyerService = buyerService;
    }

    //Метод для сохранения сущности
    //Method for saving the entity
    @Transactional
    public void save(Comments comments) {
        comments.setCreateDate(LocalDateTime.now());
        comments.setChecked(true);
        commentRepository.save(comments);
    }

    //Метод для удаления сущности по ID
    //Method to delete an entity by ID
    @Transactional
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }

    //Метод для получения сущности по ID
    //A method to retrieve an entity by ID
    public Optional<Comments> findById(int id) {
        return commentRepository.findById(id);
    }

    //Метод для редактирования сущности
    //Method for editing an entity
    @Transactional
    public void edit(int id, Comments comments) {
        comments.setId(id);
        commentRepository.save(comments);
    }
}
