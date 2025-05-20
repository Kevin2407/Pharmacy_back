package com.kevin.pharmacyapi.pharmacyapi.entities.prescriptionRequirement;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "prescription_requirement")

public class PrescriptionRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, length = 50)
    private String legal_code;
    @Column(nullable = false, length = 50)
    private String max_dose;

}
