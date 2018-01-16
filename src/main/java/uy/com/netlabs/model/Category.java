package uy.com.netlabs.model;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {

    @Id
    @Column(name="category_id")
    @GeneratedValue()
    private int id;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "discount_percentage")
    private int discountPercentage;



    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", ciudad='" + ciudad + '\'' +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
