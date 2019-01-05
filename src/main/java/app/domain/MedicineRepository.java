package app.domain;

import app.domain.model.Medecine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medecine, Integer> {
    @Query("select m from Medecine m where m.name = :name")
    List<Medecine> findByName(@Param("name") String name);

    @Query("select m from Medecine m where m.category =:category")
    List<Medecine> findByCategory(@Param("category") String category);

    @Query("select m from  Medecine m where m.expirationDate > CURRENT_DATE ")
    List<Medecine> findExpired();

    @Query("select m from  Medecine m where m.expirationDate < CURRENT_DATE ")
    List<Medecine> findActive();

}
