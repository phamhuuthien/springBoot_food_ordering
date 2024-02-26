package com.IT.osahaneat.services.imp;

import jakarta.servlet.http.HttpServletRequest;

public interface RatingFoodServiceImp {
    Boolean createRatingFood (HttpServletRequest request, int idFood, String content, double ratePoint);
}
