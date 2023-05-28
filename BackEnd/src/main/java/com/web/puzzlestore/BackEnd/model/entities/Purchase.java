package com.web.puzzlestore.BackEnd.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author User
 */
@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    @Column(nullable = false)
    private LocalDateTime date;
    @Basic
    @Column(nullable = false)
    private double total;
    @Basic
    @Column(nullable = false)
    private String username;
    @Basic
    @Column(nullable = false)
    private double shippingTax;
    @Basic
    @Column(nullable = false)
    private double shippingCharge;
    @Basic
    @Column(nullable = false)
    private String deliveryAddress;
    @Basic
    @Column(nullable = false)
    private String paymentMethod;
    @OneToMany(fetch = FetchType.EAGER)
    private List<PuzzleOrder> puzzleOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getShippingTax() {
        return shippingTax;
    }

    public void setShippingTax(double shippingTax) {
        this.shippingTax = shippingTax;
    }

    public double getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(double shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<PuzzleOrder> getPuzzleOrders() {
        if (puzzleOrders == null) {
            puzzleOrders = new ArrayList<>();
        }
        return puzzleOrders;
    }

    public void setPuzzleOrders(List<PuzzleOrder> puzzleOrders) {
        this.puzzleOrders = puzzleOrders;
    }

    public void addPuzzleOrder(PuzzleOrder puzzleOrder) {
        getPuzzleOrders().add(puzzleOrder);
    }

    public void removePuzzleOrder(PuzzleOrder puzzleOrder) {
        getPuzzleOrders().remove(puzzleOrder);
    }

}