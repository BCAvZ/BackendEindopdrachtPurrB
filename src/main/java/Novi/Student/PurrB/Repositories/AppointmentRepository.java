package Novi.Student.PurrB.Repositories;

import Novi.Student.PurrB.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository <Appointment, Long> {
}
