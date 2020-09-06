package ru.gb.trishkin.lesson1.context;

import org.springframework.stereotype.Component;

@Component("liveCartridge")
public class LiveCartridge implements Ammunition {
    public void reload() {
        System.out.println("The sound of the shot with live ammunition");
    }
}
