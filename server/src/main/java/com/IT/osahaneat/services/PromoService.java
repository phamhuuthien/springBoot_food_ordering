package com.IT.osahaneat.services;

import com.IT.osahaneat.Responsitory.PromoRepository;
import com.IT.osahaneat.dto.PromoDTO;
import com.IT.osahaneat.entity.Promo;
import com.IT.osahaneat.entity.Restaurant;
import com.IT.osahaneat.payload.Request.PromoRequest;
import com.IT.osahaneat.services.imp.PromoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromoService implements PromoServiceImp {

    @Autowired
    PromoRepository promoRepository;
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

    @Override
    public List<PromoDTO> getAll() {
        List<Promo> listPromo = promoRepository.findAll();
        List<PromoDTO> listPromoDTOS = new ArrayList<>();
        for(Promo promo : listPromo){
                PromoDTO promoDTO = new PromoDTO(promo.getRestaurant().getId(),promo.getStartDate(),promo.getEndDate(),promo.getPercent());
                listPromoDTOS.add(promoDTO);
        }
        return listPromoDTOS;
    }
}
