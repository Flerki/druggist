package app.mapper;

import app.domain.model.Medicine;
import app.web.response.MedicineDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class MedicineToMedicineDtoMapper {

    public List<MedicineDto> map(List<Medicine> medicines) {
        return medicines.stream()
                .map(this::map)
                .collect(toList());
    }

    public MedicineDto map(Medicine medicine) {
        MedicineDto dto = new MedicineDto();
        dto.setId(medicine.getId());
        dto.setName(medicine.getName());
        return dto;
    }
}
