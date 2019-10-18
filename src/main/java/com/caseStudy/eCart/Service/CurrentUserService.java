package com.caseStudy.eCart.Service;

import com.caseStudy.eCart.Repository.UserRepository;
import com.caseStudy.eCart.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
@Service
public class CurrentUserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<Users> CurrentUser(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username);
    }

    public Long getUserrId(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username).get().getUserId();
    }

    public String getUserRole(Long user_id, Principal principal) {
        return  userRepository.findByUserId(user_id).getRole();//.getroleid();
    }

    public Users getUserProfile(Long userid, Principal principal) {
        return userRepository.findByUserId(userid);
    }

    public void checkDetails(Users user, Principal principal) {

    }
}
