package Novi.Student.PurrB.Dtos;

import Novi.Student.PurrB.Models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDto {

    public Long clientId;

    public String name;

    public String email;

    public String phone;

    public String address;

    @JsonProperty("user")
    public UserDto user;

    public ClientDto() {
    }

    public ClientDto(Long clientId, String name, String email, String phone, String address, UserDto user) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.user = user;
    }
}
