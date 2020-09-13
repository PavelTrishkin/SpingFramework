package ru.gb.trishkin.lesson1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.trishkin.lesson1.context.Weapon;
import ru.gb.trishkin.lesson1.context.WeaponImpl;
import ru.gb.trishkin.lesson1.javaconfig.AppConfig;

public class SoldierJavaConfig {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Weapon weapon = context.getBean("weapon", Weapon.class);
        weapon.gunshot();

        weapon.emptyWeapon();
        System.out.println("Give me a loaded weapon please");
        weapon.gunshot();

        Weapon newWeapon = context.getBean("weapon", Weapon.class);
        newWeapon.gunshot();
        System.out.println(weapon == newWeapon);
    }
}
