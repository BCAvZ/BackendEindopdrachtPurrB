package Novi.Student.PurrB.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class UserDto {

    @JsonProperty("username")
    @NotNull(message = "There has to be a username")
    public String username;

    @NotNull(message = "There has to be a password")
    public String password;

    @NotNull(message = "There has to be a role")
    public String[] roles;
}
