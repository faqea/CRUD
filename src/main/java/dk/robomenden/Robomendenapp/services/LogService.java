package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.LogEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    private final List<LogEntry> logs = new ArrayList<>();

    public void addLog(LogEntry logEntry) {
        logs.add(logEntry);
    }

    //Метод для получения списка log'ов
    //A method for obtaining a list of logs
    public List<LogEntry> getAllLogs() {
        return logs;
    }


}
