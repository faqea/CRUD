package dk.robomenden.Robomendenapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "comment_owner", referencedColumnName = "id")
    private Buyer comment_owner;

    @ManyToOne
    @JoinColumn(name = "task_comment", referencedColumnName = "id")
    private Task task_comments;

    @ManyToOne
    @JoinColumn(name = "winter_task_comment", referencedColumnName = "id")
    private WinterTask winter_task_comments;



    @Column(name = "checked")
    private boolean checked;

    public Comments() {

    }

    public Comments(int id, LocalDateTime createDate, WinterTask winter_task_comments, boolean checked, Task task_comments, String comment, Buyer comment_owner) {
        this.id = id;
        this.createDate = createDate;
        this.winter_task_comments = winter_task_comments;
        this.checked = checked;
        this.task_comments = task_comments;
        this.comment = comment;
        this.comment_owner = comment_owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Buyer getComment_owner() {
        return comment_owner;
    }

    public void setComment_owner(Buyer comment_owner) {
        this.comment_owner = comment_owner;
    }

    public Task getTask() {
        return task_comments;
    }

    public void setTask(Task task_comments) {
        this.task_comments = task_comments;
    }

    public Task getTask_comments() {
        return task_comments;
    }

    public void setTask_comments(Task task_comments) {
        this.task_comments = task_comments;
    }

    public WinterTask getWinter_task_comments() {
        return winter_task_comments;
    }

    public void setWinter_task_comments(WinterTask winter_task_comments) {
        this.winter_task_comments = winter_task_comments;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
