package ru.gb.trishkin.lesson1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.gb.trishkin.lesson1.context.Weapon;

public class Soldier {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Weapon weapon = context.getBean("weapon", Weapon.class);
        weapon.gunshot();
    }
}
