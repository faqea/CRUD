package dk.robomenden.Robomendenapp.models;

import javax.persistence.*;

@Entity
@Table(name = "build_market_max_id")
public class BuildMarketMaxId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "maxid")
    private int maxId;

    public BuildMarketMaxId() {

    }

    public BuildMarketMaxId(int id, int maxId) {
        this.id = id;
        this.maxId = maxId;
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
