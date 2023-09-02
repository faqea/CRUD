package dk.robomenden.Robomendenapp.models;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "initials")
    private String initials;

    @Column(name = "role")
    private String role;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "post_number")
    private String postNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_number2")
    private String phoneNumber2;

    @Column(name = "email")
    private String email;

    public User(int id, String password, String initials, String role, String name, String address, String postNumber, String city, String phoneNumber, String phoneNumber2, String email) {
        this.id = id;
        this.password = password;
        this.initials = initials;
        this.role = role;
        this.name = name;
        this.address = address;
        this.postNumber = postNumber;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.phoneNumber2 = phoneNumber2;
        this.email = email;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
