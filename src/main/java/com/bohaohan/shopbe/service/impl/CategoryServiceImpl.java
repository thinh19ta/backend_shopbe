package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.dto.category.CategoryResponse;
import com.bohaohan.shopbe.entity.Category;
import com.bohaohan.shopbe.repository.CategoryRepository;
import com.bohaohan.shopbe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream().map(
                category -> new CategoryResponse(category.getId(), category.getName())
        ).toList();
        return categoryResponses;
    }
}
