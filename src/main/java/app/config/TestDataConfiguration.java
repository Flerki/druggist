package app.config;

import app.domain.CategoryRepository;
import app.domain.UserRepository;
import app.domain.model.Category;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class TestDataConfiguration {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TestDataConfiguration(UserRepository userRepository,
                                 CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init(){
        User user = new User();
        user.setEmail("email");
        user.setLogin("login");
        user.setPassword("pass1");


        userRepository.save(user);


        for (int i = 0; i < 4; i++) {
            Category category = new Category();
            category.setName("category_" + i);
            category.setOwner(user);
            categoryRepository.save(category);
        }
    }
}
