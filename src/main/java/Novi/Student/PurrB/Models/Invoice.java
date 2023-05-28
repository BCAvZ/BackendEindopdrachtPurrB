package Novi.Student.PurrB.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue
    private long invoidId;

    private String dueDate;

    private int amount;

    private boolean paid;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Client customer;

    @OneToMany(mappedBy = "appointments")
    private List<Appointment> appointments;

    public Invoice(){}

    public Invoice(long invoidId, String dueDate, int amount, boolean paid) {
        this.invoidId = invoidId;
        this.dueDate = dueDate;
        this.amount = amount;
        this.paid = paid;
    }

    public Invoice(Client customer) {
        this.customer = customer;
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

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
