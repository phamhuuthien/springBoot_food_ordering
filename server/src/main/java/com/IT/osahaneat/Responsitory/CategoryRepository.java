package com.IT.osahaneat.Responsitory;

import com.IT.osahaneat.dto.CategoryDTO;
import com.IT.osahaneat.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findCategoryByNameCate(String nameCate);

    Category findCategoryById(int id);
}
