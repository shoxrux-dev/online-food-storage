package org.example.online_food_storage.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.online_food_storage.constant.StatusEnum;
import org.example.online_food_storage.exception.RecordNotFoundException;
import org.example.online_food_storage.model.User;
import org.example.online_food_storage.repository.UserRepository;
import org.example.online_food_storage.validation.CommonSchemaValidator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    CommonSchemaValidator commonSchemaValidator;
    RoleService roleService;
    PasswordEncoder passwordEncoder;

    public User register(User user, List<Integer> rolesId) {
        commonSchemaValidator.userNotExist(user.getUsername());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setStatus(StatusEnum.ACTIVE);
        user.setRoles(roleService.getRolesById(rolesId));
        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RecordNotFoundException("User not found with name: " + username));

    }

    public User update(User user) {
        User user1 = getUser();
        if(Objects.nonNull(user1)) {
            user1.setUsername(user.getUsername());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user1);
        }
        return null;
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken token) {
            if (token.getPrincipal() instanceof User user1) {
                return userRepository.findUserByUsername(user1.getUsername())
                        .orElseThrow(() -> new RecordNotFoundException("User not found with username: " + user1.getUsername()));
            }
        }
        return null;
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + id));
    }

}
