package org.example.online_food_storage.config;

import org.example.online_food_storage.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditingConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(
                authentication == null ||
                        !authentication.isAuthenticated() ||
                        authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        User user = (User) authentication.getPrincipal();
        return Optional.ofNullable(user.getUsername());
    }
}
