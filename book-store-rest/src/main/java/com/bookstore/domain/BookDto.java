package com.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends BaseDto{
    @NotNull
    private String title;
    @NotNull
    private String author;
    private String description;
    @NotNull
    private BigDecimal price;
}
