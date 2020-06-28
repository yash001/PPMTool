package io.SpringBootReatProject.ppmtool.services;


import io.SpringBootReatProject.ppmtool.domain.User;
import io.SpringBootReatProject.ppmtool.exceptions.UsernameAlreadyExistsException;
import io.SpringBootReatProject.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){
      newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

      //Username has to be unique (exception)

        // Make sure that password and confirmPassword match
        // We don't persist or show the confirmPassword
      return userRepository.save(newUser);
    }



}
