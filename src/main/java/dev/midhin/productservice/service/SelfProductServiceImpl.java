package dev.midhin.productservice.service;

import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.service.repo.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("selfProductServiceimpl")

public class SelfProductServiceImpl implements ProductService {
    @Override
    public List<GenericProductDto> getAllProduct() {
        return null;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {return null;}

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
        return null;
    }


}
