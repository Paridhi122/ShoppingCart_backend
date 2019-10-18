package com.caseStudy.eCart.Service;

import com.caseStudy.eCart.Repository.*;
import com.caseStudy.eCart.model.Cart;
import com.caseStudy.eCart.model.OrderHistory;
import com.caseStudy.eCart.model.Product;
import com.caseStudy.eCart.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    public Cart addProduct(Long user_id, Long product_id) {
        Product product = productRepository.findByProdId(product_id);
        Users users = userRepository.findByUserId(user_id);

        if (cartRepository.findByUsersAndProducts(users, product).isPresent()) {
            Cart cart = cartRepository.findByUsersAndProducts(users, product).get();
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotalprice(cart.getQuantity()*product.getPrice());
            cartRepository.save(cart);
        } else {
            Cart c = new Cart(product, users, 1,product.getPrice());
            cartRepository.save(c);
        }
        return cartRepository.findByUsersAndProducts(users, product).get();
    }

    public List<Cart> showCart(Long user_id,Principal principal){
        Users users = userRepository.findByUserId(user_id);
        return cartRepository.findByUsersAndProducts_Active(users,1);
    }

    public List<Cart> clearCart(Long userId, Principal principal) {
        Users users = userRepository.findByUserId(userId);
        List<Cart> cartList = cartRepository.findAllByUsers(users);
        for(Cart cart: cartList) {
            cartRepository.deleteById(cart.getCartId());
        }
        return cartRepository.findAllByUsers(users);
    }
    public Optional<Cart> removefromcart(Long userid, Long productid) {
        Product product = productRepository.findByProdId(productid);
        Users users = userRepository.findByUserId(userid);
        if(cartRepository.findByUsersAndProducts(users,product).get().getQuantity() <= 1) {
            Cart cart = cartRepository.findByUsersAndProducts(users,product).get();
            cartRepository.delete(cart);
        }
        else {
            Cart cart = cartRepository.findByUsersAndProducts(users,product).get();
            cart.setQuantity(cart.getQuantity()-1);
            cart.setTotalprice(cart.getTotalprice()-product.getPrice());
            cartRepository.save(cart);
        }
        return cartRepository.findByUsersAndProducts(users,product);
    }

    public Optional<Cart> removeProduct(Long userid, Long productid) {
        Product product = productRepository.findByProdId(productid);
        Users users = userRepository.findByUserId(userid);
            Cart cart = cartRepository.findByUsersAndProducts(users,product).get();
            cartRepository.delete(cart);
        return cartRepository.findByUsersAndProducts(users,product);
    }

    public double checkout(Long user_id, Principal principal) {
        Users users = userRepository.findByUserId(user_id);
        List<Cart> cartList = cartRepository.findAllByUsers(users);
        int q = 0;
        double price=0;
        for (Cart cart: cartList) {
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setUsers(users);
            orderHistory.setProduct(cart.getProducts());
            orderHistory.setDate();
            orderHistory.setTotalQuantity(cart.getQuantity());
            orderHistory.setTotalcartprice(cart.getTotalprice());
            orderHistoryRepository.save(orderHistory);
        }

        clearCart(user_id,principal);
        return 0;
    }

    public int calquantity(Long user_id,Principal principal) {
        Users users = userRepository.findByUserId(user_id);
        List<Cart> cartList = cartRepository.findAllByUsers(users);
        int q = 0;
        for (Cart cart: cartList) {
            q = q + cart.getQuantity();

        }
        return q;
    }

    public double calprice(Long user_id,Principal principal) {
        Users users = userRepository.findByUserId(user_id);
        List<Cart> cartList = cartRepository.findAllByUsers(users);
        double q = 0;
        for (Cart cart: cartList) {
            q = q + cart.getTotalprice();
        }
        return q;
    }

    public List<OrderHistory> showorderhistory(Long user_id,Principal principal){
        Users users = userRepository.findByUserId(user_id);
        return orderHistoryRepository.findAllByUsers(users);
    }
}
