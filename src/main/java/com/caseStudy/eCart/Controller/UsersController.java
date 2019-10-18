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

    @PutMapping("/updateuser/{id}")
    public Users updateUser(@PathVariable(value = "id") Long userid,
                            @Valid @RequestBody Users userDetails) {

        Users user = userRepository.findByUserId(userid);
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        user.setAddress(userDetails.getAddress());
        Users updateduser = userRepository.save(user);
        return updateduser;
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
