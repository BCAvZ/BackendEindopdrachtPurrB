package Novi.Student.PurrB.Repositories;

import Novi.Student.PurrB.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
