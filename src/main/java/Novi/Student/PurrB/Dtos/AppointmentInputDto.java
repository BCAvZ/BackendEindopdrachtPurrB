package Novi.Student.PurrB.Dtos;
import jakarta.validation.constraints.*;


public class AppointmentInputDto {

    @NotNull(message = "Startdate of your appointment is required")
    public String startDate;

    @NotNull(message = "Enddate of your appointment is required")
    public String endDate;

    @NotNull(message = "Time is required")
    @Size(min = 5, message = "Time must be formatted digitally for example: 01:30")
    public String time;

    @NotEmpty(message = "If you have no notes please affirm this by typing an X in the field")
    public String notes;
}
