package app.controllers;


import app.domain.model.Medicine;
import app.domain.model.User;
import app.mapper.MedicineDtoToMedicineMapper;
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

    @Autowired
    MedicineDtoToMedicineMapper medicineDtoToMedicineMapper;

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
    @CrossOrigin
    public MedicineDto save(@PathVariable int userId, @RequestHeader String authorization, @RequestBody MedicineDto medicineDto) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);

        Medicine medicine = medicineDtoToMedicineMapper.map(medicineDto);
        medicine.setOwner(user);

        medicineService.save(medicine);

        return medicineToMedicineDtoMapper.map(medicine);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void delete(@PathVariable int userId, @PathVariable int id, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        medicineService.delete(id);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public void update(@PathVariable int userId, @RequestBody MedicineDto medicineDto, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);

        Medicine medicine = medicineDtoToMedicineMapper.map(medicineDto);
        medicine.setOwner(user);

        medicineService.update(medicine);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public MedicineDto getById(@PathVariable int userId, @PathVariable int id, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);

        Medicine medicine = medicineService.getById(id);

        return medicineToMedicineDtoMapper.map(medicine);
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
