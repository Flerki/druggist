package app.mapper;

import app.domain.model.Medicine;
import app.web.response.MedicineDto;
import org.springframework.stereotype.Component;

@Component
public class MedicineDtoToMedicineMapper {
    public Medicine map(MedicineDto dto) {
        Medicine medicine = new Medicine();
        medicine.setId(dto.getId());
        medicine.setName(dto.getName());
        return medicine;
    }
}
