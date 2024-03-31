package org.example.online_food_storage.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PageResponse<T> {
    List<T> content;
    int pageNum;
    int pageSize;
    long totalElements;
    int totalPages;
    boolean last;
}
