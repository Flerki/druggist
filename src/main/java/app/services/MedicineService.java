package app.services;

import app.domain.model.Medecine;
import app.domain.MedicineRepository;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    @Autowired
    MedicineRepository medicineRepository;

    public void create(Medecine medecine) {
        if (medicineRepository.findById(medecine.getId()).isPresent()) {

        }
        medicineRepository.save(medecine);
    }

    public void delete(int id) {
        medicineRepository.deleteById(id);
    }

    public void update(Medecine medecine) {
        if (medicineRepository.findById(medecine.getId()).isPresent()) {
            medicineRepository.save(medecine);
        }
    }

    public List<Medecine> getAll() {
        return medicineRepository.findAll();
    }

    public List<Medecine> getExpired() {
        return medicineRepository.findExpired();
    }

    public List<Medecine> getActive() {
        return medicineRepository.findActive();
    }

    public Medecine getById(int id) {
        return medicineRepository.findById(id).get();
    }

    public List<Medecine> findByOwner(User owner) {
        return medicineRepository.findByOwner(owner);
    }
}
