package com.web.puzzlestore.BackEnd.model.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author User
 */
@Entity
public class Puzzle {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    @Column(nullable = false)
    private String name;
    @Basic
    @Column(nullable = false)
    private String description;
    @Basic
    @Column(nullable = false)
    private double price;
    @Basic
    private String imageSource;

    public Puzzle(){
        
    }

    public Puzzle(Long id, String name, String description, double price, String imageSource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageSource = imageSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

}