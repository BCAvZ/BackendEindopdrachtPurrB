package Novi.Student.PurrB.Dtos;

public class AppointmentDto {

    private long appointmentId;
    public String startDate;

    public String endDate;

    public String time;

    public String notes;

    public AppointmentDto() {
    }

    public AppointmentDto(long appointmentId, String startDate, String endDate, String time, String notes) {
        this.appointmentId = appointmentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.notes = notes;
    }
}
