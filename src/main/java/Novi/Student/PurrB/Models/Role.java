package Novi.Student.PurrB.Models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role() {

    }
    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
