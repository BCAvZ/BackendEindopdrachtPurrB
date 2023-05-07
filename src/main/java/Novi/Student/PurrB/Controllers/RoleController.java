package Novi.Student.PurrB.Controllers;

import Novi.Student.PurrB.Dtos.RoleDto;
import Novi.Student.PurrB.Models.Role;
import Novi.Student.PurrB.Repositories.RoleRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private final RoleRepository repos;

    public RoleController(RoleRepository repos) {
        this.repos = repos;
    }
    @PostMapping("/roles")
    public String createRole(@RequestBody RoleDto role) {
        Role newRole = new Role();
        newRole.setRoleName(role.roleName);
        repos.save(newRole);

        return "Done";
    }
}
