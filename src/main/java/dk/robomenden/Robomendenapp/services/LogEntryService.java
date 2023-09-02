package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.LogEntry;
import dk.robomenden.Robomendenapp.repositories.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class LogEntryService {

    private final LogEntryRepository logEntryRepository;

    @Autowired
    public LogEntryService(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    //Метод для сохранения сущности
    //Method for saving the entity
    @Transactional
    public void save(LogEntry logEntry) {
        logEntry.setDate(LocalDateTime.now());
        logEntryRepository.save(logEntry);
    }

}
