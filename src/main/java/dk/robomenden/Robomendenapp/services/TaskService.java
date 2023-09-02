package dk.robomenden.Robomendenapp.services;


import dk.robomenden.Robomendenapp.models.MaxId;
import dk.robomenden.Robomendenapp.models.Task;
import dk.robomenden.Robomendenapp.models.TaskMaxId;
import dk.robomenden.Robomendenapp.repositories.TaskMaxIdRepository;
import dk.robomenden.Robomendenapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMaxIdRepository taskMaxIdRepository;
    private int maxId = 26;


    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMaxIdRepository taskMaxIdRepository) {
        this.taskRepository = taskRepository;
        this.taskMaxIdRepository = taskMaxIdRepository;
    }


    //Метод для получения списка task'ов
    //A method for obtaining a list of tasks
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    //Метод для сохранения сущности
    //Method for saving the entity
    @Transactional
    public void save(Task task) {
        maxId++;
        task.setOriginalDate(LocalDateTime.now());
        taskRepository.save(task);
    }

    //Метод для удаления сущности по ID
    //Method to delete an entity by ID
    @Transactional
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    //Метод для получения сущности по ID
    //A method to retrieve an entity by ID
    public Task findById(int id) {
        return taskRepository.findById(id).get();
    }

    //Метод для редактирования сущности
    //Method for editing an entity
    @Transactional
    public void edit(int id, Task task) {
        task.setId(id);
        taskRepository.save(task);
    }

    //Метод для отображения сущностей по страницам
    //Method for displaying entities by page
    public List<Task> findAll(int page) {
        return taskRepository.findAll(PageRequest.of(page, 2)).getContent();
    }

    //Метод для получения максимального ID сущности
    //A method to get the maximum entity ID
    @Transactional
    public int getMaxId() {
        maxId = taskMaxIdRepository.findById(taskMaxIdRepository.getMaxId()).get().getMax_id() + 1;
        TaskMaxId maxId1 = new TaskMaxId();
        maxId1.setMax_id(maxId);
        taskMaxIdRepository.save(maxId1);
        return maxId1.getMax_id();
    }



}
