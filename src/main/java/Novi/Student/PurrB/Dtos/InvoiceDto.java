package Novi.Student.PurrB.Dtos;

import Novi.Student.PurrB.Models.Appointment;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Models.Invoice;

import java.util.List;

public class InvoiceDto {


    public long invoidId;

    public String dueDate;

    public int amount;

    public boolean paid;

    public ClientDto customer;

    public List<Appointment> appointments;
}
