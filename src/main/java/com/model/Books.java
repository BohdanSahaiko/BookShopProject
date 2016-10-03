package com.model;

/**
 * Created by bohdan on 08.08.16.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Books")
public class Books implements Serializable{

    private static final long serialVersionUID = 2934531843307954434L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idbook")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private double price;
    @Column(name = "img")
    private byte[] img;

    @ManyToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "UserBook", catalog = "test", joinColumns = {
            @JoinColumn(name = "idbook", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "username",
                    nullable = false) })
    private Set<Users> usersSet = new HashSet<Users>(0);

    public Books() {

    }

    public Books(String name, String author, String type, double price, byte[] img) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.price = price;
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Set<Users> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<Users> usersSet) {
        this.usersSet = usersSet;
    }

    public String getImgAsBase64() {
        String encoded = Base64.getEncoder().encodeToString(img);
        return encoded;
    }


}
