package app.domain;

import app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select m from User m where m.email = :email")
    User getByEmail(@Param("email") String email);

    @Query("select m from User m where m.id = :id")
    User getById(@Param("id") int id);

    User findByEmail(String email);

    User findByLogin(String login);

}
