package app.services;


import app.domain.model.User;

import java.util.List;

public interface UserService {
    void register(User user);

    List<User> getAll();

    User findByEmail(String email);

    User findByLogin(String login);

    User findById(int userId);
}
