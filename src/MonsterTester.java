// Alejandro Mijares
// January 18, 2023
// Panther ID: 3145563
// Program Version: 1.0
// Java AdoptOpenJDK 16
// This program contains a short game that utilizes the Monster class
// The user is given an adventurer, also of the monster class
// The user will then encounter at least 10 monsters, with which they can interact
// The user can:
// - See all the stats of each monster as they are encountered
// - Change the monster's stats during an encounter, though it is discouraged as "cheating"
// - Insult or attack the monster to deplete its hit points, and defeat it
// - Run away from the monster, which will trigger the next monster encounter or end the game
// Further instructions available through in-game text

import java.util.ArrayList;
import java.util.Scanner;
public class MonsterTester {
    public static void main(String[] args) {
        // Array of monsters
        ArrayList<Monster> monsters = new ArrayList<>();
        // Populating monster array with monsters from the Dungeons and Dragons Monster Manual
        // Stats are taken directly from the manual
        // Missing values indicate the monster's lack in that stat
        // Empty string in language means the monster cannot communicate
        monsters.add(new Monster("Aboleth", 17, 135, "Deep Speech", 10, "Lawful Evil"));
        monsters.add(new Monster("Beholder", 18, 180, "Deep Speech", 13, "Lawful Evil"));
        monsters.add(new Monster("Cyclops", 14, 138, "Giant", 6, "Chaotic Neutral"));
        monsters.add(new Monster("Displacer Beast", 13, 85, "", 3, "Lawful Evil"));
        monsters.add(new Monster("Ancient Blue Dragon", 22, 481, "Common", 23, "Lawful Evil"));
        monsters.add(new Monster("Gnoll", 15, 22, "Gnoll", 1, "Chaotic Evil"));
        monsters.add(new Monster("Hydra", 15, 172, "", 8, ""));
        monsters.add(new Monster("Kobold", 12, 7, "Common", 0, "Lawful Evil"));
        monsters.add(new Monster("Mind Flayer", 15, 71, "Deep Speech", 7, "Lawful Evil"));
        monsters.add(new Monster("Sphinx", 17, 136, "Common", 11, "Lawful Neutral"));

        // Create an adventurer for the user to role-play as, using Monster class as a template
        Monster adventurer = new Monster("Steve", 16, 125, "Common", 10, "Chaotic Good");
        System.out.println("Welcome to mini DND! An adventurer has been created for you!");
        adventurer.printStats();
        // Scanner object to take user input
        Scanner input = new Scanner(System.in);
        // Input is changed to lower case to prevent capitalization errors
        // User is allowed to change a single stat
        String changes = input.nextLine().toLowerCase();
        System.out.println("Would you like to make a change to your player?\n");
        if (changes.equals("yes")){
            adventurer.makeChanges();
        }
        else if (changes.equals("no")){
            System.out.println("Not even a name change? Well, ok, Steve it is.");
        }
        else {
            System.out.println("That didn't look like a yes. The adventure continues!");
        }
        // User is asked if they would like to add a monster to the Monster array
        System.out.println("Is there a specific monster you want to encounter? Yes or no");
        String addMonster = input.nextLine().toLowerCase();
        if (addMonster.equals("yes")){
            Monster newMonster = new Monster();
            System.out.println("Awesome!");
            System.out.println("Please enter the monster's name");
            newMonster.setMonsterName(input.nextLine());
            System.out.println("New name is: " + newMonster.getMonsterName());
            System.out.println("Please enter the monster's armor class: (must be an integer)");
            newMonster.setMonsterArmorClass(input.nextInt());
            System.out.println("The armor class is now " + newMonster.getMonsterArmorClass());
            System.out.println("Please enter the monster's hit points: (must be an integer)");
            newMonster.setMonsterHitPoints(input.nextInt());
            System.out.println("New hit points: " + newMonster.getMonsterHitPoints());
            System.out.println("Please enter the monster's language: ");
            newMonster.setMonsterLanguage(input.nextLine());
            System.out.println("New language: " + newMonster.getMonsterLanguage());
            System.out.println("Please enter the monster's level: (must be an integer)");
            newMonster.setMonsterLevel(input.nextInt());
            System.out.println("New level: " + newMonster.getMonsterLevel());
            System.out.println("Please enter the monster's alignment: (Lawful, Neutral, Chaotic) (Good, Neutral, Evil)");
            System.out.println("ie: Lawful Good, Chaotic Evil. Double neutral is True Neutral");
            newMonster.setMonsterAlignment(input.nextLine());
            System.out.println("New alignment: " + newMonster.getMonsterAlignment());
            System.out.println("Ok, monster created!");
            newMonster.printStats();
            monsters.add(newMonster);
        }
        else {
            System.out.println("No problem, this world is full monsters already!");
        }
        System.out.println("\nYou are in a taver... no, you're in a forest. Yes, a forest.");
        // For loop to iterate through monster list and have the user encounter each monster
        for (int i=0; i < monsters.size(); i++){
            Monster currentMonster = monsters.get(i);
            System.out.println("A " + currentMonster.getMonsterName() + " appears in front of " + adventurer.getMonsterName()+"!");
            System.out.println("Yeah, came out of nowhere.");
            // Action for loop with 4 iterations, giving the user 4 opportunities, j, to interact with each monster
            // The action loop counts down from 4, alerting the user to how many actions are left
            // Actions that break the loop set the action counter to 0
            for (int j=4; j > 0; j--) {
                System.out.println("\nYou have " + j + " actions left!");
                System.out.println("\nYou can: (Please enter a number 1-6)");
                System.out.println("1. Investigate the monster.");
                System.out.println("2. Size up the monster and see if you can best it.");
                System.out.println("3. Insult the monster.");
                System.out.println("4. Attack the monster.");
                System.out.println("5. Run!");
                System.out.println("6. Change the monster's stats. Like a cheater.");
                String choice = input.nextLine();
                // Investigate the monster, revealing all of its attributes
                if (choice.equals("1")) {
                    currentMonster.printStats();
                }
                // Compare user level to monster level
                else if (choice.equals("2")) {
                    currentMonster.canDefeat(adventurer.getMonsterLevel());
                }
                // Insult monster
                else if (choice.equals("3")) {
                    currentMonster.insultMonster(adventurer.getMonsterLanguage());
                    // If the monster understands the insult, it will die
                    // This sets the action counter to zero, breaking out of the loop
                    if (currentMonster.getMonsterHitPoints() == 0){
                        j = 0;
                    }
                }
                // Attack the monster. If it dies, break the loop
                else if (choice.equals("4")) {
                    currentMonster.attackMonster(adventurer.getMonsterLevel());
                    if (currentMonster.getMonsterHitPoints() == 0){
                        j = 0;
                    }
                }
                // Tactical retreat. Maybe its fear, maybe its smart. Ends loop
                else if (choice.equals("5")){
                    System.out.println("You successfully ran away! No shame in that.");
                    j = 0;
                }
                // Allows the player to edit the monster's stats
                // Each change consumes one action
                else if (choice.equals("6")) {
                    currentMonster.makeChanges();
                }
                // If the user input is out of scope, the monster will get bored and leave.
                else {
                    System.out.println("The " + currentMonster.getMonsterName() + " grew bored and left.");
                    j = 0;
                }
            }
            System.out.println("Another monster down! The adventure continues!");
        }
        // Congratulatory message for completing the game
        System.out.println("Congratulations " + adventurer.getMonsterName() + "!!");
        System.out.println("You defeated " + monsters.size() + " monsters!");
    }
}