/** 
 * Abstract class for GameCharacters. Player and Monster are both
 * subclasses of this abstract class.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

import java.util.ArrayList;

public abstract class GameCharacter {
	
	protected String name;
	protected int maxHealth;
	protected int health;
	protected int attackPower;
	ArrayList<Item> itemList = new ArrayList<Item>();

	/**
	 * Constructor for a GameCharacter.
	 *
	 * @param name the name of the character
	 * @param health the number of health points the character has
	 * @param attackPower the attack power of the character
	 */
	public GameCharacter(String name, int health, int attackPower) {
		this.name = name;
		this.health = health;
		this.attackPower = attackPower;
	}

	/** 
	 * Default constructor for a GameCharacter.
	 * Initializes name to null and health/attackPower to 0.
	 */
	public GameCharacter() {
		this.name = null;
		this.health = 0;
		this.attackPower = 0;
	}

	/** 
	 * Method responsible for removing damage taken from the overall health
	 * of this GameCharacter.
	 *
	 * @param damage the number of health points to remove
	 */
	public void takeDamage(int damage) {
		this.health = health - damage;
	} 

	/**
	 * Getter for GameCharacter's name.
	 *
	 * @return the name of the GameCharacter
	 */
	public String getName() {
		return name;
	} 

	/**
	 * Getter for GameCharacter's attackPower.
	 *
	 * @return the attack power of the GameCharacter
	 */
	public int getAttackPower() {
		return attackPower;
	} 

	/**
	 * Getter for GameCharacter's health.
	 *
	 * @return the current health of the GameCharacter
	 */
	public int getHealth() {
		return health;
	} 

	/**
	 * Overridden toString method to convert GameCharacter objects to a
	 * string by including name, current health (HP), and attack power
	 * (AP).
	 *
	 * @return the String representation of the GameCharacter
	 */
	@Override
	public String toString() {
		String val = "Name: " + name + "\nHP: " + health + "\nAP: " + attackPower;
		return val;
	}
}