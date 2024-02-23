package com.IT.osahaneat.services.imp;

import com.IT.osahaneat.dto.PromoDTO;
import com.IT.osahaneat.payload.Request.PromoRequest;

import java.util.List;

public interface PromoServiceImp {
    Boolean createPromo(PromoRequest promoRequest);

    List<PromoDTO> autoDeletePromoExpires();
    List<PromoDTO> getAll();

}
