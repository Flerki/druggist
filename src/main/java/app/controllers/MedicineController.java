package app.controllers;


import app.domain.model.Medecine;
import app.domain.model.User;
import app.services.AuthService;
import app.services.MedicineService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("user/{userId}/meds")
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @GetMapping
    public List<Medecine> getAll(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.findByOwner(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Medecine medecine, @PathVariable String userId) {
        medicineService.create(medecine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        medicineService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Medecine medecine, @PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        medicineService.update(medecine);
    }

    @GetMapping("/{id}")
    public Medecine getById(@PathVariable int id, @PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.getById(id);
    }

    @GetMapping("/expired")
    public List<Medecine> getExpired(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.getExpired();
    }

    @GetMapping("/active")
    public List<Medecine> getActive(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.getActive();
    }

}
