package app.controllers;


import app.domain.model.Medicine;
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
    @CrossOrigin
    public List<Medicine> getAll(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.findByOwner(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Medicine medicine, @PathVariable String userId) {
        medicineService.create(medicine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        medicineService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Medicine medicine, @PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        medicineService.update(medicine);
    }

    @GetMapping("/{id}")
    public Medicine getById(@PathVariable int id, @PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.getById(id);
    }

    @GetMapping("/expired")
    public List<Medicine> getExpired(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.getExpired();
    }

    @GetMapping("/active")
    public List<Medicine> getActive(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.getActive();
    }

}
