package Novi.Student.PurrB.Dtos;

import Novi.Student.PurrB.Models.User;

public class ClientInputDto {

    public String name;

    public String email;

    public String phone;

    public String address;

    public ClientInputDto() {
    }

    public ClientInputDto(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
