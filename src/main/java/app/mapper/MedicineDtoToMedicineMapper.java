package app.mapper;

import app.domain.MedicineCategoryRepository;
import app.domain.model.Medicine;
import app.domain.model.User;
import app.web.request.CreateMedicineDto;
import app.web.response.MedicineDto;
import org.springframework.stereotype.Component;

@Component
public class MedicineDtoToMedicineMapper {

    private final CategoryDtoToCategoryMapper categoryDtoToCategoryMapper;

    private final MedicineCategoryRepository medicineCategoryRepository;

    public MedicineDtoToMedicineMapper(CategoryDtoToCategoryMapper categoryDtoToCategoryMapper, MedicineCategoryRepository medicineCategoryRepository) {
        this.categoryDtoToCategoryMapper = categoryDtoToCategoryMapper;
        this.medicineCategoryRepository = medicineCategoryRepository;
    }

    public Medicine map(MedicineDto dto, User owner) {
        Medicine medicine = new Medicine();
        medicine.setId(dto.getId());
        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
        medicine.setExpirationDate(dto.getExpirationDate());
        medicine.setOwner(owner);

        return medicine;
    }

    public Medicine map(MedicineDto dto) {
        Medicine medicine = new Medicine();
        medicine.setId(dto.getId());
        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
        medicine.setExpirationDate(dto.getExpirationDate());
        return medicine;
    }

    public Medicine map(CreateMedicineDto dto) {
        Medicine medicine = new Medicine();
        medicine.setId(dto.getId());
        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
        medicine.setExpirationDate(dto.getExpirationDate());
        return medicine;
    }
}
