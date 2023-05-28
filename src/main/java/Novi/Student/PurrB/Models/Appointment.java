package Novi.Student.PurrB.Models;

import Novi.Student.PurrB.Controllers.InvoiceController;
import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue
    private long appointmentId;

    private String startDate;

    private String endDate;

    private String time;

    private String notes;

    @ManyToOne
    @JoinColumn(name="appointments")
    private Client client;

    public Appointment(Client client) {
        this.client = client;
    }
    @ManyToOne
    @JoinColumn(name="invoices")
    private Invoice appointments;

    public Appointment(Invoice appointments) {
        this.appointments = appointments;
    }



    public Appointment(){}

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Invoice getInvoice() {
        return appointments;
    }

    public void setInvoice(Invoice appointments) {
        this.appointments = appointments;
    }
}
