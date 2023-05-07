package Novi.Student.PurrB.Dtos;

public class InvoiceDto {


    public long invoidId;

    public String dueDate;

    public int amount;

    public boolean paid;

    public InvoiceDto() {
    }

    public InvoiceDto(long invoidId, String dueDate, int amount, boolean paid) {
        this.invoidId = invoidId;
        this.dueDate = dueDate;
        this.amount = amount;
        this.paid = paid;
    }
}
