package org.example.online_food_storage.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.MappedSuperclass;
import org.example.online_food_storage.constant.StatusEnum;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseResponseDto {
    Integer id;
    StatusEnum status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String createdBy;
    String lastModifiedBy;
}
