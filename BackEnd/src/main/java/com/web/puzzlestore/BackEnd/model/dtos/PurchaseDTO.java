package com.web.puzzlestore.BackEnd.model.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseDTO {
    private Long id;
    private LocalDateTime date;
    private double total;
    private String username;
    private double shippingTax;
    private double shippingCharge;
    private String deliveryAddress;
    private String paymentMethod;
    private List<PuzzleOrderDTO> puzzleOrders;
    public PurchaseDTO() {
    }
    public PurchaseDTO(Long id, LocalDateTime date, double total, String username, double shippingTax,
            double shippingCharge, String deliveryAddress, String paymentMethod, List<PuzzleOrderDTO> puzzleOrders) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.username = username;
        this.shippingTax = shippingTax;
        this.shippingCharge = shippingCharge;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.puzzleOrders = puzzleOrders;
    }
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
    public List<PuzzleOrderDTO> getPuzzleOrders() {
        return puzzleOrders;
    }
    public void setPuzzleOrders(List<PuzzleOrderDTO> puzzleOrders) {
        this.puzzleOrders = puzzleOrders;
    }
}
