package com.IT.osahaneat.services.imp;

import com.IT.osahaneat.dto.CategoryDTO;
import com.IT.osahaneat.entity.Category;
import com.IT.osahaneat.payload.Response.CategoryResponse;

import java.util.List;

public interface CategoryServiceImp {
    Category createCategory(String nameCate);
    List<CategoryDTO> getCategoryHomePage();

    List<CategoryResponse> getAllCategory();

    CategoryDTO updateCategory(int id, String nameCate);
}
