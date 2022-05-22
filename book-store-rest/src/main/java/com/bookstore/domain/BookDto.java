package com.bookstore.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
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
