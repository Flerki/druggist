package app.controllers;


import app.domain.model.Medicine;
import app.domain.model.User;
import app.mapper.MedicineToMedicineDtoMapper;
import app.services.AuthService;
import app.services.MedicineService;
import app.services.UserService;
import app.web.response.MedicineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("user/{userId}/meds")
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    MedicineToMedicineDtoMapper medicineToMedicineDtoMapper;

    @GetMapping
    @CrossOrigin
    public List<MedicineDto> getAll(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return medicineService.findByOwner(user)
                .stream()
                .map(medicineToMedicineDtoMapper::map)
                .collect(toList());
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
