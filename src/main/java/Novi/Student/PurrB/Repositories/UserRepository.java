package Novi.Student.PurrB.Repositories;

import org.springframework.data.repository.CrudRepository;
import Novi.Student.PurrB.Models.User;

public interface UserRepository extends CrudRepository<User, String> {
}
