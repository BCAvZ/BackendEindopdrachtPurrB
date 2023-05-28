package Novi.Student.PurrB.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ClientDto {

    public Long clientId;

    public String name;

    public String email;

    public String phone;

    public String address;

    @JsonProperty("user")
    public UserDto user;

    private List<AppointmentDto> appointments;

    public List<AppointmentDto> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDto> appointments) {
        this.appointments = appointments;
    }

    private List<CatDto> cats;

    public List<CatDto> getCats() {
        return cats;
    }

    public void setCats(List<CatDto> cats) {
        this.cats = cats;
    }
}
