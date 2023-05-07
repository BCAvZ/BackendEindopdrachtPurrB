package Novi.Student.PurrB.Repositories;

import Novi.Student.PurrB.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByUser_Username(String username);
}
