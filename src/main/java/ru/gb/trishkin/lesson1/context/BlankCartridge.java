package ru.gb.trishkin.lesson1.context;

import org.springframework.stereotype.Component;

//@Component("blankCartridge")
public class BlankCartridge implements Ammunition {
    public void reload() {
        System.out.println("The sound of blanks being fired");
    }
}
