package Novi.Student.PurrB.Helpers;

import Novi.Student.PurrB.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    @Autowired
    private JwtService jwtService;

    public String extractUsernameFromToken(String token) {
        String jwtToken = token.substring(7);

        JwtService jwtService = new JwtService();
        String username = jwtService.extractUsername(jwtToken);

        return username;
    }
}
