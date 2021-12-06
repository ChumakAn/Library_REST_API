package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String bookName;
    private String authorIdName;
    private Integer publishedAmount;
    private Integer soldAmount;
    private Double successBookRate;
}
