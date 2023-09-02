package dk.robomenden.Robomendenapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "file")
public class File {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Buyer owner;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task file_task;

    @Column(name = "filename")
    private String File_name;

    @Column(name = "path")
    private String path;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    public File() {

    }

    public File(int id, LocalDateTime createDate, Task file_task, Buyer owner, String File_name, String path) {
        this.id = id;
        this.createDate = createDate;
        this.file_task = file_task;
        this.owner = owner;
        this.File_name = File_name;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Buyer getOwner() {
        return owner;
    }

    public void setOwner(Buyer owner) {
        this.owner = owner;
    }

    public String getFile_name() {
        return File_name;
    }

    public void setFile_name(String File_name) {
        this.File_name = File_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Task getTask() {
        return file_task;
    }

    public void setTask(Task file_task) {
        this.file_task = file_task;
    }

    public Task getFile_task() {
        return file_task;
    }

    public void setFile_task(Task file_task) {
        this.file_task = file_task;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
