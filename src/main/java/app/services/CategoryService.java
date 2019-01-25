package app.services;

import app.domain.CategoryRepository;
import app.domain.model.Category;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findByUser(User user){
        return categoryRepository.findByOwner(user);
    }

    public Category findById(int id){
        return categoryRepository.getOne(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    public void updateCategory(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            categoryRepository.save(category);
        }
    }
}
