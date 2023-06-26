package Novi.Student.PurrB.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class ClientInputDto {

    @Size(min = 2, max = 20, message = "The name of your cat must be between 2 and 20 characters long")
    public String name;

    @Email(message = "Your email must include a @")
    public String email;

    public String phone;

    public String address;
}
