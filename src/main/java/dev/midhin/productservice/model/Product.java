package dev.midhin.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    private String title;
    private String description;
    private  String image;
    private double price;
    private Category category;

}
