package com.caseStudy.eCart.Repository;

import com.caseStudy.eCart.model.Cart;
import com.caseStudy.eCart.model.Product;
import com.caseStudy.eCart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUsersAndProducts(Users users, Product product);
    List<Cart> findByUsersAndProducts_Active(Users users, int a);
    List<Cart> findAllByUsers(Users user);
}
