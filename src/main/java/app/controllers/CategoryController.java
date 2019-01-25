package app.controllers;

import app.domain.model.Category;
import app.domain.model.User;
import app.services.AuthService;
import app.services.CategoryService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/{userId}/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping
    public void create(@RequestBody Category category, @PathVariable String userId){
        categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, @PathVariable int userId, @RequestHeader String auhorization){
        categoryService.deleteCategory(id);
    }
    @PutMapping("/update/{id}")
    public void update(@RequestBody Category category, @PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        categoryService.updateCategory(category);
    }
    @GetMapping
    @CrossOrigin
    public List<Category> getAll(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return categoryService.getAll();
    }

}
