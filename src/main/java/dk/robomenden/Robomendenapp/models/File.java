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

    @Column(name = "filename")
    private String File_name;

    @Column(name = "path")
    private String path;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    public File() {

    }

    public File(int id, LocalDateTime createDate, Buyer owner, String File_name, String path) {
        this.id = id;
        this.createDate = createDate;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
