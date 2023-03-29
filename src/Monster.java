// Alejandro Mijares
// January 18, 2023
// Panther ID: 3145563
// Program Version: 1.0
// Java Version: 16
// This program contains the Monster class, with the monster's stats and functions for interacting with the monster
// Though technically considered objects, most monsters have feelings and should not be addressed as objects
// This class is used by MonsterTester to create instances of Monsters
// There are individual getter and setter objects for each attribute
// There is also an interactive function to allow the user to select changes from a list
// There are functions to attack and insult the monster with another instance of the Monster class
import java.util.Scanner;

public class Monster {
    // Instance variables
    private String monsterName; // Name of Monster type (or monster's given name)
    private int monsterArmorClass; // Monster's defense capability, or how well it deflects hits
    private int monsterHitPoints; // Monster vitality, or how many hits it can take
    private String monsterLanguage; // Monster's main language
    private int monsterLevel; // How challenging the monster is to fight
    private String monsterAlignment; // Monster's ethical orientation

    // Empty constructor for blank monster
    public Monster() {
        monsterName = "";
        monsterArmorClass = 0;
        monsterHitPoints = 0;
        monsterLanguage = "";
        monsterLevel = 0;
        monsterAlignment = "";
    }
    // Standard Constructor that takes arguments for each attribute
    public Monster(String name, int armorClass, int hitPoints, String language, int level, String alignment) {
        monsterName = name;
        monsterArmorClass = armorClass;
        monsterHitPoints = hitPoints;
        monsterLanguage = language;
        monsterLevel = level;
        monsterAlignment = alignment;
    }
    // Getters and setters for each monster attribute
    public String getMonsterName() {
        return monsterName;
    }

    public int getMonsterArmorClass() {
        return monsterArmorClass;
    }

    public int getMonsterHitPoints() {
        return monsterHitPoints;
    }

    public String getMonsterLanguage() {
        return monsterLanguage;
    }

    public int getMonsterLevel() {
        return monsterLevel;
    }

    public String getMonsterAlignment() {
        return monsterAlignment;
    }

    public void setMonsterName(String Name) {
        monsterName = Name;
    }

    public void setMonsterArmorClass(int armorClass) {
        monsterArmorClass = armorClass;
    }

    public void setMonsterHitPoints(int hitPoints) {
        monsterHitPoints = hitPoints;
    }

    public void setMonsterLanguage(String language) {
        monsterLanguage = language;
    }

    public void setMonsterLevel(int level) {
        monsterLevel = level;
    }

    public void setMonsterAlignment(String alignment) {
        monsterAlignment = alignment;
    }

    // Function that prints the monster's stats to the console
    // Each stat is numbered for ease of alteration
    public void printStats(){
        System.out.println("1. Name: " + getMonsterName());
        System.out.println("2. Armor Class: " + getMonsterArmorClass());
        System.out.println("3. Hit Points: " + getMonsterHitPoints());
        System.out.println("4. Language: " + getMonsterLanguage());
        System.out.println("5. Level: " + getMonsterLevel());
        System.out.println("6. Alignment: " + getMonsterAlignment());
    }

    // Function that allows the user to change 1 of the monster's attributes per call
    public void makeChanges() {
        printStats();
        System.out.println("Enter a number, 1-6, of which you would like to change, or 0 to cancel");
        System.out.print("Warning: Only one change is allowed at a time, due to insufficient vespene gas.\n");
        Scanner input = new Scanner(System.in);
        String change = input.nextLine().toLowerCase();
        if (change.equals("1")){
            System.out.println("Please enter the new name");
            setMonsterName(input.nextLine());
            System.out.println("New name is: " + getMonsterName());
        }
        else if (change.equals("2")) {
            System.out.println("Please enter the new armor class: (must be an integer)");
            setMonsterArmorClass(input.nextInt());
            System.out.println("The armor class is now" + getMonsterArmorClass());
        }
        else if (change.equals("3")){
            System.out.println("Please enter the new hit points: (must be an integer)");
            setMonsterHitPoints(input.nextInt());
            System.out.println("New hit points: " + getMonsterHitPoints());
        }
        else if (change.equals("4")){
            System.out.println("Please enter the new language: ");
            setMonsterLanguage(input.nextLine());
            System.out.println("New language: " + getMonsterLanguage());
        }
        else if (change.equals("5")){
            System.out.println("Please enter the new level: (must be an integer)");
            setMonsterLevel(input.nextInt());
            System.out.println("New level: " + getMonsterLevel());
        }
        else if (change.equals("6")){
            System.out.println("Please enter the new alignment: (Lawful, Neutral, Chaotic) (Good, Neutral, Evil)");
            System.out.println("ie: Lawful Good, Chaotic Evil. Double neutral is True Neutral");
            setMonsterAlignment(input.nextLine());
            System.out.println("New alignment: " + getMonsterAlignment());
        }
        else {
            System.out.println("Either by choice or by accident, you have lost your ability to make changes");
        }
    }

    // Function that determines whether the user can defeat the monster
    // Takes a given level and compares it to the monster's level
    public void canDefeat(int pcLevel){
        if (pcLevel > monsterLevel){
            System.out.println("Your character can defeat this monster.");
        }
        else if (pcLevel == monsterLevel){
            System.out.println("Your character will struggle to defeat this monster.");
        }
        else {
            System.out.println("Your character will have a bad time fighting this monster.");
        }
    }

    // Function that allows the user to attack the monster
    // Multiplies user's level by 6 to simulate max roll of a six sided dice and subtracts that from monster's HP
    // Announces the monster's condition after the attack (dead or alive)
    public void attackMonster(int pcLevel){
        setMonsterHitPoints(monsterHitPoints - pcLevel * 6);
        if (monsterHitPoints > 0){
            System.out.println("The monster still lives!");
        }
        else{
            System.out.println("The monster is slain!");
        }
    }

    // Function to allow the user to insult the monster
    // If the user speaks the monster's language, the monster is killed by the insult
    // and its hit points are set to zero.
    // Calculates the equality of the language variables and updates the monster's hit points accordingly.
    public void insultMonster(String pcLanguage){
        if (monsterLanguage.equals(pcLanguage)){
            setMonsterHitPoints(0);
            System.out.println("The monster has lost the will to live!");
        }
        else{
            System.out.println("The monster did not understand your taunt.");
        }
    }
}