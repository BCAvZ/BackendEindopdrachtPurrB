package Novi.Student.PurrB.Controllers;

import Novi.Student.PurrB.Dtos.ClientDto;
import Novi.Student.PurrB.Dtos.ClientInputDto;
import Novi.Student.PurrB.Repositories.UserRepository;
import Novi.Student.PurrB.Services.ClientService;
import Novi.Student.PurrB.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("clients")
public class ClientController {

        @Autowired
        private JwtService jwtService;
        @Autowired
        private UserRepository userRepository;

        private final ClientService clientService;

        public ClientController(ClientService service) {
                this.clientService = service;
        }

        @GetMapping("/me")
        public ResponseEntity<ClientDto> getClientByAuth(@RequestHeader ("Authorization") String authHeader){
                return ResponseEntity.ok().body(clientService.getClientByAuth(authHeader));
        }

        @PostMapping("")
        public ResponseEntity<ClientInputDto> postClient(@RequestHeader ("Authorization") String authHeader, @RequestBody ClientInputDto c){
                clientService.postClient(authHeader, c);
                return ResponseEntity.created(null).body(c);
        }

        @PutMapping("{id}")
        public ResponseEntity<ClientDto> editClient(@RequestHeader ("Authorization") String authHeader, @PathVariable Long id, @RequestBody ClientInputDto c){
                return ResponseEntity.ok(clientService.editClient(authHeader, id, c));
        }

        @DeleteMapping("")
        public ResponseEntity<ClientDto> deleteClient(@RequestHeader ("Authorization") String authHeader, Long id){
                clientService.removeClient(authHeader, id);
                return ResponseEntity.noContent().build();
        }

}
