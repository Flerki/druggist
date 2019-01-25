package app.domain;

import app.domain.model.Category;
import app.domain.model.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory, Integer> {

    List<MedicineCategory> findByCategoryId(int categoryId);
    List<MedicineCategory> findByMedicineId(int medicineId);

}
