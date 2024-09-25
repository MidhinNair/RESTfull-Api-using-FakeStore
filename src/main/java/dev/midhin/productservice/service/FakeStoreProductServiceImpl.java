package dev.midhin.productservice.service;

import dev.midhin.productservice.Dtos.FakeStoreProductDto;
import dev.midhin.productservice.Dtos.GenericProductDto;
import dev.midhin.productservice.Exceptions.NotFontException;
import dev.midhin.productservice.service.repo.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("fakeStoreProductServiceImpl")

public class FakeStoreProductServiceImpl implements ProductService {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductByIdRequestUrl ="https://fakestoreapi.com/products/{id}";
    private String CreateProductRequestUrl ="https://fakestoreapi.com/products";
    private String updateProductRequestUrl ="https://fakestoreapi.com/products/{id}";
    private String deleteProductRequestUrl ="https://fakestoreapi.com/products/{id}";
    private String getAllProductRequestUrl ="https://fakestoreapi.com/products";


    @Override
    public List<GenericProductDto> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build ();
        //when ever you do a generic arraylist this datatype is only compile at runtime

        ResponseEntity<FakeStoreProductDto[]> response=
                restTemplate.getForEntity (getAllProductRequestUrl,FakeStoreProductDto[].class );

        List<GenericProductDto> ans = new ArrayList<> ();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody ()){
            ans.add (convertFakeStoreToGenericDto (fakeStoreProductDto));
        }
        return ans;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        System.out.println("test in product service");
        RestTemplate restTemplate = restTemplateBuilder.build ();

        ResponseEntity<FakeStoreProductDto> response=
                restTemplate.getForEntity (getProductByIdRequestUrl, FakeStoreProductDto.class,id);//post/put

        FakeStoreProductDto fakeStoreProductDto = response.getBody ();

        return convertFakeStoreToGenericDto(fakeStoreProductDto);
    }
    @Override
    public GenericProductDto updateProductById(Long id , GenericProductDto genericProductDto) throws NotFontException {
        RestTemplate restTemplate = restTemplateBuilder.build ();
        ResponseEntity<FakeStoreProductDto> response=
                restTemplate.getForEntity (updateProductRequestUrl,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody ();
        if (fakeStoreProductDto == null){
            throw new NotFontException ("No Iteam Fond with the ID"+id+"iteam doesn't exist");
        }

        return convertFakeStoreToGenericDto(fakeStoreProductDto);
    }

    public GenericProductDto createProduct (GenericProductDto genericProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build ();
        ResponseEntity<GenericProductDto> response =
               restTemplate.postForEntity (CreateProductRequestUrl,genericProductDto,GenericProductDto.class);
       return response.getBody ();
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFontException {
        RestTemplate restTemplate = restTemplateBuilder.build ();
        // restTemplate.delete (deleteProductRequestUrl,id);
        //exchange or execute custom template

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responce =
                restTemplate.execute(deleteProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        if (responce.getBody () == null){
            throw new NotFontException ("No Iteam Fond with the ID"+id+"iteam doesn't exist");
        }
    return convertFakeStoreToGenericDto (responce.getBody ()) ;
    }

    public GenericProductDto convertFakeStoreToGenericDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto ();
        product.setId (fakeStoreProductDto.getId ());
        product.setTitle (fakeStoreProductDto.getTitle ());
        product.setCategory (fakeStoreProductDto.getCategory ());
        product.setPrice (fakeStoreProductDto.getPrice ());
        product.setDescription (fakeStoreProductDto.getDescription ());
        product.setImage (fakeStoreProductDto.getImage ());
        return  product;

    }


}
