package com.example.blinddate.domain.user.service;

import com.example.blinddate.domain.user.domain.User;
import com.example.blinddate.domain.user.domain.UserAuthority;
import com.example.blinddate.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByAccount(username);
    }

    @Transactional
    public void addAuthority(Long id, String authority){
        userRepository.findById(id).ifPresent(user -> {
            UserAuthority newRole = new UserAuthority(user.getId(), authority);
            if(user.getAuthorities() == null){
                HashSet<UserAuthority> authorities = new HashSet<>();
                authorities.add(newRole);
                user.setAuthorities(authorities);
//                User.builder().authorities(user.getAuthorities());
                saveUser(user);
            }else if(!user.getAuthorities().contains(newRole)){
                HashSet<UserAuthority> authorities = new HashSet<>();
                authorities.addAll(user.getAuthorities());
                authorities.add(newRole);
                user.setAuthorities(authorities);
//                User.builder().authorities(user.getAuthorities());
                saveUser(user);
            }
        });
    }

    @Transactional
    public User saveUser(User user){

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUserIdAndPassword(String id, String password){
        return userRepository.findByAccountAndPassword(id, password);
    }

}
