package ru.handh;

import ru.handh.model.Monster;
import ru.handh.model.Player;

public class App 
{
    public static void main( String[] args )
    {
        Player player = new Player(5, 4, 25, 1, 5);
        Monster monster = new Monster(15, 4, 25, 6, 10);
        while (player.isAlive()) {
            monster.attack(player);
            System.out.println("Здоровье игрока после получения урона: " + player.getHealth());
        }

        Player player1 = new Player(4,2,20,4,10);
        Player player2 = new Player(4,2,20,4,10);

        while (player1.isAlive() && player2.isAlive()) {
            System.out.println("-----------------------------------");
            System.out.println("Здоровье первого игрока: " + player1.getHealth());
            System.out.println("Здоровье второго игрока: " + player2.getHealth());
            System.out.println("-----------------------------------");

            if(player1.getCountHeal() != 0 && player1.getHealth() < player1.getMaxHealth() * 0.5) {
                int heal = player1.heal();
                System.out.println("Игрок 1 исцелился, количество оставшихся лечений: " + player1.getCountHeal());
                System.out.println("Исцеление: +" + heal);
            } else {
                int damage = player1.attack(player2);
                System.out.println("Игрок 1 атакует игрока 2");
                System.out.println("Урон: -" + damage);
                if(!player2.isAlive()) {
                    System.out.println("Игрок 2 погиб");
                    break;
                }
            }

            System.out.println("-----------------------------------");
            if(player2.getCountHeal() != 0 && player2.getHealth() < player2.getMaxHealth() * 0.5) {
                int heal = player2.heal();
                System.out.println("Игрок 2 исцелился, количество оставшихся лечений: " + player2.getCountHeal());
                System.out.println("Исцеление: +" + heal);
            } else {
                int damage = player2.attack(player1);
                System.out.println("Игрок 2 атакует игрока 1");
                System.out.println("Урон: -" + damage);
                if(!player1.isAlive()) {
                    System.out.println("Игрок 1 погиб");
                    break;
                }
            }
        }
    }
}
