package com.webprog.demo1.service.implementaion;

import com.webprog.demo1.model.User;
import com.webprog.demo1.model.exceptions.InvalidArgumentsException;
import com.webprog.demo1.model.exceptions.InvalidUserCredentialsException;
import com.webprog.demo1.model.exceptions.PasswordsDoNotMatchException;
import com.webprog.demo1.repository.InMemoryUserRepository;
import com.webprog.demo1.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final InMemoryUserRepository userRepository;
    public AuthServiceImpl(InMemoryUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        // Check if the username, password, name and surname are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword == null || repeatPassword.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        // Check if the password and the repeated password are the same
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        return userRepository.saveOrUpdate(new User(username, password, name, surname));

    }
}
