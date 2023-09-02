package dk.robomenden.Robomendenapp.models;

import javax.persistence.*;

@Entity
@Table(name = "robotmaxid")
public class RobotMaxId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "maxid")
    private int maxId;

    public RobotMaxId(int id, int maxId) {
        this.id = id;
        this.maxId = maxId;
    }

    public RobotMaxId() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }
}
