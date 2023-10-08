package ru.handh.model;

import ru.handh.util.InvalidAttackValueException;
import ru.handh.util.InvalidDefenseValueException;
import ru.handh.util.InvalidHealthValueException;
import ru.handh.util.PlayerDeadException;

public abstract class Creature {

    private int health;
    private int attack;
    private int defense;
    private int minDamage;
    private int maxDamage;

    private final int minHealth = 0;
    private final int maxHealth;
    private final int minAttack = 1;
    private final int maxAttack = 30;
    private final int minDefense = 1;
    private final int maxDefense = 30;

    public Creature(int attack, int defense, int health, int minDamage, int maxDamage) {
        if(attack > maxAttack || attack < minAttack) {
            throw new InvalidAttackValueException("Attack should be between " + minAttack + " and " + maxAttack);
        }
        if(defense > maxDefense || defense < minDefense) {
            throw new InvalidDefenseValueException("Defense should be between " + minDefense + " and " + maxDefense);
        }
        if(health < minHealth) {
            throw new InvalidHealthValueException("Defense should be more then " + minHealth);
        }
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.maxHealth = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int attack(Creature target) {
        if(!isAlive()){
            throw new PlayerDeadException("Player cant attack, because he is dead");
        }
        int damage = 0;
        int attackModifier = this.attack - target.getDefense() + 1;
        if(attackModifier < 1) {
            attackModifier = 1;
        }
        int successCount = 0;
        for (int i = 0; i < attackModifier; i++) {
            int roll = (int) (Math.random() * 6) + 1;
            if (roll >= 5) {
                successCount++;
            }
        }
        if (successCount > 0) {
            damage = (int) (Math.random() * (maxDamage - minDamage + 1)) + minDamage;
            int healthAfterDamage = target.getHealth() - damage;
            if(healthAfterDamage < 0) {
                healthAfterDamage = 0;
            }
            target.setHealth(healthAfterDamage);
        }
        return damage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    protected void setAttack(int attack) {
        if(attack > maxAttack || attack < minAttack) {
            throw new InvalidAttackValueException("Attack should be between " + minAttack + " and " + maxAttack);
        }
        this.attack = attack;
    }

    protected void setDefense(int defense) {
        if(defense > maxDefense || defense < minDefense) {
            throw new InvalidDefenseValueException("Defense should be between " + minDefense + " and " + maxDefense);
        }
        this.defense = defense;
    }

    protected void setHealth(int health) {
        if(health < minHealth || health > maxHealth) {
            throw new InvalidHealthValueException("Defense should be between " + minHealth + " and " + maxHealth);
        }
        this.health = health;
    }


    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}
