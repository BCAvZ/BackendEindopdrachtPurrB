package Novi.Student.PurrB.Controllers;

import Novi.Student.PurrB.Dtos.AppointmentDto;
import Novi.Student.PurrB.Dtos.AppointmentInputDto;
import Novi.Student.PurrB.Dtos.InvoiceDto;
import Novi.Student.PurrB.Dtos.InvoiceInputDto;
import Novi.Student.PurrB.Repositories.UserRepository;
import Novi.Student.PurrB.Services.AppointmentService;
import Novi.Student.PurrB.Services.InvoiceService;
import Novi.Student.PurrB.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("invoices")
public class InvoiceController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("")
    public ResponseEntity<List<InvoiceDto>> getInvoiceByAuth(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok().body(invoiceService.getInvoiceByAuth(authHeader));
    }

    @PostMapping("{clientId}")
    public ResponseEntity<InvoiceInputDto> postInvoice(@RequestBody InvoiceInputDto invoiceInput, @PathVariable("clientId") Long clientId){
        invoiceService.postInvoice(invoiceInput, clientId);

        return ResponseEntity.created(null).body(invoiceInput);
    }

    @PutMapping("{clientId}")
    public ResponseEntity<InvoiceDto> editInvoice(@RequestBody InvoiceDto invoiceInput, @PathVariable("clientId") Long clientId) {

        return ResponseEntity.ok(invoiceService.editInvoice(invoiceInput, clientId));
    }

    @DeleteMapping("{invoiceId}")
    public ResponseEntity<InvoiceDto> removeInvoice (@PathVariable("clientId") Long invoiceId){
        invoiceService.removeInvoice(invoiceId);

        return ResponseEntity.noContent().build();
    }


}
