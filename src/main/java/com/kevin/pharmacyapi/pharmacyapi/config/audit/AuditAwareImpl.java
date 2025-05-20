// src/main/java/com/kevin/pharmacyapi/pharmacyapi/config/audit/AuditAwareImpl.java
package com.kevin.pharmacyapi.pharmacyapi.config.audit;

import com.kevin.pharmacyapi.pharmacyapi.entities.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        // For now, return a default user
        User defaultUser = new User();
        defaultUser.setId(1L);

        // Later replace with actual authentication logic:
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if (authentication != null) {
        //     return Optional.of((User) authentication.getPrincipal());
        // }

        return Optional.of(defaultUser);
    }
}