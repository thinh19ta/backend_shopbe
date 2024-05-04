package com.bohaohan.shopbe.service;

import com.bohaohan.shopbe.dto.category.CategoryResponse;
import com.bohaohan.shopbe.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    
}
