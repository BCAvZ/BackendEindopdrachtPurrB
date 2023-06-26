package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Dtos.InvoiceDto;
import Novi.Student.PurrB.Dtos.InvoiceInputDto;
import Novi.Student.PurrB.Dtos.UserDto;
import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Models.Invoice;
import Novi.Student.PurrB.Models.Role;
import Novi.Student.PurrB.Models.User;
import Novi.Student.PurrB.Repositories.RoleRepository;
import Novi.Student.PurrB.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
    }

    public UserDto postUser(UserDto userDto){

        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        List<Role> userRoles = new ArrayList<>();
        for (String roleName : userDto.roles) {
            Optional<Role> or = roleRepos.findById(roleName);
            if(or.isPresent()){
                userRoles.add(or.get());
            } else {
                throw new RecordNotFoundException("Role is not found " + roleName);
            }
        }
        newUser.setRoles(userRoles);

        userRepos.save(newUser);

        return userDto;
    }
}
