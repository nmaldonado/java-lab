package uy.com.netlabs.model;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="transaction_detail")
public class TransactionDetail implements Serializable{


    private static final long serialVersionUID = 1L;


    public int getId() {
        return id;
    }

    @Id @GeneratedValue
    @Column(name="transaction_detail_id")
    private int id;


    //TODO if it's needed to access to all invoices with x product, this could be a relation
    @Column(name="id_transaction")
    @JoinColumn(name="id_transaction",referencedColumnName = "id_transaction")
    @ForeignKey(name="FK_transaction_id")
    private int transactionId;


    @ForeignKey(name="FK_product_id")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_product")
    private Product product;

    @Column(name="quantity")
    private int quantity;

    @Column(name="discount_applied")
    private int discountApplied;

    public TransactionDetail(int id, int transactionId, Product product, int quantity) {

        this.transactionId= transactionId;
        this.product = product;
        this.quantity= quantity;
    }

    public TransactionDetail() {}

    public static TransactionDetail createTransactionDetail() {
        return new TransactionDetail();
    }

    public int getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(int discountApplied) {
        this.discountApplied = discountApplied;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "id=" + id +
                /**", transactionId=" + transactionId +
                ", product=" + productId.getId() +
                ", product name=" + productId.getCiudad() +
                ", product price =" + productId.getPrice() +**/
                ", Quantity=" + this.quantity +
                '}';
    }
}
