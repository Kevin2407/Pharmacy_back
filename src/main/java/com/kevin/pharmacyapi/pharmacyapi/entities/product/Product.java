package com.kevin.pharmacyapi.pharmacyapi.entities.product;
import com.kevin.pharmacyapi.pharmacyapi.config.audit.Auditable;
import com.kevin.pharmacyapi.pharmacyapi.entities.category.Category;
import jakarta.persistence.*;

import lombok.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "product")
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = true, length = 255)
    private String description;
    @Column(nullable = true, length = 100)
    private String image;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Column(nullable = false)
    private int current_stock;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType product_type;

}
