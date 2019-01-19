package app.controllers;

import app.domain.model.User;
import app.exceptions.UserWithSuchEmailExists;
import app.exceptions.UserWithSuchLoginExists;
import app.services.AuthService;
import app.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @PostMapping(path = "/registration",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registration(@RequestBody User user) {
        String email = user.getEmail();

        User foundByEmailUser = userService.findByEmail(email);
        if (foundByEmailUser != null) {
            throw new UserWithSuchEmailExists(email);
        }

        String login = user.getLogin();
        User foundByLoginUser = userService.findByLogin(login);
        if (foundByLoginUser != null) {
            throw new UserWithSuchLoginExists(login);
        }

        userService.registration(user);
    }

    @PostMapping("/login")
    public String login(String email, String password) throws JsonProcessingException {
        String jwt = authService.auth(email, password);
        Map<String, Object> obj = Collections.singletonMap("jwt", jwt);
        return mapper.writeValueAsString(obj);
    }

    @GetMapping("/all")
    public List<User> getAll() {
       return userService.getAll();
    }
}
