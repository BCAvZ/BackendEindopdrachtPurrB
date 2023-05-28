package Novi.Student.PurrB.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue
    private long catId;

    private String name;

    private String dob;

    private String race;

    private int age;

    private String diet;

    private String medicalDetails;

    @ManyToOne
    @JoinColumn(name="clients_id")
    private Client owner;

    public Cat(Client owner) {
        this.owner = owner;
    }

    public Cat() {
    }

    public Cat(long catId, String name, String dob, String race, int age, String diet, String medicalDetails) {
        this.catId = catId;
        this.name = name;
        this.dob = dob;
        this.race = race;
        this.age = age;
        this.diet = diet;
        this.medicalDetails = medicalDetails;
    }

    public long getCatId() {
        return catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getMedicalDetails() {
        return medicalDetails;
    }

    public void setMedicalDetails(String medicalDetails) {
        this.medicalDetails = medicalDetails;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
}
