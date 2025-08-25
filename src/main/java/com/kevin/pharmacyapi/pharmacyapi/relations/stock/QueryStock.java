package com.kevin.pharmacyapi.pharmacyapi.relations.stock;

import java.math.BigDecimal;

public interface QueryStock {
    Long getId();

    String getName();

    String getDescription();

    BigDecimal getPrice();

    Integer getCurrent_stock();

}