package com.caseStudy.eCart.Repository;

import com.caseStudy.eCart.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    List<Product> findAll();

    Product findByProdId(Long id);

    Product findByPrice(Double p);

    List<Product> findAllByCategory(String category);

    List<Product> findByPriceBetween(Double v1,Double v2);

    List<Product> findByCategoryAndPriceBetween(String category, Double v1, Double v2);
}

