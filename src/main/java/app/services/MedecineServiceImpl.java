package app.services;

import app.domain.model.Medecine;
import app.domain.MedicineRepository;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecineServiceImpl implements MedecineService {
    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public void create(Medecine medecine) {
        if (medicineRepository.findById(medecine.getId()).isPresent()){

        }
        medicineRepository.save(medecine);
    }

    @Override
    public void delete(int id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public void update(Medecine medecine) {
        if(medicineRepository.findById(medecine.getId()).isPresent()) {
            medicineRepository.save(medecine);
        }
    }

    @Override
    public List<Medecine> getAll() {
        return medicineRepository.findAll();
    }



    @Override
    public List<Medecine> getExpired() {
        return medicineRepository.findExpired();
    }

    @Override
    public List<Medecine> getActive() {
        return  medicineRepository.findActive();
    }

    @Override
    public Medecine getById(int id) {
        return medicineRepository.findById(id).get();
    }

    @Override
    public List<Medecine> findByOwner(User owner) {
        return medicineRepository.findByOwner(owner);
    }
}
