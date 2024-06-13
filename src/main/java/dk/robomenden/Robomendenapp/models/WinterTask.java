package dk.robomenden.Robomendenapp.models;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "winter_task")
public class WinterTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ordering_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderingDate;

    @Column(name = "pickup_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;

    @Column(name = "delivery_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    @Column(name = "route")
    private long route;

    @Column(name = "location")
    private String location;

    @Column(name = "preparation1")
    private String preparation1;

    @Column(name = "preparation2")
    private String preparation2;

    @Column(name = "pickup")
    private String pickup;

    @Column(name = "delivery")
    private String delivery;

    @Column(name = "pincode")
    private long pinCode;

    @Column(name = "client")
    private String client;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_phone_number")
    private String clientPhoneNumber;

    @Column(name = "road")
    private String road;

    @Column(name = "post_number")
    private long postNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "status")
    private String status;

    @Column(name = "invoiced")
    private boolean invoiced;

    @Column(name = "sælger")
    private String seller;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "sæson")
    private String season;

    @OneToOne(mappedBy = "winterTask")
    private Robot robot;

    @OneToMany(mappedBy = "winter_task_comments")
    private List<Comments> comments;
    
    {
    	orderingDate = LocalDateTime.now();
    }

    public WinterTask(int id, LocalDateTime orderingDate, LocalDate pickupDate, LocalDate deliveryDate, long route, String location, String preparation1, String preparation2, String pickup, String delivery, long pinCode, String client, String clientName, String clientPhoneNumber, String road, long postNumber, String city, String status, boolean invoiced, String seller, String clientEmail, String season, Robot robot, List<Comments> comments) {
        this.id = id;
        this.orderingDate = orderingDate;
        this.pickupDate = pickupDate;
        this.deliveryDate = deliveryDate;
        this.route = route;
        this.location = location;
        this.preparation1 = preparation1;
        this.preparation2 = preparation2;
        this.pickup = pickup;
        this.delivery = delivery;
        this.pinCode = pinCode;
        this.client = client;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.road = road;
        this.postNumber = postNumber;
        this.city = city;
        this.status = status;
        this.invoiced = invoiced;
        this.seller = seller;
        this.clientEmail = clientEmail;
        this.season = season;
        this.robot = robot;
        this.comments = comments;
    }

    public WinterTask() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOrderingDate() {
        return orderingDate;
    }

    public void setOrderingDate(LocalDateTime orderingDate) {
        this.orderingDate = orderingDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public long getRoute() {
        return route;
    }

    public void setRoute(long route) {
        this.route = route;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPreparation1() {
        return preparation1;
    }

    public void setPreparation1(String preparation1) {
        this.preparation1 = preparation1;
    }

    public String getPreparation2() {
        return preparation2;
    }

    public void setPreparation2(String preparation2) {
        this.preparation2 = preparation2;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public long getPinCode() {
        return pinCode;
    }

    public void setPinCode(long pinCode) {
        this.pinCode = pinCode;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public long getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(long postNumber) {
        this.postNumber = postNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
