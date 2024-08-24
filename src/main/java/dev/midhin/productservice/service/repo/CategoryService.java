package dev.midhin.productservice.service.repo;

import dev.midhin.productservice.Dtos.GenericCategoryDTO;
import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.model.Category;
import dev.midhin.productservice.model.Product;

import java.util.List;

public interface CategoryService {
    public List<GenericCategoryDTO> getAllCategory();
    public GenericProductDto getCategoryByName(String name);

}
