package com.example.springBootfirstapp.Controllers;


import com.example.springBootfirstapp.DTO.ProductDto;
import com.example.springBootfirstapp.Entities.ProductEntity;
import com.example.springBootfirstapp.PaginatedProductResponse;
import com.example.springBootfirstapp.Service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService service1;

    public ProductController(ProductService service1) {
        this.service1 = service1;
    }
    @PostMapping("/product")
    public String postProduct(@RequestBody ProductEntity product) {
        return service1.postProduct(product);
    }

    @PutMapping("/product/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody ProductEntity product) {
        return service1.updateProduct(id, product);
    }
    @GetMapping("/products")
    public PaginatedProductResponse getAllProducts(@RequestParam(required = false) Integer page) {
        return service1.getAllProducts(page);
    }

    @DeleteMapping("/product/{id}")
    public String deleteByProductId(@PathVariable int id) {
        return service1.deleteByProduct(id);
    }

    @GetMapping(value = "/search", params = "product")
    public ProductDto getProduct(@RequestParam String product,
                                 @RequestParam(required = false) String lang
    ) {

        return service1.findByProduct(product, lang);
    }
}

