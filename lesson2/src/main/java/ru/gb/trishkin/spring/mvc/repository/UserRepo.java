package ru.gb.trishkin.spring.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.trishkin.spring.mvc.domain.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
    Users findFirstByName(String name);
}
