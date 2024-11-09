package com.webprog.demo1.service;
import com.webprog.demo1.model.User;
public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);
}
