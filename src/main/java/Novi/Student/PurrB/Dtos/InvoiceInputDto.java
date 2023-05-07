package Novi.Student.PurrB.Dtos;

public class InvoiceInputDto {

    public String dueDate;

    public int amount;

    public boolean paid;

    public InvoiceInputDto() {
    }

    public InvoiceInputDto(String dueDate, int amount, boolean paid) {
        this.dueDate = dueDate;
        this.amount = amount;
        this.paid = paid;
    }
}
