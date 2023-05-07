package Novi.Student.PurrB.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue
    private long invoidId;

    private String dueDate;

    private int amount;

    private boolean paid;

    public Invoice(){}

    public Invoice(long invoidId, String dueDate, int amount, boolean paid) {
        this.invoidId = invoidId;
        this.dueDate = dueDate;
        this.amount = amount;
        this.paid = paid;
    }

    public long getInvoidId() {
        return invoidId;
    }

    public void setInvoidId(long invoidId) {
        this.invoidId = invoidId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
