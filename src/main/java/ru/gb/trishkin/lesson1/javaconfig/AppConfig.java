package ru.gb.trishkin.lesson1.javaconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.gb.trishkin.lesson1.context.*;

@Configuration
@ComponentScan("ru.gb.trishkin.lesson1.context")
public class AppConfig {
    @Bean(name = "newWeapon")
    @Scope("prototype")
    public Weapon weapon(Ammunition blankCartridge){
        return new WeaponImpl(blankCartridge);
    }
}
