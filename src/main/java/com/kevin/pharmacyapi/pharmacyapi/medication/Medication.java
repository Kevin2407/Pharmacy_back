package com.kevin.pharmacyapi.pharmacyapi.medication;
import com.kevin.pharmacyapi.pharmacyapi.medication.prescriptionRequirement.PrescriptionRequirement;
import com.kevin.pharmacyapi.pharmacyapi.product.Product;
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
@Table(name = "medication")

public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String active_ingredient;
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @OneToOne
    @JoinColumn(name = "prescription_requirement_id", nullable = true)
    private PrescriptionRequirement prescriptionRequirement;
    @Column(nullable = true)
    private LocalDateTime created_at;
    @Column(nullable = true)
    private LocalDateTime updated_at;
    @Column(nullable = true)
    private LocalDateTime deleted_at;
    @Column(nullable = true)
    private String created_by;
    @Column(nullable = true)
    private String updated_by;
    @Column(nullable = true)
    private String deleted_by;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
        created_by = "default_user";
        updated_by = "default_user";
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
        updated_by = "default_user";
    }

    @PreRemove
    protected void onDelete() {
        deleted_at = LocalDateTime.now();
        deleted_by = "default_user";
    }
}
