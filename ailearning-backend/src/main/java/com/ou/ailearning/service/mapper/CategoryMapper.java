package com.ou.ailearning.service.mapper;

import com.ou.ailearning.dto.response.CategoryResponse;
import com.ou.ailearning.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse toResponse(Category category) {
        if (category == null) return null;

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCreatedAt()
        );
    }
}
