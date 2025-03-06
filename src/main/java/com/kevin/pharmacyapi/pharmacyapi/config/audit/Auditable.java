// src/main/java/com/kevin/pharmacyapi/pharmacyapi/config/audit/Auditable.java
package com.kevin.pharmacyapi.pharmacyapi.config.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kevin.pharmacyapi.pharmacyapi.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
public abstract class Auditable {

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User created_by;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User updated_by;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();

        // Make sure the user with ID 1 actually exists
        if (created_by == null) {
            User defaultUser = new User();
            defaultUser.setId(1L);
            created_by = defaultUser;
        }

        if (updated_by == null) {
            User defaultUser = new User();
            defaultUser.setId(1L);
            updated_by = defaultUser;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();

        if (updated_by == null) {
            User defaultUser = new User();
            defaultUser.setId(1L);
            updated_by = defaultUser;
        }
    }
}