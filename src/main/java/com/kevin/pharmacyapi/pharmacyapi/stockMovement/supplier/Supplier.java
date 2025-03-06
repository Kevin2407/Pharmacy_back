package com.kevin.pharmacyapi.pharmacyapi.stockMovement.supplier;

import com.kevin.pharmacyapi.pharmacyapi.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "supplier")
public class Supplier {
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