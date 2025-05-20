package com.kevin.pharmacyapi.pharmacyapi.entities.supplier;

import com.kevin.pharmacyapi.pharmacyapi.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

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