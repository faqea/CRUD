package dk.robomenden.Robomendenapp.models;



import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "logs")
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "robot_id", referencedColumnName = "id")
    private Robot owner_robot;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;

    public LogEntry() {

    }

    public LogEntry(String message) {
        this.message = message;
    }

    public LogEntry(int id, LocalDateTime date, Robot owner_robot, String message) {
        this.id = id;
        this.date = date;
        this.owner_robot = owner_robot;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Robot getOwner_robot() {
        return owner_robot;
    }

    public void setOwner_robot(Robot owner_robot) {
        this.owner_robot = owner_robot;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
