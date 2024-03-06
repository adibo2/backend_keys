package com.example.backend_keys.promoCode;

import java.util.Date;

public record PromoCodeDto(String code, int amount, Date createDate,Date expirationDate) {
}
