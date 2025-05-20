package com.kevin.pharmacyapi.pharmacyapi.relations.stock;

import java.util.Date;

public interface QueryStockIn {
    Long getProductId();
    void setProductId(Long productId);

    Integer getQuantity();
    void setQuantity(Integer quantity);

    String getBatchNumber();
    void setBatchNumber(String batchNumber);

    Date getExpirationDate();
    void setExpirationDate(Date expirationDate);
}