package dk.robomenden.Robomendenapp.models;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "robot")
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;

    @OneToMany(mappedBy = "owner_robot")
    List<LogEntry> logEntryList;

    @OneToOne
    @JoinColumn(name = "winter_task_id")
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    private WinterTask winterTask;

    @Column(name = "buyer")
    private String owner;

    @Column(name = "robot_name")
    private String robotName;

    @Column(name = "serial_number")
    private long robotNumber;

    @Column(name = "pin_code")
    private long pinCode;

    @Column(name = "purchase_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate robotDate;
    
    {
    	robotName = "null";
    	robotNumber = 0;
    }

    public Robot(int id, Buyer buyer, List<LogEntry> logEntryList, WinterTask winterTask, String owner, String robotName, long robotNumber, long pinCode, LocalDate robotDate) {
        this.id = id;
        this.buyer = buyer;
        this.logEntryList = logEntryList;
        this.winterTask = winterTask;
        this.owner = owner;
        this.robotName = robotName;
        this.robotNumber = robotNumber;
        this.pinCode = pinCode;
        this.robotDate = robotDate;
    }

    public Robot() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<LogEntry> getLogEntryList() {
        return logEntryList;
    }

    public void setLogEntryList(List<LogEntry> logEntryList) {
        this.logEntryList = logEntryList;
    }

    public WinterTask getWinterTask() {
        return winterTask;
    }

    public void setWinterTask(WinterTask winterTask) {
        this.winterTask = winterTask;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public long getRobotNumber() {
        return robotNumber;
    }

    public void setRobotNumber(long robotNumber) {
        this.robotNumber = robotNumber;
    }

    public long getPinCode() {
        return pinCode;
    }

    public void setPinCode(long pinCode) {
        this.pinCode = pinCode;
    }

    public LocalDate getRobotDate() {
        return robotDate;
    }

    public void setRobotDate(LocalDate robotDate) {
        this.robotDate = robotDate;
    }
}
