package dev.midhin.productservice.service;

import dev.midhin.productservice.Dtos.FakeStoreProductDto;
import dev.midhin.productservice.Dtos.FakestoreDTOCategory;
import dev.midhin.productservice.Dtos.GenericCategoryDTO;
import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.service.repo.CategoryService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FakestoreCategoryService implements CategoryService {
    private  String getAllCategoryUrl = "https://fakestoreapi.com/products/categories";
   // private String getProductByCategory = "https://fakestoreapi.com/products/categories/{categoryName}";
    private String getProductByCategory = "https://fakestoreapi.com/products/category/{categoryName}";

    //field injection
    private RestTemplateBuilder restTemplateBuilder;

    public FakestoreCategoryService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    @Override
    public List<GenericCategoryDTO> getAllCategory() {
        RestTemplate restTemplate =restTemplateBuilder.build();
        ResponseEntity<String[]> response = restTemplate.getForEntity(getAllCategoryUrl, String[].class);

        List<GenericCategoryDTO> categoryDTOList = new ArrayList<>();
        for(String CategoryName : response.getBody()){
            GenericCategoryDTO categoryDTO = new GenericCategoryDTO();
            categoryDTO.setCategoryName(CategoryName);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    @Override
    public GenericProductDto getCategoryByName(String name) throws NoSuchElementException {

        RestTemplate restTemplate =restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> respose =restTemplate.getForEntity(getProductByCategory, FakeStoreProductDto[].class,name);
        FakeStoreProductDto[] fakeStoreProductDto = respose.getBody();
        return convertFakeStoreToGenericDto(fakeStoreProductDto[0]);
    }

    public GenericProductDto convertFakeStoreToGenericDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto ();
        product.setId (fakeStoreProductDto.getId ());
        product.setTitle (fakeStoreProductDto.getTitle ());
        product.setCategory (fakeStoreProductDto.getCategory ());
        product.setCategory (fakeStoreProductDto.getCategory());
        product.setPrice (fakeStoreProductDto.getPrice ());
        product.setDescription (fakeStoreProductDto.getDescription ());
        product.setImage (fakeStoreProductDto.getImage ());
        return  product;

    }

    public GenericCategoryDTO convertFakeStoreToGenericDto(FakestoreDTOCategory fakestoreDTOCategory){
       GenericCategoryDTO category = new GenericCategoryDTO ();
        category.setCategoryName(fakestoreDTOCategory.getCategory());
        return category;

    }
}
