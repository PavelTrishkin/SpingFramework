package ru.gb.trishkin.spring.mvc.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.trishkin.spring.mvc.domain.Role;
import ru.gb.trishkin.spring.mvc.domain.Users;
import ru.gb.trishkin.spring.mvc.repository.UserRepo;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UsersServiceImpl implements UserService {
    private UserRepo userRepo;

    public UsersServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Users getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public Users auth(String name, String password) {
        if (name == null || name.isEmpty()){
            System.out.println("You are not authenticated");
            return null;
        }
        Users user = userRepo.findFirstByName(name);
        if(user == null){
            System.out.println("You are not authenticated");
            return null;
        }
        if(!Objects.equals(password, user.getPassword())){
            System.out.println("You are not authenticated");
            return null;
        }
        System.out.println("You are authenticated");
        return user;
    }

    @Override
    public List<Users> getAll() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findFirstByName(s);
    }

    public Users createOrSaveUser(Users user){
        return userRepo.save(user);
    }

    public void deleteUserById(Long id){
        userRepo.deleteById(id);
    }
}
