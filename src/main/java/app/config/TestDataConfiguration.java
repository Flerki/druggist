package app.config;

import app.domain.UserRepository;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Configuration
public class TestDataConfiguration {

    private final UserRepository userRepository;

    @Autowired
    public TestDataConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        User user = new User();
        user.setEmail("email");
        user.setLogin("login");
        user.setPassword("pass1");
        userRepository.save(user);
    }
}
