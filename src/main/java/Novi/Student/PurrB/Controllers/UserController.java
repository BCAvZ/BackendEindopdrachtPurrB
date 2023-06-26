package Novi.Student.PurrB.Controllers;

import Novi.Student.PurrB.Dtos.UserDto;
import Novi.Student.PurrB.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto) {
        userService.postUser(userDto);

        return "Welcome " + userDto.username + "! Your account is successfully created! You may now login!";
    }


}
