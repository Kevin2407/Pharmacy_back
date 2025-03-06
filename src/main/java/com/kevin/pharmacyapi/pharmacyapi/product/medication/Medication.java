package com.kevin.pharmacyapi.pharmacyapi.product.medication;
import com.kevin.pharmacyapi.pharmacyapi.product.medication.prescriptionRequirement.PrescriptionRequirement;
import com.kevin.pharmacyapi.pharmacyapi.product.Product;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;

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

}
