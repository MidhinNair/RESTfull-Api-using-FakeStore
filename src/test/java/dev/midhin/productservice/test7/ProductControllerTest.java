package dev.midhin.productservice.test7;

import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.controller.ProductController;
import dev.midhin.productservice.service.repo.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProductsReturnsEmptyList() throws Exception {
        when(productService.getAllProduct()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/products"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getProductByIdReturnsProduct() throws Exception {
        GenericProductDto product = new GenericProductDto();
        product.setId(1L);
        product.setTitle("Test Product");

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Product"));
    }

    @Test
    void createProductReturnsCreatedProduct() throws Exception {
        GenericProductDto product = new GenericProductDto();
        product.setTitle("New Product");

        when(productService.createProduct(any(GenericProductDto.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"New Product\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("New Product"));
    }

    @Test
    void updateProductByIdReturnsUpdatedProduct() throws Exception {
        GenericProductDto product = new GenericProductDto();
        product.setTitle("Updated Product");

        when(productService.updateProductById(any(Long.class), any(GenericProductDto.class))).thenReturn(product);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Product\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Updated Product"));
    }

    @Test
    void deleteProductByIdReturnsDeletedProduct() throws Exception {
        GenericProductDto product = new GenericProductDto();
        product.setId(1L);

        when(productService.deleteProductById(1L)).thenReturn(product);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }
}