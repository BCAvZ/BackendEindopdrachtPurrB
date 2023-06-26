package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Dtos.UserDto;
import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Models.Role;
import Novi.Student.PurrB.Models.User;
import Novi.Student.PurrB.Repositories.RoleRepository;
import Novi.Student.PurrB.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepos;
    @Mock
    private RoleRepository roleRepos;
    @Mock
    private PasswordEncoder encoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepos, roleRepos, encoder);
    }

    @Test
    void postUser_ValidInput_SuccessfullyCreatesUser() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.username = "testUser";
        userDto.password = "testPassword";
        userDto.roles = new String[]{"ROLE_ADMIN", "ROLE_USER"};

        when(roleRepos.findById("ROLE_ADMIN")).thenReturn(Optional.of(new Role("ROLE_ADMIN")));
        when(roleRepos.findById("ROLE_USER")).thenReturn(Optional.of(new Role("ROLE_USER")));
        when(encoder.encode("testPassword")).thenReturn("encodedPassword");
        when(userRepos.save(any(User.class))).thenReturn(new User());

        // Act
        UserDto result = userService.postUser(userDto);

        // Assert
        assertEquals(userDto, result);
        verify(roleRepos, times(2)).findById(anyString());
        verify(encoder).encode("testPassword");
        verify(userRepos).save(any(User.class));
    }
}