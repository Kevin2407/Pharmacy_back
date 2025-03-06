package com.kevin.pharmacyapi.pharmacyapi.stockMovement.sale;

import com.kevin.pharmacyapi.pharmacyapi.stockMovement.StockMovement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String payment_method;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total_price;
}