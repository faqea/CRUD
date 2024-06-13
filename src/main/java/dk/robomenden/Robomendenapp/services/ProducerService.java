package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.Producer;
import dk.robomenden.Robomendenapp.repositories.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProducerService {

    private final ProducerRepository producerRepository;


    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    //Метод для получения списка producer'ов
    //A method for obtaining a list of producers
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    //Метод для удаления сущности по ID
    //Method to delete an entity by ID
    @Transactional
    public void delete(int id) {
        producerRepository.deleteById(id);
    }

    //Метод для получения сущности по ID
    //A method to retrieve an entity by ID
    public Producer findById(int id) {
        return producerRepository.findById(id).get();
    }

    //Метод для сохранения сущности
    //Method for saving the entity
    @Transactional
    public void save(Producer producer) {
        producerRepository.save(producer);
    }

    //Метод для редактирования сущности
    //Method for editing an entity
    @Transactional
    public void edit(int id, Producer producer) {
        producer.setId(id);
        producerRepository.save(producer);
    }


 // Метод для отображения сущностей по страницам
 	// Method for displaying entities by page
 	public List<Producer> findAll(int page) {
 		return producerRepository.findAll(PageRequest.of(page, 2)).getContent();
 	}
}
