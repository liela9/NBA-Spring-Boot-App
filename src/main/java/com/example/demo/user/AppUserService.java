package com.example.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AppUserService {
    @Autowired
    private final AppUserRepository appUserRepository;

//    public AppUser register(RegistrationRequest request){
//        AppUser user = new AppUser();
//        user.setEmail(request.getEmail());
//        user.setFullName(request.getFullName());
//        user.setUserName(request.getUserName());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setBirthYear(request.getBirthYear());
//        user.setRole(AppUserRole.USER);
//
//        appUserRepository.save(user);
//        return user;
//    }

    public List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    public AppUser getUserById(Long userId) {
        AppUser user = appUserRepository.findUserById(userId);
        if(user == null)
            throw new IllegalStateException("user with id " + userId + "does not exists");
        return user;
    }

    public AppUser getUserByEmail(String email) {
        AppUser user = appUserRepository.findUserByEmail(email);
        if(user == null)
            throw new IllegalStateException("user with email " + email + " does not exists");
        return user;
    }

    public void addNewUser(AppUser user) {
        AppUser newUser = appUserRepository.findUserByEmail(user.getEmail());
        if (newUser != null )
            throw new IllegalStateException("email already exists");
        appUserRepository.save(user);
    }

}
