package Novi.Student.PurrB.Services;
import Novi.Student.PurrB.Dtos.ClientDto;
import Novi.Student.PurrB.Dtos.ClientInputDto;
import Novi.Student.PurrB.Dtos.UserDto;
import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Models.Role;
import Novi.Student.PurrB.Models.User;
import Novi.Student.PurrB.Repositories.ClientRepository;
import Novi.Student.PurrB.Services.ClientService;
import Novi.Student.PurrB.Services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepos;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void postClient_InvalidAuthHeader_ThrowsException() {
        // Arrange
        String authHeader = "Bearer invalidToken";
        ClientInputDto inputDto = new ClientInputDto();

        // Act & Assert
        assertThrows(Exception.class, () -> clientService.postClient(authHeader, inputDto));
        verifyNoInteractions(jwtService);
        verifyNoInteractions(clientRepos);
    }



    @Test
    void editClient_InvalidAuthHeader_ThrowsException() {
        // Arrange
        String authHeader = "Bearer invalidToken";
        Long clientId = 1L;
        ClientInputDto newClientDto = new ClientInputDto();

        // Act & Assert
        assertThrows(Exception.class, () -> clientService.editClient(authHeader, clientId, newClientDto));
        verifyNoInteractions(jwtService);
        verifyNoInteractions(clientRepos);
    }




    @Test
    void removeClient_InvalidAuthHeader_ThrowsException() {
        // Arrange
        String authHeader = "Bearer invalidToken";
        Long clientId = 1L;

        // Act & Assert
        assertThrows(Exception.class, () -> clientService.removeClient(authHeader, clientId));
        verifyNoInteractions(jwtService);
        verifyNoInteractions(clientRepos);
    }



    @Test
    void changeToModel_ValidClientInputDto_ReturnsClient() {
        // Arrange
        ClientInputDto inputDto = new ClientInputDto();
        inputDto.name = "Test Client";
        inputDto.email = "test@example.com";
        inputDto.address = "Test Address";
        inputDto.phone = "1234567890";

        // Act
        Client result = clientService.changeToModel(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(inputDto.name, result.getName());
        assertEquals(inputDto.email, result.getEmail());
        assertEquals(inputDto.address, result.getAddress());
        assertEquals(inputDto.phone, result.getPhone());
    }

}