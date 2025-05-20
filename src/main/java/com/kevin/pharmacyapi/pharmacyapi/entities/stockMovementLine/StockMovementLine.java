package com.kevin.pharmacyapi.pharmacyapi.entities.stockMovementLine;

import com.kevin.pharmacyapi.pharmacyapi.entities.product.Product;
import com.kevin.pharmacyapi.pharmacyapi.entities.stockMovement.StockMovement;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "stock_movement_line")
public class StockMovementLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "movement_id", nullable = false)
    private StockMovement movement;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = true)
    private LocalDate expiration_date;

    @Column(nullable = true)
    private String batch_number;
}