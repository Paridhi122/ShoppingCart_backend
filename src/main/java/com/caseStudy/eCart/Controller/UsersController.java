package com.caseStudy.eCart.Controller;


import com.caseStudy.eCart.Repository.UserRepository;
import com.caseStudy.eCart.Service.CurrentUserService;
import com.caseStudy.eCart.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/user")
public class UsersController {
    CurrentUserService currentUserService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    public UsersController(CurrentUserService currentUserService1) {
        this.currentUserService = currentUserService1;
    }

    @GetMapping("/all")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("username/{username}")
    public Optional<Users> getUsername(@PathVariable(value = "username")String u){
        return userRepository.findByUsername(u);
    }

    @PutMapping("/updateuser")
    public Users updateuser(@Valid @RequestBody Users userdetails, Principal principal) {

        Users users = currentUserService.getUserProfile(currentUserService.getUserrId(principal),principal);
        users.setPassword(userdetails.getPassword());
        users.setName(userdetails.getName());
        users.setEmail(userdetails.getEmail());
        users.setUsername(userdetails.getUsername());
        users.setAddress(userdetails.getAddress());

        Users updatedUser = userRepository.save(users);
        return updatedUser;
    }
    @PostMapping("/addUser")
    public Users createUser(@Valid @RequestBody Users user) {
        user.setRole("user");
        user.setActive(1);
    return userRepository.save(user);
    }

    @GetMapping("/Getuser")
    @ResponseBody
    public Users getuser(Principal principal) {
        return currentUserService.getUserProfile(currentUserService.getUserrId(principal),principal);
    }

    @GetMapping("/Getrole")
    @ResponseBody
    public String getrole(Principal principal) {
        return currentUserService.getUserRole(currentUserService.getUserrId(principal),principal);
    }
}
