package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Dtos.*;
import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Helpers.JwtUtils;
import Novi.Student.PurrB.Models.Appointment;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Models.Invoice;
import Novi.Student.PurrB.Models.Role;
import Novi.Student.PurrB.Repositories.AppointmentRepository;
import Novi.Student.PurrB.Repositories.ClientRepository;
import Novi.Student.PurrB.Repositories.InvoiceRepository;
import Novi.Student.PurrB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepos;

    @Autowired
    private ClientRepository clientRepos;

    @Autowired
    private  JwtService jwtService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    public InvoiceService(InvoiceRepository invoiceRepos) {
        this.invoiceRepos = invoiceRepos;
    }

    public List<InvoiceDto> getInvoiceByAuth(@RequestHeader("Authorization") String authHeader){
        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        return convertInvoiceToDtos(c.getInvoices());
    }

    public InvoiceDto postInvoice(InvoiceInputDto input, Long clientId){

        Optional<Client> c = (clientRepos.findById(clientId));

        Client client = c.orElseThrow(() -> new NoSuchElementException("Client not found"));

        Invoice a = invoiceToModel(input);

        a.setCustomer(client);
        a.setAppointments(client.getAppointments());
        invoiceRepos.save(a);

        return invoiceToDto(a);
    }

    public InvoiceDto editInvoice (InvoiceDto invoiceInput, Long clientId){
        Optional<Client> c = (clientRepos.findById(clientId));
        Client client = c.orElseThrow(() -> new NoSuchElementException("Client not found"));

        List<Invoice> invoices = client.getInvoices();

        for (Invoice invoice : invoices) {

            invoice.setPaid(invoiceInput.paid);
            invoice.setCustomer(client);
            invoice.setAmount(invoiceInput.amount);
            invoice.setDueDate(invoiceInput.dueDate);
            invoice.setAppointments(invoiceInput.appointments);

            invoiceRepos.save(invoice);

            return invoiceToDto(invoice);
        }
        throw new RecordNotFoundException("Wrong Appointment ID, check your appointment page for your ID.");

    }

    public void removeInvoice(Long invoiceId) {
        Optional<Invoice> i = invoiceRepos.findById(invoiceId);
        Invoice invoice = i.orElseThrow(() -> new NoSuchElementException("Invoice not found"));

        invoiceRepos.delete(invoice);
    }



    public Invoice invoiceToModel(InvoiceInputDto invoiceInput){

        Invoice invoice = new Invoice();

        invoice.setAmount(invoiceInput.amount);
        invoice.setPaid(invoiceInput.paid);
        invoice.setDueDate(invoiceInput.dueDate);

        return invoice;
    }

    public InvoiceDto invoiceToDto(Invoice input){

        InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.amount = input.getAmount();
        invoiceDto.dueDate = input.getDueDate();
        invoiceDto.paid = input.isPaid();
        invoiceDto.invoidId = input.getInvoidId();
        invoiceDto.customer = changeClientToClientDto(input.getCustomer());
        invoiceDto.appointments = input.getAppointments();

        return invoiceDto;
    }

    public List<InvoiceDto> convertInvoiceToDtos(List<Invoice> invoices) {
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (Invoice invoice : invoices) {
            InvoiceDto invoiceDto = new InvoiceDto();

            invoiceDto.amount = invoice.getAmount();
            invoiceDto.dueDate = invoice.getDueDate();
            invoiceDto.paid = invoice.isPaid();
            invoiceDto.invoidId = invoice.getInvoidId();
            invoiceDto.customer = changeClientToClientDto(invoice.getCustomer());
            invoiceDto.appointments = invoice.getAppointments();

            invoiceDtos.add(invoiceDto);
        }
        return invoiceDtos;
    }

    public ClientDto changeClientToClientDto(Client c){

        ClientDto dto = new ClientDto();

        dto.clientId = c.getClientId();
        dto.address = c.getAddress();
        dto.name = c.getName();
        dto.email = c.getEmail();
        dto.phone = c.getPhone();

        UserDto userDto = new UserDto();
        userDto.username = c.getUser().getUsername();
        userDto.roles = c.getUser().getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet())
                .toArray(new String[0]);

        dto.user = userDto;

        return dto;
    }
}
