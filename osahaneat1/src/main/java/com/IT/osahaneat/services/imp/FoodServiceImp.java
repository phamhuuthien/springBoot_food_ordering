package com.IT.osahaneat.services.imp;

import com.IT.osahaneat.dto.FoodDTO;
import com.IT.osahaneat.entity.Food;
import com.IT.osahaneat.payload.Request.FoodRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodServiceImp {
    List<FoodDTO> getAllFood();

    boolean updateFood(int id , FoodRequest foodRequest);
    boolean InsertFood(
             MultipartFile file,
            String title,
            String time_ship,
            boolean is_freeship,
            double price,
            int cate_id
    );
    boolean deleteFood(int id);
}
