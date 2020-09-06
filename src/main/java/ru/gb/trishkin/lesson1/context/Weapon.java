package ru.gb.trishkin.lesson1.context;


public interface Weapon {
    Ammunition getAmmunition();
    void gunshot();
    void setAmmunition(Ammunition ammunition);
    void emptyWeapon();
    boolean isEmpty();
}
