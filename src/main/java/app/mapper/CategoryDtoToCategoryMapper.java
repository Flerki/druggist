package app.mapper;

import app.domain.model.Category;
import app.web.response.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategoryMapper {

    public Category map(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
