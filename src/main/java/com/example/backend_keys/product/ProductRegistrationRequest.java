package com.example.backend_keys.product;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record ProductRegistrationRequest (
      String name,

     String slug,

     String Image,

     String alt,

     String meta,

     Integer stock,

     BigDecimal price,

     Integer Discount
      ){}
