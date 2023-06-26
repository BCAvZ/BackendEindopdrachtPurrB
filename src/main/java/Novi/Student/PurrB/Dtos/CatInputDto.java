package Novi.Student.PurrB.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CatInputDto {

    @NotNull(message = "Name of your cat is required")
    public String name;

    @NotNull(message = "Date of birth of your cat is required")
    public String dob;

    @NotNull(message = "Race of your cat is required")
    public String race;

    @NotNull(message = "Age of your cat is required")
    @Min(value = 1, message = "Your cat must be atleast one year old we do not host kittens")
    public int age;

    @NotEmpty(message = "If your cat has no dietary preferences or needs please affirm this by typing X")
    public String diet;

    @NotEmpty(message = "If your cat has no medical needs or needs please affirm this by typing X")
    public String medicalDetails;

    public ClientDto clientDto;
}
