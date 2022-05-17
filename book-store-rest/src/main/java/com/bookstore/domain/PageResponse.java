package com.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {
    private long totalContent;
    private int totalPages;
    private int page;
    private int size;
    private List<T> list;
}
