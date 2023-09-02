package dk.robomenden.Robomendenapp.models;

import javax.persistence.*;

@Entity
@Table(name = "buildmarket")
public class BuildMarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "postnumber")
    private String postNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "cvrnumber")
    private String CVRNumber;

    @Column(name = "email")
    private String email;

    public BuildMarket() {

    }

    public BuildMarket(int id, String city, String name, String address, String postNumber, String phoneNumber, String CVRNumber, String email) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.postNumber = postNumber;
        this.phoneNumber = phoneNumber;
        this.CVRNumber = CVRNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCVRNumber() {
        return CVRNumber;
    }

    public void setCVRNumber(String CVRNumber) {
        this.CVRNumber = CVRNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
