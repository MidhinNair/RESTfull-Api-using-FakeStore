package dev.midhin.productservice.Dtos;

import dev.midhin.productservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private  String image;
    private String category;
    private double price;
}
