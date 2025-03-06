package com.kevin.pharmacyapi.pharmacyapi.stockMovement.supplier;

import com.kevin.pharmacyapi.pharmacyapi.config.audit.Auditable;
import com.kevin.pharmacyapi.pharmacyapi.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "supplier")
public class Supplier extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String contact_person;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String address;

}