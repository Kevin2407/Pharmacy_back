package com.kevin.pharmacyapi.pharmacyapi.relations.stock;

import com.kevin.pharmacyapi.pharmacyapi.entities.product.Product;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StockMovementLineDTO {
    private Product product;
    private Integer quantity;
    private String bachNumber;
    private LocalDate expirationDate;
}
