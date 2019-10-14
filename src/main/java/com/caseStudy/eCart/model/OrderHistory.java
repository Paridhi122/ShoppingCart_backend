package com.caseStudy.eCart.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="Order_History")
@EntityListeners(AuditingEntityListener.class)
public class OrderHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Users users;
    @Column
    private int totalQuantity;
    @Column
    private double totalcartprice;
    @Column(nullable = false)
    private LocalDate date;

    public OrderHistory() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalcartprice() {
        return totalcartprice;
    }

    public void setTotalcartprice(double totalcartprice) {
        this.totalcartprice = totalcartprice;
    }

    public LocalDate getDate() {
        date = LocalDate.now();
        return date;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }
}
