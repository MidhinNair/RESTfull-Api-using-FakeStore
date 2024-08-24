package dev.midhin.productservice.controller;

import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.Exceptions.NotFontException;
import dev.midhin.productservice.service.repo.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

//field injection and setter  injection is not recomented
//constructor injection in latest version of springboot do not need to autowired
@Autowired
public ProductController(@Qualifier("fakeStoreProductServiceImpl") ProductService productService) {
    this.productService = productService;
}

    @GetMapping("")
   public List<GenericProductDto> getAllProduct(){
  //  List<GenericProductDto> allProduct = productService.getAllProduct ();
   return productService.getAllProduct ();

  }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id )throws NotFontException{
    return productService.getProductById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <GenericProductDto> deleteProductById(@PathVariable("id") Long id )throws NotFontException{
        productService.deleteProductById (id);
        System.out.println ("product deleted of id ="+id);
        //cuttom HttpStatus
        ResponseEntity<GenericProductDto> response =
                new ResponseEntity<> (productService.deleteProductById (id), HttpStatus.ACCEPTED );
        return response;
    }
    @PostMapping("")  //what ever is the request body please convert to genericDto
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto ){
        System.out.println (genericProductDto.getDescription ());
    return productService.createProduct (genericProductDto);

    }
    @PutMapping("/{id}")
    public GenericProductDto updateProduct(@PathVariable("id") Long id , @RequestBody GenericProductDto genericProductDto ) throws  NotFontException{
        System.out.println ("Product updated of id =" + id);
        return productService.updateProductById (id, genericProductDto);
    }

}
