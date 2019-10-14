package com.caseStudy.eCart.Controller;

import com.caseStudy.eCart.Repository.CartRepository;
import com.caseStudy.eCart.Service.CartService;
import com.caseStudy.eCart.Service.CurrentUserService;
import com.caseStudy.eCart.model.Cart;
import com.caseStudy.eCart.model.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/cart")
public class CartController {
    private CartRepository cartRepository;
    private CartService cartService;
    private CurrentUserService currentUserService;
    @Autowired
    public CartController(CartService cartService1,CurrentUserService currentUserService1) {
        this.cartService = cartService1;
        this.currentUserService = currentUserService1;
    }

    @GetMapping(value = "/remove/{prodId}")
    @ResponseBody
    public Optional<Cart> removeFromCart(@PathVariable Long prodId, Principal principal) {
        return cartService.removefromcart(currentUserService.getUserrId(principal),prodId);
    }

    @GetMapping(value = "/delete/{prodId}")
    @ResponseBody
    public Optional<Cart> deletefromcart(@PathVariable Long prodId, Principal principal) {
        return cartService.removeProduct(currentUserService.getUserrId(principal),prodId);
    }

    @GetMapping(value="/addtocart/{prodId}")
    @ResponseBody
    public Cart addToCart(@PathVariable Long prodId, Principal principal) {
        return cartService.addProduct(currentUserService.getUserrId(principal),prodId);
    }

    @GetMapping(value = "/showcart")
    @ResponseBody
    public List<Cart> showCart(Principal principal ) {
        return cartService.showCart(currentUserService.getUserrId(principal),principal);
    }

    @GetMapping(value = "/clearcart")
    @ResponseBody
    public List<Cart> clearCart(Principal principal) {
        return cartService.clearCart(currentUserService.getUserrId(principal),principal);
    }

    @GetMapping(value = "/checkout")
    @ResponseBody
    public double checkout(Principal principal) {
        return cartService.checkout(currentUserService.getUserrId(principal),principal);
    }

    @GetMapping(value = "/showOrderHistory")
    @ResponseBody
    public List<OrderHistory> showHistory(Principal principal ) {
        return cartService.showorderhistory(currentUserService.getUserrId(principal),principal);
    }
    @GetMapping(value = "/quantity")
    @ResponseBody
    public int quantity(Principal principal ) {
        return cartService.calquantity(currentUserService.getUserrId(principal),principal);
    }
    @GetMapping(value = "/price")
    @ResponseBody
    public double price(Principal principal ) {
        return cartService.calprice(currentUserService.getUserrId(principal),principal);
    }

}
