package app.services;

import app.domain.MedicineRepository;
import app.domain.model.Medicine;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    MedicineRepository medicineRepository;

    @Transactional
    public void save(Medicine medicine) {
        if (medicineRepository.findById(medicine.getId()).isPresent()) {

        }
        medicineRepository.save(medicine);
    }

    public void delete(int id) {
        medicineRepository.deleteById(id);
    }

    public Medicine update(Medicine medicine) {
            return medicineRepository.save(medicine);
    }

    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    public List<Medicine> getExpired() {
        return medicineRepository.findExpired();
    }

    public List<Medicine> getActive() {
        return medicineRepository.findActive();
    }

    public Medicine getById(int id) {
        return medicineRepository.findById(id).get();
    }

    public List<Medicine> findByOwner(User owner) {
        return medicineRepository.findByOwner(owner);
    }
}
