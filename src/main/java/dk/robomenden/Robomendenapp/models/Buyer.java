package dk.robomenden.Robomendenapp.models;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;


import java.util.List;

@Entity
@Table(name = "buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "task_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Task task;

    @OneToMany(mappedBy = "buyer")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Robot> robots;

    @OneToMany(mappedBy = "owner")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<File> files;

    @OneToMany(mappedBy = "comment_owner")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comments> comments;

    @Column(name = "name")
    private String name;

    @Column(name = "way")
    private String way;

    @Column(name = "post_index")
    private String post_index;

    @Column(name = "city")
    private String city;

    @Column(name = "way2")
    private String way2;

    @Column(name = "post_index2")
    private String post_index2;

    @Column(name = "city2")
    private String city2;

    @Column(name = "phone_number")
    private String phone_number;//

    @Column(name = "phone_number2")
    private String phone_number2;

    @Column(name = "email")
    private String email;

    public Buyer() {

    }

    public Buyer(int id, Task task, List<Comments> comments, List<File> files, List<Robot> robots, String name, String way, String post_index, String city, String way2, String post_index2, String city2, String phone_number, String phone_number2, String phone_number3, String email) {
        this.id = id;
        this.comments = comments;
        this.task = task;
        this.files = files;
        this.robots = robots;
        this.name = name;
        this.way = way;
        this.post_index = post_index;
        this.city = city;
        this.way2 = way2;
        this.post_index2 = post_index2;
        this.city2 = city2;
        this.phone_number = phone_number;
        this.phone_number2 = phone_number2;
        this.phone_number3 = phone_number3;
        this.email = email;
    }

    @Column(name = "phone_number3")
    private String phone_number3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getPost_index() {
        return post_index;
    }

    public void setPost_index(String post_index) {
        this.post_index = post_index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWay2() {
        return way2;
    }

    public void setWay2(String way2) {
        this.way2 = way2;
    }

    public String getPost_index2() {
        return post_index2;
    }

    public void setPost_index2(String post_index2) {
        this.post_index2 = post_index2;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number2() {
        return phone_number2;
    }

    public void setPhone_number2(String phone_number2) {
        this.phone_number2 = phone_number2;
    }

    public String getPhone_number3() {
        return phone_number3;
    }

    public void setPhone_number3(String phone_number3) {
        this.phone_number3 = phone_number3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }


}
