package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Dtos.ClientDto;
import Novi.Student.PurrB.Dtos.ClientInputDto;
import Novi.Student.PurrB.Dtos.UserDto;
import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Models.User;
import Novi.Student.PurrB.Models.Role;
import Novi.Student.PurrB.Repositories.ClientRepository;
import Novi.Student.PurrB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clientRepos;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public ClientService(ClientRepository clientRepos) {
        this.clientRepos = clientRepos;
    }

    public ClientDto getClientByAuth(@RequestHeader("Authorization") String authHeader) {
        String jwtToken = authHeader.substring(7);
        String username = jwtService.extractUsername(jwtToken);

        ClientDto returnValue = changeToDto(clientRepos.findByUser_Username(username));

        returnValue.user.password = "*********";

        return returnValue;
    }


    public ClientDto postClient(@RequestHeader("Authorization") String authHeader, ClientInputDto input){

        String jwtToken = authHeader.substring(7);
        String username = jwtService.extractUsername(jwtToken);

        Client c = changeToModel(input);
        User u = jwtService.getUserByUsername(username);

        c.setUser(u);

        clientRepos.save(c);

        return changeToDto(c);
    }

    public ClientDto editClient(@RequestHeader ("Authorization") String authHeader, Long id, ClientInputDto newClient){

        String jwtToken = authHeader.substring(7);
        String username = jwtService.extractUsername(jwtToken);

        User u = jwtService.getUserByUsername(username);
        Client c = clientRepos.findByUser_Username(username);

        if (Objects.equals(c.getClientId(), id)){
            c.setAddress(newClient.address);
            c.setName(newClient.name);
            c.setPhone(newClient.phone);
            c.setPhone(newClient.phone);

            clientRepos.save(c);
            return changeToDto(c);
        }
        else {
            throw new RecordNotFoundException("Wrong Client ID, check your client/me page for your ID.");
        }
    }

    public void removeClient(@RequestHeader ("Authorization") String authHeader, Long id){
        String jwtToken = authHeader.substring(7);
        String username = jwtService.extractUsername(jwtToken);

        User u = jwtService.getUserByUsername(username);
        Client c = clientRepos.findByUser_Username(username);

        if (Objects.equals(c.getClientId(), id)) {
            clientRepos.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("Wrong Client ID, check your client/me page for your ID.");
        }

    }

    public Client changeToModel(ClientInputDto dto){

        Client c = new Client();

        c.setPhone(dto.phone);
        c.setName(dto.name);
        c.setEmail(dto.email);
        c.setAddress(dto.address);

        return c;
    }


    public ClientDto changeToDto(Client c){

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
