package dk.robomenden.Robomendenapp.models;

import javax.persistence.*;

@Entity
@Table(name = "task_max_id")
public class TaskMaxId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "max_id")
    private int max_id;

    public TaskMaxId() {

    }

    public TaskMaxId(int id, int max_id) {
        this.id = id;
        this.max_id = max_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMax_id() {
        return max_id;
    }

    public void setMax_id(int max_id) {
        this.max_id = max_id;
    }
}
