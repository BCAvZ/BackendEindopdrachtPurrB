package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Dtos.ClientDto;
import Novi.Student.PurrB.Dtos.ClientInputDto;
import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Models.User;
import Novi.Student.PurrB.Models.Role;
import Novi.Student.PurrB.Repositories.ClientRepository;
import Novi.Student.PurrB.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getClientByAuth_ExistingClient_ReturnsClientDto() {
        // Arrange
        String authHeader = "Bearer token";
        String username = "johnsmith";
        Long clientId = 1L;

        Client client = new Client();
        client.setClientId(clientId);
        client.setName("John Smith");
        client.setAddress("123 Main St");
        // Set other client properties

        User user = new User();
        user.setUsername(username);
        // Set other user properties

        client.setUser(user);

        when(clientRepository.findByUser_Username(username)).thenReturn(client);

        // Act
        ClientDto result = clientService.getClientByAuth(authHeader);

        // Assert
        assertNotNull(result);
        assertEquals(clientId, result.clientId);
        assertEquals("John Smith", result.name);
        assertEquals("123 Main St", result.address);
        // Assert other properties
    }

    @Test
    void getClientByAuth_NonExistingClient_ThrowsRuntimeException() {
        // Arrange
        String authHeader = "Bearer token";
        String username = "johnsmith";

        when(clientRepository.findByUser_Username(username)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> clientService.getClientByAuth(authHeader));
    }

    @Test
    void postClient_ValidInput_ReturnsClientDto() {
        // Arrange
        String authHeader = "Bearer token";
        String username = "johnsmith";
        Long clientId = 1L;

        ClientInputDto inputDto = new ClientInputDto();
        inputDto.name = "John Smith";
        inputDto.address = "123 Main St";
        // Set other input properties

        User user = new User();
        user.setUsername(username);
        // Set other user properties

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Client savedClient = new Client();
        savedClient.setClientId(clientId);
        savedClient.setName("John Smith");
        savedClient.setAddress("123 Main St");
        // Set other saved client properties

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        // Act
        ClientDto result = clientService.postClient(authHeader, inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(clientId, result.clientId);
        assertEquals("John Smith", result.name);
        assertEquals("123 Main St", result.address);
        // Assert other properties
    }

    @Test
    void editClient_ValidInputAndMatchingId_ReturnsUpdatedClientDto() {
        // Arrange
        String authHeader = "Bearer token";
        String username = "johnsmith";
        Long clientId = 1L;
        Long inputClientId = 1L;

        ClientInputDto inputDto = new ClientInputDto();
        inputDto.name = "John Smith";
        inputDto.address = "123 Main St";
        // Set other input properties

        User user = new User();
        user.setUsername(username);
        // Set other user properties

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Client existingClient = new Client();
        existingClient.setClientId(clientId);
        existingClient.setName("Old Name");
        existingClient.setAddress("Old Address");
        // Set other existing client properties

        when(clientRepository.findByUser_Username(username)).thenReturn(existingClient);

        Client updatedClient = new Client();
        updatedClient.setClientId(clientId);
        updatedClient.setName("John Smith");
        updatedClient.setAddress("123 Main St");
        // Set other updated client properties

        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        // Act
        ClientDto result = clientService.editClient(authHeader, inputClientId, inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(clientId, result.clientId);
        assertEquals("John Smith", result.name);
        assertEquals("123 Main St", result.address);
        // Assert other properties
    }

    @Test
    void editClient_ValidInputAndNonMatchingId_ThrowsRecordNotFoundException() {
        // Arrange
        String authHeader = "Bearer token";
        String username = "johnsmith";
        Long clientId = 1L;
        Long inputClientId = 2L;

        ClientInputDto inputDto = new ClientInputDto();
        inputDto.name = "John Smith";
        inputDto.address = "123 Main St";
        // Set other input properties

        User user = new User();
        user.setUsername(username);
        // Set other user properties

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Client existingClient = new Client();
        existingClient.setClientId(clientId);
        existingClient.setName("Old Name");
        existingClient.setAddress("Old Address");
        // Set other existing client properties

        when(clientRepository.findByUser_Username(username)).thenReturn(existingClient);

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> clientService.editClient(authHeader, inputClientId, inputDto));
    }

    @Test
    void removeClient_ValidInputAndMatchingId_DeletesClient() {
        // Arrange
        String authHeader = "Bearer token";
        String username = "johnsmith";
        Long clientId = 1L;
        Long inputClientId = 1L;

        User user = new User();
        user.setUsername(username);
        // Set other user properties

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Client existingClient = new Client();
        existingClient.setClientId(clientId);
        existingClient.setName("John Smith");
        existingClient.setAddress("123 Main St");
        // Set other existing client properties

        when(clientRepository.findByUser_Username(username)).thenReturn(existingClient);

        // Act
        clientService.removeClient(authHeader, inputClientId);

        // Assert
        verify(clientRepository, times(1)).deleteById(inputClientId);
    }

    @Test
    void removeClient_ValidInputAndNonMatchingId_ThrowsRecordNotFoundException() {
        // Arrange
        String authHeader = "Bearer token";
        String username = "johnsmith";
        Long clientId = 1L;
        Long inputClientId = 2L;

        User user = new User();
        user.setUsername(username);
        // Set other user properties

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Client existingClient = new Client();
        existingClient.setClientId(clientId);
        existingClient.setName("John Smith");
        existingClient.setAddress("123 Main St");
        // Set other existing client properties

        when(clientRepository.findByUser_Username(username)).thenReturn(existingClient);

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> clientService.removeClient(authHeader, inputClientId));
    }

    // Additional test methods can be added for other scenarios

}
