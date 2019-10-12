package com.caseStudy.eCart.Controller;


import com.caseStudy.eCart.Repository.UserRepository;
import com.caseStudy.eCart.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("username/{username}")
    public Optional<Users> getUsername(@PathVariable(value = "username")String u){
        return userRepository.findByUsername(u);
    }

    @PostMapping("/addUser")
    public Users createUser(@Valid @RequestBody Users user) {
        user.setRole("user");
        user.setActive(1);
    return userRepository.save(user);
    }
}
