package com.kevin.pharmacyapi.pharmacyapi.medication.prescriptionRequirement;
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
    @Column(nullable = true)
    private LocalDateTime created_at;
    @Column(nullable = true)
    private LocalDateTime updated_at;
    @Column(nullable = true)
    private Date deleted_at;
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
        deleted_at = new Date();
        deleted_by = "default_user";
    }

}
