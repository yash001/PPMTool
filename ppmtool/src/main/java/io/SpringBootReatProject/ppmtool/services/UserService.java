package io.SpringBootReatProject.ppmtool.services;


import io.SpringBootReatProject.ppmtool.domain.User;
import io.SpringBootReatProject.ppmtool.exceptions.UsernameAlreadyExistsException;
import io.SpringBootReatProject.ppmtool.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //@Autowired
    //private PasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private PasswordEncoder  passwordEncoder;

    public User saveUser (User newUser){

        try{
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }



}
