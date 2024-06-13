package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.User;
import dk.robomenden.Robomendenapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Метод для сохранения сущности
    //Method for saving the entity
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    //Метод для получения списка user'ов
    //A method for obtaining a list of users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //Метод для редактирования сущности
    //Method for editing an entity
    @Transactional
    public void edit(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    //Метод для удаления сущности по ID
    //Method to delete an entity by ID
    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
    
    //Метод для отображения сущностей по страницам
    //Method for displaying entities by page
    public List<User> findAll(int page) {
        return userRepository.findAll(PageRequest.of(page, 2)).getContent();
    }
}
