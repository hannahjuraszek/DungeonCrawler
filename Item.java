/** 
 * Abstract class for Items. Potion and Weapon are both subclasses of this 
 * abstract class.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

public abstract class Item {

	protected String name;
	protected int quantity;
	
	/**
	 * Constructor for an Item object.
	 *
	 * @param name the name of the item
	 * @param quantity the quantity of the item
	 */
	public Item(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	} 

	/**
	 * Getter for the Item's name.
	 *
	 * @return the name of the Item
	 */
	public String getName() {
		return name;
	} 

	/**
	 * Getter for the Item's quantity.
	 *
	 * @return the quantity of the Item
	 */
	public int getQuantity() {
		return quantity;
	} 

	/**
	 * Setter for the Item's quantity.
	 *
	 * @param quantity the new value to set the Item's quantity to
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Method to increase the Item's quantity.
	 *
	 * @param amount the amount to increase the Item's quantity
	 */
	public void increaseQuantity(int amount) {
		this.quantity = this.quantity + amount;
	}

	/**
	 * Method to decrease the Item's quantity.
	 *
	 * @param amount the amount to decrease the Item's quantity
	 */
	public void decreaseQuantity(int amount) {
		this.quantity = this.quantity - amount;
	}

	/**
	 * Getter for the Item's mana restore.
	 *
	 * @return the amount of mana the Item restores when used
	 */
	public int getManaRestore() {
		return 0;
	} 

	/**
	 * Getter for the Item(Potion)'s health restore.
	 *
	 * @return the amount of health the Item(Potion) restores when used
	 */
	public int getHealthRestore() {
		return 0;
	}

	/**
	 * Getter for the Item(Weapon)'s weapon damage.
	 *
	 * @return the amount of damage the Item(Weapon) deals when attacking
	 */
	public int getWeaponDamage() {
		return 0;
	}
} 