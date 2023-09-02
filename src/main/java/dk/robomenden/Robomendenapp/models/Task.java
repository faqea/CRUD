package dk.robomenden.Robomendenapp.models;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "task_comments")
    private List<Comments> comments;

    @OneToOne(mappedBy = "task")
    private Robot task_robot;

    @OneToOne(mappedBy = "task")
    private Buyer buyer;

    @Column(name = "task_type")
    private String taskType;

    @Column(name = "original_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime originalDate;

    @Column(name = "specify_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate specifyDate;

    @Column(name = "time")
    private String time;

    @Column(name = "row_fold")
    private Long rowFold;

    @Column(name = "installer")
    private String installer;

    @Column(name = "construction_market")
    private String constructionMarket;

    @Column(name = "store_number")
    private Long storeNumber;

    @Column(name = "requisition_number")
    private Long requisitionNumber;

    @Column(name = "seller")
    private String seller;

    @Column(name = "client")
    private String client;

    @Column(name = "serial_number")
    private Long serialNumber;

    @Column(name = "campaign1")
    private String campaign1;

    @Column(name = "campaign2")
    private String campaign2;

    @Column(name = "producer")
    private String producer;

    @Column(name = "task_status")
    private String task_status;

    @Column(name = "robot")
    private String robot;

    @Column(name = "pin_code")
    private Long pinCode;

    @Column(name = "invoiced_manufacturer")
    private boolean invoicedManufacturer;

    @Column(name = "invoiced_client")
    private boolean invoicedClient;

    @Column(name = "invoiced_construction_market")
    private boolean invoicedConstructionMarket;


    public Task(int id, List<Comments> comments, Robot task_robot, Buyer buyer, String taskType ,LocalDateTime originalDate, LocalDate specifyDate, String time, Long rowFold, String installer, String constructionMarket, Long storeNumber, Long requisitionNumber, String seller, String client, Long serialNumber, String campaign1, String campaign2, String producer, String task_status, String robot, Long pinCode, boolean invoicedManufacturer, boolean invoicedClient, boolean invoicedConstructionMarket) {
        this.id = id;
        this.comments = comments;
        this.task_robot = task_robot;
        this.buyer = buyer;
        this.taskType = taskType;
        this.originalDate = originalDate;
        this.specifyDate = specifyDate;
        this.time = time;
        this.rowFold = rowFold;
        this.installer = installer;
        this.constructionMarket = constructionMarket;
        this.storeNumber = storeNumber;
        this.requisitionNumber = requisitionNumber;
        this.seller = seller;
        this.client = client;
        this.serialNumber = serialNumber;
        this.campaign1 = campaign1;
        this.campaign2 = campaign2;
        this.producer = producer;
        this.task_status = task_status;
        this.robot = robot;
        this.pinCode = pinCode;
        this.invoicedManufacturer = invoicedManufacturer;
        this.invoicedClient = invoicedClient;
        this.invoicedConstructionMarket = invoicedConstructionMarket;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Robot getTask_robot() {
        return task_robot;
    }

    public void setTask_robot(Robot task_robot) {
        this.task_robot = task_robot;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getOriginalDate() {
        return originalDate;
    }

    public void setOriginalDate(LocalDateTime originalDate) {
        this.originalDate = originalDate;
    }

    public LocalDate getSpecifyDate() {
        return specifyDate;
    }

    public void setSpecifyDate(LocalDate specifyDate) {
        this.specifyDate = specifyDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getRowFold() {
        return rowFold;
    }

    public void setRowFold(Long rowFold) {
        this.rowFold = rowFold;
    }

    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
    }

    public String getConstructionMarket() {
        return constructionMarket;
    }

    public void setConstructionMarket(String constructionMarket) {
        this.constructionMarket = constructionMarket;
    }

    public Long getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(Long storeNumber) {
        this.storeNumber = storeNumber;
    }

    public Long getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(Long requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCampaign1() {
        return campaign1;
    }

    public void setCampaign1(String campaign1) {
        this.campaign1 = campaign1;
    }

    public String getCampaign2() {
        return campaign2;
    }

    public void setCampaign2(String campaign2) {
        this.campaign2 = campaign2;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getRobot() {
        return robot;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }

    public boolean isInvoicedManufacturer() {
        return invoicedManufacturer;
    }

    public void setInvoicedManufacturer(boolean invoicedManufacturer) {
        this.invoicedManufacturer = invoicedManufacturer;
    }

    public boolean isInvoicedClient() {
        return invoicedClient;
    }

    public void setInvoicedClient(boolean invoicedClient) {
        this.invoicedClient = invoicedClient;
    }

    public boolean isInvoicedConstructionMarket() {
        return invoicedConstructionMarket;
    }

    public void setInvoicedConstructionMarket(boolean invoicedConstructionMarket) {
        this.invoicedConstructionMarket = invoicedConstructionMarket;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
