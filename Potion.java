/** 
 * Object class for Potions.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

public class Potion extends Item {
	
	private int manaRestore;
	private int healthRestore;

	/**
	 * Constructor for a Potion.
	 *
	 * @param name the name of the Potion
	 * @param quantity the quantity of the Potion
	 * @param manaRestore the amount of mana the Potion restores when used
	 * @param healthRestore the amount of health the Potion restores when used
	 */
	public Potion(String name, int quantity, int manaRestore, int healthRestore) {
		super(name, quantity);
		this.name = name;
		this.quantity = quantity;
		this.manaRestore = manaRestore;
		this.healthRestore = healthRestore;
	}

	/**
	 * Getter for the Potion's mana restore.
	 *
	 * @return the amount of mana the Potion restores when used
	 */
	@Override
	public int getManaRestore() {
		return manaRestore;
	} 

	/**
	 * Getter for the Potion's health restore.
	 *
	 * @return the amount of health the Potion restores when used
	 */
	@Override
	public int getHealthRestore() {
		return healthRestore;
	}
} 