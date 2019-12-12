/** 
 * Object class for Monsters.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

import java.util.Random;

public class Monster extends GameCharacter {
	
	private int xp;
	private Random ranGen;
	private Potion healthPotion = new Potion("Health Potion", 1, 0, 30);
	private Potion manaPotion = new Potion("Mana Potion", 1, 3, 0);
	private Potion superPotion = new Potion("Super Potion", 1, 3, 30);
	private Weapon weapon = new Weapon("Weapon", 1, 16);
	private Weapon otherWeapon = new Weapon("Other Weapon", 1, 18);

	/**
	 * Constructor for a Monster object.
	 *
	 * @param name the name of the Monster
	 * @param health the number of health points the Monster has
	 * @param attackPower the attack power of the Monster
	 * @param xp the amount of experience killing the Monster will give a Player
	 */
	public Monster(String name, int health, int attackPower, int xp) {
		this.name = name;
		this.health = health;
		this.attackPower = attackPower;
		this.xp = xp;

		//Create a new Random object for use in the following switch statement
		ranGen = new Random();

		//Responsible for randomly adding items to Monsters
		//Case 0 = no item
		//Case 1 = health potion
		//Case 2 = mana potion
		//Case 3 = weapon
		switch (ranGen.nextInt(11)) {
			case 0:
				break;
			case 1: 
				break;
			case 2:
				this.itemList.add(healthPotion);
				break;
			case 3:
				this.itemList.add(healthPotion);
				break;
			case 4:
				this.itemList.add(healthPotion);
				break;
			case 5:
				this.itemList.add(manaPotion);
				break;
			case 6:
				this.itemList.add(manaPotion);
				break;
			case 7:
				this.itemList.add(manaPotion);
				break;
			case 8:
				this.itemList.add(weapon);
				break;
			case 9:
				this.itemList.add(otherWeapon);
				break;
			case 10:
				this.itemList.add(superPotion);
				break;
			default:
				System.out.println("Error with ranGen");
				break;
		}
	} 

	/**
	 * Constructor for a Monster object with a weapon.
	 *
	 * @param name the name of the Monster
	 * @param health the number of health points the Monster has
	 * @param attackPower the attack power of the Monster
	 * @param xp the amount of experience killing the Monster will give a Player
	 * @param weapon the weapon the Monster is equipped with
	 */
	public Monster(String name, int health, int attackPower, int xp, Weapon weapon) {
		super(name, health, attackPower);
		this.itemList.add(weapon);
		this.name = name;
		this.health = health;
		this.attackPower = attackPower;
		this.xp = xp;
	} 

	/**
	 * Default constructor for a Monster.
	 * Initializes name to null and health/attackPower/xp to 0.
	 */
	public Monster() {
		this.name = null;
		this.health = 0;
		this.attackPower = 0;
		this.xp = 0;
	}

	/**
	 * Monster's attack method.
	 *
	 * @param thePlayer the Player object to attack
	 */
	public void attack(Player thePlayer) {
		thePlayer.takeDamage(attackPower);
		System.out.println("The " + this.name + " attacks you for " + this.attackPower + " damage.");
	}

	/**
	 * The method to enact the Monster's turn on a Player.
	 *
	 * @param thePlayer the player to act upon when enacting the Monster's turn
	 */
	public void takeTurn(Player thePlayer) {
		this.attack(thePlayer);
	}

	/**
	 * Getter for the Monster's xp.
	 *
	 * @return the amount of experience points the Monster will give a Player upon defeat
	 */
	public int getXP() {
		return xp;
	}

	/**
	 * Overridden toString method to include the xp value of the Monster.
	 *
	 * @return the String representation of the Monster, including xp value
	 */
	@Override
	public String toString() {
		String val = "Name: " + name + "\nHP: " + health + "\nAP: " + attackPower + "\nXP: " + xp;
		return val;
	}
}