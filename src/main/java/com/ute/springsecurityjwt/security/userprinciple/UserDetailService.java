package com.ute.springsecurityjwt.security.userprinciple;

import com.ute.springsecurityjwt.model.Users;
import com.ute.springsecurityjwt.repository.IUserRepository;
import com.ute.springsecurityjwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserServiceImpl userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found -> username or password" + username));
        return UserPrinciple.build(users);
    }

    public Users getCurrentUser() {
        Optional<Users> user;
        String userName;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {

            userName = principal.toString();
        }

        if (userRepository.existsByUsername(userName)) {
            user = userService.findByUsername(userName);
        } else {

            user = Optional.of(new Users());

            user.get().setUsername("Anonymous");
        }
        return user.get();
    }
}
