package com.company;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


interface  Character{
    void jump();
    void defend();
    void speak();
    void setHealth(int health);
    int getHealth();
}

//interface AlienAttacking{
//    boolean attacking();
//}

class Alien implements Character {
   private int health;

    Alien(){
        health=100;
    }

//    @Override
//    public boolean attacking(){
//        System.out.println("Alien is attacking!");
//        return true;
//    }

    @Override
    public void jump() {
        System.out.println("Alien jumped.");
    }

    @Override
    public void defend() {
        System.out.println("Alien defended the attack.");
    }

    @Override
    public void speak() {
        System.out.println("Alien is speaking.");
    }

    @Override
    public void setHealth(int health) {
        this.health=health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    public void attack(Human insaan){
        System.out.println("Alien is attacking.");
        Random rand = new Random();
        if(rand.nextBoolean()){
            insaan.defend();
        }
        else{
            int health =  insaan.getHealth();
            //check to see that health doesn't fall below zero
            if(health>0){
                health-=5;
                if(health<0){
                    health=0;
                }
                insaan.setHealth(health);
            }
        }
    }
}

abstract class Human implements Character {
    private int health;
    String name;
    //AlienAttacking obj;

    Human(String name) {
        health = 100;
        this.name = name;
    }

    @Override
    public void jump() {
        System.out.println(this.name + " jumped.");
    }

//    public void alienAttacking(AlienAttacking obj){
//        this.obj=obj;
//        if(obj.attacking()){
//            System.out.println("Alien is attacking Human");
//        }
//    }
    @Override
    public void defend() {
        System.out.println(this.name + " defended the attack.");
    }

    @Override
    public void speak() {
        System.out.println(this.name + " is speaking.");
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    public void explore() {

        System.out.println(this.name + " is exploring.");
    }

    abstract public void attack(Alien Zzz);
    void capture(){

    }

    void heal(Human insaan){

    }
}

class Warrior extends Human{
    private String name;
    Warrior(String name){
        super(name);
        this.name=name;
    }
    @Override
    void capture(){
        System.out.println("Warrior " + this.name + " is capturing.");
    }

    @Override
    public void attack(Alien Zzz){
        System.out.println("Warrior" + this.name + " is attacking with sword.");
        Random rand = new Random();

        if(rand.nextBoolean()){
            Zzz.defend();
        }
        else{
          int health =  Zzz.getHealth();
            //check to see that health doesn't fall below zero
            if(health>0){
                health-=20;
                if(health<0){
                    health=0;
                }
                Zzz.setHealth(health);
            }
        }
    }
}

class Wizard extends Human{
    private String name;
    Wizard(String name){
        super(name);
        this.name=name;
    }
    @Override
    void heal(Human insaan){
        System.out.println("Wizard " + this.name + " is healing.");
        int health = insaan.getHealth();
        //check to see that health doesn't go above 100
        if(health<100){
            health+=10;
            if(health>100){
                health=100;
            }
            insaan.setHealth(health);
        }
    }

    @Override
    public void attack(Alien Zzz){
        System.out.println("Wizard " + this.name + " is attacking with fireballs.");
        Random rand = new Random();

        if(rand.nextBoolean()){
            Zzz.defend();
        }
        else{
            int health =  Zzz.getHealth();
            //check to see that health doesn't fall below zero
            if(health>0){
                health-=15;
                if(health<0){
                    health=0;
                }
                Zzz.setHealth(health);
            }

        }
    }
}

public class Main {

    public static void main(String[] args) {
        Human h = null;
        String name;
        Alien aliens[] = new Alien[4];
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your character's name:");

            name = input.nextLine();
            //Check to see name only contains letters and numbers
            if (!name.matches("^[a-zA-Z0-9]*$")) {
                System.out.println("Invalid Name");
                break;
            }
            System.out.println("Choose type of your character:\n\nPress 1 to choose warrior.\nPress 2 to choose wizard.");
            int choice;
            choice = input.nextInt();
            if (choice == 1) {
                h = new Warrior(name);
            } else if (choice == 2) {
                h = new Wizard(name);
            } else {
                System.out.println("Invalid choice enter 1 or 2");
                break;
            }

            for (int i = 0; i < 4; i++)
                aliens[i] = new Alien();

            Random rand = new Random();

            int count = 0;
            System.out.println("Enter any key to start the game:");
            System.out.println("Menu:\n\nPress 1 to attack random alien.\nPress 2 to speak.\nPress 3 to explore.\nPress 4 to jump.\nPress 5 to heal or capture.");
            while (true) {
                choice = input.nextInt();
                if (choice == 1) {
                    int i = rand.nextInt(4);
                    h.attack(aliens[i]);
                    if (aliens[i].getHealth() == 0) {
                        System.out.println("You killed a alien.");
                        count++;
                        if (count == aliens.length) {
                            System.out.println("You Won!");
                            break;
                        }
                    }
                } else if (choice == 2) {
                    h.speak();
                } else if (choice == 3) {
                    h.explore();
                } else if (choice == 4) {
                    h.jump();
                } else if (choice == 5) {
                    if (h instanceof Wizard) {
                        h.heal(h);
                    } else if (h instanceof Warrior) {
                        h.capture();
                    }
                }
                //Random attack on Player's Character
                if (rand.nextBoolean()) {
                    aliens[rand.nextInt(4)].attack(h);
                    if (h.getHealth() == 0) {
                        System.out.println("Character has been wasted");
                        break;
                    }
                }
                System.out.println("Enter you next move between 1-5:");
            }


        }
    }
}
