package uy.com.netlabs.model;


import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id_product")
    @GeneratedValue()
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @ForeignKey(name="FK_category_id")
    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "category_id")
    private Category category;
    @Column(name = "stock")
    private int stock;


    public Product() {
    }

    public Product(int id, String name, Double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Product createProduct() {
        return new Product();
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", stock=" + stock +
                ", Category='" + category + '\'' +
                '}';
    }
}
