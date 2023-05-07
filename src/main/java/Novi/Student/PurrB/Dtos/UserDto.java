package Novi.Student.PurrB.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    @JsonProperty("username")
    public String username;

    public String password;

    public String[] roles;
}
