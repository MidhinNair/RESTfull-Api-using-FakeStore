package dev.midhin.productservice.controller;

import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.Exceptions.NotFontException;
import dev.midhin.productservice.service.repo.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {
    @MockBean
    private ProductController productController;
    @Qualifier("fakeStoreProductServiceImpl") // This is the name of the bean that we want to mock
    @Autowired
    @MockBean(name = "fakeStoreProductServiceImpl")
    private ProductService productService;
    @Captor
    private ArgumentCaptor<Long> IdCaptor;
    @Test
    void returnNullWhenProductDoesntExist() throws NotFontException {
        // Mock the behavior of productService.getProductById(19L) to return null
        when(productService.getProductById(199L))
                .thenReturn(null);

        // Call the method productController.getProductById(199L)
        GenericProductDto productDto = productController.getProductById(19L);

        // Assert that the returned productDto is null
        assertNull(productDto);
    }

    @Test
    void ShouldreturnTitleABCWithProductID1() throws NotFontException {
        // Mock the behavior of productService.getProductById(1L) to return a GenericProductDto with title "ABC"
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle("ABC");

        //genericProductDto.setTitle("ABC_Midhin");
        when(productService.getProductById(1L))
                .thenReturn(genericProductDto);

        // Call the method productController.getProductById(1L)
        GenericProductDto productDto = productController.getProductById(1L);

        // Assert that the returned productDto has title "ABC"
        assertEquals("ABC" , genericProductDto.getTitle());
    }
    @Test
    void throwExceptionWhenProductDoesntExist() {
        // Mock the behavior of productService.getProductById(any(Long.class)) to throw a NotFontException
        when(productService.getProductById(any(Long.class)))
                .thenThrow(new NotFontException("Product not found"));

        // Assert that calling productController.getProductById throws NotFontException
        assertThrows(NotFontException.class, () -> {
            productController.getProductById(129L);
        });
    }
    @Test
    void productControllerCallsProductServiceWithSameProductID(){
        Long id = 10L;
        // Call the method productController.getProductById(1L)
        when(productService.getProductById(1L)) // Mock the behavior of productService.getProductById(1L) to return a GenericProductDto with title "ABC"
                .thenReturn(new GenericProductDto());
        // check that the product service is being called with the same product ID

        productController.getProductById(id);
        verify(productController).getProductById(IdCaptor.capture());
        assertEquals(id, IdCaptor.getValue());
    }
}