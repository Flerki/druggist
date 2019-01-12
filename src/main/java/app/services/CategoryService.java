package app.services;

import app.domain.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryByName(String name);

    List<Category> getAll();

    void createCategory(Category category);

    void deleteCategory(int id);

    void updateCategory(Category category);
}
