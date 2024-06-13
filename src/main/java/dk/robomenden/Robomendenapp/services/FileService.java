package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.File;
import dk.robomenden.Robomendenapp.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    //Метод для сохранения сущности
    //Method for saving the entity
    @Transactional
    public void save(File file) {
        file.setCreateDate(LocalDateTime.now());
        fileRepository.save(file);
    }

    //Метод для удаления сущности по ID
    //Method to delete an entity by ID
    @Transactional
    public void deleteById(int id) {
        fileRepository.deleteById(id);
    }

}
