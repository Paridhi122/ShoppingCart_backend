package com.caseStudy.eCart.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Cart")
@EntityListeners(AuditingEntityListener.class)
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CartId;
    @ManyToOne
    private Product products;
    @ManyToOne
    private Users users;
    @Column
    private int quantity;
    @Column
    private Double totalprice;

    public Cart(Product products, Users users, int quantity) {
        this.products = products;
        this.users = users;
        this.quantity = quantity;
        this.totalprice = getTotalprice();
    }

    public Cart() {
    }

    public Long getCartId() {
        return CartId;
    }

    public void setCartId(Long cartId) {
        CartId = cartId;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        totalprice = getProducts().getPrice() * getQuantity();
        this.totalprice = totalprice;
    }
}
