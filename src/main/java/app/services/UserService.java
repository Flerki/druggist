package app.services;


import app.domain.model.User;



public interface UserService {
    void registration(User user);

    void getAll();

    User findByEmail(String email);

    User findByLogin(String login);

    User findById(int userId);
}
