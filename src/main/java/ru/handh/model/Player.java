package ru.handh.model;

import ru.handh.util.HealingException;
import ru.handh.util.PlayerDeadException;

public class Player extends Creature {
    private int countHeal = 4;
    private final double percentOfHeal = 0.3;

    public Player(int attack, int defense, int health, int minDamage, int maxDamage) {
        super(attack, defense, health, minDamage, maxDamage);
    }

    public int heal() {
        if(!isAlive()) {
            throw new PlayerDeadException("Player is dead");
        }
        if(countHeal <= 0) {
            throw new HealingException("There is no healing to restore health");
        }
        int heal = (int)Math.ceil(super.getMaxHealth() * percentOfHeal);
        int healthAfterHeal = super.getHealth() + heal;
        if(healthAfterHeal > super.getMaxHealth()) {
            healthAfterHeal = super.getMaxHealth();
        }
        super.setHealth(healthAfterHeal);
        --countHeal;

        return heal;
    }

    public int getCountHeal() {
        return countHeal;
    }
}
