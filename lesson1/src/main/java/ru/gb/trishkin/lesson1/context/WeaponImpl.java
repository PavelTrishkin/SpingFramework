package ru.gb.trishkin.lesson1.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("weapon")
@Scope("prototype")
public class WeaponImpl implements Weapon {
    private Ammunition ammunition;

    @Value("false")
    private boolean empty;

    public WeaponImpl() {;
    }

    public WeaponImpl(Ammunition ammunition) {
        this.ammunition = ammunition;
    }

    public void gunshot() {
        if (empty){
            System.out.println("Weapon is empty, need reload");
            return;
        }
        System.out.println("Queue of shots");
        ammunition.reload();
    }

    @Autowired(required = false)
    @Qualifier("liveCartridge")
    public void setAmmunition(Ammunition ammunition) {
        this.ammunition = ammunition;
    }

    public void emptyWeapon() {
        this.empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Ammunition getAmmunition() {
        return ammunition;
    }
}
