package com.Alex.database3.services.impl;

import com.Alex.database3.domain.entities.UserInfo;
import com.Alex.database3.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.userInfoRepository = repository;
        this.passwordEncoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(username);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        return new UserInfoDetails(userInfo.get());
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setUsername(userInfo.getUsername());
        userInfoRepository.save(userInfo);
        return "User added successfully!";
    }

    public void deleteUser(Integer id) {
        if (!userInfoRepository.existsById(id)) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        userInfoRepository.deleteById(id);
    }
}