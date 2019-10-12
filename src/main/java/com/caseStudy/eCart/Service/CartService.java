package com.caseStudy.eCart.Service;

import com.caseStudy.eCart.Repository.*;
import com.caseStudy.eCart.model.Cart;
import com.caseStudy.eCart.model.Product;
import com.caseStudy.eCart.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private OrderHistoryRepository orderHistoryRepository;

    public Cart addProduct(Long user_id, Long product_id) {
        Product product = productRepository.findByProdId(product_id);
        Users users = userRepository.findByUserId(user_id);

        if (cartRepository.findByUsersAndProducts(users, product).isPresent()) {
            Cart cart = cartRepository.findByUsersAndProducts(users, product).get();

            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
        } else {
            Cart c = new Cart(product, users, 1);
            cartRepository.save(c);
        }
        return cartRepository.findByUsersAndProducts(users, product).get();
    }

    public List<Cart> showCart(Long user_id,Principal principal){
        Users users = userRepository.findByUserId(user_id);
        return cartRepository.findByUsersAndProducts_Active(users,1);
    }

    public String clearCart(Long userId, Principal principal) {
        Users users = userRepository.findByUserId(userId);
        List<Cart> cartList = cartRepository.findAllByUsers(users);
        for(Cart cart: cartList) {
            cartRepository.deleteById(cart.getCartId());
        }
        return "Cart Cleared!!";
    }
    public String removefromcart(Long userid, Long productid) {
        Product product = productRepository.findByProdId(productid);
        Users users = userRepository.findByUserId(userid);
        if(cartRepository.findByUsersAndProducts(users,product).get().getQuantity() <= 1) {
            Cart cart = cartRepository.findByUsersAndProducts(users,product).get();
            cartRepository.delete(cart);
        }
        else {
            Cart cart = cartRepository.findByUsersAndProducts(users,product).get();
            cart.setQuantity(cart.getQuantity()-1);
            cartRepository.save(cart);
        }
        return "Removed";
    }

    public String removeProduct(Long userid, Long productid) {
        Product product = productRepository.findByProdId(productid);
        Users users = userRepository.findByUserId(userid);
            Cart cart = cartRepository.findByUsersAndProducts(users,product).get();
            cartRepository.delete(cart);
        return "Removed";
    }

}