package app.services;


import app.domain.UserRepository;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findById(int userId) {
        return userRepository.findById(userId).get();
    }

}
