package ru.gb.trishkin.lesson1.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.gb.trishkin.lesson1.context.*;

@Configuration
@ComponentScan("ru.gb.trishkin.lesson1.context")
public class AppConfig {
    @Bean(name = "weapon")
    public Weapon weapon(Ammunition ammunition){
        return new WeaponImpl(ammunition);
    }
}
