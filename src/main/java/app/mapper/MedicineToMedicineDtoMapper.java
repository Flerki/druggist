package app.mapper;

import app.domain.MedicineCategoryRepository;
import app.domain.model.Medicine;
import app.domain.model.MedicineCategory;
import app.web.response.CategoryDto;
import app.web.response.MedicineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class MedicineToMedicineDtoMapper {

    private final CategoryToCategoryDto categoryToCategoryDto;

    private final MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    public MedicineToMedicineDtoMapper(CategoryToCategoryDto categoryToCategoryDto, MedicineCategoryRepository medicineCategoryRepository) {
        this.categoryToCategoryDto = categoryToCategoryDto;
        this.medicineCategoryRepository = medicineCategoryRepository;
    }

    public List<MedicineDto> map(List<Medicine> medicines) {
        return medicines.stream()
                .map(this::map)
                .collect(toList());
    }

    public MedicineDto map(Medicine medicine) {
        MedicineDto dto = new MedicineDto();
        dto.setId(medicine.getId());
        dto.setName(medicine.getName());
        dto.setDescription(medicine.getDescription());
        dto.setExpirationDate(medicine.getExpirationDate());

        List<CategoryDto> categoryDtos = medicineCategoryRepository.findByMedicineId(medicine.getId())
                .stream()
                .map(MedicineCategory::getCategory)
                .map(categoryToCategoryDto::map)
                .collect(Collectors.toList());

        dto.setCategories(categoryDtos);

        return dto;
    }
}
