package app.services;

import app.domain.model.Medecine;

import java.util.List;

public interface MedecineService {
    void create(Medecine medecine);
    void delete(int id);
    void update(Medecine medecine);
    List<Medecine> getAll();
    List<Medecine> getExpired();
    List<Medecine> getActive();
    Medecine getById(int id);
}
