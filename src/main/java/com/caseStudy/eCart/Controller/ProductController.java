package com.caseStudy.eCart.Controller;

import com.caseStudy.eCart.Repository.ProductRepository;
import com.caseStudy.eCart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/add")
    public Product createProduct(@Valid @RequestBody Product prod) {
        prod.setActive(1);
        return productRepository.save(prod);
    }

    @GetMapping("/getProduct/{prodId}")
    @ResponseBody
    public Product getProductById(@PathVariable Long prodId) throws Exception {
        Product product =  productRepository.findById(prodId)
                .orElseThrow(() -> new Exception("Cant Find Product"));
        return product;
    }

    @PutMapping("/updateProduct/{prodId}")
    public Product updateProduct(@PathVariable(value = "prodId") Long pId,
                           @Valid @RequestBody Product productDetails) throws Exception {

        Product product = productRepository.findById(pId)
                .orElseThrow(() -> new Exception("Product not Found"));

//        product.setName(productDetails.getName());
//        product.setPrice(productDetails.getPrice());
//        product.setCategory(productDetails.getCategory());
        product.setSrc(productDetails.getSrc());

        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    @DeleteMapping("/deleteProduct/{prodId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "prodId") Long pId) throws Exception {
        Product product = productRepository.findById(pId)
                .orElseThrow(() -> new Exception("Product not found"));

        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/price/{price}")
    public Product getProductByPrice(@PathVariable(value = "price") Double p) {
        return productRepository.findByPrice(p);
    }

    @GetMapping("/range/{price1}/between/{price2}")
    public List<Product> getProductByRange(@PathVariable(value = "price1") Double p1,@PathVariable(value="price2") Double p2) {
        return productRepository.findByPriceBetween(p1,p2);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable(value = "category") String cat) {
        return productRepository.findAllByCategory(cat);
    }

    @GetMapping("/category/{category}/range/{price1}/and/{price2}")
    public List<Product> getProductbyCategoryAndRange(@PathVariable(value = "category") String cat,@PathVariable(value = "price1") Double p1,@PathVariable(value="price2") Double p2){
        return productRepository.findByCategoryAndPriceBetween(cat,p1,p2);
    }

}
