package com.kevin.pharmacyapi.pharmacyapi.entities.prescription;

import com.kevin.pharmacyapi.pharmacyapi.entities.sale.Sale;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @Column(nullable = false)
    private String doctor_name;

    @Column(nullable = false)
    private String patient_name;

    @Column(nullable = true)
    private LocalDate expiration_date;

    @Column(nullable = true, length = 1000)
    private String notes;
}