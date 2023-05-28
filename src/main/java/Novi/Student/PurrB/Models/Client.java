package Novi.Student.PurrB.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue
    private Long clientId;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats;

    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;

    private String name;

    private String email;

    private String phone;

    private String address;

    public Client(){}

    public Client(Long clientId, String name, String email, String phone, String address) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Client(Long clientId, User user, String name, String email, String phone, String address) {
        this.clientId = clientId;
        this.user = user;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Client(User user) {
        this.user = user;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNum) {
        this.phone = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
