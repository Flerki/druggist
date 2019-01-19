package app.controllers;

import app.domain.model.User;
import app.exceptions.UserWithSuchEmailExists;
import app.exceptions.UserWithSuchLoginExists;
import app.services.AuthService;
import app.services.UserService;
import app.web.request.AuthenticateRequest;
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
    @CrossOrigin
    public String registration(@RequestBody User user) throws JsonProcessingException {
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

        userService.register(user);

        String jwt = authService.auth(email, user.getPassword());
        Map<String, Object> obj = Collections.singletonMap("jwt", jwt);
        return mapper.writeValueAsString(obj);
    }

    @PostMapping("/login")
    @CrossOrigin
    public String login(@RequestBody AuthenticateRequest request) throws JsonProcessingException {
        String jwt = authService.auth(request.getEmail(), request.getPassword());
        Map<String, Object> obj = Collections.singletonMap("jwt", jwt);
        return mapper.writeValueAsString(obj);
    }

    @GetMapping("/all")
    public List<User> getAll() {
       return userService.getAll();
    }
}
