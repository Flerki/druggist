package app.config;

import app.domain.CategoryRepository;
import app.domain.MedicineCategoryRepository;
import app.domain.MedicineRepository;
import app.domain.UserRepository;
import app.domain.model.Category;
import app.domain.model.Medicine;
import app.domain.model.MedicineCategory;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TestDataConfiguration {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final MedicineRepository medicineRepository;
    private final MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    public TestDataConfiguration(UserRepository userRepository,
                                 CategoryRepository categoryRepository,
                                 MedicineRepository medicineRepository, MedicineCategoryRepository medicineCategoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.medicineRepository = medicineRepository;
        this.medicineCategoryRepository = medicineCategoryRepository;
    }

    @PostConstruct
    public void init() {
        User user = new User();
        user.setEmail("email");
        user.setLogin("login");
        user.setPassword("pass1");


        userRepository.save(user);


        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Category category = new Category();
            category.setName("category_" + i);
            category.setOwner(user);
            category.setDescription(i % 2 == 0 ? null : ("desc_" + i));
            categoryRepository.save(category);
            categories.add(category);
        }

        List<Medicine> medicines = new ArrayList<>();
        for (int medicineId = 0; medicineId < 4; medicineId++) {
            Medicine medicine = new Medicine();
            medicine.setName("medicine_" + medicineId);
            medicine.setOwner(user);
            medicine.setExpirationDate("0" + medicineId + "1.02.2018");
            medicine.setDescription(medicineId % 2 == 0 ? null : ("desc_" + medicineId));

            medicineRepository.save(medicine);
            medicines.add(medicine);


            for (int categoryId = 0; categoryId < 2; categoryId++) {
                MedicineCategory medicineCategory = new MedicineCategory();
                medicineCategory.setCategory(categories.get(categoryId));
                medicineCategory.setMedicine(medicine);
                medicineCategoryRepository.save(medicineCategory);
            }
        }
    }
}
