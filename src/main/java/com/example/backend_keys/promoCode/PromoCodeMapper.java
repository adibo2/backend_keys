package com.example.backend_keys.promoCode;

import java.util.function.Function;

public class PromoCodeMapper implements Function<PromoCode,PromoCodeDto> {

    public PromoCodeDto apply(PromoCode promoCode) {
        return new PromoCodeDto(
                promoCode.getCode(),
                promoCode.getAmount(),
                promoCode.getCreateDate(),
                promoCode.getExpirationDate()
                );
    }
}
