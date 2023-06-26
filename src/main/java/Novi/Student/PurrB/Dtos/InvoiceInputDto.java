package Novi.Student.PurrB.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InvoiceInputDto {

    @NotNull(message = "There has to be a due date")
    public String dueDate;

    @NotNull(message = "There has to be an amount in Euros")
    @Size(min = 0, max = 10000, message = "The amount has to be between 0 and 10000")
    public int amount;

    @NotNull(message = "Name of your cat is required")
    public boolean paid;
}
