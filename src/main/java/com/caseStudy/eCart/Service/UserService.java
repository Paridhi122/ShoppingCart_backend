//package com.caseStudy.eCart.Service;
//
//import com.caseStudy.eCart.Repository.UserRepository;
//import com.caseStudy.eCart.model.Users;
//
//import java.security.Principal;
//import java.util.Optional;
//
//public class UserService {//    private UserRepository userRepository;
//
//    public Optional<Users> CurrentUser(Principal principal) {
//        String username = principal.getName();
//        return userRepository.findByUsername(username);
//    }
//
//    public Long getUserId(Principal principal) {
//        String username = principal.getName();
//        return userRepository.findByUsername(username).get().getUserId();
//    }
//
//    public String getUserRole(Principal principal) {
//        return  userRepository.findByUsername(principal.getName()).get().getRole().getroleid();
//}
//
//    public Optional<Users> getUserProfile(Principal principal) {
//        return userRepository.findByUsername(principal.getName());
//    }
//
//    public void checkDetails(Users user, Principal principal) {
//
//    }
//}
