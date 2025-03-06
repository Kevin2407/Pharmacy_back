package com.kevin.pharmacyapi.pharmacyapi.product;
import com.kevin.pharmacyapi.pharmacyapi.product.category.Category;
import com.kevin.pharmacyapi.pharmacyapi.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "product")
public class Product {
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
    @Column(nullable = true)
    private LocalDateTime created_at;
    @Column(nullable = true)
    private LocalDateTime updated_at;
    @Column(nullable = true)
    private Date deleted_at;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User created_by;
    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    private User updated_by;
    @ManyToOne
    @JoinColumn(name = "deleted_by", nullable = true)
    private User deleted_by;


    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
        created_by = new User();
        created_by.setId(1L);
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
        updated_by = new User();
        updated_by.setId(1L);
    }

    @PreRemove
    protected void onDelete() {
        deleted_at = new Date();
        deleted_by = new User();
        deleted_by.setId(1L);
    }

}