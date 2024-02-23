package com.IT.osahaneat.services;

import com.IT.osahaneat.Responsitory.CategoryRepository;
import com.IT.osahaneat.Responsitory.FoodRepository;
import com.IT.osahaneat.dto.CategoryDTO;
import com.IT.osahaneat.dto.MenuDTO;
import com.IT.osahaneat.entity.Category;
import com.IT.osahaneat.entity.Food;
import com.IT.osahaneat.payload.Response.CategoryResponse;
import com.IT.osahaneat.services.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public Category createCategory(String nameCate) {
        Category category = new Category();
        if(categoryRepository.findCategoryByNameCate(nameCate)==null){
            category.setNameCate(nameCate);
            categoryRepository.save(category);
        }
        return category;
    }

    @Cacheable("getCategoryHomePage")
    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0,6, Sort.by("id"));

        Page<Category> listCategories = categoryRepository.findAll(pageRequest);

        List<CategoryDTO> listCategoryDTOs = new ArrayList<>();
        for(Category data : listCategories){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(data.getId());
            categoryDTO.setNameCate(data.getNameCate());

            List<MenuDTO> menuDTOs = new ArrayList<>();

            for(Food food : data.getListFood()){
                MenuDTO menuDTO = new MenuDTO();
                menuDTO.setImage(food.getImage());
                menuDTO.setTitle(food.getTitle());
                menuDTO.setFreeship(food.getIsFreeship());
                menuDTOs.add(menuDTO);
            }

            categoryDTO.setMenu(menuDTOs);
            listCategoryDTOs.add(categoryDTO);
        }

        return listCategoryDTOs;
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        PageRequest pageRequest = PageRequest.of(0,6, Sort.by("id"));
        Page<Category> listCategories = categoryRepository.findAll(pageRequest);
        List<CategoryResponse> listCategoriesResponse = new ArrayList<>();
        for(Category category : listCategories){
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setNameCate(category.getNameCate());
            listCategoriesResponse.add(categoryResponse);
        }
        return listCategoriesResponse;
    }

    @Override
    public CategoryDTO updateCategory(int id, String nameCate) {
        Category category = categoryRepository.findCategoryById(id);
        if(category!=null){
            category.setNameCate(nameCate);
            categoryRepository.save(category);
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNameCate(category.getNameCate());
        return categoryDTO;
    }
}
