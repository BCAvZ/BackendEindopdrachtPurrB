package Novi.Student.PurrB.Dtos;

public class CatInputDto {
    public String name;

    public String dob;

    public String race;

    public int age;

    public String diet;

    public String medicalDetails;

    public CatInputDto() {
    }

    public CatInputDto(String name, String dob, String race, int age, String diet, String medicalDetails) {
        this.name = name;
        this.dob = dob;
        this.race = race;
        this.age = age;
        this.diet = diet;
        this.medicalDetails = medicalDetails;
    }
}
