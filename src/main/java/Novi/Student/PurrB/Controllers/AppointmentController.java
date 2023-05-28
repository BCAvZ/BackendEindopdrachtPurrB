package Novi.Student.PurrB.Controllers;

import Novi.Student.PurrB.Dtos.AppointmentDto;
import Novi.Student.PurrB.Dtos.AppointmentInputDto;
import Novi.Student.PurrB.Repositories.UserRepository;
import Novi.Student.PurrB.Services.AppointmentService;
import Novi.Student.PurrB.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByAuth(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok().body(appointmentService.getAppointmentByAuth(authHeader));
    }

    @PostMapping("")
    public ResponseEntity<AppointmentInputDto> postAppointment(@RequestHeader("Authorization") String authHeader, @RequestBody AppointmentInputDto appointmentInput){
        appointmentService.postAppointment(authHeader, appointmentInput);

        return ResponseEntity.created(null).body(appointmentInput);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppointmentDto> editAppointment(@RequestHeader("Authorization") String autHeader, @PathVariable Long id, @RequestBody AppointmentDto appointmentInput) {

        return ResponseEntity.ok(appointmentService.editAppointment(autHeader, id, appointmentInput));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AppointmentDto> removeAppointment (@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        appointmentService.removeAppointment(authHeader, id);

        return ResponseEntity.noContent().build();
    }

}
