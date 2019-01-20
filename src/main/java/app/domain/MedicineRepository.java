package app.domain;

import app.domain.model.Medicine;
import app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    @Query("select m from Medicine m where m.name = :name")
    List<Medicine> findByName(@Param("name") String name);

    @Query("select m from Medicine m where m.category =:category")
    List<Medicine> findByCategory(@Param("category") String category);

    @Query("select m from  Medicine m where m.expirationDate > CURRENT_DATE ")
    List<Medicine> findExpired();

    @Query("select m from  Medicine m where m.expirationDate < CURRENT_DATE ")
    List<Medicine> findActive();

    List<Medicine> findByOwner(User owner);
}
