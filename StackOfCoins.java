/** 
 * Object class for StackOfCoins.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

public class StackOfCoins extends Item {
	
	/**
	 * Constructor for a StackOfCoins.
	 *
	 * @param name the name of the StackOfCoins
	 * @param quantity the number of coins in the StackOfCoins
	 */
	public StackOfCoins(String name, int quantity) {
		super(name, quantity);
		this.name = name;
		this.quantity = quantity;
	}
}