package org.example.online_food_storage.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.online_food_storage.exception.AlreadyExistsException;
import org.example.online_food_storage.exception.AuthenticationException;
import org.example.online_food_storage.exception.RecordNotFoundException;
import org.example.online_food_storage.model.Category;
import org.example.online_food_storage.model.Role;
import org.example.online_food_storage.model.User;
import org.example.online_food_storage.repository.CategoryRepository;
import org.example.online_food_storage.repository.RoleRepository;
import org.example.online_food_storage.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonSchemaValidator {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;
    CategoryRepository categoryRepository;

    public void validatePassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("username or password is incorrect");
        }
    }

    public void userNotExist(String username) {
        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        if(userByUsername.isPresent()){
            throw new AlreadyExistsException(String.format("user already exists with username %s", username));
        }
    }

    public void roleNotExists(String roleName) {
        Optional<Role> roleByName = roleRepository.findRoleByName(roleName);
        if(roleByName.isPresent()) {
            throw new AlreadyExistsException(String.format("role already exists with name %s", roleByName));
        }
    }

    public void categoryNotExists(String categoryName) {
        Optional<Category> categoryByName = categoryRepository.findCategoryByName(categoryName);
        if(categoryByName.isPresent()) {
            throw new AlreadyExistsException(String.format("category already exists with name %s", categoryName));
        }
    }

    public void categoryExistsById(Integer id) {
        Optional<Category> categoryById = categoryRepository.findById(id);
        if(categoryById.isEmpty()) {
            throw new RecordNotFoundException(String.format("category not found with id %s", id));
        }
    }

}
