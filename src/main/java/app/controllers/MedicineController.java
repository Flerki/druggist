package app.controllers;


import app.domain.MedicineCategoryRepository;
import app.domain.model.Category;
import app.domain.model.Medicine;
import app.domain.model.MedicineCategory;
import app.domain.model.User;
import app.mapper.CategoryDtoToCategoryMapper;
import app.mapper.MedicineDtoToMedicineMapper;
import app.mapper.MedicineToMedicineDtoMapper;
import app.services.AuthService;
import app.services.CategoryService;
import app.services.MedicineService;
import app.services.UserService;
import app.web.request.CreateMedicineDto;
import app.web.response.IdDto;
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

    @Autowired
    CategoryDtoToCategoryMapper categoryDtoToCategoryMapper;

    @Autowired
    MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    CategoryService categoryService;

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
    public IdDto save(@PathVariable int userId, @RequestHeader String authorization, @RequestBody CreateMedicineDto medicineDto) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);

        Medicine medicine = medicineDtoToMedicineMapper.map(medicineDto);
        medicine.setOwner(user);

        medicineService.save(medicine);


        List<Integer> categories = medicineDto.getCategories();
        if (categories != null){
            categories
                    .forEach(categoryId -> {
                        Category category = categoryService.findById(categoryId);
                        MedicineCategory medicineCategory = new MedicineCategory();
                        medicineCategory.setMedicine(medicine);
                        medicineCategory.setCategory(category);
                        medicineCategoryRepository.save(medicineCategory);
                    });
        }

        return new IdDto(medicine.getId());
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void delete(@PathVariable int userId, @PathVariable int id, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        medicineService.delete(id);
    }

    @PutMapping("/{medicineId}")
    @CrossOrigin
    public void update(@PathVariable int userId, @PathVariable int medicineId, @RequestBody CreateMedicineDto medicineDto, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);

        Medicine medicine = medicineDtoToMedicineMapper.map(medicineDto);
        medicine.setId(medicineId);
        medicine.setOwner(user);

        Medicine updated = medicineService.update(medicine);

        medicineCategoryRepository.findByMedicineId(medicine.getId())
                .forEach(medicineCategoryRepository::delete);

        List<Integer> categories = medicineDto.getCategories();
        if (categories != null){
            categories.forEach(categoryId -> {
                        Category category = categoryService.findById(categoryId);
                        MedicineCategory medicineCategory = new MedicineCategory();
                        medicineCategory.setMedicine(updated);
                        medicineCategory.setCategory(category);
                        medicineCategoryRepository.save(medicineCategory);
                    });
        }
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
