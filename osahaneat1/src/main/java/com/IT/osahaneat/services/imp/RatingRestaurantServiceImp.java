package com.IT.osahaneat.services.imp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface RatingRestaurantServiceImp {
    Boolean createRatingRes (HttpServletRequest request, int idRes, String content, double ratePoint);
}
