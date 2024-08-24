package dev.midhin.productservice.controller;

import dev.midhin.productservice.Dtos.GenericCategoryDTO;
import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.service.FakestoreCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class CategoryController {
    //constructor injection
    private FakestoreCategoryService categoryService;

    public CategoryController(FakestoreCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public List<GenericCategoryDTO> getCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/category/{name}")
    public GenericProductDto getAllCategoryById(@PathVariable("name") String name) {

        return categoryService.getCategoryByName(name);
    }
}
