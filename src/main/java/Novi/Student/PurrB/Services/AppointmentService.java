package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Dtos.*;
import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Helpers.JwtUtils;
import Novi.Student.PurrB.Models.Appointment;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Models.Role;
import Novi.Student.PurrB.Repositories.AppointmentRepository;
import Novi.Student.PurrB.Repositories.ClientRepository;
import Novi.Student.PurrB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepos;

    @Autowired
    private ClientRepository clientRepos;

    @Autowired
    private  JwtService jwtService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepos) {
        this.appointmentRepos = appointmentRepos;
    }

    public List<AppointmentDto> getAppointmentByAuth(@RequestHeader("Authorization") String authHeader){
        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        return convertAppointmentToDtos(c.getAppointments());
    }

    public AppointmentDto postAppointment(@RequestHeader("Authorization") String authHeader, AppointmentInputDto input){
        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        Appointment a = appointmentToModel(input);

        a.setClient(c);
        appointmentRepos.save(a);

        return appointmentToDto(a);
    }

    public AppointmentDto editAppointment (@RequestHeader("Authorization") String authHeader, Long id, AppointmentDto input){
        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        List<Appointment> appointments = c.getAppointments();

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == (id)) {

                appointment.setNotes(input.notes);
                appointment.setTime(input.time);
                appointment.setStartDate(input.startDate);
                appointment.setEndDate(input.endDate);

                appointmentRepos.save(appointment);

                return appointmentToDto(appointment);
            }

            else {
                throw new RecordNotFoundException("Wrong Appointment ID, check your appointment page for your ID.");
            }
        }
        throw new RecordNotFoundException("Wrong Appointment ID, check your appointment page for your ID.");
    }

    public void removeAppointment(@RequestHeader("Authorization") String authHeader, @RequestBody Long id) {
        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        List<Appointment> appointments = c.getAppointments();

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == (id)) {
                appointmentRepos.delete(appointment);
            } else {
                throw new RecordNotFoundException("Wrong Appointment ID, check your client/me page for your ID.");
            }
        }
    }

    public Appointment appointmentToModel(AppointmentInputDto appointmentInput){

        Appointment appointment = new Appointment();

        appointment.setNotes(appointmentInput.notes);
        appointment.setTime(appointmentInput.time);
        appointment.setStartDate(appointmentInput.startDate);
        appointment.setEndDate(appointmentInput.endDate);

        return appointment;
    }

    public AppointmentDto appointmentToDto(Appointment input){

        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.client = changeClientToClientDto(input.getClient());
        appointmentDto.time = input.getTime();
        appointmentDto.appointmentId = input.getAppointmentId();
        appointmentDto.startDate = input.getStartDate();
        appointmentDto.endDate = input.getEndDate();
        appointmentDto.notes = input.getNotes();

        return appointmentDto;
    }

    public List<AppointmentDto> convertAppointmentToDtos(List<Appointment> appointments) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.startDate = appointment.getStartDate();
            appointmentDto.endDate = appointment.getEndDate();
            appointmentDto.time = appointment.getTime();
            appointmentDto.notes = appointment.getNotes();
            appointmentDto.client = changeClientToClientDto(appointment.getClient());
            appointmentDto.appointmentId = appointment.getAppointmentId();

            appointmentDtos.add(appointmentDto);
        }
        return appointmentDtos;
    }

    public ClientDto changeClientToClientDto(Client c){

        ClientDto dto = new ClientDto();

        dto.clientId = c.getClientId();
        dto.address = c.getAddress();
        dto.name = c.getName();
        dto.email = c.getEmail();
        dto.phone = c.getPhone();

        UserDto userDto = new UserDto();
        userDto.username = c.getUser().getUsername();
        userDto.roles = c.getUser().getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet())
                .toArray(new String[0]);

        dto.user = userDto;

        return dto;
    }





}
