package app.mapper;

import app.domain.model.Category;
import app.web.response.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDto {
    public CategoryDto map(Category category){
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
