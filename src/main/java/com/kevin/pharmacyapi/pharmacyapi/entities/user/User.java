package com.kevin.pharmacyapi.pharmacyapi.entities.user;

import com.kevin.pharmacyapi.pharmacyapi.entities.role.Role;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String phone;

    @Column(nullable = false)
    private String password;

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