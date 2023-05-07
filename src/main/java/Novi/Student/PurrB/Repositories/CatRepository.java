package Novi.Student.PurrB.Repositories;

import Novi.Student.PurrB.Models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository <Cat, Long> {
}
