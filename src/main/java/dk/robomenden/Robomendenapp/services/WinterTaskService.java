package dk.robomenden.Robomendenapp.services;


import dk.robomenden.Robomendenapp.models.WinterTask;
import dk.robomenden.Robomendenapp.repositories.WinterTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WinterTaskService {

    private final WinterTaskRepository winterTaskRepository;

    @Autowired
    public WinterTaskService(WinterTaskRepository winterTaskRepository) {
        this.winterTaskRepository = winterTaskRepository;
    }


    //Метод для получения списка winterTask'ов
    //A method for obtaining a list of winterTasks
    public List<WinterTask> findAll() {
        return winterTaskRepository.findAll();
    }


    //Метод для сохранения сущности
    //Method for saving the entity
    @Transactional
    public void save(WinterTask winterTask) {
        winterTaskRepository.save(winterTask);
    }

    //Метод для отображения сущностей по страницам
    //Method for displaying entities by page
    public List<WinterTask> findAll(int page) {
        return winterTaskRepository.findAll(PageRequest.of(page, 2)).getContent();
    }


    //Метод для удаления сущности по ID
    //Method to delete an entity by ID
    @Transactional
    public void deleteById(int id) {
        winterTaskRepository.deleteById(id);
    }


    //Метод для редактирования сущности
    //Method for editing an entity
    @Transactional
    public void update(int id, WinterTask winterTask) {
        winterTask.setId(id);
        winterTaskRepository.save(winterTask);
    }

    //Метод для получения сущности по ID
    //A method to retrieve an entity by ID
    public WinterTask findById(int id) {
        return winterTaskRepository.findById(id).get();
    }

    //Метод для поиска winterTask'а по client'у
    public Optional<WinterTask> findByClient(String client) {
        return winterTaskRepository.findByClient(client);
    }
}
