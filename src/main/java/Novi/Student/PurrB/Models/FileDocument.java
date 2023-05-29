package Novi.Student.PurrB.Models;

import jakarta.persistence.*;

@Entity
public class FileDocument {
    @Id
    @GeneratedValue
    private Long id;

    private String fileName;

    @Lob
    private byte[] docFile;

    @ManyToOne
    @JoinColumn(name="clients_id")
    private Client invoiceClient;

    public Client getInvoiceClient() {
        return invoiceClient;
    }

    public void setInvoiceClient(Client invoiceClient) {
        this.invoiceClient = invoiceClient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }
}
