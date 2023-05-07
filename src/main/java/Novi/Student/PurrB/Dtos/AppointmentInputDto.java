package Novi.Student.PurrB.Dtos;

public class AppointmentInputDto {

    public String startDate;

    public String endDate;

    public String time;

    public String notes;

    public AppointmentInputDto() {
    }

    public AppointmentInputDto(String startDate, String endDate, String time, String notes) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.notes = notes;
    }
}
