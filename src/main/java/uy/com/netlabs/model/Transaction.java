package uy.com.netlabs.model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;


@Entity
@Table(name="transaction")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    @Id @GeneratedValue
    @Column(name="id_transaction")
    private int id;

    @Column(name="date")
    private Timestamp date;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_transaction",updatable = false, insertable = false)
    private List<TransactionDetail> transactionDetails = new ArrayList<>();

    public Transaction(){}

    public Transaction(int id, Timestamp date, double totalPrice, List<TransactionDetail> transactionDetail) {

        this.date = date;
        this.totalPrice = totalPrice;
        this.transactionDetails = transactionDetail;
    }

    public static Transaction createTransaction() {
        return new Transaction();
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public List<TransactionDetail> getTransactionDetail() {
        return transactionDetails;
    }

    //Don't expose collection implementation
    public void addTransactionDetails(TransactionDetail transactionDetail){
        if (transactionDetail == null)
            throw new IllegalArgumentException("Transaction detail is null");
        transactionDetail.setTransactionId(this.getId());
        this.transactionDetails.add(transactionDetail);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Id=" + this.id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
