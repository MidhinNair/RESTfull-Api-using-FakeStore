package dev.midhin.productservice.controller;

import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.Exceptions.NotFontException;
import dev.midhin.productservice.service.repo.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //    @Autowired
    // field injection
    private ProductService productService;

    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//

//    private final ProductService productService;
//
//    public ProductController( ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<GenericProductDto> productDtos = productService.getAllProduct();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(
                    productDtos,
                    HttpStatus.NOT_FOUND
            );
        }

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (GenericProductDto gpd: productDtos) {
            genericProductDtos.add(gpd);
        };

//        genericProductDtos.remove(genericProductDtos.get(0));

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);

//        productDtos.get(0).setId(1001L);
//
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFontException {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) throws NotFontException {
        productService.deleteProductById(id);
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("")
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProduct(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws NotFontException {
        return productService.updateProductById(id, genericProductDto);
    }
}