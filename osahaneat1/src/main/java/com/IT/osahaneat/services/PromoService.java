package com.IT.osahaneat.services;

import com.IT.osahaneat.Responsitory.PromoRepository;
import com.IT.osahaneat.dto.CategoryDTO;
import com.IT.osahaneat.dto.PromoDTO;
import com.IT.osahaneat.entity.Promo;
import com.IT.osahaneat.entity.Restaurant;
import com.IT.osahaneat.payload.Request.PromoRequest;
import com.IT.osahaneat.services.imp.PromoServiceImp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromoService implements PromoServiceImp {

    @Autowired
    PromoRepository promoRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private Gson gson = new GsonBuilder().serializeNulls().create();
    @Override
    public Boolean createPromo(PromoRequest promoRequest) {
        Promo promo = new Promo();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(promoRequest.getResId());
        promo.setRestaurant(restaurant);
        promo.setStartDate(promoRequest.getStartDate());
        promo.setEndDate(promoRequest.getEndDate());
        promo.setPercent(promoRequest.getPercent());
        promoRepository.save(promo);
        return true;
    }

    @Override
    public List<PromoDTO> autoDeletePromoExpires() {
        List<Promo> listPromo = promoRepository.findAll();
        List<PromoDTO> listPromoDTOS = new ArrayList<>();
        for(Promo promo : listPromo){
            long diffInMillies = Math.abs(promo.getEndDate().getTime() - promo.getStartDate().getTime());
            long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
            if(diffInDays<=0){
                PromoDTO promoDTO = new PromoDTO(promo.getRestaurant().getId(),promo.getStartDate(),promo.getEndDate(),promo.getPercent());
                listPromoDTOS.add(promoDTO);
                promoRepository.delete(promo);
            }
        }
        return listPromoDTOS;
    }

//    @Cacheable("getAllPromo")
    @Override
    public List<PromoDTO> getAll() {
        List<Promo> listPromo = promoRepository.findAll();
        List<PromoDTO> listPromoDTOS = new ArrayList<>();
        String redisData = (String) redisTemplate.opsForValue().get("promoAll");
        if(redisData == null){
            for(Promo promo : listPromo){
                PromoDTO promoDTO = new PromoDTO(promo.getRestaurant().getId(),promo.getStartDate(),promo.getEndDate(),promo.getPercent());
                listPromoDTOS.add(promoDTO);
            }
            String dataJson = gson.toJson(listPromoDTOS);

            redisTemplate.opsForValue().set("promoAll",dataJson);
        }else{
            Type listType = new TypeToken<List<CategoryDTO>>() {}.getType();
            listPromoDTOS= gson.fromJson(redisData,listType);
        }
        return listPromoDTOS;
    }
}
