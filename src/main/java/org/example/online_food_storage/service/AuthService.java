package org.example.online_food_storage.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.online_food_storage.constant.StatusEnum;
import org.example.online_food_storage.exception.RecordNotFoundException;
import org.example.online_food_storage.model.User;
import org.example.online_food_storage.repository.UserRepository;
import org.example.online_food_storage.validation.CommonSchemaValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService implements UserDetailsService {
    UserRepository userRepository;
    CommonSchemaValidator commonSchemaValidator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUserEntity = userRepository.findUserByUsername(username);
        return optionalUserEntity.orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found", username)));
    }

    public User login(User user){
        User user1 = userRepository.findUserByUsername(user.getUsername()).orElseThrow(() -> new RecordNotFoundException(String.format("user not found wit username %s ", user.getUsername())));
        commonSchemaValidator.validatePassword(user1, user.getPassword());
        return user1;
    }

}
