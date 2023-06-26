package Novi.Student.PurrB.Repositories;

import org.springframework.data.repository.CrudRepository;
import Novi.Student.PurrB.Models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
