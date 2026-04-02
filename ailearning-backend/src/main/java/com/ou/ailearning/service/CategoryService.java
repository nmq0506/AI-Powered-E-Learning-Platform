package com.ou.ailearning.service;

import com.ou.ailearning.dto.request.CategoryRequest;
import com.ou.ailearning.dto.response.CategoryResponse;
import com.ou.ailearning.entity.Category;
import com.ou.ailearning.exeption.ResourceNotFoundException;
import com.ou.ailearning.repository.CategoryRepository;
import com.ou.ailearning.service.mapper.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponse create(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.name())
                .build();
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    public Page<CategoryResponse> findAll(String search, Pageable pageable) {
        Page<Category> cates = categoryRepository.findBySearch(search, pageable);

        return cates.map(categoryMapper::toResponse);
    }

    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));

        return categoryMapper.toResponse(category);
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));

        if (request.name() != null) {
            category.setName(request.name());
        }

        Category updated = categoryRepository.save(category);
        return categoryMapper.toResponse(updated);
    }
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));

        category.setActive(false);
        categoryRepository.save(category);
    }
    public void restore(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));

        category.setActive(true);
        categoryRepository.save(category);
    }
}
