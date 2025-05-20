package com.kevin.pharmacyapi.pharmacyapi.relations.stock;

public interface QueryStock {
    Long getProductId();

    String getProductName();

    String getProductDescription();

    Integer getStock();

}