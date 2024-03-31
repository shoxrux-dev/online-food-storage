package org.example.online_food_storage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseModel implements Serializable {
    @Column(unique = true)
    String name;
}