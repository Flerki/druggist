package app.controllers;

import app.domain.model.Category;
import app.domain.model.User;
import app.mapper.CategoryDtoToCategoryMapper;
import app.mapper.CategoryToCategoryDto;
import app.services.AuthService;
import app.services.CategoryService;
import app.services.UserService;
import app.web.response.CategoryDto;
import app.web.response.IdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("user/{userId}/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryToCategoryDto categoryToCategoryDto;

    @Autowired
    CategoryDtoToCategoryMapper categoryDtoToCategoryMapper;

    @PostMapping
    @CrossOrigin
    public IdDto create(@PathVariable int userId, @RequestHeader String authorization, @RequestBody CategoryDto categoryDto){
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        Category category = categoryDtoToCategoryMapper.map(categoryDto);

        category.setOwner(user);
        Category saved = categoryService.createCategory(category);

        return new IdDto(saved.getId());
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
    public List<CategoryDto> getAll(@PathVariable int userId, @RequestHeader String authorization) {
        User user = userService.findById(userId);
        authService.checkAuthentication(user, authorization);
        return categoryService.findByUser(user)
                .stream()
                .map(categoryToCategoryDto::map)
                .collect(toList());
    }

}
