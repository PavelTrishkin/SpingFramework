package ru.gb.trishkin.spring.mvc.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.gb.trishkin.spring.mvc.domain.Users;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users  getById(Long id);
    Users auth(String name, String password);
    Users createOrSaveUser(Users users);
    void deleteUserById(Long id);

    List<Users> getAll();
}
