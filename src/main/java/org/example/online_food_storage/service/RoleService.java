package org.example.online_food_storage.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.online_food_storage.model.Role;
import org.example.online_food_storage.repository.RoleRepository;
import org.example.online_food_storage.validation.CommonSchemaValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    CommonSchemaValidator commonSchemaValidator;

    public Optional<Role> getByName(String roleName) {
        return roleRepository.findRoleByName(roleName);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> getById(Integer id) {
        return roleRepository.findById(id);
    }

    public Role create(Role role) {
        commonSchemaValidator.roleNotExists(role.getName());
        return roleRepository.save(role);
    }

    public List<Role> getRolesById(List<Integer> rolesId) {
        return rolesId.stream()
                .map(roleRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
