/** 
 * Object class for Weapons.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

public class Weapon extends Item {
	
	private int weaponDamage;

	/**
	 * Constructor for a Weapon object.
	 *
	 * @param name the name of the Weapon
	 * @param quantity the quantity of the Weapon
	 * @param weaponDamage the amount of damage the Weapon deals when attacking
	 */
	public Weapon(String name, int quantity, int weaponDamage) {
		super(name, quantity);
		this.name = name;
		this.quantity = quantity;
		this.weaponDamage = weaponDamage;
	} 

	/**
	 * Getter for the Weapon's weapon damage.
	 *
	 * @return the amount of damage the Weapon deals when attacking
	 */
	@Override
	public int getWeaponDamage() {
		return weaponDamage;
	}
} 