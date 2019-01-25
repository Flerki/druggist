package app.domain;

import app.domain.model.Category;
import app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.name = :name")
    Category findByName(@Param("name") String name);

    List<Category> findByOwner(User owner);
}
