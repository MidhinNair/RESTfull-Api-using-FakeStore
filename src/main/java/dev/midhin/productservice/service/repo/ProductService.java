package dev.midhin.productservice.service.repo;

import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.Exceptions.NotFontException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
     List<GenericProductDto> getAllProduct();
    GenericProductDto getProductById(Long id);
    GenericProductDto createProduct(GenericProductDto genericProductDto);
    GenericProductDto deleteProductById(Long id) throws NotFontException;
    GenericProductDto updateProductById(Long id,GenericProductDto genericProductDto) throws NotFontException;
}
