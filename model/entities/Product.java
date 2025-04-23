package com.wbsistemas.controle_estoque_jdbc.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Product implements Serializable {

    private int id;
    private String name;
    private Double price;
    private Date date;
    private int quantity;

    public Product() {

    }

    public Product(int id, String product, Double price, Date date, int quantity) {
        this.id = id;
        this.name = product;
        this.price = price;
        this.date = date;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return name;
    }

    public void setProduct(String product) {
        this.name = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product1 = (Product) o;
        return id == product1.id && quantity == product1.quantity && Objects.equals(name, product1.name)
                && Objects.equals(price, product1.price) && Objects.equals(date, product1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, date, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", quantity=" + quantity +
                '}';
    }
}
